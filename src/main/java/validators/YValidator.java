package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class YValidator implements Validator {

    private static final double LOW_BORDER = -3d;
    private static final double TOP_BORDER = 3d;
    private static final String NOT_A_NUMBER = "это не число";
    private static final String WRONG_INTERVAL = "Число должно быть в интервале от " + LOW_BORDER + " до " + TOP_BORDER;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String valueStr = (String) o;
        double value;
        try {
            value = Double.parseDouble(valueStr);
        } catch (NumberFormatException e) {
            FacesMessage msg = new FacesMessage(NOT_A_NUMBER);
            throw new ValidatorException(msg);
        }
        if (value <= LOW_BORDER || value >= TOP_BORDER) {
            FacesMessage msg = new FacesMessage(WRONG_INTERVAL);
            throw new ValidatorException(msg);
        }
    }
}
