package view;

import Models.Session;
import controller.SecondFrameController;
import Models.User;

import javax.management.remote.JMXAddressable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


    public SecondFrameView(SecondFrameController secondFrameController) {


        smallFen.setBounds(300, 200, 600, 300);
        smallFen.setContentPane(panSmall);
        smallFen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        smallFen.setLocation(1000, 540);


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


            }
        });


    }

    public void display(String name) {
        this.jTextField.setText(name);
    }

    @Override
    public void update(Observable o, Object arg) {

        //JOptionPane.showMessageDialog(null, id);
        System.out.println(user);

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
        JFrame adminFrame = new JFrame("Admin fenetre");
        JPanel adminPanel = new JPanel();
        adminFrame.setVisible(true);

        adminFrame.setContentPane(adminPanel);
        adminPanel.setLayout(null);
        adminFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        adminFrame.setBounds(300, 200, 460, 400);
        adminFrame.setLocation(1000, 540);

        JButton addTeacher = new JButton();
        JButton removeTeacher = new JButton();
        JButton addStudent = new JButton();
        JButton removeStudent = new JButton();
        JButton addSession = new JButton();
        JButton removeSession = new JButton();

        JLabel labelAdminInfo = new JLabel();


        adminPanel.add(addTeacher);
        addTeacher.setBounds(10, 10, 200, 70);
        addTeacher.setText("Ajouter Enseignant");

        adminPanel.add(removeTeacher);
        removeTeacher.setBounds(230, 10, 200, 70);
        removeTeacher.setText("Retirer Enseignant");

        /*///////////////////////////////////////////////////////////*/
        adminPanel.add(addStudent);
        addStudent.setBounds(10, 100, 200, 70);
        addStudent.setText("Ajouter Eleve");

        adminPanel.add(removeStudent);
        removeStudent.setBounds(230, 100, 200, 70);
        removeStudent.setText("Retirer Eleve");

        /*///////////////////////////////////////////////////////////*/

        adminPanel.add(addSession);
        addSession.setBounds(10, 190, 200, 70);
        addSession.setText("Ajouter Session");

        adminPanel.add(removeSession);
        removeSession.setBounds(230, 190, 200, 70);
        removeSession.setText("Retirer Session");
        removeSession.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondFrameControllerExtern.getChartSession();
            }
        });

        /*///////////////////////////////////////////////////////////*/

        adminPanel.add(labelAdminInfo);
        labelAdminInfo.setBounds(10, 300, 300, 70);
        labelAdminInfo.setText("Session ouvert par : " + user.getLast_name() + " " + user.getFirst_name());
        labelAdminInfo.setFont(new Font(labelAdminInfo.getName(), Font.BOLD, 16));


    }

    public void studentFrame() {
        smallFen.setVisible(false);

        JFrame studentFrame = new JFrame("Student Frame");
        studentFrame.setBounds(0, 0, 460, 400);
        studentFrame.setLocation(1000, 540);
        studentFrame.setVisible(true);

        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(null);

        studentFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
}
















