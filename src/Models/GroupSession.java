package Models;

import java.util.Observable;

public class GroupSession extends Observable {

    private Long idSession;
    private Long idGroup;

    public GroupSession(Long idSession, Long idGroup) {
        this.idSession = idSession;
        this.idGroup = idGroup;
    }

    public GroupSession() {
    }

    public Long getIdSession() {
        return idSession;
    }

    public void setIdSession(Long idSession) {
        this.idSession = idSession;
    }

    public Long getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Long idGroup) {
        this.idGroup = idGroup;
    }

    @Override
    public String toString() {
        return "GroupSession{" +
                "idSession=" + idSession +
                ", idGroup=" + idGroup +
                '}';
    }
}
