package beans;

import dao.ShotDAO;
import util.ShotValidation;

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

    private boolean r1 = false;
    private boolean r15 = false;
    private boolean r2 = false;
    private boolean r25 = false;
    private boolean r3 = false;


    public boolean isR1() {
        return r1;
    }

    public void setR1(boolean r1) {
        this.r1 = r1;
        r15 = false;
        r2 = false;
        r25 = false;
        r3 = false;
    }

    public boolean isR15() {
        return r15;
    }

    public void setR15(boolean r15) {
        this.r15 = r15;
        r1 = false;
        r2 = false;
        r25 = false;
        r3 = false;
    }

    public boolean isR2() {
        return r2;
    }

    public void setR2(boolean r2) {
        this.r2 = r2;
        r1 = false;
        r15 = false;
        r25 = false;
        r3 = false;
    }

    public boolean isR25() {
        return r25;
    }

    public void setR25(boolean r25) {
        this.r25 = r25;
        r1 = false;
        r15 = false;
        r2 = false;
        r3 = false;
    }

    public boolean isR3() {
        return r3;
    }

    public void setR3(boolean r3) {
        this.r3 = r3;
        r1 = false;
        r15 = false;
        r2 = false;
        r25 = false;
    }

    public float getRSelected() {
        if (r1) return 1f;
        if (r15) return 1.5f;
        if (r2) return 2f;
        if (r25) return 2.5f;
        return 3f;
    }

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
        r = getRSelected();
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
