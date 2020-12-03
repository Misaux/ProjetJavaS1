package InterfaceDao;

import models.Room;
import org.jfree.data.jdbc.JDBCPieDataset;

import java.util.List;

public interface RoomDao {

    List<Room> getAllRoom();
    void createRoom(Room room);
    Room readRoomByName(String room);
    Room readRoomByID(Long id);
    void updateRoom(Room room);
    void deleteRoom(Room room);
    JDBCPieDataset readDataNumberPerSite();
    JDBCPieDataset readDataCapacityPerSite();
}
