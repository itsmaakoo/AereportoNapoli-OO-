package controller;

import dao.PasseggeroDAO;
import db.ConnessioneDB;
import model.Passeggero;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VisualizzaPasseggeriController {
    private DefaultTableModel model;
    private JFrame parentFrame;

    public void caricaPasseggeri() {
        // ✅ Inizializzazione del model con le intestazioni della tabella
        this.model = new DefaultTableModel(new Object[]{"ID", "Nome", "Cognome", "Codice Fiscale"}, 0);

        try (Connection conn = ConnessioneDB.getConnection()) {

            if (conn != null) {
                PasseggeroDAO dao = new PasseggeroDAO(conn);
                List<Passeggero> passeggeri = dao.trovaListaPasseggero();

                for (Passeggero passeggero : passeggeri) {
                    Object[] row = {
                            passeggero.getId(),
                            passeggero.getNome(),
                            passeggero.getCognome(),
                            passeggero.getCodiceFiscale()
                    };
                    model.addRow(row);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Errore dei passeggeri");
            e.printStackTrace();
        }
    }

    // 💬 Aggiunto metodo getter per recuperare il model dalla GUI
    public DefaultTableModel getModel() {
        return model;
    }
}
