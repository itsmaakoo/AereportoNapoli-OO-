package controller;

import dao.PasseggeroDAO;
import db.ConnessioneDB;
import model.Passeggero;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Gestisce la visualizzazione dei passeggeri associate a un determinato volo
 * utilizza {@link PasseggeroDAO} per recuperare i dati dal database
 *
 * <p><b>Dipendenze</b></p>
 * <ul>
 * <li>{@link dao.PasseggeroDAO}</li>
 * <li>{@link db.ConnessioneDB}</li>
 * </ul>
 *
 * <p><b>Modelli</b></p>
 * <ul>
 * <li>{@link model.Passeggero}</li>
 * </ul>
 */
public class VisualizzaPasseggeriController {
    private DefaultTableModel model;

    /**
     * Crea un nuovo controller per la visualizzazione dei passeggeri
     *
     * @param model tabella {@link DefaultTableModel} in cui verranno inseriti i dati dei passeggeri
     */
    public VisualizzaPasseggeriController(DefaultTableModel model) {
        this.model = model;
    }

    /**
     * Carica tutti i passeggeri associati al volo.
     *
     * @param idVolo id del volo per cui visualizzare i passeggeri
     */
// Metodo aggiornato per caricare passeggeri filtrati per volo
    public void caricaPasseggeriPerVolo(String idVolo) {
        model.setRowCount(0); // Pulisce la tabella

        try (Connection conn = ConnessioneDB.getConnection()) {
            if (conn != null) {
                PasseggeroDAO dao = new PasseggeroDAO(conn);
                List<Passeggero> passeggeri = dao.trovaListaPasseggero();

                for (Passeggero passeggero : passeggeri) {
                    if (idVolo != null && idVolo.equals(String.valueOf(passeggero.getIdVolo()))) {
                        Object[] row = {
                                passeggero.getIdVolo(),
                                passeggero.getNome(),
                                passeggero.getCognome(),
                                passeggero.getCodiceFiscale()
                        };
                        model.addRow(row);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Errore nel caricamento dei passeggeri per il volo selezionato");
            e.printStackTrace();
        }
    }
}


