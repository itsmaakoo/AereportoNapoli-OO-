package controller;

import dao.BagaglioDAO;
import db.ConnessioneDB;
import model.Bagaglio;
import model.StatoBagaglio;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Gestisce la visualizzazione dei bagagli segnalati come smarriti.
 *
 * <p><b>Dipendenze</b></p>
 * <ul>
 * <li>{@link dao.BagaglioDAO}</li>
 * <li>{@link db.ConnessioneDB}</li>
 * </ul>
 *
 * <p><b>Modelli</b></p>
 * <ul>
 * <li>{@link model.Bagaglio}</li>
 * <li>{@link model.StatoBagaglio}</li>
 * </ul>
 */
public class VisualizzaSmarrimentiController {
    private DefaultTableModel model;

    /**
     * Crea un controller per la visualizzazione dei bagagli smarriti
     *
     * @param model tabella {@link DefaultTableModel} dove verranno inseriti i dati dei bagagli
     */
    public VisualizzaSmarrimentiController(DefaultTableModel model) {
        this.model = model;
    }

    /**
     * Carica nella tabella tutti i bagagli con stati {@link StatoBagaglio#smarrito}
     */
    public void caricaBagagliSmarriti() {
        try (Connection conn = ConnessioneDB.getConnection()) {
            BagaglioDAO dao = new BagaglioDAO(conn);
            List<Bagaglio> bagagli = dao.trovaBagagliSmarriti();

            for (Bagaglio b : bagagli) {
                Object[] row = {
                        "Volo " + b.getPrenotazioneId(),
                        b.getCodice(),
                        b.getDescrizione(),
                        b.getStato()
                };
                model.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
