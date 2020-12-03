
package models;

import java.util.Observable;

public class User extends Observable { //USER (ID, EMAIL, PASSWORD, LAST_NAME, FIRST_NAME, PERMISSION)

    public enum Permission {REFERENT, ELEVE, ENSEIGNANT, ADMIN}

    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private Long ID;
    private Permission permission;
    private User user;

    /**
     * Constructeur par defaut de notre classe User
     */
    public User() {
    }

    /**
     * Constructeur de notre classe User
     *
     * @param Id         id de notre user
     * @param email      email de notre user
     * @param password   mot de passe de notre user
     * @param first_name prenom de notre user
     * @param last_name  nom de famille de notre user
     * @param permission droit d'acces de notre user
     */
    public User(Long Id, String email, String password, String first_name, String last_name, Permission permission) {
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.permission = permission;
        this.ID = Id;
    }

    /**
     * Fonction permettant de recuperer l'email de notre user
     *
     * @return String contenant l'email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Fonction permettant de set l'email de notre user
     *
     * @param email email de notre user
     */
    public void setEmail(String email) {
        this.email = email;
        setChanged();
        notifyObservers(this.user);
    }

    /**
     * Fonction permettant de recuperer le mot de passe de notre user
     *
     * @return String comportant le mot de passe
     */
    public String getPassword() {
        return password;
    }

    /**
     * Fonction permettant de set le mot de passe de notre user
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Fonction  permettant de recuperer le prenom de notre user
     *
     * @return le prenom de notre user
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Fonction permettant de set le prenom de notre user
     *
     * @param first_name prenom de notre user
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
        setChanged();
        notifyObservers(this);
    }

    /**
     * Fonction permettant de recuper le nom de famille de notre user
     *
     * @return le nom de famille de notre user
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Fonction permettant de set le nom de famille de notre user
     *
     * @param last_name nom de famille de notre user
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
        setChanged();
        notifyObservers(this);
    }

    /**
     * Fonction permettant de recuperer l'id de notre user
     *
     * @return l'id de notre user
     */
    public Long getID() {
        return ID;
    }

    /**
     * Fonction permettant de set l'id de notre user
     *
     * @param ID id de notre user
     */
    public void setID(Long ID) {
        this.ID = ID;
    }

    /**
     * Fonction permettant de recuperer le droit d'acces de notre user
     *
     * @return droit d'acces de notre user
     */
    public String getPermission() {
        return permission.name();
    }

    /**
     * Fonction permettant de set les droits d'acces de notre user
     *
     * @param permission droit d'acces
     */
    public void setPermission(String permission) {
        this.permission = Permission.valueOf(permission);
    }

    /**
     * Fonction permettant d'afficher les attributs de notre user
     * @return String contenant les differents de notre user
     */
    @Override
    public String toString() {
        return "User{" +
                "Id='" + ID + '\'' +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", permission=" + permission +
                '}';
    }
}