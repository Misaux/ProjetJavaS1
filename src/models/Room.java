package models;

import java.util.Observable;

public class Room  extends Observable {
    private Long ID;
    private String name;
    private int capacity;
    private Long Id_site;

    /**
     * Constructeur avec parametres
      * @param ID id de la room
     * @param capacity taille de la room
     * @param idSite id du site ou est la room
     * @param name nom de la room
     */
    public Room(Long ID, int capacity, Long idSite, String name) {
        this.ID = ID;
        this.capacity = capacity;
        this.Id_site = idSite;
        this.name = name;
    }

    /**
     * constructeur par defaut
     */
    public Room() {
    }

    /**
     *
     * @return l'id de la room
     */
    public Long getID() {
        return ID;
    }

    /**
     * set l'id de la room
     * @param ID
     */
    public void setID(Long ID) {
        this.ID = ID;
    }

    /**
     *
     * @return la taille de la room
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * set la taille de la room
     * @param capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     *
     * @return le nom de la room
     */
    public String getName() {
        return name;
    }

    /**
     * set le nom de la room
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return l'id du site
     */
    public Long getId_site() {
        return Id_site;
    }

    /**
     * set l'id du site de la room
     * @param id_site
     */
    public void setId_site(Long id_site) {
        Id_site = id_site;
    }

    /**
     *
     * @return string avec id, le nom, la capacite et le site de la salle
     */
    @Override
    public String toString() {
        return "Room{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", Id_site=" + Id_site +
                '}';
    }
}
