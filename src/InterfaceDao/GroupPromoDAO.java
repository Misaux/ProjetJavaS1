package InterfaceDao;

import models.GroupPromo;

import java.util.List;

public interface GroupPromoDAO {

    List<GroupPromo> getAllGroupPromo();
    void createGroupPromoDao(GroupPromo groupPromo);
    GroupPromo readGroupPromoByName(String name);
    GroupPromo readGroupPromoById(Long Id);
    void updateGrouPromoDao(GroupPromo groupPromo);
    void deleteGroupPromoDao(GroupPromo groupPromo);
}
