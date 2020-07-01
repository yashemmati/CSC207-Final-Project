package CalendarClasses;
import java.util.ArrayList;

public class CalendarManager  extends Manager{
    private static final long serialVersionUID = 2L;
    private ArrayList<Calendar> listOfCalenders = new ArrayList<>();
    public CalendarManager(){

    }

    public void  addCalendar(Calendar calendar){
        listOfCalenders.add(calendar);
    }

    public void removeCalendar(Calendar calendar){
        listOfCalenders.remove(calendar);
    }

    public ArrayList<Calendar> getListOfCalendars(){
        return listOfCalenders;
    }

    public ArrayList<String> getCalendarNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Calendar c: this.listOfCalenders) {
            names.add(c.getName());
        }
        return names;
    }

    public Calendar getCalendar(String name) {
        int i = 0;
        while (i < getListOfCalendars().size()) {
            if (getListOfCalendars().get(i).getName().equals(name)) {
                return getListOfCalendars().get(i);
            }
            else {i++;}
        }
        return null;
    }
}

