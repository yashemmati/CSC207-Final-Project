package CalendarClasses;

import java.sql.Time;
import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * A representation of a calendar containing Events, series of Events, and alerts for Events in the calendar.
 */
public class Calendar implements java.io.Serializable {
    private static final long serialVersionUID = 2L;
    private EventManager EM = new EventManager();
    private SeriesManager SM = new SeriesManager();
    private AlertManager AM = new AlertManager();

    /**
     * The name that this calendar will be called.
     */
    public String name;

    /**
     * Creates a new calendar, setting the name of the calendar to {@link name}
     * @param name The name to be given to this calendar
     */
    public Calendar(String name) {
        this.name = name;
    }

    /**
     * Creates and returns an event in this calendar
     * @param name name of event to be created
     * @param start  start time of event to be created
     * @param end end time of event to be created
     * @param memo a string to associate with this event
     * @param tags a list of tags to associate with this event
     * @return the event that was created
     */
    public Event createEvent(String name, LocalDateTime start, LocalDateTime end, String memo, ArrayList<String> tags) {
        return EM.createEvent(name, start, end, memo, tags);
    }

    /**
     * creates an alert for an event to be stored in this calendar
     * @param toAddAlerts the event this alert is for
     * @param date the datetime of when this alert occurs
     */
    public void createAlert(Event toAddAlerts, LocalDateTime date) {
        AM.createAlert(date, toAddAlerts.getStartTime(), toAddAlerts.getEventName());
    }

    /**
     * creates an alert for an event every <frequency></> hours
     * @param toAddAlerts event to associate with these alerts
     * @param date the DateTime to start the alerts from
     * @param frequency the amount of hours between alerts
     */
    public void createFrequencyAlertFromTime(Event toAddAlerts, LocalDateTime date, int frequency) {
        LocalDateTime curr = date.plusHours(frequency);
        while (curr.isBefore(toAddAlerts.getStartTime())) {
            createAlert(toAddAlerts, curr);
            curr = curr.plusHours(frequency);
        }
    }

    /**
     * Creates a series based on an amount of days between events.
     * @param name the name of this series
     * @param first the first event in this series
     * @param frequency the events in this series
     * @param daysBetweenEvents the number of days between events
     */
    public void createFrequencySeries(String name, Event first,  int frequency, int daysBetweenEvents) {
        ArrayList<Event> inSeries = new ArrayList<>();

        inSeries.add(first);

        Event nextEvent = null;
        for (int i = 1; i < frequency; i++) {
            nextEvent = new Event(first.getEventName(), first.getStartTime().plusDays(daysBetweenEvents * i),
                    first.getEndTime().plusDays(daysBetweenEvents * i), first.getMemo(), first.getListOfTags());
            inSeries.add(nextEvent);
        }
        SM.createSeries(name, inSeries);
        for (int i = 1; i < inSeries.size(); i++) {
            EM.addEvent(inSeries.get(i));
        }
    }

    /**
     * Creates a new series of events where all events in this series occur on the same time with 1 month between their dates.
     * @param name The name to set for this series
     * @param first The first event to occur in the series, all other events will be duplicates
     *              with different start and end times.
     * @param frequency the number of events to occur in this series, including first.
     */
    public void createMonthlyFrequencySeries(String name, Event first,  int frequency) {
        ArrayList<Event> inSeries = new ArrayList<>();

        inSeries.add(first);

        Event nextEvent = null;
        for (int i = 1; i < frequency; i++) {
            nextEvent = new Event(first.getEventName(), first.getStartTime().plusMonths(i), first.getEndTime().plusMonths(i), first.getMemo(), first.getListOfTags());
            inSeries.add(nextEvent);
        }

        SM.createSeries(name, inSeries);
        for (int i = 1; i < inSeries.size(); i++) {
            EM.addEvent(inSeries.get(i));
        }
    }

    /**
     * Create a series of events from a list of events
     * @param name the name of this series
     * @param events the events in this series
     */
    public void createSeries(String name, ArrayList<Event> events) {
        SM.createSeries(name, events);
    }

