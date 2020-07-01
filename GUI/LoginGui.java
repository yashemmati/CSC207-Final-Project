package GUI;

import CalendarClasses.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGui implements Showable {
    private JPanel mainPanel;
    private JTextField txtInUser;
    private JPasswordField passIn;
    private JLabel lblPassword;
    private JLabel lblUserName;
    private JButton btnLogin;
    private JButton btnCreateUser;

    private JFrame frame = new JFrame("Login");

    /**
     * Creates our loginGUI, initializing fields and adding listeners to buttons.
     */
    public LoginGui() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = txtInUser.getText();
                String password = new String(passIn.getPassword());
                User user = new User(userName, password);

                if (user.getIsLogged()) {
                    CalendarMenu cm = new CalendarMenu(UserData.readUserData(user.getUserName()), user);
                    cm.show();
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect username or password!",
                            "Invalid login", JOptionPane.INFORMATION_MESSAGE);
                    txtInUser.setText("");
                    passIn.setText("");
                }
            }
        });

        btnCreateUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateUserGui createUser = new CreateUserGui();
                createUser.show();
                frame.dispose();
            }
        });
    }

    /**
     * Makes the window visible
     */
    public void show() {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(310, 160);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
