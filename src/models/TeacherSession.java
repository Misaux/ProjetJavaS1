package models;

import java.util.Observable;

public class TeacherSession extends Observable {

    private Long idSession;
    private Long idTeacher;

    public TeacherSession(Long idSession, Long idTeacher) {
        this.idSession = idSession;
        this.idTeacher = idTeacher;
    }

    public TeacherSession() {
    }

    public Long getIdSession() {
        return idSession;
    }

    public void setIdSession(Long idSession) {
        this.idSession = idSession;
    }

    public Long getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(Long idTeacher) {
        this.idTeacher = idTeacher;
    }

    @Override
    public String toString() {
        return "TeacherSession{" +
                "idSession=" + idSession +
                ", idTeacher=" + idTeacher +
                '}';
    }
}
