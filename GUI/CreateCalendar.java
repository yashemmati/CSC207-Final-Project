package GUI;

import CalendarClasses.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A GUI for creating a new calendar for a logged in user
 */
public class CreateCalendar {
    private JPanel panel1;
    private JTextField textName;
    private JRadioButton noRadioButton;
    private JRadioButton yesRadioButton;
    private JButton backButton;
    private JButton createButton;
    private JList list1;
    private JScrollPane scrollPane;
    private JLabel yearLabel;
    private JFrame frame = new JFrame("Create New Calendar");


    protected CreateCalendar(User user) {
        ButtonGroup bg = new ButtonGroup();
        bg.add(noRadioButton);
        bg.add(yesRadioButton);
        scrollPane.setVisible(false);
        yearLabel.setVisible(false);
        CalendarManager myCal = UserData.readUserData(user.getUserName());
        list1.setListData(yearsList().toArray());
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textName.getText();
                Calendar calendar = new Calendar(name);
                if (yesRadioButton.isSelected()) {
                    DefaultCalendar dc = new DefaultCalendar(new ArrayList<Integer>(list1.getSelectedValuesList()));
                    calendar.addEvents(dc.addHolidays());
                }
                myCal.addCalendar(calendar);
                UserData.saveUserData(myCal, user.getUserName());
                JOptionPane.showMessageDialog(null, "Calendar Has Been Created!",
                        "Calendar Created", JOptionPane.INFORMATION_MESSAGE);
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

        noRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scrollPane.setVisible(false);
                yearLabel.setVisible(false);
                show();
            }
        });

        yesRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scrollPane.setVisible(true);
                yearLabel.setVisible(true);
                show();
            }
        });
    }

    /***
     * Returns an ArrayList of years starting from 2020 to 2120
     * @return ArrayList of years
     */
    public static ArrayList<Integer> yearsList() {
        ArrayList<Integer> years = new ArrayList<>();
        int start = 2020;
        for (int i = 0; i < 100; i++) {
            years.add(start);
            start++;
        }
        return years;
    }

    /**
     * Makes this window visible.
     */
    public void show() {
        frame.setContentPane(panel1); //puts all our buttons and other objects on the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //what we want to do when the user closes the window
        frame.setSize(620, 320);
        frame.setLocationRelativeTo(null); //opens our window in the center of the screen
        frame.setResizable(false);
        frame.setVisible(true); //displays the window
    }
}
