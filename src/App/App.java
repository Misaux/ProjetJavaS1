package App;
import Cours.*;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {
    private JPanel panel1;
    private JButton buttonMsgButton;
    private JTextField textField1;
    private JCheckBox checkBox1;


    //Cours test = new Cours(Cours.Campus.LYON, Cours.Batiment.C, 402, Cours.TypeCourse.PRESENTIEL, Cours.Groupe.A, Cours.Groupe.A);


    public App() {
        buttonMsgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(test.toString());
            }
        });


    }

    public static void main(String[] args) {
        JFrame app = new JFrame("App");
        JTextField text = new JTextField();
        app.setContentPane(new App().panel1);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(200, 300);
        app.setLocation(1920/2,1080/2);
        app.setVisible(true);

    }
}