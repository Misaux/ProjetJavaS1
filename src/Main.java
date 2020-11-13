import DAO.CourseDAO;
import DAO.PromotionDAO;
import Models.*;
import DAO.SiteDAO;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/projetjava?serverTimezone=UTC";
        String username = "root";
        String password = "";


        Site site= new Site();
        Course course = new Course();
        Promotion promotion = new Promotion();

        course.setName(" Informatique ");
        site.setName(" Campus INSEEC U Lyon");
        promotion.setName(" Promotion 2023");

        SiteDAO siteDAO= new SiteDAO(url,username,password);
        CourseDAO courseDAO = new CourseDAO(url,username,password);
        PromotionDAO promotionDAO = new PromotionDAO(url,username,password);

       /* courseDAO.saveCourse(course);

        siteDAO.saveSite(site);*/

        promotionDAO.savePromotion(promotion);



    }
}
