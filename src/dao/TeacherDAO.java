package dao;

import InterfaceDao.TeacherDao;
import models.*;

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


    /**
     * Constructeur de la classe TeacherDAO
     * @param url url permettant d'acceder a PhpMyAdmin
     * @param username identifiant pour acceder PhpMyAdmin
     * @param password mot de passe pour acceder a PhpMyAdmin
     */
    public TeacherDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * constructeur par defaut
     */
    public TeacherDAO() {
    }

    /**
     * Fonction permettant d'obtenir tous les professeurs presents dans la base de donnee
     * @return List<Teacher></Teacher>
     */
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

    /**
     * Fonction permettant d'ajouter un Teacher dans la base de donnee
     * @param teacher Object Teacher qu'on veut ajouter
     */
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

    /**
     * Fonction qui permet de mettre a jour dans la base de donnee la valeur de l'id_user d'un Teacher
     * @param teacher Object Teacher dans lequel on va aller chercher l'id_user a mettre a jour
     */
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

    /**
     * Fonction qui permet de mettre a jour dans la base de donnee la valeur de l'id_course d'un Teacher
     * @param teacher Object Teacher dans lequel on va aller chercher l'id_course a mettre a jour
     */
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

    /**
     * Fonction permettant de rechercher un Teacher selon son nom de famille dans la base de donnee
     * @param name Nom de famille du Teacher qu'on cherche
     * @return Teacher
     */
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

    /**
     * Fonction permettant de rechercher un Teacher selon son id dans la base de donnee
     * @param id Id du Teacher qu'on cherche
     * @return User
     */
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

    /**
     * Fonction permettant de supprimer un Teacher de la base de donnee
     * @param teacher Object Teacher qu'on veut supprimer
     */
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

    /**
     * Fonction permettant de savoir si un professeur existe deja dans la base de donnee
     * @param lastName nom de famille du Teacher
     * @param firstName prenom du teacher
     * @return boolean
     */
    @Override
    public boolean checkIfAlreadyCreated(String lastName, String firstName){

        List<Teacher> teacher = new ArrayList<>();

        try {
            this.connection = DriverManager.getConnection(url, username, password);
            this.preparedStatement = this.connection.prepareStatement
                    ("SELECT * FROM user e NATURAL JOIN teacher t WHERE e.last_name = ? and e.first_name =? ");

            this.preparedStatement.setString(1, lastName);
            this.preparedStatement.setString(2, firstName);

            this.resultSet =preparedStatement.executeQuery();

            while(resultSet.next())
            {
                teacher.add(new Teacher());
            }

            System.out.println(teacher.size());

            if(teacher.size() != 0 )
            {
                JOptionPane.showMessageDialog(null, "This teacher already exist ");
                teacher.clear();
                return true;

            }

            this.preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to find the product");


        }

        JOptionPane.showMessageDialog(null, "This teacher has been added ");
        return false;


    }

}
