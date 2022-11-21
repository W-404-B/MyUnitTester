import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

/**
 * Course: Applikationsutveckling (Java)
 * Author: William Berglund (id20wbd)
 *
 * This class listens for ActionEvents from the view.
 */
class TestListener implements ActionListener  {

    private final JTextField textField;
    private final JTextArea textArea;

    public TestListener(JTextField textField, JTextArea textArea) {
        this.textArea = textArea;
        this.textField = textField;
    }

    /**
     * Handles the two different actions from the view.
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        // Clear textArea
        if (e.getActionCommand().equals("Clear")) {
            textArea.selectAll();
            textArea.replaceSelection("");
            // Run Tests based on the text from the textField
        } else if (e.getActionCommand().equals("Run Tests")){
            SwingTestWorker stw = new SwingTestWorker();
            stw.doInBackground();
        }
    }

    /**
     * SwingTestWorker calls on methods from the model ExecuteTests based on the text from the textField
     */
    private class SwingTestWorker extends SwingWorker<String, String> {

        @Override
        protected String doInBackground() {
            ExecuteTests et = new ExecuteTests(textField.getText());
            // Pre test text.
            publish("----- Running tests for: " + et.getClassName() + " -----");

            // Check if there was any exception thrown during the construction of the class
            if (!et.getInstantiateError().equals("")) {
                publish(et.getInstantiateError(), "", "");
                return "";
            }

            // Check that correct interface is implemented.
            if (!et.checkInterface()){
                publish("ERROR: " + et.getClassName() + " does not implement " + et.getInterfaceName());
                return "";
            }

            et.checkMethods();

            // Handle if there is no test methods in the class
            if (et.getTestMethods().size() == 0) {
                publish("ERROR: No test methods found in " + et.getClassName(), "");
                return "";
            }

            // Run all test methods
            for (int i = 0; i < et.getTestMethods().size(); i++) {
                publish(et.ExecuteTest(i));
            }

            // Publish summary of the tests
            publish("", "----- Test Summary -----");
            if (et.getSuccess() > 0) publish(et.getSuccess() + " tests succeeded!");
            if (et.getFailed() > 0) publish(et.getFailed() + " tests failed!");
            if (et.getException() > 0) publish(et.getException() + " tests failed because of an exception!", "","");

            return "";
        }

        @Override
        protected void process (List<String> chunks) {
            for(String line : chunks) {
                textArea.append(line);
                textArea.append("\n");
            }
        }
    }
}