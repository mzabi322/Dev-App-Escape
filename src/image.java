package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class image extends JPanel {

    private Image backgroundImage;
    private Image PaveImage;
    private Rectangle pcZone;
    private Rectangle serveurZone;
    private Rectangle paveZone;
    private Rectangle switchScene; // zone cliquable pour changer de scène
    private graphjeu jeu;

    // Variables pour l'effet pavé
    private boolean paveZoomed = false;
    private Enigme_finale enigmePanel;

    public image(graphjeu jeu) {
        this.jeu = jeu;

        backgroundImage = new ImageIcon("images/ecran1.png").getImage();
        PaveImage = new ImageIcon("images/Pavé.png").getImage();

        pcZone = new Rectangle(200, 450, 400, 350);
        serveurZone = new Rectangle(1200, 100, 350, 700);
        paveZone = new Rectangle(995, 350, 40, 80);
        switchScene = new Rectangle(1800, 450, 90, 90); // Position ajustée

        enigmePanel = new Enigme_finale();
        enigmePanel.setBounds(1100, 500, 350, 250);
        enigmePanel.setVisible(false);
        this.setLayout(null);
        this.add(enigmePanel);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Clic détecté à: " + e.getPoint()); // Debug

                if (pcZone.contains(e.getPoint())) {
                    System.out.println("Clic sur PC - ouverture devinette");
                    jeu.showScene("devinette");
                } else if (serveurZone.contains(e.getPoint())) {
                    System.out.println("Clic sur serveur - ouverture terminal");
                    jeu.showScene("terminal");
                } else if (paveZone.contains(e.getPoint())) {
                    System.out.println("Clic sur pavé - zoom");
                    togglePaveZoom();
                } else if (switchScene.contains(e.getPoint())) {
                    System.out.println("Clic sur switchScene - passage à scene2");
                    jeu.showScene("scene2");
                } else if (paveZoomed) {
                    closePaveZoom();
                }
            }
        });
    }

    private void togglePaveZoom() {
        if (!paveZoomed) {
            paveZoomed = true;
            enigmePanel.setVisible(true);
            repaint();
        }
    }

    private void closePaveZoom() {
        paveZoomed = false;
        enigmePanel.setVisible(false);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Arrière-plan de la scène
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // Si le pavé est zoomé
        if (paveZoomed) {
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.drawImage(PaveImage, 1000, 100, 240, 360, this);

            g.setColor(Color.CYAN);
            ((Graphics2D) g).setStroke(new BasicStroke(3));
            g.drawRect(1000, 95, 240, 360);

            g.setColor(Color.red);
            g.fillRect(900, 550, 40, 40);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Montserrat", Font.BOLD, 16));
            g.drawString("Cliquez ailleurs pour fermer", 20, 30);
        }

        // Dessiner la flèche (switch de scène)
        g.setColor(Color.GRAY);
        g.drawRect(switchScene.x, switchScene.y, 70, 70);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("→", switchScene.x + 18, switchScene.y + 40);
    }
}