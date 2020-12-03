package models;


import java.util.ArrayList;

//TEACHER (#ID_USER, #ID_COURSE)
public class Teacher extends User {


    private Long idUser;
    private ArrayList<Long> idAllCourse = new ArrayList<>();
    private Long idCourse;

    /**
     * Constructeur de notre classe teacher
     *
     * @param Id
     * @param email
     * @param password
     * @param first_name
     * @param last_name
     * @param permission
     * @param idCourse
     */
    public Teacher(Long Id, String email, String password, String first_name, String last_name, Permission permission, Long idCourse) {
        super(Id, email, password, first_name, last_name, permission);
        this.idUser = getID();
        this.idCourse = idCourse;
    }

    /**
     * Constructeur par defaut de notre classe teacher
     */
    public Teacher() {
    }

    /**
     * Fonction permettant de recuperer l'id de notre teacher
     *
     * @return id du User
     */
    public Long getIdUser() {
        return idUser;
    }


    /**
     * Fonction permettant de set l'id de notre teacher
     *
     * @param idUser id de notre teacher
     */
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    /**
     * Fonction permettant de recuperer tout les cours de notre teacher
     *
     * @return une liste de tous les cours
     */
    public ArrayList<Long> getIdAllCourse() {
        return idAllCourse;
    }

    /**
     * Fonction permettant de set tout les cours de notre teacher
     *
     * @param idAllCourse Une liste comporant differents cours
     */
    public void setIdAllCourse(ArrayList<Long> idAllCourse) {
        this.idAllCourse = idAllCourse;
    }

    /**
     * Fonction permettant de recuperer l'id de la matiere de notre professeur
     *
     * @return l'id de la matiere de notre professeur
     */
    public Long getIdCourse() {
        return idCourse;
    }

    /**
     * Fonction permettant de set la matiere de notre professeur
     *
     * @param idCourse matiere passee en parametre
     */
    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }

    /**
     * Fonction permettant d'afficher les attributs de notre teacher
     *
     * @return String avec tous les attributs de notre teacher
     */
    @Override
    public String toString() {
        return "Teacher{" +
                super.toString() +
                "idUser=" + idUser +
                ", idAllCourse=" + idAllCourse +
                ", idCourse=" + idCourse +
                '}';
    }
}
