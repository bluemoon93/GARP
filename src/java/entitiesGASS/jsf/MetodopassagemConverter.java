/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Metodopassagem;
import entitiesGASS.MetodopassagemPK;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import entitiesGASS.beans.MetodopassagemFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author asus
 */
public class MetodopassagemConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        MetodopassagemPK id = getId(string);
        MetodopassagemController controller = (MetodopassagemController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "metodopassagem");
        return controller.getJpaController().find(id);
    }

    MetodopassagemPK getId(String string) {
        MetodopassagemPK id = new MetodopassagemPK();
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
        id.setIDestado1(Integer.parseInt(params[0]));
        id.setIDestado2(Integer.parseInt(params[1]));
        id.setNIFassociacao(Integer.parseInt(params[2]));
        id.setIDmetodo(Integer.parseInt(params[3]));
        return id;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Metodopassagem) {
            Metodopassagem o = (Metodopassagem) object;
            MetodopassagemPK id = o.getMetodopassagemPK();
            if (id == null) {
                return "";
            }
            String delim = "#";
            String escape = "~";
            String IDestado1 = String.valueOf(id.getIDestado1());
            IDestado1 = IDestado1.replace(escape, escape + escape);
            IDestado1 = IDestado1.replace(delim, escape + delim);
            String IDestado2 = String.valueOf(id.getIDestado2());
            IDestado2 = IDestado2.replace(escape, escape + escape);
            IDestado2 = IDestado2.replace(delim, escape + delim);
            String NIFassociacao = String.valueOf(id.getNIFassociacao());
            NIFassociacao = NIFassociacao.replace(escape, escape + escape);
            NIFassociacao = NIFassociacao.replace(delim, escape + delim);
            String IDmetodo = String.valueOf(id.getIDmetodo());
            IDmetodo = IDmetodo.replace(escape, escape + escape);
            IDmetodo = IDmetodo.replace(delim, escape + delim);
            return IDestado1 + delim + IDestado2 + delim + NIFassociacao + delim + IDmetodo;
            // TODO: no setter methods were found in your primary key class
            //    entitiesGASS.MetodopassagemPK
            // and therefore getAsString() method could not be pre-generated.
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: entitiesGASS.Metodopassagem");
        }
    }
    
}
