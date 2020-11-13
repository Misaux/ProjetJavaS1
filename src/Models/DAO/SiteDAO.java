package Models.DAO;

import Models.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SiteDAO {

    private String url;
    private String username;
    private String password;


    public SiteDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void saveSite(Site site){

        try {
            Connection connection = DriverManager.getConnection(url,username,password);

            if( site.getID ()!= null)
            {
                PreparedStatement statement = connection.prepareStatement(
                        "update site set name= ? where id = ? ");
                statement.setString(1,site.getName());
                statement.setLong(2,site.getID());
                statement.execute();

            } else {
                PreparedStatement statement = connection.prepareStatement
                        ("insert into site (name) values (?)  ;");

                statement.setString(1,site.getName());

                statement.execute();
            }


            System.out.println(site.getName() + " saved into the database");






        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the product");
        }
    }
}
