package views;

import javax.swing.*;
import java.awt.event.ActionListener;

public class RegisterView extends JFrame {

    private JPanel registerPanel;
    private JTextField usernameField;
    private JPasswordField passwordField1, passwordField2;
    private JButton registerButton, loginButton;

    public RegisterView() {
        setTitle("Phonebook Management: Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(registerPanel);
        setSize(500, 400);
    }

    // event listener setters

    public void setRegisterButtonListener(ActionListener listener) {
        registerButton.addActionListener(listener);
    }

    public void setLoginButtonListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    // getters

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField1.getPassword());
    }

    public String getConfirmPassword() {
        return new String(passwordField2.getPassword());
    }

}
