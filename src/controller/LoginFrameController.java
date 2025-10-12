package controller;

import gui.AmministratoreGUI;
import gui.LoginFrame;
import gui.UtenteGenericoPanel;

import javax.swing.*;


public class LoginFrameController {
    private final LoginFrame view;

    public LoginFrameController(LoginFrame view) {
        this.view = view;
        initController();

    }

    private void initController() {
        view.getLoginButton().addActionListener(e -> effettusLogin());

    }

    private void effettusLogin() {
        String username = view.getUsernameField().getText();
        String password = String.valueOf(view.getPasswordField());

        if (username.equals("admin") && password.equals("admin123")) {
            new AmministratoreGUI();
            view.dispose();
        } else if (username.equals("utente") && password.equals("utente123")) {
            new UtenteGenericoPanel();
            view.dispose();
        }else{
            JOptionPane.showMessageDialog(view, "Username o password errato");
        }
    }
}