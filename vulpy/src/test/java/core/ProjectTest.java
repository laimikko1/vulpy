package core;

import core.tracker.Tracker;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ProjectTest {

    private Project project;

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
        this.project = new Project("työmaa", tags);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void rightAmountTags(){
        assertEquals(12, this.project.getTags().size());
    }

    @Test
    public void rightTimeAfterTwoSeconds() throws InterruptedException {
        this.project.startTracking();
        TimeUnit.SECONDS.sleep(2);
        assertEquals(2,this.project.getTime());
    }
}

