package GUI;

import CalendarClasses.Alert;
import CalendarClasses.Calendar;
import CalendarClasses.Event;
import CalendarClasses.TimeStringConverters;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * An extension of a JTextPane to only show a calendar date
 */
public class JDateTextPane extends JTextPane {
    /**
     * The Calendar this date is in
     */
    private Calendar cal = null;
    /**
     * The date that this pane will show
     */
    private LocalDateTime date = null;

    /**
     * Sets the date this pane will represent
     * @param date the date to set
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Sets the calendar we will show events on date from
     * @param cal the calendar to set
     */
    public void setCal(Calendar cal) {
        this.cal = cal;
    }

    /**
     * Gets the date this pane represents
     * @return the date this represents
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Returns a string representation of the date
     * @return String of the date
     */
    public String getDateString() {
        if (date.equals(null)) {
            return "Null";
        } else {
            return TimeStringConverters.datetimeToString(date);
        }
    }

    /**
     * Returns whether this date has any events/alerts in the calendar or not
     * @return Boolean if this date has no events/alerts
     */
    public boolean isEmpty() {
        return cal.findByDate(date)[0].isEmpty() && cal.findByDate(date)[1].isEmpty();
    }

    /**
     * Returns any Events on the date this pane represents in the calendar
     * @return Events on date
     */
    public ArrayList<Event> getEventsOnDate() {
        return (ArrayList<Event>)cal.findByDate(date)[0];
    }

    /**
     * Returns any alerts on the date this pane represents in the calendar
     * @return alerts on date
     */
    public ArrayList<Alert> getAlertsOnDate() {
        return (ArrayList<Alert>)cal.findByDate(date)[1];
    }

    /**
     * Fills the pane with text representing the date and events/alerts on it
     */
    public void fillText() {
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setBold(attributeSet, true);
        this.setCharacterAttributes(attributeSet, true);
        this.setText(String.valueOf(date.getDayOfMonth()) + "\n");

        StyleConstants.setBold(attributeSet, false);
        Document content = this.getStyledDocument();

        ArrayList<Event> events = (ArrayList<Event>)cal.findByDate(date)[0];
        ArrayList<Alert> alerts = (ArrayList<Alert>)cal.findByDate(date)[1];


        for (Event e : events) {
            try {
                content.insertString(content.getLength(), e.toSimpleString() + "\n", attributeSet);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        }

        for (Alert a : alerts) {
            try {
                content.insertString(content.getLength(), a.getNotification() + "\n", attributeSet);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        }
    }

}
