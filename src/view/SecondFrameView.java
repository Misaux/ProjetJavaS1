package view;

import Models.Session;
import Models.Teacher;
import controller.SecondFrameController;
import Models.User;

import javax.management.remote.JMXAddressable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static java.lang.String.valueOf;

public class SecondFrameView implements Observer {

    public static final Color grisClair = new Color(192, 192, 192);

    JPasswordField textFieldPssword;
    JTextField textFieldConnexion;
    JButton buttonSearch;
    JButton buttonQuit;
    public Long id;

    User user = new User();


    private JFrame smallFen = new JFrame("Petite Fen");

    private JPanel panSmall = new JPanel();
    JTextField jTextField = new JTextField();
    private SecondFrameController secondFrameControllerExtern = new SecondFrameController();
    private SecondFrameController secondFrameController = new SecondFrameController();

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




    public SecondFrameView(SecondFrameController secondFrameController) {

        this.secondFrameController = secondFrameController;
        connexionUser();


    }

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

        JLabel jLabelPassword = new JLabel("Mot de passe : ");
        panSmall.add(jLabelPassword);
        jLabelPassword.setBounds(55, 60, 160, 50);


        //Ajout de la text box pour saisir les infos
        textFieldConnexion = new JTextField("");
        panSmall.add(textFieldConnexion);
        textFieldConnexion.setBounds(180, 0, 200, 50);

        //ajout du bouton pour valider
        buttonSearch = new JButton("Rechercher");
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

    public void display(String name) {
        this.jTextField.setText(name);
    }

    @Override
    public void update(Observable o, Object arg) {

        //JOptionPane.showMessageDialog(null, id);


    }


    public void controlPanel() {
        smallFen.setVisible(false);
        JFrame controlPanelFrame = new JFrame("Control Panel Frame");
        JPanel panelControlFrame = new JPanel();

        panelControlFrame.setBackground(grisClair);
        panelControlFrame.setLayout(null);
        controlPanelFrame.setVisible(true);

        controlPanelFrame.setBounds(300, 200, 450, 300);
        controlPanelFrame.setContentPane(panelControlFrame);
        controlPanelFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        controlPanelFrame.setLocation(1000, 540);

        JButton getSession = new JButton("Rechercher session");
        panelControlFrame.add(getSession);
        getSession.setBounds(10, 10, 150, 50);

    }


