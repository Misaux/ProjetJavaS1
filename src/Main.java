import Models.*;

import DAO.SiteDAO;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        /*Course course = new Course();
        Course course1 = new Course();
        Course course2 = new Course();
        Course course3 = new Course();
        Teacher teacher = new Teacher(10, "novnrop","ohufirgivgir","jean ", "jean", User.Permission.ENSEIGNANT);
        teacher.addCourse(course);
        teacher.addCourse(course1);
        teacher.addCourse(course2);
        teacher.addCourse(course3);
        System.out.println(teacher.returnIdCourse());
        TestConnection testCo = new TestConnection();
        testCo.cleanDB();
        testCo.addRow("toto", "burdin");
        testCo.searchName("Parraud");
        Affichage aff = new Affichage();
        aff.showFenetrePrincipale();
        aff.afficherFen();
        aff.afficherSmallFen();
        aff.showSmallFen();*/

        String url = "jdbc:mysql://localhost:3306/projetloupe?serverTimezone=UTC";
        String username = "root";
        String password = "";


        Site site= new Site();

       site.setName(" Campus INSEEC U Paris");

        SiteDAO siteDAO= new SiteDAO(url,username,password);

        siteDAO.saveSite(site);





    }
}
