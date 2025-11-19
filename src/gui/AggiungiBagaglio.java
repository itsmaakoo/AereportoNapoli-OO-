package gui;

import dao.PrenotazioneDAO;
import dao.BagaglioDAO;
import dao.VoloDAO;
import db.ConnessioneDB;
import model.Bagaglio;
import model.Prenotazione;
import model.Volo;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

/**
 * La classe {@code AggiungiBagaglio} rappresenta una finestra grafica
 * che consente di aggiungere manualmente un bagaglio associato a un volo.
 * <p>
 * L'interfaccia include una combo box per la selezione del volo, un campo
 * di testo per il codice del bagaglio e un pulsante per confermare l'aggiunta.
 */
public class AggiungiBagaglio extends JFrame {

    /** ComboBox per selezionare il volo. */
    private JComboBox<Volo> voloJComboBox;

    /** Campo di testo per inserire il codice del bagaglio. */
    private JTextField campoBagaglio;

    /** Pulsante per confermare l'aggiunta del bagaglio. */
    private JButton confermaButton;

    /**
     * Costruttore della finestra {@code AggiungiBagaglio}.
     * <p>
     * Inizializza l'interfaccia grafica, carica i voli disponibili
     * e imposta il comportamento del pulsante di conferma.
     */
    public AggiungiBagaglio() {
        setTitle("Aggiungi Bagaglio");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(Color.WHITE);

        JLabel titolo = new JLabel("Aggiungi Bagaglio al Volo");
        titolo.setFont(new Font("Montserrat", Font.BOLD, 24));
        titolo.setForeground(new Color(19, 8, 102));
        titolo.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titolo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(12, 12, 12, 12);
        c.fill = GridBagConstraints.HORIZONTAL;

        voloJComboBox = new JComboBox<>();
        voloJComboBox.setFont(new Font("SansSerif", Font.PLAIN, 13));
        voloJComboBox.setPreferredSize(new Dimension(300, 30));
        voloJComboBox.setBackground(Color.WHITE);
        voloJComboBox.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 220)));

        // Caricamento dei voli nel database
        DefaultComboBoxModel<Volo> model = new DefaultComboBoxModel<>();
        try (Connection conn = ConnessioneDB.getConnection()) {
            if (conn != null) {
                VoloDAO voloDao = new VoloDAO(conn);
                List<Volo> voli = voloDao.getTuttiVoli();
                for (Volo volo : voli) {
                    model.addElement(volo);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Errore nel caricamento dei voli");
            e.printStackTrace();
        }
        voloJComboBox.setModel(model);

        campoBagaglio = new JTextField();
        campoBagaglio.setFont(new Font("SansSerif", Font.PLAIN, 13));
        campoBagaglio.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 220)));

        confermaButton = new JButton("Aggiungi Bagaglio");
        styleButton(confermaButton);

        // Layout dei componenti nel pannello
        c.gridx = 0;
        c.gridy = 0;
        formPanel.add(new JLabel("Seleziona Volo:"), c);
        c.gridx = 1;
        formPanel.add(voloJComboBox, c);

        c.gridx = 0;
        c.gridy = 1;
        formPanel.add(new JLabel("Codice Bagaglio:"), c);
        c.gridx = 1;
        formPanel.add(campoBagaglio, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        formPanel.add(confermaButton, c);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);

        /**
         * Listener per il pulsante di conferma.
         * <p>
         * Recupera il volo selezionato e il codice del bagaglio, verifica
         * la presenza di prenotazioni e aggiunge il bagaglio alla
         * prenotazione che si desidera.
         */
        confermaButton.addActionListener(e -> {
            Volo selezionato = (Volo) voloJComboBox.getSelectedItem();
            String descrizione = campoBagaglio.getText().trim();

            if (selezionato != null && !descrizione.isEmpty()) {
                try {
                    int codiceBagaglio = Integer.parseInt(descrizione);
                    Connection connBagaglio = ConnessioneDB.getConnection();

                    PrenotazioneDAO prenotazionidao = new PrenotazioneDAO(connBagaglio);
                    List<Prenotazione> prenotazioni = prenotazionidao.getPrenotazioniPerVolo(selezionato.getId());

                    if (!prenotazioni.isEmpty()) {
                        Prenotazione primaprenotazione = prenotazioni.get(0);
                        int prenotazioneId = primaprenotazione.getPrenotazioneId();
                        Bagaglio nuovoBagaglio = new Bagaglio(prenotazioneId, codiceBagaglio, "bagaglio aggiunto manualmente");
                        BagaglioDAO bagagliodao = new BagaglioDAO(connBagaglio);
                        bagagliodao.add(nuovoBagaglio, prenotazioneId);
                        JOptionPane.showMessageDialog(this, "Bagaglio aggiunto al Volo");
                        campoBagaglio.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "Nessuna Prenotazione trovata per questo Volo");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Inserisci un numero valido per il codice del bagaglio.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Errore durante l'aggiunta del bagaglio.");
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleziona un Volo e inserisci il codice del bagaglio.");
            }
        });

        setVisible(true);
    }

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

