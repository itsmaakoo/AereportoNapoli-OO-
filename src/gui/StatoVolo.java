package gui;

import model.Volo;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StatoVolo extends JFrame {
    public StatoVolo() {
        setTitle("Stato del Volo");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        DefaultListModel<Volo> model = new DefaultListModel<>();
        List<Volo> voli = Volo.archivio;
        for(Volo volo : voli) {
            model.addElement(volo);
        }
        JList<Volo> lista= new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(lista);
        panel.add(scrollPane, BorderLayout.CENTER);
        add(panel);
        setVisible(true);
    }
}