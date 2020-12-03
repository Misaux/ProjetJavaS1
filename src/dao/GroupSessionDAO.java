package dao;

import InterfaceDao.GroupSessionDao;
import models.GroupSession;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupSessionDAO implements GroupSessionDao {

    private String url;
    private String username;
    private String password;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    /**
     *  constructeur de la classe avec parametres
     * @param url url permettant d'acceder a PhpMyAdmin
     * @param username nom utilisateur
     * @param password mot de passe utlisateur
     */
    public GroupSessionDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * constructeur par defaut
     */
    public GroupSessionDAO() {
    }


    /**
     * permet d'obtenir toutes les sessions avec les groupes
     * @return  List<GroupSession></GroupSession>
     */
    @Override
    public List<GroupSession> getAllGroupSession() {

        List<GroupSession> list = new ArrayList<>();
        try {
            this.connection= DriverManager.getConnection(this.url,this.username,this.password);
            this.preparedStatement = this.connection.prepareStatement("SELECT * from group_session");
            this.resultSet = this.preparedStatement.executeQuery();

            while(resultSet.next()){
                Long idSession = resultSet.getLong("id_session");
                Long idGroup = resultSet.getLong("id_group");
                list.add(new GroupSession(idSession,idGroup));
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
     * cree une session pour un groupe donne
     * @param groupSession objet de Groupsession pour obtenir l'id de la session et l'id du group
     */
    @Override
    public void createGroupSession(GroupSession groupSession) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);


            this.preparedStatement = this.connection.prepareStatement
                    ("insert into group_session (id_session,id_group) values (?,?)");

            this.preparedStatement.setLong(1, groupSession.getIdSession());
            this.preparedStatement.setLong(2,groupSession.getIdGroup());
            this.preparedStatement.execute();
            System.out.println(" saved into the database");
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the product");
        }

    }

    /**
     * renvoie une group session avec les bons parametres
     * @param idSession session associee au group
     * @param idGroup group pour attribuer la session
     * @return groupsession selon les parametres
     */
        @Override
    public GroupSession readGroupSession(Long idSession, Long idGroup) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
            this.preparedStatement = this.connection.prepareStatement
                    ("select * from group_session where id_session = ? AND id_group = ? ");
            this.preparedStatement.setLong(1, idSession);
            this.preparedStatement.setLong(2,idGroup);

            this.resultSet = this.preparedStatement.executeQuery();

            GroupSession groupSession = new GroupSession();

            while (this.resultSet.next()) {
                groupSession.setIdSession(this.resultSet.getLong("id_session"));
                groupSession.setIdGroup(this.resultSet.getLong("id_group"));
            }
            this.resultSet.close();
            this.preparedStatement.close();
            this.connection.close();
            return groupSession;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * met a jour une groupsession si des changements se produisent
     * @param groupSession groupsession qui remplace
     */
    @Override
    public void updateGroupSessionIdGroup(GroupSession groupSession) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);


                this.preparedStatement = this.connection.prepareStatement(
                        "update group_session set id_group= ? where id_session= ? ");
                this.preparedStatement.setLong(2,groupSession.getIdSession());
                this.preparedStatement.setLong(1,groupSession.getIdGroup());
                this.preparedStatement.execute();

            System.out.println(" saved into the database");
            this.preparedStatement.close();
            this.connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the site");
        }

    }

    /**
     * met a jour une groupsession si des changements se produisent
     * @param groupSession groupsession qui possede un id session qui est utilise pour les modifs
     */

    @Override
    public void updateGroupSessionIdSession(GroupSession groupSession) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
            this.preparedStatement = this.connection.prepareStatement(
                    "update group_session set id_session= ? where id_group= ? ");
            this.preparedStatement.setLong(1,groupSession.getIdSession());
            this.preparedStatement.setLong(2,groupSession.getIdGroup());
            this.preparedStatement.execute();

            System.out.println(" saved into the database");
            this.preparedStatement.close();
            this.connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the site");
        }

    }

    /**
     * supprime la groupsession passee en parametre
     * @param groupSession groupsession a supprimer
     */
    @Override
    public void deleteGroupSession(GroupSession groupSession) {

        try {
            connection = DriverManager.getConnection(url, username, password);

            preparedStatement = connection.prepareStatement("DELETE from group_session where (id_session) = ? AND id_group = ?  ");
            preparedStatement.setLong(1, groupSession.getIdSession());
            preparedStatement.setLong(2, groupSession.getIdGroup());

            this.preparedStatement.execute();
            System.out.println("La Session a bien été supprimée.");

            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public boolean checkIfAlreadyAssociated(String startTime, Long idGroup, String date){

        List<GroupSession> groupSession = new ArrayList<>();

        try {
            this.connection = DriverManager.getConnection(url, username, password);
            this.preparedStatement = this.connection.prepareStatement
                    ("SELECT * FROM session INNER JOIN group_session ON session.id = group_session.id_session and group_session.id_group= ? and session.date =?  and session.start_time =? ");

            this.preparedStatement.setLong(1, idGroup);
            this.preparedStatement.setString(3, startTime);
            this.preparedStatement.setString(2, date);

            this.resultSet =preparedStatement.executeQuery();

            while(resultSet.next())
            {
               groupSession.add(new GroupSession());
            }

            System.out.println(groupSession.size());

            if(groupSession.size() != 0 )
            {
                JOptionPane.showMessageDialog(null, "This group already have a session at this hour ");
                groupSession.clear();
                return true;

            }

            this.preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to find the product");


        }


        return false;


    }
}
