package model;

/**
 * Rappresenta un amministratore del sistema con credenziali di accesso.
 * Contiene informazioni di login e password, e metodi per accedervi e modificarli.
 */
public class Amministratore {
    private String login;
    private String password;

    /**
     * Costruisce un nuovo oggetto Amministratore con login e password specificati.
     *
     * @param login    il nome utente dell'amministratore
     * @param password la password dell'amministratore
     */
    public Amministratore(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * Restituisce il login dell'amministratore.
     *
     * @return il nome utente
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Restituisce la password dell'amministratore.
     *
     * @return la password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Imposta un nuovo login per l'amministratore.
     *
     * @param login il nuovo nome utente
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Imposta una nuova password per l'amministratore.
     *
     * @param password la nuova password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
