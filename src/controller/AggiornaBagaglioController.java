package controller;

import dao.BagaglioDAO;
import dao.VoloDAO;
import dao.PrenotazioneDAO;
import db.ConnessioneDB;
import model.Prenotazione;
import model.Volo;
import model.Bagaglio;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Fornisce l'elenco dei voli disponibili e i bagagli associati alle prenotazioni
 * in un determinato volo. Con l'utilizzo classe BagaglioDAO accede al database
 *
 * <p><b>Dipendenze</b></p>
 * <ul>
 * <li>{@link dao.VoloDAO}</li>
 * <li>{@link dao.PrenotazioneDAO}</li>
 * <li>{@link dao.BagaglioDAO}</li>
 * <li>{@link db.ConnessioneDB}</li>
 * </ul>
 *
 * <p><b>Modelii</b></p>
 * <ul>
 * <li>{@link model.Volo}</li>
 * <li>{@link model.Prenotazione}</li>
 * <li>{@link model.Bagaglio}</li>
 * </ul>
 *
 */
public class AggiornaBagaglioController {

    /**
     * Recupera tutti i voli presente nel database.
     *
     * @return lista di oggetti {@link Volo} rappresentati tutti i voli disponibili.
     * restituisce una lista vuota in caso di errore o errore nella connessione al database
     */
    public List<Volo> recuperaVoli(){
        try(Connection conn = ConnessioneDB.getConnection()){
            if(conn != null){
                VoloDAO voloDAO = new VoloDAO(conn);
                return voloDAO.getTuttiVoli();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Recupera tutti i bagagli associati alle prenotazioni di un determinato volo.
     *
     * @param idVolo l'id del volo per cui recuperare i bagagli
     * @return lista di oggetti {@link Bagaglio} associati alle prenotazioni del vole specificato in precedenza.
     * restituisce una lista vuota in caso di errore o di errore nella conessione al database
     */
    public List<Bagaglio> recuperaBagagliPerVolo(int idVolo){
        List<Bagaglio> bagagli = new ArrayList<>();
        try (Connection conn = ConnessioneDB.getConnection()){
            if(conn != null){
                PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO(conn);
                BagaglioDAO bagaglioDAO = new BagaglioDAO(conn);

                List<Prenotazione> prenotazioni = prenotazioneDAO.getPrenotazioniPerVolo(idVolo);
                for (Prenotazione prenotazione : prenotazioni) {
                    bagagli.addAll(bagaglioDAO.trovaBagagliPerPrenotazione(prenotazione.getPrenotazioneId()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bagagli;
}
}
