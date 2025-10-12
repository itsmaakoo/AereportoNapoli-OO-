package gui;

import controller.VisualizzaPasseggeriController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class VisualizzaPasseggeri extends JFrame {

    public VisualizzaPasseggeri() {
        setTitle("Passeggeri Registrati");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] colonne = {"id","Nome", "Cognome", "Codice Fiscale"};
        DefaultTableModel model = new DefaultTableModel(colonne, 0);

        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        VisualizzaPasseggeriController controller = new VisualizzaPasseggeriController();
        controller.caricaPasseggeri();

        setVisible(true);
    }
}