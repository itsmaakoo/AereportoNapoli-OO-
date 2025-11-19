package model;

/**
 * Rappresenta un utente del sistema, identificato da credenziali di accesso.
 * La classe include metodi per accedere e modificare login e password,
 * oltre a una funzione placeholder per la visualizzazione dei voli.
 */
public class Utente {
    private String login;
    private String password;

    /**
     * Costruisce un nuovo utente con login e password specificati.
     *
     * @param login    il nome utente per l'accesso
     * @param password la password associata all'utente
     */
    Utente(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * Restituisce il login dell'utente.
     *
     * @return il nome utente
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Restituisce la password dell'utente.
     *
     * @return la password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Imposta un nuovo login per l'utente.
     *
     * @param login il nuovo nome utente
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Imposta una nuova password per l'utente.
     *
     * @param password la nuova password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Restituisce una rappresentazione testuale dell'utente.
     *
     * @return una stringa contenente login e password
     */
    @Override
    public String toString() {
        return "Login: " + this.login + "\nPassword: " + this.password;
    }
}
