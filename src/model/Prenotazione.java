package model;

/**
 * Rappresenta una prenotazione di volo effettuata da un passeggero.
 * Contiene informazioni relative al biglietto, al passeggero, al volo,
 * allo stato della prenotazione e al posto assegnato.
 */
public class Prenotazione {
    private int num_biglietto;
    private int passeggeroId;
    private String codiceFiscale;
    private int voloId;
    private int prenotazioneId;
    private String stato_prenotazione;
    private String posto_assegnato;

    /**
     * Costruisce una nuova prenotazione con tutti i dettagli specificati.
     *
     * @param num_biglietto      il numero del biglietto
     * @param passeggeroId       l'identificativo del passeggero
     * @param codiceFiscale      il codice fiscale del passeggero
     * @param voloId             l'identificativo del volo
     * @param prenotazioneId     l'identificativo della prenotazione
     * @param stato_prenotazione lo stato attuale della prenotazione (es. confermata, in attesa, annullata)
     * @param posto_assegnato    il posto assegnato al passeggero
     */
    public Prenotazione(int num_biglietto, int passeggeroId, String codiceFiscale, int voloId, int prenotazioneId, String stato_prenotazione, String posto_assegnato) {
        this.num_biglietto = num_biglietto;
        this.passeggeroId = passeggeroId;
        this.codiceFiscale = codiceFiscale;
        this.voloId = voloId;
        this.prenotazioneId = prenotazioneId;
        this.stato_prenotazione = stato_prenotazione;
        this.posto_assegnato = posto_assegnato;
    }

    /**
     * Restituisce il numero del biglietto.
     *
     * @return il numero del biglietto
     */
    public int getNum_biglietto() {
        return num_biglietto;
    }

    /**
     * Imposta il numero del biglietto.
     *
     * @param num_biglietto il nuovo numero del biglietto
     */
    public void setNum_biglietto(int num_biglietto) {
        this.num_biglietto = num_biglietto;
    }

    /**
     * Restituisce l'identificativo del passeggero.
     *
     * @return l'id del passeggero
     */
    public int getPasseggeroId() {
        return passeggeroId;
    }

    /**
     * Imposta l'identificativo del passeggero.
     *
     * @param passeggeroId il nuovo id del passeggero
     */
    public void setPasseggeroId(int passeggeroId) {
        this.passeggeroId = passeggeroId;
    }

    /**
     * Restituisce il codice fiscale del passeggero.
     *
     * @return il codice fiscale
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
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
     * Restituisce l'identificativo del volo.
     *
     * @return l'id del volo
     */
    public int getVoloId() {
        return voloId;
    }

    /**
     * Imposta l'identificativo del volo.
     *
     * @param voloId il nuovo id del volo
     */
    public void setVoloId(int voloId) {
        this.voloId = voloId;
    }

    /**
     * Restituisce l'identificativo della prenotazione.
     *
     * @return l'id della prenotazione
     */
    public int getPrenotazioneId() {
        return prenotazioneId;
    }

    /**
     * Imposta l'identificativo della prenotazione.
     *
     * @param prenotazioneId il nuovo id della prenotazione
     */
    public void setPrenotazioneId(int prenotazioneId) {
        this.prenotazioneId = prenotazioneId;
    }

    /**
     * Restituisce lo stato della prenotazione.
     *
     * @return lo stato della prenotazione
     */
    public String getStato_prenotazione() {
        return stato_prenotazione;
    }

    /**
     * Imposta lo stato della prenotazione.
     *
     * @param stato_prenotazione il nuovo stato
     */
    public void setStato_prenotazione(String stato_prenotazione) {
        this.stato_prenotazione = stato_prenotazione;
    }

    /**
     * Restituisce il posto assegnato al passeggero.
     *
     * @return il posto assegnato
     */
    public String getPosto_assegnato() {
        return posto_assegnato;
    }

    /**
     * Imposta il posto assegnato al passeggero.
     *
     * @param posto_assegnato il nuovo posto assegnato
     */
    public void setPosto_assegnato(String posto_assegnato) {
        this.posto_assegnato = posto_assegnato;
    }

    /**
     * Restituisce una rappresentazione testuale della prenotazione.
     *
     * @return una stringa con numero biglietto, posto e stato
     */
    @Override
    public String toString() {
        return "Biglietto: " + num_biglietto +
                " | Posto: " + posto_assegnato +
                " | Stato: " + stato_prenotazione;
    }
}
