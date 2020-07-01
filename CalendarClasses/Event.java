package CalendarClasses;


import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDateTime;


public class Event implements Serializable {
    private static final long serialVersionUID = 2L;
    private String name;
    private String memo;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ArrayList<String> listOfTags;

    public Event(String eventName, LocalDateTime start, LocalDateTime end, String eventMemo, ArrayList<String>tags) {
        name = eventName;
        startTime = start;
        endTime = end;
        memo = eventMemo;
        listOfTags = tags;
    }

    public void setEventName(String name) {
        this.name = name;
    }

    public String getEventName() {
        return name;
    }

    public String getMemo() {
        return memo;
    }

    public void editMemo(String newMemo) {
        this.memo = newMemo;
    }

    public ArrayList<String> getListOfTags() {
        return listOfTags;
    }

    public void addTag(String tag) {
        listOfTags.add(tag);
    }

    public void removeTag(String tag) {
        listOfTags.remove(tag);
    }

    public String tagsToString() {
        StringBuilder tags = new StringBuilder();
        for (String s: this.listOfTags) {
            tags.append(s + ", ");
        }
        if (listOfTags.isEmpty()) {
            return tags.toString();
        } else {
            return tags.substring(0, tags.length() - 2);
        }
    }

    public void setTags(ArrayList<String> tags) {
        this.listOfTags = tags;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime newStartTime) {
        this.startTime = newStartTime;
    }

    public void setEndTime(LocalDateTime newEndTime) {
        this.endTime = newEndTime;
    }

    public String toString() {
        StringBuilder s = new StringBuilder("Event: " + this.name + "\nFrom: " + TimeStringConverters.datetimeToString(this.startTime) +
                " to " + TimeStringConverters.datetimeToString(this.endTime) +
                ".\nMemo: " + this.memo + "\nTags: " + this.tagsToString());

        return s.toString();
    }

    public String toSimpleString() {
        return this.name + "\n" + TimeStringConverters.timeInterval(this.startTime, this.endTime);
    }

}
