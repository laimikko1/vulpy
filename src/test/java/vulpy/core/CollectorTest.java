package vulpy.core;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CollectorTest {

    Collector collector;
    List<Project> projectList;

    public CollectorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.projectList = new ArrayList<>();
        this.collector = new Collector();
        for (int i = 0; i < 5; i++) {
            this.projectList.add(new Project("" + i,new ArrayList<String>()));
        }
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("Java");
        this.projectList.add(new Project("vulpy",stringList));
        stringList.add("Javascript");
        this.projectList.add(new Project("olor",stringList));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void correctSize(){
        assertEquals(this.collector.getProjectListSize(), 0);
        this.collector.addProject(projectList.get(0));
        assertEquals(this.collector.getProjectListSize(), 1);
    }

    @Test
    public void startTrackingWorks() throws InterruptedException {
        Project project = projectList.get(this.projectList.size() -1);
        this.collector.addProject(project);
        assertEquals(project.getCalendar().getMilliSeconds(),0l);
        this.collector.startTrackingByProject(project);
        Thread.sleep(10);
        assertNotEquals(0l, project.getCalendar().getMilliSeconds());
}

    @Test
    public void stopTrackingWorks() throws InterruptedException {
        Project project = projectList.get(this.projectList.size() - 1);
        this.collector.addProject(project);
        assertEquals(project.getCalendar().getMilliSeconds(),0l);
        this.collector.startTrackingByProject(project);
        this.collector.stopTrackingByProject(project);
        long seconds = project.getCalendar().getMilliSeconds();
        Thread.sleep(10);
        assertEquals(seconds, project.getCalendar().getMilliSeconds());
    }

    @Test
    public void getTagsWorksOk(){
        this.collector.addProject(projectList.get(0));
        assertEquals(this.collector.getTag("vulpy"), null);
        this.collector.addProject(projectList.get(projectList.size() - 1));
        assertEquals("Java", this.collector.getTag("Java").getName());
    }

    @Test
    public void rightAmountTags(){
        this.collector.addProject(projectList.get(projectList.size() - 1));
        assertEquals(2, this.collector.getTagMap().size());
        this.collector.addProject(projectList.get(projectList.size() - 2));
        assertEquals(2, this.collector.getTagMap().size());
    }

}

