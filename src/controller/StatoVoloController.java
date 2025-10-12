package controller;

import dao.VoloDAO;
import db.ConnessioneDB;
import model.Volo;
import java.sql.Connection;
import java.util.Collections;
import java.util.List;

public class StatoVoloController {
    private VoloDAO voloDAO;

    public StatoVoloController() {
        try {
            Connection conn = ConnessioneDB.getConnection();
            if(conn != null){
                voloDAO = new VoloDAO(conn);
            } else {
                System.err.println("Connessione al database fallita");
            }
        } catch (Exception e) {
            System.err.println("Errore durante l'inizializzazione del controller");
            e.printStackTrace();
        }
    }
    public List<Volo> recuperaTuttiIVoli() {
        if(voloDAO != null){
            return voloDAO.getTuttiVoli();

        }
        return Collections.emptyList();
    }
}