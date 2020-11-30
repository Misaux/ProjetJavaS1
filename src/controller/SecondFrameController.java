package controller;

import DAO.*;
import InterfaceDao.UserDao;
import Models.*;
import com.mysql.cj.util.StringUtils;
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

    private User user = new User();
    private Session session = new Session();
    private Promotion promotion = new Promotion();
    private RoomDAO roomDAO = new RoomDAO(url, username, password);
    private Room room = new Room();



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

    public List<Teacher> getAllTeacher() {
        return teacherDAO.getAllTeacher();
    }

    public List<Course> getAllCourse() {
        return courseDAO.getAllCourse();
    }

    public List<CourseType> getAllCourseType() {
        return courseTypeDAO.getAllCourseType();
    }

    public List<String> getAllSessionState() {
        return sessionDAO.getAllSessionState();
    }

    public List<Session> getAllSession() {
        return sessionDAO.getAllSession();
    }

    public List<String> getAllSessionStartTime() {
        return sessionDAO.getAllSessionStartTime();
    }

    public List<String> getAllSessionDate() {
        return sessionDAO.getAllSessionDate();
    }

    public List<GroupPromo> getAllGroupPromotion() {
        return groupPromoDAO.getAllGroupPromo();
    }

    public List<Student> getAllStudent() {
        return studentDAO.getAllStudent();
    }

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

    public void addTeacher(String email, String password, String first_name, String last_name, String courseSelected) {

        Teacher teacher = new Teacher();
        teacher.setFirst_name(first_name);
        teacher.setLast_name(last_name);
        teacher.setEmail(email);
        teacher.setPassword(password);
        teacher.setPermission("ENSEIGNANT");

        userDao.createUser(teacher);

        teacher.setIdUser(userDao.findUserByLastName(teacher.getLast_name()).getID());
        teacher.setIdCourse(courseDAO.readCourseByName(courseSelected).getID());

        teacherDAO.createTeacher(teacher);


    }

    public void removeTeacher(String teacherSelected) {
        String output[] = teacherSelected.split("\\s+");
        String firstName = output[0];
        String lastName = output[1];
        Teacher teacher = new Teacher();
        teacher.setLast_name(lastName);
        teacher.setFirst_name(firstName);
        userDao.deleteUser(teacher);
    }

    public void addStudent(String email, String password, String first_name, String last_name, String numberStudentType, String groupSelected) {

        Student student = new Student();
        student.setFirst_name(first_name);
        student.setLast_name(last_name);
        student.setEmail(email);
        student.setPassword(password);
        student.setPermission("ELEVE");

        int numberStudent = Integer.parseInt(numberStudentType);

        userDao.createUser(student);

        student.setIdUser(userDao.findUserByLastName(student.getLast_name()).getID());
        student.setIdGroupPromotion(groupPromoDAO.readGroupPromoByName(groupSelected).getID());
        student.setNumber(numberStudent);

        studentDAO.createStudent(student);


    }

    public void removeStudent(String studentSelected) {

        String output[] = studentSelected.split("\\s+");
        String firstName = output[0];
        String lastName = output[1];

        Student student = new Student();
        student.setLast_name(lastName);
        student.setFirst_name(firstName);

        userDao.deleteUser(student);


    }

    public void addSession(String weekSession, String date, String startTime, String endTime, String state, String courseSelected, String typeSelected, String teacherSelected, String groupSelected) {


        String output[] = teacherSelected.split("\\s+");
        String firstName = output[0];
        String lastName = output[1];
        Session session = new Session();
        int week = Integer.parseInt(weekSession);
        session.setWeek(week);
        session.setDate(date);
        session.setStartTime(startTime);
        session.setEndTime(endTime);
        session.setID_course(courseDAO.readCourseByName(courseSelected).getID());
        session.setID_type(courseTypeDAO.readCourseTypeByName(typeSelected).getID());
        session.setState(state);
        sessionDAO.createSession(session);

        TeacherSession teacherSession = new TeacherSession();
        teacherSession.setIdSession(sessionDAO.readSession(session.getWeek(),session.getDate(),session.getStartTime(),session.getID_course()).getID());
        teacherSession.setIdTeacher(userDao.findUserByLastName(lastName).getID());
        teacherSessionDAO.createTeacherSession(teacherSession);

        GroupSession groupSession = new GroupSession();
        groupSession.setIdGroup(groupPromoDAO.readGroupPromoByName(groupSelected).getID());
        groupSession.setIdSession(sessionDAO.readSession(session.getWeek(),session.getDate(),session.getStartTime(),session.getID_course()).getID());
        groupSessionDAO.createGroupSession(groupSession);



    }

    public void removeSession(String dateselected , String startTimeSelected , String courseSelected) {


        Session session = new Session();
        session.setDate(dateselected);
        session.setStartTime(startTimeSelected);
        session.setID_course(courseDAO.readCourseByName(courseSelected).getID());
        sessionDAO.deleteSession(session);


    }


    public List<Session> getSessionByWeek(User user, String weekSelected){
        return sessionDAO.getWeekSession(user, weekSelected);
    }

}



