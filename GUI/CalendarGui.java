package GUI;

import CalendarClasses.*;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDateTime;

/**
 * A GUI representation of a calendar that allows a user to interact with objects in their calendar
 */
public class CalendarGui implements Showable {
    private JPanel mainPanel;
    private JButton btnLogOut;
    private JToolBar tbarOptions;
    private JLabel lblSunday;
    private JLabel lblMonday;
    private JLabel lblTuesday;
    private JLabel lblWednesday;
    private JLabel lblThursday;
    private JLabel lblFriday;
    private JLabel lblSaturday;
    private JDateTextPane tpSunday1, tpMonday1, tpTuesday1, tpWednesday1, tpThursday1, tpFriday1, tpSaturday1,
            tpSunday2, tpMonday2, tpTuesday2, tpWednesday2, tpThursday2, tpFriday2, tpSaturday2,
            tpSunday3, tpMonday3, tpTuesday3, tpWednesday3, tpThursday3, tpFriday3, tpSaturday3,
            tpSunday4, tpMonday4, tpTuesday4, tpWednesday4, tpThursday4, tpFriday4, tpSaturday4,
            tpSunday5, tpMonday5, tpTuesday5, tpWednesday5, tpThursday5, tpFriday5, tpSaturday5,
            tpSunday6, tpMonday6, tpTuesday6, tpWednesday6, tpThursday6, tpFriday6, tpSaturday6;
    private JDateTextPane[] dayTp = {tpSunday1, tpMonday1, tpTuesday1, tpWednesday1, tpThursday1, tpFriday1, tpSaturday1,
            tpSunday2, tpMonday2, tpTuesday2, tpWednesday2, tpThursday2, tpFriday2, tpSaturday2,
            tpSunday3, tpMonday3, tpTuesday3, tpWednesday3, tpThursday3, tpFriday3, tpSaturday3,
            tpSunday4, tpMonday4, tpTuesday4, tpWednesday4, tpThursday4, tpFriday4, tpSaturday4,
            tpSunday5, tpMonday5, tpTuesday5, tpWednesday5, tpThursday5, tpFriday5, tpSaturday5,
            tpSunday6, tpMonday6, tpTuesday6, tpWednesday6, tpThursday6, tpFriday6, tpSaturday6};
    private JButton btnSwitchView;
    private JButton btnMinusTime;
    private JButton btnPlusTime;
    private JButton btnCreateEvent;
    private JButton btnFindEvent;
    private JButton btnChangeCalendar;
    private JButton btnCreateSeries;
    private JButton btnViewSeries;
    public static User user;
    public static Calendar cal;
    private int viewing;

    /**
     * The Calendar currently being displayed in our GUI
     */
    private LocalDateTime currTime;

    private JFrame frame;

