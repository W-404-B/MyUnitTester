import javax.swing.*;
import java.awt.*;

/**
 * Course: Applikationsutveckling (Java)
 * Author: William Berglund (id20wbd)
 *
 * This class builds up the view and implements the actions from the controller.
 */
public class TestPanel {

    private JFrame frame;

    private JButton runTests;

    private JButton clearButton;

    private JTextField textField;

    private JTextArea textArea;

    public TestPanel(String title) {
        // Create new frame
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 600));

        // Build the panels
        JPanel middlePanel = buildTextAreaPanel();
        JPanel upperPanel = buildTextFieldPanel(title);

        // Add panels to the frame
        frame.add(middlePanel, BorderLayout.CENTER);
        frame.add(upperPanel, BorderLayout.NORTH);

        frame.pack();
    }

    /**
     * Builds the text area panel
     * @return The panel
     */
    private JPanel buildTextAreaPanel() {
        // create new panel
        JPanel textAreaPanel = new JPanel();
        textAreaPanel.setBorder(BorderFactory.createTitledBorder("Tests Results:"));
        textAreaPanel.setLayout(new BorderLayout());

        // add text area to frame
        textArea = new JTextArea();
        textArea.setHighlighter(null);
        textArea.setEditable(false);

        // add scrollable function to textarea
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        textAreaPanel.add(scrollPane, BorderLayout.CENTER);

        // add button to clear the text area
        clearButton = new JButton("Clear");
        clearButton.addActionListener(new TestListener(textField, textArea));
        textAreaPanel.add(clearButton, BorderLayout.SOUTH);

        return textAreaPanel;
    }

    /**
     * Builds the text field panel
     * @param title title of the panel
     * @return the panel
     */
    private JPanel buildTextFieldPanel(String title) {
        // create new panel
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setBorder(BorderFactory.createTitledBorder(title));
        textFieldPanel.setLayout(new BorderLayout());

        // add text field to frame
        textField = new JTextField("");
        textFieldPanel.add(textField, BorderLayout.CENTER);

        // add button to run tests to
        runTests = new JButton("Run Tests");
        runTests.addActionListener(new TestListener(textField, textArea));
        textFieldPanel.add(runTests, BorderLayout.EAST);

        return textFieldPanel;
    }

    public void show() {
        frame.setVisible(true);
    }
}
