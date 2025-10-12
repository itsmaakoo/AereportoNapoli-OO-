package controller;

import dao.VoloDAO;
import model.Volo;
import db.ConnessioneDB;
import java.sql.Connection;
import java.util.Date;

public class InserisciVoloController {
    private Connection conn;
    private VoloDAO voloDAO;

    public InserisciVoloController() {
        conn = ConnessioneDB.getConnection();
        if(conn != null) {
            voloDAO = new VoloDAO(conn);
        }
    }
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
        Volo nuovoVolo = new Volo(0, destinazione.trim(), partenza, arrivo, "programmato", "1");

        boolean successo = voloDAO.salvaVolo(nuovoVolo);
        if(successo) {
            System.out.println("Volo salvato con ID: " + nuovoVolo.getId());
        } else {
            System.err.println("Salvataggio fallito.");
        }
        return successo;
    }
}
