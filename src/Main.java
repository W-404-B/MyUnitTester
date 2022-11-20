import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws Exception{
        try {
            Class<?> cl = Class.forName("Test1");
            Method[] m = cl.getMethods();
            Constructor<?> cons = cl.getConstructor();
            Object o = cons.newInstance();
            // run the method defined
            for (int i = 0; i < m.length; i++) {
                System.out.println(m[i].getName());
                m[i].invoke(o);
            }

        } catch (ClassNotFoundException e) {
            System.err.println("wadup");
        } catch (InvocationTargetException e) {
            System.err.println("Exception in code we tried to run");
        }
    }
}