package controllers;

import views.*;
import models.*;

import javax.swing.*;
import java.awt.event.*;

public class RegisterController {

    private final RegisterView registerView;

    public RegisterController(RegisterView rv) {
        registerView = rv;
        registerView.setRegisterButtonListener(new RegisterButtonListener());
        registerView.setLoginButtonListener(new LoginButtonListener());
        registerView.setVisible(true);
    }

    // EVENT HANDLERS

    // register user account into database, build LoginView, hide RegisterView
    private class RegisterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User user = new User(registerView.getUsername(), registerView.getPassword());

            if (registerView.getPassword().equals(registerView.getConfirmPassword())) {
                DBManager data = new DBManager();
                if (data.addUser(user)) {
                    JOptionPane.showMessageDialog(null, "Registration Complete.");
                    LoginView lv = new LoginView();
                    LoginController lc = new LoginController(lv);
                    registerView.setVisible(false);
                }
                else JOptionPane.showMessageDialog(null, "Registration failed.");
            }
            else JOptionPane.showMessageDialog(null,"Passwords do not match, please try again.");
        }
    }

    // build LoginView, hide RegisterView
    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoginView lv = new LoginView();
            LoginController lc = new LoginController(lv);
            registerView.setVisible(false);
        }
    }

}
