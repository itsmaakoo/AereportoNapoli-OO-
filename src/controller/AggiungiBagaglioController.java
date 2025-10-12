package controller;

import dao.BagaglioDAO;
import dao.PrenotazioneDAO;
import db.ConnessioneDB;
import model.Bagaglio;
import model.Prenotazione;
import model.Volo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class AggiungiBagaglioController {
    public ActionListener getAggiungiBagaglioAction(JComboBox <Volo> voloComboBox, JTextField campoBagaglio, JFrame parentFrame) {
        return e -> {
            Volo selezionato = (Volo) voloComboBox.getSelectedItem();
            String descrizione = campoBagaglio.getText().trim();

            if (selezionato != null && descrizione.isEmpty()) {
                try {
                    int codiceBagaglio = Integer.parseInt(descrizione);
                    try (Connection conn = ConnessioneDB.getConnection()) {
                        PrenotazioneDAO prenotazioneDao = new PrenotazioneDAO(conn);
                        List<Prenotazione> prenotazioni = prenotazioneDao.trovaPrenotazioniPerVolo(selezionato.getId());

                        if (!prenotazioni.isEmpty()) {
                            Prenotazione primaprenotazione = prenotazioni.get(0); //usa la prima Prenotazione come default
                            int prenotazioneId = primaprenotazione.getPrenotazioneId();
                            Bagaglio nuovoBagaglio = new Bagaglio(prenotazioneId, codiceBagaglio, "bagaglio aggiunto manualmente");
                            BagaglioDAO bagagliodao = new BagaglioDAO(conn);
                            bagagliodao.add(nuovoBagaglio, prenotazioneId);
                            JOptionPane.showMessageDialog(parentFrame, "Bagaglio aggiunto al Volo");
                            campoBagaglio.setText("");

                        } else {
                            JOptionPane.showMessageDialog(parentFrame, "Nessuna Prenotazione trovata per questo Volo");
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(parentFrame, "Inserisci un numero valido per il codice del bagaglio.");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(parentFrame, "Inserisci un numero valido per codice del bagaglio.");
                    ex.printStackTrace();
                }
            } else{
                JOptionPane.showMessageDialog(parentFrame, "Seleziona un Volo e inserisci il codice del bagaglio.");
            }
        };

    }
}
