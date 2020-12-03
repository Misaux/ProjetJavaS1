package dao;
import InterfaceDao.CourseTypeDao;
import models.CourseType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CourseTypeDAO implements CourseTypeDao {

    private String url;
    private String username;
    private String password;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    /**
     * Constructeur de la classe
     * @param url url permettant d'acceder a PhpMyAdmin
     * @param username identifiant pour acceder PhpMyAdmin
     * @param password mot de passe pour acceder a PhpMyAdmin
     */
    public CourseTypeDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;

    }

    /**
     * Fonction permettant d'obtenir une liste de tous les types de cours
     * @return List<CourseType></CourseType>
     */
    @Override
    public List<CourseType> getAllCourseType() {
        List<CourseType> list = new ArrayList<>();

        try {
            this.connection=DriverManager.getConnection(this.url,this.username,this.password);
            preparedStatement = connection.prepareStatement("SELECT * from type_course");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                String name = resultSet.getString("name");
                Long id = resultSet.getLong("id");
                list.add(new CourseType(id, CourseType.Type.valueOf(name)));
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
     * Fonction permettant de creer un type de cours dans la base de donnee
     * @param courseType Objet de classe CourseType
     */
    @Override
    public void createCoursTypeDao(CourseType courseType) {
        try {
            connection = DriverManager.getConnection(url, username, password);

            if (courseType.getID() != null) {
                System.out.println("This course already exists.");

            } else {
                preparedStatement = connection.prepareStatement
                        ("insert into type_course (name) values (?)");

                preparedStatement.setString(1, courseType.getType());

                preparedStatement.execute();
            }


            System.out.println(courseType.getType() + " saved into the database");

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the product");
        }


    }

    /**
     * Fonction qui cherche un type de cours selon son nom dans la base de donnee
     * @param name nom du type de cours que l'on recherche
     * @return CourseType
     */
    @Override
    public CourseType readCourseTypeByName(String name) {
        try {
            this.connection= DriverManager.getConnection(this.url,this.username,this.password);
            this.preparedStatement= this.connection.prepareStatement
                    ("select * from type_course where name = ?");
            this.preparedStatement.setString(1,name);
            this.resultSet = this.preparedStatement.executeQuery();

            CourseType courseType=new CourseType();

            while(this.resultSet.next()){

                courseType.setID(this.resultSet.getLong("id"));
                courseType.setType(this.resultSet.getString("name"));

            }

            this.resultSet.close();
            this.preparedStatement.close();
            this.connection.close();
            return courseType;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }

    /**
     * Fonction qui cherche un type de cours dans la base de donnee selon un id rentre en parametre
     * @param Id Id du type de cours qu'on recherche
     * @return CourseType
     */
    @Override
    public CourseType readCourseTypeById(Long Id) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
            this.preparedStatement = this.connection.prepareStatement
                    ("select * from type_course where id = ?");
            this.preparedStatement.setLong(1, Id);

            this.resultSet = this.preparedStatement.executeQuery();

            CourseType courseType = new CourseType();

            while (this.resultSet.next()) {
                courseType.setID(this.resultSet.getLong("id"));
                courseType.setType(this.resultSet.getString("name"));
            }
            this.resultSet.close();
            this.preparedStatement.close();
            this.connection.close();
            return courseType;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Fonction qui permet de mettre a jour un type de cours
     * s'il existe il est mis a jour dans la base de donnee
     * sinon il est cree dans la base de donnee
     * @param courseType type de cours a mettre a jour
     */
    @Override
    public void updateCourseTypeDao(CourseType courseType) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);

            if (courseType.getID() != null) {
                this.preparedStatement = this.connection.prepareStatement(
                        "update type_course set name= ? where id = ? ");
                this.preparedStatement.setString(1, courseType.getType());
                this.preparedStatement.setLong(2, courseType.getID());
                this.preparedStatement.execute();

            } else {
                this.preparedStatement = this.connection.prepareStatement
                        ("insert into type_course (name) values (?)  ;");

                this.preparedStatement.setString(1, courseType.getType());

                this.preparedStatement.execute();
            }

            System.out.println(courseType.getType() + " saved into the database");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the product");
        }

    }

    /**
     * Fonction qui permet de supprimer un type de cours de la base de donnee
     * selon le nom entre en parametre de la fonction
     * @param courseType type de cours que l'on veut supprimer
     */
    @Override
    public void deleteCourseTypeDao(CourseType courseType) {
        try {
            connection = DriverManager.getConnection(url, username, password);

            preparedStatement = connection.prepareStatement("DELETE from type_course where (name) = ? ");
            preparedStatement.setString(1, courseType.getType());

            this.preparedStatement.execute();
            System.out.println("La matière a bien été supprimée.");

            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
