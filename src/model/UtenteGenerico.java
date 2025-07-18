//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package model;

public class UtenteGenerico {
    private String nome;
    private String cognome;
    private String codiceFiscale;

    public UtenteGenerico(String nome, String cognome, String codiceFiscale) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCognome() {
        return this.cognome;
    }

    public String getCodiceFiscale() {
        return this.codiceFiscale;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setcodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
}
