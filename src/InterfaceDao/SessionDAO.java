package InterfaceDao;


import Models.Session;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface SessionDAO {
    List<Session> getAllSession();
    void createSession(Session session);
    Session readSession(int week, String date, String time);
    void updateSession(Session session);
    void deleteSession(Session session);
}
