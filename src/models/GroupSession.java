package models;

import java.util.Observable;

public class GroupSession extends Observable {

    private Long idSession;
    private Long idGroup;

    /**
     * constructeur
     * @param idSession id de la session
     * @param idGroup id du groupe
     */
    public GroupSession(Long idSession, Long idGroup) {
        this.idSession = idSession;
        this.idGroup = idGroup;
    }

    /**
     * constructeur par defaut
     */
    public GroupSession() {
    }

    /**
     *
     * @return id session
     */
    public Long getIdSession() {
        return idSession;
    }

    /**
     * set l'id session
     * @param idSession
     */
    public void setIdSession(Long idSession) {
        this.idSession = idSession;
    }

    /**
     *
     * @return id group
     */
    public Long getIdGroup() {
        return idGroup;
    }

    /**
     * set l'id du groupe
     * @param idGroup
     */
    public void setIdGroup(Long idGroup) {
        this.idGroup = idGroup;
    }

    /**
     * @return string avec l'id de la session et du groupe
     */
    @Override
    public String toString() {
        return "GroupSession{" +
                "idSession=" + idSession +
                ", idGroup=" + idGroup +
                '}';
    }
}
