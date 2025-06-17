package src;

import javax.swing.*;
import java.awt.*;

public class Enigme1_DevinettePanel extends JPanel {

    private int essai = 0;
    private boolean resolu = false;
    private boolean alaremdeclenche = false;
    private graphjeu parent;

    private JLabel DevinetteLabel;
    private JTextField Repzone;
    private JButton AcceptButton;
    private JLabel EssaisLabel;
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

        String devinette = "Je suis une structure en Java qui ne contient que des " +
                "méthodes abstraites et aucune implémentation concrète,je peux etre implemnté dans differents classes";


        DevinetteLabel = new JLabel(" "+devinette);
        DevinetteLabel.setForeground(Color.GREEN);
        DevinetteLabel.setHorizontalAlignment(SwingConstants.CENTER);
        DevinetteLabel.setFont(new Font("Monospaced", Font.BOLD, 16));

        Repzone = new JTextField();
        quitButton=new JButton("Quitter");
        AcceptButton = new JButton("Valider");
        EssaisLabel = new JLabel("Tentatives : 0/3");
        EssaisLabel.setForeground(Color.WHITE);
        EssaisLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.DARK_GRAY);

        centerPanel.add(DevinetteLabel);
        centerPanel.add(Repzone);
        bottomPanel.add(AcceptButton);
        centerPanel.add(EssaisLabel);
        bottomPanel.add(quitButton);
        add(bottomPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);

        AcceptButton.addActionListener(e -> Verif());
        quitButton.addActionListener(e -> {
            parent.showScene("scene1");
        });
        this.mot="interface";
    }

    private void Verif() {
        if (resolu || essai >= 3) return;

        String motTape = Repzone.getText();

        if (motTape.equals(this.mot)) {
            EssaisLabel.setText("Bonne réponse!" +" Gardez ce Mot en tête : " + this.mot);
            EssaisLabel.setForeground(Color.GREEN);
            resolu = true;



        } else {
            essai++;
            EssaisLabel.setText(" Mauvaise réponse. Tentative " + essai + "/3");
            EssaisLabel.setForeground(Color.RED);

            if (essai == 3) {
                JOptionPane.showMessageDialog(this, "Alerte déclenchée !");
                AcceptButton.setEnabled(false);
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