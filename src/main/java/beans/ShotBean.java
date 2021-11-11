package beans;

import dao.ShotDAO;
import util.ShotValidation;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ShotBean implements Serializable {
    private static final float NANOSECONDS_IN_SECOND = 1000000000f;
    private Float y;
    private Float x;
    private Float r;
    private boolean success;
    private LocalDateTime requestTime;
    private float processingTime; //seconds
    private String error = "";
    private final ShotDAO shotDAO = new ShotDAO();
    private String yStr;

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
        printR();
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
        printR();
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
        printR();
    }

    public boolean isR25() {
        return r25;
    }

    public void setR25(boolean r25) {
        this.r25 = true;
        r1 = false;
        r15 = false;
        r2 = false;
        r3 = false;
        printR();
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
        printR();
    }

    public Float getRSelected() {
        printR();
        if (r1) return 1f;
        if (r15) return 1.5f;
        if (r2) return 2f;
        if (r25) return 2.5f;
        if (r3) return 3f;
        return null;
    }

    private void printR() {
        System.out.println("r1: " + r1 + "  r15: " + r15 +
                "   r2: " + r2 + "   r25: " + r25 + "   r3: " + r3);
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public void setYStr(String yStr) {
        if (yStr.matches("[ \t]*")) {
            this.y = null;
            return;
        }
        try {
            this.y = Float.parseFloat(yStr);
        } catch (NumberFormatException e) {
            error = "Y Должен быть числом из отрезка (-3, 3)";
        }
    }

    public String getYStr() {
        return y != null ? y.toString() : "";
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
        long begin = System.nanoTime();
        System.out.println("shot method triggered");
        r = getRSelected();
        System.out.println(r);
        error = ShotValidation.validate(this);
        System.out.println(error);
        if (!error.equals("")) return;
        success = checkShot(x, y, r);
        requestTime = LocalDateTime.now();
        processingTime = (System.nanoTime() - begin)/NANOSECONDS_IN_SECOND;
        shotDAO.save(this);
    }

    boolean checkShot(float x, float y, float r) {
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

    public LocalDateTime getRequestTime() {
        return requestTime;
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

    @Override
    public int hashCode() {
        return x.intValue() * 12412434 + y.intValue() * 497 + r.intValue() * 51 + requestTime.getNano() +
                Float.valueOf(processingTime * NANOSECONDS_IN_SECOND).intValue();
    }
}
