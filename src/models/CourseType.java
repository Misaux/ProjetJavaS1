package models;


public class CourseType{
    private Long ID;
    public enum Type {INTERACTIF,MAGISTRAL, TD,TP,PROJET,SOUTIEN}

    public Type type;

    /**
     * constructeur parametre
     * @param ID id du type de cours
     * @param type nom du type de cours
     */
    public CourseType(Long ID, Type type) {
        this.ID = ID;
        this.type = type;
    }

    /**
     * constructeur par defaut
     */
    public CourseType() {
    }

    /**
     *
     * @return id du type de cours
     */
    public Long getID() {
        return ID;
    }

    /**
     *
     * @param ID type de cours a mettre
     */
    public void setID(Long ID) {
        this.ID = ID;
    }

    /**
     *
     * @return string type de cours
     */
    public String getType() {
        return type.name();
    }

    /**
     * set le type de cours
     * @param type string type de cours
     */
    public void setType(String type) {
        this.type = Type.valueOf(type);
    }

    /**
     *
     * @return string avec le type de cours complet avec l'id et le nom du type de cours
     */
    @Override
    public String toString() {
        return "CourseType{" +
                "ID=" + ID +
                ", type=" + type +
                '}';
    }
}
