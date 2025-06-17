package src;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GrilleMorpion extends JPanel {
    int[][] matrice = new int[3][3];
    boolean tourJoueur = true;

    public GrilleMorpion() {
        // Fixer la taille à 300x300 pixels
        this.setMaximumSize(new Dimension(300, 300));
        this.setSize(300, 300);
        this.addMouseListener(new souris(this));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Utiliser une taille fixe de 300 pixels au lieu de getWidth()
        int taille = 300;
        int cases = 3;
        int caseSize = taille / cases;

        // Dessiner la grille
        for(int i = 1; i < cases; ++i) {
            int x = i * caseSize;
            g.drawLine(x, 0, x, taille);
            g.drawLine(0, x, taille, x);
        }

        // Dessiner les X et O
        for(int i = 0; i < cases; ++i) {
            for(int j = 0; j < cases; ++j) {
                int x = j * caseSize;
                int y = i * caseSize;
                if (this.matrice[i][j] == 1) {
                    // Dessiner O
                    g.drawOval(x + 20, y + 20, caseSize - 40, caseSize - 40);
                } else if (this.matrice[i][j] == -1) {
                    // Dessiner X
                    g.drawLine(x + 20, y + 20, x + caseSize - 20, y + caseSize - 20);
                    g.drawLine(x + caseSize - 20, y + 20, x + 20, y + caseSize - 20);
                }
            }
        }
    }

    public void test(int x, int y) {
        // Utiliser une taille fixe de 300 pixels
        int caseSize = 300 / 3;
        int i = y / caseSize;
        int j = x / caseSize;
        if (this.tourJoueur && this.matrice[i][j] == 0) {
            this.matrice[i][j] = 1;
            this.tourJoueur = false;
            this.repaint();
            if (!this.verifFin()) {
                this.ordiJoue();
            }
        }
    }

    public boolean victoire(int joueur) {
        for(int i = 0; i < 3; ++i) {
            if (this.matrice[i][0] == joueur && this.matrice[i][1] == joueur && this.matrice[i][2] == joueur) {
                return true;
            }

            if (this.matrice[0][i] == joueur && this.matrice[1][i] == joueur && this.matrice[2][i] == joueur) {
                return true;
            }
        }

        if (this.matrice[0][0] == joueur && this.matrice[1][1] == joueur && this.matrice[2][2] == joueur) {
            return true;
        } else if (this.matrice[0][2] == joueur && this.matrice[1][1] == joueur && this.matrice[2][0] == joueur) {
            return true;
        } else {
            return false;
        }
    }

    public void ordiJoue() {
        // Essayer de gagner
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                if (this.matrice[i][j] == 0) {
                    this.matrice[i][j] = -1;
                    if (this.victoire(-1)) {
                        this.tourJoueur = true;
                        this.repaint();
                        this.verifFin();
                        return;
                    }
                    this.matrice[i][j] = 0;
                }
            }
        }

        // Essayer de bloquer le joueur
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                if (this.matrice[i][j] == 0) {
                    this.matrice[i][j] = 1;
                    if (this.victoire(1)) {
                        this.matrice[i][j] = -1;
                        this.tourJoueur = true;
                        this.repaint();
                        this.verifFin();
                        return;
                    }
                    this.matrice[i][j] = 0;
                }
            }
        }

        // Jouer n'importe où
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                if (this.matrice[i][j] == 0) {
                    this.matrice[i][j] = -1;
                    this.tourJoueur = true;
                    this.repaint();
                    this.verifFin();
                    return;
                }
            }
        }
    }

    public boolean verifFin() {
        for(int joueur : new int[]{1, -1}) {
            for(int i = 0; i < 3; ++i) {
                if (this.matrice[i][0] == joueur && this.matrice[i][1] == joueur && this.matrice[i][2] == joueur || this.matrice[0][i] == joueur && this.matrice[1][i] == joueur && this.matrice[2][i] == joueur) {
                    JOptionPane.showMessageDialog(this, joueur == 1 ? "Le joueur a gagné!\nCode : implémentation" : "L'ordinateur a gagné!");
                    this.reset();
                    return true;
                }
            }

            if (this.matrice[0][0] == joueur && this.matrice[1][1] == joueur && this.matrice[2][2] == joueur || this.matrice[0][2] == joueur && this.matrice[1][1] == joueur && this.matrice[2][0] == joueur) {
                JOptionPane.showMessageDialog(this, joueur == 1 ? "Le joueur a gagné!\nCode : implémentation" : "L'ordinateur a gagné!");
                this.reset();
                return true;
            }
        }

        boolean plein = true;
        for(int[] ligne : this.matrice) {
            for(int c : ligne) {
                if (c == 0) {
                    plein = false;
                }
            }
        }

        if (plein) {
            JOptionPane.showMessageDialog(this, "Égalité !");
            this.reset();
            return true;
        } else {
            return false;
        }
    }

    public void reset() {
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.matrice[i][j] = 0;
            }
        }
        this.tourJoueur = true;
        this.repaint();
    }

    private class souris implements MouseListener {
        private GrilleMorpion grille;

        public souris(GrilleMorpion grilleMorpion) {
            this.grille = grilleMorpion;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            grille.test(e.getX(), e.getY());
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // Pas besoin d'implémentation pour ce jeu
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // Pas besoin d'implémentation pour ce jeu
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // Pas besoin d'implémentation pour ce jeu
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // Pas besoin d'implémentation pour ce jeu
        }
    }
}