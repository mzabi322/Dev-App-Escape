package src;

import javax.swing.*;
import java.awt.*;

public class Enigme_finale extends JPanel {
    private String CodeCorrect = "Interface cherche son implementation ";
    private JTextField Saisie;
    private JLabel msg;
    private static int Tentative = 0;

    public Enigme_finale() {
        setLayout(new BorderLayout(5, 5));
        setBackground(new Color(20, 20, 20, 200)); // Fond semi-transparent
        setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));

        JLabel instruction = new JLabel("Code final avec les mots:");
        instruction.setHorizontalAlignment(JLabel.CENTER);
        instruction.setFont(new Font("Arial", Font.BOLD, 12));
        instruction.setForeground(Color.WHITE);

        Saisie = new JTextField(15);
        Saisie.setFont(new Font("Consolas", Font.BOLD, 14));
        Saisie.setHorizontalAlignment(JTextField.CENTER);
        Saisie.setBackground(Color.BLACK);
        Saisie.setForeground(Color.GREEN);
        Saisie.setCaretColor(Color.GREEN);

        JButton valider = new JButton("✓");
        valider.setFont(new Font("Arial", Font.BOLD, 16));
        valider.setPreferredSize(new Dimension(30, 25));
        valider.setBackground(Color.DARK_GRAY);
        valider.setForeground(Color.WHITE);
        valider.addActionListener(e -> verifierCode());

        msg = new JLabel("Entrez le code...");
        msg.setHorizontalAlignment(JLabel.CENTER);
        msg.setFont(new Font("Arial", Font.ITALIC, 10));
        msg.setForeground(Color.LIGHT_GRAY);

        JPanel centerPanel = new JPanel(new BorderLayout(2, 2));
        centerPanel.setOpaque(false);
        centerPanel.add(Saisie, BorderLayout.CENTER);
        centerPanel.add(valider, BorderLayout.EAST);

        add(instruction, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(msg, BorderLayout.SOUTH);

        // Rendre le panel un peu transparent
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dessiner le fond arrondi semi-transparent
        g2d.setColor(new Color(20, 20, 20, 180));
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        // Bordure
        g2d.setColor(Color.CYAN);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, 10, 10);
    }

    private void verifierCode() {
        String codeSaisie = Saisie.getText();
        if (codeSaisie.equals(CodeCorrect)) {
            msg.setText("> ACCÈS ACCORDÉ !Vous êtes libre");
            Saisie.setEnabled(false);
        } else {
            Tentative++;
            int reste = 3 - Tentative;
            setForeground(Color.red);
            if (reste > 0) {
                msg.setText(">Code incorrect! Il reste " + reste + " tentatives");
            } else {

                msg.setText(">ÉCHEC!Trop de tentatives! ALARME déclenché ");
                Saisie.setEnabled(false);
            }
        }
    }
}