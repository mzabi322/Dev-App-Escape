package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ArrierePlanScene2 extends JPanel {

    private Image backgroundImage;
    private Rectangle switchBackZone;
    private graphjeu parentFrame;

    // ----- ÉTAPE 1 : Déclarez vos nouvelles zones ici -----
    private Rectangle zonemorpion;
    private Rectangle zoneasci;

    public ArrierePlanScene2(graphjeu parent) {
        this.parentFrame = parent;
        setLayout(null);

        backgroundImage = new ImageIcon("images/Arrierplan2.jpg").getImage();
        switchBackZone = new Rectangle(50, 450, 80, 80);

        // ----- ÉTAPE 2 : Initialisez vos zones avec leur position et leur taille -----
        // (Ajustez ces valeurs pour qu'elles correspondent aux éléments sur votre image de fond)
        zoneasci = new Rectangle(1380, 600, 250, 250); // x, y, largeur, hauteur
        zonemorpion= new Rectangle(1400, 200, 380, 350);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Clic détecté dans scene2 à: " + e.getPoint()); // Votre debug, très utile !

                // ----- ÉTAPE 3 : Ajoutez la logique de clic pour les nouvelles zones -----
                if (switchBackZone.contains(e.getPoint())) {
                    System.out.println("Clic sur retour - passage à scene1");
                    parentFrame.showScene("scene1");

                } else if (zonemorpion.contains(e.getPoint())) {
                    System.out.println("Clic sur la zone morpion - lancement du jeu!");

                    parentFrame.showScene("XO");

                } else if (zoneasci.contains(e.getPoint())) {
                    System.out.println("Clic sur la zone mystérieuse 2 !");
                    parentFrame.showScene("Asci");;
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // Dessin de la flèche de retour
        g.setColor(Color.GRAY);
        g.drawRect(switchBackZone.x, switchBackZone.y, 70, 70);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("←", switchBackZone.x + 20, switchBackZone.y + 55);





    }
}