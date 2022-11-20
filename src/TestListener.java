import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

class TestListener implements ActionListener  {

    private final JTextField textField;
    private final JTextArea textArea;

    public TestListener(JTextField textField, JTextArea textArea) {
        this.textArea = textArea;
        this.textField = textField;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Clear")) {
            textArea.selectAll();
            textArea.replaceSelection("");
        } else if (e.getActionCommand().equals("Run Tests")){
            SwingTestWorker stw = new SwingTestWorker();
            stw.doInBackground();
        }
    }

    private class SwingTestWorker extends SwingWorker<String, String> {
        @Override
        protected String doInBackground() {
            ExecuteTests et = new ExecuteTests(textField.getText());
            // Pre test text.
            publish("----- Running tests for: " + et.getClassName() + " -----");

            if (!et.getInstantiateError().equals("")) {
                publish(et.getInstantiateError(), "", "");
                return "";
            }

            // get class by className and check that correct interface is implemented.

            if (!et.checkInterface(et.getCl().getInterfaces())){
                publish("ERROR: " + et.getClassName() + " does not implement " + et.getInterfaceName());
                return "";
            }

            et.checkMethods();

            if (et.getTestMethods().size() == 0) {
                publish("ERROR: No test methods found in " + et.getClassName(), "");
                return "";
            }

            for (int i = 0; i < et.getTestMethods().size(); i++) {
                publish(et.ExecuteTest(i));
            }

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