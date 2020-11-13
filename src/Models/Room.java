package Models;

public class Room {
    private Long ID;
    private int capacity;
    private Long Id_site;

    public Room(Long ID, int capacity, Site site) {
        this.ID = ID;
        this.capacity = capacity;
        this.Id_site = site.getID();
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
}
