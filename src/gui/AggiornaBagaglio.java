package gui;

import controller.AggiornaBagaglioController;
import model.Volo;
import model.Bagaglio;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * La classe {@code AggiornaBagaglio} rappresenta una finestra grafica
 * il quale consente di visualizzare i bagagli associati a un volo selezionato.
 * <p>
 * L'interfaccia possiede una combo box per la selezione del volo e una tabella
 * per mostrare tutti i dettagli dei bagagli. I dati, poi vengono recuperati tramite
 * {@link AggiornaBagaglioController}.
 */
public class AggiornaBagaglio extends JFrame {

    /** Tabella per visualizzare i bagagli. */
    private JTable bagagliTable;

    /** ComboBox per selezionare il volo. */
    private JComboBox<Volo> voloComboBox;

    /** Modello della tabella per gestire i dati dei bagagli. */
    private DefaultTableModel tableModel;

    /** Controller per la logica di recupero dei dati. */
    private AggiornaBagaglioController controller;

    /**
     * Costruttore della finestra {@code AggiornaBagaglio}.
     * <p>
     * Inizializza l'interfaccia grafica, imposta lo stile dei componenti
     * e carica l'elenco dei voli disponibili nel {@code JComboBox}.
     */
    public AggiornaBagaglio() {
        setTitle("Visualizza Bagagli per Volo");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        controller = new AggiornaBagaglioController();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(Color.WHITE);

        JLabel titolo = new JLabel("Bagagli per Volo");
        titolo.setFont(new Font("Montserrat", Font.BOLD, 24));
        titolo.setForeground(new Color(19, 8, 102));
        titolo.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titolo, BorderLayout.NORTH);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.WHITE);
        JLabel label = new JLabel("Seleziona Volo:");
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label.setForeground(new Color(40, 40, 80));
        voloComboBox = new JComboBox<>();
        voloComboBox.setFont(new Font("SansSerif", Font.PLAIN, 13));
        voloComboBox.setPreferredSize(new Dimension(300, 30));
        voloComboBox.setBackground(Color.WHITE);
        voloComboBox.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 220)));

        DefaultComboBoxModel<Volo> model = new DefaultComboBoxModel<>();
        List<Volo> voli = controller.recuperaVoli();
        for (Volo volo : voli) {
            model.addElement(volo);
        }
        voloComboBox.setModel(model);

        topPanel.add(label);
        topPanel.add(voloComboBox);
        mainPanel.add(topPanel, BorderLayout.CENTER);

        String[] colonne = {"Volo", "Codice Bagaglio", "Descrizione", "Stato"};
        tableModel = new DefaultTableModel(colonne, 0);
        bagagliTable = new JTable(tableModel);
        bagagliTable.setRowHeight(22);
        bagagliTable.setFont(new Font("SansSerif", Font.PLAIN, 13));
        bagagliTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        bagagliTable.getTableHeader().setBackground(new Color(230, 230, 240));
        bagagliTable.getTableHeader().setForeground(new Color(50, 50, 80));
        bagagliTable.setGridColor(new Color(220, 220, 220));
        bagagliTable.setShowVerticalLines(false);
        bagagliTable.setShowHorizontalLines(true);
        bagagliTable.setIntercellSpacing(new Dimension(0, 1));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < bagagliTable.getColumnCount(); i++) {
            bagagliTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(bagagliTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                "Elenco Bagagli",
                0,
                0,
                new Font("SansSerif", Font.BOLD, 14),
                new Color(19, 8, 102)
        ));
        scrollPane.setPreferredSize(new Dimension(600, 300));
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        // Listener per aggiornare la tabella in base al volo selezionato
        voloComboBox.addActionListener(e -> caricaBagagli());

        add(mainPanel);
        setVisible(true);
    }

    /**
     * Carica i bagagli associati al volo selezionato.
     * <p>
     * Recupera i dati dal controller e li inserisce nella tabella.
     * Se non sono presenti bagagli, mostra un messaggio informativo.
     */
    private void caricaBagagli() {
        tableModel.setNumRows(0);
        Volo selezionato = (Volo) voloComboBox.getSelectedItem();
        if (selezionato == null) return;

        List<Bagaglio> bagagli = controller.recuperaBagagliPerVolo(selezionato.getId());
        if (bagagli.isEmpty()) {
            if (this.isShowing()) {
                JOptionPane.showMessageDialog(this, "Nessun bagaglio trovato per questo volo.");
            }
        }
        for (Bagaglio b : bagagli) {
            Object[] row = {
                    selezionato.toString(),
                    b.getCodice(),
                    b.getDescrizione(),
                    b.getStato()
            };
            tableModel.addRow(row);
        }
    }
}


