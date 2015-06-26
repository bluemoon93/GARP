/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Participa;
import entitiesGASS.ParticipaPK;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import entitiesGASS.beans.ParticipaFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author asus
 */
public class ParticipaConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        ParticipaPK id = getId(string);
        ParticipaController controller = (ParticipaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "participa");
        return controller.getJpaController().find(id);
    }

    ParticipaPK getId(String string) {
        ParticipaPK id = new ParticipaPK();
        String[] params = new String[3];
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
            throw new IllegalArgumentException("string " + string + " is not in expected format. expected 3 ids delimited by " + delim);
        }
        params[p] = string.substring(grabStart);
        for (int i = 0; i < params.length; i++) {
            params[i] = params[i].replace(escape + delim, delim);
            params[i] = params[i].replace(escape + escape, escape);
        }
        id.setIDEvento(Integer.parseInt(params[0]));
        id.setEmail(params[1]);
        id.setNIFasso(Integer.parseInt(params[2]));
        return id;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Participa) {
            Participa o = (Participa) object;
            ParticipaPK id = o.getParticipaPK();
            if (id == null) {
                return "";
            }
            String delim = "#";
            String escape = "~";
            String IDEvento = String.valueOf(id.getIDEvento());
            IDEvento = IDEvento.replace(escape, escape + escape);
            IDEvento = IDEvento.replace(delim, escape + delim);
            String email = id.getEmail();
            email = email == null ? "" : email.replace(escape, escape + escape);
            email = email.replace(delim, escape + delim);
            String NIFasso = String.valueOf(id.getNIFasso());
            NIFasso = NIFasso.replace(escape, escape + escape);
            NIFasso = NIFasso.replace(delim, escape + delim);
            return IDEvento + delim + email + delim + NIFasso;
            // TODO: no setter methods were found in your primary key class
            //    entitiesGASS.ParticipaPK
            // and therefore getAsString() method could not be pre-generated.
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: entitiesGASS.Participa");
        }
    }
    
}
