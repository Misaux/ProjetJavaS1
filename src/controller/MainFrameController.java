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





    /**
     * Constructeur de la classe qui instancie la session et la promotion du user qui se connecte
     * @param session session de base
     * @param promotion promotion de base
     */
    public MainFrameController(Session session, Promotion promotion) {
        this.session = session;
        this.promotion = promotion;
    }

    /**
     * Methode qui permet de recuperer la promotion voulue grace a son id passe en parametre
     * @param id id de la promotion
     */
    public void getPromotion(Long id) {
        this.promotion.setPromotion(promotionDao.getPromotionByID(id));
    }

    /**
     * Fonction pour obtenir la liste de toutes les sessions ayant lieu un lundi pour le user en parametre
     * @param user user pour lequel on recherche les sessions
     * @param week semaine ou ont lieu les sessions
     * @return List<Session></Session>
     */
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

    /**
     * Fonction pour obtenir la liste de toutes les sessions ayant lieu un mardi pour le user en parametre
     * @param user user pour lequel on recherche les sessions
     * @param week semaine ou ont lieu les sessions
     * @return List<Session></Session>
     */
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

    /**
     * Fonction pour obtenir la liste de toutes les sessions ayant lieu un mercredi pour le user en parametre
     * @param user user pour lequel on recherche les sessions
     * @param week semaine ou ont lieu les sessions
     * @return List<Session></Session>
     */
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

    /**
     * Fonction pour obtenir la liste de toutes les sessions ayant lieu un jeudi pour le user en parametre
     * @param user user pour lequel on recherche les sessions
     * @param week semaine ou ont lieu les sessions
     * @return List<Session></Session>
     */
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

    /**
     * Fonction pour obtenir la liste de toutes les sessions ayant lieu un vendredi pour le user en parametre
     * @param user user pour lequel on recherche les sessions
     * @param week semaine ou ont lieu les sessions
     * @return List<Session></Session>
     */
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

    /**
     * Fonction pour obtenir la liste de toutes les sessions ayant lieu un samedi pour le user en parametre
     * @param user user pour lequel on recherche les sessions
     * @param week semaine ou ont lieu les sessions
     * @return List<Session></Session>
     */
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

    /**
     * Fonction pour obtenir la liste de toutes les sessions ayant lieu durant la semaine pour le user en parametre
     * @param user eleve pour lequel on recherche les sessions
     * @param weekSelected semaine ou ont lieu les sessions
     * @return List<Session></Session>
     */
    public List<Session> getSessionByWeekForStudent(User user, String weekSelected) {
        return sessionDAO.getWeekSessionStudent(user, weekSelected);
    }

    /**
     * Fonction pour obtenir la liste de toutes les sessions ayant lieu durant la semaine pour le professeur en parametre
     * @param user professeur pour lequel on recherche les sessions
     * @param weekSelected semaine ou ont lieu les sessions
     * @return List<Session></Session>
     */
    public List<Session> getSessionByWeekForTeacher(User user, String weekSelected) {
        return sessionDAO.getWeekSessionTeacher(user, weekSelected);


    }

    /**
     * Fonction pour obtenir la liste de toutes les sessions ayant lieu un lundi durant la semaine specifiee pour le professeur passe en parametre
     * @param user professeur pour lequel on recherche les sessions
     * @param week semaine ou ont lieu les sessions
     * @return List<Session></Session>
     */
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

    /**
     * Fonction pour obtenir la liste de toutes les sessions ayant lieu un mardi durant la semaine specifiee pour le professeur passe en parametre
     * @param user professeur pour lequel on recherche les sessions
     * @param week semaine ou ont lieu les sessions
     * @return List<Session></Session>
     */
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

    /**
     * Fonction pour obtenir la liste de toutes les sessions ayant lieu un mercredi durant la semaine specifiee pour le professeur passe en parametre
     * @param user professeur pour lequel on recherche les sessions
     * @param week semaine ou ont lieu les sessions
     * @return List<Session></Session>
     */
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

    /**
     * Fonction pour obtenir la liste de toutes les sessions ayant lieu un jeudi durant la semaine specifiee pour le professeur passe en parametre
     * @param user professeur pour lequel on recherche les sessions
     * @param week semaine ou ont lieu les sessions
     * @return List<Session></Session>
     */
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

    /**
     * Fonction pour obtenir la liste de toutes les sessions ayant lieu un vendredi durant la semaine specifiee pour le professeur passe en parametre
     * @param user professeur pour lequel on recherche les sessions
     * @param week semaine ou ont lieu les sessions
     * @return List<Session></Session>
     */
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

    /**
     * Fonction pour obtenir la liste de toutes les sessions ayant lieu un samedi durant la semaine specifiee pour le professeur passe en parametre
     * @param user professeur pour lequel on recherche les sessions
     * @param week semaine ou ont lieu les sessions
     * @return List<Session></Session>
     */
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

    /**
     * Fonction pour obtenir la liste de toutes les sessions ayant lieu un lundi durant la semaine specifiee pour la salle passee en parametre
     * @param room salle pour laquelle on recherche les sessions
     * @param week semaine ou ont lieu les sessions
     * @return List<Session></Session>
     */
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

    /**
     * Fonction pour obtenir la liste de toutes les sessions ayant lieu un mardi durant la semaine specifiee pour la salle passee en parametre
     * @param room salle pour laquelle on recherche les sessions
     * @param week semaine ou ont lieu les sessions
     * @return List<Session></Session>
     */
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

    /**
     * Fonction pour obtenir la liste de toutes les sessions ayant lieu un mercredi durant la semaine specifiee pour la salle passee en parametre
     * @param room salle pour laquelle on recherche les sessions
     * @param week semaine ou ont lieu les sessions
     * @return List<Session></Session>
     */
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

    /**
     * Fonction pour obtenir la liste de toutes les sessions ayant lieu un jeudi durant la semaine specifiee pour la salle passee en parametre
     * @param room salle pour laquelle on recherche les sessions
     * @param week semaine ou ont lieu les sessions
     * @return List<Session></Session>
     */
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

    /**
     * Fonction pour obtenir la liste de toutes les sessions ayant lieu un vendredi durant la semaine specifiee pour la salle passee en parametre
     * @param room salle pour laquelle on recherche les sessions
     * @param week semaine ou ont lieu les sessions
     * @return List<Session></Session>
     */
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

    /**
     * Fonction pour obtenir la liste de toutes les sessions ayant lieu un samedi durant la semaine specifiee pour la salle passee en parametre
     * @param room salle pour laquelle on recherche les sessions
     * @param week semaine ou ont lieu les sessions
     * @return List<Session></Session>
     */
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

    /**
     * Fonction permettant de recuperer un enseignant grace a son nom de famille et son prenom
     * @param teacherSelected enseignant recherche
     * @return Teacher de la BDD
     */
    public Teacher getTeacherByName(String teacherSelected){
        String output[] = teacherSelected.split("\\s+");
        String firstName = output[0];
        String lastName = output[1];

        return teacherDAO.findTeacherByName(lastName);
    }

    /**
     * Fonction permettant de recuperer un eleve grace a son nom de famille et son prenom
     * @param studentSelected eleve recherche
     * @return Student de la BDD
     */
    public Student getStudentByName(String studentSelected){
        String output[] = studentSelected.split("\\s+");
        String firstName = output[0];
        String lastName = output[1];

        return studentDAO.findStudentByLastName(lastName);
    }

    /**
     * Fonction permettant de recuperer une salle grace a son nom
     * @param roomSelected salle recherchee
     * @return Room de la BDD
     */
    public Room getRoomByName(String roomSelected){


        return roomDAO.readRoomByName(roomSelected);
    }

    /**
     * Fonction qui permet de recuperer un enseignant grace a une session passee en parametre
     * @param idSession session permettant de recuperer le professeur
     * @return String comportant l'id du teacher
     */
    public String getTeacherFromIdSession(Long idSession) {
        return teacherSessionDAO.getIdTeacherFromIdSession(idSession);
    }

    /**
     * Fonction permettant de retourner une string contenant la matiere et le type de cours de la session
     * @param session session dont nous voulons svoir nos informations
     * @return String comportant nos informations
     */
    public String getInformation(Session session){

        return  courseDAO.getCourseById(session.getID_course()).getName() + "--" + courseTypeDAO.readCourseTypeById(session.getID_type()).getType() ;
    }


}
