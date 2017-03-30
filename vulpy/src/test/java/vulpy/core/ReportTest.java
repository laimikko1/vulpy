package vulpy.core;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class ReportTest {

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
    }

    @After
    public void tearDown() {
    }

    @Test
    public void returnRightName(){
        Tag tag = new Tag("tagi");
        assertEquals(tag.getName(),"tagi");
    }

    @Test
    public void setRightName(){
        Tag tag = new Tag("tagi");
        tag.setName("tag");
        assertEquals(tag.getName(),"tag");
    }
}

