package gui;

import controller.BagaglioSmarritoController;

import model.Bagaglio;
import model.Volo;

import javax.swing.*;
import java.awt.*;


public class BagaglioSmarrito extends JFrame {
    private JComboBox<Volo> voloJComboBox;
    private JComboBox<Bagaglio> bagaglioJComboBox;
    private JButton segnalaBagaglioButton;

    private BagaglioSmarritoController controller;

    public BagaglioSmarrito() {
        setTitle("segnala bagaglio smarrito");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3,2,10,10));

        controller = new BagaglioSmarritoController();

        voloJComboBox = new JComboBox<>(Volo.archivio.toArray(new Volo[0]));
        bagaglioJComboBox=new JComboBox<>();
        segnalaBagaglioButton=new JButton("segnala bagaglio");

        add(new JLabel("Seleziona Volo"));
        add(voloJComboBox);
        add(new JLabel("Seleziona bagaglio"));
        add(bagaglioJComboBox);
        add(new JLabel());
        add(segnalaBagaglioButton);

        controller.caricaVoli(voloJComboBox, this);

        voloJComboBox.addActionListener(controller.getCambioVoloListener(voloJComboBox, bagaglioJComboBox, this));
        segnalaBagaglioButton.addActionListener(controller.getSegnalaBagaglioListener(voloJComboBox, bagaglioJComboBox, this));


        setVisible(true);
    }
}