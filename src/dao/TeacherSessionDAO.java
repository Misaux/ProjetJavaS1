package dao;

import InterfaceDao.TeacherSessionDao;
import models.TeacherSession;


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


    public TeacherSessionDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public TeacherSessionDAO() {
    }

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


}
