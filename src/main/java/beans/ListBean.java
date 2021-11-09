package beans;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean (name = "listBean")
@SessionScoped
public class ListBean implements Serializable {
}
