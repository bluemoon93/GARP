/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Ocupa;
import entitiesGASS.OcupaPK;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import entitiesGASS.beans.OcupaFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author asus
 */
public class OcupaConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        OcupaPK id = getId(string);
        OcupaController controller = (OcupaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "ocupa");
        return controller.getJpaController().find(id);
    }

    OcupaPK getId(String string) {
        OcupaPK id = new OcupaPK();
        String[] params = new String[4];
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
            throw new IllegalArgumentException("string " + string + " is not in expected format. expected 4 ids delimited by " + delim);
        }
        params[p] = string.substring(grabStart);
        for (int i = 0; i < params.length; i++) {
            params[i] = params[i].replace(escape + delim, delim);
            params[i] = params[i].replace(escape + escape, escape);
        }
        id.setNomeC(params[0]);
        id.setGrupoC(params[1]);
        id.setNIFsocio(Integer.parseInt(params[2]));
        id.setNIFAssociacao(Integer.parseInt(params[3]));
        return id;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Ocupa) {
            Ocupa o = (Ocupa) object;
            OcupaPK id = o.getOcupaPK();
            if (id == null) {
                return "";
            }
            String delim = "#";
            String escape = "~";
            String nomeC = id.getNomeC();
            nomeC = nomeC == null ? "" : nomeC.replace(escape, escape + escape);
            nomeC = nomeC.replace(delim, escape + delim);
            String grupoC = id.getGrupoC();
            grupoC = grupoC == null ? "" : grupoC.replace(escape, escape + escape);
            grupoC = grupoC.replace(delim, escape + delim);
            String NIFsocio = String.valueOf(id.getNIFsocio());
            NIFsocio = NIFsocio.replace(escape, escape + escape);
            NIFsocio = NIFsocio.replace(delim, escape + delim);
            String NIFAssociacao = String.valueOf(id.getNIFAssociacao());
            NIFAssociacao = NIFAssociacao.replace(escape, escape + escape);
            NIFAssociacao = NIFAssociacao.replace(delim, escape + delim);
            return nomeC + delim + grupoC + delim + NIFsocio + delim + NIFAssociacao;
            // TODO: no setter methods were found in your primary key class
            //    entitiesGASS.OcupaPK
            // and therefore getAsString() method could not be pre-generated.
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: entitiesGASS.Ocupa");
        }
    }
    
}
