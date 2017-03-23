package vulpy.core.tracker;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SystemTimeSupplierTest {

    public SystemTimeSupplierTest() {
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
    public void getRightTime() {
        SystemTimeSupplier systemTimeSupplier = new SystemTimeSupplier();
        assertEquals(nanosecondsToCentiseconds(System.nanoTime()),nanosecondsToCentiseconds(systemTimeSupplier.getNanoseconds()));
    }

    @Test
    public void notGetWrongTime() {
        SystemTimeSupplier systemTimeSupplier = new SystemTimeSupplier();
        assertNotEquals(nanosecondsToCentiseconds(System.nanoTime()),nanosecondsToCentiseconds(systemTimeSupplier.getNanoseconds()) + 1);
    }

    public long nanosecondsToCentiseconds(long nanoseconds){
        return nanoseconds / 10000000;
    }
}


