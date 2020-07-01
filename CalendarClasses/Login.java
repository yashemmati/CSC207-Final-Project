package CalendarClasses;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.springframework.security.crypto.bcrypt.BCrypt;

/***
 * This class handles the logging in process for the calendar
 *
 * used https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/
 * crypto/bcrypt/BCrypt.html
 */
public class Login {


    /***
     * This checks if the input username and password match an entry in the database
     * @param user the input username
     * @param pass the input password
     * @return a boolean representing whether or not these are valid credentials
     */
    public static Boolean logIn(String user, String pass) {


        String sql = "SELECT Username, Password, Salt FROM phase2";

        try (Connection conn = Connect.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {


            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                if (rs.getString("Username").equals(user) &&
                        rs.getString("Password").equals(BCrypt.hashpw(pass,
                                rs.getString("Salt")))) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}




