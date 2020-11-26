package Models;

import java.util.Observable;

public class Promotion extends Observable {
    private Long ID;
    private String name;
    private Promotion promotion;



    public Promotion(Long id, String name) {
        this.ID = id;
        this.name = name;
    }

    public Promotion() {
    }


    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
        setChanged();
        notifyObservers(this.promotion);
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

    @Override
    public String toString() {
        return "Promotion{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                '}';
    }
}
