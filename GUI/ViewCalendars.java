package GUI;

import CalendarClasses.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/***
 * This class creates a GUI that shows the calendars that this user has created
 */
public class ViewCalendars {
    private JPanel mainPanel;
    private JLabel lblInfo;
    private JList lstCalendars;
    private JButton btnOpenCal;
    private JButton btnBack;
    private JButton btnDeleteCalendar;
    private CalendarManager usersCals;
    private Calendar[] calList;
    private String[] names;
    private User u;
    private JFrame frame = new JFrame("Select Calendar"); //this variable is our window

    /***
     * Constructs a ViewCalendars object
     * @param u the user whose calendars are being viewed
     */
    public ViewCalendars(User u) {
        this.u = u;
        updateList();
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalendarMenu cm = new CalendarMenu(UserData.readUserData(u.getUserName()), u);
                cm.show();
                frame.dispose();
            }
        });

        btnOpenCal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) lstCalendars.getSelectedValue();
                Calendar cal = null;
                for (Calendar c : calList) {
                    if (c.name.equals(s)) {
                        cal = c;
                    }
                }
                if (cal == null) {
                    JOptionPane.showMessageDialog(null, "Please Select a Calendar First!",
                            "No Calendar Selected", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    CalendarGui cg = new CalendarGui(u, cal.name, cal);
                    cg.show();
                    frame.dispose();
                }
            }
        });

        btnDeleteCalendar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) lstCalendars.getSelectedValue();
                Calendar cal = null;
                for (Calendar c : calList) {
                    if (c.name.equals(s)) {
                        cal = c;
                    }
                }
                if (cal == null) {
                    JOptionPane.showMessageDialog(null, "Please Select a Calendar First!",
                            "No Calendar Selected", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    usersCals.removeCalendar(cal);
                    UserData.removeCalendar(cal, u);
                    updateList();
                    JOptionPane.showMessageDialog(null, "Calendar Has Been Deleted!",
                            "Calendar Deleted", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    /***
     * Updates JList
     */
    public void updateList(){
        usersCals = UserData.readUserData(u.getUserName());
        calList = usersCals.getListOfCalendars().toArray(new Calendar[0]);
        names = usersCals.getCalendarNames().toArray(new String[0]);
        lstCalendars.setListData(names);
    }

    /***
     * Shows the GUI and its components
     */
    public void show() {
        frame.setContentPane(mainPanel); //puts all our buttons and other objects on the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //what we want to do when the user closes the window
        frame.setSize(400, 320);
        frame.setLocationRelativeTo(null); //opens our window in the center of the screen
        frame.setResizable(false);
        frame.setVisible(true); //displays the window
    }
}
