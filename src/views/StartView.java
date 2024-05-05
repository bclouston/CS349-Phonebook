package views;

import javax.swing.*;
import java.awt.event.ActionListener;

public class StartView extends JFrame {

    private JButton loginButton, registerButton;
    private JPanel startPanel;

    public StartView() {
        setTitle("Phonebook Management: Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(startPanel);
        setSize(500, 250);
    }

    // event listener setters

    public void setLoginButtonListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void setRegisterButtonListener(ActionListener listener) {
        registerButton.addActionListener(listener);
    }

}
