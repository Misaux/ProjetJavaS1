package InterfaceDao;


import models.Site;

import java.util.List;

public interface SiteDao {
    List<Site> getAllSite();
    void createSite();
    Site updateSite(Site site);
    Site findSiteByName(String name);
    Site findSiteByID(Long id);
    void deleteSite(Site site);
    void saveSite(Site site);
    Site getSiteByID(Long id);


}
