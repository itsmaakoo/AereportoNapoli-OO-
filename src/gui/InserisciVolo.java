package gui;

import dao.VoloDAO;
import db.ConnessioneDB;
import model.Volo;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.Date;

public class InserisciVolo extends JFrame {

    private JTextField destinazioneField;
    private JSpinner dataPartenzaSpinner;
    private JSpinner dataArrivoSpinner;
    private JButton confermaButton;

    public InserisciVolo() {
        setTitle("Inserisci Volo");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.HORIZONTAL;

        // Destinazione
        c.gridx = 0;
        c.gridy = 0;
        panel.add(new JLabel("Destinazione:"), c);
        c.gridx = 1;
        destinazioneField = new JTextField(15);
        panel.add(destinazioneField, c);

        // Data partenza
        c.gridx = 0;
        c.gridy = 1;
        panel.add(new JLabel("Data partenza:"), c);
        c.gridx = 1;
        dataPartenzaSpinner = new JSpinner(new SpinnerDateModel());
        dataPartenzaSpinner.setEditor(new JSpinner.DateEditor(dataPartenzaSpinner, "dd/MM/yyyy HH:mm"));
        panel.add(dataPartenzaSpinner, c);

        // Data arrivo
        c.gridx = 0;
        c.gridy = 2;
        panel.add(new JLabel("Data arrivo:"), c);
        c.gridx = 1;
        dataArrivoSpinner = new JSpinner(new SpinnerDateModel());
        dataArrivoSpinner.setEditor(new JSpinner.DateEditor(dataArrivoSpinner, "dd/MM/yyyy HH:mm"));
        panel.add(dataArrivoSpinner, c);

        // Bottone conferma
        confermaButton = new JButton("Salva Volo");
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(confermaButton, c);

        confermaButton.addActionListener(e -> inserisciVolo());

        add(panel);
        setVisible(true);
    }

    private void inserisciVolo() {
        String destinazione = destinazioneField.getText().trim();
        Date partenza = (Date) dataPartenzaSpinner.getValue();
        Date arrivo = (Date) dataArrivoSpinner.getValue();

        if (!arrivo.after(partenza) && !arrivo.equals(partenza)) {
            JOptionPane.showMessageDialog(this, "La data di arrivo deve essere uguale o successiva alla partenza.");
            return;
        }


        if (destinazione.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inserisci una destinazione.");
            return;
        }


        // Crea il volo senza ID (verrà generato dal DB)
        Volo nuovoVolo = new Volo (0, destinazione, partenza, arrivo, "programmato", "1"); // puoi cambiare gate e stato

        try {
            Connection conn = ConnessioneDB.getConnection();
            VoloDAO voloDao = new VoloDAO(conn);
            boolean successo = voloDao.salvaVolo(nuovoVolo);

            if (successo) {
                JOptionPane.showMessageDialog(this, "Volo inserito con ID: " + nuovoVolo.getId());
                destinazioneField.setText("");
                // resetta anche gli spinner se vuoi
            } else {
                JOptionPane.showMessageDialog(this, "Errore durante l'inserimento del volo.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore di connessione o salvataggio.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InserisciVolo::new);
    }
}
