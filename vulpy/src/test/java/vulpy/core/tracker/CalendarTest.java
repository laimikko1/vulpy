package vulpy.core.tracker;

import org.junit.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.assertEquals;

public class CalendarTest {

    private static final long SECONDS_TO_NANOSECONDS = 1000000000;
    private static final long MINUTES_TO_NANOSECONDS = 60 * SECONDS_TO_NANOSECONDS;
    private static final long HOURS_TO_NANOSECONDS = 60 * MINUTES_TO_NANOSECONDS;
    private static final long DAY_TO_NANOSECONDS = 24 * HOURS_TO_NANOSECONDS;

    private Calendar calendar;
    private TimeSupplier timeSupplier;
    private AtomicLong time;

    public CalendarTest() {
        this.calendar = new Calendar();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.time = new AtomicLong(System.currentTimeMillis());
        this.timeSupplier = time::get;
        Tracker tracker = new Tracker(timeSupplier);
        for (int i = 0; i < 5; i++) {
            this.calendar.putOneDateAndTracker("1" + i + "/12/199" + i,tracker);
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getCorrectDate(){
        assertEquals(currentDate(),this.calendar.getCurrentDate());
    }

    @Test
    public void getCorrectStringListOfDates(){
        ArrayList<String> Dates = calendar.getStringDates();
        boolean b = false;
        for (int i = 0; i < 5; i++) {
            String contains = Dates.get(i);
            if(contains.equals("10/12/1990")){
                b = true;
            }
        }
        assertEquals(true,b);
        assertEquals(5,Dates.size());
    }

    @Test
    public void rightTimeAfterSevenHoursTenMinutesFiveSeconds(){
        this.calendar.putOneDateAndTracker(currentDate(),new Tracker(timeSupplier));
        this.calendar.start();
        long sevenHoursTenMinutesFiveSeconds = 7 * HOURS_TO_NANOSECONDS + 10 * MINUTES_TO_NANOSECONDS + 5 * SECONDS_TO_NANOSECONDS;
        time.addAndGet(sevenHoursTenMinutesFiveSeconds);
        assertEquals(nanosecondsToCentiseconds(sevenHoursTenMinutesFiveSeconds),this.calendar.getSeconds());
    }

    @Test
    public void rightTimeIfStartAndStop(){
        this.calendar.putOneDateAndTracker(currentDate(),new Tracker(timeSupplier));
        this.calendar.start();
        long clock = 12 * HOURS_TO_NANOSECONDS + 12 * MINUTES_TO_NANOSECONDS;
        time.addAndGet(clock);
        this.calendar.stop();
        time.addAndGet(clock);
        assertEquals(nanosecondsToCentiseconds(clock),this.calendar.getSeconds());
    }

    @Test
    public void ifNotContainsCurrentDate(){
        assertEquals(5,this.calendar.getCalendarSize());
        this.calendar.putOneDate("12/12/1982");
        assertEquals(6,this.calendar.getCalendarSize());
        this.calendar.putOneDate("12/12/1982");
        assertEquals(6,this.calendar.getCalendarSize());
    }

    public String currentDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public long nanosecondsToCentiseconds(long nanoseconds){
        return nanoseconds / 10000000;
    }
}


