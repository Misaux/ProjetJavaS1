package DAO;

import Models.Promotion;
import Models.Site;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PromotionDAO {

    private String url;
    private String username;
    private String password;

    public PromotionDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }


    public void savePromotion(Promotion promotion){

        try {
            Connection connection = DriverManager.getConnection(url,username,password);

            if( promotion.getID ()!= null)
            {
                PreparedStatement statement = connection.prepareStatement(
                        "update promotion set name= ? where id = ? ");
                statement.setString(1,promotion.getName());
                statement.setLong(2,promotion.getID());
                statement.execute();

            } else {
                PreparedStatement statement = connection.prepareStatement
                        ("insert into promotion (name) values (?)  ;");

                statement.setString(1,promotion.getName());

                statement.execute();
            }


            System.out.println(promotion.getName() + " saved into the database");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the product");
        }
    }



}
