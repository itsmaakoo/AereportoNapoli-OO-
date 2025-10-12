package controller;

import model.Bagaglio;
import model.StatoBagaglio;
import model.Volo;

import javax.swing.table.DefaultTableModel;

public class VisualizzaSmarrimentiController {
    private DefaultTableModel model;

    public VisualizzaSmarrimentiController(DefaultTableModel model) {
        this.model = model;

    }

    public void caricaBagagliSmarriti(){
        model.setRowCount(0);
        for(Volo v : Volo.archivio) {
            for (Bagaglio b : v.getBagagli()) {
                if (b.getStato() == StatoBagaglio.smarrito) {
                    Object[] row = {
                            v.toString(),
                            b.getCodice(),
                            b.getDescrizione(),
                            b.getStato().toString()
                    };
                    model.addRow(row);
                }
            }
        }

    }

}