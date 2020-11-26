package InterfaceDao;

import Models.Teacher;
import Models.User;

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
    String getTeacherFirstName(String connexion, String passwordEmail);
}
