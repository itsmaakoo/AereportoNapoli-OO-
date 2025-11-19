package controller;

import dao.VoloDAO;
import db.ConnessioneDB;
import model.Volo;

import javax.swing.*;
import java.sql.Connection;
import java.util.List;

/**
 * Gestisce la modifica dello stato di un volo selzionato.
 * Tramite la ComboBox carica l'elenco dei voli disponibili e consente l'aggiornamneto dello stato tramite {@link VoloDAO}
 * <p><b>Dipendenze</b></p>
 * <ul>
 * <li>{@link dao.VoloDAO}</li>
 * <li>{@link db.ConnessioneDB}</li>
 * </ul>
 *
 * <p><b>Modelli</b></p>
 * <ul>
 * <li>{@link model.Volo}</li>
 * </ul>
 */
public class ModificaVoloController {
    private JComboBox<Volo> voloJComboBox;
    private JComboBox<String> statoBox;
    private JFrame parentFrame;

    /**
     * Crea un nuovo controller per la modifica dello stato del volo.
     * Carica i voli disponibili nel {@link JComboBox}
     *
     * @param voloJComboBox componente grafico per la selezione del volo
     * @param statoBox      componente grafico per la selezione dello stato
     * @param parentFrame   frame per la gestione dei messaggi di errore
     */
    public ModificaVoloController(JComboBox<Volo> voloJComboBox, JComboBox<String> statoBox, JFrame parentFrame) {
        this.voloJComboBox = voloJComboBox;
        this.statoBox = statoBox;
        this.parentFrame = parentFrame;
        caricaVoli();

        /**
         * Carica tutti i voli disponibili dal database e li inserisce nel {@link JComboBox}
         * Nel caso di problemi di connessione o di caricamento mostra un messaggio di errore
         */

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

    /**
     * Aggiorna lo stato volo selezionato nel database con il valore scelto dall'amministratore nel {@link JComboBox}.
     * Nel caso di problemi di connessione o di caricamento mostra un messaggio di errore
     */
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
