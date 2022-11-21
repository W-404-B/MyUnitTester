import javax.swing.*;

/**
 * Course: Applikationsutveckling (Java)
 * Author: William Berglund (id20wbd)
 *
 * Main class, starts up the view.
 */
public class MyUnitTester {
    public static void main( String[] args ) {

        SwingUtilities.invokeLater(() -> {
            TestPanel gui=new TestPanel( "My Unit Tester" );
            gui.show();
        });
    }
}
