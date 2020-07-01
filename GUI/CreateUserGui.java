package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import CalendarClasses.*;
import com.sun.net.httpserver.Authenticator;

/**
 * A GUI for creating a user
 */
public class CreateUserGui {
    private JPanel mainPanel;
    private JLabel lblUserName;
    private JTextField txtInUser;
    private JLabel lblPassword;
    private JPasswordField passIn;
    private JLabel lblConfirmPass;
    private JPasswordField passInConfirm;
    private JButton btnCreateUser;
    private JButton btnBack;

    private JFrame frame;

    /**
     * creates a new GUI by filling in the frame and assigning listeners to buttons
     */
    public CreateUserGui() {
        frame = new JFrame("Create User");
        btnCreateUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = txtInUser.getText();
                String pass = new String(passIn.getPassword());
                String confirmPass = new String(passInConfirm.getPassword());

                if (user.equals("") || pass.equals("") || confirmPass.equals("")) {
                    showMsgBox("Please fill out all forms!", "Error!");
                } else if (!pass.equals(confirmPass)) {
                    showMsgBox("Passwords do not match!", "Error!");
                    passIn.setText("");
                    passInConfirm.setText("");
                } else {
                    User newUser = new User(user, pass);
                    SignUp s = new SignUp(newUser);
                    showMsgBox("New User Created!", "Success!");
                    dispose();
                }
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void dispose() {
        LoginGui login = new LoginGui();
        login.show();
        frame.dispose();
    }

    /**
     * Makes this window visible.
     */
    public void show() {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(310, 200);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true); //displays the window
    }

    private void showMsgBox(String msg , String title) {
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
