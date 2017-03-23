package vulpy.core.tracker;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class TrackerTest {

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
    }

    @After
    public void tearDown() {
    }

    @Test
    public void newTrackerSecondsZero() {
        Tracker tracker = new Tracker();
        assertEquals(tracker.getSeconds(),0);
    }

    @Test
    public void secondsCorrectAfterStartAndFiveSecondSleep() throws InterruptedException {
        Tracker tracker = new Tracker();
        tracker.startTracking();
        TimeUnit.SECONDS.sleep(5);
        assertEquals(5,tracker.getSeconds());
    }

    @Test
    public void secondsCorrectIfStop() throws InterruptedException {
        Tracker tracker = new Tracker();
        tracker.startTracking();
        TimeUnit.SECONDS.sleep(2);
        tracker.stopTracking();
        TimeUnit.SECONDS.sleep(1);
        assertEquals(2,tracker.getSeconds());
    }

    @Test
    public void secondsCorrectIfStopAndStart() throws InterruptedException {
        Tracker tracker = new Tracker();
        tracker.startTracking();
        for (int i = 0; i < 5; i++) {
            tracker.startTracking();
            TimeUnit.SECONDS.sleep(1);
            tracker.stopTracking();
            TimeUnit.SECONDS.sleep(1);
        }
        assertEquals(5,tracker.getSeconds());
    }
}

