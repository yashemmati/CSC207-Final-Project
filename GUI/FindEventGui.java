package GUI;

import CalendarClasses.Alert;
import CalendarClasses.Calendar;
import CalendarClasses.Event;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A GUI for finding an event in a logged in users selected calendar.
 */
public class FindEventGui implements Showable{
    private Calendar toSearch;
    private Showable parent;

    private JPanel mainPanel;
    private JLabel lblFindDate;
    private JLabel lblYear;
    private JTextField txtYear;
    private JLabel lblMonth;
    private JComboBox cboxMonth;
    private JLabel lblDay;
    private JComboBox cboxDay;
    private JLabel lblFindMemo;
    private JButton btnDateSearch;
    private JComboBox cboxMemos;
    private JButton btnSearchMemo;
    private JLabel lblFindTag;
    private JComboBox cboxTags;
    private JButton btnSearchTag;
    private JButton btnBack;

    private JFrame frame = new JFrame("Search for Events");

    protected FindEventGui(Calendar cal, Showable parent) {
        toSearch = cal;
        DateTimeGuiComponents.formatDateComponents(txtYear, cboxMonth, cboxDay);

        cboxMemos.addItem("");
        cboxMemos.setSelectedItem("");
        for (String s : cal.allMemos()) {
            cboxMemos.addItem(s);
        }
        cboxTags.addItem("");
        cboxTags.setSelectedItem("");
        for (String s : cal.allTags()) {
            cboxTags.addItem(s);
        }

        btnDateSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime date = LocalDateTime.of(
                        Integer.parseInt(txtYear.getText()),
                        (int)cboxMonth.getSelectedItem(),
                        (int)cboxDay.getSelectedItem(), 0, 0);
                ArrayList[] matching = toSearch.findByDate(date);
                displayFoundEvents((ArrayList<Event>) matching[0], (ArrayList<Alert>) matching[1]);
            }
        });
        btnSearchMemo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selection = (String)cboxMemos.getSelectedItem();
                if (selection.equals("")) {
                    showMessageBox("Please make a selection first!", "No Selection");
                } else {
                    ArrayList<Event> matching = cal.findByMemo(selection);
                    displayFoundEvents(matching, new ArrayList<>());
                }
            }
        });
        btnSearchTag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selection = (String)cboxTags.getSelectedItem();
                if (selection.equals("")) {
                    showMessageBox("Please make a selection first!", "No Selection");
                } else {
                    ArrayList<Event> matching = cal.findByTag(selection);
                    displayFoundEvents(matching, new ArrayList<>());
                }
            }
        });
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.show();
                frame.dispose();
            }
        });
    }

    private void displayFoundEvents(ArrayList<Event> events, ArrayList<Alert> alerts) {
        DisplayEventsGui d = new DisplayEventsGui(events, alerts, this, "Search by date results");
        d.show();
        frame.setVisible(false);
    }

    private void showMessageBox(String msg, String title) {
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
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
