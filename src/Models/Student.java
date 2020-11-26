package Models;

//STUDENT(#ID_USER, NUMBER, #ID_GROUP …)
public class Student extends User  {

private int number;
private Long idUser;
private Long IdGroupPromotion;


    public Student(Long Id, String email, String password, String first_name, String last_name, Permission permission, int number, Long idGroupPromotion) {
        super(Id, email, password, first_name, last_name, permission);
        this.number = number;
        this.idUser = getID();
        IdGroupPromotion = idGroupPromotion;
    }

    public Student() {
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdGroupPromotion() {
        return IdGroupPromotion;
    }

    public void setIdGroupPromotion(Long idGroupPromotion) {
        IdGroupPromotion = idGroupPromotion;
    }

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
