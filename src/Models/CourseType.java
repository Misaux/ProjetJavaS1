package Models;

public class CourseType {
    private Long ID;
    private enum Type {INTERACTIF,MAGISTRAL, TD,TP,PROJET,SOUTIEN}

    public Type type;

    public CourseType(Long ID, Type type) {
        this.ID = ID;
        this.type = type;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
