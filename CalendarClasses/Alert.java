package CalendarClasses;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * An object to representan alert in a calendar
 */
public class Alert implements Serializable {
    private static final long serialVersionUID = 2L;
    private LocalDateTime time;
    private LocalDateTime eventTime;
    private String eventName;

    protected Alert(LocalDateTime alertTime, LocalDateTime eventTime, String eventName) {
        //Shouldn't be used outside of this package
        this.time = alertTime;
        this.eventName = eventName;
        this.eventTime = eventTime;
    }

    /**
     * Returns the name of the event that this alert is for.
     * @return the name of the event this alert is for
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Sets the time that this Alert will occur at
     * @param time the new time for this alert to occur at
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * Sets the name of the Event this alert is for
     * @param name the new Event Name for this Alert.
     */
    public void setEventName(String name) {
        this.eventName = name;
    }


    /**
     * Sets the Event time of the event this alert is for
     * @param newTime the new time to set as the events time
     */
    public void  setEventTime(LocalDateTime newTime) {
        this.eventTime = newTime;
    }

    /**
     * Returns a string representation of the alert with the structure of: Alert time \n Event name, Event time
     * @return String containing the alert time, event name, and event time
     */
    public String toString(){
        return "Alert scheduled for: " + TimeStringConverters.datetimeToString(this.time) +
                "\nNotification: "  + "Alert: " + eventName + "\nOn: " + TimeStringConverters.datetimeToString(this.eventTime);
    }

    /**
     * Returns the notification for this alert.
     * @return String of structure: Alert: Event name \nOn: Event Time
     */
    public String getNotification() {
        return "Alert: " + eventName + "\nOn: " + TimeStringConverters.datetimeToString(this.eventTime);
    }

    /**
     * Returns the time that this alert occurs at.
     * @return LocalDateTime the time of this alert.
     */
    public LocalDateTime getTime(){
        return time;
    }


}
