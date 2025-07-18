
package model;

public class Utente {
    private String login;
    private String password;

    Utente(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        String stringa = "Login: " + this.login + "\nPassword: " + this.password;
        return stringa;
    }

    public void visualizza_voli() {
    }
}
