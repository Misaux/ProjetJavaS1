package controller;

import DAO.*;
import InterfaceDao.UserDao;
import Models.Promotion;
import Models.Session;
import Models.User;

import java.util.Observable;

public class SecondFrameController extends Observable {

    String url = "jdbc:mysql://localhost:3306/projets1?serverTimezone=UTC";
    String username = "root";
    String password = "";

    private UserDAO userDao = new UserDAO(url, username, password);
    private User user = new User();

    private PromotionDAO promotionDao = new PromotionDAO(url, username,password);
    private Promotion promotion = new Promotion();
    private SessionDAO sessionDAO = new SessionDAO(url, username, password);
    private Session session = new Session();

    public SecondFrameController(Session session) {
        this.session = session;
    }

    public void getUser(String connexion, String password) {
        this.user.setFirst_name(this.userDao.getUserFirstName(connexion, password));
        this.user.setLast_name(this.userDao.getUserName(connexion, password));
    }

    public void getPromotion(Long id){
        this.promotion.setName(promotionDao.getPromotionByID(id).getName());
    }

    public void getSession(){
        this.session.setSession(sessionDAO.readSession(1, "2020-11-23", "09:30:00"));
        System.out.println(this.session.toString());
    }

    public void showSessionPlanning(){

        setChanged();
        notifyObservers(this.session);


    }




}



