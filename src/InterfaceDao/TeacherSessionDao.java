package InterfaceDao;


import models.TeacherSession;

import java.util.List;

public interface TeacherSessionDao {

    List<TeacherSession> getAllTeacherSession();
    void createTeacherSession(TeacherSession teacherSession);
    TeacherSession readTeacherSession(Long idSession , Long idTeacher);
    void updateTeacherSession(TeacherSession teacherSession);
    boolean checkIfAlreadyAssociated(String startTime, Long idTeacher, String date);
    void deleteTeacherSession(TeacherSession teacherSession);
    String getIdTeacherFromIdSession(Long idSession);

}
