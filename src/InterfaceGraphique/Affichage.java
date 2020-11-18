package InterfaceGraphique;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Affichage extends Component {

    private int mPosX = MouseInfo.getPointerInfo().getLocation().x;
    private int mPosY = MouseInfo.getPointerInfo().getLocation().y;

    private JButton[][] tabButton;


    //objets menu pour afficher les cours dans le planning
    private final JPopupMenu popupMenu = new JPopupMenu();


    JComboBox comboBox;
    JTextField textField;
    JButton button;
    JLabel label;

    JButton buttonReset;


    public static final Color meduimpurple = new Color(147, 112, 219);
    public static final Color thistle = new Color(216, 191, 216);


    private JFrame fenetre = new JFrame("fenetre");
    private JFrame smallFen = new JFrame("Petite Fen");


    // Panneau global qui englobe tout
    private JPanel pan = new JPanel();
    private JPanel panSmall = new JPanel();

    //panneaux pour les jours de la semaines

    private List<JPanel> panels = new ArrayList<>(8);


    //liste des colonnes avec les boutons pour interragir
    private List<JButton> heures = new ArrayList<>();
    private List<JButton> lundi = new ArrayList<>();
    private List<JButton> mardi = new ArrayList<>();
    private List<JButton> mercredi = new ArrayList<>();
    private List<JButton> jeudi = new ArrayList<>();
    private List<JButton> vendredi = new ArrayList<>();
    private List<JButton> samedi = new ArrayList<>();
    private JButton dimanche = new JButton();


    public Affichage() {
    }

    public void afficherFen() {


        //Definition de la fenetre++
        fenetre.setSize((800 * 16 / 9), 800);
        fenetre.setContentPane(pan);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);

        //Layout principal avec les 7 Jours de la semaine
        pan.setLayout(new GridLayout(1, 8));


        //Ajout des panneaux dans la liste
        for (int i = 0; i < 8; i++) {
            panels.add(i, new JPanel());
        }

        //Ajouts des panneaux au grand panneaux
        for (int i = 0; i < panels.size(); i++) {
            pan.add(panels.get(i));
            panels.get(i).setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            panels.get(i).setBackground(Color.GRAY);
            panels.get(i).setLayout(new GridLayout(11, 1));
        }
        panels.get(7).setLayout(new BorderLayout());

        //Creation des boutons dans les jours de la semaine
        for (int i = 0; i < 11; i++) {
            heures.add(new JButton(""));
            lundi.add(new JButton(""));
            mardi.add(new JButton(""));
            mercredi.add(new JButton(""));
            jeudi.add(new JButton(""));
            vendredi.add(new JButton(""));
            samedi.add(new JButton(""));
        }
        //Exception pour dimanche avec seulement un gros bouton
        dimanche.setText("Dimanche, école fermée");


        JLabel labelCours = new JLabel("test ajout cours");

        JLabel test = new JLabel();
        for (JButton jButton : lundi) {
            jButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {


                }

                @Override
                public void mousePressed(MouseEvent e) {
                    popupMenu.add(test);
                    test.setPreferredSize(new Dimension(200, 60));
                    test.setText("Essai d'ajout d'un popup");

                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    popupMenu.isVisible();

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

        }
        for (JButton jButton : lundi) {
            jButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {


                }

                @Override
                public void mousePressed(MouseEvent e) {
                    popupMenu.add(test);
                    popupMenu.add(labelCours);
                    labelCours.setPreferredSize(new Dimension(300, 50));
                    test.setPreferredSize(new Dimension(200, 60));
                    test.setText("Essai d'ajout d'un popup");


                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    popupMenu.isVisible();

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

        }
        for (JButton jButton : mardi) {
            jButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {


                }

                @Override
                public void mousePressed(MouseEvent e) {
                    popupMenu.add(test);
                    test.setPreferredSize(new Dimension(200, 60));
                    test.setText("Essai d'ajout d'un popup");

                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    popupMenu.isVisible();

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

        }

        for (JButton jButton : mercredi) {
            jButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {


                }

                @Override
                public void mousePressed(MouseEvent e) {
                    popupMenu.add(test);
                    test.setPreferredSize(new Dimension(200, 60));
                    test.setText("Essai d'ajout d'un popup");

                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    popupMenu.isVisible();

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

        }
        for (JButton jButton : jeudi) {
            jButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {


                }

                @Override
                public void mousePressed(MouseEvent e) {
                    popupMenu.add(test);
                    test.setPreferredSize(new Dimension(200, 60));
                    test.setText("Essai d'ajout d'un popup");

                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    popupMenu.isVisible();

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

        }
        for (JButton jButton : vendredi) {
            jButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {


                }

                @Override
                public void mousePressed(MouseEvent e) {
                    popupMenu.add(test);
                    test.setPreferredSize(new Dimension(200, 60));
                    test.setText("Essai d'ajout d'un popup");

                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    popupMenu.isVisible();

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

        }
        for (JButton jButton : samedi) {
            jButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {


                }

                @Override
                public void mousePressed(MouseEvent e) {
                    popupMenu.add(test);
                    test.setPreferredSize(new Dimension(200, 60));
                    test.setText("Essai d'ajout d'un popup");

                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    popupMenu.isVisible();

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

        }


        //Ajout des boutons dans les colonnes
        for (int j = 0; j < 11; j++) {
            panels.get(0).add(heures.get(j));
            heures.get(j).setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

            panels.get(1).add(lundi.get(j));
            lundi.get(j).setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));

            panels.get(2).add(mardi.get(j));
            mardi.get(j).setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));

            panels.get(3).add(mercredi.get(j));
            mercredi.get(j).setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));

            panels.get(4).add(jeudi.get(j));
            jeudi.get(j).setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));

            panels.get(5).add(vendredi.get(j));
            vendredi.get(j).setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));

            panels.get(6).add(samedi.get(j));
            samedi.get(j).setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));

            panels.get(7).add(dimanche, BorderLayout.CENTER);
            dimanche.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
            dimanche.setBackground(Color.LIGHT_GRAY);

        }

        //Definition du label dans le bouton avec l'heure
        heures.get(0).setText("8h-9h30");
        heures.get(1).setText("9h30-11h");
        heures.get(3).setText("11h15-12h45");
        heures.get(4).setText("12h45-14h15");
        heures.get(6).setText("14h30-16h");
        heures.get(7).setText("16h-17h30");
        heures.get(9).setText("17h45-19h15");
        heures.get(10).setText("19h15-20h45");

        addColor();


    }

    public void addColor() {
        //Ajout des couleurs sur la colonne des heures
        for (int i = 0; i < 11; i++) {
            heures.get(i).setBackground(new Color(221, 160, 221));

        }
        //remplissage des colonnes pour les couleurs
        for (int i = 0; i < 11; i++) {

            if (i != 2 || i != 5 || i != 8) {
                lundi.get(i).setBackground(thistle);
                mardi.get(i).setBackground(thistle);
                mercredi.get(i).setBackground(thistle);
                jeudi.get(i).setBackground(thistle);
                vendredi.get(i).setBackground(thistle);
                samedi.get(i).setBackground(thistle);
            }

        }
        //Ajout des pauses
        heures.get(2).setText("PAUSE 15min");
        heures.get(2).setBackground(meduimpurple);
        heures.get(2).setForeground(Color.white);
        heures.get(5).setText("PAUSE 15min");
        heures.get(5).setBackground(meduimpurple);
        heures.get(5).setForeground(Color.white);
        heures.get(8).setText("PAUSE 15min");
        heures.get(8).setBackground(meduimpurple);
        heures.get(8).setForeground(Color.white);

        lundi.get(2).setText("PAUSE 15min");
        lundi.get(2).setBackground(meduimpurple);
        lundi.get(2).setForeground(Color.white);
        lundi.get(5).setText("PAUSE 15min");
        lundi.get(5).setBackground(meduimpurple);
        lundi.get(5).setForeground(Color.white);
        lundi.get(8).setText("PAUSE 15min");
        lundi.get(8).setBackground(meduimpurple);
        lundi.get(8).setForeground(Color.white);

        mardi.get(2).setText("PAUSE 15min");
        mardi.get(2).setBackground(meduimpurple);
        mardi.get(2).setForeground(Color.white);
        mardi.get(5).setText("PAUSE 15min");
        mardi.get(5).setBackground(meduimpurple);
        mardi.get(5).setForeground(Color.white);
        mardi.get(8).setText("PAUSE 15min");
        mardi.get(8).setBackground(meduimpurple);
        mardi.get(8).setForeground(Color.white);

        mercredi.get(2).setText("PAUSE 15min");
        mercredi.get(2).setBackground(meduimpurple);
        mercredi.get(2).setForeground(Color.white);
        mercredi.get(5).setText("PAUSE 15min");
        mercredi.get(5).setBackground(meduimpurple);
        mercredi.get(5).setForeground(Color.white);
        mercredi.get(8).setText("PAUSE 15min");
        mercredi.get(8).setBackground(meduimpurple);
        mercredi.get(8).setForeground(Color.white);

        jeudi.get(2).setText("PAUSE 15min");
        jeudi.get(2).setBackground(meduimpurple);
        jeudi.get(2).setForeground(Color.white);
        jeudi.get(5).setText("PAUSE 15min");
        jeudi.get(5).setBackground(meduimpurple);
        jeudi.get(5).setForeground(Color.white);
        jeudi.get(8).setText("PAUSE 15min");
        jeudi.get(8).setBackground(meduimpurple);
        jeudi.get(8).setForeground(Color.white);

        vendredi.get(2).setText("PAUSE 15min");
        vendredi.get(2).setBackground(meduimpurple);
        vendredi.get(2).setForeground(Color.white);
        vendredi.get(5).setText("PAUSE 15min");
        vendredi.get(5).setBackground(meduimpurple);
        vendredi.get(5).setForeground(Color.white);
        vendredi.get(8).setText("PAUSE 15min");
        vendredi.get(8).setBackground(meduimpurple);
        vendredi.get(8).setForeground(Color.white);

        samedi.get(2).setText("PAUSE 15min");
        samedi.get(2).setBackground(meduimpurple);
        samedi.get(2).setForeground(Color.white);
        samedi.get(5).setText("PAUSE 15min");
        samedi.get(5).setBackground(meduimpurple);
        samedi.get(5).setForeground(Color.white);
        samedi.get(8).setText("PAUSE 15min");
        samedi.get(8).setBackground(meduimpurple);
        samedi.get(8).setForeground(Color.white);
    }

    public void afficherSmallFen() {

        smallFen.setBounds(300, 200, 400, 300);
        smallFen.setContentPane(panSmall);
        smallFen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        smallFen.setLocation(1000, 540);

        panSmall.setLayout(null);


        //Ajout de la text box pour saisir les infos
        textField = new JTextField("");
        panSmall.add(textField);
        textField.setBounds(0, 0, 200, 50);

        //ajout du bouton pour valider
        button = new JButton("Rechercher");
        panSmall.add(button);
        button.setBounds(220, 0, 150, 50);

        //ajout du listener du bouton
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = Integer.parseInt(String.valueOf(comboBox.getSelectedIndex()));
                int j = Integer.parseInt(textField.getText());
                lundi.get(i).setBackground(Color.red);
                lundi.get(j).setBackground(Color.red);
            }
        });

        //Ajout d'une combobox

        String[] tab = {"1", "2", "3", "4"};
        comboBox = new JComboBox(tab);
        panSmall.add(comboBox);
        comboBox.setBounds(0, 60, 200, 50);


        //ajout du resetr
        buttonReset = new JButton("Reset");
        panSmall.add(buttonReset);
        buttonReset.setBounds(220, 60, 100, 50);

        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addColor();
                textField.setText(String.valueOf(comboBox.getSelectedIndex() + 1));
                label = new JLabel();
                panSmall.add(label);
            }


        });
    }

    public void showFenetrePrincipale() {
        pan.setBackground(Color.white);
        fenetre.setVisible(true);
    }

    public void showSmallFen() {
        panSmall.setBackground(Color.white);
        smallFen.setVisible(true);
    }

/*
faudra aajouter une list de prof, d'eleve, pur pouvoir faire un menu defilant qui
affichera les profs possible et les eleves et quandon clique on affiche
 */
}
