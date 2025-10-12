package gui;

import controller.VisualizzaSmarrimentiController;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VisualizzaSmarrimenti extends JFrame {

    private JTable tabellaSmarrimenti;
    private DefaultTableModel tableModel;
    private VisualizzaSmarrimentiController controller;

    public VisualizzaSmarrimenti(){
        setTitle("bagagli smarrito");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] colonne ={"Volo", "codice", "descrizione", "stato"};
        tableModel = new DefaultTableModel(colonne, 0);
        tabellaSmarrimenti = new JTable(tableModel);
        add(new JScrollPane(tabellaSmarrimenti), BorderLayout.CENTER);

        controller = new VisualizzaSmarrimentiController(tableModel);
        controller.caricaBagagliSmarriti();

        setVisible(true);
    }

}