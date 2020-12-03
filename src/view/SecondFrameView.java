package view;

import models.*;
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
import java.util.Objects;

import static java.lang.String.valueOf;

public class SecondFrameView {


    /*
    VARIABLES POUR LA GRANDE FENETRE
     */
    public static final Color bleuClair = new Color(51, 153, 255);
    public static final Color bleuPale = new Color(153, 204, 255);

    /*
    Couleurs pour les cours
     */
    public static final Color rougeClair = new Color(254, 76, 47);
    public static final Color saumon = new Color(255, 181, 139);
    public static final Color kaki = new Color(200, 156, 73);
    public static final Color jaunePale = new Color(255, 242, 109);
    public static final Color vertClair = new Color(194, 246, 17);
    public static final Color violetPale = new Color(188, 132, 254);
    public static final Color vertBleu = new Color(17, 246, 128);
    public static final Color bleuCiel = new Color(132, 212, 254);
    public static final Color vertPomme = new Color(65, 141, 43);


    private final JFrame mainFrame = new JFrame("Emploi du temps");


    // Panneau global qui englobe tout
    private final JPanel mainFramePanel = new JPanel();

    //panneaux pour les jours de la semaines

    private final java.util.List<JPanel> listPanels = new ArrayList<>(8);

    //liste des colonnes avec les boutons pour interragir
    private final java.util.List<JButton> heures = new ArrayList<>();
    private final java.util.List<JButton> lundi = new ArrayList<>();
    private final java.util.List<JButton> mardi = new ArrayList<>();
    private final java.util.List<JButton> mercredi = new ArrayList<>();
    private final java.util.List<JButton> jeudi = new ArrayList<>();
    private final java.util.List<JButton> vendredi = new ArrayList<>();
    private final java.util.List<JButton> samedi = new ArrayList<>();
    private final JButton dimanche = new JButton();

    private java.util.List<Session> lundiSession = new ArrayList<>();
    private java.util.List<Session> mardiSession = new ArrayList<>();
    private java.util.List<Session> mercrediSession = new ArrayList<>();
    private java.util.List<Session> jeudiSession = new ArrayList<>();
    private java.util.List<Session> vendrediSession = new ArrayList<>();
    private List<Session> samediSession = new ArrayList<>();


    /*
    VARIABLES POUR LA FENTRE DE CONTROLLE
     */


    JPasswordField textFieldPssword;
    JTextField textFieldConnexion;
    JButton buttonSearch;
    JButton buttonQuit;
    public Long id;

    User user = new User();


    private final JFrame smallFen = new JFrame("Petite Fen");

    private final JPanel panSmall = new JPanel();

    private final JFrame referentFrame = new JFrame("Referent Frame");


    private final SecondFrameController secondFrameController;
    private final MainFrameController mainFrameController;


    /*-------------------------------------------------------------------------------------------*/
    JFrame adminFrame = new JFrame("Admin fenetre");
    JButton addTeacher = new JButton();
    JButton removeTeacher = new JButton();
    JButton addStudent = new JButton();
    JButton removeStudent = new JButton();
    JButton addSession = new JButton();
    JButton removeSession = new JButton();
    JButton consultData = new JButton();
    JButton deconnexion = new JButton();
    JLabel labelAdminInfo = new JLabel();
    /*-------------------------------------------------------------------------------------------*/
    JFrame studentFrame = new JFrame("Student Frame");
    JPanel studentPanel = new JPanel();
    JFrame teacherFrame = new JFrame("Teacher Frame");
    JPanel teacherPanel = new JPanel();
    JPanel referentPanel = new JPanel();

    /**
     * Constructeur de la classe
     * Prend en parametre les deux controleurs et affiche les deux fenetres à l'execution
     * ajoute les listeners sur tous les boutons de l'emploi du temps
     */
    public SecondFrameView(SecondFrameController secondFrameController, MainFrameController mainFrameController) {

        this.mainFrameController = mainFrameController;
        this.secondFrameController = secondFrameController;
        showMainFrame();
        connexionUser();

    }

