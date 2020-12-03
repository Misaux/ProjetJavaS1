package models;

public class Course  {
    private Long ID;
    private String name;

    /**
     *constructeur par defaut
     */
    public Course() {
    }

    /**
     * constructeur parametre
     * @param ID id du cours
     * @param name nom du cours
     */
    public Course(Long ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    /**
     *
     * @return id course
     */
    public Long getID() {
        return ID;
    }

    /**
     *
     * @return course name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param ID id course
     */
    public void setID(Long ID) {
        this.ID = ID;
    }

    /**
     *
     * @param name nom du course
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return string avec le nom et l'id du cours
     */
    @Override
    public String toString() {
        return "Course{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                '}';
    }
}
