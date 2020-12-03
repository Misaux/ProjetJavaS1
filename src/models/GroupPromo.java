package models;


public class GroupPromo{
    private Long ID;
    private String name;
    private Long promotion;

    /**
     * constructeur avec parametre
     * @param ID id du groupe
     * @param name nom du groupe
     * @param idPromotion id de la promotion du group
     */
    public GroupPromo(Long ID, String name, Long idPromotion) {
        this.ID = ID;
        this.name = name;
        this.promotion = idPromotion;
    }

    /**
     * constructeur par defaut
     */
    public GroupPromo() {
    }

    /**
     *
     * @return id du group
     */
    public Long getID() {
        return ID;
    }

    /**
     * set id du group
     * @param ID
     */
    public void setID(Long ID) {
        this.ID = ID;
    }

    /**
     *
     * @return nom du groupe
     */
    public String getName() {
        return name;
    }

    /**
     * set le nom du groupe
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return id promotion
     */
    public Long getPromotion() {
        return promotion;
    }

    /**
     * set le id de promo
     * @param promotion
     */
    public void setPromotion(Long promotion) {
        this.promotion = promotion;
    }

    /**
     *
     * @return String avec les infos du groupe : id et nom et promotion
     */

    @Override
    public String toString() {
        return "GroupPromo{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", promotion=" + promotion +
                '}';
    }
}
