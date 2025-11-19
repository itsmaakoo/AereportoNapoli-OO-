package gui;

import controller.StatoVoloController;
import model.Volo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * La classe {@code StatoVolo} rappresenta l'interfaccia grafica per la visualizzazione
 * dello stato dei voli in partenza e in arrivo.
 * <p>
 * L'interfaccia consente all'utente di:
 * <ul>
 *     <li>Visualizzare due tabelle separate per voli in partenza e in arrivo</li>
 *     <li>Effettuare una ricerca per destinazione</li>
 *     <li>Visualizzare orari e stato aggiornato dei voli</li>
 * </ul>
 * I dati sono forniti dal {@link StatoVoloController}.
 */
public class StatoVolo extends JFrame {

    private JTable tabellaPartenze;
    private JTable tabellaArrivi;
    private JTextField campoRicerca;
    private StatoVoloController controller;

    /**
     * Costruttore della finestra {@code StatoVolo}.
     * Inizializza l'interfaccia, imposta layout e componenti, e carica i dati iniziali.
     */
    public StatoVolo() {
        setTitle("Stato dei Voli");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        controller = new StatoVoloController();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(Color.WHITE);

        JLabel titolo = new JLabel("Stato dei Voli");
        titolo.setFont(new Font("Montserrat", Font.BOLD, 26));
        titolo.setForeground(new Color(19, 8, 102));
        titolo.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titolo, BorderLayout.NORTH);

        campoRicerca = new JTextField();
        campoRicerca.setFont(new Font("SansSerif", Font.PLAIN, 13));
        campoRicerca.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 220)));
        campoRicerca.setPreferredSize(new Dimension(300, 30));

        JButton cercaButton = new JButton("Cerca");
        styleButton(cercaButton);
        cercaButton.addActionListener(e -> aggiornaTabelle(campoRicerca.getText().trim()));

        JPanel barraRicerca = new JPanel(new FlowLayout(FlowLayout.LEFT));
        barraRicerca.setBackground(Color.WHITE);
        barraRicerca.add(new JLabel("Cerca destinazione:"));
        barraRicerca.add(campoRicerca);
        barraRicerca.add(cercaButton);
        mainPanel.add(barraRicerca, BorderLayout.SOUTH);

        JPanel tabellePanel = new JPanel(new GridLayout(2, 1, 20, 20));
        tabellePanel.setBackground(Color.WHITE);

        tabellaPartenze = creaTabella();
        tabellaArrivi = creaTabella();

        tabellePanel.add(creaSezione("✈ Voli in Partenza", tabellaPartenze));
        tabellePanel.add(creaSezione("🛬 Voli in Arrivo", tabellaArrivi));

        mainPanel.add(tabellePanel, BorderLayout.CENTER);
        add(mainPanel);

        aggiornaTabelle("");

        setVisible(true);
    }

    /**
     * Aggiorna le tabelle dei voli in base al filtro di ricerca.
     *
     * @param filtro stringa da confrontare con la destinazione dei voli
     */
    private void aggiornaTabelle(String filtro) {
        List<Volo> tuttiVoli = controller.recuperaTuttiIVoli();

        List<Volo> partenze = tuttiVoli.stream()
                .filter(v -> v.getTipo().equalsIgnoreCase("partenza"))
                .filter(v -> v.getDestinazione().toLowerCase().contains(filtro.toLowerCase()))
                .collect(Collectors.toList());

        List<Volo> arrivi = tuttiVoli.stream()
                .filter(v -> v.getTipo().equalsIgnoreCase("arrivo"))
                .filter(v -> v.getDestinazione().toLowerCase().contains(filtro.toLowerCase()))
                .collect(Collectors.toList());

        aggiornaTabella(tabellaPartenze, partenze);
        aggiornaTabella(tabellaArrivi, arrivi);
    }

    /**
     * Crea una tabella Swing con intestazioni e stile personalizzato.
     *
     * @return una nuova {@link JTable} configurata
     */
    private JTable creaTabella() {
        String[] colonne = {"Destinazione", "Partenza", "Arrivo", "Stato"};
        DefaultTableModel model = new DefaultTableModel(colonne, 0);
        JTable table = new JTable(model);
        table.setRowHeight(24);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(230, 230, 240));
        table.setGridColor(new Color(220, 220, 220));
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(true);
        table.setIntercellSpacing(new Dimension(0, 1));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        return table;
    }

    /**
     * Aggiorna il contenuto di una tabella con i dati dei voli forniti.
     *
     * @param table la tabella da aggiornare
     * @param voli  la lista di voli da visualizzare
     */
    private void aggiornaTabella(JTable table, List<Volo> voli) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Volo v : voli) {
            Object[] row = {
                    v.getDestinazione(),
                    formattaData(v.getDataPartenza()),
                    formattaData(v.getDataArrivo()),
                    v.getStato()
            };
            model.addRow(row);
        }
    }

    /**
     * Converte un oggetto {@link Date} in una stringa formattata.
     *
     * @param data la data da formattare
     * @return la data in formato "dd/MM/yyyy HH:mm", oppure un messaggio se nulla
     */
    private String formattaData(Date data) {
        if (data == null) return "Data non disponibile";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(data);
    }

    /**
     * Crea una sezione con titolo e tabella incapsulata in uno {@link JScrollPane}.
     *
     * @param titolo  il titolo della sezione
     * @param tabella la tabella da includere
     * @return un pannello contenente titolo e tabella
     */
    private JPanel creaSezione(String titolo, JTable tabella) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        JLabel label = new JLabel(titolo);
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        label.setForeground(new Color(40, 40, 80));
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.add(label, BorderLayout.NORTH);
        panel.add(new JScrollPane(tabella), BorderLayout.CENTER);
        return panel;
    }

    /**
     * Applica uno stile personalizzato a un pulsante.
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
                BorderFactory.createEmptyBorder(6, 16, 6, 16)
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
