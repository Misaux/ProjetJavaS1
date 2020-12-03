package controller;

import dao.*;
import models.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Observable;

public class SecondFrameController extends Observable {

    String url = "jdbc:mysql://localhost:3306/projets1?serverTimezone=UTC";
    String username = "root";
    String password = "";

    private UserDAO userDao = new UserDAO(url, username, password);
    private PromotionDAO promotionDao = new PromotionDAO(url, username, password);
    private GroupPromoDAO groupPromoDAO = new GroupPromoDAO(url, username, password);
    private SessionDAO sessionDAO = new SessionDAO(url, username, password);
    private TeacherDAO teacherDAO = new TeacherDAO(url, username, password);
    private TeacherSessionDAO teacherSessionDAO = new TeacherSessionDAO(url,username,password);
    private StudentDAO studentDAO = new StudentDAO(url, username, password);
    private CourseDAO courseDAO = new CourseDAO(url, username, password);
    private CourseTypeDAO courseTypeDAO = new CourseTypeDAO(url, username, password);
    private GroupSessionDAO groupSessionDAO = new GroupSessionDAO(url,username,password);
    private RoomSessionDAO roomSessionDAO = new RoomSessionDAO(url,username,password);


    private User user = new User();
    private Session session = new Session();
    private Promotion promotion = new Promotion();
    private RoomDAO roomDAO = new RoomDAO(url, username, password);
    private Room room = new Room();


    /**
     * Constructeur de la classe qui instancie la session et le user
     * @param session session de base
     * @param user user de base
     */
    public SecondFrameController(Session session, User user) {
        this.session = session;
        this.user = user;
    }

    /**
     *retourne un user qui aura comme attributs ceux revoyes par la BDD
     * @param connexion adresse email
     * @param password  mot de passe
     * @return user de la BDD
     */
    public User getUser(String connexion, String password) {
        this.user = userDao.getUserConnection(connexion, password);
        setChanged();
        notifyObservers(this);
        return this.user;
    }

    /**
     *fonction qui retourne une promotion que l'on aura selectionne par son id
     * @param id id de la promo que l'on souhaite obtenir
     */
    public void getPromotion(Long id) {
        this.promotion.setName(promotionDao.getPromotionByID(id).getName());
    }

    /**
     *  Fonction pour afficher la repartition des sessions sur l'ensemble de la BDD
     *  et l'affiche en camembert
     * @return fenetre JfreeChart
     */
    public JFrame getChartSession() {

        JFrame frame = new JFrame();
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
        frame = new ChartFrame("Répartitions des cours ", chart);
        frame.pack();
        return frame;

    }

    /**
     *fonction pour obtenir la liste de tous les enseignants de la BDD
     * @return list<Teacher></Teacher>
     */
    public List<Teacher> getAllTeacher() {
        return teacherDAO.getAllTeacher();
    }

    /**
     *fonction pour obtenir la liste de tous les cours de la BDD
     * @return List<Course></Course>
     */
    public List<Course> getAllCourse() {
        return courseDAO.getAllCourse();
    }

    /**
     *fonction pour obtenir la liste de tous les types de cours de la BDD
     * @return List<CourseType></CourseType>
     */
    public List<CourseType> getAllCourseType() {
        return courseTypeDAO.getAllCourseType();
    }

    /**
     *fonction pour obtenir la liste de tous les etats possibles de cours de la BDD
     * @return List<String></String>
     */
    public List<Room> getAllRoom(){ return roomDAO.getAllRoom(); }

    public List<String> getAllSessionState() {
        return sessionDAO.getAllSessionState();
    }

    /**
     *fonction pour obtenir la liste de toutes les sessions de la BDD
     * @return List <Session></Session>
     */
    public List<Session> getAllSession() {
        return sessionDAO.getAllSession();
    }

