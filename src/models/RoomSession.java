package models;

import java.util.Observable;

public class RoomSession extends Observable {

    private Long idSession;
    private Long idRoom;

    /**
     * constructeur avec parametres
     * @param idSession id de la session
     * @param idRoom id de la salle
     */
    public RoomSession(Long idSession, Long idRoom) {
        this.idSession = idSession;
        this.idRoom = idRoom;
    }

    /**
     * constructeur par defaut
     */
    public RoomSession() {

    }

    /**
     *
     * @return id de la session
     */
    public Long getIdSession() {
        return idSession;
    }

    /**
     *
     * @return l'id de la room
     */
    public Long getIdRoom() {
        return idRoom;
    }

    /**
     * set l'id de la session
     * @param idSession
     */
    public void setIdSession(Long idSession) {
        this.idSession = idSession;
    }

    /**
     * set l'id de la room
     * @param idRoom
     */
    public void setIdRoom(Long idRoom) {
        this.idRoom = idRoom;
    }

    /**
     *
     * @return string avec id session et id de la room
     */
    @Override
    public String toString() {
        return "RoomSession{" +
                "idSession=" + idSession +
                ", idRoom=" + idRoom +
                '}';
    }
}
