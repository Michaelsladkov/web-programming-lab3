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
    private Float xToR;
    private Float ytoR;
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

    private void falseAllR() {
        r1 = r15 = r2 = r25 = r3 = false;
    }

    public boolean isR1() {
        return r1;
    }

    public void setR1(boolean r1) {
        if (!r1) return;
        falseAllR();
        this.r1 = true;
        printR();
    }


    public boolean isR15() {
        return r15;
    }

    public void setR15(boolean r15) {
        if (!r15) return;
        falseAllR();
        this.r15 = true;
        printR();
    }

    public boolean isR2() {
        return r2;
    }

    public void setR2(boolean r2) {
        if (!r2) return;
        falseAllR();
        this.r2 = true;
        printR();
    }

    public boolean isR25() {
        return r25;
    }

    public void setR25(boolean r25) {
        if (!r25) return;
        falseAllR();
        this.r25 = true;
        printR();
    }

    public boolean isR3() {
        return r3;
    }

    public void setR3(boolean r3) {
        if (!r3) return;
        falseAllR();
        this.r3 = true;
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
            y = null;
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
        Float tmpX = x;
        Float tmpY = y;
        if (xToR != null && ytoR != null) {
            x = new Float(xToR * r);
            y = new Float(ytoR * r);
        }
        success = checkShot(x, y, r);
        requestTime = LocalDateTime.now();
        processingTime = (System.nanoTime() - begin)/NANOSECONDS_IN_SECOND;
        shotDAO.save(this);
        x = tmpX;
        y = tmpY;
        xToR = null;
        ytoR = null;
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

    public Float getXToR() {
        return xToR;
    }

    public void setXToR(Float xToR) {
        this.xToR = xToR;
        if (xToR == null) return;
        Float r = getRSelected();
        if (r == null) {
            error = "Выберите R";
            return;
        }
        System.out.println("xtr");
    }

    public Float getYtoR() {
        return ytoR;
    }

    public void setYtoR(Float ytoR) {
        this.ytoR = ytoR;
        if (ytoR == null) return;
        Float r = getRSelected();
        if (r == null) {
            error = "Выберите R";
            return;
        }
    }
}
