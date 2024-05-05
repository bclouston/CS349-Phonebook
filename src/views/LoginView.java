package views;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private JPanel loginPanel;
    private JTextField usernameField;
    private JPasswordField passwordField1;
    private JButton loginButton, registerButton;

    public LoginView() {
        setTitle("Phonebook Management: Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(loginPanel);
        setSize(550, 300);
    }

    // event listener setters

    public void setLoginButtonListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void setRegisterButtonListener(ActionListener listener) {
        registerButton.addActionListener(listener);
    }

    // getters

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField1.getText();
    }

}

