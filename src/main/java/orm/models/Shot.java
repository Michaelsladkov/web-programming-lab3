package orm.models;

import beans.ShotBean;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table (name = "WEB3_SHOTS")
public class Shot {
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public float getProcessingTime() {
        return processingTime;
    }
}
