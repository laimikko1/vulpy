package vulpy.core;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import vulpy.core.tracker.Calendar;
import vulpy.core.tracker.TimeSupplier;
import vulpy.core.tracker.Tracker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.assertEquals;

public class ProjectTest {

    private static final long SECONDS_TO_NANOSECONDS = 1000000000;
    private static final long MINUTES_TO_NANOSECONDS = 60 * SECONDS_TO_NANOSECONDS;
    private static final long HOURS_TO_NANOSECONDS = 60 * MINUTES_TO_NANOSECONDS;
    private static final long DAY_TO_NANOSECONDS = 24 * HOURS_TO_NANOSECONDS;

    private Project project;
    private TimeSupplier timeSupplier;
    private AtomicLong time;

    public ProjectTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ArrayList<String> tags = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            tags.add("Testi" + i + "Testi");
        }
        Calendar calendar = new Calendar();
        this.time = new AtomicLong(System.currentTimeMillis());
        this.timeSupplier = time::get;
        Tracker tracker = new Tracker(timeSupplier, 86400000);
        calendar.putOneDateAndTracker(currentDate(),tracker);
        this.project = new Project("työmaa", tags,calendar);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void rightAmountTags(){
        assertEquals(12, this.project.getTags().size());
    }

    @Test
    public void rightTimeAfterThreeHoursFiveMinutesAndTenSeconds() {
        this.project.startTracking();
        long threeHoursFiveMinutesAndTenSeconds = 3 * HOURS_TO_NANOSECONDS + 5 * MINUTES_TO_NANOSECONDS + 10 * SECONDS_TO_NANOSECONDS;
        this.time.addAndGet(threeHoursFiveMinutesAndTenSeconds);
        assertEquals(nanosecondsToMilliseconds(threeHoursFiveMinutesAndTenSeconds) / 1000,this.project.getTime());
    }

    @Test
    public void rightAnswerAfterStop(){
        this.project.startTracking();
        long threeHoursFiveMinutesAndTenSeconds = 3 * HOURS_TO_NANOSECONDS + 5 * MINUTES_TO_NANOSECONDS + 10 * SECONDS_TO_NANOSECONDS;
        this.time.addAndGet(threeHoursFiveMinutesAndTenSeconds);
        this.project.stopTracking();
        this.time.addAndGet(threeHoursFiveMinutesAndTenSeconds);
        assertEquals(nanosecondsToMilliseconds(threeHoursFiveMinutesAndTenSeconds) / 1000,this.project.getTime());
    }

    @Test
    public void rightAmountTagsAfterAddedTags(){
        for (int i = 0; i < 10; i++) {
            this.project.addTag("tagii");
        }
        assertEquals(22,this.project.getTags().size());
    }

    @Test
    public void rightHourlyWage(){
        assertEquals(0,this.project.getHourlyWage().getWage());
        this.project.setHourlyWage(new HourlyWage(100,"EURO"));
        assertEquals(100,this.project.getHourlyWage().getWage());
    }

    @Test
    public void projectHaveCorrectName(){
        assertEquals("työmaa",this.project.getName());
    }

    @Test
    public void anotherConstructor(){
        Project project = new Project("Työmaa", new ArrayList<>());
        assertEquals("Työmaa",project.getName());
        assertEquals(0,project.getTags().size());
    }

    public String currentDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public long nanosecondsToMilliseconds(long nanoseconds){
        return nanoseconds / 1000000;
    }

}

