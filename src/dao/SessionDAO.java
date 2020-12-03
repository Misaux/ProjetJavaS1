package dao;

import models.*;
import org.jfree.data.jdbc.JDBCPieDataset;

import javax.swing.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionDAO implements InterfaceDao.SessionDAO {
    private String url;
    private String username;
    private String password;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    public SessionDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public SessionDAO() {
    }

    @Override
    public List<Session> getAllSession() {

        List<Session> list = new ArrayList<>();

        try {
            this.connection = DriverManager.getConnection(this.url, this.username, this.password);
            preparedStatement = connection.prepareStatement("SELECT * from session");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long Id = resultSet.getLong("id");
                int week = resultSet.getInt("week");
                String date = resultSet.getString("date");
                String startTime = resultSet.getString("start_time");
                String endTime = resultSet.getString("end_time");
                String state = resultSet.getString("state");
                Long idCourse = resultSet.getLong("id_course");
                Long idType = resultSet.getLong("id_type");
                Long id = resultSet.getLong("id");
                list.add(new Session(Id, week, startTime, endTime, date, idCourse, idType, Session.State.valueOf(state)));
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
    public List<Session> getWeekSessionStudent(User user, String weekSelected) {

        List<Session> list = new ArrayList<>();

        try {
            this.connection = DriverManager.getConnection(this.url, this.username, this.password);
            preparedStatement = connection.prepareStatement("SELECT id_group_promotion FROM student WHERE id_user = ?");
            preparedStatement.setLong(1, user.getID());

            resultSet = preparedStatement.executeQuery();

            resultSet.next();

            int gp = resultSet.getInt("id_group_promotion");

            resultSet.close();
            preparedStatement.close();
            /*--------------------------------*/

            preparedStatement = connection.prepareStatement("SELECT id_session FROM group_session WHERE id_group = ?");
            preparedStatement.setInt(1, gp);

            resultSet = preparedStatement.executeQuery();

            List<Long> id_session = new ArrayList<>();
            while (resultSet.next()) {
                id_session.add(resultSet.getLong("id_session"));
            }


            resultSet.close();
            preparedStatement.close();
            /*------------------------*/
            for (int i = 0; i < id_session.size(); i++) {
                preparedStatement = connection.prepareStatement("SELECT * FROM session WHERE id = ? AND week = ?");
                preparedStatement.setLong(1, id_session.get(i));
                preparedStatement.setString(2, weekSelected);

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Long Id = resultSet.getLong("id");
                    int week = resultSet.getInt("week");
                    String date = resultSet.getString("date");
                    String startTime = resultSet.getString("start_time");
                    String endTime = resultSet.getString("end_time");
                    String state = resultSet.getString("state");
                    Long idCourse = resultSet.getLong("id_course");
                    Long idType = resultSet.getLong("id_type");
                    list.add(new Session(Id, week, startTime, endTime, date, idCourse, idType, Session.State.valueOf(state)));
                }

                resultSet.close();
                preparedStatement.close();

            }
            connection.close();

            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Session> getWeekSessionTeacher(User user, String weekSelected) {

        /*pour la version teacher
        1- SELECT id_course FROM teacher WHERE id_user = (ici user connecté)

        2 - SELECT * FROM session WHERE id_course = (ici obtenu avec la requette precedente))
         */


        List<Session> list = new ArrayList<>();

        try {
            this.connection = DriverManager.getConnection(this.url, this.username, this.password);
            preparedStatement = connection.prepareStatement("SELECT id_course FROM teacher WHERE id_user = ?");
            preparedStatement.setLong(1, user.getID());

            resultSet = preparedStatement.executeQuery();
            int gp = 0;
            int idCourse = 0;
            while (resultSet.next()) {

                idCourse = resultSet.getInt("id_course");
            }
            resultSet.close();
            preparedStatement.close();
            /*--------------------------------*/

            preparedStatement = connection.prepareStatement("SELECT * FROM session WHERE id_course = ? and week = ? ");
            preparedStatement.setInt(1, idCourse);
            preparedStatement.setString(2, weekSelected);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long Id = resultSet.getLong("id");
                int week = resultSet.getInt("week");
                String date = resultSet.getString("date");
                String startTime = resultSet.getString("start_time");
                String endTime = resultSet.getString("end_time");
                String state = resultSet.getString("state");
                Long idCourse1 = resultSet.getLong("id_course");
                Long idType = resultSet.getLong("id_type");
                list.add(new Session(Id, week, startTime, endTime, date, idCourse1, idType, Session.State.valueOf(state)));
            }


            resultSet.close();
            preparedStatement.close();
            /*------------------------*/
            connection.close();

            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Session> getWeekSessionRoom(Room room, String weekSelected) {

        List<Session> list = new ArrayList<>();

        try {
            this.connection = DriverManager.getConnection(this.url, this.username, this.password);
            preparedStatement = connection.prepareStatement("SELECT id_session FROM room_session WHERE id_room = ?");
            preparedStatement.setLong(1, room.getID());

            resultSet = preparedStatement.executeQuery();
            List<Long> id_session = new ArrayList<>();

            while (resultSet.next()) {
                id_session.add(resultSet.getLong("id_session"));
            }

            resultSet.close();
            preparedStatement.close();
            /*------------------------*/
            for (int i = 0; i < id_session.size(); i++) {
                preparedStatement = connection.prepareStatement("SELECT * FROM session WHERE id = ? AND week = ?");
                preparedStatement.setLong(1, id_session.get(i));
                preparedStatement.setString(2, weekSelected);

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Long Id = resultSet.getLong("id");
                    int week = resultSet.getInt("week");
                    String date = resultSet.getString("date");
                    String startTime = resultSet.getString("start_time");
                    String endTime = resultSet.getString("end_time");
                    String state = resultSet.getString("state");
                    Long idCourse = resultSet.getLong("id_course");
                    Long idType = resultSet.getLong("id_type");
                    list.add(new Session(Id, week, startTime, endTime, date, idCourse, idType, Session.State.valueOf(state)));
                }

                resultSet.close();
                preparedStatement.close();

            }
            connection.close();

            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<String> getAllSessionState() {

        List<String> list = new ArrayList<>();

        try {
            this.connection = DriverManager.getConnection(this.url, this.username, this.password);
            preparedStatement = connection.prepareStatement("SELECT DISTINCT state from session ");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String state = resultSet.getString("state");
                list.add(state);
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
    public List<String> getAllSessionStartTime() {

        List<String> list = new ArrayList<>();

        try {
            this.connection = DriverManager.getConnection(this.url, this.username, this.password);
            preparedStatement = connection.prepareStatement("SELECT DISTINCT start_time from session order by start_time asc ");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String start_time = resultSet.getString("start_time");
                list.add(start_time);
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
    public List<String> getAllSessionDate() {

        List<String> list = new ArrayList<>();

        try {
            this.connection = DriverManager.getConnection(this.url, this.username, this.password);
            preparedStatement = connection.prepareStatement("SELECT DISTINCT date from session order by start_time asc ");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String date = resultSet.getString("date");
                list.add(date);
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
    public List<Session> getInfomatiqueSession() {

        List<Session> sessionInformatique = new ArrayList<>();

        for (Session s : getAllSession()) {
            if (s.getID_course() == 1) {

                sessionInformatique.add(s);

            }
        }

        return sessionInformatique;
    }

    @Override
    public List<Session> getMathematiqueSession() {

        List<Session> sessionMaths = new ArrayList<>();

        for (Session s : getAllSession()) {
            if (s.getID_course() == 2) {

                sessionMaths.add(s);

            }
        }

        return sessionMaths;
    }

    @Override
    public List<Session> getAnglaisSession() {

        List<Session> sessionAnglais = new ArrayList<>();

        for (Session s : getAllSession()) {
            if (s.getID_course() == 3) {

                sessionAnglais.add(s);

            }
        }

        return sessionAnglais;
    }

    @Override
    public void createSession(Session session) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);

                this.preparedStatement = this.connection.prepareStatement
                        ("insert into session (week,date,start_time,end_time,state,id_course,id_type) values (?,?,?,?,?,?,?)");
                this.preparedStatement.setInt(1, session.getWeek());
                this.preparedStatement.setString(2, session.getDate());
                this.preparedStatement.setString(3, session.getStartTime());
                this.preparedStatement.setString(4, session.getEndTime());
                this.preparedStatement.setString(5, session.getState());
                this.preparedStatement.setLong(6, session.getID_course());
                this.preparedStatement.setLong(7, session.getID_type());
                this.preparedStatement.execute();

                System.out.println(" Session saved into the database");
                JOptionPane.showMessageDialog(null, "Session Successfully added in the user database");


            this.preparedStatement.close();
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the product");
        }

    }

    @Override
    public Session readSession(int week, String date, String startTime, Long idcourse) {
        try {
            this.connection = DriverManager.getConnection(this.url, this.username, this.password);
            this.preparedStatement = this.connection.prepareStatement
                    ("select * from session where week = ? AND date = ? AND start_time = ? and id_course = ? ");
            this.preparedStatement.setInt(1, week);
            this.preparedStatement.setString(2, date);
            this.preparedStatement.setString(3, startTime);
            this.preparedStatement.setLong(4, idcourse);
            this.resultSet = this.preparedStatement.executeQuery();

            Session session = new Session();

            while (this.resultSet.next()) {

                session.setID(this.resultSet.getLong("id"));
                session.setDate(this.resultSet.getString("date"));
                session.setStartTime(this.resultSet.getString("start_time"));
                session.setEndTime(this.resultSet.getString("end_time"));
                session.setState(this.resultSet.getString("state"));
                session.setID_course(this.resultSet.getLong("id_course"));
                session.setID_type(this.resultSet.getLong("id_type"));
                session.setWeek();

            }

            this.resultSet.close();
            this.preparedStatement.close();
            this.connection.close();
            return session;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public void updateSession(Session session) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);

            if (session.getID() != null) {
                this.preparedStatement = this.connection.prepareStatement(
                        "update session set week= ? set date = ? set start_time = ? set end_time= ? set state = ? set id_course = ? set id_type = ? where id = ? ");
                this.preparedStatement.setInt(1, session.getWeek());
                this.preparedStatement.setString(2, session.getDate());
                this.preparedStatement.setString(3, session.getStartTime());
                this.preparedStatement.setString(4, session.getEndTime());
                this.preparedStatement.setString(5, session.getState());
                this.preparedStatement.setLong(6, session.getID_course());
                this.preparedStatement.setLong(7, session.getID_type());
                this.preparedStatement.setLong(8, session.getID());
                this.preparedStatement.execute();

            } else {
                this.preparedStatement = this.connection.prepareStatement
                        ("insert into session (week,date,start_time,end_time,state,id_course,id_type) values (?,?,?,?,?,?,?)");

                this.preparedStatement.setInt(1, session.getWeek());
                this.preparedStatement.setString(2, session.getDate());
                this.preparedStatement.setString(3, session.getStartTime());
                this.preparedStatement.setString(4, session.getEndTime());
                this.preparedStatement.setString(5, session.getState());
                this.preparedStatement.setLong(6, session.getID_course());
                this.preparedStatement.setLong(7, session.getID_type());
                this.preparedStatement.execute();
            }

            System.out.println(session.getState() + " saved into the database");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the product");
        }

    }

    @Override
    public void deleteSession(Session session) {
        try {
            connection = DriverManager.getConnection(url, username, password);

            preparedStatement = connection.prepareStatement("DELETE from session where date= ? and start_time = ? and id_course = ? ");
            preparedStatement.setString(1, session.getDate());
            preparedStatement.setString(2, session.getStartTime());
            preparedStatement.setLong(3, session.getID_course());

            this.preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "This session is successfully deleted ");

            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            System.err.print("SQLException: ");
            System.err.println(e.getMessage());
        }

    }

    @Override
    public JDBCPieDataset readData() {
        JDBCPieDataset data = null;

        try {
            this.connection = DriverManager.getConnection(this.url, this.username, this.password);
            data = new JDBCPieDataset(this.connection);
            String sql = "SELECT name, Count(id_course) FROM course c INNER JOIN session s WHERE c.id = s.id_course GROUP BY id_course";
            data.executeQuery(sql);
            this.connection.close();
        } catch (SQLException e) {
            System.err.print("SQLException: ");
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.print("Exception: ");
            System.err.println(e.getMessage());
        }

        return data;
    }
}
