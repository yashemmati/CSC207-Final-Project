package CalendarClasses;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class to manage all objects of type Alert
 */
public class AlertManager extends Manager{
    private static final long serialVersionUID = 2L;
    private ArrayList<Alert> listOfAlerts = new ArrayList<>();

    protected AlertManager(){
        //Shouldn't be used outside this package.
    }

    /**
     * creates an Alert
     * @param time time that alert is suppose to happen
     * @param timeOfEvent start time of the event alert is used for
     * @param nameOfEvent name of the event occurring
     */
    public void createAlert(LocalDateTime time, LocalDateTime timeOfEvent, String nameOfEvent){
        if (time.getMinute() != timeOfEvent.getMinute()) {
            time.minusMinutes(time.getMinute());
            time.plusMinutes(timeOfEvent.getMinute());
        }
        Alert newAlert = new Alert(time, timeOfEvent, nameOfEvent);
        listOfAlerts.add(newAlert);
    }

    /**
     * Adds an alert to manage
     * @param alert alert to be managed
     */
    public void addAlert(Alert alert){
        listOfAlerts.add(alert);
    }

    /**
     * deletes an alert from the calendar
     * @param alert alert to remove
     */
    public void removeAlert(Alert alert){
        listOfAlerts.remove(alert);
    }

    /**
     * Removes all alerts with eventName of name.
     * @param name the eventName to search for alerts with and remove them.
     */
    public void removeAlertWithName(String name) {
        ArrayList<Alert> toRemove = new ArrayList<>();
        for (Alert a: this.listOfAlerts) {
            toRemove.add(a);
        }
        for (Alert a: toRemove) {
            this.removeAlert(a);
        }
    }

    /**
     * Sets a new time for a specified alert in this calendar to occur at.
     * @param a the alert to change time of.
     * @param newTime the new time to set a to occur at.
     */
    public void editTimeOfAlert(Alert a, LocalDateTime newTime) {
        if (inListOfAlerts(a)) {
            a.setTime(newTime);
        }
    }

    /**
     * Sets a new event name for an alert in this calendar
     * @param a the alert to change the event name of.
     * @param name the new Event Name.
     */
    public void editEventNameOfAlert(Alert a, String name) {
        if (inListOfAlerts(a)) {
            a.setEventName(name);
        }
    }

    /**
     * Sets a new event time of an alert in this calendar
     * @param a the alert to change the event name of.
     * @param eventTime the new start time of the event this alert is for
     */
    public void editEventTimeOfAlert(Alert a, LocalDateTime eventTime) {
        if (inListOfAlerts(a)) {
            a.setEventTime(eventTime);
        }
    }

    private boolean inListOfAlerts(Alert  a) {
        for (Alert al: listOfAlerts) {
            if (al == a) {
                return true;
            }
        }
        return false;
    }


    /**
     * getter for the private attributes listOfAlerts
     * @return the lisOfAlerts attribute
     */
    public ArrayList<Alert> getListOfAlerts(){
        return listOfAlerts;
    }

    /**
     * get an array list of all the alerts that will happen on a certain date
     * @param date the date in which you want to get all the alerts happening on that date
     * @return ArrayList<Alert> all the alerts happening on date
     */
    public ArrayList<Alert> getAlertsByDate(LocalDateTime date){
        ArrayList<Alert> alertsOnDay = new ArrayList<>();
        for(Alert alert: listOfAlerts){
            if((alert.getTime().getMonth()== date.getMonth())&&(alert.getTime().getDayOfMonth()==date.getDayOfMonth())&&
                    (alert.getTime().getYear() == date.getYear())){
                alertsOnDay.add(alert);
            }
        }
        return alertsOnDay;
    }

    /**
     * returns all alerts for the event with name name
     * @param name the name of the event to get alerts for
     * @return ArrayList<Alert> List of alerts with event name.
     */
    public ArrayList<Alert> getAlertsForEventName(String name) {
        ArrayList<Alert> toReturn = new ArrayList<>();
        for (Alert a: this.listOfAlerts) {
            if (a.getEventName().equals(name)) {
                toReturn.add(a);
            }
        }
        return toReturn;
    }

    /**
     * Returns a string representation of all the alerts in a calendar
     * @return String All alerts in a calendar
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Alert a: this.listOfAlerts) {
            s.append(a.toString()).append("\n");
        }
        return s.toString();
    }



}
