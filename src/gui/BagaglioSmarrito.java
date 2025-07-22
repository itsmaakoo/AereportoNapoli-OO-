package gui;

import dao.VoloDAO;
import db.ConnessioneDB;

import model.Bagaglio;
import model.Volo;
import model.StatoBagaglio;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class BagaglioSmarrito extends JFrame {
    private JComboBox<Volo> voloJComboBox;
    private JComboBox<Bagaglio> bagaglioJComboBox;
    private JButton segnalaBagaglioButton;

    public BagaglioSmarrito() {
        setTitle("segnala bagaglio smarrito");
        setSize(400, 300);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3,2,10,10));

        voloJComboBox = new JComboBox<>(Volo.archivio.toArray(new Volo[0]));
        bagaglioJComboBox=new JComboBox<>();
        segnalaBagaglioButton=new JButton("segnala bagaglio");

        add(new JLabel("Seleziona Volo"));
        add(voloJComboBox);
        add(new JLabel("Seleziona bagaglio"));
        add(bagaglioJComboBox);
        add(new JLabel());
        add(segnalaBagaglioButton);

        try {
            Connection conn = ConnessioneDB.getConnection();
            if (conn != null) {
                VoloDAO voloDAO = new VoloDAO(conn);
                List<Volo> voli = voloDAO.getTuttiVoli();
                for (Volo v : voli) {
                    voloJComboBox.addItem(v);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Connessione al database fallita");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante il caricamento dei voli");
        }
        voloJComboBox.addActionListener(e -> {
            Volo v = (Volo) voloJComboBox.getSelectedItem();
            bagaglioJComboBox.removeAllItems();
            if (v != null) {
                for (Bagaglio b : v.getBagagli()) {
                    bagaglioJComboBox.addItem(b);
                }
            }
        });

        segnalaBagaglioButton.addActionListener(e->{
            Bagaglio b = (Bagaglio) bagaglioJComboBox.getSelectedItem();
            if(b != null){
                b.setStato(StatoBagaglio.smarrito);
                JOptionPane.showMessageDialog(this, "segnala bagaglio come smarrito");

            }
        });
        setVisible(true);
    }
}

