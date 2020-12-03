package dao;

import InterfaceDao.TeacherSessionDao;
import models.TeacherSession;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherSessionDAO implements TeacherSessionDao {

    private String url;
    private String username;
    private String password;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    /**
     * Constructeur de la classe TeacherSessionDAO
     * @param url url permettant d'acceder a PhpMyAdmin
     * @param username identifiant pour acceder PhpMyAdmin
     * @param password mot de passe pour acceder a PhpMyAdmin
     */
    public TeacherSessionDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * constructeur par defaut
     */
    public TeacherSessionDAO() {
    }

    /**
     * Fonction permettant d'obtenir une liste de toutes les teacherSession presentes dans la base de donnee
     * @return List<TeacherSession></TeacherSession>
     */
    @Override
    public List<TeacherSession> getAllTeacherSession() {
        List<TeacherSession> list = new ArrayList<>();
        try {
            this.connection = DriverManager.getConnection(this.url, this.username, this.password);
            this.preparedStatement = this.connection.prepareStatement("SELECT * from teacher_session");
            this.resultSet = this.preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long idSession = resultSet.getLong("id_session");
                Long idTeacher = resultSet.getLong("id_teacher");
                list.add(new TeacherSession(idSession, idTeacher));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Fonction permettant d'ajouter une teacherSession a la base de donnee
     * @param teacherSession Object teacherSession a inserer dans la base de donnee
     */
    @Override
    public void createTeacherSession(TeacherSession teacherSession) {

        try {
            this.connection = DriverManager.getConnection(url, username, password);


            this.preparedStatement = this.connection.prepareStatement
                    ("insert into teacher_session (id_teacher,id_session) values (?,?)");

            this.preparedStatement.setLong(2, teacherSession.getIdSession());
            this.preparedStatement.setLong(1, teacherSession.getIdTeacher());
            this.preparedStatement.execute();
            System.out.println(" saved into the database");


            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the product");
        }

    }

    /**
     * Fonction permettant de rechercher une teacherSession dans la base de donnee selon un id_session et un id_teacher
     * @param idSession id de la table session
     * @param idTeacher id de la table teacher
     * @return TeacherSession
     */
    @Override
    public TeacherSession readTeacherSession(Long idSession, Long idTeacher) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
            this.preparedStatement = this.connection.prepareStatement
                    ("select * from teacher_session where id_session = ? AND id_teacher = ? ");
            this.preparedStatement.setLong(1, idSession);
            this.preparedStatement.setLong(2, idTeacher);

            this.resultSet = this.preparedStatement.executeQuery();

            TeacherSession teacherSession = new TeacherSession();

            while (this.resultSet.next()) {
                teacherSession.setIdSession(this.resultSet.getLong("id_session"));
                teacherSession.setIdTeacher(this.resultSet.getLong("id_teacher"));
            }
            this.resultSet.close();
            this.preparedStatement.close();
            this.connection.close();
            return teacherSession;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Fonction permettant de mettre a jour une teacherSession passe en parametre dans la base de donee
     * si elle existe deja on la met a jour sinon on l'ajoute a la base de donnee
     * @param teacherSession Object TeacherSession qu'on veut mettre a jour
     */
    @Override
    public void updateTeacherSession(TeacherSession teacherSession) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);

            if (teacherSession.getIdSession() != null) {
                this.preparedStatement = this.connection.prepareStatement(
                        "update teacher_session set id_session= ? set id_teacher= ? ");
                this.preparedStatement.setLong(1, teacherSession.getIdSession());
                this.preparedStatement.setLong(2, teacherSession.getIdTeacher());
                this.preparedStatement.execute();

            } else {
                this.preparedStatement = this.connection.prepareStatement
                        ("insert into teacher_session (id_session,id_teacher) values (?,?)  ;");
                this.preparedStatement.setLong(1, teacherSession.getIdSession());
                this.preparedStatement.setLong(2, teacherSession.getIdTeacher());
                this.preparedStatement.execute();
            }
            System.out.println(" saved into the database");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the site");
        }


    }

    /**
     * Fonction permettant de supprimer une TeacherSession de la base de donnee
     * @param teacherSession Object TeacherSession a supprimer
     */
    @Override
    public void deleteTeacherSession(TeacherSession teacherSession) {

        try {
            connection = DriverManager.getConnection(url, username, password);

            preparedStatement = connection.prepareStatement("DELETE from teacher_session where (id_session) = ? AND id_teacher = ?  ");
            preparedStatement.setLong(1, teacherSession.getIdSession());
            preparedStatement.setLong(2, teacherSession.getIdTeacher());

            this.preparedStatement.execute();
            System.out.println("successfully deleted");

            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Fonction qui permet de trouver le nom et le prenom d'un professeur dans la base de donnee selon l'id de sa session
     * @param idSession Id de la session du professeur qu'on recherche
     * @return String
     */
    @Override
    public String getIdTeacherFromIdSession(Long idSession) {
        int id_teacher = 0;
        String last_name, first_name, fullName = null;
        try {
            connection = DriverManager.getConnection(url, username, password);

            preparedStatement = connection.prepareStatement("SELECT * FROM teacher_session WHERE id_session = ?");
            preparedStatement.setLong(1, idSession);


            this.resultSet = this.preparedStatement.executeQuery();

            resultSet.next();

            id_teacher = this.resultSet.getInt("id_teacher");


            resultSet.close();
            preparedStatement.close();

/*
//////////////////////////////////////////////////////////
 */
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE id = ?");
            preparedStatement.setLong(1, id_teacher);


            this.resultSet = this.preparedStatement.executeQuery();

            while (resultSet.next()) {
                last_name = this.resultSet.getString("last_name");
                first_name = this.resultSet.getString("first_name");
                fullName = last_name + "  " + first_name;
            }

            connection.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return fullName;
    }

    /**
     * Fonction permettant de verifier dans la base de donnee si un cours affecte a un proffesseur existe deja
     * @param startTime Heure de depart d'une session
     * @param idTeacher id du professeur
     * @param date Date de la session
     * @return boolean
     */
    @Override
    public boolean checkIfAlreadyAssociated(String startTime, Long idTeacher, String date){

        List<TeacherSession> teacherSessions = new ArrayList<>();

        try {
            this.connection = DriverManager.getConnection(url, username, password);
            this.preparedStatement = this.connection.prepareStatement
                    ("SELECT * FROM session INNER JOIN teacher_session ON session.id = teacher_session.id_session and teacher_session.id_teacher= ? and session.date =?  and session.start_time =? ");

            this.preparedStatement.setLong(1, idTeacher);
            this.preparedStatement.setString(3, startTime);
            this.preparedStatement.setString(2, date);

            this.resultSet =preparedStatement.executeQuery();

            while(resultSet.next())
            {
                teacherSessions.add(new TeacherSession());
            }

            System.out.println(teacherSessions.size());

            if(teacherSessions.size() != 0 )
            {
                JOptionPane.showMessageDialog(null, "This teacher already have a session at this hour ");
                teacherSessions.clear();
                return true;

            }

            this.preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to find the product");


        }


        return false;


    }

}
