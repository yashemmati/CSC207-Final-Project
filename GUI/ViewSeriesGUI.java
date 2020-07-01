package GUI;

import CalendarClasses.Calendar;
import CalendarClasses.Event;
import CalendarClasses.Series;
import CalendarClasses.UserData;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A GUI to view series in a logged in users selected calender
 */
public class ViewSeriesGUI {
    private JComboBox comboBox1;
    private JList list1;
    private JButton btnDelete;
    private JPanel mainPanel;
    JFrame frame = new JFrame("View Series");

    protected ViewSeriesGUI(){
        fillComboBox(getSeriesNames(CalendarGui.cal.getSeries()));
        comboBox1.setSelectedItem(null);
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) comboBox1.getSelectedItem();
                list1.setListData(getSeriesEventsByName(s, CalendarGui.cal.getSeries()).toArray(new Event[0]));
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) comboBox1.getSelectedItem();
                CalendarGui.cal.removeSeries(getSeriesByName(s, CalendarGui.cal.getSeries()));
                comboBox1.removeItem(s);
                UserData.saveCalendar(CalendarGui.cal, CalendarGui.user);
            }
        });
    }

    private ArrayList<String> getSeriesNames(ArrayList<Series> series){
        ArrayList<String> names = new ArrayList<>();
        for (Series s : series) {
            names.add(s.getSeriesName());
        }
        return names;
    }

    private ArrayList<Event> getSeriesEventsByName(String name, ArrayList<Series> series){
        ArrayList<Event> found = new ArrayList<>();
        for (Series s : series) {
            if (name.equals(s.getSeriesName())) {
                found = s.getEventsInSeries();
            }
        }
        return found;
    }
    
    private Series getSeriesByName(String name, ArrayList<Series> series){
        Series found = null;
        for (Series s : series) {
            if (name.equals(s.getSeriesName())) {
                found = s;
            }
        }
        return found;
    }
    
    private void fillComboBox(ArrayList<String> names){
        for(String s: names){
            comboBox1.addItem(s);
        }
    }

    /**
     * Makes this window visible
     */
    public void show(){
        frame.setContentPane(mainPanel); //puts all our buttons and other objects on the window
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null); //opens our window in the center of the screen
        frame.setResizable(false);
        frame.setVisible(true); //displays the window
    }
}
