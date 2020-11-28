package view;

import Models.Session;
import controller.MainFrameController;
import controller.SecondFrameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MainFrameView implements Observer {
    private int mPosX = MouseInfo.getPointerInfo().getLocation().x;
    private int mPosY = MouseInfo.getPointerInfo().getLocation().y;

    public static final Color bleuClair = new Color(51, 153, 255);
    public static final Color colorTotoBubuSamsam = new Color(50, 179, 190);
    public static final Color bleuPale = new Color(153, 204, 255);

    private JFrame fenetre = new JFrame("fenetre");


    // Panneau global qui englobe tout
    private JPanel pan = new JPanel();

    //panneaux pour les jours de la semaines

    private java.util.List<JPanel> panels = new ArrayList<>(8);

    //liste des colonnes avec les boutons pour interragir
    private java.util.List<JButton> heures = new ArrayList<>();
    private java.util.List<JButton> lundi = new ArrayList<>();
    private java.util.List<JButton> mardi = new ArrayList<>();
    private java.util.List<JButton> mercredi = new ArrayList<>();
    private java.util.List<JButton> jeudi = new ArrayList<>();
    private java.util.List<JButton> vendredi = new ArrayList<>();
    private java.util.List<JButton> samedi = new ArrayList<>();
    private JButton dimanche = new JButton();

    private List<Session> lundiSession = new ArrayList<>();
    private List<Session> mardiSession = new ArrayList<>();
    private List<Session> mercrediSession = new ArrayList<>();
    private List<Session> jeudiSession = new ArrayList<>();
    private List<Session> vendrediSession = new ArrayList<>();
    private List<Session> samediSession = new ArrayList<>();


    MainFrameController mainFrameController;
    JLabel labelCourse = new JLabel();

    public MainFrameView(MainFrameController mainFrameController) {

        this.mainFrameController = mainFrameController;
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
            dimanche.setBackground(bleuClair);

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

        pan.setBackground(Color.white);
        fenetre.setVisible(true);
        addColor();


        lundi.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrameController.getPromotion(4L);
            }
        });
        dimanche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrameController.getSession();
            }
        });


       /* for (JButton button : lundi){

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrameController.getSessionContent(lundi.indexOf(button))
                }
            });
        }*/


        /*
        NE PAS OUBLIER DE REMETTRE EN VISIBLE
         */

            fenetre.setVisible(false);
    }

    public void addColor() {
        //Ajout des couleurs sur la colonne des heures
        for (int i = 0; i < 11; i++) {
            heures.get(i).setBackground(new Color(102, 178, 255));
        }
        //remplissage des colonnes pour les couleurs
        for (int i = 0; i < 11; i++) {

            if (i != 2 || i != 5 || i != 8) {
                lundi.get(i).setBackground(bleuPale);
                mardi.get(i).setBackground(bleuPale);
                mercredi.get(i).setBackground(bleuPale);
                jeudi.get(i).setBackground(bleuPale);
                vendredi.get(i).setBackground(bleuPale);
                samedi.get(i).setBackground(bleuPale);
            }

        }
        //Ajout des pauses
        heures.get(2).setText("PAUSE 15min");
        heures.get(2).setBackground(bleuClair);
        heures.get(2).setForeground(Color.white);
        heures.get(5).setText("PAUSE 15min");
        heures.get(5).setBackground(bleuClair);
        heures.get(5).setForeground(Color.white);
        heures.get(8).setText("PAUSE 15min");
        heures.get(8).setBackground(bleuClair);
        heures.get(8).setForeground(Color.white);

        lundi.get(2).setText("PAUSE 15min");
        lundi.get(2).setBackground(bleuClair);
        lundi.get(2).setForeground(Color.white);
        lundi.get(5).setText("PAUSE 15min");
        lundi.get(5).setBackground(bleuClair);
        lundi.get(5).setForeground(Color.white);
        lundi.get(8).setText("PAUSE 15min");
        lundi.get(8).setBackground(bleuClair);
        lundi.get(8).setForeground(Color.white);

        mardi.get(2).setText("PAUSE 15min");
        mardi.get(2).setBackground(bleuClair);
        mardi.get(2).setForeground(Color.white);
        mardi.get(5).setText("PAUSE 15min");
        mardi.get(5).setBackground(bleuClair);
        mardi.get(5).setForeground(Color.white);
        mardi.get(8).setText("PAUSE 15min");
        mardi.get(8).setBackground(bleuClair);
        mardi.get(8).setForeground(Color.white);

        mercredi.get(2).setText("PAUSE 15min");
        mercredi.get(2).setBackground(bleuClair);
        mercredi.get(2).setForeground(Color.white);
        mercredi.get(5).setText("PAUSE 15min");
        mercredi.get(5).setBackground(bleuClair);
        mercredi.get(5).setForeground(Color.white);
        mercredi.get(8).setText("PAUSE 15min");
        mercredi.get(8).setBackground(bleuClair);
        mercredi.get(8).setForeground(Color.white);

        jeudi.get(2).setText("PAUSE 15min");
        jeudi.get(2).setBackground(bleuClair);
        jeudi.get(2).setForeground(Color.white);
        jeudi.get(5).setText("PAUSE 15min");
        jeudi.get(5).setBackground(bleuClair);
        jeudi.get(5).setForeground(Color.white);
        jeudi.get(8).setText("PAUSE 15min");
        jeudi.get(8).setBackground(bleuClair);
        jeudi.get(8).setForeground(Color.white);

        vendredi.get(2).setText("PAUSE 15min");
        vendredi.get(2).setBackground(bleuClair);
        vendredi.get(2).setForeground(Color.white);
        vendredi.get(5).setText("PAUSE 15min");
        vendredi.get(5).setBackground(bleuClair);
        vendredi.get(5).setForeground(Color.white);
        vendredi.get(8).setText("PAUSE 15min");
        vendredi.get(8).setBackground(bleuClair);
        vendredi.get(8).setForeground(Color.white);

        samedi.get(2).setText("PAUSE 15min");
        samedi.get(2).setBackground(bleuClair);
        samedi.get(2).setForeground(Color.white);
        samedi.get(5).setText("PAUSE 15min");
        samedi.get(5).setBackground(bleuClair);
        samedi.get(5).setForeground(Color.white);
        samedi.get(8).setText("PAUSE 15min");
        samedi.get(8).setBackground(bleuClair);
        samedi.get(8).setForeground(Color.white);


    }

    public void getCouleurFromCourseType(List<JButton> list,Session session, int index){

        switch (Math.toIntExact(session.getID_course())){
            case 1:
                list.get(index).setBackground(colorTotoBubuSamsam);
                break;
            case 2:
                list.get(index).setBackground(Color.ORANGE);
                break;
            case 3:
                list.get(index).setBackground(Color.red);
                break;
            case 4:
                list.get(index).setBackground(Color.green);
                break;
            case 5:

                break;

            default:
                break;
        }
    }

    public int getIntFromSessionHour(Session session){

        LocalTime startTime;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        startTime = LocalTime.parse(session.getStartTime(), timeFormatter);
        int i =0;

        switch (startTime.getHour()){
            case 8:
                i = 0;
                break;
            case 9:
                i = 1;
                break;

            case 11:
                i = 3;
                break;
            case 12:
                i = 4;
                break;

            case 14:
                i = 6;
                break;

            case 16:
                i = 7;
                break;
            case 17:
                i = 9;
                break;

            case 19:
                i = 10;
                break;

            default:
                break;
        }
        return i;
    }

    public  void showSession(){
        lundiSession = mainFrameController.getSessionLundi();
        mardiSession = mainFrameController.getSessionMardi();
        mercrediSession = mainFrameController.getSessionMercredi();
        jeudiSession = mainFrameController.getSessionJeudi();
        vendrediSession = mainFrameController.getSessionVendredi();
        samediSession = mainFrameController.getSessionSamedi();



        for (Session s : lundiSession) {
            LocalTime startTime;
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            startTime = LocalTime.parse(s.getStartTime(), timeFormatter);

            getCouleurFromCourseType(lundi, s, getIntFromSessionHour(s));


        }
        for (Session s : mardiSession) {
            LocalTime startTime;
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            startTime = LocalTime.parse(s.getStartTime(), timeFormatter);

            getCouleurFromCourseType(mardi, s, getIntFromSessionHour(s));

        }
        for (Session s : mercrediSession) {
            LocalTime startTime;
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            startTime = LocalTime.parse(s.getStartTime(), timeFormatter);

            getCouleurFromCourseType(mercredi, s, getIntFromSessionHour(s));

        }
        for (Session s : jeudiSession) {
            LocalTime startTime;
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            startTime = LocalTime.parse(s.getStartTime(), timeFormatter);

            getCouleurFromCourseType(jeudi, s, getIntFromSessionHour(s));

        }
        for (Session s : vendrediSession) {
            LocalTime startTime;
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            startTime = LocalTime.parse(s.getStartTime(), timeFormatter);

            getCouleurFromCourseType(vendredi, s, getIntFromSessionHour(s));

        }
        for (Session s : samediSession) {
            LocalTime startTime;
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            startTime = LocalTime.parse(s.getStartTime(), timeFormatter);

            getCouleurFromCourseType(samedi, s, getIntFromSessionHour(s));

        }
    }


    @Override
    public void update(Observable o, Object arg) {

        if(o instanceof SecondFrameController){
               // showSession();
        }
        

    }
}
