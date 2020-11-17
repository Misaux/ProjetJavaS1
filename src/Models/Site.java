package Models;

public class Site {
    private Long ID;
    private String name;

    public Site(Long ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public Site() {
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
        return "Site{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                '}';
    }
}
