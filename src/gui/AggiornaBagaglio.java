package gui;

import dao.BagaglioDAO;
import dao.VoloDAO;
import dao.PrenotazioneDAO;
import model.Prenotazione;
import model.Volo;
import model.Bagaglio;
import db.ConnessioneDB;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AggiornaBagaglio extends JFrame {

    private JTable bagagliTable;
    private JComboBox<Volo> voloComboBox;
    private DefaultTableModel tableModel;


    public AggiornaBagaglio() {
        setTitle("Visualizza bagaglio");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        voloComboBox = new JComboBox<>();
        DefaultComboBoxModel<Volo> model = new DefaultComboBoxModel<>();
        try(Connection conn = ConnessioneDB.getConnection()){
            if(conn != null){
                VoloDAO volodao = new VoloDAO(conn);
                List<Volo> voli = volodao.getTuttiVoli();
                for(Volo volo : voli){
                    model.addElement(volo);
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Errore nel caricamento dei voli");
            e.printStackTrace();
        }
        voloComboBox.setModel(model);
        add(voloComboBox, BorderLayout.NORTH);

        String[] colonne = {"Volo", "codice bagaglio", "descrizione", "stato"};
        tableModel = new DefaultTableModel(colonne, 0);
        bagagliTable = new JTable(tableModel);
        add(new JScrollPane(bagagliTable), BorderLayout.CENTER);

        voloComboBox.addActionListener(e -> {
            caricaBagagli();
        });

        if (voloComboBox.getItemCount() > 0) {
            caricaBagagli();
        }
        setVisible(true);
    }

    private void caricaBagagli() {
        tableModel.setNumRows(0);
        Volo selezionato = (Volo) voloComboBox.getSelectedItem();

        if (selezionato == null) return;
        try (Connection conn = ConnessioneDB.getConnection()){
            if(conn != null) {
                PrenotazioneDAO prenotazionidao = new PrenotazioneDAO(conn);
                BagaglioDAO bagagliodao = new BagaglioDAO(conn);

                List<Prenotazione> prenotazioni = prenotazionidao.trovaPrenotazioniPerVolo(selezionato.getId());
                List<Bagaglio> tuttibagagli = new ArrayList<>();

                for (Prenotazione p : prenotazioni) {
                    List<Bagaglio> bagagliPerPrenotazione = bagagliodao.trovaBagagliPerPrenotazione(p.getPrenotazioneId());
                    if (bagagliPerPrenotazione != null) {
                        tuttibagagli.addAll(bagagliPerPrenotazione);
                    }
                }


                for (Bagaglio b : tuttibagagli) {
                    Object[] row = {selezionato.toString(), b.getCodice(), b.getDescrizione(), b.getStato()};
                    tableModel.addRow(row);
                }
                if (tuttibagagli.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Non trovato");

                }
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "Errore bagaglio");
            e.printStackTrace();

        }
    }

}
