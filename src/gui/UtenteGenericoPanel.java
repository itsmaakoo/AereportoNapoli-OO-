package gui;

import controller.StatoVoloController;
import controller.UtenteGenericoController;
import model.Volo;
import model.Prenotazione;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * La classe {@code UtenteGenericoPanel} rappresenta l'interfaccia grafica dedicata
 * all'utente generico del sistema aeroportuale.
 * <p>
 * Consente di accedere a diverse funzionalità come:
 * <ul>
 *     <li>Prenotazione di voli</li>
 *     <li>Visualizzazione dello stato dei voli</li>
 *     <li>Gestione dei bagagli</li>
 *     <li>Consultazione delle prenotazioni</li>
 *     <li>Logout dal sistema</li>
 * </ul>
 * L'interfaccia è composta da un pannello laterale con pulsanti e un pannello centrale
 * che mostra una tabella con i voli programmati.
 */

public class UtenteGenericoPanel extends JFrame {

    private final UtenteGenericoController controller = new UtenteGenericoController();
    /**
     * Costruttore della finestra {@code UtenteGenericoPanel}.
     * <p>
     * Inizializza l'interfaccia grafica, imposta lo sfondo, configura i pulsanti
     * e visualizza la tabella dei voli programmati.
     */
    public UtenteGenericoPanel() {
        super("Utente Generico");

        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon(getClass().getResource("/gui/Opera_senza_titolo copia 2.jpg"));
        Image scaledImage = icon.getImage().getScaledInstance(900, 500, Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(scaledImage));
        backgroundLabel.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 30));
        leftPanel.setPreferredSize(new Dimension(320, getHeight()));

        JLabel saluto = new JLabel("Benvenuto!");
        saluto.setFont(new Font("Montserrat", Font.BOLD, 30));
        saluto.setForeground(new Color(19, 8, 102));
        saluto.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(saluto);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        leftPanel.add(createButtonWithSpacing("Prenota volo", e -> controller.apriPrenotaVolo()));
        leftPanel.add(createButtonWithSpacing("Stato volo", e -> controller.apriStatoVolo()));
        leftPanel.add(createButtonWithSpacing("Aggiungi bagaglio", e -> controller.apriAggiungiBagaglio()));
        leftPanel.add(createButtonWithSpacing("Segnala bagaglio smarrito", e -> controller.apriSegnalaBagaglioSmarrito()));
        leftPanel.add(createButtonWithSpacing("Le mie prenotazioni", e -> apriVisualizzaPrenotazioni()));

        JButton logoutButton = createButton("Logout", e -> {
            controller.logout();
            dispose();
        });
        logoutButton.setForeground(Color.red);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        leftPanel.add(logoutButton);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setOpaque(false);
        rightPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        rightPanel.add(creaTabellaVoli(), BorderLayout.CENTER);

        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);
        backgroundLabel.add(mainPanel);
        setContentPane(backgroundLabel);
        setVisible(true);
    }
    /**
     * Apre la finestra di visualizzazione delle prenotazioni dell'utente.
     */
    private void apriVisualizzaPrenotazioni() {
        List<Prenotazione> prenotazioni = controller.recuperaPrenotazioniUtente();
        new VisualizzaPrenotazioni(prenotazioni);
    }
    /**
     * Crea un componente contenente un pulsante con uno spazio verticale inferiore.
     *
     * @param text   il testo da visualizzare sul pulsante
     * @param action l'azione da eseguire al clic del pulsante
     * @return un componente contenente il pulsante e uno spazio vuoto
     */
    private Component createButtonWithSpacing(String text, ActionListener action) {
        JButton button = createButton(text, action);
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setOpaque(false);
        wrapper.add(button);
        wrapper.add(Box.createRigidArea(new Dimension(0, 15)));
        return wrapper;
    }
    /**
     * Crea un pulsante stilizzato con comportamento interattivo al passaggio del mouse.
     *
     * @param text   il testo da visualizzare sul pulsante
     * @param action l'azione da eseguire al clic del pulsante
     * @return il pulsante configurato
     */
    private JButton createButton(String text, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
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
        button.setMaximumSize(new Dimension(250, 40));
        button.setPreferredSize(new Dimension(250, 40));
        button.addActionListener(action);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(220, 220, 250));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(240, 240, 255));
            }
        });

        return button;
    }
    /**
     * Crea un pannello contenente la tabella dei voli programmati.
     * <p>
     * I dati vengono recuperati dal {@link StatoVoloController} e visualizzati in una tabella
     * con intestazioni e stile personalizzato.
     *
     * @return il pannello con la tabella dei voli
     */
    private JPanel creaTabellaVoli() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] colonne = {"Destinazione", "Partenza", "Gate", "Tipo", "Stato"};
        DefaultTableModel modello = new DefaultTableModel(colonne, 0);

        StatoVoloController controller = new StatoVoloController();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        try {
            List<Volo> voli = controller.recuperaTuttiIVoli();
            for (Volo v : voli) {
                String partenza = (v.getDataPartenza() != null) ? sdf.format(v.getDataPartenza()) : "N/D";
                Object[] riga = {
                        v.getDestinazione(),
                        partenza,
                        v.getGate(),
                        v.getTipo(),
                        v.getStato()
                };
                modello.addRow(riga);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Errore nel caricamento dei voli.");
        }

        JTable tabella = new JTable(modello);
        tabella.setRowHeight(20);
        tabella.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tabella.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        tabella.getTableHeader().setBackground(new Color(230, 230, 240));
        tabella.getTableHeader().setForeground(new Color(50, 50, 80));
        tabella.setGridColor(new Color(220, 220, 220));
        tabella.setShowVerticalLines(false);
        tabella.setShowHorizontalLines(true);
        tabella.setIntercellSpacing(new Dimension(0, 1));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabella.getColumnCount(); i++) {
            tabella.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            tabella.getColumnModel().getColumn(i).setPreferredWidth(100);
        }

        JScrollPane scrollPane = new JScrollPane(tabella);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                "Voli Programmati",
                0,
                0,
                new Font("SansSerif", Font.BOLD, 14),
                new Color(19, 8, 102)
        ));
        scrollPane.setBackground(Color.WHITE);

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Metodo principale di avvio dell'applicazione.
     * <p>
     * Avvia l'interfaccia grafica dell'utente generico in un thread dedicato
     * alla gestione dell'interfaccia utente Swing.
     *
     * @param args gli argomenti da riga di comando (non utilizzati)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(UtenteGenericoPanel::new);
    }
}