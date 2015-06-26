/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Estado;
import entitiesGASS.EstadoPK;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import entitiesGASS.beans.EstadoFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author asus
 */
public class EstadoConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        EstadoPK id = getId(string);
        EstadoController controller = (EstadoController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "estado");
        return controller.getJpaController().find(id);
    }

    EstadoPK getId(String string) {
        EstadoPK id = new EstadoPK();
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
        id.setIDestado(Integer.parseInt(params[0]));
        id.setNIFassociacao(Integer.parseInt(params[1]));
        return id;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Estado) {
            Estado o = (Estado) object;
            EstadoPK id = o.getEstadoPK();
            if (id == null) {
                return "";
            }
            String delim = "#";
            String escape = "~";
            String IDestado = String.valueOf(id.getIDestado());
            IDestado = IDestado.replace(escape, escape + escape);
            IDestado = IDestado.replace(delim, escape + delim);
            String NIFassociacao = String.valueOf(id.getNIFassociacao());
            NIFassociacao = NIFassociacao.replace(escape, escape + escape);
            NIFassociacao = NIFassociacao.replace(delim, escape + delim);
            return IDestado + delim + NIFassociacao;
            // TODO: no setter methods were found in your primary key class
            //    entitiesGASS.EstadoPK
            // and therefore getAsString() method could not be pre-generated.
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: entitiesGASS.Estado");
        }
    }
    
}
