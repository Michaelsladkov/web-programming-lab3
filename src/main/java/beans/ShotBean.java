package beans;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean (name = "shotBean")
@SessionScoped
public class ShotBean implements Serializable {
    private double y;

    public double getY() {
        return y;
    }

    public void setY(double y) {
        System.out.println(y);
        this.y = y;
    }
}
