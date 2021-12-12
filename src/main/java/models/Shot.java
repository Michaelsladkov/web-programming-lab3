package models;

import beans.ShotBean;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table (name = "WEB3_SHOTS")
public class Shot implements Comparable<Shot> {
    @Id
    private int id;

    @Column(name = "x")
    private float x;

    @Column(name = "y")
    private float y;

    @Column(name = "r")
    private float r;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "execution_time")
    private float processingTime;

    @Column(name = "session_id")
    private long sessionId;

    @Column(name = "success")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean success;

    public Shot(){}

    public Shot(ShotBean bean) {
        this.id = bean.hashCode();
        this.x = bean.getX();
        this.y = bean.getY();
        this.r = bean.getR();
        this.dateTime = bean.getRequestTime();
        this.processingTime = bean.getProcessingTime();
        this.success = bean.isSuccess();
        this.sessionId = bean.getSessionId();
    }

    public long getSessionId() {
        return sessionId;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getR() {
        return r;
    }

    public boolean isSuccess() {return success;}

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String isSuccessString() {
        return success ? "ДА" : "НЕТ";
    }

    public String getRequestTimeString() {
        return dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss"));
    }

    public float getProcessingTime() {
        return processingTime;
    }

    public String getProcessingTimeString() {return String.format("%f", processingTime);}

    @Override
    public String toString() {
        return "Shot hibernate model\n" +
                "\tid: " + id + "\n" +
                "\tx: " + x + "\n" +
                "\ty: " + y + "\n" +
                "\tr: " + r + "\n" +
                "\tdate-time: " + dateTime + "\n" +
                "\tprocessing-time: " + processingTime + "\n" +
                "\tsuccess: " + success;
    }

    @Override
    public int compareTo(Shot o) {
        return dateTime.compareTo(o.getDateTime());
    }
}
