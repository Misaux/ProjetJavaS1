package Models;


import java.util.ArrayList;
import java.util.List;

//TEACHER (#ID_USER, #ID_COURSE)
public class Teacher extends User {


    private  Long idUser;
    private ArrayList<Long> idAllCourse = new ArrayList<>();
    private Long idCourse;

    public Teacher(Long Id, String email, String password, String first_name, String last_name, Permission permission, Long idCourse) {
        super(Id, email, password, first_name, last_name, permission);
        this.idUser = getID();
        this.idCourse= idCourse;
    }

    public Teacher() {
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public ArrayList<Long> getIdAllCourse() {
        return idAllCourse;
    }

    public void setIdAllCourse(ArrayList<Long> idAllCourse) {
        this.idAllCourse = idAllCourse;
    }

    public Long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }

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
