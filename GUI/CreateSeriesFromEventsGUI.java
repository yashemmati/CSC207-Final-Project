package GUI;

import CalendarClasses.Calendar;
import CalendarClasses.Event;
import CalendarClasses.UserData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/***
 * This class creates a GUI that allows the user to create a series from existing events
 */
public class CreateSeriesFromEventsGUI {
    private JList list1;
    private JTextField textName;
    private JButton createSeriesBtn;
    private JButton backBtn;
    private JPanel mainPanel;
    private Calendar cal = CalendarGui.cal;
    JFrame frame = new JFrame("Create Series");
    private CalendarGui parent;

    protected CreateSeriesFromEventsGUI(Showable parent){
        this.parent =(CalendarGui) parent;
        updateList();
        list1.setSelectedIndex(0);
        createSeriesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textName.getText() != null) {
                    cal.createSeries(textName.getText(), getSelectedEvents());
                    JOptionPane.showMessageDialog(null,
                            "Series has Been Created", "Create Series", JOptionPane.INFORMATION_MESSAGE);
                    returnToParent();
                } else {
                    JOptionPane.showMessageDialog(null, "Please Enter a Name",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                }
                UserData.saveCalendar(cal, CalendarGui.user);
            }
        });

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToParent();
            }
        });
    }

    private void updateList(){
        Event[] events = cal.getEvents().toArray(new Event[0]);
        list1.setListData(events);
    }

    private ArrayList<Event> getSelectedEvents(){
        return new ArrayList<Event>(list1.getSelectedValuesList());
    }

    private void returnToParent() {
        parent.show();
        frame.dispose();
    }

    /**
     * Makes this window visible.
     */
    public void show(){
        frame.setContentPane(mainPanel); //puts all our buttons and other objects on the window
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null); //opens our window in the center of the screen
        frame.setResizable(false);
        frame.setVisible(true); //displays the window
    }
}
