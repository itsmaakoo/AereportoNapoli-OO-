package gui;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JPanel panel1;
    private JLabel benvenuto;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JLabel username;
    private JLabel password;
    private JButton LButton;

    public LoginFrame() {
        setTitle("Login");

        ImageIcon icon = new ImageIcon(getClass().getResource("Opera_senza_titolo.jpg"));
        Image bgImage = icon.getImage();

        JPanel backgroundPanel = new JPanel(new GridBagLayout()) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if(bgImage != null){
                    g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);

                }
            }
        };

        backgroundPanel.setLayout(new BorderLayout());

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setOpaque(false);

        panel1 = new JPanel(new GridBagLayout()){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        panel1.setOpaque(false);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 20, 10, 20);
        c.anchor = GridBagConstraints.WEST;

        benvenuto = new JLabel("Effetua login!");
        benvenuto.setForeground(new Color(19, 8, 102));
        benvenuto.setFont(new Font("Montserrat", Font.BOLD, 27));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.insets = new Insets(10, 20, 10, 20);
        panel1.add(benvenuto, c);

        username = new JLabel("Username");
        username.setForeground(new Color(19, 8, 102));
        username.setFont(new Font("Arial", Font.ITALIC, 16));
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        panel1.add(username, c);

        textField1 = new JTextField(15);
        addPlaceholder(textField1, "Inserisci Username");
        textField1.setPreferredSize(new Dimension(200, 35));
        textField1.setBorder(new roundedBorder(20));
        c.gridy = 2;
        c.insets = new Insets(5, 20, 5, 20);
        panel1.add(textField1, c);

        password = new JLabel("password");
        password.setForeground(new Color(19, 8, 102));
        password.setFont(new Font("Arial", Font.ITALIC, 16));
        c.gridy = 3;
        c.insets = new Insets(10, 20, 5, 20);
        panel1.add(password, c);

        passwordField1 = new JPasswordField(15);
        addPlaceholder(passwordField1, "Inserisci Password");
        passwordField1.setPreferredSize(new Dimension(200, 35));
        passwordField1.setBorder(new roundedBorder(20));
        c.gridy = 4;
        c.insets = new Insets(5, 20, 10, 20);
        panel1.add(passwordField1, c);

        LButton = new JButton("LOGIN");
        LButton.setPreferredSize(new Dimension(100, 35));
        LButton.setBorder(new roundedBorder(20));
        LButton.setForeground(new Color(19, 8, 102));
        c.gridy = 5;
        c.anchor = GridBagConstraints.CENTER;
        panel1.add(LButton, c);

        wrapper.add(panel1);
        backgroundPanel.add(wrapper, BorderLayout.WEST);

        add(backgroundPanel);

        LButton.addActionListener(e -> {
            String username = textField1.getText();
            String password = String.valueOf(passwordField1.getPassword());

            if (username.equals("admin") && password.equals("admin123")) {
                new AmministratoreGUI();
                dispose();
            } else if (username.equals("Utente") && password.equals("utente123")) {
                new UtenteGenericoPanel();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Errore nella chiusura della password");
            }
        });

        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    static class roundedBorder extends AbstractBorder {
        private final int radius;

        public roundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.GRAY);
            g2.setStroke(new BasicStroke(1.0f));
            g2.drawRoundRect(x,y,width -1,height -1,radius,radius);
            g2.dispose();
        }
        public Insets getBorderInsets(Component c) {
            return new Insets(8, 12, 8, 12);
        }
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(8, 12, 8, 12);
            return insets;
        }
    }

    private void addPlaceholder(JTextField field, String placeholder) {
        field.setForeground(Color.GRAY);
        field.setText(placeholder);

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if(field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent e) {
                if(field.getText().isEmpty()){
                    field.setForeground(Color.GRAY);
                    field.setText(placeholder);
                }
            }
        });
    }
    public JTextField getUsernameField(){
        return textField1;
    }

    public JPasswordField getPasswordField(){
        return passwordField1;
    }

    public JButton getLoginButton(){
        return LButton;
    }

    public static void main(String[] args) {

        new LoginFrame();
    }
}