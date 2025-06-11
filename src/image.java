package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class image extends JPanel {

    private Image backgroundImage;
    private Image PaveImage;
    private Rectangle pcZone;
    private graphjeu jeu;
    private Rectangle serveurZone;
    private Rectangle paveZone;

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

        enigmePanel = new Enigme_finale();
        enigmePanel.setBounds(1100, 500, 350, 250);
        enigmePanel.setVisible(false);
        this.setLayout(null);
        this.add(enigmePanel);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (pcZone.contains(e.getPoint())) {
                    jeu.showScene("devinette");
                }
                else if (serveurZone.contains(e.getPoint())) {
                    jeu.showScene("terminal");
                }
                else if (paveZone.contains(e.getPoint())) {
                    togglePaveZoom();
                }
                // Clic pour fermer le pavé agrandi
                else if (paveZoomed) {
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

        // Dessiner l'arrière-plan
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // Si le pavé est zoomé, dessiner l'overlay et le pavé agrandi
        if (paveZoomed) {
            // Overlay semi-transparent
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(0, 0, getWidth(), getHeight());

            // Dessiner le pavé agrandi
            g.drawImage(PaveImage, 1000, 100, 240, 360, this);

            // Contour autour du pavé agrandi
            g.setColor(Color.CYAN);
            ((Graphics2D) g).setStroke(new BasicStroke(3));
            g.drawRect(1000, 95, 240, 360);

            // Instruction
            g.setColor(Color.WHITE);
            g.setFont(new Font("Montserrat", Font.BOLD, 16));
            g.drawString("Cliquez ailleurs pour fermer", 20, 30);
        }
    }
}