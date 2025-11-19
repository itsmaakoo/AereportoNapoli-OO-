package model;

/**
 * Rappresenta un passeggero associato a un volo.
 * Contiene informazioni personali come nome, cognome, codice fiscale,
 * identificativo del passeggero e del volo.
 */
public class Passeggero {
    private int id;
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private int idVolo;

    /**
     * Costruisce un nuovo passeggero con nome, cognome e codice fiscale.
     *
     * @param nome          il nome del passeggero
     * @param cognome       il cognome del passeggero
     * @param codiceFiscale il codice fiscale del passeggero
     */
    public Passeggero(String nome, String cognome, String codiceFiscale) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
    }

    /**
     * Costruisce un nuovo passeggero con id, nome, cognome e codice fiscale.
     *
     * @param id            l'identificativo del passeggero
     * @param nome          il nome del passeggero
     * @param cognome       il cognome del passeggero
     * @param codiceFiscale il codice fiscale del passeggero
     */
    public Passeggero(int id, String nome, String cognome, String codiceFiscale) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
    }

    /**
     * Costruisce un passeggero vuoto.
     */
    public Passeggero() {
    }

    /**
     * Restituisce l'identificativo del volo associato.
     *
     * @return l'id del volo
     */
    public int getIdVolo() {
        return idVolo;
    }

    /**
     * Imposta l'identificativo del volo associato.
     *
     * @param idVolo il nuovo id del volo
     */
    public void setIdVolo(int idVolo) {
        this.idVolo = idVolo;
    }

    /**
     * Restituisce il nome del passeggero.
     *
     * @return il nome
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Imposta il nome del passeggero.
     *
     * @param nome il nuovo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il cognome del passeggero.
     *
     * @return il cognome
     */
    public String getCognome() {
        return this.cognome;
    }

    /**
     * Imposta il cognome del passeggero.
     *
     * @param cognome il nuovo cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Restituisce il codice fiscale del passeggero.
     *
     * @return il codice fiscale
     */
    public String getCodiceFiscale() {
        return this.codiceFiscale;
    }

    /**
     * Imposta il codice fiscale del passeggero.
     *
     * @param codiceFiscale il nuovo codice fiscale
     */
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    /**
     * Restituisce l'identificativo del passeggero.
     *
     * @return l'id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Imposta l'identificativo del passeggero.
     *
     * @param id il nuovo id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Restituisce una rappresentazione testuale del passeggero.
     *
     * @return una stringa contenente nome, cognome e codice fiscale
     */
    @Override
    public String toString() {
        return this.nome + " " + this.cognome + " " + this.codiceFiscale;
    }
}