    /**
     * Fenetre de connexion
     */
    public void connexionUser() {

        smallFen.setBounds(300, 200, 600, 300);
        smallFen.setContentPane(panSmall);
        smallFen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        smallFen.setLocation(1000, 540);

        panSmall.removeAll();
        SwingUtilities.updateComponentTreeUI(smallFen);
        panSmall.setLayout(null);


        JLabel jlabelConnexion = new JLabel("E-mail");
        panSmall.add(jlabelConnexion);
        jlabelConnexion.setBounds(55, 0, 160, 50);

        JLabel jLabelPassword = new JLabel("Password : ");
        panSmall.add(jLabelPassword);
        jLabelPassword.setBounds(55, 60, 160, 50);


        //Ajout de la text box pour saisir les infos
        textFieldConnexion = new JTextField("");
        panSmall.add(textFieldConnexion);
        textFieldConnexion.setBounds(180, 0, 200, 50);

        //ajout du bouton pour valider
        buttonSearch = new JButton("Connect");
        panSmall.add(buttonSearch);
        buttonSearch.setBounds(400, 0, 150, 50);

        //ajout du bouton pour quitter
        buttonQuit = new JButton("Quit");
        panSmall.add(buttonQuit);
        buttonQuit.setBounds(400, 60, 150, 50);


        /*///////////////////////////////// */
        textFieldPssword = new JPasswordField();
        panSmall.add(textFieldPssword);
        textFieldPssword.setEchoChar('*');
        textFieldPssword.setBounds(180, 60, 200, 50);


        panSmall.setBackground(Color.white);
        smallFen.setVisible(true);


        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addColor();
                resetButtons();
                user = secondFrameController.getUser(textFieldConnexion.getText(), textFieldPssword.getText());
                try {
                    switch (user.getPermission()) {
                        case "ADMIN":
                            adminFrame();
                            break;
                        case "ENSEIGNANT":
                            teacherFrame();
                            break;
                        case "REFERENT":
                            referentFrame();
                            break;
                        case "ELEVE":
                            studentFrame();
                            break;
                        default:
                            break;
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Identification incorrecte, veuillez réessayer. ");
                }
            }
        });

        buttonQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


    }


    /**
     * Affichage de l'emploi du temps
     * la fonction est appelee lors du lancement du programme
     */
    public void showMainFrame() {


        mainFrame.setVisible(true);


        //Definition de la fenetre++
        mainFrame.setSize((800 * 16 / 9), 800);
        mainFrame.setContentPane(mainFramePanel);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);

        //Layout principal avec les 7 Jours de la semaine
        mainFramePanel.setLayout(new GridLayout(1, 8));

        //Ajout des panneaux dans la liste
        for (int i = 0; i < 8; i++) {
            listPanels.add(i, new JPanel());
        }

        //Ajouts des panneaux au grand panneaux
        for (JPanel panel : listPanels) {
            mainFramePanel.add(panel);
            panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            panel.setBackground(Color.GRAY);
            panel.setLayout(new GridLayout(11, 1));
        }
        listPanels.get(7).setLayout(new BorderLayout());

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
            listPanels.get(0).add(heures.get(j));
            heures.get(j).setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

            listPanels.get(1).add(lundi.get(j));
            lundi.get(j).setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));

            listPanels.get(2).add(mardi.get(j));
            mardi.get(j).setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));

            listPanels.get(3).add(mercredi.get(j));
            mercredi.get(j).setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));

            listPanels.get(4).add(jeudi.get(j));
            jeudi.get(j).setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));

            listPanels.get(5).add(vendredi.get(j));
            vendredi.get(j).setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));

            listPanels.get(6).add(samedi.get(j));
            samedi.get(j).setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));

            listPanels.get(7).add(dimanche, BorderLayout.CENTER);
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

        mainFramePanel.setBackground(Color.white);
        mainFrame.setVisible(true);
        addColor();


    }

















    /**
     * permet de remettre le texte des boutons a 0 pour un nouvel affichage
     */
    public void resetButtons() {

        for (JButton b : lundi) {
            b.setText("");
        }
        for (JButton b : mardi) {
            b.setText("");
        }
        for (JButton b : mercredi) {
            b.setText("");
        }
        for (JButton b : jeudi) {
            b.setText("");
        }
        for (JButton b : vendredi) {
            b.setText("");
        }
        for (JButton b : samedi) {
            b.setText("");
        }

    }

    /**
     * fonction qui change la couleur de tous les boutons de l'emploi du temps
     */
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













    /**
     * Fenetre menu lorsque l'utilisateur est admin
     */
    public void adminFrame() {

        smallFen.setVisible(false);
        JPanel adminPanel = new JPanel(new GridLayout(6, 2));
        adminFrame.setVisible(true);

        adminFrame.setContentPane(adminPanel);

        adminFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        adminFrame.setBounds(300, 200, 817, 417);
        adminFrame.setLocation(1000, 540);


        adminPanel.add(addTeacher);

        addTeacher.setText("Add teacher");

        adminPanel.add(removeTeacher);

        removeTeacher.setText("Remove teacher");

        /*///////////////////////////////////////////////////////////*/
        adminPanel.add(addStudent);

        addStudent.setText("Add student ");

        adminPanel.add(removeStudent);

        removeStudent.setText("Remove student");

        /*///////////////////////////////////////////////////////////*/

        adminPanel.add(addSession);

        addSession.setText("Add session");


        adminPanel.add(removeSession);

        removeSession.setText("Remove session");


        /*/////////////////////////////////////////////////////*/

        adminPanel.add(consultData);
        consultData.setText(("Consult data"));

        /*///////////////////////////////////////////////////////////*/

        adminPanel.add(deconnexion);
        deconnexion.setText("Disconnect");

        /*///////////////////////////////////////////////////////////*/

        adminPanel.add(labelAdminInfo);

        labelAdminInfo.setText("Session opened as : " + user.getLast_name() + " " + user.getFirst_name());
        labelAdminInfo.setFont(new Font(labelAdminInfo.getName(), Font.BOLD, 16));


        /*///////////////////////////////////////////////////////////*/


        addSession.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(adminFrame);

            }
        });

        addTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(adminFrame);
                addTeacherAdmin();
            }
        });

        addStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(adminFrame);
                addStudentAdmin();
            }
        });

        removeStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(adminFrame);
                removeStudentAdmin();
            }
        });

        addSession.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(adminFrame);
                addSessionAdmin();

            }
        });

        removeSession.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(adminFrame);
                removeSessionAdmin();
            }
        });

        consultData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(adminFrame);
                dataConsulting();
            }
        });

        deconnexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                adminFrame.setVisible(false);
                adminPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(adminFrame);

                connexionUser();
            }
        });

        removeTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(adminFrame);
                removeTeacherAdmin();
            }
        });

    }


    /**
     * Fenetre menu lorsque l'utilisateur est un eleve
     */
    public void studentFrame() {
        smallFen.setVisible(false);
        studentPanel.setLayout(new GridLayout(4, 1));

        studentFrame.setBounds(0, 0, 460, 400);
        studentFrame.setLocation(1000, 540);
        studentFrame.setContentPane(studentPanel);

        //premiere page
        //-------------------------------------------


        JButton viewPlaning1 = new JButton();
        studentPanel.add(viewPlaning1);
        viewPlaning1.setText("View planning");
        viewPlaning1.setFont(new Font("Arial", Font.BOLD, 20));

        JButton viewSessionTable = new JButton();
        studentPanel.add(viewSessionTable);
        viewSessionTable.setText("View session table");
        viewSessionTable.setFont(new Font("Arial", Font.BOLD, 20));

        JButton deconnexionStudent = new JButton();
        studentPanel.add(deconnexionStudent);
        deconnexionStudent.setText("Deconnexion");
        deconnexionStudent.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel labelStudentName = new JLabel("User : " + user.getLast_name() + " " + user.getFirst_name(), SwingConstants.CENTER);
        studentPanel.add(labelStudentName);
        labelStudentName.setFont(new Font("Arial", Font.BOLD, 25));


        //--------------------------------------------


        studentFrame.setVisible(true);

        studentFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        viewPlaning1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(studentFrame);
                viewPlanningStudent();
            }
        });

        viewSessionTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(studentFrame);
                viewSessionTableStudent();
            }
        });

        deconnexionStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentFrame.setVisible(false);
                studentPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(studentFrame);

                connexionUser();
            }
        });


    }

    /**
     * Fenetre menu lorsque l'utilisateur est un enseignant
     */
    public void teacherFrame() {
        smallFen.setVisible(false);
        teacherPanel.setLayout(new GridLayout(3, 1));

        teacherFrame.setBounds(0, 0, 460, 400);
        teacherFrame.setLocation(1000, 540);
        teacherFrame.setContentPane(teacherPanel);

        //premiere page
        //-------------------------------------------
        JButton viewPlaning = new JButton();
        teacherPanel.add(viewPlaning);
        viewPlaning.setText("view planning");

        JButton viewSessionTable = new JButton();
        teacherPanel.add(viewSessionTable);
        viewSessionTable.setText("view table session");

        JButton deconnexionTeacher = new JButton();
        teacherPanel.add(deconnexionTeacher);
        deconnexionTeacher.setText("Deconnexion");

        //--------------------------------------------

        teacherFrame.setVisible(true);
        teacherPanel.setLayout(null);
        teacherFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        viewPlaning.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teacherPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(teacherFrame);
                viewPlanningTeacher();
            }
        });

        viewSessionTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teacherPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(teacherFrame);
                viewSessionTeacherWeek();
            }
        });

        deconnexionTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teacherFrame.setVisible(false);
                teacherPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(teacherPanel);

                connexionUser();
            }
        });

    }

    /**
     * fenetre principale pour le refernt pedagogique
     */

    public void referentFrame() {
        smallFen.setVisible(false);

        referentFrame.setBounds(0, 0, 500, 400);
        referentFrame.setLocation(1000, 540);
        referentFrame.setVisible(true);

        referentFrame.setContentPane(referentPanel);
        referentPanel.setLayout(new GridLayout(4, 2));

        JButton buttonShowTeacher = new JButton("Show Teacher Planning");
        JButton buttonShowStudent = new JButton("Show Student Planning");
        JButton buttonAddSessionTeacher = new JButton("Add Session Teacher");
        JButton buttonAddSessionStudent = new JButton("Add Session Student");
        JButton buttonShowAllTeachers = new JButton("Show All Teachers");
        JButton buttonShowAllStudents = new JButton("Show All Students");


        JButton buttonDisconnect = new JButton("Disconnect");
        JLabel labelReferentName = new JLabel("Connected as : " + user.getLast_name().toUpperCase() + " " + user.getFirst_name().toUpperCase());
        labelReferentName.setFont(new Font(" Arial ", Font.BOLD, 15));

        referentPanel.add(buttonShowTeacher);
        referentPanel.add(buttonShowStudent);
        referentPanel.add(buttonAddSessionTeacher);
        referentPanel.add(buttonAddSessionStudent);
        referentPanel.add(buttonShowAllTeachers);
        referentPanel.add(buttonShowAllStudents);
        referentPanel.add(buttonDisconnect);
        referentPanel.add(labelReferentName);


        referentFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        buttonShowTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                referentPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(referentFrame);
                showTableTeacher();
            }
        });
        buttonShowAllStudents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                referentPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(referentFrame);
                showTableStudent();
            }
        });
    }
















    /**
     * Fenetre de choix de semaine pour afficher le planning de l'eleve
     */
    public void viewPlanningStudent() {
        JButton buttonReturn = new JButton("Return");


        JButton buttonSearch = new JButton("Search");
        studentPanel.setLayout(new GridLayout(3, 1));

        String[] tabWeek = new String[52]; // on recupere le nombre de semaines dispo dans edt (semaines dans une année)
        int nb = 1;
        for (int i = 0; i < tabWeek.length; i++) {
            tabWeek[i] = valueOf(nb + i);
        }
        JComboBox comboBoxStudent = new JComboBox(tabWeek);
        studentPanel.add(comboBoxStudent);

        studentPanel.add(buttonSearch);
        studentPanel.add(buttonReturn);

        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                addColor();
                resetButtons();
                showSessionStudent(Objects.requireNonNull(comboBoxStudent.getSelectedItem()).toString());
                showSessionsDetails();
            }
        });

        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(studentFrame);
                studentFrame();
            }
        });


    }

    public void viewSessionTableStudent() {
        JButton buttonReturn = new JButton("Return");


        JButton buttonSearch = new JButton("Search");
        studentPanel.setLayout(new GridLayout(3, 1));

        String[] tabWeek = new String[52]; // on recupere le nombre de semaines dispo dans edt (semaines dans une année)
        int nb = 1;
        for (int i = 0; i < tabWeek.length; i++) {
            tabWeek[i] = valueOf(nb + i);
        }
        JComboBox comboBoxStudent = new JComboBox(tabWeek);
        studentPanel.add(comboBoxStudent);

        studentPanel.add(buttonSearch);
        studentPanel.add(buttonReturn);

        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                showTableStudentSession(comboBoxStudent.getSelectedItem().toString());

            }
        });

        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(studentFrame);
                studentFrame();
            }
        });




    }

    public void showTableStudentSession(String item) {
        JPanel panelTable = new JPanel();

        studentFrame.setContentPane(panelTable);
        panelTable.setLayout(new BorderLayout());

        String[][] table = new String[mainFrameController.getSessionByWeekForStudent(user, item ).size()][8];

        for (int i = 0; i < mainFrameController.getSessionByWeekForStudent(user, item ).size(); i++) {

            table[i][0] = String.valueOf(mainFrameController.getSessionByWeekForStudent(user, item ).get(i).getID());
            table[i][1] = String.valueOf(mainFrameController.getSessionByWeekForStudent(user, item ).get(i).getWeek());
            table[i][2] = String.valueOf(mainFrameController.getSessionByWeekForStudent(user, item ).get(i).getStartTime());
            table[i][3] = String.valueOf(mainFrameController.getSessionByWeekForStudent(user, item ).get(i).getEndTime());
            table[i][4] = String.valueOf(mainFrameController.getSessionByWeekForStudent(user, item ).get(i).getState());
            table[i][5] = String.valueOf(mainFrameController.getSessionByWeekForStudent(user, item ).get(i).getDate());
            table[i][6] = String.valueOf(mainFrameController.getSessionByWeekForStudent(user, item ).get(i).getID_type());
            table[i][7] = String.valueOf(mainFrameController.getSessionByWeekForStudent(user, item ).get(i).getID_course());

        }

        String[] entetes = {"ID", "Week", "Heure de début", "Heure de fin", "Etat","Date", "ID_Type", "ID_course"};


        JButton buttonReturn = new JButton("Return");



        JTable jTable = new JTable(table, entetes);

        panelTable.add(jTable.getTableHeader(), BorderLayout.NORTH);
        panelTable.add(jTable, BorderLayout.CENTER);
        panelTable.add(buttonReturn, BorderLayout.SOUTH);

        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(referentFrame);
                studentFrame();

            }
        });

        studentFrame.pack();
    }






    /**
     * Fenetre de choix de l'afgfichage de l'emploi du temps en fonction
     * de la semaine selectionnee dans la combobox
     */
    public void viewPlanningTeacher() {
        JButton buttonSearch = new JButton("search");
        JButton buttonReturn = new JButton("Return");

        teacherPanel.setLayout(new GridLayout(3, 1));
        String[] tabWeek = new String[52]; // on recupere le nombre de semaines dispo dans edt (semaines dans une année)
        int nb = 1;
        for (int i = 0; i < tabWeek.length; i++) {
            tabWeek[i] = valueOf(nb + i);
        }
        JComboBox comboBoxTeacher;
        comboBoxTeacher = new JComboBox(tabWeek);
        teacherPanel.add(comboBoxTeacher);
        teacherPanel.add(buttonSearch);
        teacherPanel.add(buttonReturn);

        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addColor();
                SwingUtilities.updateComponentTreeUI(teacherFrame);
                showSessionTeacher(Objects.requireNonNull(comboBoxTeacher.getSelectedItem()).toString());
            }
        });

        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teacherPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(teacherFrame);
                teacherFrame();
            }
        });

    }




    public void viewSessionTeacherWeek( ) {
        JButton buttonSearch = new JButton("search");
        JButton buttonReturn = new JButton("Return");

        teacherPanel.setLayout(new GridLayout(3, 1));
        String[] tabWeek = new String[52]; // on recupere le nombre de semaines dispo dans edt (semaines dans une année)
        int nb = 1;
        for (int i = 0; i < tabWeek.length; i++) {
            tabWeek[i] = valueOf(nb + i);
        }
        JComboBox comboBoxTeacher;
        comboBoxTeacher = new JComboBox(tabWeek);
        teacherPanel.add(comboBoxTeacher);
        teacherPanel.add(buttonSearch);
        teacherPanel.add(buttonReturn);

        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTableTeacherSession(comboBoxTeacher.getSelectedItem().toString());
            }
        });

        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teacherPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(teacherFrame);
                teacherFrame();
            }
        });

    }




    /**
     * Fenetre pour l'ajout d'un admin a la BDD
     */
    public void addTeacherAdmin() {

        String[] tab = new String[secondFrameController.getAllCourse().size()];

        for (int i = 0; i < tab.length; i++) {
            tab[i] = secondFrameController.getAllCourse().get(i).getName();
        }

        JLabel jLabelCourse = new JLabel("Teacher of :");
        JComboBox comboBox = new JComboBox(tab);

        JPanel addTeacherAdmin = new JPanel(new GridLayout(7, 2));

        adminFrame.setContentPane(addTeacherAdmin);

        JLabel jLabelFirstName = new JLabel("First Name :");
        JTextField addTeacherFirstName = new JTextField();

        JLabel jLabelLastName = new JLabel("Last Name :");
        JTextField addTeacherLastName = new JTextField();

        JLabel jLabelEmail = new JLabel("Email :");
        JTextField addTeacherEmail = new JTextField();

        JLabel jLabelPassword = new JLabel("Password :");
        JTextField addTeacherPassword = new JTextField();

        JButton buttonAddTeacher = new JButton("Add teacher");
        JButton buttonReturn = new JButton("Return");


        jLabelFirstName.setFont(new Font(" Arial ", Font.BOLD, 20));
        addTeacherFirstName.setFont(new Font(" Arial ", Font.BOLD, 20));

        jLabelLastName.setFont(new Font(" Arial ", Font.BOLD, 20));
        addTeacherLastName.setFont(new Font(" Arial ", Font.BOLD, 20));

        jLabelEmail.setFont(new Font(" Arial ", Font.BOLD, 20));
        addTeacherEmail.setFont(new Font(" Arial ", Font.BOLD, 20));

        jLabelPassword.setFont(new Font(" Arial ", Font.BOLD, 20));
        addTeacherPassword.setFont(new Font(" Arial ", Font.BOLD, 20));

        jLabelCourse.setFont(new Font(" Arial ", Font.BOLD, 20));
        panSmall.add(comboBox);
        comboBox.setFont(new Font(" Arial", Font.BOLD, 20));
        comboBox.setBounds(0, 60, 200, 50);


        addTeacherAdmin.add((jLabelFirstName));
        addTeacherAdmin.add(addTeacherFirstName);

        addTeacherAdmin.add((jLabelLastName));
        addTeacherAdmin.add(addTeacherLastName);

        addTeacherAdmin.add(jLabelEmail);
        addTeacherAdmin.add(addTeacherEmail);

        addTeacherAdmin.add(jLabelPassword);
        addTeacherAdmin.add(addTeacherPassword);

        addTeacherAdmin.add(jLabelCourse);
        addTeacherAdmin.add(comboBox);

        addTeacherAdmin.add(buttonAddTeacher);
        addTeacherAdmin.add(buttonReturn);

        buttonAddTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondFrameController.addTeacher(addTeacherEmail.getText(),
                        addTeacherPassword.getText(),
                        addTeacherFirstName.getText(),
                        addTeacherLastName.getText(),
                        Objects.requireNonNull(comboBox.getSelectedItem()).toString());
            }
        });

        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTeacherAdmin.removeAll();
                SwingUtilities.updateComponentTreeUI(adminFrame);
                adminFrame();
            }
        });
    }


    /**
     * Fenetre pour la suppression d'un admin de la BDD
     */
    public void removeTeacherAdmin() {


        String[] tab = new String[secondFrameController.getAllTeacher().size()];

        for (int i = 0; i < tab.length; i++) {
            tab[i] = secondFrameController.getAllTeacher().get(i).getFirst_name() + "  " + secondFrameController.getAllTeacher().get(i).getLast_name();
        }


        JComboBox comboBoxCourse = new JComboBox(tab);


        panSmall.add(comboBoxCourse);

        comboBoxCourse.setFont(new Font(" TimesRoman ", Font.BOLD, 30));
        comboBoxCourse.setBounds(0, 60, 200, 50);
        JPanel adminPanel = new JPanel(new GridLayout(3, 1));


        adminFrame.setContentPane(adminPanel);
        adminPanel.add(comboBoxCourse);

        JButton buttonRemove = new JButton("Remove teacher");
        JButton buttonreturn = new JButton("Return");

        adminPanel.add(buttonRemove);
        adminPanel.add(buttonreturn);


        buttonreturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(adminFrame);
                adminFrame();
            }
        });

        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                secondFrameController.removeTeacher(Objects.requireNonNull(comboBoxCourse.getSelectedItem()).toString());

            }
        });

    }


    /**
     * fonction de la fenetre admin qui permet d'ajouter un eleve a la BDD
     */
    public void addStudentAdmin() {

        String[] tab = new String[secondFrameController.getAllGroupPromotion().size()];

        for (int i = 0; i < tab.length; i++) {
            tab[i] = secondFrameController.getAllGroupPromotion().get(i).getName();
        }

        JLabel jLabelCourse = new JLabel("Promotion Group :");
        JComboBox comboBox = new JComboBox(tab);

        JPanel addStudentAdmin = new JPanel(new GridLayout(8, 2));

        adminFrame.setContentPane(addStudentAdmin);

        JLabel jLabelFirstName = new JLabel("First Name :");
        JTextField addStudentFirstName = new JTextField();

        JLabel jLabelLastName = new JLabel("Last Name :");
        JTextField addStudentLastName = new JTextField();

        JLabel jLabelEmail = new JLabel("Email :");
        JTextField addStudentEmail = new JTextField();

        JLabel jLabelPassword = new JLabel("Password :");
        JTextField addStudentPassword = new JTextField();

        JLabel jLabelNumber = new JLabel("Number :");
        JTextField addStudentNumber = new JTextField();

        JButton buttonAddStudent = new JButton("Add Student");
        JButton buttonReturn = new JButton("Return");


        jLabelFirstName.setFont(new Font(" Arial ", Font.BOLD, 20));
        addStudentFirstName.setFont(new Font(" Arial ", Font.BOLD, 20));

        jLabelLastName.setFont(new Font(" Arial ", Font.BOLD, 20));
        addStudentLastName.setFont(new Font(" Arial ", Font.BOLD, 20));

        jLabelEmail.setFont(new Font(" Arial ", Font.BOLD, 20));
        addStudentEmail.setFont(new Font(" Arial ", Font.BOLD, 20));

        jLabelPassword.setFont(new Font(" Arial ", Font.BOLD, 20));
        addStudentPassword.setFont(new Font(" Arial ", Font.BOLD, 20));

        jLabelNumber.setFont(new Font(" Arial ", Font.BOLD, 20));
        addStudentNumber.setFont(new Font(" Arial ", Font.BOLD, 20));

        jLabelCourse.setFont(new Font(" Arial ", Font.BOLD, 20));
        panSmall.add(comboBox);
        comboBox.setFont(new Font(" Arial", Font.BOLD, 20));
        comboBox.setBounds(0, 60, 200, 50);


        addStudentAdmin.add((jLabelFirstName));
        addStudentAdmin.add(addStudentFirstName);

        addStudentAdmin.add((jLabelLastName));
        addStudentAdmin.add(addStudentLastName);

        addStudentAdmin.add(jLabelEmail);
        addStudentAdmin.add(addStudentEmail);

        addStudentAdmin.add(jLabelPassword);
        addStudentAdmin.add(addStudentPassword);

        addStudentAdmin.add(jLabelNumber);
        addStudentAdmin.add(addStudentNumber);

        addStudentAdmin.add(jLabelCourse);
        addStudentAdmin.add(comboBox);

        addStudentAdmin.add(buttonAddStudent);
        addStudentAdmin.add(buttonReturn);

        buttonAddStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondFrameController.addStudent(addStudentEmail.getText(),
                        addStudentPassword.getText(),
                        addStudentFirstName.getText(),
                        addStudentLastName.getText(),
                        addStudentNumber.getText(),
                        Objects.requireNonNull(comboBox.getSelectedItem()).toString());


            }
        });

        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudentAdmin.removeAll();
                SwingUtilities.updateComponentTreeUI(adminFrame);
                adminFrame();
            }
        });


    }

    /**
     * fonction de la fenetre
     * admin qui permet de supprimer un eleve de la BDD
     */
    public void removeStudentAdmin() {


        String[] tab = new String[secondFrameController.getAllStudent().size()];

        for (int i = 0; i < tab.length; i++) {
            tab[i] = secondFrameController.getAllStudent().get(i).getFirst_name() + "  " + secondFrameController.getAllStudent().get(i).getLast_name();
        }

        JComboBox comboBoxStudent = new JComboBox(tab);
        panSmall.add(comboBoxStudent);
        comboBoxStudent.setFont(new Font(" TimesRoman ", Font.BOLD, 30));
        comboBoxStudent.setBounds(0, 60, 200, 50);
        JPanel adminPanel = new JPanel(new GridLayout(3, 1));

        adminFrame.setContentPane(adminPanel);
        adminPanel.add(comboBoxStudent);

        JButton buttonRemove = new JButton("Remove student");
        JButton buttonreturn = new JButton("Return");

        adminPanel.add(buttonRemove);
        adminPanel.add(buttonreturn);


        buttonreturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(adminFrame);
                adminFrame();
            }
        });

        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                secondFrameController.removeStudent(Objects.requireNonNull(comboBoxStudent.getSelectedItem()).toString());

            }
        });

    }

    /**
     * fonction de la fenetre admin pour ajouter une session dans la bdd
     */

    public void addSessionAdmin() {

        ///////////////

        String[] tab = new String[secondFrameController.getAllCourse().size()];

        for (int i = 0; i < tab.length; i++) {
            tab[i] = secondFrameController.getAllCourse().get(i).getName();
        }

        JLabel jLabelCourse = new JLabel("Session of :");
        JComboBox comboBox = new JComboBox(tab);

        ///////////////

        String[] tab1 = new String[secondFrameController.getAllCourseType().size()];

        for (int i = 0; i < tab1.length; i++) {
            tab1[i] = secondFrameController.getAllCourseType().get(i).getType();
        }

        JLabel jLabelCourseType = new JLabel("Type of :");
        JComboBox comboBoxType = new JComboBox(tab1);

        ///////////////
        String[] tab2 = new String[secondFrameController.getAllSessionState().size()];

        for (int i = 0; i < tab2.length; i++) {
            tab2[i] = secondFrameController.getAllSessionState().get(i);
        }

        JLabel jLabelSessionState = new JLabel("Session State :");
        JComboBox comboBoxState = new JComboBox(tab2);


        ///////////////


        String[] tab3 = new String[secondFrameController.getAllTeacher().size()];

        for (int i = 0; i < tab3.length; i++) {
            tab3[i] = secondFrameController.getAllTeacher().get(i).getFirst_name() + "  " + secondFrameController.getAllTeacher().get(i).getLast_name();
        }

        JLabel jLabelTeacher = new JLabel("Teacher :");
        JComboBox comboBoxTeacher = new JComboBox(tab3);

        //////////////


        String[] tab4 = new String[secondFrameController.getAllGroupPromotion().size()];
        for (int i = 0; i < tab4.length; i++) {
            tab4[i] = secondFrameController.getAllGroupPromotion().get(i).getName();
        }
        JLabel jLabelGroupPromo = new JLabel("Group :");
        JComboBox comboBoxGroupPromo = new JComboBox(tab4);

        ///////////////

        JPanel addSessionAdmin = new JPanel(new GridLayout(10, 2));

        adminFrame.setContentPane(addSessionAdmin);

        JLabel jLabelDate = new JLabel("Date (yyyy-MM-dd) :");
        JTextField addSessionDate = new JTextField();

        JLabel jLabelStartTime = new JLabel("Start Time (HH:mm:ss) :");
        JTextField addSessionStartTime = new JTextField();

        JLabel jLabelEndTime = new JLabel("End Time (HH:mm:ss) :");
        JTextField addSessionEndTime = new JTextField();

        JLabel jLabelWeek = new JLabel("Week :");
        JTextField addSessionWeek = new JTextField();

        JButton buttonAddSession = new JButton("Add Session");
        JButton buttonReturn = new JButton("Return");


        jLabelDate.setFont(new Font(" Arial ", Font.BOLD, 20));
        addSessionDate.setFont(new Font(" Arial ", Font.BOLD, 20));

        jLabelStartTime.setFont(new Font(" Arial ", Font.BOLD, 20));
        addSessionStartTime.setFont(new Font(" Arial ", Font.BOLD, 20));

        jLabelEndTime.setFont(new Font(" Arial ", Font.BOLD, 20));
        addSessionEndTime.setFont(new Font(" Arial ", Font.BOLD, 20));

        jLabelWeek.setFont(new Font(" Arial ", Font.BOLD, 20));
        addSessionWeek.setFont(new Font(" Arial ", Font.BOLD, 20));

        jLabelCourse.setFont(new Font(" Arial ", Font.BOLD, 20));
        panSmall.add(comboBox);
        comboBox.setFont(new Font(" Arial", Font.BOLD, 20));
        comboBox.setBounds(0, 60, 200, 50);

        jLabelCourseType.setFont(new Font(" Arial ", Font.BOLD, 20));
        panSmall.add(comboBoxType);
        comboBoxType.setFont(new Font(" Arial", Font.BOLD, 20));
        comboBoxType.setBounds(0, 60, 200, 50);

        jLabelSessionState.setFont(new Font(" Arial ", Font.BOLD, 20));
        panSmall.add(comboBoxState);
        comboBoxState.setFont(new Font(" Arial", Font.BOLD, 20));
        comboBoxState.setBounds(0, 60, 200, 50);

        jLabelTeacher.setFont(new Font(" Arial ", Font.BOLD, 20));
        panSmall.add(comboBoxTeacher);
        comboBoxTeacher.setFont(new Font(" Arial", Font.BOLD, 20));
        comboBoxTeacher.setBounds(0, 60, 200, 50);

        jLabelGroupPromo.setFont(new Font(" Arial ", Font.BOLD, 20));
        panSmall.add(comboBoxGroupPromo);
        comboBoxGroupPromo.setFont(new Font(" Arial", Font.BOLD, 20));
        comboBoxGroupPromo.setBounds(0, 60, 200, 50);


        addSessionAdmin.add((jLabelDate));
        addSessionAdmin.add(addSessionDate);

        addSessionAdmin.add((jLabelStartTime));
        addSessionAdmin.add(addSessionStartTime);

        addSessionAdmin.add((jLabelEndTime));
        addSessionAdmin.add(addSessionEndTime);

        addSessionAdmin.add(jLabelWeek);
        addSessionAdmin.add(addSessionWeek);

        addSessionAdmin.add(jLabelCourse);
        addSessionAdmin.add(comboBox);

        addSessionAdmin.add(jLabelCourseType);
        addSessionAdmin.add(comboBoxType);

        addSessionAdmin.add(jLabelSessionState);
        addSessionAdmin.add(comboBoxState);

        addSessionAdmin.add(jLabelTeacher);
        addSessionAdmin.add(comboBoxTeacher);

        addSessionAdmin.add(jLabelGroupPromo);
        addSessionAdmin.add(comboBoxGroupPromo);

        addSessionAdmin.add(buttonAddSession);
        addSessionAdmin.add(buttonReturn);

        buttonAddSession.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondFrameController.addSession(addSessionWeek.getText(),
                        addSessionDate.getText(),
                        addSessionStartTime.getText(),
                        addSessionEndTime.getText(),
                        comboBoxState.getSelectedItem().toString(),
                        comboBox.getSelectedItem().toString(),
                        comboBoxType.getSelectedItem().toString(),
                        comboBoxTeacher.getSelectedItem().toString(),
                        comboBoxGroupPromo.getSelectedItem().toString());


            }
        });

        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSessionAdmin.removeAll();
                SwingUtilities.updateComponentTreeUI(adminFrame);
                adminFrame();
            }
        });


    }

    /**
     * fonction de la fenetre admin qui permet d'de supprimer une session de la  BDD
     */

    public void removeSessionAdmin() {

        String[] tab = new String[secondFrameController.getAllSessionDate().size()];

        for (int i = 0; i < tab.length; i++) {
            tab[i] = secondFrameController.getAllSessionDate().get(i);
        }

        String[] tab2 = new String[secondFrameController.getAllSessionStartTime().size()];

        for (int i = 0; i < tab2.length; i++) {
            tab2[i] = secondFrameController.getAllSessionStartTime().get(i);
        }

        String[] tab3 = new String[secondFrameController.getAllCourse().size()];

        for (int i = 0; i < tab3.length; i++) {
            tab3[i] = secondFrameController.getAllCourse().get(i).getName();
        }

        ///////////////

        JPanel adminPanel = new JPanel(new GridLayout(5, 1));
        adminFrame.setContentPane(adminPanel);
        adminFrame.setTitle("Remove session ");

        JComboBox comboBoxDate = new JComboBox(tab);
        comboBoxDate.setFont(new Font(" TimesRoman ", Font.BOLD, 25));

        JComboBox comboBoxStartTime = new JComboBox(tab2);
        comboBoxStartTime.setFont(new Font(" TimesRoman ", Font.BOLD, 25));

        JComboBox comboBoxCourse = new JComboBox(tab3);
        comboBoxCourse.setFont(new Font(" TimesRoman ", Font.BOLD, 25));


        JButton buttonReturn = new JButton("Return");
        buttonReturn.setFont(new Font(" TimesRoman ", Font.BOLD, 30));

        JButton buttonRemove = new JButton("Search and Delete");
        buttonRemove.setFont(new Font(" TimesRoman ", Font.BOLD, 30));

        adminPanel.add(comboBoxDate);
        adminPanel.add(comboBoxStartTime);
        adminPanel.add(comboBoxCourse);
        adminPanel.add(buttonRemove);
        adminPanel.add(buttonReturn);


        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(adminFrame);
                adminFrame();
            }
        });


        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondFrameController.removeSession(Objects.requireNonNull(comboBoxDate.getSelectedItem()).toString(),
                        Objects.requireNonNull(comboBoxStartTime.getSelectedItem()).toString(),
                        Objects.requireNonNull(comboBoxCourse.getSelectedItem()).toString());
            }
        });
    }




    public void showTableTeacher() {
        JPanel panelTable = new JPanel();

        referentFrame.setContentPane(panelTable);
        panelTable.setLayout(new BorderLayout());

        String[][] table = new String[secondFrameController.getAllTeacher().size()][6];

        for (int i = 0; i < secondFrameController.getAllTeacher().size(); i++) {

            table[i][0] = String.valueOf(secondFrameController.getAllTeacher().get(i).getID());
            table[i][1] = String.valueOf(secondFrameController.getAllTeacher().get(i).getEmail());
            table[i][2] = String.valueOf(secondFrameController.getAllTeacher().get(i).getPassword());
            table[i][3] = String.valueOf(secondFrameController.getAllTeacher().get(i).getFirst_name());
            table[i][4] = String.valueOf(secondFrameController.getAllTeacher().get(i).getLast_name());



        }

        String[] entetes = {"ID", "Email", "Mot de passe", "Prenom", "Nom"};


        JButton buttonReturn = new JButton("Return");


        JTable jTable = new JTable(table, entetes);

        panelTable.add(jTable.getTableHeader(), BorderLayout.NORTH);
        panelTable.add(jTable, BorderLayout.CENTER);
        panelTable.add(buttonReturn, BorderLayout.SOUTH);

        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelTable.removeAll();
                SwingUtilities.updateComponentTreeUI(referentFrame);
                teacherFrame();

            }
        });

        referentFrame.pack();
    }

    public void showTableStudent() {
        JPanel panelTable = new JPanel();

        referentFrame.setContentPane(panelTable);
        panelTable.setLayout(new BorderLayout());

        String[][] table = new String[secondFrameController.getAllStudent().size()][6];

        for (int i = 0; i < secondFrameController.getAllStudent().size(); i++) {

            table[i][0] = String.valueOf(secondFrameController.getAllStudent().get(i).getID());
            table[i][1] = String.valueOf(secondFrameController.getAllStudent().get(i).getEmail());
            table[i][2] = String.valueOf(secondFrameController.getAllStudent().get(i).getPassword());
            table[i][3] = String.valueOf(secondFrameController.getAllStudent().get(i).getFirst_name());
            table[i][4] = String.valueOf(secondFrameController.getAllStudent().get(i).getLast_name());



        }

        String[] entetes = {"ID", "Email", "Mot de passe", "Prenom", "Nom"};


        JButton buttonReturn = new JButton("Return");



        JTable jTable = new JTable(table, entetes);

        panelTable.add(jTable.getTableHeader(), BorderLayout.NORTH);
        panelTable.add(jTable, BorderLayout.CENTER);
        panelTable.add(buttonReturn, BorderLayout.SOUTH);

        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelTable.removeAll();
                SwingUtilities.updateComponentTreeUI(referentFrame);
                referentFrame();

            }
        });

        referentFrame.pack();
    }


    public void showTableTeacherSession(String item) {
        JPanel panelTable = new JPanel();

        teacherFrame.setContentPane(panelTable);
        panelTable.setLayout(new BorderLayout());

        String[][] table = new String[mainFrameController.getSessionByWeekForTeacher(user, item ).size()][8];

        for (int i = 0; i < mainFrameController.getSessionByWeekForTeacher(user, item ).size(); i++) {

            table[i][0] = String.valueOf(mainFrameController.getSessionByWeekForTeacher(user, item ).get(i).getID());
            table[i][1] = String.valueOf(mainFrameController.getSessionByWeekForTeacher(user, item ).get(i).getWeek());
            table[i][2] = String.valueOf(mainFrameController.getSessionByWeekForTeacher(user, item ).get(i).getStartTime());
            table[i][3] = String.valueOf(mainFrameController.getSessionByWeekForTeacher(user, item ).get(i).getEndTime());
            table[i][4] = String.valueOf(mainFrameController.getSessionByWeekForTeacher(user, item ).get(i).getState());
            table[i][5] = String.valueOf(mainFrameController.getSessionByWeekForTeacher(user, item ).get(i).getDate());
            table[i][6] = String.valueOf(mainFrameController.getSessionByWeekForTeacher(user, item ).get(i).getID_type());
            table[i][7] = String.valueOf(mainFrameController.getSessionByWeekForTeacher(user, item ).get(i).getID_course());

        }

        String[] entetes = {"ID", "Week", "Heure de début", "Heure de fin", "Etat","Date", "ID_Type", "ID_course"};


        JButton buttonReturn = new JButton("Return");



        JTable jTable = new JTable(table, entetes);

        panelTable.add(jTable.getTableHeader(), BorderLayout.NORTH);
        panelTable.add(jTable, BorderLayout.CENTER);
        panelTable.add(buttonReturn, BorderLayout.SOUTH);

        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teacherPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(teacherFrame);
                teacherFrame();

            }
        });

        teacherFrame.pack();
    }

    /**
     * fonction de la fentre admin qui permet de voir les differents JFreeCharts avec les infos sur les
     * sessions dans la BDD, sur les capacités de chaque site et le nombre de salles par site
     */

    public void dataConsulting() {

        JPanel adminPanel = new JPanel(new GridLayout(6, 2));
        adminFrame.setContentPane(adminPanel);
        JButton buttonSessionData = new JButton("Sessions Data");
        JButton buttonSiteData = new JButton("Site Data");
        JButton buttonReturn = new JButton("Return");

        adminPanel.add(buttonSessionData);
        adminPanel.add(buttonSiteData);
        adminPanel.add(buttonReturn);

        buttonSessionData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondFrameController.getChartSession().setVisible(true);
            }
        });

        buttonSiteData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondFrameController.getChartSite().setVisible(true);
            }
        });

        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(adminFrame);
                adminFrame();
            }
        });

    }


    /**
     * Fonction qui selon la session passee en parametre colorie les boutons de la list en parametre avec l'index associée, egalement passé en parametre
     * l'index sera dependra de l'heure de la session
     *
     * @param list    list de boutons
     * @param session session de l'utilisateur
     * @param index   heure de la session
     */

    public void getCouleurFromCourseType(List<JButton> list, Session session, int index) {

        switch (Math.toIntExact(session.getID_course())) {
            case 1:
                list.get(index).setBackground(rougeClair);
                break;
            case 2:
                list.get(index).setBackground(saumon);
                break;
            case 3:
                list.get(index).setBackground(kaki);
                break;
            case 4:
                list.get(index).setBackground(jaunePale);
                break;
            case 5:
                list.get(index).setBackground(vertClair);
                break;
            case 6:
                list.get(index).setBackground(violetPale);
                break;
            case 7:
                list.get(index).setBackground(vertBleu);
                break;
            case 8:
                list.get(index).setBackground(bleuCiel);
                break;
            case 9:
                list.get(index).setBackground(vertPomme);
                break;


            default:
                break;
        }
    }

    /**
     * renvoie un int qui depend de l'heure de la session
     *
     * @param session session du user
     * @return selon l'heure de la session, int pour gerer la couleur des boutons
     */
    public int getIntFromSessionHour(Session session) {

        LocalTime startTime;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        startTime = LocalTime.parse(session.getStartTime(), timeFormatter);
        int i = 0;

        switch (startTime.getHour()) {
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

    /**
     * fonction qui affiche les cours du user  de la classe secondframe view connecté en eleve
     * l'item en parametre correspond a la semaine choisie dans la comboBox de la fenetre student
     *
     * @param item semaine saisie en chiffre par l'utilisateur dans la comboBox
     */
    public void showSessionStudent(String item) {


        lundiSession.clear();
        mardiSession.clear();
        mercrediSession.clear();
        jeudiSession.clear();
        vendrediSession.clear();
        samediSession.clear();


        lundiSession = mainFrameController.getSessionLundi(user, item);
        mardiSession = mainFrameController.getSessionMardi(user, item);
        mercrediSession = mainFrameController.getSessionMercredi(user, item);
        jeudiSession = mainFrameController.getSessionJeudi(user, item);
        vendrediSession = mainFrameController.getSessionVendredi(user, item);
        samediSession = mainFrameController.getSessionSamedi(user, item);


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

    /**
     * fonction qui affiche les cours du user  de la classe secondframe view connecté en enseignant
     *
     * @param item Semaine choisir par l'utilisateur
     */

    public void showSessionTeacher(String item) {


        lundiSession.clear();
        mardiSession.clear();
        mercrediSession.clear();
        jeudiSession.clear();
        vendrediSession.clear();
        samediSession.clear();


        lundiSession = mainFrameController.getSessionLundiTeacher(user, item);
        mardiSession = mainFrameController.getSessionMardiTeacher(user, item);
        mercrediSession = mainFrameController.getSessionMercrediTeacher(user, item);
        jeudiSession = mainFrameController.getSessionJeudiTeacher(user, item);
        vendrediSession = mainFrameController.getSessionVendrediTeacher(user, item);
        samediSession = mainFrameController.getSessionSamediTeacher(user, item);


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


    /**
     * fonction qui affiche dans le text du bouton le contenu de la session
     * affichage du cours, du type de cours
     */
    public void showSessionsDetails() {

        for (int i = 0; i < lundi.size(); i++) {

            for (Session s : lundiSession) {
                LocalTime startTime;
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                startTime = LocalTime.parse(s.getStartTime(), timeFormatter);

                switch (startTime.getHour()) {
                    case 8:
                        switchCourse(lundi, s, 0);
                        break;
                    case 9:
                        switchCourse(lundi, s, 1);
                        break;
                    case 11:
                        switchCourse(lundi, s, 3);
                        break;
                    case 12:
                        switchCourse(lundi, s, 4);
                        break;
                    case 14:
                        switchCourse(lundi, s, 6);
                        break;
                    case 16:
                        switchCourse(lundi, s, 7);
                        break;
                    case 17:
                        switchCourse(lundi, s, 9);
                        break;
                    case 19:
                        switchCourse(lundi, s, 10);
                        break;

                    default:
                        break;

                }


            }


        }
        for (int i = 0; i < mardi.size(); i++) {

            for (Session s : mardiSession) {
                LocalTime startTime;
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                startTime = LocalTime.parse(s.getStartTime(), timeFormatter);

                switch (startTime.getHour()) {
                    case 8:
                        switchCourse(mardi, s, 0);
                        break;
                    case 9:
                        switchCourse(mardi, s, 1);
                        break;
                    case 11:
                        switchCourse(mardi, s, 3);
                        break;
                    case 12:
                        switchCourse(mardi, s, 4);
                        break;
                    case 14:
                        switchCourse(mardi, s, 6);
                        break;
                    case 16:
                        switchCourse(mardi, s, 7);
                        break;
                    case 17:
                        switchCourse(mardi, s, 9);
                        break;
                    case 19:
                        switchCourse(mardi, s, 10);
                        break;

                    default:
                        break;

                }


            }


        }
        for (int i = 0; i < mercredi.size(); i++) {

            for (Session s : mercrediSession) {
                LocalTime startTime;
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                startTime = LocalTime.parse(s.getStartTime(), timeFormatter);

                switch (startTime.getHour()) {
                    case 8:
                        switchCourse(mercredi, s, 0);
                        break;
                    case 9:
                        switchCourse(mercredi, s, 1);
                        break;
                    case 11:
                        switchCourse(mercredi, s, 3);
                        break;
                    case 12:
                        switchCourse(mercredi, s, 4);
                        break;
                    case 14:
                        switchCourse(mercredi, s, 6);
                        break;
                    case 16:
                        switchCourse(mercredi, s, 7);
                        break;
                    case 17:
                        switchCourse(mercredi, s, 9);
                        break;
                    case 19:
                        switchCourse(mercredi, s, 10);
                        break;

                    default:
                        break;

                }


            }


        }
        for (int i = 0; i < jeudi.size(); i++) {

            for (Session s : jeudiSession) {
                LocalTime startTime;
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                startTime = LocalTime.parse(s.getStartTime(), timeFormatter);

                switch (startTime.getHour()) {
                    case 8:
                        switchCourse(jeudi, s, 0);
                        break;
                    case 9:
                        switchCourse(jeudi, s, 1);
                        break;
                    case 11:
                        switchCourse(jeudi, s, 3);
                        break;
                    case 12:
                        switchCourse(jeudi, s, 4);
                        break;
                    case 14:
                        switchCourse(jeudi, s, 6);
                        break;
                    case 16:
                        switchCourse(jeudi, s, 7);
                        break;
                    case 17:
                        switchCourse(jeudi, s, 9);
                        break;
                    case 19:
                        switchCourse(jeudi, s, 10);
                        break;

                    default:
                        break;

                }


            }


        }
        for (int i = 0; i < vendredi.size(); i++) {

            for (Session s : vendrediSession) {
                LocalTime startTime;
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                startTime = LocalTime.parse(s.getStartTime(), timeFormatter);

                switch (startTime.getHour()) {
                    case 8:
                        switchCourse(vendredi, s, 0);
                        break;
                    case 9:
                        switchCourse(vendredi, s, 1);
                        break;
                    case 11:
                        switchCourse(vendredi, s, 3);
                        break;
                    case 12:
                        switchCourse(vendredi, s, 4);
                        break;
                    case 14:
                        switchCourse(vendredi, s, 6);
                        break;
                    case 16:
                        switchCourse(vendredi, s, 7);
                        break;
                    case 17:
                        switchCourse(vendredi, s, 9);
                        break;
                    case 19:
                        switchCourse(vendredi, s, 10);
                        break;

                    default:
                        break;

                }


            }


        }
        for (int i = 0; i < samedi.size(); i++) {

            for (Session s : samediSession) {
                LocalTime startTime;
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                startTime = LocalTime.parse(s.getStartTime(), timeFormatter);

                switch (startTime.getHour()) {
                    case 8:
                        switchCourse(samedi, s, 0);
                        break;
                    case 9:
                        switchCourse(samedi, s, 1);
                        break;
                    case 11:
                        switchCourse(samedi, s, 3);
                        break;
                    case 12:
                        switchCourse(samedi, s, 4);
                        break;
                    case 14:
                        switchCourse(samedi, s, 6);
                        break;
                    case 16:
                        switchCourse(samedi, s, 7);
                        break;
                    case 17:
                        switchCourse(samedi, s, 9);
                        break;
                    case 19:
                        switchCourse(samedi, s, 10);
                        break;

                    default:
                        break;

                }


            }


        }

    }


    /**
     * fonction qui affiche la matiere du cours sur le bouton
     *
     * @param list  liste de boutons
     * @param s     session en cours a afficher
     * @param index index du boutons a modifier
     */
    public void switchCourse(List<JButton> list, Session s, int index) {
        switch (Math.toIntExact(s.getID_course())) {
            case 1 -> list.get(index).setText(mainFrameController.getTeacherNameFromIdSession(s.getID()) + "\nCours info");//
            case 2 -> list.get(index).setText("Cours mathématiques");
            case 3 -> list.get(index).setText("Cours electronique");
            case 4 -> list.get(index).setText("Cours anglais");
            case 5 -> list.get(index).setText("Cours physique");
            default -> list.get(index).setText("Cours inconnu");
        }


    }

    /**
     * fonction qui affiche le type de cours sur le bouton
     *
     * @param list  liste de boutons
     * @param s     session en cours a afficher
     * @param index index du boutons a modifier
     */

    public void switchTypeCourse(List<JButton> list, Session s, int index) {
        switch (Math.toIntExact(s.getID_type())) {
            case 1 -> list.get(index).setText("Cours interactif");
            case 2 -> list.get(index).setText("Cours magistral");
            case 3 -> list.get(index).setText("Cours TD");
            case 4 -> list.get(index).setText("Cours TP");
            case 5 -> list.get(index).setText("Projet");
            case 6 -> list.get(index).setText("Soutien");
        }


    }
}
















