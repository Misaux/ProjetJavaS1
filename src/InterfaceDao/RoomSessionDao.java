package InterfaceDao;

import models.RoomSession;

import java.util.List;

public interface RoomSessionDao {

    List<RoomSession> getAllRoomSession();
    void createRoomSession(RoomSession roomSession);
    RoomSession readRoomSession(Long idSession , Long idRoom);
    void updateRoomSession(RoomSession roomSession);
    void deleteRoomSession(RoomSession roomSession);
}
