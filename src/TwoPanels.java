import javax.swing.*;
import java.awt.*;

//Exempel på ett enkelt gränssnitt.



public class TwoPanels {

    private JFrame frame;
    
    private JButton blackButton;
    private JButton redButton;
    private JButton greenButton;
    
    private JCheckBox disableCheckBox;
    
    private JTextField textField;
    
    
    //Should only be called on EDT
    public TwoPanels(String title) {
        frame=new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Build panels
        JPanel upperPanel = buildUpperPanel(); 
        JPanel middlePanel = buildMiddlePanel();
        
        //Add panels to the frame
        frame.add(upperPanel, BorderLayout.NORTH);
        frame.add(middlePanel, BorderLayout.CENTER);
        
        frame.pack();
        
    }
    
   //Should only be called on EDT
    public void show() {
        frame.setVisible(true);
    }


    private JPanel buildMiddlePanel() {
        JPanel middlePanel = new JPanel();
        middlePanel.setBorder(
                BorderFactory.createTitledBorder("Textfärgskontroll"));
        middlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        blackButton = new JButton("Svart");
        blackButton.addActionListener(
                new ButtonListener(textField, Color.black));
        
        middlePanel.add(blackButton);
        
        redButton = new JButton("Rött");
        redButton.addActionListener(
                new ButtonListener(textField, Color.red));
        
        middlePanel.add(redButton);
        
        greenButton = new JButton("Grönt");
        greenButton.addActionListener(
                new ButtonListener(textField, Color.green));
        
        middlePanel.add(greenButton);
        return middlePanel;
    }

    private JPanel buildUpperPanel() {
        JPanel upperPanel = new JPanel();
        upperPanel.setBorder(BorderFactory.createTitledBorder("Exempel"));
        upperPanel.setLayout(new BorderLayout());
        
        textField = new JTextField("Byt färg på texten");
        upperPanel.add(textField, BorderLayout.CENTER);
        return upperPanel;
    }

}
