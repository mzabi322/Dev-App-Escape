package src;

import javax.swing.*;
import java.awt.*;

public class Enigme1_DevinettePanel extends JPanel {

    private int essai = 0;
    private boolean resolu = false;
    private boolean alaremdeclenche = false;
    private graphjeu parent;

    private JLabel riddleLabel;
    private JTextField answerField;
    private JButton submitButton;
    private JLabel feedbackLabel;
    private String mot;
    private JButton quitButton;

    // CHANGEZ LE CONSTRUCTEUR POUR ACCEPTER LE PARENT
    public Enigme1_DevinettePanel(graphjeu parent) {
        this.parent = parent; // AJOUTEZ CETTE LIGNE

        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 1, 10, 10));
        centerPanel.setBackground(Color.BLACK);

        String devinette = "Je suis une structure en Java qui ne contient que des méthodes abstraites et aucune implémentation concrète,je peux etre implemnté dans differents classes";

        String riddle = devinette;

        riddleLabel = new JLabel(" "+riddle);
        riddleLabel.setForeground(Color.GREEN);
        riddleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        riddleLabel.setFont(new Font("Monospaced", Font.BOLD, 16));

        answerField = new JTextField();
        quitButton=new JButton("Quitter");
        submitButton = new JButton("Valider");
        feedbackLabel = new JLabel("Tentatives : 0/3");
        feedbackLabel.setForeground(Color.WHITE);
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.DARK_GRAY);

        centerPanel.add(riddleLabel);
        centerPanel.add(answerField);
        bottomPanel.add(submitButton);
        centerPanel.add(feedbackLabel);
        bottomPanel.add(quitButton);
        add(bottomPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> Verif());
        quitButton.addActionListener(e -> {
            parent.showScene("scene1");
        });
        this.mot="interface";
    }

    private void Verif() {
        if (resolu || essai >= 3) return;

        String motTape = answerField.getText();

        if (motTape.equals(this.mot)) {
            feedbackLabel.setText("Bonne réponse!" +" Gardez ce Mot en tête : " + this.mot);
            feedbackLabel.setForeground(Color.GREEN);
            resolu = true;



        } else {
            essai++;
            feedbackLabel.setText(" Mauvaise réponse. Tentative " + essai + "/3");
            feedbackLabel.setForeground(Color.RED);

            if (essai == 3) {
                JOptionPane.showMessageDialog(this, "Alerte déclenchée !");
                submitButton.setEnabled(false);
            }
        }
    }

    public boolean isResolu() {
        return resolu;
    }

    public String getSolution() {
        return this.mot;
    }

    public boolean isAlaremdeclenche() {
        return alaremdeclenche;
    }
}