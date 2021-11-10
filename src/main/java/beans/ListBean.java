package beans;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;

@ManagedBean (name = "listBean")
@SessionScoped
public class ListBean implements Serializable {
    private ArrayList<ShotBean> shots;

    public ArrayList<ShotBean> getShots() {
        return shots;
    }
}
