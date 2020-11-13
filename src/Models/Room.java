package Models;

public class Room {
    private int ID;
    private int capacity;
    private int Id_site;

    public Room(int ID, int capacity, Site site) {
        this.ID = ID;
        this.capacity = capacity;
        this.Id_site = site.getID();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
