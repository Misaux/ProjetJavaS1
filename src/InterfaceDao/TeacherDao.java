package InterfaceDao;

import models.Teacher;
import models.User;

import java.util.List;

public interface TeacherDao {

    List<Teacher> getAllTeacher();
    void createTeacher(Teacher teacher);
    void updateTeacherIdUser(Teacher teacher);
    void updateTeacherIdCourse(Teacher teacher);
    User findTeacherByName(String name);
    User findTeacherByID(Long id);
    void deleteTeacher(Teacher teacher);
    String getTeacherName(String connexion, String passwordEmail);
    boolean checkIfAlreadyCreated(String firstname, String last_name);
    String getTeacherFirstName(String connexion, String passwordEmail);
}
