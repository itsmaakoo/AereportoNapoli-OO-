package controller;

import dao.VoloDAO;
import db.ConnessioneDB;
import model.Volo;

import javax.swing.*;
import java.sql.Connection;
import java.util.List;


public class ModificaVoloController {
    private JComboBox<Volo> voloJComboBox;
    private JComboBox<String> statoBox;
    private JFrame parentFrame;

    public ModificaVoloController(JComboBox<Volo> voloJComboBox, JComboBox<String> statoBox, JFrame parentFrame) {
        this.voloJComboBox = voloJComboBox;
        this.statoBox = statoBox;
        this.parentFrame = parentFrame;
        caricaVoli();

    }
    private void caricaVoli() {
        try{
            Connection conn = ConnessioneDB.getConnection();
            if(conn != null) {
                VoloDAO voloDao = new VoloDAO(conn);
                List<Volo> voli = voloDao.getTuttiVoli();
                for (Volo volo : voli) {
                    voloJComboBox.addItem(volo);
                }
            }else {
                JOptionPane.showMessageDialog(parentFrame, "Connessione non trovato");
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(parentFrame, "Errore durante il caricamento dei voli");
        }
    }
    public void aggiornaStatoVolo() {
        Volo voloSelezionato = (Volo)voloJComboBox.getSelectedItem();
        if (voloSelezionato != null) {
            String nuovoStato= (String) statoBox.getSelectedItem();
            voloSelezionato.setStato(nuovoStato);
            try{
                Connection conn = ConnessioneDB.getConnection();
                if(conn != null) {
                    VoloDAO voloDao = new VoloDAO(conn);
                    voloDao.aggiornaStato(voloSelezionato.getId(), statoBox.getSelectedItem().toString());
                    JOptionPane.showMessageDialog(parentFrame, "Stato aggiornato:" +nuovoStato);
                }
            }catch (Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(parentFrame, "Errore durante il caricamento dei voli");
            }
        }
    }
}
