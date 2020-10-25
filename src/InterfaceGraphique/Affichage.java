package InterfaceGraphique;

import javax.swing.*;
import java.awt.*;

public class Affichage {
    private JFrame fenetre = new JFrame("fenetre");
    private JPanel pan = new JPanel();
    private JButton bouton1 = new JButton("Lundi");
    private JButton bouton2 = new JButton("Mardi");
    private JButton bouton3 = new JButton("Mercredi");
    private JButton bouton4 = new JButton("Jeudi");
    private JButton bouton5 = new JButton("Vendredi");
    private JButton bouton6 = new JButton("Samedi");
    private JButton bouton7 = new JButton("Dimanche");
    private JTextField textField = new JTextField("Text dans le bouton");

    public Affichage() {
        fenetre.setSize(1062, 600);
        fenetre.setContentPane(pan);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pan.setLayout(new GridLayout());
        pan.add(bouton1);
        bouton1.setLayout(new BorderLayout());
        bouton1.add(textField, BorderLayout.NORTH);
        pan.add(bouton2);
        pan.add(bouton3);
        pan.add(bouton4);
        pan.add(bouton5);
        pan.add(bouton6);
        pan.add(bouton7);

    }

    public void show() {
        pan.setBackground(Color.white);
        fenetre.setVisible(true);
    }


}