    /**
     * Finds all events and alerts occurring on a date
     * @param date the day that we went events and alerts to occur on
     * @return a ArrayList where the first index is an ArrayList of events and the second is ArrayList of alerts
     */
    public ArrayList[] findByDate(LocalDateTime date) {
        ArrayList<Event> events = EM.getEventsByDate(date);
        ArrayList<Alert> alerts = AM.getAlertsByDate(date);
        return new ArrayList[]{events, alerts};
    }

    /**
     * Finds all events in this calendar that contain memo as their memo.
     * @param memo the memo to search for events with.
     * @return ArrayList<Event> Events containing memo memo
     */
    public ArrayList<Event> findByMemo(String memo) {
        return EM.getEventsWithMemo(memo);
    }

    /**
     * Finds all Events that contain the tag tag in their list of tags
     * @param tag the tag to search for events with
     * @return ArrayList<Event> Events containing tag tag
     */
    public ArrayList<Event> findByTag(String tag) {
        return EM.getEventsWithTag(tag);
    }

    /**
     * Returns all Memos found in events in this calendar.
     * @return ArrayList<String> Memos that events in this calendar have.
     */
    public ArrayList<String> allMemos() {
        return EM.getMemos();
    }

    /**
     * Returns all Tags found in events in this calendar.
     * @return ArrayList<String> Tags that events in this calendar have.
     */
    public ArrayList<String> allTags() {
        return EM.getTags();
    }

    /**
     * getter for the name given to this calendar.
     * @return the name given to this calendar.
     */
    public String getName(){ return name; }

    /**
     * Adds a list of events to this calendar.
     * @param events list of events to add to this calendar
     */
    public void addEvents(ArrayList<Event> events){
        for(Event e: events){
            EM.addEvent(e);
        }
    }

    /**
     * Finds the series that an event occurs in and returns the series. returns null if the
     * event doesnt occur as a part of a series.
     * @param e the event to search for it's series
     * @return Series the series that e occurs in.
     */
    public Series getSeries(Event e) {
        return SM.findSeries(e);
    }

    /**
     * Removes the event toRemove from this Calendar
     * @param toRemove the event to remove from this Calendar.
     */
    public void removeEvent(Event toRemove) {
        EM.removeEvent(toRemove);
        AM.removeAlertWithName(toRemove.getEventName());
    }

    /**
     * Removes the alert toRemove from this calendar.
     * @param toRemove the alert to remove the calendar.
     */
    public void removeAlert(Alert toRemove) {
        AM.removeAlert(toRemove);
    }

    /**
     * Removes the series toRemove from this calendar
     * @param toRemove the series to remove from this calendar
     */
    public void removeSeries(Series toRemove) {
        SM.removeSeries(toRemove);
    }

    /**
     * Changes the name, start time, end time, memo, and tags of an event to new values. Also edits
     * alerts for these events.
     * @param e The event to change.
     * @param name The new name to set for event e.
     * @param start The new start time for event e.
     * @param end The new end time for event e.
     * @param memo The new memo for the event e.
     * @param tags The new list of tags for event e.
     */
    public void editEvent(Event e, String name, LocalDateTime start, LocalDateTime end, String memo, ArrayList<String> tags) {
        ArrayList<Alert> toEdit = AM.getAlertsForEventName(e.getEventName());
        EM.editEvent(e, name, start, end, memo, tags);
        for (Alert a: toEdit) {
            AM.editEventNameOfAlert(a, name);
            AM.editEventTimeOfAlert(a, start);
        }
    }

    /**
     * Changes the time of an alert in this calendar.
     * @param a the alert to change the time of.
     * @param newTime the new time to set for this alert
     */
    public void editAlert(Alert a, LocalDateTime newTime) {
        AM.editTimeOfAlert(a, newTime);
    }

    /**
     * Return a list of all Events in this calendar.
     * @return
     */
    public  ArrayList<Event> getEvents(){
        return EM.eventList;
    }

    /**
     * Returns a list of all series in this calendar.
     * @return ArrayList<Series> All Series in this calendar
     */
    public ArrayList<Series> getSeries() { return SM.getListOfSeries(); }
}


