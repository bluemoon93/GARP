/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Detalhebotao;
import entitiesGASS.DetalhebotaoPK;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import entitiesGASS.beans.DetalhebotaoFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author asus
 */
public class DetalhebotaoConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        DetalhebotaoPK id = getId(string);
        DetalhebotaoController controller = (DetalhebotaoController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "detalhebotao");
        return controller.getJpaController().find(id);
    }

    DetalhebotaoPK getId(String string) {
        DetalhebotaoPK id = new DetalhebotaoPK();
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
        id.setIDcaixa(Integer.parseInt(params[0]));
        id.setIDbotao(Integer.parseInt(params[1]));
        id.setIDestado(Integer.parseInt(params[2]));
        id.setNIFassociacao(Integer.parseInt(params[3]));
        return id;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Detalhebotao) {
            Detalhebotao o = (Detalhebotao) object;
            DetalhebotaoPK id = o.getDetalhebotaoPK();
            if (id == null) {
                return "";
            }
            String delim = "#";
            String escape = "~";
            String IDcaixa = String.valueOf(id.getIDcaixa());
            IDcaixa = IDcaixa.replace(escape, escape + escape);
            IDcaixa = IDcaixa.replace(delim, escape + delim);
            String IDbotao = String.valueOf(id.getIDbotao());
            IDbotao = IDbotao.replace(escape, escape + escape);
            IDbotao = IDbotao.replace(delim, escape + delim);
            String IDestado = String.valueOf(id.getIDestado());
            IDestado = IDestado.replace(escape, escape + escape);
            IDestado = IDestado.replace(delim, escape + delim);
            String NIFassociacao = String.valueOf(id.getNIFassociacao());
            NIFassociacao = NIFassociacao.replace(escape, escape + escape);
            NIFassociacao = NIFassociacao.replace(delim, escape + delim);
            return IDcaixa + delim + IDbotao + delim + IDestado + delim + NIFassociacao;
            // TODO: no setter methods were found in your primary key class
            //    entitiesGASS.DetalhebotaoPK
            // and therefore getAsString() method could not be pre-generated.
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: entitiesGASS.Detalhebotao");
        }
    }
    
}
