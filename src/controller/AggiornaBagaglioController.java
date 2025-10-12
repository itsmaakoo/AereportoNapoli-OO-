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

public class AggiornaBagaglioController {

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

    public List<Bagaglio> recuperaBagagliPerVolo(int idVolo){
        List<Bagaglio> bagagli = new ArrayList<>();
        try (Connection conn = ConnessioneDB.getConnection()){
            if(conn != null){
                PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO(conn);
                BagaglioDAO bagaglioDAO = new BagaglioDAO(conn);

                List<Prenotazione> prenotazioni = prenotazioneDAO.trovaPrenotazioniPerVolo(idVolo);
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
 // ma li abbiamo tutti, bisogna specificare quale vuole
// presuppongo voglia getId prenotazione qua?