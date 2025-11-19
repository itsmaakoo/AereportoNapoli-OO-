package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Rappresenta un volo aereo con informazioni su destinazione, orari, stato, gate, tipo,
 * passeggeri e bagagli associati. Include anche un archivio statico di tutti i voli.
 */
public class Volo {
    private int id;
    private String destinazione;
    private Date data_partenza;
    private Date data_arrivo;
    private String stato;
    private String gate;
    private String tipo;
    private List<Bagaglio> bagagli = new ArrayList<>();
    private List<Passeggero> passggeri = new ArrayList<>();

    /**
     * Archivio statico contenente tutti i voli registrati.
     */
    public static List<Volo> archivio = new ArrayList<>();

    /**
     * Costruisce un nuovo oggetto Volo con i dati specificati.
     *
     * @param id           identificativo del volo
     * @param destinazione destinazione del volo
     * @param dataPartenza data e ora di partenza
     * @param dataArrivo   data e ora di arrivo
     * @param stato        stato attuale del volo (es. in orario, in ritardo)
     * @param gate         gate di partenza
     * @param tipo         tipo di volo (es. nazionale, internazionale)
     */
    public Volo(int id, String destinazione, Date dataPartenza, Date dataArrivo, String stato, String gate, String tipo) {
        this.destinazione = destinazione;
        this.data_partenza = dataPartenza;
        this.data_arrivo = dataArrivo;
        this.stato = stato;
        this.gate = gate;
        this.id = id;
        this.tipo = tipo;
    }

    /**
     * Restituisce il tipo di volo.
     *
     * @return il tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Imposta il tipo di volo.
     *
     * @param tipo il nuovo tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Aggiunge un bagaglio alla lista dei bagagli del volo.
     *
     * @param b il bagaglio da aggiungere
     */
    public void aggiungiBagaglio(Bagaglio b) {
        this.bagagli.add(b);
    }

    /**
     * Restituisce la lista dei bagagli associati al volo.
     *
     * @return lista di bagagli
     */
    public List<Bagaglio> getBagagli() {
        return this.bagagli;
    }

    /**
     * Aggiunge un passeggero alla lista dei passeggeri del volo.
     *
     * @param p il passeggero da aggiungere
     */
    public void aggiungiPasseggero(Passeggero p) {
        this.passggeri.add(p);
    }

    /**
     * Restituisce la lista dei passeggeri del volo.
     *
     * @return lista di passeggeri
     */
    public List<Passeggero> getPassggeri() {
        return this.passggeri;
    }

    /**
     * Restituisce l'identificativo del volo.
     *
     * @return l'id del volo
     */
    public int getId() {
        return this.id;
    }

    /**
     * Imposta l'identificativo del volo.
     *
     * @param id il nuovo id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Restituisce la destinazione del volo.
     *
     * @return la destinazione
     */
    public String getDestinazione() {
        return this.destinazione;
    }

    /**
     * Imposta la destinazione del volo.
     *
     * @param destinazione la nuova destinazione
     */
    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    /**
     * Restituisce la data e ora di partenza.
     *
     * @return la data di partenza
     */
    public Date getDataPartenza() {
        return this.data_partenza;
    }

    /**
     * Imposta la data e ora di partenza.
     *
     * @param dataPartenza la nuova data di partenza
     */
    public void setDataPartenza(Date dataPartenza) {
        this.data_partenza = dataPartenza;
    }

    /**
     * Restituisce la data e ora di arrivo.
     *
     * @return la data di arrivo
     */
    public Date getDataArrivo() {
        return this.data_arrivo;
    }

    /**
     * Imposta la data e ora di arrivo.
     *
     * @param dataArrivo la nuova data di arrivo
     */
    public void setDataArrivo(Date dataArrivo) {
        this.data_arrivo = dataArrivo;
    }

    /**
     * Restituisce lo stato attuale del volo.
     *
     * @return lo stato del volo
     */
    public String getStato() {
        return this.stato;
    }

    /**
     * Imposta lo stato del volo.
     *
     * @param stato il nuovo stato
     */
    public void setStato(String stato) {
        this.stato = stato;
    }

    /**
     * Restituisce il gate di partenza.
     *
     * @return il gate
     */
    public String getGate() {
        return this.gate;
    }

    /**
     * Imposta il gate di partenza.
     *
     * @param gate il nuovo gate
     */
    public void setGate(String gate) {
        this.gate = gate;
    }

    /**
     * Restituisce una rappresentazione testuale del volo.
     *
     * @return stringa con destinazione, orari e stato
     */
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        String partenzaStr = (data_partenza != null) ? sdf.format(data_partenza) : "Data partenza non disponibile";
        String arrivoStr = (data_arrivo != null) ? sdf.format(data_arrivo) : "Data arrivo non disponibile";

        return "Volo: " + destinazione + " | Partenza: " + partenzaStr + " | Arrivo: " + arrivoStr + " | Stato: " + stato;
    }
}

