package GUI;

import CalendarClasses.Alert;
import CalendarClasses.Event;
import CalendarClasses.UserData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

/**
 * A GUI for editing an alert in a logged in users selected calendar
 */
public class EditAlertGui extends EditGui{
    private JPanel mainPanel;
    private JLabel lblAlertTime;
    private JLabel lblAlertForEvent;
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
    private JLabel lblEventName;
    private JButton btnSave;
    private JButton btnBack;
    private JButton btnDelete;

    private Alert toEdit;

    protected EditAlertGui(Alert toEdit, DisplayEventsGui parent) {
        this.toEdit = toEdit;
        this.parent = parent;
        super.mainPanel = mainPanel;
        frame = new JFrame("Editing Alert: " + toEdit.getNotification());

        DateTimeGuiComponents.formatDateTimeComponents(txtYear, cboxMonth, cboxDay, cboxHour, cboxMinute);
        DateTimeGuiComponents.fillDefaults(toEdit.getTime(), txtYear, cboxMonth, cboxDay, cboxHour, cboxMinute);
        lblAlertForEvent.setText(toEdit.getEventName());

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToParent();
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalendarGui.cal.removeAlert(toEdit);
                showMessageBox("Event: " + toEdit.getEventName() + " Successfully Deleted!", "Event Deleted");
                UserData.saveCalendar(CalendarGui.cal, CalendarGui.user);
                returnToParent(toEdit);
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
                    CalendarGui.cal.editAlert(toEdit, newTime);
                    showMessageBox("Changes saved to Event!", "Saved Changes");
                    UserData.saveCalendar(CalendarGui.cal, CalendarGui.user);
                    returnToParent();
                } catch (NumberFormatException nfe) {
                    showMessageBox("Please Enter a Valid Year!", "Error");
                }
            }
        });
    }

}
