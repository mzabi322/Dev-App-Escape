package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArrierePlanStart extends JPanel {
    private Image ArrierePlanImage;
    private graphjeu parent;
    private JButton startButton;

    public ArrierePlanStart(graphjeu parent) {
        this.parent = parent;
        setLayout(null);


        ArrierePlanImage = new ImageIcon("images/Arrierplan.png").getImage();

        // Crée le bouton Start
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


        startButton.setBounds(600, 450 ,200, 60);
        add(startButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ArrierePlanImage, 0, 0, getWidth(), getHeight(), this);


        // Dessiner le titre du jeu
        Graphics2D g2d = (Graphics2D) g;


      //config texte button
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Orbiter", Font.BOLD, 40));

        String title = "Bienvenue à notre ESCAPE GAME... :)";
        g2d.drawString(title, 550, 400);


        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial" , Font.ITALIC, 15));
        String indice ="Dans ces Deux chambres se trouve 4 énigmes... Bonne chance !!";
        g2d.drawString(indice,700,450);

        // Txt copyright en bas :)
        g2d.setColor(Color.red);
        g2d.setFont(new Font("Arial", Font.ITALIC, 18));
        String txt = "© BreakTHELab - MZABI & MHALLA  ";
        g2d.drawString(txt, 30, 1000);

    }
}