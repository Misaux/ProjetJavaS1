package Models;


import java.util.ArrayList;
import java.util.List;

//TEACHER (#ID_USER, #ID_COURSE)
public class Teacher extends User {


    private final Long IdTeacher;
    private List<Long> idCourse = new ArrayList<>();

    public Teacher(Long Id, String email, String password, String first_name, String last_name, Permission permission) {
        super(Id, email, password, first_name, last_name, permission);
        this.IdTeacher = getId();
    }

    public void addCourse(Course cours){
        idCourse.add(cours.getID());
    }

    public List<Long> returnIdCourse(){
        return idCourse;
    }

}
