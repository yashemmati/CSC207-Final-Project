package CalendarClasses;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * handles conversions of dates to string and strings to date.
 */
public class TimeStringConverters {
    /**
     * Converts a string representing a date & time in format "YYYY-MM-DD HH:MM" to a LocalDateTime object
     *
     * @param strDateTime has format "YYYY-MM-DD HH:MM"
     * @return LocalDateTime corresponding to inputted string
     */
    public static LocalDateTime stringToDateTime(String strDateTime) {
        String[] split = strDateTime.split(" ");
        String[] dateSplit = split[0].split("-");
        String[] timeSplit = split[1].split(":");

        int[] dt = new int[5];

        for (int i = 0; i < 5; i++) {
            if (i < 3) {
                dt[i] = Integer.valueOf(dateSplit[i]);
            } else {
                dt[i] = Integer.valueOf(timeSplit[i - 3]);
            }
        }

        return LocalDateTime.of(dt[0], dt[1], dt[2], dt[3], dt[4], 0, 0);

    }

    /**
     * Converts a string representing a date in format "YYYY-MM-DD" to a LocalDateTime object
     *
     * @param strDate has format "YYYY-MM-DD"
     * @return LocalDateTime corresponding to inputted string
     */
    public static LocalDateTime stringToDate(String strDate) {
        String[] dateSplit = strDate.split("-");
        return LocalDateTime.of(Integer.valueOf(dateSplit[0]), Integer.valueOf(dateSplit[1]),
                Integer.valueOf(dateSplit[2]), 0, 0, 0, 0);
    }

    /**
     * Converts a LocalDateTime object to a string with format "YYYY-MM-DD HH:MM"
     *
     * @param date a LocalDateTime object
     * @return String with format "YYYY-MM-DD HH:MM"
     */
    public static String datetimeToString(LocalDateTime date) {
        String year = String.valueOf(date.getYear());
        String month = addZero(date.getMonthValue());
        String day = addZero(date.getDayOfMonth());
        String hour = addZero(date.getHour());
        String minute = addZero(date.getMinute());

        return year + "-" + month + "-" + day + " " + hour + ":" + minute;
    }

    /**
     * Returns a time interval between two LocalDateTime objects in the form HH:MM - HH:MM
     * @param start the 1st time in the interval
     * @param end the 2nd time in the interval
     * @return String of form HH:MM - HH:MM
     */
    public static String timeInterval(LocalDateTime start, LocalDateTime end) {
        String startHour = addZero(start.getHour());
        String startMin = addZero(start.getMinute());
        String endHour = addZero(end.getHour());
        String endMin = addZero(end.getMinute());

        return startHour + ":" + startMin + " - " + endHour + ":" + endMin;
    }

    /**
     * Adds a zero to a positive integer if its string has length less than 2.
     * @param toAdd the integer to add a zero too
     * @return String of in with min length 2
     */
    public static String addZero(int toAdd) {
        if (toAdd < 10) {
            return "0" + String.valueOf(toAdd);
        } else {
            return String.valueOf(toAdd);
        }
    }

    /**
     * checks if input date's format is "YYYY-MM-DD"
     * @param time
     * @return
     * @throws NumberFormatException
     * @throws DateTimeException
     */
    public static Boolean isValid(String time) throws NumberFormatException,DateTimeException {

        if (time.length() != 16) {
            return Boolean.FALSE;
        }
        try {
            stringToDateTime(time);
        } catch (NumberFormatException | DateTimeException e) {
            return Boolean.FALSE;}

        return Boolean.TRUE;
    }


}
