package Models;

public class GroupPromo {
    private Long ID;
    private String name;
    private Long promotion;

    public GroupPromo(Long ID, String name, Promotion promo) {
        this.ID = ID;
        this.name = name;
        this.promotion = promo.getID();
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPromotion() {
        return promotion;
    }

    public void setPromotion(Long promotion) {
        this.promotion = promotion;
    }
}
