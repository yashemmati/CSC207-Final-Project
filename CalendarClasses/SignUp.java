package CalendarClasses;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;

import GUI.CreateCalendar;
import org.springframework.security.crypto.bcrypt.BCrypt;



/***
 * This class handles registering a new user
 *
 * used https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/
 * crypto/bcrypt/BCrypt.html
 */
public class SignUp {
    /***
     * Creates a new user object
     * @param user user object
     */
    public SignUp(User user) {
        Add(user);
    }

    /***
     * This method writes a new user's data into an sqlite databse
     * @param c user to be registered
     */
    public void Add(User c) {
        String user = c.getUserName();
        String pass = c.getPassword();
        String salt = BCrypt.gensalt(13);
        String hash = BCrypt.hashpw(pass, salt);
        try {
            String query = "INSERT INTO phase2(Calendars, Username, Password, Salt) VALUES(?,?,?, ?)";
            CalendarManager newCal = new CalendarManager();
            Calendar cal = new Calendar("Default Calendar");
            DefaultCalendar dc = new DefaultCalendar(CreateCalendar.yearsList());
            cal.addEvents(dc.addHolidays());
            newCal.addCalendar(cal);
            try (Connection conn = Connect.connect();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                ByteArrayOutputStream byteArrOut = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(byteArrOut);
                out.writeObject(newCal); //Serialization of CalendarManager Object
                out.close();
                pstmt.setBytes(1, byteArrOut.toByteArray());
                pstmt.setString(2, user);
                pstmt.setString(3, hash);
                pstmt.setString(4, salt);
                pstmt.executeUpdate();
                System.out.print("A new account has been created");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}



