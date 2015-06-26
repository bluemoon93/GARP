/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.jsf.util.JsfUtil;
import java.util.Arrays;
import java.util.List;
import javax.faces.model.SelectItem;


/**
 *
 * @author Carlos Manuel
 */
public class buttonDetailWorker {
    private String tipo, nome, conteudo;
    List<String> tipos = Arrays.asList("CaixaTexto", "CheckBox");
    
    public SelectItem[] getTipoItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(tipos, true);
    }
    
    public List getTipos(){
        return tipos;
    }
    
    public void setTipos(List<String> t){
        tipos=t;
    }
    
    public short getTipoN(){
        if(tipo.equals("CaixaTexto")){
            return 1;
        }
        else if(tipo.equals("CheckBox")){
            return 2;
        }
        return 0;
    }
    
    public String getTipo(){
        return tipo;
    }
    public void setTipo(String t){
        tipo=t;
    }
    
     public String getNome(){
        return nome;
    }
    public void setNome(String t){
        nome=t;
    }
    
     public String getConteudo(){
        return conteudo;
    }
    public void setConteudo(String t){
        conteudo=t;
    }
}

