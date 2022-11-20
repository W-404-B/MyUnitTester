import javax.swing.*;
import java.awt.*;


public class TestPanel {

    private JFrame frame;

    private JButton testClass;

    private JButton clearButton;

    private JTextField textField;

    private JTextArea textArea;


    //Should only be called on EDT
    public TestPanel(String title) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Build panels
        JPanel middlePanel = buildMiddlePanel();
        JPanel upperPanel = buildUpperPanel(title);


        //Add panels to the frame
        frame.add(middlePanel, BorderLayout.CENTER);
        frame.add(upperPanel, BorderLayout.NORTH);

        frame.pack();

    }

    //Should only be called on EDT
    public void show() {
        frame.setVisible(true);
    }


    private JPanel buildMiddlePanel() {
        JPanel middlePanel = new JPanel();

        middlePanel.setBorder(BorderFactory.createTitledBorder("Tests Results:"));
        middlePanel.setLayout(new BorderLayout());
        textArea = new JTextArea();
        textArea.setHighlighter(null);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        middlePanel.add(scrollPane, BorderLayout.CENTER);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(new TestListener(textField, textArea));
        middlePanel.add(clearButton, BorderLayout.SOUTH);

        return middlePanel;
    }

    private JPanel buildUpperPanel(String title) {
        JPanel upperPanel = new JPanel();
        upperPanel.setBorder(BorderFactory.createTitledBorder(title));
        upperPanel.setLayout(new BorderLayout());

        textField = new JTextField("");
        upperPanel.add(textField, BorderLayout.CENTER);


        testClass = new JButton("Run Tests");
        testClass.addActionListener(new TestListener(textField, textArea));
        upperPanel.add(testClass, BorderLayout.EAST);
        return upperPanel;
    }
}
