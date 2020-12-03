package models;

import java.util.Observable;

public class TeacherSession extends Observable {

    private Long idSession;
    private Long idTeacher;


    /**
     * Constucteur de notre classe TeacherSession
     *
     * @param idSession id de notre session
     * @param idTeacher id de notre teacher participant à notre session
     */
    public TeacherSession(Long idSession, Long idTeacher) {
        this.idSession = idSession;
        this.idTeacher = idTeacher;
    }

    /**
     * Constructeur par defaut
     */
    public TeacherSession() {
    }

    /**
     * Fonction permettant de recuperer l'id de notre session
     *
     * @return l'id de notre session
     */
    public Long getIdSession() {
        return idSession;
    }

    /**
     * Fonction permettant de set l'id de notre de session
     *
     * @param idSession id d'une session
     */
    public void setIdSession(Long idSession) {
        this.idSession = idSession;
    }

    /**
     * Fonction permettant de recuperer l'id du teacher participant a une session
     *
     * @return id du teacher
     */
    public Long getIdTeacher() {
        return idTeacher;
    }

    /**
     * Fonction permettant de set l'id d'un teacher pour un cours
     *
     * @param idTeacher
     */
    public void setIdTeacher(Long idTeacher) {
        this.idTeacher = idTeacher;
    }

    /**
     * Fonction permettant d'afficher les attributs de notre cours
     *
     * @return String comportant les attributs de notre cours
     */
    @Override
    public String toString() {
        return "TeacherSession{" +
                "idSession=" + idSession +
                ", idTeacher=" + idTeacher +
                '}';
    }
}
