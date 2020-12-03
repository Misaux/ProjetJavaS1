package dao;

import models.GroupPromo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupPromoDAO implements InterfaceDao.GroupPromoDAO {

    private String url;
    private String username;
    private String password;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    /**
     * Constructeur de la classe GroupPromoDAO
     * @param url url permettant d'acceder a PhpMyAdmin
     * @param username identifiant pour acceder PhpMyAdmin
     * @param password mot de passe pour acceder a PhpMyAdmin
     */
    public GroupPromoDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;

    }

    /**
     * Fonction permettant de retourner une liste de tous les groupes de promotion present dans la base de donnee
     * @return List<GroupPromo></GroupPromo>
     */
    @Override
    public List<GroupPromo> getAllGroupPromo() {
        List<GroupPromo> list = new ArrayList<>();
        try {
            this.connection=DriverManager.getConnection(this.url,this.username,this.password);
            this.preparedStatement = this.connection.prepareStatement("SELECT * from group_promotion");
            this.resultSet = this.preparedStatement.executeQuery();

            while(resultSet.next()){
                String name = resultSet.getString("name");
                Long id = resultSet.getLong("id");
                Long idPromotion = resultSet.getLong("id_promotion");
                list.add(new GroupPromo(id,name,idPromotion));
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
     * Fonction permettant d'ajouter un groupe de promotion a la base de donnee
     * @param groupPromo groupePromo qu'on veut ajouter
     */
    @Override
    public void createGroupPromoDao(GroupPromo groupPromo) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);

            if (groupPromo.getID() != null) {
                System.out.println("This course already exists.");

            } else {
                this.preparedStatement = this.connection.prepareStatement
                        ("insert into group_promotion (name,id_promotion) values (?,?)");

                this.preparedStatement.setString(1, groupPromo.getName());
                this.preparedStatement.setLong(2,groupPromo.getPromotion());
                this.preparedStatement.execute();
            }

            System.out.println(groupPromo.getName() + " saved into the database");
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the product");
        }

    }

    /**
     * Fonction permettant de rechercher un groupe de promotion dans la base de donnee
     * @param name Nom du groupe qu'on recherche
     * @return GroupPromo
     */
    @Override
    public GroupPromo readGroupPromoByName(String name) {
        try {
            this.connection= DriverManager.getConnection(this.url,this.username,this.password);
            this.preparedStatement= this.connection.prepareStatement
                    ("select * from group_promotion where name = ?");
            this.preparedStatement.setString(1,name);
            this.resultSet = this.preparedStatement.executeQuery();

            GroupPromo groupPromo = new GroupPromo();


            while(this.resultSet.next()){

                groupPromo.setID(this.resultSet.getLong("id"));
                groupPromo.setName(this.resultSet.getString("name"));
                groupPromo.setPromotion(this.resultSet.getLong("id_promotion"));

            }
            this.resultSet.close();
            this.preparedStatement.close();
            this.connection.close();
            return groupPromo;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Fonction permettant de rechercher un groupe de promotion dans la base de donnee selon son id
     * @param Id Id du groupe qu'on recherche
     * @return GroupPromo
     */
    @Override
    public GroupPromo readGroupPromoById(Long Id) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
            this.preparedStatement = this.connection.prepareStatement
                    ("select * from group_promotion where id = ?");
            this.preparedStatement.setLong(1, Id);

            this.resultSet = this.preparedStatement.executeQuery();

            GroupPromo groupPromo = new GroupPromo();

            while (this.resultSet.next()) {
                groupPromo.setID(this.resultSet.getLong("id"));
                groupPromo.setName(this.resultSet.getString("name"));
                groupPromo.setPromotion(this.resultSet.getLong("id_promotion"));
            }
            this.resultSet.close();
            this.preparedStatement.close();
            this.connection.close();
            return groupPromo;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Fonction qui permet de mettre a jour un groupe de promotion
     * si le groupe entre en parametre existe on le met a jour dans la base de donnee
     * sinon on l'ajoute a notre base de donnee
     * @param groupPromo Groupe qu'on veut mettre a jour
     */
    @Override
    public void updateGroupPromoDao(GroupPromo groupPromo) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);

            if (groupPromo.getID() != null) {
                this.preparedStatement = this.connection.prepareStatement(
                        "update group_promotion set name= ? set id_promotion=? where id = ? ");
                this.preparedStatement.setString(1, groupPromo.getName());
                this.preparedStatement.setLong(2,groupPromo.getPromotion());
                this.preparedStatement.setLong(3, groupPromo.getID());
                this.preparedStatement.execute();

            } else {
                this.preparedStatement = this.connection.prepareStatement
                        ("insert into group_promotion (name,id_promotion) values (?,?,?)");
                this.preparedStatement.setString(1, groupPromo.getName());
                this.preparedStatement.setLong(2,groupPromo.getPromotion());
                this.preparedStatement.setLong(3, groupPromo.getID());
                this.preparedStatement.execute();
            }
            System.out.println(groupPromo.getName() + " saved into the database");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the site");
        }

    }

    /**
     * Fonction permettant de supprimer un groupe de promotion de la base de donnee
     * @param groupPromo Groupe qu'on veut supprimer
     */
    @Override
    public void deleteGroupPromoDao(GroupPromo groupPromo) {
        try {
            connection = DriverManager.getConnection(url, username, password);

            preparedStatement = connection.prepareStatement("DELETE from group_promotion where (name) = ? ");
            preparedStatement.setString(1, groupPromo.getName());

            this.preparedStatement.execute();
            System.out.println("Le groupe de promotion a bien été supprimée.");

            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    }
