package src;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Enigme1_DevinettePanel extends JPanel {

    private int attempts = 0;
    private boolean solved = false;
    private boolean alarmTriggered = false;

    private JLabel riddleLabel;
    private JTextField answerField;
    private JButton submitButton;
    private JLabel feedbackLabel;
    private String mot;
    private JButton quitButton;

    public Enigme1_DevinettePanel() {
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
        centerPanel.add(submitButton);
        centerPanel.add(feedbackLabel);
        bottomPanel.add(quitButton);
        add(bottomPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> Verif());
        quitButton.addActionListener(e -> System.exit(0));
        this.mot="interface";
    }

    private void Verif() {
        if (solved || attempts >= 3) return;

        String userInput = answerField.getText();

        if (userInput.equals(this.mot)) {
            feedbackLabel.setText("Bonne réponse!" +" Gardez ce Mot en tête : " + this.mot);
            feedbackLabel.setForeground(Color.GREEN);
            solved = true;




            // Ferme la fenêtre principale

        } else {
            attempts++;
            feedbackLabel.setText(" Mauvaise réponse. Tentative " + attempts + "/3");
            feedbackLabel.setForeground(Color.RED);

            if (attempts == 3) {
                JOptionPane.showMessageDialog(this, "🚨 Alerte déclenchée ! Échec.");
                submitButton.setEnabled(false);
            }
        }
    }

    public boolean isSolved() {
        return solved;
    }

    public String getSolution() {
        return this.mot;
    }

    public boolean isAlarmTriggered() {
        return alarmTriggered;
    }
}

