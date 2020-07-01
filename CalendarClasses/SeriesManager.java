package CalendarClasses;

import java.util.ArrayList;

public class SeriesManager extends Manager {
    private static final long serialVersionUID = 2L;
    private ArrayList<Series> listOfSeries = new ArrayList<>();

    protected SeriesManager(){
    }

    /**
     * Removes a series from this calendar
     * @param series the series to remove
     */
    public void removeSeries(Series series){
        listOfSeries.remove(series);
    }

    /**
     * Returns a list of all series in this calendar
     * @return ArrayList<Series> list of all series in calendar
     */
    public ArrayList<Series> getListOfSeries(){
        return listOfSeries;
    }

    /**
     * Creates a new series in the calendar with the given name and list of events in the series.
     * @param name the name of the new series
     * @param inSeries the events in this series
     */
    public void createSeries(String name, ArrayList<Event> inSeries) {
        Series s = new Series(inSeries, name);
        this.listOfSeries.add(s);
    }

    /**
     * Returns a String representation of all series in this calendar
     * @return String of all series in the calendar
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Series ser: this.listOfSeries) {
            s.append(ser.toString()).append("\n");
        }
        return s.toString();
    }

    /**
     * Finds a series of a given event, if its not in a series null is returned
     * @param e the event to search for its series
     * @return Series of the event, or null.
     */
    public Series findSeries(Event e) {
        for (Series s: listOfSeries) {
            if (s.getEventsInSeries().contains(e)) {
                return s;
            }
        }
        return null;
    }

}
