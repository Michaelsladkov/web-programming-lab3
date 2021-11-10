package beans;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@ManagedBean (name = "selectRController")
@SessionScoped
public class SelectRController implements Serializable {
    private final Map<Float, Boolean> rSelected = new HashMap<>();

    public void toggle(float a) {
        if(!rSelected.containsKey(a)){
            rSelected.put(a, true);
        } else {
            rSelected.put(a, !rSelected.get(a));
        }
        System.out.println(rSelected);
    }

    public boolean getRSelected(float key){
        return rSelected.containsKey(key) && rSelected.get(key);
    }

    public Map<Float, Boolean> getRSelected() {
        return rSelected;
    }
}
