package InterfaceDao;

import Models.Room;
import Models.RoomSession;

import java.util.List;

public interface RoomSessionDao {

    List<RoomSession> getAllRoomSession();
    void createRoomSession(RoomSession roomSession);
    RoomSession readRoomSession(Long idSession , Long idRoom);
    void updateRoom(RoomSession roomSession);
    void deleteRoom(RoomSession roomSession);
}
