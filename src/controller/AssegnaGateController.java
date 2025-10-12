package controller;

import dao.VoloDAO;
import db.ConnessioneDB;
import model.Volo;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class AssegnaGateController {
    private Connection conn;
    private VoloDAO voloDAO;

    public AssegnaGateController() {
        conn = ConnessioneDB.getConnection();
        if (conn != null) {
            voloDAO = new VoloDAO(conn);
        }
    }

    public List<Volo> getTuttiVoli() {
        if (voloDAO != null) {
            return voloDAO.getTuttiVoli();
        }
        return new ArrayList<>();
    }

    public boolean assegnaGate(int idVolo, String gate) {
        if (voloDAO != null && gate != null && !gate.isEmpty()) {
            voloDAO.aggiornaGate(idVolo, gate);
            return true;
        }
        return false;
    }
}
