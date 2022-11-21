import se.umu.cs.unittest.TestClass;

import java.awt.*;

public class ColorTester implements TestClass {

    private MyColorChanger mcc;

    public ColorTester() { }

    public void setUp() { mcc = new MyColorChanger(); }

    public void tearDown() {
        mcc = null;
    }

    //Test that should succeed
    public boolean testInitialisation() {
        return mcc.getColor() == Color.BLACK;
    }

    //Test that should succeed
    private boolean testColorChange() {
        Color blue = Color.BLUE;
        mcc.setColor(blue);
        return mcc.getColor() == blue;
    }

    //Test that should succeed
    private boolean testMultipleColorChange() throws Exception{
        Color blue = Color.BLUE;
        Color green = Color.GREEN;

        mcc.setColor(blue);

        mcc.setColor(green);

        return mcc.getColor() == green;
    }

    //Test that should fail
    public boolean testWrongColorChange() {
        Color blue = Color.BLUE;
        Color green = Color.GREEN;

        mcc.setColor(blue);

        return mcc.getColor() == green;
    }

    //Test that should fail
    public boolean testColorFailByException() {
       mcc = null;
       mcc.setColor(Color.blue);

       return mcc.getColor() == Color.blue;
    }

}
