package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Volo;
import controller.AssegnaGateController;

public class AssegnaGate extends JFrame {
    private AssegnaGateController controller;

    public AssegnaGate() {
        controller = new AssegnaGateController();

        setTitle("Assegna Gate");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2, 2, 2, 2);
        c.fill = GridBagConstraints.HORIZONTAL;

        JComboBox<Volo> voloJComboBox = new JComboBox<>();
        List<Volo> voli = controller.getTuttiVoli();
        for (Volo v : voli) {
            voloJComboBox.addItem(v);
        }

        JLabel voloLabel = new JLabel("Seleziona Volo");
        c.gridx = 0;
        c.gridy = 0;
        panel.add(voloLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        panel.add(voloJComboBox, c);

        //campo gate
        JLabel gateLabel = new JLabel("Seleziona gate");
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        panel.add(gateLabel, c);

        JTextField gateJTextField = new JTextField(10);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        panel.add(gateJTextField, c);

        //bottone conferma
        JButton confermaButton = new JButton("Assegna Gate");
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        panel.add(confermaButton, c);

        confermaButton.addActionListener(e -> {
            Volo volo = (Volo) voloJComboBox.getSelectedItem();
            String gate = gateJTextField.getText().trim();

            if (volo != null && !gate.isEmpty()) {
                boolean success = controller.assegnaGate(volo.getId(), gate);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Assegna gate: " + gate + "al Volo" + volo.getDestinazione());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Errore nell'assegnazione del gate.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleziona un volo e inserisci un gate valido.");
            }
        });

        add(panel);
        setVisible(true);
    }
}

