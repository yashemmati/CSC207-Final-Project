package GUI;

import CalendarClasses.Alert;
import CalendarClasses.Event;

import javax.swing.*;

/**
 * A Abstract GUI for editing objects in a calendar
 */
public abstract class EditGui implements Showable{
    protected DisplayEventsGui parent;
    protected JFrame frame;

    protected JPanel mainPanel;

    protected void showMessageBox(String msg, String title) {
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    protected void returnToParent() {
        parent.show();
        frame.dispose();
    }

    protected void returnToParent(Event e) {
        parent.show(e);
        frame.dispose();
    }

    protected void returnToParent(Alert a) {
        parent.show(a);
        frame.dispose();
    }

    /**
     * Makes this window visible
     */
    public void show() {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
