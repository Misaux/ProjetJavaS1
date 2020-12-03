package InterfaceDao;

import models.Student;
import models.User;

import java.util.List;

public interface StudentDAO {
    List<Student> getAllStudent();
    void createStudent(Student student);
    void updateStudentIdUser(Student student);
    void updateStudentIdGroupPromotion(Student student);
    User findStudentByLastName(String lastName);
    User findStudentByID(Long id);
    void deleteStudent(Student student);
    String getStudentName(String connexion, String passwordEmail);
    boolean checkIfAlreadyCreated(String lastName, String firstName,  int number);
    String getStudentFirstName(String connexion, String passwordEmail);
}
