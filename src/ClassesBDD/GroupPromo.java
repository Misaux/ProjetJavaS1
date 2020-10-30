package ClassesBDD;

public class GroupPromo {
    private int ID;
    private String name;
    private int promotion;

    public GroupPromo(int ID, String name, Promotion promo) {
        this.ID = ID;
        this.name = name;
        this.promotion = promo.getID();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }
}
