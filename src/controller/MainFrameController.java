package controller;


import dao.*;
import models.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainFrameController {

    String url = "jdbc:mysql://localhost:3306/projets1?serverTimezone=UTC";
    String username = "root";
    String password = "";

    private PromotionDAO promotionDao = new PromotionDAO(url, username, password);
    private Promotion promotion = new Promotion();
    private SessionDAO sessionDAO = new SessionDAO(url, username, password);
    private CourseDAO courseDAO = new CourseDAO(url,username,password);
    private CourseTypeDAO courseTypeDAO = new CourseTypeDAO(url,username,password);
    private TeacherDAO teacherDAO = new TeacherDAO(url,username,password);
    private StudentDAO studentDAO = new StudentDAO(url,username,password);
    private RoomDAO roomDAO = new RoomDAO(url,username,password);
    private Session session = new Session();
    private TeacherSessionDAO teacherSessionDAO = new TeacherSessionDAO(url, username, password);

    public MainFrameController(Session session, Promotion promotion) {
        this.session = session;
        this.promotion = promotion;
    }

    public void getPromotion(Long id) {
        this.promotion.setPromotion(promotionDao.getPromotionByID(id));
    }


    public List<Session> getSessionLundi(User user, String week) {
        List<Session> list = new ArrayList<>();

        for (Session s : this.sessionDAO.getWeekSessionStudent(user, week)) {
            LocalDate date;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(s.getDate(), dateFormatter);
            if (date.getDayOfWeek() == DayOfWeek.MONDAY) {
                list.add(s);
            }
        }
        return list;
    }

    public List<Session> getSessionMardi(User user, String week) {
        List<Session> list = new ArrayList<>();

        for (Session s : this.sessionDAO.getWeekSessionStudent(user, week)) {
            LocalDate date;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(s.getDate(), dateFormatter);
            if (date.getDayOfWeek() == DayOfWeek.TUESDAY) {
                list.add(s);
            }
        }
        return list;
    }

    public List<Session> getSessionMercredi(User user, String week) {
        List<Session> list = new ArrayList<>();

        for (Session s : this.sessionDAO.getWeekSessionStudent(user, week)) {
            LocalDate date;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(s.getDate(), dateFormatter);
            if (date.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
                list.add(s);
            }
        }

        return list;
    }

    public List<Session> getSessionJeudi(User user, String week) {
        List<Session> list = new ArrayList<>();

        for (Session s : this.sessionDAO.getWeekSessionStudent(user, week)) {
            LocalDate date;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(s.getDate(), dateFormatter);
            if (date.getDayOfWeek() == DayOfWeek.THURSDAY) {
                list.add(s);
            }
        }

        return list;
    }

    public List<Session> getSessionVendredi(User user, String week) {
        List<Session> list = new ArrayList<>();

        for (Session s : this.sessionDAO.getWeekSessionStudent(user, week)) {
            LocalDate date;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(s.getDate(), dateFormatter);
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                list.add(s);
            }
        }
        return list;
    }

    public List<Session> getSessionSamedi(User user, String week) {
        List<Session> list = new ArrayList<>();

        for (Session s : this.sessionDAO.getWeekSessionStudent(user, week)) {
            LocalDate date;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(s.getDate(), dateFormatter);
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
                list.add(s);
            }
        }
        return list;
    }

    public List<Session> getSessionByWeekForStudent(User user, String weekSelected) {
        return sessionDAO.getWeekSessionStudent(user, weekSelected);
    }

    public List<Session> getSessionByWeekForTeacher(User user, String weekSelected) {
        return sessionDAO.getWeekSessionTeacher(user, weekSelected);


    }

    public List<Session> getSessionLundiTeacher(User user, String week) {
        List<Session> list = new ArrayList<>();

        for (Session s : this.sessionDAO.getWeekSessionTeacher(user, week)) {
            LocalDate date;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(s.getDate(), dateFormatter);
            if (date.getDayOfWeek() == DayOfWeek.MONDAY) {
                list.add(s);
            }
        }
        return list;
    }

    public List<Session> getSessionMardiTeacher(User user, String week) {
        List<Session> list = new ArrayList<>();

        for (Session s : this.sessionDAO.getWeekSessionTeacher(user, week)) {
            LocalDate date;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(s.getDate(), dateFormatter);
            if (date.getDayOfWeek() == DayOfWeek.TUESDAY) {
                list.add(s);
            }
        }
        return list;
    }

    public List<Session> getSessionMercrediTeacher(User user, String week) {
        List<Session> list = new ArrayList<>();

        for (Session s : this.sessionDAO.getWeekSessionTeacher(user, week)) {
            LocalDate date;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(s.getDate(), dateFormatter);
            if (date.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
                list.add(s);
            }
        }

        return list;
    }

    public List<Session> getSessionJeudiTeacher(User user, String week) {
        List<Session> list = new ArrayList<>();

        for (Session s : this.sessionDAO.getWeekSessionTeacher(user, week)) {
            LocalDate date;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(s.getDate(), dateFormatter);
            if (date.getDayOfWeek() == DayOfWeek.THURSDAY) {
                list.add(s);
            }
        }

        return list;
    }

    public List<Session> getSessionVendrediTeacher(User user, String week) {
        List<Session> list = new ArrayList<>();

        for (Session s : this.sessionDAO.getWeekSessionTeacher(user, week)) {
            LocalDate date;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(s.getDate(), dateFormatter);
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                list.add(s);
            }
        }
        return list;
    }

    public List<Session> getSessionSamediTeacher(User user, String week) {
        List<Session> list = new ArrayList<>();

        for (Session s : this.sessionDAO.getWeekSessionTeacher(user, week)) {
            LocalDate date;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(s.getDate(), dateFormatter);
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
                list.add(s);
            }
        }
        return list;
    }

    public List<Session> getSessionLundiRoom(Room room, String week) {
        List<Session> list = new ArrayList<>();

        for (Session s : this.sessionDAO.getWeekSessionRoom(room, week)) {
            LocalDate date;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(s.getDate(), dateFormatter);
            if (date.getDayOfWeek() == DayOfWeek.MONDAY) {
                list.add(s);
            }
        }
        return list;
    }

    public List<Session> getSessionMardiRoom(Room room, String week) {
        List<Session> list = new ArrayList<>();

        for (Session s : this.sessionDAO.getWeekSessionRoom(room, week)) {
            LocalDate date;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(s.getDate(), dateFormatter);
            if (date.getDayOfWeek() == DayOfWeek.TUESDAY) {
                list.add(s);
            }
        }
        return list;
    }

    public List<Session> getSessionMercrediRoom(Room room, String week) {
        List<Session> list = new ArrayList<>();

        for (Session s : this.sessionDAO.getWeekSessionRoom(room, week)) {
            LocalDate date;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(s.getDate(), dateFormatter);
            if (date.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
                list.add(s);
            }
        }
        return list;
    }

    public List<Session> getSessionJeudiRoom(Room room, String week) {
        List<Session> list = new ArrayList<>();

        for (Session s : this.sessionDAO.getWeekSessionRoom(room, week)) {
            LocalDate date;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(s.getDate(), dateFormatter);
            if (date.getDayOfWeek() == DayOfWeek.THURSDAY) {
                list.add(s);
            }
        }
        return list;
    }

    public List<Session> getSessionVendrediRoom(Room room, String week) {
        List<Session> list = new ArrayList<>();

        for (Session s : this.sessionDAO.getWeekSessionRoom(room, week)) {
            LocalDate date;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(s.getDate(), dateFormatter);
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                list.add(s);
            }
        }
        return list;
    }

    public List<Session> getSessionSamediRoom(Room room, String week) {
        List<Session> list = new ArrayList<>();

        for (Session s : this.sessionDAO.getWeekSessionRoom(room, week)) {
            LocalDate date;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(s.getDate(), dateFormatter);
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
                list.add(s);
            }
        }
        return list;
    }

    public Teacher getTeacherByName(String teacherSelected){
        String output[] = teacherSelected.split("\\s+");
        String firstName = output[0];
        String lastName = output[1];

        return teacherDAO.findTeacherByName(lastName);
    }

    public Student getStudentByName(String studentSelected){
        String output[] = studentSelected.split("\\s+");
        String firstName = output[0];
        String lastName = output[1];

        return studentDAO.findStudentByLastName(lastName);
    }

    public Room getRoomByName(String roomSelected){


        return roomDAO.readRoomByName(roomSelected);
    }

    public String getTeacherNameFromIdSession(Long idSession) {
        return teacherSessionDAO.getIdTeacherFromIdSession(idSession);
    }


    public String getInformation(Session session){

        return  courseDAO.getCourseById(session.getID_course()).getName() + "--" + courseTypeDAO.readCourseTypeById(session.getID_type()).getType() ;
    }


}
