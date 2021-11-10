package beans;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeBean {
    private static final DecimalFormat TWO_DIGITS_FORMAT = new DecimalFormat("00");

    public int getYear() {
        return LocalDateTime.now().getYear();
    }

    public int getMonth() {
        return LocalDateTime.now().getMonthValue();
    }

    public int getDayOfMonth() {
        return LocalDateTime.now().getDayOfMonth();
    }

    public String getDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public String getHours() {
        return TWO_DIGITS_FORMAT.format(LocalDateTime.now().getHour());
    }

    public String getMinutes() {
        return TWO_DIGITS_FORMAT.format(LocalDateTime.now().getMinute());
    }

    public String getSeconds() {
        return TWO_DIGITS_FORMAT.format(LocalDateTime.now().getSecond());
    }
}
