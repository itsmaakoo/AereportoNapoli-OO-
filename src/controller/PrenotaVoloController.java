package controller;

import dao.PasseggeroDAO;
import dao.PrenotazioneDAO;
import dao.VoloDAO;
import db.ConnessioneDB;
import model.Passeggero;
import model.Prenotazione;
import model.Volo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

/**
 * Gestisce la prenotazione dei voli da parte dell'utente gemerico (passeggero)
 * Consente di visualizzare un volo e prenotarlo
 *
 * <p><b>Dipendenze</b></p>
 * <ul>
 * <li>{@link dao.VoloDAO}</li>
 * <li>{@link dao.PasseggeroDAO}</li>
 * <li>{@link dao.PrenotazioneDAO}</li>
 * <li>{@link db.ConnessioneDB}</li>
 * </ul>
 *
 * <p><b>Modelli</b></p>
 * <ul>
 * <li>{@link model.Volo}</li>
 * <li>{@link model.Passeggero}</li>
 * <li>{@link model.Prenotazione}</li>
 * </ul>
 */
public class PrenotaVoloController {
    private Connection conn;
    private VoloDAO voloDAO;
    private PasseggeroDAO passeggeroDAO;
    private PrenotazioneDAO prenotazioneDAO;

    /**
     * Crea un nuovo controller per la prenotazione dei voli.
     * Stabilisce la connessione al database e le DAO necessarie
     */
    public PrenotaVoloController() {
        conn = ConnessioneDB.getConnection();
        if (conn != null) {
            voloDAO = new VoloDAO(conn);
            passeggeroDAO = new PasseggeroDAO(conn);
            prenotazioneDAO = new PrenotazioneDAO(conn);
        } else {
            System.err.println("Connessione al database fallita.");
        }
    }

    /**
     * Recupera l'elenco dei voli disponibili per la prenotazione
     *
     * @return lista di voli {@link Volo} prenotabili, oppure una lista vuota in caso di errore
     */
    public List<Volo> getVoliPrenotabili() {
        if (voloDAO != null) {
            return voloDAO.getVoliPrenotabili();
        }
        return List.of();
    }

    /**
     * Esegue la prenotazione di un volo per un passeggero co i dati forniti
     *
     * @param nome            nome del passeggero
     * @param cognome         cognome del passeggero
     * @param codiceFiscale   codice fiscale del passeggero
     * @param voloSelezionato il volo selezionato da prenotare
     * @return true se la prenotazione è stata registrata correttamente altrimenti false
     * @throws SQLException se si verifica un errore durante l'interazione con il database
     */
    public boolean prenotaVolo(String nome, String cognome, String codiceFiscale, Volo voloSelezionato) throws SQLException {
        if (voloSelezionato == null || nome == null || cognome == null || codiceFiscale == null ||
                nome.trim().isEmpty() || cognome.trim().isEmpty() || codiceFiscale.trim().isEmpty()) {
            System.err.println("Errore: dati mancanti o non validi.");
            return false;
        }

        Passeggero passeggero = new Passeggero(nome.trim(), cognome.trim(), codiceFiscale.trim());
        passeggero.setIdVolo(voloSelezionato.getId());

        int passeggeroId = passeggeroDAO.salvaPasseggero(passeggero);
        if (passeggeroId == 0) {
            System.err.println("Errore nel salvataggio del passeggero.");
            return false;
        }

        Random rand = new Random();
        int num_biglietto = 1000 + rand.nextInt(999999);
        int prenotazioneId = 1 + rand.nextInt(10000);
        String stato = "Prenotazione confermata";
        char lettera = (char) ('A' + rand.nextInt(6));
        int numero = 1 + rand.nextInt(30);
        String posto = "" + lettera + numero;

        Prenotazione prenotazione = new Prenotazione(
                num_biglietto,
                passeggeroId,
                codiceFiscale.trim(),
                voloSelezionato.getId(),
                prenotazioneId,
                stato,
                posto
        );
        prenotazioneDAO.salvaPrenotazione(prenotazione);
        return true;
    }
}
