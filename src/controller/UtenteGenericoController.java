package controller;

import dao.PrenotazioneDAO;
import db.ConnessioneDB;
import gui.*;
import model.Prenotazione;


import java.sql.Connection;
import java.util.List;

/**
 * Gestisce le funzionalità disponibili per un utente generico
 * Consente di interagire tra le interfacce grafiche
 *
 * <p><b>Dipendenze</b></p>
 * <ul>
 * <li>{@link dao.PrenotazioneDAO}</li>
 * <li>{@link db.ConnessioneDB}</li>
 * </ul>
 *
 * <p><b>GUI</b></p>
 * <ul>
 * <li>{@link gui.PrenotaVolo}</li>
 * <li>{@link gui.StatoVolo}</li>
 * <li>{@link gui.AggiungiBagaglio}</li>
 * <li>{@link gui.BagaglioSmarrito}</li>
 * <li>{@link gui.LoginFrame}</li>
 * <li>{@link model.Prenotazione}</li>
 * </ul>
 *
 * <p><b>Modelli</b></p>
 */
public class UtenteGenericoController {

    private final PrenotazioneDAO prenotazioneDAO;

    public UtenteGenericoController() {
        Connection conn = ConnessioneDB.getConnection();
        prenotazioneDAO = new PrenotazioneDAO(conn);
    }
    /**
     * Apre l'interfaccia grafica per la prenotazione di un volo
     */
    public void apriPrenotaVolo() {
        new PrenotaVolo();
    }

    /**
     * Apre l'interfaccia grafica per la visualizzazione dello stato dei voli
     */
    public void apriStatoVolo() {
        new StatoVolo();
    }

    /**
     * Apre l'interfaccia grafica per l'aggiunta di un bagaglio a una prenotazione
     */
    public void apriAggiungiBagaglio() {
        new AggiungiBagaglio();
    }

    /**
     * Apre l'interfaccia grafica per la segnalazione di un bagaglio smarrito
     */
    public void apriSegnalaBagaglioSmarrito() {
        new BagaglioSmarrito();
    }

    /**
     * Esegue il logout dell'utente
     */
    public void logout() {
        new LoginFrame();
    }

    /**
     * Recupera tutte le prenotazioni dell'utente.
     *
     * @return lista di prenotazioni {@link Prenotazione} oppure la lista vuota in caso di errore
     */
    public List<Prenotazione> recuperaPrenotazioniUtente() {
        try {
            return prenotazioneDAO.getTuttePrenotazioni();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}


