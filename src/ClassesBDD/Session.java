package ClassesBDD;

import Cours.Cours;

import java.time.LocalDateTime;

public class Session {
    private int ID;
    private int week;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime date;
    private int ID_course;
    private int ID_type;

    public Session(int ID, int week, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime date, Course course, CourseType ct, State state) {
        this.ID = ID;
        this.week = week;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.ID_course = course.getID();
        this.ID_type = ct.getID();
        this.state = state;
    }

    private enum State {PROGRESS, DONE, CANCELLED}
    public State state;

}
