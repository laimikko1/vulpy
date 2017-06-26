package vulpy.core;

import org.junit.*;
import vulpy.core.tracker.Calendar;
import vulpy.core.tracker.TimeSupplier;
import vulpy.core.tracker.Tracker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.assertEquals;

public class ReportTest {

    private static final long SECONDS_TO_NANOSECONDS = 1000000000;
    private static final long MINUTES_TO_NANOSECONDS = 60 * SECONDS_TO_NANOSECONDS;
    private static final long HOURS_TO_NANOSECONDS = 60 * MINUTES_TO_NANOSECONDS;
    private static final long DAY_TO_NANOSECONDS = 24 * HOURS_TO_NANOSECONDS;

    private Report report;
    private Project project;
    private TimeSupplier timeSupplier;
    private AtomicLong time;

    public ReportTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.project = new Project("Vulpy", new ArrayList<>());
        Calendar calendar = this.project.getCalendar();
        this.time = new AtomicLong(System.currentTimeMillis());
        this.timeSupplier = time::get;
        Tracker tracker = new Tracker(timeSupplier, 86400000);
        calendar.putOneDateAndTracker(currentDate(),tracker);
        this.report = new Report(this.project);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void correctHoursMinutesAndSecondsString(){
        this.project.startTracking();
        long threeHoursFiveMinutesAndTenSeconds = 3 * HOURS_TO_NANOSECONDS + 5 * MINUTES_TO_NANOSECONDS + 10 * SECONDS_TO_NANOSECONDS;
        this.time.addAndGet(threeHoursFiveMinutesAndTenSeconds);
        this.project.stopTracking();
        String threeHoursFiveMinutesAndTenSecondsString = this.report.getHoursMinutesAndSeconds();
        assertEquals(threeHoursFiveMinutesAndTenSecondsString, "03h 05min 10sec");

    }

    public String currentDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
}