    public void adminFrame() {

        smallFen.setVisible(false);
        JPanel adminPanel = new JPanel(new GridLayout(6, 2));
        adminFrame.setVisible(true);

        adminFrame.setContentPane(adminPanel);

        adminFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        adminFrame.setBounds(300, 200, 817, 417);
        adminFrame.setLocation(1000, 540);


        adminPanel.add(addTeacher);

        addTeacher.setText("Ajouter Enseignant");

        adminPanel.add(removeTeacher);

        removeTeacher.setText("Retirer Enseignant");

        /*///////////////////////////////////////////////////////////*/
        adminPanel.add(addStudent);

        addStudent.setText("Ajouter Eleve");

        adminPanel.add(removeStudent);

        removeStudent.setText("Retirer Eleve");

        /*///////////////////////////////////////////////////////////*/

        adminPanel.add(addSession);

        addSession.setText("Ajouter Session");


        adminPanel.add(removeSession);

        removeSession.setText("Retirer Session");


        /*/////////////////////////////////////////////////////*/

        adminPanel.add(consultData);
        consultData.setText(("Consult data"));

        /*///////////////////////////////////////////////////////////*/

        adminPanel.add(deconnexion);
        deconnexion.setText("Deconnexion");

        /*///////////////////////////////////////////////////////////*/

        adminPanel.add(labelAdminInfo);

        labelAdminInfo.setText("Session ouvert par : " + user.getLast_name() + " " + user.getFirst_name());
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

    public void studentFrame() {
        smallFen.setVisible(false);
        studentPanel.setLayout(new GridLayout(2,1));

        studentFrame.setBounds(0, 0, 460, 400);
        studentFrame.setLocation(1000, 540);
        studentFrame.setContentPane(studentPanel);

        //premiere page
        //-------------------------------------------
        JButton viewPlaning1 = new JButton();
        studentPanel.add(viewPlaning1);
        viewPlaning1.setText("view planning");

        JButton deconnexionStudent = new JButton();
        studentPanel.add(deconnexionStudent);
        deconnexionStudent.setText("Deconnexion");

        //--------------------------------------------


        studentFrame.setVisible(true);
        studentPanel.setLayout(null);
        studentFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        viewPlaning1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(studentFrame);
                viewPlanning();
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

    public void viewPlanning(){
        JButton buttonSearch = new JButton("search");

        studentPanel.setLayout(new GridLayout(2,1));
        String[] tabWeek = new String[52]; // on recupere le nombre de semaines dispo dans edt (semaines dans une année)
        int nb = 1;
        for (int i = 0; i < tabWeek.length; i++) {
            tabWeek[i] = valueOf(nb+i);
        }
        JComboBox comboBoxStudent;
        comboBoxStudent = new JComboBox(tabWeek);
        studentPanel.add(comboBoxStudent);
        studentPanel.add(buttonSearch);

        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentPanel.removeAll();
                SwingUtilities.updateComponentTreeUI(studentFrame);
                secondFrameController.getSessionByWeek(user, comboBoxStudent.getSelectedItem().toString());
            }
        });


    }

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

        JButton buttonAddTeacher = new JButton("Ajouter Enseigant");
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
                secondFrameControllerExtern.addTeacher(addTeacherEmail.getText(), addTeacherPassword.getText(), addTeacherFirstName.getText(), addTeacherLastName.getText(), comboBox.getSelectedItem().toString());


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

    public void removeTeacherAdmin() {


        String[] tab = new String[secondFrameController.getAllTeacher().size()];

        for (int i = 0; i < tab.length; i++) {
            tab[i] = secondFrameController.getAllTeacher().get(i).getFirst_name() + "  " + secondFrameController.getAllTeacher().get(i).getLast_name();
        }

        JComboBox comboBox;
        comboBox = new JComboBox(tab);
        panSmall.add(comboBox);
        comboBox.setFont(new Font(" TimesRoman ", Font.BOLD, 30));
        comboBox.setBounds(0, 60, 200, 50);
        JPanel adminPanel = new JPanel(new GridLayout(3, 1));

        adminFrame.setContentPane(adminPanel);
        adminPanel.add(comboBox);

        JButton buttonRemove = new JButton("Retirer enseignant.");
        JButton buttonreturn = new JButton("Retour.");

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

                secondFrameControllerExtern.removeTeacher(comboBox.getSelectedItem().toString());

            }
        });

    }

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
                secondFrameControllerExtern.addStudent(addStudentEmail.getText(), addStudentPassword.getText(), addStudentFirstName.getText(), addStudentLastName.getText(), addStudentNumber.getText(), comboBox.getSelectedItem().toString());


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

    public void removeStudentAdmin() {


        String[] tab = new String[secondFrameController.getAllStudent().size()];

        for (int i = 0; i < tab.length; i++) {
            tab[i] = secondFrameController.getAllStudent().get(i).getFirst_name() + "  " + secondFrameController.getAllStudent().get(i).getLast_name();
        }

        JComboBox comboBoxStudent;
        comboBoxStudent = new JComboBox(tab);
        panSmall.add(comboBoxStudent);
        comboBoxStudent.setFont(new Font(" TimesRoman ", Font.BOLD, 30));
        comboBoxStudent.setBounds(0, 60, 200, 50);
        JPanel adminPanel = new JPanel(new GridLayout(3, 1));

        adminFrame.setContentPane(adminPanel);
        adminPanel.add(comboBoxStudent);

        JButton buttonRemove = new JButton("Remove student.");
        JButton buttonreturn = new JButton("Return.");

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

                secondFrameControllerExtern.removeStudent(comboBoxStudent.getSelectedItem().toString());

            }
        });

    }

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


        JPanel addSessionAdmin = new JPanel(new GridLayout(9, 2));

        adminFrame.setContentPane(addSessionAdmin);

        JLabel jLabelDate = new JLabel("Date (yyyy-MM-dd) :");
        JTextField addSessionDate = new JTextField();

        JLabel jLabelStartTime = new JLabel("Start Time (HH:mm:ss) :");
        JTextField addSessionStartTime = new JTextField();

        JLabel jLabelEndTime = new JLabel("End Time (HH:mm:ss) :");
        JTextField addSessionEndTime = new JTextField();

        JLabel jLabelWeek = new JLabel("Week :");
        JTextField addSessionWeek = new JTextField();

        JButton buttonAddTeacher = new JButton("Ajouter Enseigant");
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
        comboBoxState.setBounds(0, 60, 200, 50);


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

        addSessionAdmin.add(buttonAddTeacher);
        addSessionAdmin.add(buttonReturn);

        buttonAddTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondFrameControllerExtern.addSession(addSessionWeek.getText(), addSessionDate.getText(),
                        addSessionStartTime.getText(), addSessionEndTime.getText(), comboBoxState.getSelectedItem().toString(), comboBox.getSelectedItem().toString(), comboBoxType.getSelectedItem().toString());


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

    public void removeSessionAdmin() {

        String[] tab = new String[secondFrameController.getAllTeacher().size()];

        for (int i = 0; i < tab.length; i++) {
            tab[i] = secondFrameController.getAllTeacher().get(i).getFirst_name() + "  " + secondFrameController.getAllTeacher().get(i).getLast_name();
        }

        JPanel adminPanel = new JPanel(new GridLayout(5, 1));
        adminFrame.setContentPane(adminPanel);
        adminFrame.setTitle("Remove session ");

        JComboBox comboBoxType = new JComboBox(tab);
        comboBoxType.setFont(new Font(" TimesRoman ", Font.BOLD, 25));

        JComboBox comboBoxCourse = new JComboBox();
        comboBoxCourse.setFont(new Font(" TimesRoman ", Font.BOLD, 25));

        JComboBox comboBoxSession = new JComboBox();
        comboBoxSession.setFont(new Font(" TimesRoman ", Font.BOLD, 25));


        JButton buttonReturn = new JButton("Return");
        buttonReturn.setFont(new Font(" TimesRoman ", Font.BOLD, 30));

        JButton buttonRemove = new JButton("Research");
        buttonRemove.setFont(new Font(" TimesRoman ", Font.BOLD, 30));

        adminPanel.add(comboBoxType);
        adminPanel.add(comboBoxCourse);
        adminPanel.add(comboBoxSession);
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
    }

    public void teacherFrame() {
        smallFen.setVisible(false);

        JFrame teacherFrame = new JFrame("Teacher Frame");
        teacherFrame.setBounds(0, 0, 460, 400);
        teacherFrame.setLocation(1000, 540);
        teacherFrame.setVisible(true);

        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(null);

        teacherFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void referentFrame() {
        smallFen.setVisible(false);

        JFrame referentFrame = new JFrame("Referent Frame");
        referentFrame.setBounds(0, 0, 460, 400);
        referentFrame.setLocation(1000, 540);
        referentFrame.setVisible(true);

        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(null);

        referentFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

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
                secondFrameControllerExtern.getChartSession().setVisible(true);
            }
        });

        buttonSiteData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondFrameControllerExtern.getChartSite().setVisible(true);
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

}
















