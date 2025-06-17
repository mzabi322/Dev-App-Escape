package src;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Enigme2_Serveur extends JPanel {
    private JTextArea Affichage;
    private JTextField txt;
    private Map<String, String> Fichiers;
    private JButton quitter;
    private graphjeu parent;

    // Modifier le constructeur pour accepter le parent
    public Enigme2_Serveur(graphjeu parent) {
        this.parent = parent;
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        Fichiers = new HashMap<>();
        Fichiers.put("passwords.txt", "Chaque \nGilou le roi du Pastis \nSoso93\n");
        Fichiers.put("notes.txt", "tout est confidentiel ici !\n");

        Affichage = new JTextArea();
        Affichage.setBackground(Color.BLACK);
        Affichage.setForeground(Color.GREEN);
        Affichage.setFont(new Font("Consolas", Font.PLAIN, 14));
        Affichage.setEditable(false);

        JScrollPane scroll = new JScrollPane(Affichage);

        txt = new JTextField();
        txt.setBackground(Color.BLACK);
        txt.setForeground(Color.GREEN);
        txt.setFont(new Font("Consolas", Font.PLAIN, 14));
        txt.setCaretColor(Color.GREEN);
        txt.addActionListener(e -> terminal());

        // Créer un petit bouton quitter stylisé
        quitter = new JButton("Retour");
        quitter.setBackground(Color.DARK_GRAY);
        quitter.setForeground(Color.WHITE);
        quitter.setFont(new Font("Arial", Font.BOLD, 12));
        quitter.setFocusPainted(false);
        quitter.setBorderPainted(false);
        quitter.setPreferredSize(new Dimension(80, 30));

        // Ajouter un effet hover
        quitter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                quitter.setBackground(Color.GRAY);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                quitter.setBackground(Color.DARK_GRAY);
            }
        });

        // Panel pour le bouton en haut à droite
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        topPanel.setBackground(Color.BLACK);
        topPanel.add(quitter);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(txt, BorderLayout.SOUTH);

        // Action corrigée pour retourner à la scène principale
        quitter.addActionListener(e -> {
            parent.showScene("scene1");
        });

        Bienvenue();
    }

    private void Bienvenue() {
        Afficher(">Bienvenue dans nos serveurs !");
        Afficher(">Accès interdit au publique!!Assurez vous de rien diffuser!!!!!!");
    }

    private void Afficher(String s) {
        Affichage.append(s + "\n");
    }

    private void terminal() {
        String cmd = txt.getText();
        Afficher("$ " + cmd);

        if (cmd.equals("ls")) {
            Afficher(String.join("  ", Fichiers.keySet()));
        } else if (cmd.startsWith("cat ")) {
            String NomFichiers = cmd.substring(4);// prendre les mots du mots tapé 5 carcatres apres le debut qui font ref a act et l'espace
            if (Fichiers.containsKey(NomFichiers)) {
                Afficher(Fichiers.get(NomFichiers));
            } else {
                Afficher("cat: " + NomFichiers + ": Aucun fichier de ce type");
            }
        } else {
            Afficher(cmd + ": commande inconnue");
        }
        txt.setText("");
    }
}