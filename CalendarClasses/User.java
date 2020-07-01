package CalendarClasses;
import java.io.*;


/**
 * User Class
 * Will log in a user, save and get their event data
 * i.e its single purpose is to interact with User data (passwords, events etc)
 * user data is saved to a txt in the following format:
 *      userName\n
 *      password\n
 *      event\n   \\ all user events are saved 1 per line
 */
public class User implements Serializable {
    private static final long serialVersionUID = 2L;
    protected String userName;
    private Boolean isLogged;
    protected String pw;

    /**
     * temp implimentation, will be able to create a user later, only have a pre existing one rn.
     * @param userName the username to login to
     * @param password the password to verify the user
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.pw = password;
        this.isLogged = Login.logIn(userName, password);
    }

    public void setIsLogged(boolean t) {
        this.isLogged = t;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return pw;
    }

    public Boolean getIsLogged() {
        return isLogged;
    }

    public static Boolean isEquals(User one, User two){
        return one.userName.equals(two.userName) && one.pw.equals(two.pw);
    }


    // Used https://www.tutorialspoint.com/java/java_serialization.htm for serializing and deserializing in method
    // saveUserData and readUserData

    //leave uncommented for testing
//    public  Calendar readUserData() {
//        Calendar cal = null;
//        String user;
//        String pw;
//        try {
//            FileInputStream fileIn = new FileInputStream("User_data.txt");
//            ObjectInputStream in = new ObjectInputStream(fileIn);
//            user = (String) in.readObject();
//            pw = (String) in.readObject();
//            User u = new User(user,pw);
//            cal = (Calendar) in.readObject();
//            in.close();
//            fileIn.close();
//        } catch (IOException i) {
//            i.printStackTrace();
//
//        } catch (ClassNotFoundException c) {
//            System.out.println("Calendar class not found");
//            c.printStackTrace();
//
//        }
//        return cal;
//    }


}
