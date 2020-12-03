package models;

import java.util.Observable;

public class RoomSession extends Observable {

    private Long idSession;
    private Long idRoom;

    public RoomSession(Long idSession, Long idRoom) {
        this.idSession = idSession;
        this.idRoom = idRoom;
    }

    public RoomSession() {

    }

    public Long getIdSession() {
        return idSession;
    }

    public Long getIdRoom() {
        return idRoom;
    }

    public void setIdSession(Long idSession) {
        this.idSession = idSession;
    }

    public void setIdRoom(Long idRoom) {
        this.idRoom = idRoom;
    }

    @Override
    public String toString() {
        return "RoomSession{" +
                "idSession=" + idSession +
                ", idRoom=" + idRoom +
                '}';
    }
}
