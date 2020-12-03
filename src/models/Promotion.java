package models;

import java.util.Observable;

public class Promotion {
    private Long ID;
    private String name;
    private Promotion promotion;


    /**
     * constructeur de la promo avec id et name
     * @param id
     * @param name
     */
    public Promotion(Long id, String name) {
        this.ID = id;
        this.name = name;
    }

    /**
     *constructeur par defaut
     */
    public Promotion() {
    }

    /**
     * set la promo de la classe pour initialiser la promo
     * @param promotion
     */
    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    /**
     *
     * @return id de promo
     */
    public Long getID() {
        return ID;
    }

    /**
     * set id pour la promo
     * @param ID
     */
    public void setID(Long ID) {
        this.ID = ID;
    }

    /**
     * @return le nom de la promo
     */
    public String getName() {
        return name;
    }

    /**
     * set le nom de la promo
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }



    /**
     *
     * @return string avec id et nom de la promo
     */
    @Override
    public String toString() {
        return "Promotion{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                '}';
    }
}
