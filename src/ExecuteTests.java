import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class ExecuteTests {

    String className;
    int start;
    int after;
    int success;
    int failed;
    int exception;
    ArrayList<String> testMethods;
    String interfaceName = "se.umu.cs.unittest.TestClass";
    Class<?> cl;
    Method[] m;
    Object o;
    String instantiateError;

    public ExecuteTests (String className) {
        this.className = className;
        this.start = -1;
        this.after = -1;
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

    public String getInstantiateError() {
        return instantiateError;
    }

    public String ExecuteTest (int i) {
        String result = "";
        try {
            if (start != -1 && after != -1) m[start].invoke(o);
            try {
                if ((boolean) cl.getMethod(testMethods.get(i)).invoke(o)) {
                    success++;
                    if (start != -1 && after != -1) m[after].invoke(o);
                    result = (testMethods.get(i) + ": SUCCESS");
                } else {
                    failed++;
                    if (start != -1 && after != -1) m[after].invoke(o);
                    result = (testMethods.get(i) + ": FAIL");
                }
            } catch (InvocationTargetException e) {
                exception++;
                result = (testMethods.get(i) + ": FAIL Generated a " + e.getCause().toString().split(": ")[0]);
            }
            if (start != -1 && after != -1) m[after].invoke(o);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignore) {

        }

        return result;
    }

    public ArrayList<String> getTestMethods() {
        return testMethods;
    }

    public String getClassName() {
        return className;
    }

    public Class<?> getCl() {
        return cl;
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

    public boolean checkInterface (Class<?>[] interfaces) {
        for (int i = 0; i < interfaces.length; i++) {
            if (interfaceName.equals(interfaces[i].getName())) {
                return true;
            }
        }
        return false;
    }

    // Find all methods beginning with test within the imported class
    public void checkMethods () {
        for (int i = 0; i < m.length; i++) {
            if (m[i].getName().equals("setUp")) {
                start = i;
            } else if (m[i].getName().equals("tearDown")) {
                after = i;
            } else if (m[i].getName().contains("test")) {
                testMethods.add(m[i].getName());
            }
        }
    }
}
