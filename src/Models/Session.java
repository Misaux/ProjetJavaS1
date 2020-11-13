package Models;



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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
