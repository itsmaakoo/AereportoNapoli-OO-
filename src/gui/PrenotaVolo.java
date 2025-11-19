package gui;

import controller.PrenotaVoloController;
import model.Volo;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

/**
 * La classe {@code PrenotaVolo} rappresenta l'interfaccia grafica per la prenotazione
 * di un volo da parte di un passeggero.
 * <p>
 * L'interfaccia consente all'utente di:
 * <ul>
 *     <li>Inserire nome, cognome e codice fiscale</li>
 *     <li>Selezionare un volo disponibile</li>
 *     <li>Effettuare il check-in e confermare la prenotazione</li>
 * </ul>
 * <p>
 * La logica applicativa è gestita dal {@link controller.PrenotaVoloController}, che
 * si occupa della validazione e del salvataggio dei dati nel sistema.
 *
 */
public class PrenotaVolo extends JFrame {

    private JTextField nomeField, cognomeField, codiceFiscale;
    private JComboBox<Volo> voloComboBox;
    private PrenotaVoloController controller;

    /**
     * Costruttore della finestra {@code PrenotaVolo}.
     * <p>
     * Inizializza l'interfaccia grafica, configura i campi di input e collega
     * il pulsante di check-in all'azione di prenotazione del volo.
     */
    public PrenotaVolo() {
        setTitle("Prenotazione Volo");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        controller = new PrenotaVoloController();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(Color.WHITE);

        JLabel titolo = new JLabel("Prenotazione Volo");
        titolo.setFont(new Font("Montserrat", Font.BOLD, 24));
        titolo.setForeground(new Color(19, 8, 102));
        titolo.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titolo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(12, 12, 12, 12);
        c.fill = GridBagConstraints.HORIZONTAL;

        nomeField = new JTextField(20);
        cognomeField = new JTextField(20);
        codiceFiscale = new JTextField(20);

        voloComboBox = new JComboBox<>();
        voloComboBox.setFont(new Font("SansSerif", Font.PLAIN, 13));
        voloComboBox.setPreferredSize(new Dimension(300, 30));
        voloComboBox.setBackground(Color.WHITE);
        voloComboBox.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 220)));

        List<Volo> voliPrenotabili = controller.getVoliPrenotabili();
        for (Volo v : voliPrenotabili) {
            voloComboBox.addItem(v);
        }

        c.gridx = 0;
        c.gridy = 0;
        formPanel.add(new JLabel("Nome:"), c);
        c.gridx = 1;
        formPanel.add(nomeField, c);

        c.gridx = 0;
        c.gridy = 1;
        formPanel.add(new JLabel("Cognome:"), c);
        c.gridx = 1;
        formPanel.add(cognomeField, c);

        c.gridx = 0;
        c.gridy = 2;
        formPanel.add(new JLabel("Codice Fiscale:"), c);
        c.gridx = 1;
        formPanel.add(codiceFiscale, c);

        c.gridx = 0;
        c.gridy = 3;
        formPanel.add(new JLabel("Seleziona Volo:"), c);
        c.gridx = 1;
        formPanel.add(voloComboBox, c);

        JButton checkInButton = new JButton("Effettua Check-in");
        styleButton(checkInButton);
        checkInButton.addActionListener(e -> {
            try {
                prenota();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        formPanel.add(checkInButton, c);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }

    /**
     * Esegue la prenotazione del volo selezionato.
     * <p>
     * Recupera i dati inseriti dall'utente, li valida e invia la richiesta
     * al controller. Mostra un messaggio di conferma o errore in base all'esito.
     *
     * @throws SQLException se si verifica un errore durante la comunicazione con il database
     */
    private void prenota() throws SQLException {
        Volo selezionato = (Volo) voloComboBox.getSelectedItem();
        String nome = nomeField.getText().trim();
        String cognome = cognomeField.getText().trim();
        String cf = codiceFiscale.getText().trim();

        boolean success = controller.prenotaVolo(nome, cognome, cf, selezionato);
        if (success) {
            JOptionPane.showMessageDialog(this, "Prenotazione confermata");
            nomeField.setText("");
            cognomeField.setText("");
            codiceFiscale.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Errore durante la prenotazione. Controlla i dati.");
        }
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
}

