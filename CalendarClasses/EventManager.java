package CalendarClasses;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class EventManager extends Manager {
    private static final long serialVersionUID = 2L;
    public ArrayList<Event> eventList = new ArrayList<>();
    protected EventManager(){
    }

    /**
     * creates a new event
     * @param name the name of the event
     * @param startTime  the start time of the event
     * @param endTime  the end time of the event
     * @param memo the memo associated with the event
     * @param tags all the tags associated with the event
     * @return the event that was created
     */
    public Event createEvent(String name, LocalDateTime startTime, LocalDateTime endTime, String memo, ArrayList<String> tags) {
        Event newEvent = new Event(name,startTime, endTime, memo, tags);
        this.eventList.add(newEvent);
        return newEvent;
    }

    /**
     * add a new event to the eventList attribute
     * @param item the event to be added to eventList
     */
    public void addEvent(Event item) {
        eventList.add(item);
    }

    /**
     * Removes an Event from this calendar.
     * @param e the Event to remove.
     */
    public void removeEvent(Event e) {
        eventList.remove(e);
    }

    private boolean inEventList(Event e) {
        for (Event in: eventList) {
            if (in == e) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns all events in this calendar that occur on a date.
     * @param date the date to find events on.
     * @return ArrayList<Event> events occurring on date.
     */
    public ArrayList<Event> getEventsByDate(LocalDateTime date) {
        ArrayList<Event> eventsOnDate = new ArrayList<>();
        for (Event item : eventList) {
            if ((item.getStartTime().getMonth() == date.getMonth())&&(item.getStartTime().getDayOfMonth()==
                    date.getDayOfMonth())&&(item.getStartTime().getYear()== date.getYear())){
                eventsOnDate.add(item);
            }
        }
        return eventsOnDate;
    }

    /**
     * Returns all events in the calendar that have a certain tag.
     * @param tag the tag to find events with.
     * @return ArrayList<Event> events that have the tag tag
     */
    public ArrayList<Event> getEventsWithTag(String tag){
        ArrayList<Event> eventsSameTag = new ArrayList<>();
        for (Event item: eventList){
            for(String eventTag: item.getListOfTags()){
                if (tag.equals(eventTag)){
                    eventsSameTag.add(item);
                }
            }
        }
        return eventsSameTag;
    }

    /**
     * Returns a string representation of all events in this calendar
     * @return String of all events in this calendar
     */
    public String toString(){
        StringBuilder s = new StringBuilder();
        for (Event event: this.eventList){
            s.append(event.toString()).append("\n");
        }
        return s.toString();
    }

    private Boolean checkInList(ArrayList<String> listOfStrings, String message){
        for (String string: listOfStrings){
            if(string.equals(message)){
                return false;
            }
        }
        return true;
    }

    /**
     * Returns all Memos in events.
     * @return ArrayList<String> memo's from events.
     */
    public ArrayList<String> getMemos(){
        ArrayList<String> memoList = new ArrayList<>();
        for (Event event: eventList){
            if(!(event.getMemo().equals(""))&&(checkInList(memoList, event.getMemo())) ){
                memoList.add(event.getMemo());
            }
        }
        return memoList;
    }

    /**
     * Returns all tags from events.
     * @return ArrayList<String> tag's from events
     */
    public ArrayList<String> getTags() {
        ArrayList<String> tagList = new ArrayList<>();
        for (Event event: eventList) {
            for (String s : event.getListOfTags()) {
                if (checkInList(tagList, s)) {
                    tagList.add(s);
                }
            }
        }
        return tagList;
    }

    /**
     * Returns all events with memo matching memo.
     * @param memo
     * @return
     */
    public ArrayList<Event> getEventsWithMemo(String memo){
        ArrayList<Event> events = new ArrayList<>();
        for (Event item: eventList){
            if(item.getMemo().equals(memo)){
                events.add(item);
            }
        }
        return events;
    }

    /**
     * Edits an event in the calendar to have the new details specified.
     * @param e The event we are editing.
     * @param name the new name of the event.
     * @param start the new start time of the event.
     * @param end the new end time of the event.
     * @param memo the new memo for the event.
     * @param tags the new tags for the event.
     */
    public void editEvent(Event e, String name, LocalDateTime start, LocalDateTime end, String memo, ArrayList<String> tags) {
        if (this.inEventList(e)) {
            e.setEventName(name);
            e.setStartTime(start);
            e.setEndTime(end);
            e.editMemo(memo);
            e.setTags(tags);
        }
    }

}
