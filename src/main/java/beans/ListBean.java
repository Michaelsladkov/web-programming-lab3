package beans;

import dao.ShotDAO;
import models.Shot;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class ListBean implements Serializable {
    ShotDAO shotDAO = new ShotDAO();
    public List<Shot> getShots() {
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(true);
        String sessionIdStr = session.getId();
        long sessionId = sessionIdStr.hashCode();
        return shotDAO.findAll().stream().filter(o -> o.getSessionId() == sessionId).collect(Collectors.toList());
    }

    public void clear() {
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(true);
        String sessionIdStr = session.getId();
        long sessionId = sessionIdStr.hashCode();
        shotDAO.clear(sessionId);
    }
}
