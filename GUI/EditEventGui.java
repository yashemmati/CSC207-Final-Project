package GUI;


import CalendarClasses.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A GUI for editing an Event in a logged in users selected calendar
 */
public class EditEventGui extends EditGui {
    private Event toEdit;

    private JPanel mainPanel;
    private JTextField txtEventName;
    private JLabel lblEventName;
    private JLabel lblStartDate;
    private JLabel lblEndTime;
    private JLabel lblStartYear;
    private JTextField txtStartYear;
    private JLabel lblStartMonth;
    private JComboBox cboxStartMonth;
    private JLabel lblStartDay;
    private JComboBox cboxStartDay;
    private JLabel lblStartHour;
    private JComboBox cboxStartHour;
    private JLabel lblStartMinute;
    private JComboBox cboxStartMinute;
    private JLabel lblEndYear;
    private JTextField txtEndYear;
    private JLabel lblEndMonth;
    private JComboBox cboxEndMonth;
    private JLabel lblEndDay;
    private JComboBox cboxEndDay;
    private JLabel lblEndHour;
    private JComboBox cboxEndHour;
    private JLabel lblEndMinute;
    private JComboBox cboxEndMinute;
    private JLabel lblMemo;
    private JTextField txtTags;
    private JButton btnBack;
    private JButton btnSave;
    private JTextArea taMemo;
    private JButton btnDeleteEvent;
    private JButton btnDeleteSeries;
    private JButton btnAddAlert;

    protected EditEventGui(Event toEdit, DisplayEventsGui parent) {
        this.toEdit = toEdit;
        this.parent = parent;
        super.mainPanel = mainPanel;
        frame = new JFrame("Editing Event: " + this.toEdit.getEventName());
        taMemo.setLineWrap(true);
        taMemo.setWrapStyleWord(true);
        taMemo.setRows(3);
        Series inSeries = CalendarGui.cal.getSeries(toEdit);

        DateTimeGuiComponents.formatDateTimeComponents(txtStartYear, cboxStartMonth, cboxStartDay, cboxStartHour, cboxStartMinute);
        DateTimeGuiComponents.formatDateTimeComponents(txtEndYear, cboxEndMonth, cboxEndDay, cboxEndHour, cboxEndMinute);
        DateTimeGuiComponents.fillDefaults(toEdit.getStartTime(), txtStartYear, cboxStartMonth, cboxStartDay, cboxStartHour, cboxStartMinute);
        DateTimeGuiComponents.fillDefaults(toEdit.getEndTime(), txtEndYear, cboxEndMonth, cboxEndDay, cboxEndHour, cboxEndMinute);
        fillDefaults();

        if (inSeries != null) {
            btnDeleteSeries.setVisible(true);
        } else {
            btnDeleteSeries.setVisible(false);
        }

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToParent();
            }
        });
        btnDeleteSeries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalendarGui.cal.removeSeries(inSeries);
                showMessageBox("Series: " + inSeries.getSeriesName() + " Successfully Deleted!", "Series Deleted");
                returnToParent(toEdit);
            }
        });
        btnDeleteEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalendarGui.cal.removeEvent(toEdit);
                showMessageBox("Event: " + toEdit.getEventName() + " Successfully Deleted!", "Event Deleted");
                UserData.saveCalendar(CalendarGui.cal, CalendarGui.user);
                returnToParent(toEdit);
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LocalDateTime start = LocalDateTime.of(
                            Integer.valueOf(txtStartYear.getText()),
                            (int) cboxStartMonth.getSelectedItem(),
                            (int) cboxStartDay.getSelectedItem(),
                            (int) cboxStartHour.getSelectedItem(),
                            (int) cboxStartMinute.getSelectedItem()
                    );
                    LocalDateTime end = LocalDateTime.of(
                            Integer.valueOf(txtEndYear.getText()),
                            (int) cboxEndMonth.getSelectedItem(),
                            (int) cboxEndDay.getSelectedItem(),
                            (int) cboxEndHour.getSelectedItem(),
                            (int) cboxEndMinute.getSelectedItem()
                    );

                    if (start.isBefore(end)) {
                        ArrayList<String> tags = new ArrayList<>();
                        Collections.addAll(tags, txtTags.getText().split(","));
                        CalendarGui.cal.editEvent(toEdit, txtEventName.getText(), start, end, taMemo.getText(), tags);
                        showMessageBox("Changes saved to Event!", "Saved Changes");
                        UserData.saveCalendar(CalendarGui.cal, CalendarGui.user);
                        returnToParent();
                    } else {
                        showMessageBox("Start time must come before end time!", "Error");
                    }
                } catch (NumberFormatException nfe) {
                    showMessageBox("Please Enter a Valid Year!", "Error");
                }
            }
        });
        btnAddAlert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAlert();
            }
        });
    }

    private void addAlert() {
        AddAlertGui a = new AddAlertGui(this.toEdit, this);
        a.show();
        frame.setVisible(false);
    }

    private void fillDefaults() {
        String memo = toEdit.getMemo();
        String tags = toEdit.tagsToString();

        txtEventName.setText(toEdit.getEventName());
        taMemo.setText(memo);
        txtTags.setText(tags);
    }
}
