/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Pagamentoevento;
import entitiesGASS.PagamentoeventoPK;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import entitiesGASS.beans.PagamentoeventoFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author asus
 */
public class PagamentoeventoConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        PagamentoeventoPK id = getId(string);
        PagamentoeventoController controller = (PagamentoeventoController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "pagamentoevento");
        return controller.getJpaController().find(id);
    }

    PagamentoeventoPK getId(String string) {
        PagamentoeventoPK id = new PagamentoeventoPK();
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
        id.setNIFAssociacao(Integer.parseInt(params[0]));
        id.setIDEvento(Integer.parseInt(params[1]));
        id.setEmail(params[2]);
        return id;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Pagamentoevento) {
            Pagamentoevento o = (Pagamentoevento) object;
            PagamentoeventoPK id = o.getPagamentoeventoPK();
            if (id == null) {
                return "";
            }
            String delim = "#";
            String escape = "~";
            String NIFAssociacao = String.valueOf(id.getNIFAssociacao());
            NIFAssociacao = NIFAssociacao.replace(escape, escape + escape);
            NIFAssociacao = NIFAssociacao.replace(delim, escape + delim);
            String IDEvento = String.valueOf(id.getIDEvento());
            IDEvento = IDEvento.replace(escape, escape + escape);
            IDEvento = IDEvento.replace(delim, escape + delim);
            String email = id.getEmail();
            email = email == null ? "" : email.replace(escape, escape + escape);
            email = email.replace(delim, escape + delim);
            return NIFAssociacao + delim + IDEvento + delim + email;
            // TODO: no setter methods were found in your primary key class
            //    entitiesGASS.PagamentoeventoPK
            // and therefore getAsString() method could not be pre-generated.
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: entitiesGASS.Pagamentoevento");
        }
    }
    
}
