package beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SelectRController implements Serializable {
    private final Map<Float, Boolean> rSelected = new HashMap<>();

    public void toggle(float a) {
        boolean toggleOk = rSelected.keySet().stream()
                .map(k -> k != a ? rSelected.put(k, false) : rSelected.put(k, true))
                .reduce((value, combinedValue) -> combinedValue &= value).orElse(false);
        System.out.println(toggleOk);
    }

    public Optional<Float> getRSelected(){
        return rSelected.keySet().stream().filter(k -> rSelected.get(k)).findAny();
    }
}
