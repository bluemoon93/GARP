/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Associacao;
import entitiesGASS.Estado;

/**
 *
 * @author Carlos Manuel
 */
public class buttonWorker {
    private boolean funcao, funcao2, addPay, changeIf;
    private Estado estado;
    private String assunto="";

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public boolean isChangeIf() {
        return changeIf;
    }

    public void setChangeIf(boolean changeIf) {
        this.changeIf = changeIf;
    }

    public boolean getAddPay() {
        return addPay;
    }

    public void setAddPay(boolean addPay) {
        this.addPay = addPay;
    }
    
    
    
    public Estado getEstado(){
       return estado;
    }
    
    public void setEstado(Estado t){
        estado=t;
    }
    
    public boolean getFuncao() {
        return funcao;
    }

    public void setFuncao(boolean funcao) {
        this.funcao = funcao;
    }
    
    public boolean getFuncao2() {
        return funcao2;
    }

    public void setFuncao2(boolean funcao2) {
        this.funcao2 = funcao2;
    }
    
    public int checkFunction(Associacao assoc){
        if(funcao2 && estado==null){
            return 1;
        }
      
        return 0;
    }
    
    public String getFunction(){
        String temp="";
        
        if(addPay){
            temp=temp.concat("addPay;");
        }
        
        if(funcao){
            temp=temp.concat("email;");
        }
        
        if(funcao2){
            if(!changeIf)
                temp=temp.concat("changestate"+estado.getEstadoPK().getIDestado()+";");
            else
                temp=temp.concat("changeIf"+estado.getEstadoPK().getIDestado()+";");
        }
        
        

        return temp;
    }
}

