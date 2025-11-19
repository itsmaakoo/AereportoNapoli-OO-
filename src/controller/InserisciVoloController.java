package controller;

import dao.VoloDAO;
import model.Volo;
import db.ConnessioneDB;
import java.sql.Connection;
import java.util.Date;
/**
 /**
 * Controller per l'inserimento di nuovi voli.
 * * <p><b>Dipendenze</b></p>
 * <ul>
 * <li>{@link dao.VoloDAO}</li>
 * <li>{@link db.ConnessioneDB}</li>
 * </ul>
 * * <p><b>Modelli</b></p>
 * <ul>
 * <li>{@link model.Volo}</li>
 * </ul>
 */
public class InserisciVoloController {
    private Connection conn;
    private VoloDAO voloDAO;
    /**
     * Crea un nuovo controller per l'inserimento dei voli.
     * Inizializza la connessione al database e la classe di {@link VoloDAO}
     */
    public InserisciVoloController() {
        conn = ConnessioneDB.getConnection();
        if(conn != null) {
            voloDAO = new VoloDAO(conn);
        }
    }
    /**
     * Salva un nuovo volo con i parametri dati
     *
     * @param destinazione destinazione del volo
     * @param partenza     la data e ora di partenza
     * @param arrivo      la data e ora d'arrivo
     * @return true se il volo è stato salvato correttamente altrimenti falso
     */
    public boolean salvaVolo(String destinazione, Date partenza, Date arrivo) {
        if(voloDAO == null) {
            System.err.println("VoloDAO non inizializzato");
            return false;
        }
        if(destinazione == null || destinazione.trim().isEmpty()) {
            System.err.println("Destinazione mancante");
            return false;
        }
        if(!arrivo.after(partenza) && !arrivo.equals(partenza)) {
            System.err.println("La data di arrivo non deve essere uguale o alla partenza");
            return false;
        }
        Volo nuovoVolo = new Volo(0, destinazione.trim(), partenza, arrivo, "programmato", "1", "partenza");

        boolean successo = voloDAO.salvaVolo(nuovoVolo);
        if(successo) {
            System.out.println("Volo salvato con ID: " + nuovoVolo.getId());
        } else {
            System.err.println("Salvataggio fallito.");
        }
        return successo;
    }
}

