package InterfaceDao;

import models.Promotion;

import java.util.List;

public interface PromotionDao {
    List<Promotion> getAllPromotion();

    void createPromotion(Promotion promotion);
    Promotion findPromotionByName(String promotion);
    Promotion getPromotionByID(Long id);
    void updatePromotion(Promotion promotion);
    void deletePromotion (Promotion promotion);

}
