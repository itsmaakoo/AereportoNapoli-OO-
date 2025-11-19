package gui;

import model.Prenotazione;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * La classe {@code VisualizzaPrenotazioni} rappresenta l'interfaccia grafica
 * per la visualizzazione delle prenotazioni effettuate da un utente.
 * <p>
 * Mostra una tabella con i dettagli di ciascuna prenotazione, inclusi:
 * <ul>
 *     <li>Numero del biglietto</li>
 *     <li>Codice fiscale del passeggero</li>
 *     <li>ID del volo</li>
 *     <li>Stato della prenotazione</li>
 *     <li>Posto assegnato</li>
 * </ul>
 * La tabella è stilizzata per una visualizzazione chiara e ordinata.
 *
 */
public class VisualizzaPrenotazioni extends JFrame {

    /**
     * Costruttore della finestra {@code VisualizzaPrenotazioni}.
     * <p>
     * Inizializza l'interfaccia grafica, configura la tabella e popola i dati
     * delle prenotazioni fornite.
     *
     * @param prenotazioni la lista di prenotazioni da visualizzare
     */
    public VisualizzaPrenotazioni(List<Prenotazione> prenotazioni) {
        setTitle("Le mie prenotazioni");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(Color.WHITE);

        JLabel titolo = new JLabel("Prenotazioni Effettuate");
        titolo.setFont(new Font("Montserrat", Font.BOLD, 24));
        titolo.setForeground(new Color(19, 8, 102));
        titolo.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titolo, BorderLayout.NORTH);

        String[] colonne = {"Biglietto", "Codice Fiscale", "Volo ID", "Stato", "Posto"};
        DefaultTableModel modello = new DefaultTableModel(colonne, 0);

        for (Prenotazione p : prenotazioni) {
            Object[] riga = {
                    p.getNum_biglietto(),
                    p.getCodiceFiscale(),
                    p.getVoloId(),
                    p.getStato_prenotazione(),
                    p.getPosto_assegnato()
            };
            modello.addRow(riga);
        }

        JTable tabella = new JTable(modello);
        tabella.setRowHeight(22);
        tabella.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tabella.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
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
        }

        JScrollPane scrollPane = new JScrollPane(tabella);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                "Dettagli Prenotazioni",
                0,
                0,
                new Font("SansSerif", Font.BOLD, 14),
                new Color(19, 8, 102)
        ));
        scrollPane.setPreferredSize(new Dimension(600, 300));

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }
}