import InterfaceGraphique.Affichage;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {


        //TestConnection testCo = new TestConnection();
       // testCo.searchName("momo");
        /*testCo.cleanDB();
        testCo.addRow("toto", "burdin");
        testCo.searchName("Parraud");*/


        Affichage aff = new Affichage();
        aff.showFenetrePrincipale();
        aff.afficherFen();
        aff.afficherSmallFen();
        aff.showSmallFen();





    }
}
