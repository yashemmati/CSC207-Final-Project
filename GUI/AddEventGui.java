package GUI;

import CalendarClasses.*;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A GUI for adding events to a calendar.
 */
public class AddEventGui {

    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton back;
    private JTextArea textArea1;
    private JButton createNewEventButton;


    private JFrame frame = new JFrame("Create Event");

    protected AddEventGui(Calendar calendar) {
        createNewEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = textField1.getText().trim();
                String eventMemo = textArea1.getText();
                String eventStartTime = textField2.getText().trim();
                String eventEndTime = textField3.getText().trim();
                String eventTags = textField4.getText();
                String[] list = eventTags.split(", ");
                ArrayList<String> listOfTags = new ArrayList<>();
                for (String s: list){
                    listOfTags.add(s);
                }



                if (TimeStringConverters.isValid(eventStartTime) != Boolean.TRUE) {
                    showMsgBox("Please add valid start time of the form YYYY-MM-DD HH:MM", "Error!");
                }if (TimeStringConverters.isValid(eventEndTime)!= Boolean.TRUE){
                    showMsgBox("Please add valid end time of the form YYYY-MM-DD HH:MM", "Error!");
                }if(eventName.equals(new String(""))){
                    showMsgBox("Please add an event Name", "Error!");
                }
                else if (TimeStringConverters.isValid(eventStartTime)&&TimeStringConverters.isValid(eventEndTime)){
                    LocalDateTime end = TimeStringConverters.stringToDateTime(eventEndTime);
                    LocalDateTime start = TimeStringConverters.stringToDateTime(eventStartTime);
                    calendar.createEvent(eventName, start, end, eventMemo, listOfTags);
                    showMsgBox("New event created", "Success!");}
                    UserData.saveCalendar(CalendarGui.cal, CalendarGui.user);
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

    }

    /**
     * Makes this window visible.
     */
    public void show() {
        frame.setContentPane(panel1);
        frame.setSize(620, 310);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true); //displays the window
    }

    private void showMsgBox(String msg , String title) {
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

}
