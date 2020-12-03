package dao;

import InterfaceDao.CoursDao;
import models.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CourseDAO implements CoursDao {

    private String url;
    private String username;
    private String password;

    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    /**
     * Constructeur de la classe
     * @param url url permettant d'acceder a PhpMyAdmin
     * @param username identifiant pour acceder PhpMyAdmin
     * @param password mot de passe pour acceder a PhpMyAdmin
     */
    public CourseDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;

    }

    /**
     * fonction permettant d'obtenir une liste de tous les cours de la base de donnee
     *
     * @return List<Course></Course>
     */
    @Override
    public List<Course> getAllCourse() {
        List<Course> list = new ArrayList<>();

        try {
            this.connection = DriverManager.getConnection(this.url, this.username, this.password);
            preparedStatement = connection.prepareStatement("SELECT * from course");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Long id = resultSet.getLong("id");
                list.add(new Course(id, name));
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
     * fonction permettant d'obtenir un cours venant de la base de donnee grace a son nom
     *
     * @param name, nom du cours qu'on cherche
     * @return Course
     */
    @Override
    public Course readCourseByName(String name) {
        try {
            this.connection = DriverManager.getConnection(this.url, this.username, this.password);
            this.preparedStatement = this.connection.prepareStatement
                    ("select * from course where name = ?");
            this.preparedStatement.setString(1, name);
            this.resultSet = this.preparedStatement.executeQuery();

            Course course = new Course();

            while (this.resultSet.next()) {

                course.setID(this.resultSet.getLong("id"));
                course.setName(this.resultSet.getString("name"));

            }

            this.resultSet.close();
            this.preparedStatement.close();
            this.connection.close();
            return course;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * fonction permettant de mettre a jour un cours dans la base de donnee s'il existe et sinon d'inserer ce cours dans la base de donnee
     *
     * @param course, cours envoye pour etre mis a jour ou bien insere dans la bdd
     */
    @Override
    public void saveCourse(Course course) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
            if (course.getID() != null) {
                this.preparedStatement = this.connection.prepareStatement
                        ("update course set name = ? where id = ?;");
                this.preparedStatement.setString(1, course.getName());
                this.preparedStatement.setLong(2, course.getID());
                this.preparedStatement.execute();
            } else {
                this.preparedStatement = this.connection.prepareStatement
                        ("insert into course (name) values (?);");
                this.preparedStatement.setString(1, course.getName());
                this.preparedStatement.execute();
            }

            System.out.println(course.getName() + "saved into the database");
            this.preparedStatement.close();
            this.connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the product");
        }

    }

    /**
     * fonction permettant de chercher un cours dans la base de donnée en fonction de son son id
     *
     * @param id, id du cours que l'on recherche
     * @return Course
     */
    @Override
    public Course getCourseById(Long id) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
            this.preparedStatement = this.connection.prepareStatement
                    ("select * from course where id = ?");
            this.preparedStatement.setLong(1, id);

            ResultSet resultSet = this.preparedStatement.executeQuery();

            Course course = new Course();

            while (resultSet.next()) {
                course.setID(resultSet.getLong("id"));
                course.setName(resultSet.getString("name"));
            }
            this.preparedStatement.close();
            this.connection.close();
            return course;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
