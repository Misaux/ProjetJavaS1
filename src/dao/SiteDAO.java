package dao;

import InterfaceDao.SiteDao;
import models.*;
//import com.mysql.cj.protocol.a.BinaryResultsetReader;

import javax.swing.*;
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

    /**
     * constructeur
     * @param url adresse connexion serveur sql
     * @param username nom de l'utilisateur
     * @param password mot de passe
     */
    public SiteDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * ajoute un site a la BDD
     * @param site site a ajouter
     */
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

    /**
     * permet d'obtenir tous les sites inscrits dans la BDD
     * @return List<Site></Site>
     */
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

    /**
     * cree un site
     */
    @Override
    public void createSite() {

    }

    /**
     * met a jour le site donne dans la base de donnees
     * @param site site a mettre a jour
     * @return site qui a ete mis a jour
     */
    @Override
    public Site updateSite(Site site) {
        return null;
    }

    /**
     * trouve le site de la BDD selon son nom
     * @param name nom du site
     * @return le site trouve avec le bon nom
     */
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

    /**
     *trouve le site de la BDD selon son id
     * @param id id du site a rechercher
     * @return le site trouve avec le bon id
     */
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

    /**
     * supprime le site qu'on lui passe en parametre
     * @param site site a supprimer
     */
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
