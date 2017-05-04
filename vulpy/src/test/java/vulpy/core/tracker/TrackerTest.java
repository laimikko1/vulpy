package vulpy.core.tracker;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.*;

public class TrackerTest {

    private static final long SECONDS_TO_NANOSECONDS = 1000000000;
    private static final long MINUTES_TO_NANOSECONDS = 60 * SECONDS_TO_NANOSECONDS;
    private static final long HOURS_TO_NANOSECONDS = 60 * MINUTES_TO_NANOSECONDS;
    private static final long DAY_TO_NANOSECONDS = 24 * HOURS_TO_NANOSECONDS;

    private AtomicLong time;
    private TimeSupplier timeSupplier;

    public TrackerTest() {

    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.time = new AtomicLong(System.nanoTime());
        this.timeSupplier = time::get;
    }

    @After
    public void tearDown() {
    }

    @Test
    public void newTrackerCentisecondsZero() {
        Tracker tracker = new Tracker(86400000);
        assertEquals(tracker.getMilliseconds(),0);
    }

    @Test
    public void centisecondsCorrectAfterStartAndSixMinutesTwentySeconds() {
        Tracker tracker = new Tracker(this.timeSupplier);
        tracker.startTracking();
        long sixMinutesTwentySeconds = 6 * MINUTES_TO_NANOSECONDS + 20 * SECONDS_TO_NANOSECONDS;
        time.addAndGet(sixMinutesTwentySeconds);
        long change = nanosecondsToCentiseconds(sixMinutesTwentySeconds);
        System.out.println(change + " " + sixMinutesTwentySeconds);
        System.out.println(tracker.getMilliseconds());
        assertEquals(change, tracker.getMilliseconds());
    }

    @Test
    public void centisecondsCorrectIfStop() {
        Tracker tracker = new Tracker(this.timeSupplier);
        tracker.startTracking();
        this.time.addAndGet(6 * HOURS_TO_NANOSECONDS + 12 * SECONDS_TO_NANOSECONDS);
        tracker.stopTracking();
        this.time.addAndGet(1* DAY_TO_NANOSECONDS);
        long change = nanosecondsToCentiseconds(6 * HOURS_TO_NANOSECONDS + 12 * SECONDS_TO_NANOSECONDS);
    }

    @Test
    public void centisecondsCorrectIfStopAndStart() {
        Tracker tracker = new Tracker(this.timeSupplier);
        tracker.startTracking();
        for (int i = 0; i < 5; i++) {
            tracker.startTracking();
            this.time.addAndGet(1 * MINUTES_TO_NANOSECONDS);
            tracker.stopTracking();
            this.time.addAndGet(1000);
        }
        assertEquals(nanosecondsToCentiseconds(5 * MINUTES_TO_NANOSECONDS),tracker.getMilliseconds());
    }

    public long nanosecondsToCentiseconds(long nanoseconds){
        return nanoseconds / 10000000;
    }
}