    /**
     * fonction pour obtenir la liste de toutes les heures de debuts de cours de la BDD
     * @return List<String></String>
     */
    public List<String> getAllSessionStartTime() {
        return sessionDAO.getAllSessionStartTime();
    }

    /**
     * fonction pour obtenir la liste de toutes les dates de sessions de la BDD
     * @return List<String></String>
     */
    public List<String> getAllSessionDate() {
        return sessionDAO.getAllSessionDate();
    }

    /**
     * fonction pour obtenir la liste de tous les groupes de la BDD

     * @return List<GroupPromo></GroupPromo>
     */
    public List<GroupPromo> getAllGroupPromotion() {
        return groupPromoDAO.getAllGroupPromo();
    }

    /**
     * fonction pour obtenir la liste de tous les eleves de la BDD
     * @return List<Student></Student>
     */
    public List<Student> getAllStudent() {
        return studentDAO.getAllStudent();
    }

    /**
     *Fonction pour afficher les informations des sites
     * (repartition des salles selon les campus, et la taille des salles)
     * sur l'ensemble de la BDD
     * et l'affiche en camembert
     * @return JFrame en camembert
     */
    public JFrame getChartSite() {


        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        JFreeChart chart = ChartFactory.createPieChart(
                "Capacity per site",
                roomDAO.readDataCapacityPerSite(),
                true, // legend?
                true, // tooltips?
                false);// URLs?

        frame.getContentPane().add(new ChartPanel(chart));
        JFreeChart chart1 = ChartFactory.createPieChart(
                "Number of room per site",
                roomDAO.readDataNumberPerSite(),
                true, // legend?
                true, // tooltips?
                false);// URLs?

        frame.getContentPane().add(new ChartPanel(chart1));
        frame.pack();

        return frame;

    }


    /**
     *Ajoute un enseignant a la base de donnees
     * @param email string, email de l'enseignant
     * @param password string, mot de passe pour se connecter
     * @param firstName string, prenom de l'enseignant
     * @param lastName string, nom de l'enseignant
     * @param courseSelected string, matiere de l'enseignant
     */
    public void addTeacher(String email, String password, String firstName, String lastName, String courseSelected) {

        Teacher teacher = new Teacher();
        teacher.setFirst_name(firstName);
        teacher.setLast_name(lastName);
        teacher.setEmail(email);
        teacher.setPassword(password);
        teacher.setPermission("ENSEIGNANT");


        if(teacherDAO.checkIfAlreadyCreated(lastName,firstName) == false)
        {
            userDao.createUser(teacher);
            teacher.setIdUser(userDao.findUserByLastName(teacher.getLast_name()).getID());
            teacher.setIdCourse(courseDAO.readCourseByName(courseSelected).getID());

            teacherDAO.createTeacher(teacher);

        }


    }

    /**
     * retire un enseignant de la BDD
     * @param teacherSelected string, nom et prenom de l'enseignant
     */
    public void removeTeacher(String teacherSelected) {
        String output[] = teacherSelected.split("\\s+");
        String firstName = output[0];
        String lastName = output[1];
        Teacher teacher = new Teacher();
        teacher.setLast_name(lastName);
        teacher.setFirst_name(firstName);
        userDao.deleteUser(teacher);
    }

    /**
     *
     * @param email string, email de l'eleve
     * @param password string, mot de passe de l'eleve
     * @param firstName string, prenom de l'eleve
     * @param lastName string, nom de l'eleve
     * @param numberStudentType string, numero de l'eleve
     * @param groupSelected string, group de l'eleve
     */
    public void addStudent(String email, String password, String firstName, String lastName, String numberStudentType, String groupSelected) {

        Student student = new Student();
        student.setFirst_name(firstName);
        student.setLast_name(lastName);
        student.setEmail(email);
        student.setPassword(password);
        student.setPermission("ELEVE");

        int numberStudent = Integer.parseInt(numberStudentType);

        if (studentDAO.checkIfAlreadyCreated(lastName, firstName, numberStudent) == false) {

            userDao.createUser(student);

            student.setIdUser(userDao.findUserByLastName(student.getLast_name()).getID());
            student.setIdGroupPromotion(groupPromoDAO.readGroupPromoByName(groupSelected).getID());
            student.setNumber(numberStudent);

            studentDAO.createStudent(student);


        }
    }

