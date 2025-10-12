package gui;

import controller.AmministratoreController;
import javax.swing.*;
import java.awt.*;

public class AmministratoreGUI extends JFrame {
    private final AmministratoreController controller = new AmministratoreController();

    public AmministratoreGUI() {
        super("Amministratore");

        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon(getClass().getResource("/gui/Opera_senza_titolo copia 2.jpg"));
        Image scaledImage = icon.getImage().getScaledInstance(900, 500, Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(scaledImage));
        backgroundLabel.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 30));
        leftPanel.setPreferredSize(new Dimension(320, getHeight()));

        JLabel saluto = new JLabel("Benvenuto!");
        saluto.setFont(new Font("Montserrat", Font.BOLD, 30));
        saluto.setForeground(new Color(19, 8, 102));
        saluto.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(saluto);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        leftPanel.add(createButtonWithSpacing("Inserisci volo", e -> controller.apriInserisciVolo()));
        leftPanel.add(createButtonWithSpacing("Assegna gate", e -> controller.apriAssegnaGate()));
        leftPanel.add(createButtonWithSpacing("Visualizza smarrimenti", e -> controller.apriVisualizzaSmarrimenti()));
        leftPanel.add(createButtonWithSpacing("Modifica volo", e -> controller.apriModificaVolo()));
        leftPanel.add(createButtonWithSpacing("Aggiorna bagaglio", e -> controller.apriAggiornaBagaglio()));
        leftPanel.add(createButtonWithSpacing("Visualizza passeggero", e -> controller.apriVisualizzaPasseggeri()));

        JButton logoutButton = createButton("Logout", e -> {
            controller.logout();
            dispose();
        });
        logoutButton.setForeground(Color.red);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        leftPanel.add(logoutButton);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setOpaque(false);

        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);
        backgroundLabel.add(mainPanel);
        setContentPane(backgroundLabel);
        setVisible(true);
    }

    private Component createButtonWithSpacing(String text, java.awt.event.ActionListener action) {
        JButton button = createButton(text, action);
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setOpaque(false);
        wrapper.add(button);
        wrapper.add(Box.createRigidArea(new Dimension(0, 15)));
        return wrapper;
    }

    private JButton createButton(String text, java.awt.event.ActionListener action) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(Color.white);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                g2.setColor(Color.gray);
                g2.setStroke(new BasicStroke(1));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            public boolean isOpaque() {
                return false;
            }
        };

        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setForeground(new Color(19, 8, 102));
        button.setFont(new Font("SansSerif", Font.PLAIN, 14));
        button.setMaximumSize(new Dimension(250, 40));
        button.setPreferredSize(new Dimension(250, 40));
        button.addActionListener(action);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AmministratoreGUI::new);
    }
}



