import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Course: Applikationsutveckling (Java)
 * Author: William Berglund (id20wbd)
 *
 * This class is a model to run tests that the controller (TestListener) have picked up.
 */
public class ExecuteTests {

    private String className;
    private int setUp;
    private int tearDown;
    private int success;
    private int failed;
    private int exception;
    private ArrayList<String> testMethods;
    private final String interfaceName = "se.umu.cs.unittest.TestClass";
    private Class<?> cl;
    private Method[] m;
    private Object o;
    private String instantiateError;

    public ExecuteTests (String className) {
        this.className = className;
        this.setUp = -1;
        this.tearDown = -1;
        this.testMethods  = new ArrayList<>();
        this.exception = 0;
        this.failed = 0;
        this.success = 0;
        this.instantiateError = "";
        try {
            this.cl = Class.forName(className);
            this.m = cl.getMethods();
            this.o = cl.getConstructor().newInstance();
        } catch (InvocationTargetException e) {
            instantiateError = ("ERROR: An method failed in " + className + "!");
        } catch (ClassNotFoundException e) {
            instantiateError = ("ERROR: Class " + className + " does not exist!");
        } catch (InstantiationException e) {
            instantiateError = ("ERROR: " + className + " could not be instantiated!");
        } catch (IllegalAccessException e) {
            instantiateError = ("ERROR: " + className + " is missing access to one or more methods!");
        } catch (NoSuchMethodException e) {
            instantiateError = ("ERROR: Some methods in " + className + " could not be found!");
        }
    }

    /**
     * Execute a test from testMethods
     * @param i which test to be invoked.
     * @return The result from the test
     */
    public String ExecuteTest (int i) {
        String result = "";
        try {
            if (setUp != -1) m[setUp].invoke(o);
            try {
                if ((boolean) cl.getMethod(testMethods.get(i)).invoke(o)) {
                    success++;
                    if (setUp != -1 && tearDown != -1) m[tearDown].invoke(o);
                    result = (testMethods.get(i) + ": SUCCESS");
                } else {
                    failed++;
                    if (setUp != -1 && tearDown != -1) m[tearDown].invoke(o);
                    result = (testMethods.get(i) + ": FAIL");
                }
            } catch (InvocationTargetException e) {
                exception++;
                result = (testMethods.get(i) + ": FAIL Generated a " + e.getCause().toString().split(": ")[0]);
            }
            if (tearDown != -1) m[tearDown].invoke(o);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignore) {

        }

        return result;
    }

    /**
     * Checks if the correct interface is implemented in the class.
     * @return true or false
     */
    public boolean checkInterface () {
        for (int i = 0; i < cl.getInterfaces().length; i++) {
            if (interfaceName.equals(cl.getInterfaces()[i].getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Find all methods beginning with test within the imported class
     */
    public void checkMethods () {
        for (int i = 0; i < m.length; i++) {
            if (m[i].getName().equals("setUp")) {
                setUp = i;
            } else if (m[i].getName().equals("tearDown")) {
                tearDown = i;
            } else if (m[i].getName().contains("test")) {
                testMethods.add(m[i].getName());
            }
        }
        if (testMethods.size() == 0) {
            instantiateError = "Warning: No test methods found in " + className +"!";
        }
    }

    public String getInstantiateError() {
        return instantiateError;
    }

    public ArrayList<String> getTestMethods() {
        return testMethods;
    }

    public String getClassName() {
        return className;
    }

    public int getSuccess() {
        return success;
    }

    public int getFailed() {
        return failed;
    }

    public int getException() {
        return exception;
    }

    public String getInterfaceName() {
        return interfaceName;
    }
}
