package DAO;

import Models.Room;
import Models.Student;
import Models.Teacher;
import Models.User;

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


    public StudentDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public StudentDAO() {
    }

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

    @Override
    public void createStudent(Student student) {

        try {
            this.connection = DriverManager.getConnection(url, username, password);

            if (student.getID() != null) {
                System.out.println("This course already exists.");

            } else {
                this.preparedStatement = this.connection.prepareStatement
                        ("insert into student (id_user, number, id_course) values (?,?,?)");
                this.preparedStatement.setLong(1, student.getIdUser());
                this.preparedStatement.setInt(2,student.getNumber());
                this.preparedStatement.setLong(3, student.getIdGroupPromotion());
                this.preparedStatement.execute();
            }
            System.out.println(" student saved into the database");
            this.preparedStatement.close();
            this.connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the user");
        }

    }

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

    @Override
    public User findStudentByLastName(String name) {
        try {
            this.connection= DriverManager.getConnection(this.url,this.username,this.password);
            this.preparedStatement= this.connection.prepareStatement
                    ("SELECT * FROM user e NATURAL JOIN student s WHERE e.last_name = ? ");
            this.preparedStatement.setString(1,name);
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

    @Override
    public String getStudentName(String connexion, String passwordEmail) {
        return null;
    }

    @Override
    public String getStudentFirstName(String connexion, String passwordEmail) {
        return null;
    }
}
