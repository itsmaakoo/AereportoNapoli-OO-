package gui;

import controller.PrenotaVoloController;
import model.Volo;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;


public class PrenotaVolo extends JFrame {

    private JTextField nomeField, cognomeField, codiceFiscale;
    private JList<Volo> listaVoli;
    private PrenotaVoloController controller;

    public PrenotaVolo() {
        setTitle("Prenota Volo");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        controller = new PrenotaVoloController();

        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        nomeField = new JTextField();
        cognomeField = new JTextField();
        codiceFiscale = new JTextField();

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        formPanel.add(new JLabel("Cognome:"));
        formPanel.add(cognomeField);
        formPanel.add(new JLabel("CodiceFiscale:"));
        formPanel.add(codiceFiscale);

        panel.add(formPanel, BorderLayout.NORTH);

        DefaultListModel<Volo> model = new DefaultListModel<>();
        List<Volo> voliPrenotabili = controller.getVoliPrenotabili();
        voliPrenotabili.forEach(model::addElement);

        listaVoli = new JList<>(model);
        listaVoli.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaVoli);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton checkInButton = new JButton(" Effettua check in");
        checkInButton.addActionListener(e -> {
            try {
                prenota();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        panel.add(checkInButton, BorderLayout.SOUTH);
        add(panel);
        setVisible(true);
    }

    private void prenota() throws SQLException {
        Volo selezionato = listaVoli.getSelectedValue();
        String nome = nomeField.getText().trim();
        String cognome = cognomeField.getText().trim();
        String cf = codiceFiscale.getText().trim();

        boolean success = controller.prenotaVolo(nome, cognome, cf, selezionato);
        if (success) {
            JOptionPane.showMessageDialog(this, "Prenotazione confermata");
            nomeField.setText("");
            cognomeField.setText("");
            codiceFiscale.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Errore durante la prenotazione controlla i dati.");
        }
    }
}