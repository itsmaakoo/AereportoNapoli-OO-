package model;

/**
 * Rappresenta un utente generico del sistema, identificato da nome, cognome e codice fiscale.
 * Questa classe viene utilizzata come base per utenti non autenticati o con ruoli generici.
 */
public class UtenteGenerico {
    private String nome;
    private String cognome;
    private String codiceFiscale;

    /**
     * Costruisce un nuovo utente generico con i dati personali specificati.
     *
     * @param nome          il nome dell'utente
     * @param cognome       il cognome dell'utente
     * @param codiceFiscale il codice fiscale dell'utente
     */
    public UtenteGenerico(String nome, String cognome, String codiceFiscale) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
    }

    /**
     * Restituisce il nome dell'utente.
     *
     * @return il nome
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce il cognome dell'utente.
     *
     * @return il cognome
     */
    public String getCognome() {
        return this.cognome;
    }

    /**
     * Restituisce il codice fiscale dell'utente.
     *
     * @return il codice fiscale
     */
    public String getCodiceFiscale() {
        return this.codiceFiscale;
    }

    /**
     * Imposta il nome dell'utente.
     *
     * @param nome il nuovo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Imposta il cognome dell'utente.
     *
     * @param cognome il nuovo cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Imposta il codice fiscale dell'utente.
     *
     * @param codiceFiscale il nuovo codice fiscale
     */
    public void setcodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
}
