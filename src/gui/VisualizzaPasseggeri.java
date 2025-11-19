package gui;

import controller.VisualizzaPasseggeriController;
import dao.VoloDAO;
import db.ConnessioneDB;
import model.Volo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * La classe {@code VisualizzaPasseggeri} rappresenta l'interfaccia grafica per la visualizzazione
 * dei passeggeri associati a un volo selezionato.
 * <p>
 * L'interfaccia consente all'operatore di:
 * <ul>
 *     <li>Selezionare un volo da una lista</li>
 *     <li>Visualizzare l'elenco dei passeggeri associati a quel volo</li>
 * </ul>
 * I dati vengono recuperati tramite il {@link VisualizzaPasseggeriController} e il {@link VoloDAO}.
 * La tabella è stilizzata per una visualizzazione chiara e ordinata.
 *
 */
public class VisualizzaPasseggeri extends JFrame {
    private JComboBox<Volo> voloComboBox;
    private JTable table;
    private DefaultTableModel model;
    private VisualizzaPasseggeriController controller;

    /**
     * Costruttore della finestra {@code VisualizzaPasseggeri}.
     * <p>
     * Inizializza l'interfaccia grafica, configura i componenti e carica i voli disponibili.
     * Al cambio di selezione del volo, aggiorna la tabella con i passeggeri associati.
     */
    public VisualizzaPasseggeri() {
        setTitle("Visualizza Passeggeri per Volo");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(Color.WHITE);

        JLabel titolo = new JLabel("Passeggeri per Volo");
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

        topPanel.add(label);
        topPanel.add(voloComboBox);
        mainPanel.add(topPanel, BorderLayout.CENTER);

        String[] colonne = {"ID Volo", "Nome", "Cognome", "Codice Fiscale"};
        model = new DefaultTableModel(colonne, 0);
        table = new JTable(model);
        table.setRowHeight(22);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(230, 230, 240));
        table.getTableHeader().setForeground(new Color(50, 50, 80));
        table.setGridColor(new Color(220, 220, 220));
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(true);
        table.setIntercellSpacing(new Dimension(0, 1));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                "Elenco Passeggeri",
                0,
                0,
                new Font("SansSerif", Font.BOLD, 14),
                new Color(19, 8, 102)
        ));
        scrollPane.setPreferredSize(new Dimension(600, 250));
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        controller = new VisualizzaPasseggeriController(model);

        try (Connection conn = ConnessioneDB.getConnection()) {
            VoloDAO voloDAO = new VoloDAO(conn);
            List<Volo> voli = voloDAO.getTuttiVoli();
            for (Volo v : voli) {
                voloComboBox.addItem(v);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Errore nel caricamento dei voli");
            e.printStackTrace();
        }

        voloComboBox.addActionListener(e -> {
            Volo voloSelezionato = (Volo) voloComboBox.getSelectedItem();
            if (voloSelezionato != null) {
                controller.caricaPasseggeriPerVolo(String.valueOf(voloSelezionato.getId()));
            }
        });

        add(mainPanel);
        setVisible(true);
    }
}