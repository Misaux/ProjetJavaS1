package DAO;
import Models.Course;

import java.sql.*;


public class CourseDAO {

    private String url;
    private String username;
    private String password;

    public CourseDAO(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void saveCourse(Course course){
        try{
            Connection connection = DriverManager.getConnection(url,username,password);

            if(course.getID()!=null){
                PreparedStatement statement = connection.prepareStatement
                        ("update course set name = ? where id = ?;");
                statement.setString(1, course.getName());
                statement.setLong(2, course.getID());
                statement.execute();
            }else{
                PreparedStatement statement = connection.prepareStatement
                        ("insert into course (name) values (?);");
                statement.setString(1, course.getName());
                statement.execute();
            }

            System.out.println(course.getName() + "saved into the database");

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("unable to save the product");
        }
    }

    public Course getCourseById(Long id){
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement statement = connection.prepareStatement
                    ("select * from course where id = ?");
            statement.setLong(1, id);

            ResultSet resultset = statement.executeQuery();

            Course course = new Course();

            while(resultset.next()){
                course.setID(resultset.getLong("id"));
                course.setName(resultset.getString("name"));
            }
            return course;
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
