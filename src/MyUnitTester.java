import javax.swing.*;

public class MyUnitTester {
    public static void main( String[] args ) {

        SwingUtilities.invokeLater(() -> {
            TestPanel gui=new TestPanel( "Testing Stuff" );
            gui.show();
        });
    }
}
