package gui;

import controller.ModificaVoloController;
import model.Volo;

import javax.swing.*;
import java.awt.*;

/**
 * La classe {@code ModificaVolo} rappresenta l'interfaccia grafica per la modifica
 * dello stato di un volo esistente nel sistema aeroportuale.
 * <p>
 * L'interfaccia consente all'operatore di:
 * <ul>
 *     <li>Selezionare un volo dalla lista disponibile</li>
 *     <li>Modificare lo stato del volo (In Orario, In Ritardo, Cancellato)</li>
 *     <li>Confermare la modifica tramite un pulsante</li>
 * </ul>
 * <p>
 * La logica applicativa è gestita dal {@link controller.ModificaVoloController}, che
 * si occupa dell'aggiornamento dello stato nel sistema.
 *
 */
public class ModificaVolo extends JFrame {
    private JComboBox<Volo> voloJComboBox;
    private JComboBox<String> statoBox;
    private JButton confermaButton;
    private ModificaVoloController controller;

    /**
     * Costruttore della finestra {@code ModificaVolo}.
     * <p>
     * Inizializza l'interfaccia grafica, configura i componenti e collega
     * il pulsante di conferma all'azione di aggiornamento dello stato del volo.
     */
    public ModificaVolo() {
        setTitle("Modifica Volo");
        setSize(700, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(Color.WHITE);

        JLabel titolo = new JLabel("Modifica Stato Volo");
        titolo.setFont(new Font("Montserrat", Font.BOLD, 24));
        titolo.setForeground(new Color(19, 8, 102));
        titolo.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titolo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(12, 12, 12, 12);
        c.fill = GridBagConstraints.HORIZONTAL;

        voloJComboBox = new JComboBox<>();
        voloJComboBox.setFont(new Font("SansSerif", Font.PLAIN, 13));
        voloJComboBox.setPreferredSize(new Dimension(300, 30));
        voloJComboBox.setBackground(Color.WHITE);
        voloJComboBox.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 220)));

        statoBox = new JComboBox<>(new String[]{"In Orario", "In Ritardo", "Cancellato"});
        statoBox.setFont(new Font("SansSerif", Font.PLAIN, 13));
        statoBox.setBackground(Color.WHITE);
        statoBox.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 220)));

        confermaButton = new JButton("Conferma");
        styleButton(confermaButton);

        c.gridx = 0;
        c.gridy = 0;
        formPanel.add(new JLabel("Seleziona Volo:"), c);
        c.gridx = 1;
        formPanel.add(voloJComboBox, c);

        c.gridx = 0;
        c.gridy = 1;
        formPanel.add(new JLabel("Stato Volo:"), c);
        c.gridx = 1;
        formPanel.add(statoBox, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        formPanel.add(confermaButton, c);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);

        controller = new ModificaVoloController(voloJComboBox, statoBox, this);
        confermaButton.addActionListener(e -> controller.aggiornaStatoVolo());

        setVisible(true);
    }

    /**
     * Applica uno stile personalizzato al pulsante fornito.
     * <p>
     * Il metodo imposta font, colori, bordo e cursore del pulsante, e aggiunge
     * un effetto visivo al passaggio del mouse per migliorare l'interazione utente.
     *
     * @param button il pulsante da stilizzare
     */
    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);
        button.setBackground(new Color(240, 240, 255));
        button.setForeground(new Color(40, 40, 80));
        button.setFont(new Font("SansSerif", Font.PLAIN, 13));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 220)),
                BorderFactory.createEmptyBorder(8, 16, 8, 16)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(220, 220, 250));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(240, 240, 255));
            }
        });
    }

    /**
     * Metodo principale di avvio dell'applicazione.
     * <p>
     * Avvia l'interfaccia grafica per la modifica dello stato di un volo
     * in un thread dedicato alla gestione dell'interfaccia utente Swing.
     *
     * @param args gli argomenti da riga di comando (non utilizzati)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ModificaVolo::new);
    }
}
