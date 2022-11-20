import javax.swing.SwingUtilities;

public class TwoPanelsMain {
    public static void main( String[] args ) {
        
        //Undviker att anropa swing metoder från main-tråden
        SwingUtilities.invokeLater(() -> {
            TwoPanels gui=new TwoPanels( "Exempel" );
            gui.show();
        });
    }
}
