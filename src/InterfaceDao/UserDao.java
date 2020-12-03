package InterfaceDao;

import models.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUser();
    void createUser(User user);
    void updateUser(User user);
    User findUserByLastName(String lastName);
    User findUserByFirstName(String firstName);
    User findUserByID(Long id);
    void deleteUser(User user);
    User getUserConnection(String email, String password);
    String getUserName(String connexion, String passwordEmail);
    String getUserFirstName(String connexion, String passwordEmail);



}