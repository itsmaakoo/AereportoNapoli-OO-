package gui;

import controller.BagaglioSmarritoController;
import model.Bagaglio;
import model.Volo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * La classe {@code BagaglioSmarrito} rappresenta l'interfaccia grafica per la segnalazione
 * di un bagaglio smarrito associato a un volo specifico.
 * <p>
 * L'interfaccia consente all'operatore aeroportuale di:
 * <ul>
 * <li>Selezionare un volo dall'archivio disponibile</li>
 * <li>Visualizzare i bagagli associati a quel volo</li>
 * <li>Segnalare un bagaglio come smarrito tramite un pulsante dedicato</li>
 * </ul>
 * <p>
 * La logica applicativa è gestita dal {@link controller.BagaglioSmarritoController}, che
 * si occupa del caricamento dei voli, del filtraggio dei bagagli e della gestione della segnalazione.
 * <p>
 * L'interfaccia è costruita con componenti Swing come {@link JComboBox}, {@link JButton} e {@link JPanel},
 * ed è progettata con uno stile coerente e responsivo per migliorare l'usabilità.
 *
 * @see controller.BagaglioSmarritoController
 * @see model.Bagaglio
 * @see model.Volo
 */
public class BagaglioSmarrito extends JFrame {
    private JComboBox<Volo> voloJComboBox;
    private JComboBox<Bagaglio> bagaglioJComboBox;
    private JButton segnalaBagaglioButton;
    private BagaglioSmarritoController controller;

    /**
     * Costruttore della finestra {@code BagaglioSmarrito}.
     * <p>
     * Inizializza l'interfaccia grafica, configura i componenti e collega
     * le azioni ai metodi del controller per la gestione dei bagagli smarriti.
     */
    public BagaglioSmarrito() {
        setTitle("Segnala Bagaglio Smarrito");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        controller = new BagaglioSmarritoController();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(Color.WHITE);

        JLabel titolo = new JLabel("Segnala Bagaglio Smarrito");
        titolo.setFont(new Font("Montserrat", Font.BOLD, 24));
        titolo.setForeground(new Color(19, 8, 102));
        titolo.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titolo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        voloJComboBox = new JComboBox<>();
        voloJComboBox.setFont(new Font("SansSerif", Font.PLAIN, 13));
        voloJComboBox.setPreferredSize(new Dimension(300, 30));
        voloJComboBox.setBackground(Color.WHITE);
        voloJComboBox.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 220)));

        bagaglioJComboBox = new JComboBox<>();
        bagaglioJComboBox.setFont(new Font("SansSerif", Font.PLAIN, 13));
        bagaglioJComboBox.setPreferredSize(new Dimension(300, 30));
        bagaglioJComboBox.setBackground(Color.WHITE);
        bagaglioJComboBox.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 220)));

        segnalaBagaglioButton = new JButton("Segnala Bagaglio");
        styleButton(segnalaBagaglioButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Seleziona Volo:"), gbc);
        gbc.gridx = 1;
        formPanel.add(voloJComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Seleziona Bagaglio:"), gbc);
        gbc.gridx = 1;
        formPanel.add(bagaglioJComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(segnalaBagaglioButton, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);




        controller.caricaVoli(voloJComboBox, this);


        ActionListener cambioVoloListener = controller.getCambioVoloListener(voloJComboBox, bagaglioJComboBox, this);


        voloJComboBox.addActionListener(cambioVoloListener);
        segnalaBagaglioButton.addActionListener(controller.getSegnalaBagaglioListener(voloJComboBox, bagaglioJComboBox, this));


        if (voloJComboBox.getItemCount() > 0) {
            voloJComboBox.setSelectedIndex(0); // Assicura selezione visiva del primo elemento
            cambioVoloListener.actionPerformed(null); // Scatena l'evento manualmente
        }

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