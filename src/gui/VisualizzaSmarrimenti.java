package gui;

import controller.VisualizzaSmarrimentiController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * La classe {@code VisualizzaSmarrimenti} rappresenta l'interfaccia grafica
 * per la visualizzazione dei bagagli smarriti associati ai voli.
 * <p>
 * L'interfaccia consente all'operatore di:
 * <ul>
 *     <li>Visualizzare l'elenco dei bagagli segnalati come smarriti</li>
 *     <li>Consultare informazioni come volo, codice bagaglio, descrizione e stato</li>
 * </ul>
 * I dati vengono caricati tramite il {@link VisualizzaSmarrimentiController}
 * e mostrati in una tabella stilizzata.
 *
 */
public class VisualizzaSmarrimenti extends JFrame {

    private JTable tabellaSmarrimenti;
    private DefaultTableModel tableModel;
    private VisualizzaSmarrimentiController controller;
    /**
     * Costruttore della finestra {@code VisualizzaSmarrimenti}.
     * <p>
     * Inizializza l'interfaccia grafica, configura la tabella e carica i dati
     * dei bagagli smarriti tramite il controller.
     */
    public VisualizzaSmarrimenti() {
        setTitle("Bagagli Smarriti");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(Color.WHITE);

        JLabel titolo = new JLabel("Bagagli Smarriti");
        titolo.setFont(new Font("Montserrat", Font.BOLD, 24));
        titolo.setForeground(new Color(19, 8, 102));
        titolo.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titolo, BorderLayout.NORTH);

        String[] colonne = {"Volo", "Codice", "Descrizione", "Stato"};
        tableModel = new DefaultTableModel(colonne, 0);
        tabellaSmarrimenti = new JTable(tableModel);
        tabellaSmarrimenti.setRowHeight(22);
        tabellaSmarrimenti.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tabellaSmarrimenti.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        tabellaSmarrimenti.getTableHeader().setBackground(new Color(230, 230, 240));
        tabellaSmarrimenti.getTableHeader().setForeground(new Color(50, 50, 80));
        tabellaSmarrimenti.setGridColor(new Color(220, 220, 220));
        tabellaSmarrimenti.setShowVerticalLines(false);
        tabellaSmarrimenti.setShowHorizontalLines(true);
        tabellaSmarrimenti.setIntercellSpacing(new Dimension(0, 1));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabellaSmarrimenti.getColumnCount(); i++) {
            tabellaSmarrimenti.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(tabellaSmarrimenti);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                "Elenco Bagagli",
                0,
                0,
                new Font("SansSerif", Font.BOLD, 14),
                new Color(19, 8, 102)
        ));
        scrollPane.setPreferredSize(new Dimension(600, 300));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        controller = new VisualizzaSmarrimentiController(tableModel);
        controller.caricaBagagliSmarriti();

        add(mainPanel);
        setVisible(true);
    }
}