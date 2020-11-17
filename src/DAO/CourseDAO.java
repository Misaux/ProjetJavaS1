package DAO;
import Models.Course;

import java.sql.*;


public class CourseDAO {

    private String url;
    private String username;
    private String password;
    private Connection connection;
    private PreparedStatement preparedStatement;

    public CourseDAO(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;

    }

    public void saveCourse(Course course){
        try{
            this.connection= DriverManager.getConnection(url,username,password);
            if(course.getID()!=null){
                this.preparedStatement = this.connection.prepareStatement
                        ("update course set name = ? where id = ?;");
                this.preparedStatement.setString(1, course.getName());
                this.preparedStatement.setLong(2, course.getID());
                this.preparedStatement.execute();
            }else{
               this.preparedStatement = this.connection.prepareStatement
                        ("insert into course (name) values (?);");
               this.preparedStatement.setString(1, course.getName());
               this.preparedStatement.execute();
            }

            System.out.println(course.getName() + "saved into the database");
            this.preparedStatement.close();
            this.connection.close();

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("unable to save the product");
        }

    }

    public Course getCourseById(Long id){
        try{
            this.connection= DriverManager.getConnection(url,username,password);
            this.preparedStatement = this.connection.prepareStatement
                    ("select * from course where id = ?");
            this.preparedStatement.setLong(1, id);

            ResultSet resultSet = this.preparedStatement.executeQuery();

            Course course = new Course();

            while(resultSet.next()){
                course.setID(resultSet.getLong("id"));
                course.setName(resultSet.getString("name"));
            }
            this.preparedStatement.close();
            this.connection.close();
            return course;

        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