    /**
     *  retire un eleve
     * @param studentSelected string, nom et prenom de l'eleve
     */
    public void removeStudent(String studentSelected) {

        String output[] = studentSelected.split("\\s+");
        String firstName = output[0];
        String lastName = output[1];

        Student student = new Student();
        student.setLast_name(lastName);
        student.setFirst_name(firstName);

        userDao.deleteUser(student);


    }

        /**
         * Ajoute une session a la base de donnee
         * @param date string, date de la session
         * @param startTime string, heure de debut de la session
         * @param endTime string, heure de fin de la session
         * @param state string, etat de la session
         * @param courseSelected string, cours de la session
         * @param typeSelected string, type de la session
         * @param teacherSelected string, enseignant de la session
         * @param groupSelected string, group de la session
         */

    public void addSession(String date, String startTime, String endTime, String state, String courseSelected, String typeSelected, String teacherSelected, String groupSelected, String roomSelected) {


        String output[] = teacherSelected.split("\\s+");
        String firstName = output[0];
        String lastName = output[1];
        Session session = new Session();
        session.setDate(date);
        session.setStartTime(startTime);
        session.setEndTime(endTime);
        session.setWeek();
        session.setID_course(courseDAO.readCourseByName(courseSelected).getID());
        session.setID_type(courseTypeDAO.readCourseTypeByName(typeSelected).getID());
        session.setState(state);
        sessionDAO.createSession(session);

        TeacherSession teacherSession = new TeacherSession();
        teacherSession.setIdSession(sessionDAO.readSession(session.getWeek(),session.getDate(),session.getStartTime(),session.getID_course()).getID());
        teacherSession.setIdTeacher(userDao.findUserByLastName(lastName).getID());
        teacherSessionDAO.checkIfAlreadyAssociated(session.getStartTime(),
                teacherDAO.findTeacherByName(lastName).getID(),
                session.getDate());
        teacherSessionDAO.createTeacherSession(teacherSession);

        GroupSession groupSession = new GroupSession();
        groupSession.setIdGroup(groupPromoDAO.readGroupPromoByName(groupSelected).getID());
        groupSession.setIdSession(sessionDAO.readSession(session.getWeek(),session.getDate(),session.getStartTime(),session.getID_course()).getID());
        groupSessionDAO.checkIfAlreadyAssociated(session.getStartTime(),
                groupPromoDAO.readGroupPromoByName(groupSelected).getID(),
                session.getDate());
        groupSessionDAO.createGroupSession(groupSession);

        RoomSession roomSession = new RoomSession();
        roomSession.setIdRoom(roomDAO.readRoomByName(roomSelected).getID());
        roomSession.setIdSession(sessionDAO.readSession(session.getWeek(),session.getDate(),session.getStartTime(),session.getID_course()).getID());
        roomSessionDAO.checkIfAlreadyAssociated(session.getStartTime(),
                roomDAO.readRoomByName(roomSelected).getID(),
                session.getDate());
        roomSessionDAO.createRoomSession(roomSession);

    }

    /**
     * retire une session de la BDD
     * @param dateselected string, date de la session
     * @param startTimeSelected string, heure de debut de la session
     * @param courseSelected string, cours de la session
     */
    public void removeSession(String dateselected, String startTimeSelected , String courseSelected) {


        Session session = new Session();
        session.setDate(dateselected);
        session.setStartTime(startTimeSelected);
        session.setID_course(courseDAO.readCourseByName(courseSelected).getID());
        sessionDAO.deleteSession(session);


    }




}



