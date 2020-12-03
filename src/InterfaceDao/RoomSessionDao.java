package InterfaceDao;

import models.RoomSession;

import java.util.List;

public interface RoomSessionDao {

    List<RoomSession> getAllRoomSession();
    void createRoomSession(RoomSession roomSession);
    RoomSession readRoomSession(Long idSession , Long idRoom);
    void updateRoom(RoomSession roomSession);
    void deleteRoom(RoomSession roomSession);
    boolean checkIfAlreadyAssociated(String startTime, Long idRoom, String date);
}
