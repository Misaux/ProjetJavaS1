package Models;

import java.util.Observable;

public class Room  extends Observable {
    private Long ID;
    private String name;
    private int capacity;
    private Long Id_site;

    public Room(Long ID, int capacity, Long idSite, String name) {
        this.ID = ID;
        this.capacity = capacity;
        this.Id_site = idSite;
        this.name = name;
    }

    public Room() {
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId_site() {
        return Id_site;
    }

    public void setId_site(Long id_site) {
        Id_site = id_site;
    }

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
