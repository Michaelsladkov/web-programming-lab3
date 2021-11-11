package beans;

import dao.ShotDAO;
import models.Shot;

import java.io.Serializable;
import java.util.List;

public class ListBean implements Serializable {
    ShotDAO shotDAO = new ShotDAO();
    public List<Shot> getShots() {
        return shotDAO.findAll();
    }

    public void clear() {
        shotDAO.clear();
    }
}
