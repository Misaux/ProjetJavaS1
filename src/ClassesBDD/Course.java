package ClassesBDD;

public class Course {
    private int ID;
    private String name;


    public Course(int ID, String name) {
        this.ID = ID;
        this.name = "DS de " + name;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                '}';
    }
}
