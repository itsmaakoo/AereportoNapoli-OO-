package gui;

import controller.InserisciVoloController;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

/**
 * La classe {@code InserisciVolo} rappresenta l'interfaccia grafica per l'inserimento
 * di un nuovo volo nel sistema aeroportuale.
 * <p>
 * L'interfaccia consente all'operatore di:
 * <ul>
 *     <li>Inserire la destinazione del volo</li>
 *     <li>Selezionare la data e l'orario di partenza</li>
 *     <li>Selezionare la data e l'orario di arrivo</li>
 *     <li>Salvare il volo tramite un pulsante di conferma</li>
 * </ul>
 * <p>
 * La logica applicativa è gestita dal {@link controller.InserisciVoloController}, che
 * si occupa della validazione e del salvataggio dei dati nel sistema.
 *
 * @author Sistema
 * @version 1.0
 */
public class InserisciVolo extends JFrame {

    private JTextField destinazioneField;
    private JSpinner dataPartenzaSpinner, orarioPartenzaSpinner;
    private JSpinner dataArrivoSpinner, orarioArrivoSpinner;
    private JButton confermaButton;
    private InserisciVoloController controller;

    /**
     * Costruttore della finestra {@code InserisciVolo}.
     * <p>
     * Inizializza l'interfaccia grafica, configura i campi di input e collega
     * il pulsante di conferma all'azione di salvataggio del volo.
     */
    public InserisciVolo() {
        setTitle("Inserisci Volo");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        controller = new InserisciVoloController();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(Color.WHITE);

        JLabel titolo = new JLabel("Inserisci Nuovo Volo");
        titolo.setFont(new Font("Montserrat", Font.BOLD, 24));
        titolo.setForeground(new Color(19, 8, 102));
        titolo.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titolo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(12, 12, 12, 12);
        c.fill = GridBagConstraints.HORIZONTAL;

        destinazioneField = new JTextField();
        destinazioneField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        destinazioneField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 220)));

        dataPartenzaSpinner = new JSpinner(new SpinnerDateModel());
        dataPartenzaSpinner.setEditor(new JSpinner.DateEditor(dataPartenzaSpinner, "dd/MM/yyyy"));
        dataPartenzaSpinner.setFont(new Font("SansSerif", Font.PLAIN, 13));
        dataPartenzaSpinner.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 220)));

        orarioPartenzaSpinner = new JSpinner(new SpinnerDateModel());
        orarioPartenzaSpinner.setEditor(new JSpinner.DateEditor(orarioPartenzaSpinner, "HH:mm"));
        orarioPartenzaSpinner.setFont(new Font("SansSerif", Font.PLAIN, 13));
        orarioPartenzaSpinner.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 220)));

        dataArrivoSpinner = new JSpinner(new SpinnerDateModel());
        dataArrivoSpinner.setEditor(new JSpinner.DateEditor(dataArrivoSpinner, "dd/MM/yyyy"));
        dataArrivoSpinner.setFont(new Font("SansSerif", Font.PLAIN, 13));
        dataArrivoSpinner.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 220)));

        orarioArrivoSpinner = new JSpinner(new SpinnerDateModel());
        orarioArrivoSpinner.setEditor(new JSpinner.DateEditor(orarioArrivoSpinner, "HH:mm"));
        orarioArrivoSpinner.setFont(new Font("SansSerif", Font.PLAIN, 13));
        orarioArrivoSpinner.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 220)));

        confermaButton = new JButton("Salva Volo");
        styleButton(confermaButton);

        c.gridx = 0;
        c.gridy = 0;
        formPanel.add(new JLabel("Destinazione:"), c);
        c.gridx = 1;
        formPanel.add(destinazioneField, c);

        c.gridx = 0;
        c.gridy = 1;
        formPanel.add(new JLabel("Data Partenza:"), c);
        c.gridx = 1;
        formPanel.add(dataPartenzaSpinner, c);

        c.gridx = 0;
        c.gridy = 2;
        formPanel.add(new JLabel("Orario Partenza:"), c);
        c.gridx = 1;
        formPanel.add(orarioPartenzaSpinner, c);

        c.gridx = 0;
        c.gridy = 3;
        formPanel.add(new JLabel("Data Arrivo:"), c);
        c.gridx = 1;
        formPanel.add(dataArrivoSpinner, c);

        c.gridx = 0;
        c.gridy = 4;
        formPanel.add(new JLabel("Orario Arrivo:"), c);
        c.gridx = 1;
        formPanel.add(orarioArrivoSpinner, c);

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        formPanel.add(confermaButton, c);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);

        confermaButton.addActionListener(e -> salvaVolo());

        setVisible(true);
    }

    /**
     * Salva il volo inserito dall'utente.
     * <p>
     * Combina data e orario di partenza e arrivo, valida i dati e invia
     * la richiesta al controller per il salvataggio. Mostra un messaggio
     * di conferma o errore in base all'esito.
     */
    private void salvaVolo() {
        String destinazione = destinazioneField.getText().trim();
        Date dataPartenza = combinaDataOra((Date) dataPartenzaSpinner.getValue(), (Date) orarioPartenzaSpinner.getValue());
        Date dataArrivo = combinaDataOra((Date) dataArrivoSpinner.getValue(), (Date) orarioArrivoSpinner.getValue());

        boolean success = controller.salvaVolo(destinazione, dataPartenza, dataArrivo);

        if (success) {
            JOptionPane.showMessageDialog(this, "Volo inserito correttamente.");
            destinazioneField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Errore: verifica i dati o la connessione.");
        }
    }

    /**
     * Combina una data e un orario in un unico oggetto {@link Date}.
     *
     * @param data la data selezionata
     * @param ora  l'orario selezionato
     * @return un oggetto {@code Date} che rappresenta la combinazione di data e ora
     */
    private Date combinaDataOra(Date data, Date ora) {
        Calendar calData = Calendar.getInstance();
        calData.setTime(data);

        Calendar calOra = Calendar.getInstance();
        calOra.setTime(ora);

        calData.set(Calendar.HOUR_OF_DAY, calOra.get(Calendar.HOUR_OF_DAY));
        calData.set(Calendar.MINUTE, calOra.get(Calendar.MINUTE));

        return calData.getTime();
    }

    /**
     * Applica uno stile personalizzato al pulsante fornito.
     * <p>
     * Include font, colori, bordo e comportamento al passaggio del mouse.
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
     * Avvia l'interfaccia grafica per l'inserimento di un volo in un thread dedicato
     * alla gestione dell'interfaccia utente Swing.
     *
     * @param args gli argomenti da riga di comando (non utilizzati)
     */
    public static void main(String[] args) {
    }}