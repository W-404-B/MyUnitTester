import org.junit.Test;
//import org.testng.annotations.Test;
import static org.junit.Assert.*;


public class JunitTests {

    @Test
    public void testSetUp() {
        ExecuteTests et = new ExecuteTests("");
        assertNotNull(et);
    }

    @Test
    public void testEmptyClassName() {
        String className = "";
        ExecuteTests et = new ExecuteTests(className);
        assertEquals("ERROR: Class " + className + " does not exist!", et.getInstantiateError());
    }

    @Test
    public void testNoMethodsInClass() {
        String className = "Test2";
        ExecuteTests et = new ExecuteTests(className);
        et.checkMethods();
        assertEquals("Warning: No test methods found in " + className + "!", et.getInstantiateError());
    }

    @Test
    public void testInterfaceNotFound() {
        String className = "Test3";
        ExecuteTests et = new ExecuteTests(className);
        assertFalse(et.checkInterface());
    }

    @Test
    public void testInterfaceFound() {
        String className = "Test1";
        ExecuteTests et = new ExecuteTests(className);
        assertTrue(et.checkInterface());
    }

    @Test
    public void testSuccess() {
        String className = "Test1";
        ExecuteTests et = new ExecuteTests(className);
        et.checkMethods();
        for (int i = 0; i < et.getTestMethods().size(); i++) {
            if (et.getTestMethods().get(i).equals("testIncrement")) {
                assertEquals(et.getTestMethods().get(i) + ": SUCCESS", et.ExecuteTest(i));
            }
        }
    }

    @Test
    public void testFailed() {
        String className = "Test1";
        ExecuteTests et = new ExecuteTests(className);
        et.checkMethods();
        for (int i = 0; i < et.getTestMethods().size(); i++) {
            if (et.getTestMethods().get(i).equals("testFailing")) {
                assertEquals(et.getTestMethods().get(i) + ": FAIL", et.ExecuteTest(i));
            }
        }
    }

    @Test
    public void testException() {
        String className = "Test1";
        ExecuteTests et = new ExecuteTests(className);
        et.checkMethods();
        for (int i = 0; i < et.getTestMethods().size(); i++) {
            if (et.getTestMethods().get(i).equals("testFailingByException")) {
                assertEquals(et.getTestMethods().get(i) + ": FAIL Generated a java.lang.NullPointerException", et.ExecuteTest(i));
            }
        }
    }
}
