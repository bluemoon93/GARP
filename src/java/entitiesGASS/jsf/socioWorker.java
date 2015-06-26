/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Associacao;
import entitiesGASS.Tiposocio;
import entitiesGASS.jsf.util.JsfUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author Carlos Manuel
 */
public class socioWorker {
    private int nif, idAssoc;
    private Associacao assoc;
    private String email, password;
    private Tiposocio tipoSocio;
    private List<Tiposocio> lista=new ArrayList<Tiposocio>();
    
    public Tiposocio getTipoSocio() {
        return tipoSocio;
    }

    public void setTipoSocio(Tiposocio tipoSocio) {
        this.tipoSocio = tipoSocio;
    }
    
    public Associacao getAssoc(){
        return assoc;
    }
    
    public void setAssoc(Associacao a){
        assoc=a;
    }
    
    public int getNif(){
        return nif;
    }
    
    public int getIdAssoc(){
        return idAssoc;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setNif(int a){
        nif=a;
    }
    
    public void setIdAssoc(int a){
        idAssoc=a;
    }
    
    public void setEmail(String a){
        email=a;
    }
    
    public void setPassword(String a){
        password=a;
    }
    
    public SelectItem[] tipoItemsAvailableSelectOne(int lol) {
        TiposocioController a= (TiposocioController) beanWorker.getTheBean("tiposocio");
        lista=a.getJpaController().findAll();

            for(int i=0; i<lista.size(); i++){
                if(lista.get(i).getAssociacao().getNif()!=(lol)){
                    lista.remove(i);
                    i--;
                }

        }
        
        return JsfUtil.getSelectItems(lista, false);
    }
   
}

