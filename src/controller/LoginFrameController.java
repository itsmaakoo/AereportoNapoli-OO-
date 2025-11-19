package controller;

import gui.AmministratoreGUI;
import gui.LoginFrame;
import gui.UtenteGenericoPanel;

import javax.swing.*;


/**
 * Gestisce l'autenticazione per l'interfaccia di login.
 * Verifica le credenziali inserite e lo indirizza sull'interfaccia grafica corrispondente
 * in base al tipo di utente (amministratore o utente generico)
 * <p><b>Dipendenze</b></p>
 * <ul>
 * <li>{@link gui.LoginFrame}</li>
 * <li>{@link gui.AmministratoreGUI}</li>
 * <li>{@link gui.UtenteGenericoPanel}</li>
 * </ul>
 */
public class LoginFrameController {
    private final LoginFrame view;

    /**
     * CCrea un nuovo controller per la gestione di login
     *
     * @param view la vista {@link LoginFrame} associata al controller
     */
    public LoginFrameController(LoginFrame view) {
        this.view = view;
        initController();

    }

    /**
     * Inizializza il controller associando l'azione di login al pulsante.
     */

    private void initController() {
        view.getLoginButton().addActionListener(e -> effettusLogin());

    }

    /**
     * Esegue la logica di autenticazione in base alle credenziali inserite.
     *
     * <p>Se l'utente è "admin" con password "admin123", viene aperta l'interfaccia {@link AmministratoreGUI}</p>
     * <p>Se l'utente è "utente" con password "utente123", viene aperta l'interfaccia {@link UtenteGenericoPanel}</p>
     * <p>In caso di errore di credenziali, viene mostrato un messaggio di errore</p>
     */

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
