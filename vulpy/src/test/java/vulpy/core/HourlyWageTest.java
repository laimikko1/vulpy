package vulpy.core;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class HourlyWageTest {

    private HourlyWage hourlyWage;
    private static int EURO = 1000;
    private static int DOLLAR = 1072;
    private static int CNY = 7391;

    public HourlyWageTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.hourlyWage =new HourlyWage(1200,  "Euro");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void rightAmountEuros() {
        assertEquals(1200,this.hourlyWage.getWage());
        this.hourlyWage.setWage(1500);
        assertEquals(1500,this.hourlyWage.getWage());
    }

    @Test
    public void changeCurrencyWorks(){
        assertEquals("Euro", this.hourlyWage.getUnit());
        this.hourlyWage.setUnit("Cnyasd");
        assertEquals("Euro", this.hourlyWage.getUnit());
        this.hourlyWage.setUnit("Dollar");
        assertEquals("Dollar", this.hourlyWage.getUnit());
        this.hourlyWage.setUnit("CNY");
        assertEquals("CNY", this.hourlyWage.getUnit());
    }

    @Test
    public void getRightSalary(){
        int hours = 12;
        this.hourlyWage.setWage(1200);
        assertEquals(12*1200, this.hourlyWage.getSalary(hours));
    }

    @Test
    public void getRightSymbol(){
        this.hourlyWage.setUnit("Euro");
        assertEquals("€", this.hourlyWage.getSymbol());
        this.hourlyWage.setUnit("CNY");
        assertEquals("¥", this.hourlyWage.getSymbol());
        this.hourlyWage.setUnit("Dollar");
        assertEquals("$", this.hourlyWage.getSymbol());
    }
}

