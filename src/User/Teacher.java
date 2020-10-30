package User;


import ClassesBDD.Course;

import java.util.ArrayList;
import java.util.List;

//TEACHER (#ID_USER, #ID_COURSE)
public class Teacher extends User {


    private final int m_IdTeacher;
    private List<Integer> m_idCourse = new ArrayList<>();

    public Teacher(int Id, String email, String password, String first_name, String last_name, Permission permission) {
        super(Id, email, password, first_name, last_name, permission);
        this.m_IdTeacher = getId();
    }

    public void addCourse(Course cours){
        m_idCourse.add(cours.getID());
    }

    public List<Integer> returnIdCourse(){
        return m_idCourse;
    }

}
