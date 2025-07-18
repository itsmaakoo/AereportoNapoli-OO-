package gui;

import javax.swing.*;
import java.awt.*;

public class UtenteGenericoPanel extends JFrame {

    private JPanel panel1;
    private JButton prenotaVoloButton;
    private JButton statoVoloButton;
    private JButton segnalaBagaglioSmarritoButton;
    private JButton aggiungiBagaglio;
    private JButton logoutButton;

    public UtenteGenericoPanel() {
        super("Utente Generico");

        panel1 = new JPanel(new GridBagLayout());
        panel1.setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20, 20, 20, 20);
        Dimension buttonSize = new Dimension(200, 40);

        prenotaVoloButton = new JButton("Prenota Volo");
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        prenotaVoloButton.setPreferredSize(buttonSize);
        c.anchor = GridBagConstraints.CENTER;
        panel1.add(prenotaVoloButton, c);

        prenotaVoloButton.addActionListener(e -> {
            PrenotaVolo pr = new PrenotaVolo();
            pr.setVisible(true);
        });

        statoVoloButton = new JButton("Stato Volo");
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        statoVoloButton.setPreferredSize(buttonSize);
        c.anchor = GridBagConstraints.CENTER;
        panel1.add(statoVoloButton, c);

        statoVoloButton.addActionListener(e -> {
            new StatoVolo();
        });

        aggiungiBagaglio = new JButton("Aggiungi bagaglio");
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 3;
        aggiungiBagaglio.setPreferredSize(buttonSize);
        c.anchor = GridBagConstraints.CENTER;
        panel1.add(aggiungiBagaglio, c);

        aggiungiBagaglio.addActionListener(e -> {
            new AggiungiBagaglio();
        });

        segnalaBagaglioSmarritoButton = new JButton("Segnala bagaglio smarrito");
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 3;
        segnalaBagaglioSmarritoButton.setPreferredSize(buttonSize);
        c.anchor = GridBagConstraints.CENTER;
        panel1.add(segnalaBagaglioSmarritoButton, c);

        segnalaBagaglioSmarritoButton.addActionListener(e -> {
            new BagaglioSmarrito();
        });

        logoutButton = new JButton("Logout");
        logoutButton.setForeground(Color.RED);
        c.gridx = 1;
        c.gridy = 6;
        panel1.add(logoutButton, c);

        logoutButton.addActionListener(e -> {
            new LoginFrame();
            dispose();
        });

        add(panel1);

        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {

        new UtenteGenericoPanel();
    }
}