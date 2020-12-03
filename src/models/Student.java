package models;

//STUDENT(#ID_USER, NUMBER, #ID_GROUP …)
public class Student extends User  {

private int number;
private Long idUser;
private Long IdGroupPromotion;

    /**
     * constructeur avec parametres de student qui herite de user
     * @param Id id du eleve
     * @param email email du eleve
     * @param password mot de passe de l'eleve
     * @param first_name prenom de l'eleve
     * @param last_name nom de l'eleve
     * @param permission permission de l'eleve
     * @param number numero de l'eleve
     * @param idGroupPromotion id de la promo de l'eleve
     */
    public Student(Long Id, String email, String password, String first_name, String last_name, Permission permission, int number, Long idGroupPromotion) {
        super(Id, email, password, first_name, last_name, permission);
        this.number = number;
        this.idUser = getID();
        IdGroupPromotion = idGroupPromotion;
    }

    /**
     * constructeur par defaut
     */
    public Student() {
    }

    /**
     *
     * @return le numero de l'eleve
     */
    public int getNumber() {
        return number;
    }

    /**
     * set le numero de l'eleve
     * @param number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     *
     * @return l'id de l'eleve
     */
    public Long getIdUser() {
        return idUser;
    }

    /**
     * set l'id de l'eleve
     * @param idUser
     */
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    /**
     *
     * @return l'id du groupe de l'eleve
     */
    public Long getIdGroupPromotion() {
        return IdGroupPromotion;
    }

    /**
     * set l'id de la promo de l'eleve
     * @param idGroupPromotion
     */
    public void setIdGroupPromotion(Long idGroupPromotion) {
        IdGroupPromotion = idGroupPromotion;
    }

    /**
     *
     * @return string avec toutes les infos de l'eleve
     */
    @Override
    public String toString() {
        return "Student{" +
                super.toString()+
                "number=" + number +
                ", idUser=" + idUser +
                ", IdGroupPromotion=" + IdGroupPromotion +
                '}';
    }
}
