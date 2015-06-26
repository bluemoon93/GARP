/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Pagamentoquota;
import entitiesGASS.PagamentoquotaPK;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import entitiesGASS.beans.PagamentoquotaFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author asus
 */
public class PagamentoquotaConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        PagamentoquotaPK id = getId(string);
        PagamentoquotaController controller = (PagamentoquotaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "pagamentoquota");
        return controller.getJpaController().find(id);
    }

    PagamentoquotaPK getId(String string) {
        PagamentoquotaPK id = new PagamentoquotaPK();
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
        id.setNIFsocio(Integer.parseInt(params[0]));
        id.setNIFAssociacao(Integer.parseInt(params[1]));
        id.setAno(Integer.parseInt(params[2]));
        return id;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Pagamentoquota) {
            Pagamentoquota o = (Pagamentoquota) object;
            PagamentoquotaPK id = o.getPagamentoquotaPK();
            if (id == null) {
                return "";
            }
            String delim = "#";
            String escape = "~";
            String NIFsocio = String.valueOf(id.getNIFsocio());
            NIFsocio = NIFsocio.replace(escape, escape + escape);
            NIFsocio = NIFsocio.replace(delim, escape + delim);
            String NIFAssociacao = String.valueOf(id.getNIFAssociacao());
            NIFAssociacao = NIFAssociacao.replace(escape, escape + escape);
            NIFAssociacao = NIFAssociacao.replace(delim, escape + delim);
            String ano = String.valueOf(id.getAno());
            ano = ano.replace(escape, escape + escape);
            ano = ano.replace(delim, escape + delim);
            return NIFsocio + delim + NIFAssociacao + delim + ano;
            // TODO: no setter methods were found in your primary key class
            //    entitiesGASS.PagamentoquotaPK
            // and therefore getAsString() method could not be pre-generated.
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: entitiesGASS.Pagamentoquota");
        }
    }
    
}
