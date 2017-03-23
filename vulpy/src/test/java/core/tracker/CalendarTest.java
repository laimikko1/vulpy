package core.tracker;

import org.junit.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class CalendarTest {

    private Calendar calendar;

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
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getCorrectDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        assertEquals(dateFormat.format(date),this.calendar.getCurrentDate());
    }

    @Test
    public void getCorrectStringListOfDates(){
        for (int i = 1; i < 8; i++) {
            this.calendar.putOneDate("0" + i + "/12/1995");
        }
        ArrayList<String> Dates = calendar.getStringDates();
        boolean b = false;
        for (int i = 0; i < 7; i++) {
            String contains = Dates.get(i);
            if(contains.equals("01/12/1995")){
                b = true;
            }
        }
        assertEquals(true,b);
        assertEquals(7,Dates.size());
    }

    @Test
    public void rightTimeAfterTwoSeconds() throws InterruptedException {
        this.calendar.start();
        TimeUnit.SECONDS.sleep(2);
        assertEquals(2,this.calendar.getSeconds());
    }
}


