package gui;

import javax.swing.*;
import java.awt.*;

import model.Volo;


public class ModificaVolo extends JFrame {
    private JComboBox<Volo> voloJComboBox;
    private JButton confermaButton;
    private JComboBox<String> statoBox;


    public ModificaVolo() {

        setTitle("Modifica Volo");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2, 2, 2, 2);
        c.fill = GridBagConstraints.HORIZONTAL;

        voloJComboBox = new JComboBox<>(Volo.archivio.toArray(new Volo[0]));
        statoBox= new JComboBox<>(new String[]{"In Orario","In ritardo","Cancellato"});
        confermaButton = new JButton("Conferma");

        c.gridx = 0;
        c.gridy = 0;
        panel.add(new JLabel("Seleziona Volo:"),c);

        c.gridx = 1;
        c.gridy = 0;
        panel.add(voloJComboBox,c);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(new JLabel("Seleziona stato:"),c);

        c.gridx = 1;
        c.gridy = 1;
        panel.add(statoBox,c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        panel.add(confermaButton,c);

        add(panel);
        setVisible(true);


        confermaButton.addActionListener(e->{
            Volo voloSelezionato = (Volo)voloJComboBox.getSelectedItem();
            if (voloSelezionato != null) {
                voloSelezionato.setStato((String) statoBox.getSelectedItem());
                JOptionPane.showMessageDialog(this, "Stato Aggiornato"+voloSelezionato);
            }
        });
    }
    public static void main(String[] args) {
        new AmministratoreGUI();
    }
}