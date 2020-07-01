package CalendarClasses;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DefaultCalendar{

    private ArrayList<Integer> years;
    //gets the list of years the user wants the holidays to show for
    public DefaultCalendar(ArrayList<Integer> yrs){
        this.years = yrs;
    }

    private EventManager em = new EventManager();

    public ArrayList<Event> addHolidays(){
        //This function will get the events and add a holiday tag to them, and update it according to the years provided
        //by the user.

        ArrayList<String> tags = new ArrayList<String>(1);
        tags.add("holiday");
        //waiting to find out what is the name of the class


        for (Integer year : years) {
            LocalDateTime newstart = TimeStringConverters.stringToDateTime(year + "-01-01 00:00");
            LocalDateTime newfinish = TimeStringConverters.stringToDateTime(year + "-01-02 00:00");
            em.createEvent("New Year's Day", newstart, newfinish, "", tags);

            LocalDateTime famdaystart = TimeStringConverters.stringToDateTime(year + "-02-17 00:00");
            LocalDateTime famdayfinish = TimeStringConverters.stringToDateTime(year + "-02-18 00:00");
            em.createEvent("Family Day", famdaystart, famdayfinish, "", tags);

            LocalDateTime vdaystart = TimeStringConverters.stringToDateTime(year + "-02-14 00:00");
            LocalDateTime vdayfinish = TimeStringConverters.stringToDateTime(year + "-02-15 00:00");
            em.createEvent("Valentine's Day", vdaystart, vdayfinish, "", tags);

            LocalDateTime stpstart = TimeStringConverters.stringToDateTime(year + "-03-17 00:00");
            LocalDateTime stpfinish = TimeStringConverters.stringToDateTime(year + "-03-18 00:00");
            em.createEvent("St. Patrick's Day", stpstart, stpfinish, "", tags);

            LocalDateTime goodfridaystart = TimeStringConverters.stringToDateTime(year + "-04-10 00:00");
            LocalDateTime goodfridayfinish = TimeStringConverters.stringToDateTime(year + "-04-11 00:00");
            em.createEvent("Good Friday", goodfridaystart, goodfridayfinish, "", tags);

            LocalDateTime easterstart = TimeStringConverters.stringToDateTime(year + "-04-12 00:00");
            LocalDateTime easterfinish = TimeStringConverters.stringToDateTime(year + "-04-13 00:00");
            em.createEvent("Easter Monday", easterstart, easterfinish, "", tags);

            LocalDateTime mdaystart = TimeStringConverters.stringToDateTime(year + "-05-10 00:00");
            LocalDateTime mdayfinish = TimeStringConverters.stringToDateTime(year + "-05-11 00:00");
            em.createEvent("Mother's Day", mdaystart, mdayfinish, "", tags);

            LocalDateTime vicdaystart = TimeStringConverters.stringToDateTime(year + "-05-18 00:00");
            LocalDateTime vicdayfinish = TimeStringConverters.stringToDateTime(year + "-05-19 00:00");
            em.createEvent("Victoria Day", vicdaystart, vicdayfinish, "", tags);

            LocalDateTime fdaystart = TimeStringConverters.stringToDateTime(year + "-06-21 00:00");
            LocalDateTime fdayfinish = TimeStringConverters.stringToDateTime(year + "-06-22 00:00");
            em.createEvent("Father's Day", fdaystart, fdayfinish, "", tags);

            LocalDateTime cdaystart = TimeStringConverters.stringToDateTime(year + "-07-01 00:00");
            LocalDateTime cdayfinish = TimeStringConverters.stringToDateTime(year + "-07-02 00:00");
            em.createEvent("Canada Day", cdaystart, cdayfinish, "", tags);

            LocalDateTime civdaystart = TimeStringConverters.stringToDateTime(year + "-08-03 00:00");
            LocalDateTime civdayfinish = TimeStringConverters.stringToDateTime(year + "-08-04 00:00");
            em.createEvent("Civic Day", civdaystart, civdayfinish, "", tags);

            LocalDateTime ldaystart = TimeStringConverters.stringToDateTime(year + "-09-07 00:00");
            LocalDateTime ldayfinish = TimeStringConverters.stringToDateTime(year + "-09-08 00:00");
            em.createEvent("Labour Day", ldaystart, ldayfinish, "", tags);

            LocalDateTime thxstart = TimeStringConverters.stringToDateTime(year + "-010-12 00:00");
            LocalDateTime thxfinish = TimeStringConverters.stringToDateTime(year + "-10-13 00:00");
            em.createEvent("Thanksgiving Day", thxstart, thxfinish, "", tags);

            LocalDateTime halstart = TimeStringConverters.stringToDateTime(year + "-10-31 00:00");
            LocalDateTime halfinish = TimeStringConverters.stringToDateTime(year + "-11-01 00:00");
            em.createEvent("Halloween", halstart, halfinish, "", tags);

            LocalDateTime rmrstart = TimeStringConverters.stringToDateTime(year + "-11-11 00:00");
            LocalDateTime rmrfinish = TimeStringConverters.stringToDateTime(year + "-11-12 00:00");
            em.createEvent("Remembrance Day", rmrstart, rmrfinish, "", tags);

            LocalDateTime evestart = TimeStringConverters.stringToDateTime(year + "-12-24 00:00");
            LocalDateTime evefinish = TimeStringConverters.stringToDateTime(year + "-12-25 00:00");
            em.createEvent("Christmas Eve", evestart, evefinish, "", tags);

            LocalDateTime xmasstart = TimeStringConverters.stringToDateTime(year + "-12-25 00:00");
            LocalDateTime xmasfinish = TimeStringConverters.stringToDateTime(year + "-12-26 00:00");
            em.createEvent("Christmas Day", xmasstart, xmasfinish, "", tags);

            LocalDateTime boxstart = TimeStringConverters.stringToDateTime(year + "-12-26 00:00");
            LocalDateTime boxfinish = TimeStringConverters.stringToDateTime(year + "-12-27 00:00");
            em.createEvent("Boxing Day", boxstart, boxfinish, "", tags);

            LocalDateTime nyestart = TimeStringConverters.stringToDateTime(year + "-12-31 00:00");
            LocalDateTime nyefinish = TimeStringConverters.stringToDateTime(year+1 + "-01-01 00:00");
            em.createEvent("New Year's Eve", nyestart, nyefinish, "", tags);


        }
        return em.eventList;
    }




}
