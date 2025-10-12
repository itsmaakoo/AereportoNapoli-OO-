package gui;

import controller.ModificaVoloController;
import model.Volo;

import javax.swing.*;
import java.awt.*;


public class ModificaVolo extends JFrame {
    private JComboBox<Volo> voloJComboBox;
    private JButton confermaButton;
    private JComboBox<String> statoBox;
    private ModificaVoloController controller;


    public ModificaVolo() {

        setTitle("Modifica Volo");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2, 2, 2, 2);
        c.fill = GridBagConstraints.HORIZONTAL;

        voloJComboBox = new JComboBox<>();
        statoBox= new JComboBox<>(new String[]{"In Orario","In ritardo","Cancellato"});
        confermaButton = new JButton("Conferma");

        controller = new ModificaVoloController(voloJComboBox, statoBox, this);
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


        confermaButton.addActionListener(e-> controller.aggiornaStatoVolo());
    }
    public static void main(String[] args) {
        new ModificaVolo();
    }
}