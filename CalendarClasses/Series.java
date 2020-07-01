package CalendarClasses;
import java.io.Serializable;
import java.util.ArrayList;

public class Series implements Serializable {
    private String seriesName;
    private ArrayList<Event> eventsInSeries;
    private static final long serialVersionUID = 2L;

    protected Series(ArrayList<Event> eventsOfSeries, String nameOfSeries){
        eventsInSeries = eventsOfSeries;
        seriesName = nameOfSeries;
    }

    /**
     * Returns the name of this series
     * @return the name of this series
     */
    public String getSeriesName() {
        return seriesName;
    }

    /**
     * Returns all of the events that are in this series.
     * @return events in this series.
     */
    public ArrayList<Event> getEventsInSeries() {
        return eventsInSeries;
    }

    /**
     * Adds an Event to this series
     * @param event the event to add
     */
    public void addEvent(Event event){
        eventsInSeries.add(event);
    }

    /**
     * Removes an event from this series
     * @param event  the event to remove
     */
    public void removeEvent(Event event){
        eventsInSeries.remove(event);
    }

    /**
     * Returns a string representation of this series and its events
     * @return String representing this series
     */
    public String toString() {
        String s = "Series name: " + this.seriesName + ".\nEvents in Series: \n";
        for (Event e: this.eventsInSeries) {
            s += e.toString() + "\n";
        }

        return s;
    }

}
