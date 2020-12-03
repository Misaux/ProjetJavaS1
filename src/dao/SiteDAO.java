package dao;

import InterfaceDao.SiteDao;
import models.*;
//import com.mysql.cj.protocol.a.BinaryResultsetReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SiteDAO implements SiteDao {

    private final String url;
    private final String username;
    private final String password;

    private ResultSet resultSet;
    private Connection connection;
    private PreparedStatement preparedStatement;


    public SiteDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void saveSite(Site site) {

        try {
            this.connection = DriverManager.getConnection(url, username, password);

            if (site.getID() != null) {
                this.preparedStatement = this.connection.prepareStatement(
                        "update site set name= ? where id = ? ");
                this.preparedStatement.setString(1, site.getName());
                this.preparedStatement.setLong(2, site.getID());
                this.preparedStatement.execute();

            } else {
                this.preparedStatement = this.connection.prepareStatement
                        ("insert into site (name) values (?)  ;");

                this.preparedStatement.setString(1, site.getName());

                this.preparedStatement.execute();
            }

            this.preparedStatement.close();

            this.connection.close();
            System.out.println(site.getName() + " saved into the database");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the product");
        }
    }
    public Site getSiteByID(Long id){
        try{
            this.connection = DriverManager.getConnection(url,username,password);
            this.preparedStatement = this.connection.prepareStatement
                    ("select * from promotion where id = ?");
            this.preparedStatement.setLong(1, id);

            this.resultSet = this.preparedStatement.executeQuery();

            Site site = new Site();

            while(this.resultSet.next()){
                site.setID(this.resultSet.getLong("id"));
                site.setName(this.resultSet.getString("name"));
            }

            this.resultSet.close();
            this.preparedStatement.close();
            this.connection.close();
            return site;

        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Site> getAllSite() {
        List<Site> list = new ArrayList<>();
        try{
            connection = DriverManager.getConnection(url, username,password);
            preparedStatement = connection.prepareStatement("SELECT * from site");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                String nameSite = resultSet.getString("name");
                Long idSite = resultSet.getLong("id");
                list.add(new Site(idSite,nameSite));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        list.toString();
        return list;
    }

    @Override
    public void createSite() {

    }

    @Override
    public Site updateSite(Site site) {
        return null;
    }

    @Override
    public Site findSiteByName(String name) {
        Site site = new Site();

        try{
            connection =DriverManager.getConnection(url, username,password);

            preparedStatement= connection.prepareStatement("SELECT * from site where (name ) = ?");
            preparedStatement.setString(1, name);

            resultSet = preparedStatement.executeQuery();

            try{
                resultSet.next();
                site.setID(resultSet.getLong("id"));
                site.setName(resultSet.getString("name"));
                System.out.println(site.toString());
            } catch (SQLException throwables) {
                System.out.println("Site introuvable dans la BDD");
            }
            preparedStatement.close();
            resultSet.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return site;
    }

    @Override
    public Site findSiteByID(Long id) {
        Site site = new Site();

        try{
            connection =DriverManager.getConnection(url, username,password);

            preparedStatement= connection.prepareStatement("SELECT * from site where (id) = ?");
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            try{
                resultSet.next();
                site.setID(resultSet.getLong("id"));
                site.setName(resultSet.getString("name"));
                System.out.println(site.toString());
            } catch (SQLException throwables) {
                System.out.println("Site introuvable dans la BDD");
            }

            preparedStatement.close();
            resultSet.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return site;
    }

    @Override
    public void deleteSite(Site site) {
        try{
            connection = DriverManager.getConnection(url, username, password);
            preparedStatement = connection.prepareStatement("delete from site wher (name) = ?");
            preparedStatement.setString(1, site.getName());
            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
