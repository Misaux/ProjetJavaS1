package controller;

import DAO.*;
import InterfaceDao.UserDao;
import Models.Promotion;
import Models.Session;
import Models.User;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.util.Observable;

public class SecondFrameController extends Observable {

    String url = "jdbc:mysql://localhost:3306/projets1?serverTimezone=UTC";
    String username = "root";
    String password = "";

    private UserDAO userDao = new UserDAO(url, username, password);
    private User user = new User();

    private PromotionDAO promotionDao = new PromotionDAO(url, username, password);
    private Promotion promotion = new Promotion();
    private SessionDAO sessionDAO = new SessionDAO(url, username, password);
    private Session session = new Session();

    public SecondFrameController() {
    }

    public SecondFrameController(Session session, User user) {
        this.session = session;
        this.user = user;
    }

    public User getUser(String connexion, String password) {
        this.user = userDao.getUserConnection(connexion, password);
        setChanged();
        notifyObservers(this);
        return this.user;
    }

    public void getPromotion(Long id) {
        this.promotion.setName(promotionDao.getPromotionByID(id).getName());
    }

    public void getChartSession() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        // create a chart...
        JFreeChart chart = ChartFactory.createPieChart(
                "Répartition des cours",
                sessionDAO.readData(),
                true, // legend?
                true, // tooltips?
                false // URLs?
        );
        // create and display a frame...
        ChartFrame frame = new ChartFrame("First", chart);
        frame.pack();
        frame.setVisible(true);
    }

    public void getSession() {
        this.session.setSession(sessionDAO.readSession(1, "2020-11-23", "09:30:00"));
        System.out.println(this.session.toString());
    }

    public void showSessionPlanning() {

        setChanged();
        notifyObservers(this.session);


    }

    public void testMVC() {
        setChanged();
        notifyObservers(this);
    }

    public void testControlExtern() {

    }


}



