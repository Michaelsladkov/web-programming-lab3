package beans;

import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SelectRController implements Serializable {

    private final Map<Float, Boolean> rSelected = new HashMap<>();

    public void toggle(ValueChangeEvent param) {
        String boxValue = param.getComponent().getId().split("-")[1];
        float a = Float.parseFloat(boxValue);
        System.out.println(a);
        if (!rSelected.containsKey(a)) rSelected.put(a, true);
        boolean toggleOk = rSelected.keySet().stream()
                .map(k -> k != a ? rSelected.put(k, false) : rSelected.put(k, true))
                .reduce((value, combinedValue) -> combinedValue &= value).orElse(false);
        System.out.println(toggleOk);
        System.out.println(rSelected);
    }

    public Optional<Float> getRSelected(){
        return rSelected.keySet().stream().filter(k -> rSelected.get(k)).findAny();
    }

    public boolean getValue(float key) {
        return rSelected.getOrDefault(key, false);
    }
}
