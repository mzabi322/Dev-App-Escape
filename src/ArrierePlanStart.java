package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArrierePlanStart extends JPanel {
    private Image backgroundImage;
    private graphjeu parent;
    private JButton startButton;

    public ArrierePlanStart(graphjeu parent) {
        this.parent = parent;
        setLayout(null); // Layout absolu pour positionner les éléments librement

        // Charger l'image de fond (vous pouvez changer le chemin)
        backgroundImage = new ImageIcon("images/Arrierplan.png").getImage();

        // Créer le bouton Start
        startButton = new JButton("START");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setBackground(new Color(57, 250, 20));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);
        startButton.setPreferredSize(new Dimension(200, 60));


        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                startButton.setBackground(new Color(0, 120, 200));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                startButton.setBackground(new Color(0, 150, 255));
            }
        });

        // Action du bouton
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.showScene("scene1");
            }
        });


        startButton.setBounds(100, 100, 200, 60);
        add(startButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dessiner l'image de fond
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            // Fond par défaut si l'image n'est pas trouvée
            g.setColor(new Color(20, 20, 40));
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        // Dessiner le titre du jeu
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Titre principal
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Orbiter", Font.BOLD, 40));
        FontMetrics fm = g2d.getFontMetrics();
        String title = "Bienvenue à notre ESCAPE GAME... :)";
        int titleX = (getWidth() - fm.stringWidth(title)) / 2;
        int titleY = getHeight() / 3;
        g2d.drawString(title, titleX, titleY);

        // Sous-titre
        g2d.setColor(Color.red);
        g2d.setFont(new Font("Arial", Font.ITALIC, 18));
        fm = g2d.getFontMetrics();
        String txt = "©BreakTHELab - MZABI & MHALLA  ";
        g2d.drawString(txt, 30, 1000);

        // Repositionner le bouton au centre
        int buttonX = (getWidth() - startButton.getWidth()) / 2;
        int buttonY = getHeight() * 2 / 3;
        startButton.setBounds(buttonX, buttonY, 200, 60);
    }
}