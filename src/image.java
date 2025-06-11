package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class image extends JPanel {

    private Image backgroundImage;
    private Rectangle pcZone;
    private graphjeu jeu;
    private Rectangle serveurZone;

    public image (graphjeu jeu) {
        this.jeu = jeu;

        backgroundImage = new ImageIcon("images/ecran1.png").getImage();
        pcZone = new Rectangle(200,450,400,350);
        serveurZone = new Rectangle(1200,100,350,700);


        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (pcZone.contains(e.getPoint())) {
                    jeu.showScene("devinette");
                }
                if (serveurZone.contains(e.getPoint())) {
                    jeu.showScene("terminal");
                }

            }
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);




    }
}
