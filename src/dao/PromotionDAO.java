package dao;

import InterfaceDao.PromotionDao;
import models.Promotion;

import javax.management.relation.RelationSupportMBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PromotionDAO implements PromotionDao {

    private String url;
    private String username;
    private String password;

    private PreparedStatement preparedStatement;
    private Connection connection;
    private ResultSet resultSet;

    /**
     * constructeur
     * @param url url du serveur sql
     * @param username nom d'utilisateur
     * @param password mot de passe
     */
    public PromotionDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * mettre a jour une promo dans la BDD
     * @param promotion promo passee en parametre pour remplacer une ancienne
     */
    public void updatePromotion(Promotion promotion) {

        try {
            connection = DriverManager.getConnection(url, username, password);

            if (promotion.getID() != null) {
                preparedStatement = connection.prepareStatement(
                        "update promotion set name= ? where id = ? ");
                preparedStatement.setString(1, promotion.getName());
                preparedStatement.setLong(2, promotion.getID());
                preparedStatement.execute();

            } else {
                preparedStatement = connection.prepareStatement
                        ("insert into promotion (name) values (?)  ;");

                preparedStatement.setString(1, promotion.getName());

                preparedStatement.execute();
            }


            System.out.println(promotion.getName() + " saved into the database");

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the product");
        }
    }

    /**
     * fonction pour obtenir toutes promos de la BDD
     * @return List<Promotion></Promotion>
     */
    @Override
    public List<Promotion> getAllPromotion() {
        List<Promotion> list = new ArrayList<>();
        try{
            connection = DriverManager.getConnection(url, username,password);
            preparedStatement = connection.prepareStatement("SELECT * from promotion");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                String nameSite = resultSet.getString("name");
                Long idSite = resultSet.getLong("id");
                list.add(new Promotion(idSite,nameSite));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



        return list;
    }

    /**
     * ajoute une promo a la BDD
     * @param promotion promo a ajouter a la BDD
     */
    @Override
    public void createPromotion(Promotion promotion) {
        try {
            connection = DriverManager.getConnection(url, username, password);

            if (promotion.getID() != null) {
                System.out.println("This promotion already exists.");

            } else {
                preparedStatement = connection.prepareStatement
                        ("insert into promotion (name) values (?)  ;");

                preparedStatement.setString(1, promotion.getName());

                preparedStatement.execute();
            }


            System.out.println(promotion.getName() + " saved into the database");

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the product");
        }
    }

    /**
     * trouve une promo dans la BDD
     * @param name nom de la promo a trouver
     * @return la promo selon son nom
     */
    @Override
    public Promotion findPromotionByName(String name) {
        Promotion promotion = new Promotion();
        try {
            connection = DriverManager.getConnection(url, username, password);
            preparedStatement = connection.prepareStatement("SELECT * from promotion where (name) = ?");
            this.preparedStatement.setString(1, name);
            this.resultSet = this.preparedStatement.executeQuery();

            try {
                resultSet.next();
                promotion.setName(this.resultSet.getString("name"));
                promotion.setID(this.resultSet.getLong("id"));
                System.out.println(promotion.toString());
                return promotion;
            } catch (SQLException throwables) {
                System.out.println("ERREUR, promotion introuvable");
            }


            preparedStatement.close();
            resultSet.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  supprime la BDD selon les parametres
     * @param promotion promo a supprimer
     */
    @Override
    public void deletePromotion(Promotion promotion) {
        try {
            connection = DriverManager.getConnection(url, username, password);

            preparedStatement = connection.prepareStatement("DELETE from promotion where (name) = ? ");
            preparedStatement.setString(1, promotion.getName());

            System.out.println("La promo a bien été supprimée.");

            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     *  trouver une promo par son id
     * @param id id de la promo pour rechercher
     * @return la promo selon l'id saisi
     */
    @Override
    public Promotion getPromotionByID(Long id) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            preparedStatement = connection.prepareStatement
                    ("select * from promotion where id = ?");
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            Promotion promotion = new Promotion();

            while (resultSet.next()) {
                promotion.setID(resultSet.getLong("id"));
                promotion.setName(resultSet.getString("name"));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
            System.out.println(promotion.getName());
            return promotion;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
