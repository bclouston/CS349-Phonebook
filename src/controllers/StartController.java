package controllers;

import views.*;

import java.awt.event.*;

public class StartController {

    private final StartView startView;

    public StartController(StartView sv) {
        startView = sv;
        startView.setLoginButtonListener(new LoginButtonListener());
        startView.setRegisterButtonListener(new RegisterButtonListener());
        startView.setVisible(true);
    }

    // EVENT HANDLERS

    // build RegisterView, hide StartView
    private class RegisterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            RegisterView rv = new RegisterView();
            RegisterController rc = new RegisterController(rv);
            startView.setVisible(false);
        }
    }

    // build LoginView, hide StartView
    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoginView lv = new LoginView();
            LoginController lc = new LoginController(lv);
            startView.setVisible(false);
        }
    }

}
