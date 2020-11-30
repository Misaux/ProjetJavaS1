package DAO;

import InterfaceDao.TeacherDao;
import Models.Session;
import Models.Student;
import Models.Teacher;
import Models.User;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO implements TeacherDao {

    private  String url;
    private  String username;
    private  String password;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    public TeacherDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public TeacherDAO() {
    }


    @Override
    public List<Teacher> getAllTeacher() {

        List<Teacher> list = new ArrayList<>();
        try{

            this.connection = DriverManager.getConnection(url, username,password);
            this.preparedStatement = this.connection.prepareStatement("SELECT * FROM user INNER JOIN teacher ON user.id = teacher.id_user");

            this.resultSet = this.preparedStatement.executeQuery();

            while(this.resultSet.next()){
                String emailUser = this.resultSet.getString("email");
                String passwordUser = this.resultSet.getString("password");
                String lastNameUser = this.resultSet.getString("last_name");
                String firstNameUser = this.resultSet.getString("first_name");
                String permissionUser = this.resultSet.getString("permission");
                Long idUser = this.resultSet.getLong("id");
                Long idCourse = this.resultSet.getLong("id_course");
                list.add(new Teacher(idUser,emailUser,passwordUser,firstNameUser,lastNameUser,User.Permission.valueOf(permissionUser),idCourse));
            }

            this.resultSet.close();
            this.preparedStatement.close();
            this.connection.close();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }



    }



    @Override
    public void createTeacher(Teacher teacher) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);

                this.preparedStatement = this.connection.prepareStatement
                        ("insert into teacher (id_user, id_course) values (?,?)");
                this.preparedStatement.setLong(1, teacher.getIdUser());
                this.preparedStatement.setLong(2, teacher.getIdCourse());
                this.preparedStatement.execute();

            System.out.println(" user saved into the database");
            JOptionPane.showMessageDialog(null, "Teacher Successfully added in the teacher database");
            this.preparedStatement.close();
            this.connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the user");
        }

    }

    @Override
    public void updateTeacherIdUser(Teacher teacher) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);


            this.preparedStatement = this.connection.prepareStatement(
                    "update teacher set id_user= ? where id_course= ? ");
            this.preparedStatement.setLong(2,teacher.getIdUser());
            this.preparedStatement.setLong(1,teacher.getIdCourse());
            this.preparedStatement.execute();

            System.out.println(" saved into the database");
            this.preparedStatement.close();
            this.connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the site");
        }
    }

    @Override
    public void updateTeacherIdCourse(Teacher teacher){

        try {
            this.connection = DriverManager.getConnection(url, username, password);
            this.preparedStatement = this.connection.prepareStatement(
                    "update teacher set id_course= ? where id_user= ? ");
            this.preparedStatement.setLong(1,teacher.getIdCourse());
            this.preparedStatement.setLong(2,teacher.getIdUser());
            this.preparedStatement.execute();
            System.out.println(" saved into the database");
            this.preparedStatement.close();
            this.connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the site");
        }


    }

    @Override
    public Teacher findTeacherByName(String name) {

        try {
            this.connection= DriverManager.getConnection(this.url,this.username,this.password);
            this.preparedStatement= this.connection.prepareStatement
                    ("SELECT * FROM user e NATURAL JOIN teacher t WHERE e.last_name = ? ");
            this.preparedStatement.setString(1,name);
            this.resultSet = this.preparedStatement.executeQuery();

            Teacher teacher = new Teacher();

            while(this.resultSet.next())
            {
                teacher.setID(this.resultSet.getLong("id"));
                teacher.setEmail(this.resultSet.getString("email"));
                teacher.setPassword(this.resultSet.getString("password"));
                teacher.setFirst_name(this.resultSet.getString("first_name"));
                teacher.setLast_name(this.resultSet.getString("last_name"));
                teacher.setPermission(this.resultSet.getString("permission"));
                teacher.setIdUser(this.resultSet.getLong("id_user"));
                teacher.setIdCourse((this.resultSet.getLong("id_course")));;
            }
            this.resultSet.close();
            this.preparedStatement.close();
            this.connection.close();
            return teacher;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }




    @Override
    public User findTeacherByID(Long id) {
        try {
            this.connection= DriverManager.getConnection(this.url,this.username,this.password);
            this.preparedStatement= this.connection.prepareStatement
                    ("SELECT * FROM user e NATURAL JOIN teacher t WHERE e.id = ? ");
            this.preparedStatement.setLong(1,id);
            this.resultSet = this.preparedStatement.executeQuery();

            Teacher teacher = new Teacher();

            while(this.resultSet.next())
            {
                teacher.setID(this.resultSet.getLong("id"));
                teacher.setEmail(this.resultSet.getString("email"));
                teacher.setPassword(this.resultSet.getString("password"));
                teacher.setFirst_name(this.resultSet.getString("first_name"));
                teacher.setLast_name(this.resultSet.getString("last_name"));
                teacher.setPermission(this.resultSet.getString("permission"));
                teacher.setIdUser(this.resultSet.getLong("id_user"));
                teacher.setIdCourse((this.resultSet.getLong("id_course")));;
            }
            this.resultSet.close();
            this.preparedStatement.close();
            this.connection.close();
            return teacher;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        try{
            this.connection = DriverManager.getConnection(url, username, password);

            this.preparedStatement = this.connection.prepareStatement("DELETE from teacher where (id_user) = ? ");
            this.preparedStatement.setLong(1, teacher.getIdUser());

            this.preparedStatement.execute();
            System.out.println("The teacher has been successfully deleted.");

            this.preparedStatement.close();
            this.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getTeacherName(String connexion, String passwordEmail) {
        return null;
    }

    @Override
    public String getTeacherFirstName(String connexion, String passwordEmail) {
        return null;
    }
}
