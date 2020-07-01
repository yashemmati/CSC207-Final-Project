package CalendarClasses;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.lang.Class.*;


// Used http://alvinalexander.com/java/java-mysql-insert-example-preparedstatement/
// Used https://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/

public class Connect {

    public static Connection connect() {
        Connection conn = null;
        try {
            // db parameters

            String url = "jdbc:sqlite:csc207.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } return null;
    }

}
