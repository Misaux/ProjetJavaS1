package controller;


import DAO.*;
import InterfaceDao.PromotionDao;
import InterfaceDao.UserDao;
import Models.Promotion;
import Models.Session;
import Models.User;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainFrameController {

    String url = "jdbc:mysql://localhost:3306/projets1?serverTimezone=UTC";
    String username = "root";
    String password = "";

    private PromotionDAO promotionDao = new PromotionDAO(url, username,password);
    private Promotion promotion = new Promotion();
    private SessionDAO sessionDAO = new SessionDAO(url, username, password);
    private Session session = new Session();

    public MainFrameController(Session session, Promotion promotion) {
        this.session = session;
        this.promotion = promotion;
    }

    public void getPromotion(Long id){
        this.promotion.setPromo(promotionDao.getPromotionByID(id));
    }

    public Session  getSession(){
        this.session.setSession(sessionDAO.readSession(1, "2020-09-17", "09:30:00"));
        return this.session;
    }

    public List<Session> getSessionLundi() {
        List<Session> list = new ArrayList<>();

        for (Session s : this.sessionDAO.getAllSession()){
            LocalDate date;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date  = LocalDate.parse(s.getDate(),dateFormatter);
            if (date.getDayOfWeek() == DayOfWeek.MONDAY){
                list.add(s);
            }
        }
        return list;
    }

    public List<Session> getSessionMardi() {
        List<Session> list = new ArrayList<>();

                     for (Session s : this.sessionDAO.getAllSession()){
            LocalDate date;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date  = LocalDate.parse(s.getDate(),dateFormatter);
            if (date.getDayOfWeek() == DayOfWeek.TUESDAY){
                list.add(s);
            }
        }
        return list;
    }
    public List<Session> getSessionMercredi() {
        List<Session> list = new ArrayList<>();

        for (Session s : this.sessionDAO.getAllSession()){
            LocalDate date;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date  = LocalDate.parse(s.getDate(),dateFormatter);
            if (date.getDayOfWeek() == DayOfWeek.WEDNESDAY){
                list.add(s);
            }
        }

        return list;
    }
    public List<Session> getSessionJeudi() {
        List<Session> list = new ArrayList<>();

        for (Session s : this.sessionDAO.getAllSession()){
            LocalDate date;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date  = LocalDate.parse(s.getDate(),dateFormatter);
            if (date.getDayOfWeek() == DayOfWeek.THURSDAY){
                list.add(s);
            }
        }

        return list;
    }
    public List<Session> getSessionVendredi() {
        List<Session> list = new ArrayList<>();

        for (Session s : this.sessionDAO.getAllSession()){
            LocalDate date;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date  = LocalDate.parse(s.getDate(),dateFormatter);
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY){
                list.add(s);
            }
        }
        return list;
    }
    public List<Session> getSessionSamedi() {
        List<Session> list = new ArrayList<>();

        for (Session s : this.sessionDAO.getAllSession()){
            LocalDate date;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date  = LocalDate.parse(s.getDate(),dateFormatter);
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY){
                list.add(s);
            }
        }
        return list;
    }


}
