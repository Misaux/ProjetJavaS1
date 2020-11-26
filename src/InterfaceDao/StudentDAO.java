package InterfaceDao;

import Models.Student;
import Models.Teacher;
import Models.User;

import java.util.List;

public interface StudentDAO {
    List<Student> getAllStudent();
    void createStudent(Student student);
    void updateStudentIdUser(Student student);
    void updateStudentIdGroupPromotion(Student student);
    User findStudentByLastName(String name);
    User findStudentByID(Long id);
    void deleteStudent(Student student);
    String getStudentName(String connexion, String passwordEmail);
    String getStudentFirstName(String connexion, String passwordEmail);
}
