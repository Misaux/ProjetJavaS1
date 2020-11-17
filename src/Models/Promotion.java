package Models;

public class Promotion {
    private Long ID;
    private String name;

    public Promotion(Long id, String name) {
        this.ID = id;
        this.name = name;
    }

    public Promotion() {
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
