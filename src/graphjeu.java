package src;
import javax.swing.*;
import java.awt.*;


public class graphjeu extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public graphjeu() {
        setTitle("Escape Game");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Layout principal
        setLayout(new BorderLayout());


        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);


        JButton quitterButton = new JButton("X");
        quitterButton.setBackground(Color.GRAY);
        quitterButton.setForeground(Color.WHITE);
        quitterButton.setFont(new Font("Arial", Font.BOLD, 20));
        quitterButton.setFocusPainted(false);
        quitterButton.setBorderPainted(false);
        quitterButton.setPreferredSize(new Dimension(100, 30));

        // Action pour quitter le jeu
        quitterButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Êtes-vous sûr de vouloir quitter le jeu ?",
                    "Quitter le jeu",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // Panel pour positionner le bouton en haut à droite
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.BLACK);
        topPanel.setPreferredSize(new Dimension(0, 40));

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        rightPanel.setBackground(Color.BLACK);
        rightPanel.add(quitterButton);

        topPanel.add(rightPanel, BorderLayout.EAST);


        image imageScene = new image(this);
        Enigme1_DevinettePanel devinettePanel = new Enigme1_DevinettePanel(this);
        Enigme2_Serveur Enigme2 = new Enigme2_Serveur();


        mainPanel.add(imageScene, "scene1");
        mainPanel.add(devinettePanel, "devinette");
        mainPanel.add(Enigme2,"terminal");

        // Ajouter les composants à la fenêtre
        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Permet de changer de scène
    public void showScene(String name) {
        cardLayout.show(mainPanel, name);
    }
}