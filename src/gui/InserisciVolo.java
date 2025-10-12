package gui;

import controller.InserisciVoloController;
import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class InserisciVolo extends JFrame {

    private JTextField destinazioneField;
    private JSpinner dataPartenzaSpinner;
    private JSpinner dataArrivoSpinner;
    private JButton confermaButton;
    private InserisciVoloController controller;

    public InserisciVolo() {
        setTitle("Inserisci Volo");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        controller = new InserisciVoloController();

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

        confermaButton.addActionListener(e -> salvaVolo());

        add(panel);
        setVisible(true);
    }

    private void salvaVolo() {
        String destinazione = destinazioneField.getText().trim();
        Date partenza = (Date) dataPartenzaSpinner.getValue();
        Date arrivo = (Date) dataArrivoSpinner.getValue();

        boolean success = controller.salvaVolo(destinazione, partenza, arrivo);

        if (success) {
            JOptionPane.showMessageDialog(this, "Volo inserito correttamente.");
            destinazioneField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Errore verifica i dati o la connessione.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InserisciVolo::new);
    }
}