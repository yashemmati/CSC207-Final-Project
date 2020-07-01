package GUI;

import CalendarClasses.Event;
import CalendarClasses.UserData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * A GUI for creating a series in a logged in users selected calendar, by frequency
 */
public class CreateSeriesFromFrequencyGUI {
    private JPanel mainPanel;
    private JLabel lblEventName;
    private JTextField txtEventName;
    private JLabel lblStartDate;
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
    private JLabel lblEndTime;
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
    private JTextArea taMemo;
    private JTextField txtSeriesName;
    private JLabel lblSeriesName;
    private JLabel lblInstructions;
    private JLabel lblFrequency;
    private JTextField txtFrequency;
    private JButton btnCancel;
    private JButton btnDaily;
    private JButton btnWeekly;
    private JButton btnMonthly;

    private CalendarGui parent;

    private JFrame frame = new JFrame("Frequency Series");
    private Pattern frequencyValidation = Pattern.compile("[\\d]*");

    protected CreateSeriesFromFrequencyGUI(CalendarGui parent) {
        this.parent = parent;
        DateTimeGuiComponents.formatDateTimeComponents(txtStartYear, cboxStartMonth, cboxStartDay, cboxStartHour, cboxStartMinute);
        DateTimeGuiComponents.formatDateTimeComponents(txtEndYear, cboxEndMonth, cboxEndDay, cboxEndHour, cboxEndMinute);
        DateTimeGuiComponents.fillDefaults(LocalDateTime.now(), txtStartYear, cboxStartMonth, cboxStartDay, cboxStartHour, cboxStartMinute);
        DateTimeGuiComponents.fillDefaults(LocalDateTime.now(), txtEndYear, cboxEndMonth, cboxEndDay, cboxEndHour, cboxEndMinute);
        txtFrequency.setText("1");

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToParent();
            }
        });
        btnDaily.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtSeriesName == null){
                    showMessageBox("Please Enter a Name", "Blank Name Field");
                }
                if (validateFrequency()) {
                    String name = txtSeriesName.getText();
                    Event first = createFirstEvent();
                    CalendarGui.cal.createFrequencySeries(name, first, Integer.parseInt(txtFrequency.getText()), 1);
                    showMessageBox("Created Series: " + name, "Success");
                    UserData.saveCalendar(CalendarGui.cal, CalendarGui.user);
                    returnToParent();
                } else {
                    showMessageBox("Enter a valid Frequency", "Invalid Input");
                }
            }
        });
        btnWeekly.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateFrequency()) {
                    String name = txtSeriesName.getText();
                    Event first = createFirstEvent();
                    CalendarGui.cal.createFrequencySeries(name, first, Integer.parseInt(txtFrequency.getText()), 7);
                    showMessageBox("Created Series: " + name, "Success");
                    UserData.saveCalendar(CalendarGui.cal, CalendarGui.user);
                    returnToParent();
                } else {
                    showMessageBox("Enter a valid Frequency", "Invalid Input");
                }
            }
        });
        btnMonthly.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateFrequency()) {
                    String name = txtSeriesName.getText();
                    Event first = createFirstEvent();
                    CalendarGui.cal.createMonthlyFrequencySeries(name, first, Integer.parseInt(txtFrequency.getText()));
                    showMessageBox("Created Series: " + name, "Success");
                    UserData.saveCalendar(CalendarGui.cal, CalendarGui.user);
                    returnToParent();
                } else {
                    showMessageBox("Enter a valid Frequency", "Invalid Input");
                }
            }
        });
        txtFrequency.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (!frequencyValidation.matcher(txtFrequency.getText()).matches()) {
                    showMessageBox("Please Enter a Number!", "Invalid Input");
                    txtFrequency.setText("1");
                }
            }
        });
    }

    private boolean validateFrequency() {
        Pattern isNumeric = Pattern.compile("[\\d]+");
        return isNumeric.matcher(txtFrequency.getText()).matches();
    }

    private void showMessageBox(String msg, String title) {
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private void returnToParent() {
        parent.show();
        frame.dispose();
    }

    private Event createFirstEvent() {
        ArrayList<String> tags = new ArrayList<>();
        LocalDateTime start = LocalDateTime.of(Integer.parseInt(txtStartYear.getText()), (int)cboxStartMonth.getSelectedItem(),
                (int)cboxStartDay.getSelectedItem(), (int)cboxStartHour.getSelectedItem(), (int)cboxStartMinute.getSelectedItem());
        LocalDateTime end = LocalDateTime.of(Integer.parseInt(txtEndYear.getText()), (int)cboxEndMonth.getSelectedItem(),
                (int)cboxEndDay.getSelectedItem(), (int)cboxEndHour.getSelectedItem(), (int)cboxEndMinute.getSelectedItem());

        for (String s: txtTags.getText().split(",")) {
            tags.add(s);
        }
        return CalendarGui.cal.createEvent(txtEventName.getText(), start, end, taMemo.getText(), tags);
    }

    /**
     * Makes this window visible.
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
