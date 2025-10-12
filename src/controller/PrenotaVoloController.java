package controller;

import dao.PasseggeroDAO;
import db.ConnessioneDB;
import model.Passeggero;
import model.Prenotazione;
import model.Volo;
import dao.VoloDAO;
import dao.PrenotazioneDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class PrenotaVoloController {
    private Connection conn;
    private VoloDAO voloDAO;
    private PasseggeroDAO passeggeroDAO;
    private PrenotazioneDAO prenotazioneDAO;

    public PrenotaVoloController() {
        conn = ConnessioneDB.getConnection();
        if(conn != null){
            voloDAO = new VoloDAO(conn);
            passeggeroDAO = new PasseggeroDAO(conn);
            prenotazioneDAO = new PrenotazioneDAO(conn);
        } else {
            System.err.println("Connessione al database fallita.");
        }
    }
    public List<Volo> getVoliPrenotabili() {
        if(voloDAO != null) {
            return voloDAO.getVoliPrenotabili();
        }
        return List.of();
    }
    public boolean prenotaVolo(String nome, String cognome, String codiceFiscale, Volo voloSelezionato) throws SQLException {
        if(voloSelezionato == null || nome == null || cognome == null || codiceFiscale == null ||
                nome.trim().isEmpty() || cognome.trim().isEmpty() || codiceFiscale.trim().isEmpty()){
            System.err.println("Errore, dati mancanti o non validi.");
            return false;
        }
        Passeggero passeggero = new Passeggero(nome.trim(), cognome.trim(), codiceFiscale.trim());
        int passeggeroId = passeggeroDAO.salvaPasseggero(passeggero);

        if(passeggeroId == 0) {
            System.err.println("Errore nel salvataggio del passeggero.");
            return false;
        }
        Random rand = new Random();
        int num_biglietto = 1000 + rand.nextInt(999999);
        int prenotazioneId = 1 + rand.nextInt(10000);
        String stato = "Prenotazione confermata";

        Prenotazione prenotazione = new Prenotazione(
                prenotazioneId,
                num_biglietto,
                codiceFiscale.trim(),
                passeggeroId,
                voloSelezionato.getId(),
                stato
        );
//sempre sto coso qua
        prenotazioneDAO.salvaPrenotazione(prenotazione);
        return true;
    }
}
