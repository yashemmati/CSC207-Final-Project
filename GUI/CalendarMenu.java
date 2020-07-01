package GUI;

import CalendarClasses.CalendarManager;
import CalendarClasses.User;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/***
 * This class creates the main calendar menu where the user can create, share, or delete a calendar
 */
public class CalendarMenu {
    private JButton viewCalendarsButton;
    private JPanel panel1;
    private JButton newCalendarButton;
    private JButton shareCalendarButton;
    private JButton signOutButton;
    private JFrame frame = new JFrame("Menu");

    /***
     * Constructs a CalendarMenu object
     * @param myCal current user's calendar manager
     * @param user current user
     */
    public CalendarMenu(CalendarManager myCal, User user) {
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setIsLogged(false);
                LoginGui log = new LoginGui();
                log.show();
                frame.dispose();
            }
        });
        shareCalendarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShareCalendar l = new ShareCalendar(myCal, user);
                l.show();
                frame.dispose();

            }
        });
        newCalendarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateCalendar l = new CreateCalendar(user);
                l.show();
                frame.dispose();
            }
        });
        viewCalendarsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewCalendars view = new ViewCalendars(user);
                view.show();
                frame.dispose();
            }
        });
    }

    /**
     * Makes this window visible.
     */
    public void show() {
        frame.setContentPane(panel1); //puts all our buttons and other objects on the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //what we want to do when the user closes the window
        frame.setSize(400, 165);
        frame.setLocationRelativeTo(null); //opens our window in the center of the screen
        frame.setResizable(false);
        frame.setVisible(true); //displays the window
    }
}
