package util;

import beans.ShotBean;

public class ShotValidation {
    private static final String VALUE_NOT_SPECIFIED = "Необходимо установить значение ";
    private static float Y_FROM = -3;
    private static float Y_TO = 3;
    public static String validate(ShotBean shot) {
        if (shot.getR() == null) {
            return VALUE_NOT_SPECIFIED + "R";
        }
        if (shot.getY() == null && shot.getXToR() != null && shot.getYtoR()!= null) {
            System.out.println(shot.getXToR() + "\t" + shot.getYtoR());
            return "";
        }
        if (shot.getY() == null) {
            return "Введите значение Y";
        }
        if (shot.getY() < Y_FROM || shot.getY() > Y_TO) {
            return "Y должен быть в интервале (-3, 3)";
        }
        if (shot.getX() == null) {
            return VALUE_NOT_SPECIFIED + "X";
        }
        return "";
    }
}
