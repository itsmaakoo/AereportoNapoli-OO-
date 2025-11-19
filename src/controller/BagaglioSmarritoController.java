package controller;

import dao.BagaglioDAO;
import dao.PrenotazioneDAO;
import dao.VoloDAO;
import db.ConnessioneDB;
import model.Bagaglio;
import model.Prenotazione;
import model.StatoBagaglio;
import model.Volo;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

/**
 * Gestisce la segnalazione dei bagagli smarriti associati ai voli effettuata dagli utenti.
 * In base al volo prenotato si può aggiornare lo stato del bagaglio come "smarrito".
 * <p><b>Dipendenze</b></p>
 * <ul>
 * <li>{@link dao.VoloDAO}</li>
 * <li>{@link dao.PrenotazioneDAO}</li>
 * <li>{@link dao.BagaglioDAO}</li>
 * <li>{@link db.ConnessioneDB}</li>
 * </ul>
 *
 * <p><b>Modelli</b></p>
 * <ul>
 * <li>{@link model.Volo}</li>
 * <li>{@link model.Prenotazione}</li>
 * <li>{@link model.Bagaglio}</li>
 * <li>{@link model.StatoBagaglio}</li>
 * </ul>
 */

public class BagaglioSmarritoController {

    /**
     * Carica l'elenco dei voli disbonibili nella {@link JComboBox}.
     *
     * @param voloJComboBox componente per la visualizzazione dei voli
     * @param parentFrame   il frame per la gestione dei messaggi di errore
     */
    public void caricaVoli(JComboBox<Volo> voloJComboBox, JFrame parentFrame) {
        try {
            Connection conn = ConnessioneDB.getConnection();
            if (conn != null) {
                VoloDAO voloDAO = new VoloDAO(conn);
                List<Volo> voli = voloDAO.getTuttiVoli();
                voloJComboBox.removeAllItems();
                for (Volo v : voli) {
                    voloJComboBox.addItem(v);
                }
            } else {
                JOptionPane.showMessageDialog(parentFrame, "Connessione al database fallita");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentFrame, "Errore durante il caricamento dei voli");
        }
    }
    /**
     * Restituisce un {@link ActionListener} che aggiorna la {@link JComboBox} dei bagagli
     * in base al volo selezionato
     *
     * @param voloJComboBox     il componente che contiene i voli
     * @param bagaglioJComboBox il componente da aggiornare co i bagagli del volo selezionato
     * @param parentFrame       il frame principale per la gestione dei messaggi di errore
     * @return un listener che aggiorna la lista dei bagagli in base al volo selezionato
     */
    public ActionListener getCambioVoloListener(JComboBox<Volo> voloJComboBox, JComboBox<Bagaglio> bagaglioJComboBox, JFrame parentFrame) {
        return e -> {
            Volo v = (Volo) voloJComboBox.getSelectedItem();
            bagaglioJComboBox.removeAllItems();
            if (v != null) {
                try {
                    Connection conn = ConnessioneDB.getConnection();
                    PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO(conn);
                    BagaglioDAO bagaglioDAO = new BagaglioDAO(conn);
                    List<Prenotazione> prenotazioni = prenotazioneDAO.getPrenotazioniPerVolo(v.getId());

                    for (Prenotazione p : prenotazioni) {
                        List<Bagaglio> bagagli = bagaglioDAO.trovaBagagliPerPrenotazione(p.getPrenotazioneId());
                        for (Bagaglio b : bagagli) {
                            bagaglioJComboBox.addItem(b);
                        }
                    }
                    if (bagaglioJComboBox.getItemCount() == 0) {
                        JOptionPane.showMessageDialog(parentFrame, "Nessun bagaglio trovato per questo volo.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(parentFrame, "Errore durante il caricamento dei bagagli");
                }
            }
        };
    }
    /**
     * Restituisce un {@link ActionListener} che aggiorna lo stato del bagaglio come {@link StatoBagaglio #smarrito}
     *
     * @param voloJComboBox     il componente che contiene i voli
     * @param bagaglioJComboBox il componente che contiene i bagagli
     * @param parentFrame       il frame per la gestione dei messaggi di errori
     * @return un listener che segnala il bagaglio selezionato come smarrito.
     */
    public ActionListener getSegnalaBagaglioListener(JComboBox<Volo> voloJComboBox, JComboBox<Bagaglio> bagaglioJComboBox, JFrame parentFrame) {
        return e -> {
            Bagaglio b = (Bagaglio) bagaglioJComboBox.getSelectedItem();
            if (b != null) {
                try {
                    b.setStato(StatoBagaglio.smarrito);
                    Connection conn = ConnessioneDB.getConnection();
                    BagaglioDAO dao = new BagaglioDAO(conn);
                    dao.aggiornaStatoBagaglio(String.valueOf(b.getCodice()), StatoBagaglio.smarrito);
                    JOptionPane.showMessageDialog(parentFrame, "Bagaglio segnalato come smarrito");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(parentFrame, "Errore durante l'aggiornamento dello stato del bagaglio");
                }
            } else {
                JOptionPane.showMessageDialog(parentFrame, "Seleziona un bagaglio da segnalare.");
            }
  };
}
}
