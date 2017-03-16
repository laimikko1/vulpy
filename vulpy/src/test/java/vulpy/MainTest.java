package vulpy;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import vulpy.Main;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

    public class MainTest {

        public MainTest() {
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
        public void mainPrintHelloWorld(){
            String message = "Hello World!";
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            PrintStream old = System.out;
            System.setOut(ps);
            Main.main(null);
            System.out.flush();
            System.setOut(old);
            assertEquals(message, baos.toString());
        }

        @Test
        public void mainNotPrintElloWorld(){
            String message = "Ello World!";
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            PrintStream old = System.out;
            System.setOut(ps);
            Main.main(null);
            System.out.flush();
            System.setOut(old);
            assertNotEquals(message, baos.toString());
        }

    }

