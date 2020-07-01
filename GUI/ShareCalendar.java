package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import CalendarClasses.Calendar;
import CalendarClasses.CalendarManager;
import CalendarClasses.User;
import CalendarClasses.UserData;

/***
 * This class creates a GUI that allows the user to share one of their calendars
 */
public class ShareCalendar {
    private JPanel mainPanel;
    private JButton shareButton;
    private JButton backButton;
    private JLabel labelName;
    private JLabel labelUser;
    private JTextField textName;
    private JTextField textUser;
    private JFrame frame = new JFrame("Share Calendar");

    /***
     * Constructs a ShareCalendar object
     * @param myCal current user's calendar manager
     * @param user current user
     */
    public ShareCalendar(CalendarManager myCal, User user) {
        shareButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CalendarManager theirCal = UserData.readUserData(textUser.getText());
                    Calendar toShare = myCal.getCalendar(textName.getText());
                    theirCal.addCalendar(toShare);
                    UserData.saveUserData(theirCal, textUser.getText());
                } catch (NullPointerException n) {
                    JOptionPane.showMessageDialog(null, "This Calendar/User does not exist!",
                            "Sharing Error", JOptionPane.INFORMATION_MESSAGE);
                    textName.setText("");
                    textUser.setText("");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalendarMenu l = new CalendarMenu(myCal, user);
                l.show();
                frame.dispose();
            }
        });
    }

    /***
     * Shows GUI and its components
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



