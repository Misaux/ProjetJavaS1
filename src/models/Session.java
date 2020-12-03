package models;




import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Observable;

public class Session extends Observable {

    public enum State {PROGRESS,DONE,CANCELLED}

    public State state;
    private Long ID;
    private int week;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
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

    /**
     * constructeur par defaut
     */
    public Session() {
    }

    /**
     * set la session de la classe
     * @param session
     */
    public void setSession(Session session) {

        this.session = session;
    }

    /**
     *
     * @return id de la session
     */
    public Long getID() {
        return ID;
    }

    /**
     * set l'id de la session
     * @param ID
     */
    public void setID(Long ID) {
        this.ID = ID;
    }

    /**
     *
     * @return la semaine de la session dans le calendrier
     */
    public int getWeek() {
        return week;
    }

    /**
     * set la week pour la session
     * @param week
     */
    public void setWeek(int week) {
        this.week = week;
    }

    /**
     *
     * @return la date de la session avec l'heure de debut
     */
        public String getStartTime() {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return startTime.format(timeFormatter);
    }

    /**
     *
     * @return la date de la session avec l'heure de fin
     */
    public String getEndTime() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return endTime.format(timeFormatter);
    }

    /**
     *
     * @return la date de la session
     */
    public String getDate() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(dateFormatter);
    }

    /**
     * set l'heure de debut de session
     * @param startTime
     */
    public void setStartTime(String startTime) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.startTime = LocalTime.parse(startTime,timeFormatter);
    }
    /**
     * set l'heure de debut de fin
     * @param endTime
     */
    public void setEndTime(String endTime) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.endTime = LocalTime.parse(endTime,timeFormatter);
    }

    /**
     * set la date de session
     * @param date
     */
    public void setDate(String date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(date,dateFormatter);
    }

    /**
     *
     * @return l'etat de la session
     */
    public String getState() {
        return state.name();
    }

    /**
     * set l'etat de la session
     * @param state
     */
    public void setState(String state) {
        this.state = Session.State.valueOf(state);
    }

    /**
     *
     * @return l'id du cours de la session
     */
    public Long getID_course() {
        return ID_course;
    }

    /**
     * set l'id du cours de la session
     * @param ID_course
     */
    public void setID_course(Long ID_course) {
        this.ID_course = ID_course;
    }

    /**
     *
     * @return l'id du type de cours de la session
     */
    public Long getID_type() {
        return ID_type;
    }

    /**
     * set l'id du type de cours de la session
     * @param ID_type
     */
    public void setID_type(Long ID_type) {
        this.ID_type = ID_type;
    }


    /**
     *
     * @return string avec l'etat, l'id, la semaine,
     * l'heure de debut, l'heure de fin, la date, l'id du cours,
     * l'id du type de cours de la session
     */
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
