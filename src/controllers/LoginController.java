package controllers;

import models.*;
import views.*;

import javax.swing.*;
import java.awt.event.*;

public class LoginController {

    private final LoginView loginView;

    public LoginController(LoginView lv) {
        loginView = lv;
        loginView.setLoginButtonListener(new LoginButtonListener());
        loginView.setRegisterButtonListener(new RegisterButtonListener());
        loginView.setVisible(true);
    }

    // EVENT HANDLERS

    // authenticate user, build ContactsView, hide LoginView
    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DBManager data = new DBManager();
            if (data.loginUser(loginView.getUsername(), loginView.getPassword())) {
                loginView.setVisible(false);
                ContactView cv = new ContactView();
                new ContactController(cv, data.getCurrentUser());
            }
            else JOptionPane.showMessageDialog(null, "Incorrect username or password");
        }
    }

    // build RegisterView, hide LoginView
    private class RegisterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            RegisterView rv = new RegisterView();
            RegisterController rc = new RegisterController(rv);
            loginView.setVisible(false);
        }
    }
}
