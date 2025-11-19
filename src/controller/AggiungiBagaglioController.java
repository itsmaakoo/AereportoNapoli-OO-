package controller;

import dao.BagaglioDAO;
import dao.PrenotazioneDAO;
import db.ConnessioneDB;
import model.Bagaglio;
import model.Prenotazione;
import model.Volo;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

/**
 * Controller per aggiungere un bagaglio a una prenotazione associata a un volo.
 * Questo cosice fornisce un {@link ActionListener} che può essere associato a un componente grafico
 * per la gestione dell'inserimento del bagaglio, utilizzamdo il {@link Prenotazione} disponibile
 * per il volo selezionato.
 *
 * <p><b>Dipendenze</b></p>
 * <ul>
 * <li>{@link dao.PrenotazioneDAO}</li>
 * <li>{@link dao.BagaglioDAO}</li>
 * <li>{@link db.ConnessioneDB}</li>
 * </ul>
 *
 * <p><b>Modelii</b></p>
 * <ul>
 * <li>{@link model.Volo}</li>
 * <li>{@link model.Prenotazione}</li>
 * <li>{@link model.Bagaglio}</li>
 * </ul>
 */
public class AggiungiBagaglioController {

    /**
     * Crea e restituisce un {@link ActionListener} per aggiungere un bagaglio.
     * Il listner verifica che siano stati selezionati un volo e un codice bagaglio e in seguito
     * recupera le prenotazioni associate al volo e aggiunge il bagaglio alla prenotazione disponibile.
     *
     * @param voloComboBox  combo box che contiene i voli disponibili
     * @param campoBagaglio campo di input che contiene il codice del bagaglio
     * @param parentFrame   frame genitore per i messaggi di conferma o di errore
     * @return action listener
     *
     *
     */
    public ActionListener getAggiungiBagaglioAction(JComboBox<Volo> voloComboBox, JTextField campoBagaglio, JFrame parentFrame) {
        return e -> {
            Volo selezionato = (Volo) voloComboBox.getSelectedItem();
            String input = campoBagaglio.getText().trim();

            if (selezionato == null || input.isEmpty()) {
                JOptionPane.showMessageDialog(parentFrame, "Seleziona un volo e inserisci il codice del bagaglio.");
                return;
            }

            try {
                int codiceBagaglio = Integer.parseInt(input);

                try (Connection conn = ConnessioneDB.getConnection()) {
                    PrenotazioneDAO prenotazioneDao = new PrenotazioneDAO(conn);
                    List<Prenotazione> prenotazioni = prenotazioneDao.getPrenotazioniPerVolo(selezionato.getId());

                    if (!prenotazioni.isEmpty()) {
                        Prenotazione primaPrenotazione = prenotazioni.get(0); // usa la prima prenotazione disponibile
                        int prenotazioneId = primaPrenotazione.getPrenotazioneId();

                        Bagaglio nuovoBagaglio = new Bagaglio(prenotazioneId, codiceBagaglio, "Bagaglio aggiunto manualmente");
                        BagaglioDAO bagaglioDao = new BagaglioDAO(conn);
                        bagaglioDao.add(nuovoBagaglio, prenotazioneId);

                        JOptionPane.showMessageDialog(parentFrame, "Bagaglio aggiunto correttamente al volo.");
                        campoBagaglio.setText("");
                    } else {
                        JOptionPane.showMessageDialog(parentFrame, "Nessuna prenotazione trovata per questo volo.");
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(parentFrame, "Il codice del bagaglio deve essere un numero intero.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(parentFrame, "Errore durante l'aggiunta del bagaglio.");
            }
};
}
}

