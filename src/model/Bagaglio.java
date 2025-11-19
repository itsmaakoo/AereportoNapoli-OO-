package model;

/**
 * Rappresenta un bagaglio associato a una prenotazione.
 * Ogni bagaglio ha un identificativo di prenotazione, un codice univoco,
 * uno stato (es. registrato, imbarcato, smarrito) e una descrizione.
 */
public class Bagaglio {
    private int prenotazioneId;
    private int codice;
    private StatoBagaglio stato;
    private String descrizione;

    /**
     * Costruisce un nuovo oggetto Bagaglio con stato iniziale "registrato".
     *
     * @param prenotazioneId l'identificativo della prenotazione associata
     * @param codice          il codice univoco del bagaglio
     * @param descrizione     la descrizione del contenuto o caratteristiche del bagaglio
     */
    public Bagaglio(int prenotazioneId, int codice, String descrizione) {
        this.prenotazioneId = prenotazioneId;
        this.codice = codice;
        this.stato = StatoBagaglio.registrato;
        this.descrizione = descrizione;
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
     * Restituisce il codice del bagaglio.
     *
     * @return il codice
     */
    public int getCodice() {
        return codice;
    }

    /**
     * Imposta il codice del bagaglio.
     *
     * @param codice il nuovo codice
     */
    public void setCodice(int codice) {
        this.codice = codice;
    }

    /**
     * Restituisce la descrizione del bagaglio.
     *
     * @return la descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta la descrizione del bagaglio.
     *
     * @param descrizione la nuova descrizione
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Restituisce lo stato attuale del bagaglio.
     *
     * @return lo stato del bagaglio
     */
    public StatoBagaglio getStato() {
        return stato;
    }

    /**
     * Imposta lo stato del bagaglio.
     *
     * @param stato il nuovo stato
     */
    public void setStato(StatoBagaglio stato) {
        this.stato = stato;
    }

    /**
     * Restituisce una rappresentazione testuale del bagaglio.
     *
     * @return il codice come stringa
     */
    @Override
    public String toString() {
        return String.valueOf(codice); // oppure "Bagaglio " + codice se vuoi un prefisso
    }
}

