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

public class SecondFrameView implements Observer {

    public static final Color grisClair = new Color(192, 192, 192);

    JPasswordField textFieldPssword;
    JTextField textFieldConnexion;
    JButton buttonSearch;
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


    public SecondFrameView(SecondFrameController secondFrameController) {

        this.secondFrameController = secondFrameController;
        connexionUser();

    }

    public void connexionUser() {
        adminFrame.setVisible(false);

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


    }


    @Override
    public void update(Observable o, Object arg) {

        //JOptionPane.showMessageDialog(null, id);


    }


    public void adminFrame() {

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

        JFrame studentFrame = new JFrame("Student Frame");
        JPanel studentPanel = new JPanel();

        studentFrame.setBounds(0, 0, 460, 400);
        studentFrame.setLocation(1000, 540);
        studentFrame.setVisible(true);


        studentPanel.setLayout(null);

        studentFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void addTeacherAdmin() {
        JPanel adminPanel = new JPanel(new GridLayout(6, 2));

        JTextField addTeacherLastName = new JTextField();
        JTextField addTeacherFirstName = new JTextField();
        JTextField addTeacherEmail = new JTextField();
        JLabel addTeacherPassword = new JLabel("permission :");
        JTextField addTeacherPermission = new JTextField("Permission :");
        JTextField addTeacherCourse = new JTextField();

        JButton buttonAddTeacher = new JButton("Ajouter Enseigant");

        buttonAddTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(addTeacherPermission.getText());
            }
        });

        addTeacherFirstName.setFont(new Font(" Arial ", Font.BOLD, 30));
        addTeacherLastName.setFont(new Font(" Arial ", Font.BOLD, 30));
        addTeacherEmail.setFont(new Font(" Arial ", Font.BOLD, 30));
        addTeacherPassword.setFont(new Font(" Arial ", Font.BOLD, 30));
        addTeacherPermission.setFont(new Font(" Arial ", Font.BOLD, 30));
        addTeacherCourse.setFont(new Font(" Arial ", Font.BOLD, 30));

        adminPanel.add(addTeacherFirstName);
        adminPanel.add(addTeacherEmail);
        adminPanel.add(addTeacherPassword);
        adminPanel.add(addTeacherPermission);
        adminPanel.add(addTeacherCourse);

        adminPanel.add(addTeacherLastName);
        adminPanel.add(buttonAddTeacher);
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

                System.out.println(comboBox.getSelectedItem().toString());

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

        JButton buttonSessionData = new JButton("Sessions Data");
        JButton buttonSiteData = new JButton("Site Data");
        JButton buttonReturn = new JButton("Return");

        JPanel adminPanel = new JPanel(new GridLayout(6, 2));

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
















