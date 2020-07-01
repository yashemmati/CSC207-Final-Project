package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

/**
 * A class used to format GUI components to accept and display proper datetime components
 */
public class DateTimeGuiComponents {

    private static Pattern yearValidation = Pattern.compile("[\\d]{1,4}");

    protected static void formatDateTimeComponents(JTextField txtYear, JComboBox cboxMonth, JComboBox cboxDay, JComboBox cboxHour, JComboBox cboxMin) {
        for (int i = 1; i < 13; i++) {
            cboxMonth.addItem(i);
        }
        for (int i = 0; i < 24; i++) {
            cboxHour.addItem(i);
        }
        for (int i = 0; i < 60; i++) {
            cboxMin.addItem(i);
        }

        txtYear.setText("2020");
        cboxMonth.setSelectedItem(1);
        fillDayCBox(202, 1, cboxDay);

        cboxMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int year = Integer.valueOf(txtYear.getText());
                    fillDayCBox(year, (int)cboxMonth.getSelectedItem(), cboxDay);
                } catch (NumberFormatException nfe) {
                    System.out.println(nfe.getStackTrace());
                    JOptionPane.showMessageDialog(null, "Please Specify a Start Year!",
                            "Invalid Year", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        txtYear.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (!yearValidation.matcher(txtYear.getText()).matches()) {
                    JOptionPane.showMessageDialog(null, "Please Enter a Valid Year!",
                            "Invalid Year", JOptionPane.INFORMATION_MESSAGE);
                    txtYear.setText("");
                } else {
                    int year = Integer.parseInt(txtYear.getText());
                    fillDayCBox(year, (int)cboxMonth.getSelectedItem(), cboxDay);
                }
            }
        });
    }

    protected static void fillDayCBox(int year, int month, JComboBox cboxDay) {
        LocalDate yearMonth = LocalDate.of(year, month, 1);
        cboxDay.removeAllItems();
        for (int i = 1; i <= yearMonth.lengthOfMonth(); i++) {
            cboxDay.addItem(i);
        }
    }

    protected static void formatDateComponents(JTextField txtYear, JComboBox cboxMonth, JComboBox cboxDay) {
        for (int i = 1; i < 13; i++) {
            cboxMonth.addItem(i);
        }

        txtYear.setText("2020");
        cboxMonth.setSelectedItem(1);
        fillDayCBox(202, 1, cboxDay);

        cboxMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int year = Integer.valueOf(txtYear.getText());
                    fillDayCBox(year, (int)cboxMonth.getSelectedItem(), cboxDay);
                } catch (NumberFormatException nfe) {
                    System.out.println(nfe.getStackTrace());
                    JOptionPane.showMessageDialog(null, "Please Specify a Start Year!",
                            "Invalid Year", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        txtYear.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (!yearValidation.matcher(txtYear.getText()).matches()) {
                    JOptionPane.showMessageDialog(null, "Please Enter a Valid Year!",
                            "Invalid Year", JOptionPane.INFORMATION_MESSAGE);
                    txtYear.setText("");
                } else {
                    int year = Integer.parseInt(txtYear.getText());
                    fillDayCBox(year, (int)cboxMonth.getSelectedItem(), cboxDay);
                }
            }
        });
    }

    protected static void fillDefaults(LocalDateTime toFill, JTextField txtYear, JComboBox cboxMonth, JComboBox cboxDay, JComboBox cboxHour, JComboBox cboxMin) {
        String year = String.valueOf(toFill.getYear());
        int month = toFill.getMonth().getValue();
        int day = toFill.getDayOfMonth();
        int hour = toFill.getHour();
        int minute = toFill.getMinute();

        txtYear.setText(year);
        cboxMonth.setSelectedItem(month);
        DateTimeGuiComponents.fillDayCBox(Integer.valueOf(year), month, cboxDay);
        cboxDay.setSelectedItem(day);
        cboxHour.setSelectedItem(hour);
        cboxMin.setSelectedItem(minute);
    }
}