    protected CalendarGui(User user, String title, Calendar cal) {
        this.user = user;
        this.frame = new JFrame(title);
        LocalDateTime now = LocalDateTime.now();
        CalendarGui.cal = cal;
        this.viewing = 1;

        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setIsLogged(false);
                LoginGui l = new LoginGui();
                l.show();
                frame.dispose();
            }
        });
        btnMinusTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subtractTime();
            }
        });
        btnPlusTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTime();
            }
        });
        btnCreateEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddEventGui ae = new AddEventGui(cal);
                ae.show();
            }
        });
        btnFindEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFindGui();
            }
        });
        btnChangeCalendar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewCalendars view = new ViewCalendars(user);
                view.show();
                frame.dispose();
            }
        });
        btnSwitchView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (viewing == 3) {
                    viewing = 1;
                } else {
                    viewing++;
                }
                showView();
            }
        });
        btnCreateSeries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createSeries();
            }
        });
        btnViewSeries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewSeriesGUI vs = new ViewSeriesGUI();
                vs.show();
            }
        });
    }

    private void showView() {
        currTime = LocalDateTime.now();
         if (viewing == 2){
            while (currTime.getDayOfWeek().toString() != "SUNDAY") {
                currTime = currTime.minusDays(1);
            }
        } else if (viewing == 1) {
            currTime = currTime.minusDays(currTime.getDayOfMonth() - 1);
        }
        fillCalendar(currTime);
    }

    private void fillCalendar(LocalDateTime firstDay) {
        LocalDateTime now = LocalDateTime.now();
        if (viewing == 1) {
            int numDays = firstDay.toLocalDate().lengthOfMonth();
            int firstIndex = getDayIndex(firstDay);
            int lastIndex = numDays + firstIndex;

            btnSwitchView.setText(firstDay.getMonth().toString() + " " + firstDay.getYear());
            lblWednesday.setText("Wednesday");
            showLabels(true);

            viewMonth(lastIndex > 35);

            for (int i = 0; i < 42; i++) {
                if (i < firstIndex || i >= lastIndex) {
                    dayTp[i].setText("");
                } else {
                    dayTp[i].setDate(firstDay);
                    dayTp[i].setCal(cal);
                    dayTp[i].fillText();
                    firstDay = firstDay.plusDays(1);
                }
            }
        } else if (viewing == 2) {
            btnSwitchView.setText("Week of: " + firstDay.getMonth().toString() + " " + firstDay.getDayOfMonth());
            viewWeek();
            for (int i = 0; i < 7; i++) {
                dayTp[i].setDate(firstDay);
                dayTp[i].setCal(cal);
                dayTp[i].fillText();
                firstDay = firstDay.plusDays(1);
            }
            frame.setSize(1100, 300);
        } else {
            btnSwitchView.setText(firstDay.getMonth().toString() + " " + firstDay.getDayOfMonth());
            lblWednesday.setText(firstDay.getDayOfWeek().toString());
            showLabels(false);
            for (int i = 0; i < 42; i++) {
                if (i == 3) {
                    dayTp[i].setDate(firstDay);
                    dayTp[i].setCal(cal);
                    dayTp[i].fillText();
                } else {
                    dayTp[i].setVisible(false);
                }
            }
            frame.setSize(700, 300);
        }
    }

    private void addTime() {
        if (viewing == 1) {
            currTime = currTime.plusMonths(1);
        } else if (viewing == 2) {
            currTime = currTime.plusDays(7);
        } else {
            currTime = currTime.plusDays(1);
        }
        fillCalendar(currTime);
    }

    private void subtractTime() {
        if (viewing == 1) {
            currTime = currTime.minusMonths(1);
        } else if (viewing == 2) {
            currTime = currTime.minusDays(7);
        } else {
            currTime = currTime.minusDays(1);
        }
        fillCalendar(currTime);
    }

    private void showLabels(boolean show) {
        lblSunday.setVisible(show);
        lblMonday.setVisible(show);
        lblTuesday.setVisible(show);
        lblThursday.setVisible(show);
        lblFriday.setVisible(show);
        lblSaturday.setVisible(show);
    }

    private void addListenersToPanes() {
        for(JDateTextPane tp: dayTp) {
            removeAnyListener(tp);
            tp.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    displayEvents(tp);
                    super.mouseClicked(e);
                }
            });
        }
    }

    private void removeAnyListener(JDateTextPane tp) {
        for (MouseListener ml : tp.getMouseListeners()) {
            tp.removeMouseListener(ml);
        }
    }

    private void displayEvents(JDateTextPane tp) {
        if (tp.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No Events in This Time Frame!",
                    "No Events", JOptionPane.INFORMATION_MESSAGE);
        } else {
            DisplayEventsGui d = new DisplayEventsGui(tp, this);
            d.show();
            frame.setVisible(false);
        }
    }

    private void viewMonth(boolean visible) {
        for (int i = 0; i < 42; i++) {
            if (i < 35) {
                dayTp[i].setVisible(true);
            } else {
                dayTp[i].setVisible(visible);
            }
        }

        if (visible) {
            frame.setSize(1100, 935);
        } else {
            frame.setSize(1100, 800);
        }

    }

    private void viewWeek() {
        for (int i = 7; i < 42; i++) {
            dayTp[i].setVisible(false);
        }
    }

    private int getDayIndex(LocalDateTime day) {
        String dayOfWeek = day.getDayOfWeek().toString();
        switch (dayOfWeek) {
            case "SUNDAY": return 0;
            case "MONDAY": return 1;
            case "TUESDAY": return 2;
            case "WEDNESDAY": return 3;
            case "THURSDAY": return 4;
            case "FRIDAY": return 5;
            case "SATURDAY": return 6;
            default: return -1;
        }
    }

    private void showFindGui() {
        FindEventGui find = new FindEventGui(cal, this);
        find.show();
        frame.setVisible(false);
    }

    private void createSeries(){
        String[] options = new String[]{"From Existing Events", "By Frequency"};
        int choice = JOptionPane.showOptionDialog(frame,
                "Would you like to create from existing Events, or by frequency?",
                "Select an Item", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        if (choice == 0) {
            CreateSeriesFromEventsGUI csfe = new CreateSeriesFromEventsGUI(this);
            csfe.show();
            frame.setVisible(false);
        } else {
            CreateSeriesFromFrequencyGUI csff = new CreateSeriesFromFrequencyGUI(this);
            csff.show();
            frame.setVisible(false);
        }
    }

    /**
     * Makes this window visible.
     */
    public void show() {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showView();
        addListenersToPanes();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}
