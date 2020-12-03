package InterfaceDao;


import models.TeacherSession;

import java.util.List;

public interface TeacherSessionDao {

    List<TeacherSession> getAllTeacherSession();
    void createTeacherSession(TeacherSession teacherSession);
    TeacherSession readTeacherSession(Long idSession , Long idTeacher);
    void updateTeacherSession(TeacherSession teacherSession);
    void deleteTeacherSession(TeacherSession teacherSession);

}
