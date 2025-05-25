package src;

import src.Enigme1_DevinettePanel;

import javax.swing.*;
import java.awt.*;

public class TestDevinette {
    public static void main(String[] args) {
        // Crée une fenêtre JFrame avec un titre
        JFrame frame = new JFrame("Énigme 1 - Devinette");

        // Fermer le programme quand la fenêtre est fermée
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Plein écran sans bordures (mais pas mode exclusif)
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiser
        frame.setUndecorated(true); // Enlever la barre de titre (sans décorations)

        // Ajouter ton panel personnalisé
        frame.add(new Enigme1_DevinettePanel());

        // Afficher la fenêtre
        frame.setVisible(true);
    }
}
