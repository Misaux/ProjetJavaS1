package models;

import java.util.Observable;

public class GroupPromo extends Observable {
    private Long ID;
    private String name;
    private Long promotion;

    public GroupPromo(Long ID, String name, Long idPromotion) {
        this.ID = ID;
        this.name = name;
        this.promotion = idPromotion;
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

    public GroupPromo() {
    }

    @Override
    public String toString() {
        return "GroupPromo{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", promotion=" + promotion +
                '}';
    }
}
