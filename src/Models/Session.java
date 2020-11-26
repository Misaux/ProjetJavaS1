package Models;



import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Observable;

public class Session extends Observable {

    public enum State {PROGRESS, DONE, CANCELLED}

    public State state;
    private Long ID;
    private int week;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date; // Regarder TP prof pour date !!!!
    private Long ID_course;
    private Long ID_type;
    private Session session;

    public Session(Long ID, int week, String startTime, String endTime, String date, Long course, Long ct, State state) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.ID = ID;
        this.week = week;
        this.ID_course = course;
        this.ID_type = ct;
        this.state = state;
        this.date= LocalDate.parse(date,dateFormatter);
        this.startTime= LocalTime.parse(startTime,timeFormatter);
        this.endTime = LocalTime.parse(endTime,timeFormatter);
    }


    public Session() {
    }

    public void setSession(Session session) {

        this.session = session;
        setChanged();
        notifyObservers(this.session);
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

        public String getStartTime() {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return startTime.format(timeFormatter);
    }

    public String getEndTime() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return endTime.format(timeFormatter);
    }

    public String getDate() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(dateFormatter);
    }

    public void setStartTime(String startTime) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.startTime = LocalTime.parse(startTime,timeFormatter);
    }

    public void setEndTime(String endTime) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.endTime = LocalTime.parse(endTime,timeFormatter);
    }

    public void setDate(String date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(date,dateFormatter);
    }

    public String getState() {
        return state.name();
    }

    public void setState(String state) {
        this.state = State.valueOf(state);
    }

    public Long getID_course() {
        return ID_course;
    }

    public void setID_course(Long ID_course) {
        this.ID_course = ID_course;
    }

    public Long getID_type() {
        return ID_type;
    }

    public void setID_type(Long ID_type) {
        this.ID_type = ID_type;
    }

    @Override
    public String toString() {
        return "Session{" +
                "state=" + state +
                ", ID=" + ID +
                ", week=" + week +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", date=" + date +
                ", ID_course=" + ID_course +
                ", ID_type=" + ID_type +
                '}';
    }
}
