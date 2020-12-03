package dao;

import models.Student;
import models.User;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements InterfaceDao.StudentDAO {

    private String url;
    private String username;
    private String password;

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    /**
     * Constructeur de la classe StudentDAO
     * @param url url permettant d'acceder a PhpMyAdmin
     * @param username identifiant pour acceder PhpMyAdmin
     * @param password mot de passe pour acceder a PhpMyAdmin
     */
    public StudentDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public StudentDAO() {
    }

    /**
     * Fonction permettant d'obtenir une liste de tous les etudiants presents dans la base de donnee
     * @return List<Student></Student>
     */
    @Override
    public List<Student> getAllStudent() {

        List<Student> list = new ArrayList<>();
        try {

            this.connection = DriverManager.getConnection(url, username, password);
            this.preparedStatement = this.connection.prepareStatement("SELECT * FROM user INNER JOIN student ON user.id = student.id_user");

            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {
                String emailUser = this.resultSet.getString("email");
                String passwordUser = this.resultSet.getString("password");
                String lastNameUser = this.resultSet.getString("last_name");
                String firstNameUser = this.resultSet.getString("first_name");
                String permissionUser = this.resultSet.getString("permission");
                Long idUser = this.resultSet.getLong("id");
                Long idGroupPromotion = this.resultSet.getLong("id_group_promotion");
                int numberStudent = this.resultSet.getInt("number");
                list.add(new Student(idUser, emailUser, passwordUser, firstNameUser, lastNameUser, User.Permission.valueOf(permissionUser), numberStudent, idGroupPromotion));
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
     * Fonction permettant d'ajouter un Student a la base de donnee
     * @param student Object Student qu'on veut ajouter
     */
    @Override
    public void createStudent(Student student) {

        try {
            this.connection = DriverManager.getConnection(url, username, password);

            if (student.getID() != null) {
                System.out.println("This course already exists.");

            } else {
                this.preparedStatement = this.connection.prepareStatement
                        ("insert into student (id_user, number, id_group_promotion) values (?,?,?)");
                this.preparedStatement.setLong(1, student.getIdUser());
                this.preparedStatement.setInt(2,student.getNumber());
                this.preparedStatement.setLong(3, student.getIdGroupPromotion());
                this.preparedStatement.execute();
            }
            System.out.println(" student saved into the database");
            JOptionPane.showMessageDialog(null, "Student Successfully added in the teacher database");
            this.preparedStatement.close();
            this.connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the user");
        }

    }

    /**
     * Fonction permettant de mettre a jour l'id_user d'un Student
     * @param student Object Student dans lequel on veut mettre a jour l'id_user
     */
    @Override
    public void updateStudentIdUser(Student student) {

        try {
            this.connection = DriverManager.getConnection(url, username, password);
            this.preparedStatement = this.connection.prepareStatement(
                    "update student set id_user= ? where number= ? ");
            this.preparedStatement.setLong(1,student.getIdUser());
            this.preparedStatement.setInt(2,student.getNumber());
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
     * Fonction permettant de mettre a jour l'id_group_promotion d'un Student
     * @param student Object Student dans lequel on veut mettre a jour l'id_group_promotion
     */
    @Override
    public void updateStudentIdGroupPromotion(Student student) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
            this.preparedStatement = this.connection.prepareStatement(
                    "update student set id_group_promotion= ? where number= ? ");
            this.preparedStatement.setLong(1,student.getIdGroupPromotion());
            this.preparedStatement.setInt(2,student.getNumber());
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
     * Fonction permettant de rechercher dans la base de donnee un Student selon son nom de famille
     * @param lastName nom de famille du Student qu'on recherche
     * @return Student
     */
    @Override
    public Student findStudentByLastName(String lastName) {
        try {
            this.connection= DriverManager.getConnection(this.url,this.username,this.password);
            this.preparedStatement= this.connection.prepareStatement
                    ("SELECT * FROM user e NATURAL JOIN student s WHERE s.id_user=e.id and e.last_name = ? ");
            this.preparedStatement.setString(1,lastName);
            this.resultSet = this.preparedStatement.executeQuery();

            Student student = new Student();

            while(this.resultSet.next())
            {
                student.setID(this.resultSet.getLong("id"));
                student.setEmail(this.resultSet.getString("email"));
                student.setPassword(this.resultSet.getString("password"));
                student.setFirst_name(this.resultSet.getString("first_name"));
                student.setLast_name(this.resultSet.getString("last_name"));
                student.setPermission(this.resultSet.getString("permission"));
                student.setIdUser(this.resultSet.getLong("id_user"));
                student.setNumber(this.resultSet.getInt("number"));
                student.setIdGroupPromotion(this.resultSet.getLong("id_group_promotion"));
            }
            this.resultSet.close();
            this.preparedStatement.close();
            this.connection.close();
            return student;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Fonction permettant de rechercher dans la base de donnee un Student selon son id
     * @param id Id du Student qu'on recherche
     * @return Student
     */
    @Override
    public User findStudentByID(Long id) {
        try {
            this.connection= DriverManager.getConnection(this.url,this.username,this.password);
            this.preparedStatement= this.connection.prepareStatement
                    ("SELECT * FROM user e NATURAL JOIN student s WHERE e.id = ? ");
            this.preparedStatement.setLong(1,id);
            this.resultSet = this.preparedStatement.executeQuery();

            Student student= new Student();

            while(this.resultSet.next())
            {
                student.setID(this.resultSet.getLong("id"));
                student.setEmail(this.resultSet.getString("email"));
                student.setPassword(this.resultSet.getString("password"));
                student.setFirst_name(this.resultSet.getString("first_name"));
                student.setLast_name(this.resultSet.getString("last_name"));
                student.setPermission(this.resultSet.getString("permission"));
                student.setIdUser(this.resultSet.getLong("id_user"));
                student.setNumber((this.resultSet.getInt("number")));
                student.setIdGroupPromotion(this.resultSet.getLong("id_group_promotion"));
            }
            this.resultSet.close();
            this.preparedStatement.close();
            this.connection.close();
            return student;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Fonction permettant de supprimer un Student de la base de donnee
     * @param student Object Student qu'on veut supprimer
     */
    @Override
    public void deleteStudent(Student student) {
        try{
            connection = DriverManager.getConnection(url, username, password);

            preparedStatement = connection.prepareStatement("DELETE from student where (id_user) = ? ");
            preparedStatement.setLong(1, student.getIdUser());

            this.preparedStatement.execute();
            System.out.println("The student has been successfully deleted.");
            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param connexion
     * @param passwordEmail
     * @return
     */
    @Override
    public String getStudentName(String connexion, String passwordEmail) {
        return null;
    }

    /**
     *
     * @param connexion
     * @param passwordEmail
     * @return
     */
    @Override
    public String getStudentFirstName(String connexion, String passwordEmail) {
        return null;
    }

    /**
     * Fonction permettant de verifier si un Student existe deja dans la base de donnee
     * @param lastName Nom de famille du Student
     * @param firstName Prenom du Student
     * @param number Numero du Student
     * @return true si le student existe / false sinon
     */
    @Override
    public boolean checkIfAlreadyCreated(String lastName, String firstName,  int number){

        List<Student> student = new ArrayList<>();

        try {
            this.connection = DriverManager.getConnection(url, username, password);
            this.preparedStatement = this.connection.prepareStatement
                    ("SELECT * FROM user e NATURAL JOIN student s WHERE e.last_name = ? and e.first_name =? and s.number= ?  ");

            this.preparedStatement.setString(1, lastName);
            this.preparedStatement.setString(2, firstName);
            this.preparedStatement.setInt(3,number);

            this.resultSet =preparedStatement.executeQuery();

            while(resultSet.next())
            {
                student.add(new Student());
            }

            System.out.println(student.size());

            if(student.size() != 0 )
            {
                JOptionPane.showMessageDialog(null, "This student already exist ");
                student.clear();
                return true;

            }

            this.preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to find the product");


        }

        JOptionPane.showMessageDialog(null, "This student has been added ");
        return false;


    }
}
