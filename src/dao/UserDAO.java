package dao;

import InterfaceDao.UserDao;
import models.User;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAO implements UserDao  {

    private String url;
    private String username;
    private String password;
    private User user = new User();

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    public UserDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }


    public User getUserConnection(String email, String psswrd) {
        try {


            connection = DriverManager.getConnection(url, username, password);

            preparedStatement = connection.prepareStatement("SELECT * from user where email = ? and password = ?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, psswrd);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                this.user.setID(resultSet.getLong("id"));
                this.user.setEmail(resultSet.getString("email"));
                this.user.setPassword(resultSet.getString("password"));
                this.user.setLast_name(resultSet.getString("last_name"));
                this.user.setFirst_name(resultSet.getString("first_name"));
                this.user.setPermission(resultSet.getString("permission"));
            }

            if (user == null) {
                throw new Exception("le user n'exitse pas");
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();


            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }


    @Override
    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        try{
            this.connection = DriverManager.getConnection(url, username,password);
            this.preparedStatement = connection.prepareStatement("SELECT * from user");
            this.resultSet = this.preparedStatement.executeQuery();

            while(this.resultSet.next()){
                String emailUser = this.resultSet.getString("email");
                String passwordUser =this.resultSet.getString("password");
                String lastNameUser = this.resultSet.getString("last_name");
                String firstNameUser =this.resultSet.getString("first_name");
                String permissionUser = this.resultSet.getString("permission");
                Long idUser = this.resultSet.getLong("id");
                list.add(new User(idUser, emailUser, passwordUser, firstNameUser, lastNameUser,User.Permission.valueOf(permissionUser)));
            }

            this.resultSet.close();
            this.preparedStatement.close();
            this.connection.close();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    @Override
    public void createUser(User user) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);

            if (user.getID() != null) {
                System.out.println("This course already exists.");

            } else {
                this.preparedStatement = this.connection.prepareStatement
                        ("insert into user (email,password,last_name,first_name,permission) values (?,?,?,?,?)");
                this.preparedStatement.setString(1, user.getEmail());
                this.preparedStatement.setString(2, user.getPassword());
                this.preparedStatement.setString(3, user.getLast_name());
                this.preparedStatement.setString(4, user.getFirst_name());
                this.preparedStatement.setString(5, user.getPermission());
                this.preparedStatement.execute();
            }

            JOptionPane.showMessageDialog(null, "User Successfully added in the user database");
            System.out.println(" user saved into the database");
            this.preparedStatement.close();
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the user");
        }


    }

    @Override
    public void updateUser(User user) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);

            if (user.getID() != null) {
                this.preparedStatement = this.connection.prepareStatement(
                        "update user set email= ? set password = ? set last_name = ? set first_name= ? set permission = ? where id = ? ");
                this.preparedStatement.setString(1, user.getEmail());
                this.preparedStatement.setString(2, user.getPassword());
                this.preparedStatement.setString(3, user.getLast_name());
                this.preparedStatement.setString(4, user.getFirst_name());
                this.preparedStatement.setString(5, user.getPermission());
                this.preparedStatement.setLong(6, user.getID());
                this.preparedStatement.execute();

            } else {
                this.preparedStatement = this.connection.prepareStatement(
                        "insert into user (email,password,last_name,first_name,permission) values (?,?,?,?,?)");
                this.preparedStatement.setString(1, user.getEmail());
                this.preparedStatement.setString(2, user.getPassword());
                this.preparedStatement.setString(3, user.getLast_name());
                this.preparedStatement.setString(4, user.getFirst_name());
                this.preparedStatement.setString(5, user.getPermission());
                this.preparedStatement.execute();
            }

            System.out.println(" saved into the database");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the user");
        }
    }

    @Override
    public User findUserByFirstName(String firstName){
        try {
            this.connection= DriverManager.getConnection(this.url,this.username,this.password);
            this.preparedStatement= this.connection.prepareStatement
                    ("select * from user where first_name = ?");
            this.preparedStatement.setString(1,firstName);
            this.resultSet = this.preparedStatement.executeQuery();

            User user = new User();


            while(this.resultSet.next()){

                user.setEmail(this.resultSet.getString("email"));
                user.setPassword(this.resultSet.getString("password"));
                user.setLast_name(this.resultSet.getString("last_name"));
                user.setFirst_name(this.resultSet.getString("first_name"));
                user.setPermission(this.resultSet.getString("permission"));
                user.setID(this.resultSet.getLong("id"));

            }
            this.resultSet.close();
            this.preparedStatement.close();
            this.connection.close();
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findUserByLastName(String lastName) {
        try {
            this.connection= DriverManager.getConnection(this.url,this.username,this.password);
            this.preparedStatement= this.connection.prepareStatement
                    ("select * from user where last_name = ?");
            this.preparedStatement.setString(1,lastName);
            this.resultSet = this.preparedStatement.executeQuery();

           User user = new User();


            while(this.resultSet.next()){

                user.setEmail(this.resultSet.getString("email"));
                user.setPassword(this.resultSet.getString("password"));
                user.setLast_name(this.resultSet.getString("last_name"));
                user.setFirst_name(this.resultSet.getString("first_name"));
                user.setPermission(this.resultSet.getString("permission"));
                user.setID(this.resultSet.getLong("id"));

            }
            this.resultSet.close();
            this.preparedStatement.close();
            this.connection.close();
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findUserByID(Long id) {
        try{
            User user = new User();
            this.connection =DriverManager.getConnection(url, username,password);

            this.preparedStatement= this.connection.prepareStatement("SELECT * from user where (id) = ?");
            this.preparedStatement.setLong(1, id);

            this.resultSet = this.preparedStatement.executeQuery();

            try{
                this.resultSet.next();
                user.setID(this.resultSet.getLong("id"));
                user.setEmail(this.resultSet.getString("email"));
                user.setPassword(this.resultSet.getString("password"));
                user.setLast_name(this.resultSet.getString("last_name"));
                user.setFirst_name(this.resultSet.getString("first_name"));
                user.setPermission(this.resultSet.getString("permission"));

                System.out.println(user.toString());
            } catch (SQLException throwables) {
                System.out.println("Site introuvable dans la BDD");
            }

            this.preparedStatement.close();
            this.resultSet.close();
            this.connection.close();

            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void deleteUser(User user) {
        try{
            this.connection = DriverManager.getConnection(url, username, password);

            this.preparedStatement = this.connection.prepareStatement("DELETE from user where last_name = ? and first_name = ? ");
            this.preparedStatement.setString(1,user.getLast_name());
            this.preparedStatement.setString(2,user.getFirst_name());

            this.preparedStatement.execute();

            System.out.println("L'user a bien été supprimée.");

            this.preparedStatement.close();
            this.connection.close();
            JOptionPane.showMessageDialog(null, "User Successfully deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getUserName(String connexion, String passwordEmail) {
        try {

            this.connection = DriverManager.getConnection(url, username, password);

            this.preparedStatement =  this.connection.prepareStatement("SELECT * from user where email = ? and password = ?");
            this.preparedStatement.setString(1, connexion);
            this.preparedStatement.setString(2, passwordEmail);

            this.resultSet =  this.preparedStatement.executeQuery();

            while ( this.resultSet.next()) {
                this.user.setLast_name( this.resultSet.getString("last_name"));
            }

            if ( this.user.getLast_name() == null) {
                throw new Exception();
            }
            this.resultSet.close();
            this.preparedStatement.close();
            this.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return this.user.getLast_name();
    }

    @Override
    public String getUserFirstName(String connexion, String passwordEmail) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);

            this.preparedStatement =  this.connection.prepareStatement("SELECT * from user where email = ? and password = ?");
            this.preparedStatement.setString(1, connexion);
            this.preparedStatement.setString(2, passwordEmail);

            this.resultSet =  this.preparedStatement.executeQuery();

            while ( this.resultSet.next()) {
                this.user.setFirst_name( this.resultSet.getString("first_name"));
            }

            if ( this.user.getFirst_name() == null) {
                throw new Exception();
            }
            this.resultSet.close();
            this.preparedStatement.close();
            this.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return this.user.getFirst_name();
    }
}
