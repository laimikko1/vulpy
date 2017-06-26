package vulpy.core;

import org.junit.*;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TagTest {

    private Tag tag;

    public TagTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.tag = new Tag("Vulpy");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void rightTimeAfterStartAndStop(){
        this.tag.startTracking();
        this.tag.stopTracking();
        long time = this.tag.getTime();
        assertEquals(time, this.tag.getTime());
    }

    @Test
    public void rightName(){
        Tag tag = new Tag("tagi");
        tag.setName("tag");
        assertEquals("tag",tag.getName());
        assertEquals("Vulpy", this.tag.getName());
    }

    @Test
    public void notShutDownIfTwoStarts() throws InterruptedException {
        this.tag.startTracking();
        this.tag.startTracking();
        this.tag.stopTracking();
        long time = this.tag.getTime();
        TimeUnit.MILLISECONDS.sleep(10);
        assertNotEquals(time, this.tag.getCalendar().getMilliSeconds());
    }

    @Test
    public void newTagTrackedTimeIsZero() {
        assertEquals(0, this.tag.getTime());
    }

    @Test
    public void shutDownIfOnlyOneStart() throws InterruptedException {
        this.tag.startTracking();
        this.tag.stopTracking();
        long time = this.tag.getCalendar().getMilliSeconds();
        TimeUnit.MILLISECONDS.sleep(10);
        assertEquals(time, this.tag.getCalendar().getMilliSeconds());
    }
}

