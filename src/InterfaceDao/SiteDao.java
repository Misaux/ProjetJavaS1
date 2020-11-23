package InterfaceDao;


import Models.Site;

import java.util.List;

public interface SiteDao {
    List<Site> getAllSite();
    void createSite();
    Site updateSite(Site site);
    Site findSiteByName(String name);
    Site findSiteByID(Long id);
    void deleteSite(Site site);


}