package vulpy.core;

import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ReportJsonTest {

    private Project project;

    public ReportJsonTest() {
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
        tags.add("Java");
        tags.add("Javascript");
        this.project = new Project("Olor", tags);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void correctJson(){
        assertEquals(this.project.getReport().getJson().getJson(),"");
    }

}

