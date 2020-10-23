package Cours;

public class Cours {

    public enum Campus {LYON, PARIS}
    public enum Batiment {A, B, C, D, E}
    public enum TypeCourse {PRESENTIEL, DISTANCIEL}
    public enum Course {MATHS,PHYSIQUE,ELEC ,INFO}
    public enum Groupe {A,B,C,}

    private final int room;

    private final Groupe groupe;
    private final TypeCourse type;
    private final Batiment bat;
    private final Campus campus;
    public final Course matiere;


    public Cours(Campus campus, Batiment bat, int room, TypeCourse type, Course matiere, Groupe groupe) {
        this.campus = campus;
        this.bat = bat;
        this.room = room;
        this.type = type;
        this.matiere = matiere;
        this.groupe = groupe;
    }

    @Override
    public String toString() {
        return "Cours{" +
                "room=" + room +
                ", groupe=" + groupe +
                ", type=" + type +
                ", bat=" + bat +
                ", campus=" + campus +
                ", matiere=" + matiere +
                '}';
    }
}
