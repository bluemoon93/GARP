/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Associacao;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author asus
 */
public class AssociacaoConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        AssociacaoController controller = (AssociacaoController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "associacao");
        return controller.getJpaController().find(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Associacao) {
            Associacao o = (Associacao) object;
            return o.getNif() == null ? "" : o.getNif().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: entitiesGASS.Associacao");
        }
    }
    
}
