package controller;

import dao.BagaglioDAO;
import dao.VoloDAO;
import db.ConnessioneDB;
import model.Bagaglio;
import model.StatoBagaglio;
import model.Volo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;


public class BagaglioSmarritoController {

    public void caricaVoli (JComboBox<Volo> voloJComboBox, JFrame parentFrame) {
        try {
            Connection conn = ConnessioneDB.getConnection();
            if (conn != null) {
                VoloDAO voloDAO = new VoloDAO(conn);
                List<Volo> voli = voloDAO.getTuttiVoli();
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
    public ActionListener getCambioVoloListener(JComboBox<Volo> voloJComboBox, JComboBox<Bagaglio> bagaglioJComboBox, JFrame parentFrame) {
        return e->{
            Volo v = (Volo) voloJComboBox.getSelectedItem();
            bagaglioJComboBox.removeAllItems();
            if (v != null) {
                try{
                    Connection conn = ConnessioneDB.getConnection();
                    BagaglioDAO BagaglioDao = new BagaglioDAO(conn);
                    List<Bagaglio> bagagli = BagaglioDao.trovaBagagliPerPrenotazione(v.getId());

                    for (Bagaglio b : v.getBagagli()) {
                        bagaglioJComboBox.addItem(b);
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(parentFrame, "Errore durante il caricamento dei voli");
                }
            }
        };
    }

    public ActionListener getSegnalaBagaglioListener(JComboBox<Volo> voloJComboBox, JComboBox<Bagaglio> bagaglioJComboBox, JFrame parentFrame) {
        return e -> {
            Bagaglio b = (Bagaglio) bagaglioJComboBox.getSelectedItem();
            if (b != null) {
                b.setStato(StatoBagaglio.smarrito);
                JOptionPane.showMessageDialog(parentFrame, "segnala bagaglio come smarrito");

            }
        };
    }
}
