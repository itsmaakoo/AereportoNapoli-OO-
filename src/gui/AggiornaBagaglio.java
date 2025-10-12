package gui;

import controller.AggiornaBagaglioController;
import model.Volo;
import model.Bagaglio;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AggiornaBagaglio extends JFrame {

    private JTable bagagliTable;
    private JComboBox<Volo> voloComboBox;
    private DefaultTableModel tableModel;
    private AggiornaBagaglioController controller;


    public AggiornaBagaglio() {
        setTitle("Visualizza bagaglio");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        controller = new AggiornaBagaglioController();

        voloComboBox = new JComboBox<>();
        DefaultComboBoxModel<Volo> model = new DefaultComboBoxModel<>();

        List<Volo> voli = controller.recuperaVoli();
        for(Volo volo : voli){
            model.addElement(volo);
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

        List<Bagaglio> bagagli = controller.recuperaBagagliPerVolo(selezionato.getId());
        if(bagagli.isEmpty()){
            JOptionPane.showMessageDialog(this, "Nessun bagaglio trovato per questo volo.");
        }
        for(Bagaglio b : bagagli){
            Object[] row = {
                    selezionato.toString(),
                    b.getCodice(),
                    b.getDescrizione(),
                    b.getStato()
            };
            tableModel.addRow(row);
        }
    }
}