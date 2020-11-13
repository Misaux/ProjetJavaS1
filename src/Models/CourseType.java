package Models;

public class CourseType {
    private int ID;
    private enum Type {INTERACTIF,MAGISTRAL, TD,TP,PROJET,SOUTIEN}

    public Type type;

    public CourseType(int ID, Type type) {
        this.ID = ID;
        this.type = type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
