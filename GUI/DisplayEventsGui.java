package GUI;

import CalendarClasses.Alert;
import CalendarClasses.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * A GUI for displaying Events and Alerts from a users calendar.
 */
public class DisplayEventsGui implements Showable {
    private JPanel mainPanel;
    private JButton btnBack;
    private JLabel lblInstructions;
    private JScrollPane scrlEvents;
    private JPanel eventsPanel;

    private ArrayList<Event> events;
    private ArrayList<Alert> alerts;
    private String[] options;
    private JFrame frame;
    private Showable parent;
    private JDateTextPane dateInfo;

    protected DisplayEventsGui(JDateTextPane tp, Showable parent) {
        this.dateInfo = tp;
        this.parent = parent;
        this.events = dateInfo.getEventsOnDate();
        this.alerts = dateInfo.getAlertsOnDate();
        this.frame = new JFrame("Events on: " + tp.getDateString());

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToParent();
            }
        });
    }

    protected DisplayEventsGui(ArrayList<Event> events, ArrayList<Alert> alerts, Showable parent, String title) {
        this.dateInfo = null;
        this.parent = parent;
        this.events = events;
        this.alerts = alerts;
        this.frame = new JFrame(title);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToParent();
            }
        });
    }

    private void buildEventsPanel() {
        int i = 0;
        eventsPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(3, 0, 3, 0);
        gbc.gridx = 0;
        gbc.weightx = 1;

        eventsPanel.setLayout(new GridBagLayout());
        eventsPanel.setPreferredSize(new Dimension(390, (events.size() + alerts.size()) * 100));

        for (Event e: events) {
            if (e == events.get(events.size() - 1) && alerts.isEmpty()) {
                gbc.weighty = 1;
            }
            gbc.gridy = i;
            JEventTextPane tp = new JEventTextPane(e);
            eventsPanel.add(tp, gbc);
            tp.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    openEditEvent(tp);
                }
            });
            i++;
        }
        for (Alert a: alerts) {
            if (a == alerts.get(alerts.size() - 1)) {
                gbc.weighty = 1;
            }
            gbc.gridy = i;
            JAlertTextPane tp = new JAlertTextPane(a);;
            eventsPanel.add(tp, gbc);
            tp.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    openEditAlert(tp);
                }
            });
            i++;
        }
    }

    private void openEditEvent(JEventTextPane tp) {
        EditEventGui edit = new EditEventGui(tp.getEvent(), this);
        edit.show();
        frame.setVisible(false);
    }

    private void openEditAlert(JAlertTextPane tp) {
        EditAlertGui edit = new EditAlertGui(tp.getAlert(), this);
        edit.show();
        frame.setVisible(false);
    }

    private void returnToParent() {
        parent.show();
        frame.dispose();
    }

    /**
     * Makes this window visible.
     */
    public void show() {
        buildEventsPanel();
        scrlEvents.setViewportView(eventsPanel);
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(405,600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Makes this window visible, while removing an event from what is displayed
     * @param e the event to remove from what is displayed.
     */
    public void show(Event e) {
        this.events.remove(e);
        show();
    }

    /**
     *  Makes this window visible, while removing an alert from what is displayed
     * @param a the alert to remove from what is displayed
     */
    public void show(Alert a) {
        this.alerts.remove(a);
        show();
    }
}
