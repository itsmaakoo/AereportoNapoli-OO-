package gui;

import dao.VoloDAO;
import db.ConnessioneDB;
import model.Volo;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.List;




public class ModificaVolo extends JFrame {
    private JComboBox<Volo> voloJComboBox;
    private JButton confermaButton;
    private JComboBox<String> statoBox;


    public ModificaVolo() {

        setTitle("Modifica Volo");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2, 2, 2, 2);
        c.fill = GridBagConstraints.HORIZONTAL;

        voloJComboBox = new JComboBox<>();
        statoBox= new JComboBox<>(new String[]{"In Orario","In ritardo","Cancellato", "Programmato"});
        confermaButton = new JButton("Conferma");

        try{
            Connection conn = ConnessioneDB.getConnection();
            if(conn != null) {
                VoloDAO voloDao = new VoloDAO(conn);
                List<Volo> voli = voloDao.getTuttiVoli();
                for (Volo volo : voli) {
                    voloJComboBox.addItem(volo);
                }
            }else {
                JOptionPane.showMessageDialog(this, "Connessione non trovato");
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante il caricamento dei voli");
        }

        c.gridx = 0;
        c.gridy = 0;
        panel.add(new JLabel("Seleziona Volo:"),c);

        c.gridx = 1;
        c.gridy = 0;
        panel.add(voloJComboBox,c);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(new JLabel("Seleziona stato:"),c);

        c.gridx = 1;
        c.gridy = 1;
        panel.add(statoBox,c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        panel.add(confermaButton,c);

        add(panel);
        setVisible(true);


        confermaButton.addActionListener(e->{
            Volo voloSelezionato = (Volo)voloJComboBox.getSelectedItem();
            if (voloSelezionato != null) {
                String nuovoStato= (String) statoBox.getSelectedItem();
                voloSelezionato.setStato(nuovoStato);
                try{
                    Connection conn = ConnessioneDB.getConnection();
                    if(conn != null) {
                        VoloDAO voloDao = new VoloDAO(conn);
                        voloDao.aggiornaStato(voloSelezionato.getId(), statoBox.getSelectedItem().toString());
                        JOptionPane.showMessageDialog(this, "Stato aggiornato:" +nuovoStato);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Errore durante il caricamento dei voli");
                }
            }
        });
    }
    public static void main(String[] args) {
        new ModificaVolo();
    }
}