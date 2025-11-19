package controller;

import dao.VoloDAO;
import db.ConnessioneDB;
import model.Volo;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller per la gestione dell'assegnazione dei gate.
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

public class AssegnaGateController {
    private Connection conn;
    private VoloDAO voloDAO;
    /**
     * Genera un controller per l'assegnazione del gate.
     * Si connette al database e alla calsse {@link VoloDAO}
     */
    public AssegnaGateController() {
        conn = ConnessioneDB.getConnection();
        if (conn != null) {
            voloDAO = new VoloDAO(conn);
        }
    }
    /**
     * Recupera tutti i voli presenti nel database
     *
     * @return la lista di oggetti {@link Volo} che rappresentano i voli disponibili.
     * Nel caso di errore o se laconnessione al database fallisce restituisce una lista vuota
     */
    public List<Volo> getTuttiVoli() {
        if (voloDAO != null) {
            return voloDAO.getTuttiVoli();
        }
        return new ArrayList<>();
    }
    /**
     * Assegna gate specifico a ogni volo idendificato con un ID
     *
     * @param idVolo l'id del volo a cui assegnare il gate
     * @param gate   il numero del gate
     * @return true se l'assegnazione è riuscita altrimenti false.
     */
    public boolean assegnaGate(int idVolo, String gate) {
        if (voloDAO != null && gate != null && !gate.isEmpty()) {
            voloDAO.aggiornaGate(idVolo, gate);
            return true;
        }
        return false;
}
}

