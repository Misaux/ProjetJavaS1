package models;

import java.util.Observable;

public class Site extends Observable {
    private Long ID;
    private String name;

    /**
     * constructeur de la classe avec des parametres
     * @param ID du site
     * @param name du site
     */
    public Site(Long ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    /**
     * constructeur par defaut
     */
    public Site() {
    }

    /**
     *
     * @return l'id du site
     */
    public Long getID() {
        return ID;
    }

    /**
     * set l'id du site
     * @param ID
     */
    public void setID(Long ID) {
        this.ID = ID;
    }

    /**
     *
     * @return le nom du site
     */
    public String getName() {
        return name;
    }

    /**
     * set le nom du site
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return string avec l'id et le nom du site
     */
    @Override
    public String toString() {
        return "Site{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                '}';
    }
}
