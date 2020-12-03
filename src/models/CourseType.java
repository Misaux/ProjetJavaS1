package models;

import java.util.Observable;

public class CourseType extends Observable {
    private Long ID;
    public enum Type {INTERACTIF,MAGISTRAL, TD,TP,PROJET,SOUTIEN}

    public Type type;

    public CourseType(Long ID, Type type) {
        this.ID = ID;
        this.type = type;
    }

    public CourseType() {
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getType() {
        return type.name();
    }

    public void setType(String type) {
        this.type = Type.valueOf(type);
    }

    @Override
    public String toString() {
        return "CourseType{" +
                "ID=" + ID +
                ", type=" + type +
                '}';
    }
}
