/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Associacao;
import entitiesGASS.Candidato;
import entitiesGASS.Socio;
import entitiesGASS.jsf.util.JsfUtil;


/**
 *
 * @author Nuno
 */
public class passWorker {

    String pass, dcheck, old;
    boolean socio,ass;
    SocioController socCtrl;
    CandidatoController candyCtrl;
    Socio one;
    Candidato snd;

    public boolean isSocio() {
        return socio;
    }

    public void setSocio(boolean socio) {
        this.socio = socio;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDcheck() {
        return dcheck;
    }

    public void setDcheck(String dcheck) {
        this.dcheck = dcheck;
    }

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    public String changePass() {
        
        if(pass.isEmpty() || old.isEmpty() || dcheck.isEmpty()){
                JsfUtil.addErrorMessage("Todos os campos são de preenchimento obrigatório");
                return "";
        }
        
        if (!ass) {
              
            candyCtrl = (CandidatoController) beanWorker.getTheBean("candidato");

            if (socio) {
                snd = one.getCandidato();
            } 
            
            if (!snd.getPalavraPasse().equals(beanWorker.encodePass(old))) {
                JsfUtil.addErrorMessage("A password antiga está incorrecta");
                pass = dcheck = old = "";
                return "";
            }

            if (!pass.equals(dcheck)) {
                JsfUtil.addErrorMessage("A nova password nao coincide em ambos os campos");
                pass = dcheck = old = "";
                return "";
            }
            snd.setPalavraPasse(pass);
            candyCtrl.setCandy(snd);
            
            this.socCtrl = null;
            this.candyCtrl = null;
            pass = dcheck = old = "";
            return returnChoice();
        }
        
        AssociacaoController assCtrl = (AssociacaoController) beanWorker.getTheBean("associacao");
        Associacao assoc = assCtrl.getAssociacao();

        if (!assoc.getPasswd().equals(old)) {
                JsfUtil.addErrorMessage("A password antiga está incorrecta");
                pass = dcheck = old = "";
                return "";
            }

            if (!pass.equals(dcheck)) {
                JsfUtil.addErrorMessage("A nova password nao coincide em ambos os campos");
                pass = dcheck = old = "";
                return "";
            }
            assoc.setPasswd(pass);
            assCtrl.editGass2(); 
            pass = dcheck = old = "";
        return "gassDetail";
    }

    public String assPassPage2() {
        ass = true;
        old = null;
        System.out.println("sjkhfkdhv " + old);
        return "changePass";
    }


    public String returnChoice() {
        if (socio) {
            return "detalheSoss";
        } else {
            return "detalheCandy";
        }
    }

    public String cancelar() {
        pass = dcheck = old = "";
        this.socCtrl = null;
        this.candyCtrl = null;
        if(!ass)
            return returnChoice();
        return "gassDetail";
    }

    public String changePassPage() {
        socio = true;
        ass = false;
        this.socCtrl = (SocioController) beanWorker.getTheBean("socio");
        one = socCtrl.getSocio();
        return "changePass";
    }

    public String changePassPageCandy() {
        socio = ass = false;
        this.candyCtrl = (CandidatoController) beanWorker.getTheBean("candidato");
        snd = candyCtrl.getCandidato();
        return "changePass";
    }
}
