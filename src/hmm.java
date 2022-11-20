import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class hmm {
    public static void main () throws Exception {
        // setup for reflect
        try {
            Class<?> cl = Class.forName("Test1");
            Method m = cl.getMethod("setUp");
            Constructor<?> cons = cl.getConstructor();
            Object o = cons.newInstance();
            // run the method defined
            m.invoke(o);
        } catch (ClassNotFoundException e) {
            System.err.println("wadup");
        } catch (InvocationTargetException e) {
            System.err.println("Exception in code we tried to run");
        }
    }
}
