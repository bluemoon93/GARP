/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Cargo;
import entitiesGASS.CargoPK;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import entitiesGASS.beans.CargoFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author asus
 */
public class CargoConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        CargoPK id = getId(string);
        CargoController controller = (CargoController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "cargo");
        return controller.getJpaController().find(id);
    }

    CargoPK getId(String string) {
        CargoPK id = new CargoPK();
        String[] params = new String[2];
        int p = 0;
        int grabStart = 0;
        String delim = "#";
        String escape = "~";
        Pattern pattern = Pattern.compile(escape + "*" + delim);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            String found = matcher.group();
            if (found.length() % 2 == 1) {
                params[p] = string.substring(grabStart, matcher.start());
                p++;
                grabStart = matcher.end();
            }
        }
        if (p != params.length - 1) {
            throw new IllegalArgumentException("string " + string + " is not in expected format. expected 2 ids delimited by " + delim);
        }
        params[p] = string.substring(grabStart);
        for (int i = 0; i < params.length; i++) {
            params[i] = params[i].replace(escape + delim, delim);
            params[i] = params[i].replace(escape + escape, escape);
        }
        id.setNome(params[0]);
        id.setGrupo(params[1]);
        return id;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Cargo) {
            Cargo o = (Cargo) object;
            CargoPK id = o.getCargoPK();
            if (id == null) {
                return "";
            }
            String delim = "#";
            String escape = "~";
            String nome = id.getNome();
            nome = nome == null ? "" : nome.replace(escape, escape + escape);
            nome = nome.replace(delim, escape + delim);
            String grupo = id.getGrupo();
            grupo = grupo == null ? "" : grupo.replace(escape, escape + escape);
            grupo = grupo.replace(delim, escape + delim);
            return nome + delim + grupo;
            // TODO: no setter methods were found in your primary key class
            //    entitiesGASS.CargoPK
            // and therefore getAsString() method could not be pre-generated.
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: entitiesGASS.Cargo");
        }
    }
    
}
