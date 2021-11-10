package beans;

import orm.dao.ShotDAO;
import orm.util.ShotValidation;

import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ShotBean implements Serializable {
    private Float y;
    private Float x;
    private Float r;
    private boolean success;
    private LocalDateTime requestTime;
    private float processingTime; //seconds
    private String error = "";
    private final ShotDAO shotDAO = new ShotDAO();

    @ManagedProperty(value = "#{selectRController}")
    private SelectRController rController;

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getX() {
        return x;
    }

    public String getXString() {
        if (x == null) return "null";
        return x.toString();
    }

    public void setX(Float x) {
        this.x = x;
    }

    public void shot() {
        System.out.println(rController == null);
        if (rController == null) return;
        r = rController.getRSelected().orElse(1f);
        System.out.println("shot method triggered");
        error = ShotValidation.validate(this);
        System.out.println(error);
        if (error.equals("")) {
            shotDAO.save(this);
        }
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
        return requestTime.format(DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss"));
    }

    public float getProcessingTime() {
        return processingTime;
    }

    public Float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public String getError() {
        System.out.println("error requested: " + error);
        return error;
    }
}
