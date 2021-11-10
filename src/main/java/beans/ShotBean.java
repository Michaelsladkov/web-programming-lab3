package beans;

import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ShotBean implements Serializable {
    private float y;
    private float x;
    private float r;
    private boolean success;
    private LocalDateTime requestTime;
    private float processingTime; //seconds

    @ManagedProperty(value = "#{selectRController}")
    private SelectRController rController;

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void shot() {

    }

    boolean checkShot(float x, double y, float r) {
        if (x > 0) {
            if (y > 0) return false;
            else return x <= r && y >= -r/2;
        } else {
            if (y > 0) {
                return y <= x + r/2;
            } else {
                return x*x + y*y <= r*r/4;
            }
        }
    }

    public void setrController(SelectRController rController) {
        this.rController = rController;
    }

    public boolean isSuccess() {
        return success;
    }

    public String isSuccessString() {
        return success ? "ДА" : "НЕТ";
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public String getRequestTimeString() {
        return requestTime.format(DateTimeFormatter.ofPattern("dd-mm-yy HH:mm:ss"));
    }

    public float getProcessingTime() {
        return processingTime;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }
}
