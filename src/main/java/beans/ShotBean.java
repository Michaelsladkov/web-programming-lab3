package beans;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@ManagedBean (name = "shotBean")
@SessionScoped
public class ShotBean implements Serializable {
    private double y;
    private double x;
    private Map<Float, Boolean> rValues;
    private boolean success;
    private LocalDateTime requestTime;
    private double processingTime; //seconds

    @ManagedProperty(value = "#{selectRController}")
    private SelectRController rController;

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void shot() {
        rValues = rController.getRSelected();
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

    public String getRequestTime() {
        return requestTime.format(DateTimeFormatter.ofPattern("dd-mm-yy HH:mm:ss"));
    }

    public double getProcessingTime() {
        return processingTime;
    }
}
