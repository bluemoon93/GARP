/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Socio;
import entitiesGASS.SocioPK;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import entitiesGASS.beans.SocioFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author asus
 */
public class SocioConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        SocioPK id = getId(string);
        SocioController controller = (SocioController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "socio");
        return controller.getJpaController().find(id);
    }

    SocioPK getId(String string) {
        SocioPK id = new SocioPK();
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
        id.setNif(Integer.parseInt(params[0]));
        id.setNIFAssociacao(Integer.parseInt(params[1]));
        return id;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Socio) {
            Socio o = (Socio) object;
            SocioPK id = o.getSocioPK();
            if (id == null) {
                return "";
            }
            String delim = "#";
            String escape = "~";
            String nif = String.valueOf(id.getNif());
            nif = nif.replace(escape, escape + escape);
            nif = nif.replace(delim, escape + delim);
            String NIFAssociacao = String.valueOf(id.getNIFAssociacao());
            NIFAssociacao = NIFAssociacao.replace(escape, escape + escape);
            NIFAssociacao = NIFAssociacao.replace(delim, escape + delim);
            return nif + delim + NIFAssociacao;
            // TODO: no setter methods were found in your primary key class
            //    entitiesGASS.SocioPK
            // and therefore getAsString() method could not be pre-generated.
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: entitiesGASS.Socio");
        }
    }
    
}
