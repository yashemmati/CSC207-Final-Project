package GUI;

import CalendarClasses.Event;
import CalendarClasses.UserData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;
import java.time.LocalDateTime;

/**
 * A Graphical User Interface for adding alerts to an event in a calendar
 */
public class AddAlertGui {
    private JPanel mainPanel;
    private JLabel lblTime;
    private JLabel lblYear;
    private JTextField txtYear;
    private JLabel lblMonth;
    private JComboBox cboxMonth;
    private JLabel lblDay;
    private JComboBox cboxDay;
    private JLabel lblHour;
    private JComboBox cboxHour;
    private JLabel lblMinute;
    private JComboBox cboxMinute;
    private JButton btnSave;
    private JButton btnBack;

    private EditEventGui parent;
    private Event toAddAlert;
    private JFrame frame = new JFrame("Add Alert");

    protected AddAlertGui(Event e, EditEventGui parent) {
        this.toAddAlert = e;
        this.parent = parent;

        DateTimeGuiComponents.formatDateTimeComponents(txtYear, cboxMonth, cboxDay, cboxHour, cboxMinute);
        DateTimeGuiComponents.fillDefaults(LocalDateTime.now(), txtYear, cboxMonth, cboxDay, cboxHour, cboxMinute);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.show();
                frame.dispose();
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LocalDateTime newTime = LocalDateTime.of(
                            Integer.valueOf(txtYear.getText()),
                            (int) cboxMonth.getSelectedItem(),
                            (int) cboxDay.getSelectedItem(),
                            (int) cboxHour.getSelectedItem(),
                            (int) cboxMinute.getSelectedItem()
                    );
                    CalendarGui.cal.createAlert(toAddAlert, newTime);
                    showMessageBox("Changes saved to Event!", "Saved Changes");
                    UserData.saveCalendar(CalendarGui.cal, CalendarGui.user);
                    parent.show();
                    frame.dispose();
                } catch (NumberFormatException nfe) {
                    showMessageBox("Please Enter a Valid Year!", "Error");
                }
            }
        });
    }

    private void showMessageBox(String msg, String title) {
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Makes this window visible.
     */
    public void show() {
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
