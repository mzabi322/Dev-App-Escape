package src;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Enigme2_Serveur extends JPanel {
    private JTextArea Affichage;
    private JTextField txt;
    private Map<String, String> Fichiers;
    private JButton quitter;


    public Enigme2_Serveur() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        Fichiers = new HashMap<>();
        Fichiers.put("passwords.txt", "serveur \nGilou le roi du Pastis \nSoso93\n");
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

        quitter = new JButton("Quitter");


        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
        add(txt, BorderLayout.SOUTH);
        add(quitter, BorderLayout.EAST);
        quitter.addActionListener(e -> {
            // On ferme la fenêtre la plus proche qui contient ce panel
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) window.dispose();
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
