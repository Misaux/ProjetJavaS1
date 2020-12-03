package InterfaceDao;

import models.GroupSession;

import java.util.List;

public interface GroupSessionDao {

    List<GroupSession> getAllGroupSession();
    void createGroupSession(GroupSession groupSession);
    GroupSession readGroupSession(Long idSession , Long idGroup);
    void updateGroupSessionIdGroup(GroupSession groupSession);
    void updateGroupSessionIdSession(GroupSession groupSession);
    void deleteGroupSession(GroupSession groupSession);

}
