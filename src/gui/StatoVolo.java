package gui;

import model.Volo;
import controller.StatoVoloController;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StatoVolo extends JFrame {
    private StatoVoloController controller;

    public StatoVolo() {
        setTitle("Stato del Volo");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        controller = new StatoVoloController();

        JPanel panel = new JPanel(new BorderLayout());
        DefaultListModel<Volo> model = new DefaultListModel<>();
        try {
            List<Volo> voli = controller.recuperaTuttiIVoli();
            if (voli.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nessun volo trovato");
            } else {
                for (Volo v : voli) {
                    model.addElement(v);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Errore nel caricamento dei voli.");
        }

        JList<Volo> lista= new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(lista);
        panel.add(scrollPane, BorderLayout.CENTER);
        add(panel);
        setVisible(true);
    }
}