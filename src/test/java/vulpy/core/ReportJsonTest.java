package vulpy.core;

import org.junit.*;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
    public void correctJson() {
        assertEquals("{\"Olor\":{}}",this.project.getReport().getJson().getJson());
    }

    @Test
    public void correctJsonAfterTracking() throws InterruptedException {
        this.project.startTracking();
        TimeUnit.MILLISECONDS.sleep(10);
        this.project.stopTracking();
        assertEquals("{\"Olor\":{\"" + this.project.getCalendar().getCurrentDate() + "\":" + this.project.getCalendar().getSeconds() +"}}",this.project.getReport().getJson().getJson());
    }
}

