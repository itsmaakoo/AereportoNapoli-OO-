package gui;

import dao.PasseggeroDAO;
import db.ConnessioneDB;
import model.Passeggero;
import model.Prenotazione;
import model.Volo;
import dao.VoloDAO;
import dao.PrenotazioneDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class PrenotaVolo extends JFrame {

    private JTextField nomeField, cognomeField, codiceFiscale;
    private JList<Volo> listaVoli;
    private Connection conn;

    public PrenotaVolo() {
        setTitle("Prenota Volo");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());


        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        nomeField = new JTextField();
        cognomeField = new JTextField();
        codiceFiscale = new JTextField();

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        formPanel.add(new JLabel("Cognome:"));
        formPanel.add(cognomeField);
        formPanel.add(new JLabel("CodiceFiscale:"));
        formPanel.add(codiceFiscale);

        panel.add(formPanel, BorderLayout.NORTH);

        DefaultListModel<Volo> model = new DefaultListModel<>();
        /*try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        conn = ConnessioneDB.getConnection();
        if (conn != null) {
            VoloDAO voloDAO = new VoloDAO(conn);
            List<Volo> voli = voloDAO.getTuttiVoli();
            for (Volo v : voli) {
                model.addElement(v);
            }
            // Usa la connessione, es. passala al tuo DAO
            /*JOptionPane.showMessageDialog(null, "Connessione al DB riuscita!");
            dispose();
            return;*/
        }




        listaVoli = new JList<>(model);
        listaVoli.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaVoli);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton checkInButton = new JButton(" Effettua check in");
        checkInButton.addActionListener(e -> {
            try {
                prenota();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(checkInButton, BorderLayout.SOUTH);
        add(panel);
        setVisible(true);
    }

    private void prenota() throws SQLException {
        Volo selezionato = listaVoli.getSelectedValue();
        String nome = nomeField.getText().trim();
        String cognome = cognomeField.getText().trim();
        String cf = codiceFiscale.getText().trim();

        if (selezionato == null || nome.isEmpty() || cognome.isEmpty() || cf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "compila tutti i campi");
        }
        // crea salva passaggero
        Passeggero psg = new Passeggero(nome, cognome, cf);
        PasseggeroDAO passeggeroDao = new PasseggeroDAO(conn);
        int passeggeroId = passeggeroDao.salvaPasseggero(psg);

        if (passeggeroId == 0) {
            JOptionPane.showMessageDialog(this, "errore salvataggio Passeggero");
            return;
        }

        Random rand = new Random();
        int num_biglietto = 1000 + rand.nextInt(999999);
        int prenotazioneId = 1 + rand.nextInt(10000);
        String documento = cf;
        String stato = "Prenotazione confermata";

        Prenotazione pr = new Prenotazione(num_biglietto, passeggeroId, documento, selezionato.getId(), prenotazioneId, stato);
        PrenotazioneDAO prenotazioneDao = new PrenotazioneDAO(conn);
        prenotazioneDao.salvaPrenotazione(pr);
        JOptionPane.showMessageDialog(this, "Prenotazione confermata");

        nomeField.setText("");
        cognomeField.setText("");
        codiceFiscale.setText("");
    }
}