/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Evento;
import entitiesGASS.EventoPK;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import entitiesGASS.beans.EventoFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author asus
 */
public class EventoConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        EventoPK id = getId(string);
        EventoController controller = (EventoController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "evento");
        return controller.getJpaController().find(id);
    }

    EventoPK getId(String string) {
        EventoPK id = new EventoPK();
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
        id.setIDEvento(Integer.parseInt(params[0]));
        id.setNIFassoc(Integer.parseInt(params[1]));
        return id;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Evento) {
            Evento o = (Evento) object;
            EventoPK id = o.getEventoPK();
            if (id == null) {
                return "";
            }
            String delim = "#";
            String escape = "~";
            String IDEvento = String.valueOf(id.getIDEvento());
            IDEvento = IDEvento.replace(escape, escape + escape);
            IDEvento = IDEvento.replace(delim, escape + delim);
            String NIFassoc = String.valueOf(id.getNIFassoc());
            NIFassoc = NIFassoc.replace(escape, escape + escape);
            NIFassoc = NIFassoc.replace(delim, escape + delim);
            return IDEvento + delim + NIFassoc;
            // TODO: no setter methods were found in your primary key class
            //    entitiesGASS.EventoPK
            // and therefore getAsString() method could not be pre-generated.
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: entitiesGASS.Evento");
        }
    }
    
}
