package src;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class codeAsci extends JPanel implements ActionListener {
    private JTextField saisie;
    private JButton verif;

    public codeAsci() {
        this.setPreferredSize(new Dimension(300, 150));
        this.setLayout((LayoutManager)null);
        this.saisie = new JTextField(40);
        this.saisie.setBounds(10, 80, 250, 25);
        this.add(this.saisie);
        this.verif = new JButton("Vérifier");
        this.verif.setBounds(110, 110, 80, 25);
        this.add(this.verif);
        this.verif.addActionListener(this);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("67 72 69 82 67 72 69 82 67 72 69 82 67 72 69", 15, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
