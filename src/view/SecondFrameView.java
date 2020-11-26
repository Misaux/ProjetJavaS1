package view;

import Models.Session;
import controller.SecondFrameController;
import Models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class SecondFrameView implements Observer {


    JPasswordField textFieldPssword;
    JTextField textFieldConnexion;
    JButton buttonSearch;
    JLabel label;
    JButton buttonReset;
    private JFrame smallFen = new JFrame("Petite Fen");
    private JPanel panSmall = new JPanel();
    JTextField jTextField = new JTextField();


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

        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondFrameController.showSessionPlanning();

            }
        });





        //Ajout d'une combobox


        textFieldPssword = new JPasswordField();
        panSmall.add(textFieldPssword);
        textFieldPssword.setEchoChar('*');
        textFieldPssword.setBounds(180, 60, 200, 50);


        //ajout du reset
        buttonReset = new JButton("Reset");
        panSmall.add(buttonReset);
        buttonReset.setBounds(220, 60, 100, 50);


        // ajout de l'affichage

        panSmall.add(jTextField);
        jTextField.setBounds(55, 200, 200, 50);


        panSmall.setBackground(Color.white);
        smallFen.setVisible(true);


    }

    public void display(String name) {
        this.jTextField.setText(name);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(o.getClass());
        display(((Session)(arg)).toString() );
    }
}
