package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Volo;
import controller.AssegnaGateController;

/**
 * La classe {@code AssegnaGate} rappresenta una finestra grafica dedicata
 * all'assegnazione di un gate a un volo specifico.
 * <p>
 * L'interfaccia consente all'operatore di:
 * <ul>
 *     <li>Selezionare un volo dalla lista disponibile</li>
 *     <li>Inserire manualmente il gate da assegnare</li>
 *     <li>Confermare l'assegnazione tramite un pulsante</li>
 * </ul>
 * <p>
 * La logica dell'interazione è gestita dal {@link controller.AssegnaGateController},
 * che fornisce i dati dei voli e gestisce l'assegnazione del gate nel sistema.
 * <p>
 * In caso di assegnazione riuscita, viene mostrato un messaggio di conferma;
 * altrimenti, viene visualizzato un messaggio di errore.
 * <p>
 * L'interfaccia è costruita con componenti Swing come {@link JComboBox}, {@link JTextField}
 * e {@link JButton}, e utilizza layout e stili personalizzati per migliorare l'usabilità.
 *
 * @see controller.AssegnaGateController
 * @see model.Volo
 */


public class AssegnaGate extends JFrame {
    private AssegnaGateController controller;

    /**
     * Instantiates a new Assegna gate.
     */
    public AssegnaGate() {
        controller = new AssegnaGateController();

        setTitle("Assegna Gate");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(Color.WHITE);

        JLabel titolo = new JLabel("Assegna Gate al Volo");
        titolo.setFont(new Font("Montserrat", Font.BOLD, 24));
        titolo.setForeground(new Color(19, 8, 102));
        titolo.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titolo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(12, 12, 12, 12);
        c.fill = GridBagConstraints.HORIZONTAL;

        JComboBox<Volo> voloJComboBox = new JComboBox<>();
        voloJComboBox.setFont(new Font("SansSerif", Font.PLAIN, 13));
        voloJComboBox.setPreferredSize(new Dimension(300, 30));
        voloJComboBox.setBackground(Color.WHITE);
        voloJComboBox.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 220)));

        List<Volo> voli = controller.getTuttiVoli();
        for (Volo v : voli) {
            voloJComboBox.addItem(v);
        }

        JTextField gateJTextField = new JTextField();
        gateJTextField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        gateJTextField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 220)));

        JButton confermaButton = new JButton("Assegna Gate");
        styleButton(confermaButton);

        c.gridx = 0;
        c.gridy = 0;
        formPanel.add(new JLabel("Seleziona Volo:"), c);
        c.gridx = 1;
        formPanel.add(voloJComboBox, c);

        c.gridx = 0;
        c.gridy = 1;
        formPanel.add(new JLabel("Gate da assegnare:"), c);
        c.gridx = 1;
        formPanel.add(gateJTextField, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        formPanel.add(confermaButton, c);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);

        confermaButton.addActionListener(e -> {
            Volo volo = (Volo) voloJComboBox.getSelectedItem();
            String gate = gateJTextField.getText().trim();

            if (volo != null && !gate.isEmpty()) {
                boolean success = controller.assegnaGate(volo.getId(), gate);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Assegnato gate " + gate + " al Volo per " + volo.getDestinazione());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Errore nell'assegnazione del gate.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleziona un volo e inserisci un gate valido.");
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