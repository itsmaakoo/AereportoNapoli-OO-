package gui;

import controller.AmministratoreController;
import javax.swing.*;
import java.awt.*;

/**
 * La classe {@code AmministratoreGUI} rappresenta l'interfaccia grafica principale
 * dedicata all'amministratore del sistema aeroportuale.
 * <p>
 * Consente l'accesso a diverse funzionalità amministrative come la gestione dei voli,
 * l'assegnazione dei gate, la visualizzazione dei passeggeri e dei bagagli, e il logout.
 * L'interfaccia è composta da un pannello laterale con pulsanti e uno sfondo personalizzato.
 */
public class AmministratoreGUI extends JFrame {

    /** Controller che gestisce le azioni dell'amministratore. */
    private final AmministratoreController controller = new AmministratoreController();

    /**
     * Costruttore della finestra {@code AmministratoreGUI}.
     * <p>
     * Inizializza la finestra principale, imposta lo sfondo, crea il layout
     * con i pulsanti di navigazione e collega le azioni ai metodi del controller.
     */
    public AmministratoreGUI() {
        super("Amministratore");

        setSize(1100, 650); // Finestra ampia
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Caricamento e ridimensionamento dell'immagine di sfondo
        ImageIcon icon = new ImageIcon(getClass().getResource("/gui/Opera_senza_titolo copia 2.jpg"));
        Image scaledImage = icon.getImage().getScaledInstance(1100, 650, Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(scaledImage));
        backgroundLabel.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);

        // Pannello laterale sinistro con pulsanti
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 30));
        leftPanel.setPreferredSize(new Dimension(320, 650));

        JLabel saluto = new JLabel("Benvenuto!");
        saluto.setFont(new Font("Montserrat", Font.BOLD, 30));
        saluto.setForeground(new Color(19, 8, 102));
        saluto.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(saluto);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Aggiunta dei pulsanti con relative azioni
        leftPanel.add(createButtonWithSpacing("Inserisci volo in partenza", e -> controller.apriInserisciVolo()));
        leftPanel.add(createButtonWithSpacing("Inserisci volo in arrivo", e -> controller.apriInserisciVoloArrivo()));
        leftPanel.add(createButtonWithSpacing("Assegna gate", e -> controller.apriAssegnaGate()));
        leftPanel.add(createButtonWithSpacing("Visualizza smarrimenti", e -> controller.apriVisualizzaSmarrimenti()));
        leftPanel.add(createButtonWithSpacing("Modifica volo", e -> controller.apriModificaVolo()));
        leftPanel.add(createButtonWithSpacing("Aggiorna bagaglio", e -> controller.apriAggiornaBagaglio()));
        leftPanel.add(createButtonWithSpacing("Visualizza passeggero", e -> controller.apriVisualizzaPasseggeri()));

        // Pulsante di logout
        JButton logoutButton = createButton("Logout", e -> {
            controller.logout();
            dispose();
        });
        logoutButton.setForeground(Color.red);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        leftPanel.add(logoutButton);

        // Pannello destro vuoto per espansione futura
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setOpaque(false);
        rightPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);
        backgroundLabel.add(mainPanel);
        setContentPane(backgroundLabel);
        setVisible(true);
    }

    /**
     * Crea un componente contenente un pulsante con uno spazio verticale inferiore.
     * <p>
     * Utile per mantenere una distanza uniforme tra i pulsanti nel layout verticale.
     *
     * @param text   il testo da visualizzare sul pulsante
     * @param action l'azione da eseguire al clic del pulsante
     * @return un componente contenente il pulsante e uno spazio vuoto
     */
    private Component createButtonWithSpacing(String text, java.awt.event.ActionListener action) {
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
     * <p>
     * Il pulsante è personalizzato con colori, font, bordo e cursore. Include
     * un effetto hover per migliorare l'esperienza utente.
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

        // Effetto hover al passaggio del mouse
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
     * Metodo principale di avvio dell'applicazione.
     * <p>
     * Avvia l'interfaccia grafica dell'amministratore in un thread dedicato
     * alla gestione dell'interfaccia utente Swing.
     *
     * @param args gli argomenti passati da riga di comando (non utilizzati)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AmministratoreGUI::new);
    }
}
