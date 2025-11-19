package gui;

import controller.VoloArrivoController;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

/**
 * La classe {@code InserisciVoloArrivo} rappresenta l'interfaccia grafica per l'inserimento
 * di un volo in arrivo nel sistema aeroportuale.
 * <p>
 * L'interfaccia consente all'operatore di:
 * <ul>
 *     <li>Inserire la destinazione del volo</li>
 *     <li>Specificare il gate di arrivo</li>
 *     <li>Selezionare lo stato del volo (in arrivo, in ritardo, cancellato)</li>
 *     <li>Impostare data e ora di arrivo</li>
 *     <li>Salvare il volo tramite un pulsante di conferma</li>
 * </ul>
 * <p>
 * La logica applicativa è gestita dal {@link controller.VoloArrivoController}, che
 * si occupa della validazione e del salvataggio dei dati nel sistema.
 *
 */
public class InserisciVoloArrivo extends JFrame {

    /**
     * Costruttore della finestra {@code InserisciVoloArrivo}.
     * <p>
     * Inizializza l'interfaccia grafica, configura i campi di input e collega
     * il pulsante di salvataggio all'azione di registrazione del volo in arrivo.
     */
    public InserisciVoloArrivo() {
        super("Inserisci Volo in Arrivo");

        setSize(550, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(Color.WHITE);

        JLabel titolo = new JLabel("Inserisci Volo in Arrivo");
        titolo.setFont(new Font("Montserrat", Font.BOLD, 24));
        titolo.setForeground(new Color(19, 8, 102));
        titolo.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titolo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField destinazioneField = new JTextField();
        JTextField gateField = new JTextField();
        JComboBox<String> statoBox = new JComboBox<>(new String[]{"In Arrivo", "In Ritardo", "Cancellato"});

        JSpinner dataSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dataEditor = new JSpinner.DateEditor(dataSpinner, "dd/MM/yyyy");
        dataSpinner.setEditor(dataEditor);

        JSpinner oraSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor oraEditor = new JSpinner.DateEditor(oraSpinner, "HH:mm");
        oraSpinner.setEditor(oraEditor);

        JButton salvaButton = new JButton("Salva Volo");
        styleButton(salvaButton);

        // Righe del form
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Volo in arrivo da:"), gbc);
        gbc.gridx = 1;
        formPanel.add(destinazioneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Gate:"), gbc);
        gbc.gridx = 1;
        formPanel.add(gateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Stato:"), gbc);
        gbc.gridx = 1;
        formPanel.add(statoBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Data Arrivo:"), gbc);
        gbc.gridx = 1;
        formPanel.add(dataSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Ora Arrivo:"), gbc);
        gbc.gridx = 1;
        formPanel.add(oraSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(salvaButton, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);

        salvaButton.addActionListener(e -> {
            String destinazione = destinazioneField.getText().trim();
            String gate = gateField.getText().trim();
            String stato = (String) statoBox.getSelectedItem();

            Date data = (Date) dataSpinner.getValue();
            Date ora = (Date) oraSpinner.getValue();

            Calendar calData = Calendar.getInstance();
            calData.setTime(data);

            Calendar calOra = Calendar.getInstance();
            calOra.setTime(ora);

            calData.set(Calendar.HOUR_OF_DAY, calOra.get(Calendar.HOUR_OF_DAY));
            calData.set(Calendar.MINUTE, calOra.get(Calendar.MINUTE));
            calData.set(Calendar.SECOND, 0);
            calData.set(Calendar.MILLISECOND, 0);

            Date dataArrivoCompleta = calData.getTime();

            VoloArrivoController controller = new VoloArrivoController();
            boolean success = controller.salvaVoloArrivo(destinazione, dataArrivoCompleta, stato, gate);

            if (success) {
                JOptionPane.showMessageDialog(this, "Volo in arrivo salvato con successo!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Errore nel salvataggio del volo.");
            }
        });

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
}

