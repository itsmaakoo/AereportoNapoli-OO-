package gui;

import dao.PrenotazioneDAO;
import dao.BagaglioDAO;
import dao.VoloDAO;
import db.ConnessioneDB;
import model.Bagaglio;
import model.Prenotazione;
import model.Volo;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class AggiungiBagaglio extends JFrame {

    private JComboBox<Volo> voloJComboBox;
    private JTextField campoBagaglio;
    private JButton confermaButton;

    public AggiungiBagaglio() {
        setTitle("Aggiungi bagaglio");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;

        voloJComboBox = new JComboBox<>();
        DefaultComboBoxModel<Volo> model = new DefaultComboBoxModel<>();

        try(Connection conn = ConnessioneDB.getConnection()){
            if(conn != null) {
                VoloDAO voloDao = new VoloDAO(conn);
                List<Volo> voli = voloDao.getTuttiVoli();
                for (Volo volo : voli) {
                    model.addElement(volo);
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Errore nel caricamento dei voli");
            e.printStackTrace();
        }

        voloJComboBox.setModel(model);

        campoBagaglio = new JTextField();
        confermaButton = new JButton("Aggiungi");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        panel.add(new JLabel("Seleziona Volo: "), c);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1.0;
        panel.add(voloJComboBox, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        panel.add(new JLabel("Seleziona bagaglio: "), c);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1.0;
        panel.add(campoBagaglio, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        panel.add(confermaButton, c);

        add(panel);

        confermaButton.addActionListener(e -> {
            Volo selezionato = (Volo) voloJComboBox.getSelectedItem();
            String descrizione = campoBagaglio.getText().trim();

            if (selezionato != null && !descrizione.isEmpty()) {
                try {
                    int codiceBagaglio = Integer.parseInt(descrizione);
                    Connection connBagaglio = ConnessioneDB.getConnection();

                    PrenotazioneDAO prenotazionidao = new PrenotazioneDAO(connBagaglio);
                    List<Prenotazione> prenotazioni = prenotazionidao.trovaPrenotazioniPerVolo(selezionato.getId());

                    if (!prenotazioni.isEmpty()) {
                        Prenotazione primaprenotazione = prenotazioni.get(0); //usa la prima Prenotazione come default
                        int prenotazioneId = primaprenotazione.getPrenotazioneId();
                        Bagaglio nuovoBagaglio = new Bagaglio(prenotazioneId, codiceBagaglio, "bagaglio aggiunto manualmente");
                        BagaglioDAO bagagliodao = new BagaglioDAO(connBagaglio);
                        bagagliodao.add(nuovoBagaglio, prenotazioneId);
                        JOptionPane.showMessageDialog(this, "Bagaglio aggiunto al Volo");
                        campoBagaglio.setText("");

                    } else {
                        JOptionPane.showMessageDialog(this, "Nessuna Prenotazione trovata per questo Volo");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Inserisci un numero valido per il codice del bagaglio.");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Inserisci un numero valido per codice del bagaglio.");
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleziona un Volo e inserisci il codice del bagaglio.");
            }
        });
        setVisible(true);
    }
}
