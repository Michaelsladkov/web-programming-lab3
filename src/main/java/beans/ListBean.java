package beans;

import orm.models.Shot;

import java.io.Serializable;
import java.util.ArrayList;

public class ListBean implements Serializable {
    private ArrayList<Shot> shots = new ArrayList();

    public ArrayList<Shot> getShots() {
        return shots;
    }
}
