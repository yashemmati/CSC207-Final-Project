package CalendarClasses;


import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/***
 * This class reads and writes data into the database
 *
 * Used https://javapapers.com/core-java/serialize-de-serialize-java-object-from-database/
 */
public class UserData {

    /***
     * Saves a Calendar Manager object in the entry for the input username or creates one if it does not exist
     * @param cal the calendar manager object to be saved
     * @param username the username of the user who's calendar manager is being saved
     */
    public static void saveUserData(CalendarManager cal, String username) {
        String query = "UPDATE phase2 SET Calendars = ? WHERE Username == ? ";
        try (Connection conn = Connect.connect();
             PreparedStatement pstmt1 = conn.prepareStatement(query)) {
            pstmt1.setString(2, username);
            ByteArrayOutputStream byteArrOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteArrOut);
            out.writeObject(cal);
            out.close();
            pstmt1.setBytes(1, byteArrOut.toByteArray());
            pstmt1.executeUpdate();
            pstmt1.close();
            System.out.print("Calendar Has been saved!");
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }


    }

    /***
     * Reads a CalendarManager object from the database and returns it
     *
     * @param username username of the user who's calendar manager is being retrieved
     * @return returns a CalendarManager object
     */
    public static CalendarManager readUserData(String username) {

        String sql = "SELECT Calendars FROM phase2 WHERE Username == ?";
        CalendarManager cal = null;
        try (Connection conn = Connect.connect();
             PreparedStatement pstmt2 = conn.prepareStatement(sql)) {

            // set the value
            pstmt2.setString(1, username);
            //
            ResultSet rs = pstmt2.executeQuery();
            rs.next();
            byte[] calBytes = rs.getBytes(1);
            if (calBytes != null) {
                ObjectInputStream objectIn = new ObjectInputStream(new ByteArrayInputStream(calBytes));
                return (CalendarManager) objectIn.readObject();
            }
            rs.close();
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return cal;
    }

    /***
     * Saves one calendar to the database. This method will check if the input calendar already exists in this user's
     * Calendar Manager, and adds it if it does not exist or replaces the existing one if it does
     * @param cal a new or edited calendar to be saved
     * @param u user whose calendar this is
     */
    public static void saveCalendar(Calendar cal, User u) {
        CalendarManager currentCal = readUserData(u.getUserName());
        for (Calendar c : currentCal.getListOfCalendars()) {
            if (c.getName().equals(cal.getName())) {
                currentCal.getListOfCalendars().set(currentCal.getListOfCalendars().indexOf(c), cal);
            } else {
                currentCal.getListOfCalendars().add(cal);
            }
        }
        saveUserData(currentCal, u.getUserName());
    }

    /***
     * Deletes a calendar from the database.
     * @param cal the calendar that is to deleted
     * @param u user whose calendar this is
     */
    public static void removeCalendar(Calendar cal, User u) {
        CalendarManager currentCal = readUserData(u.getUserName());
        currentCal.getListOfCalendars().removeIf(c -> c.getName().equals(cal.getName()));
        saveUserData(currentCal, u.getUserName());
    }
}
