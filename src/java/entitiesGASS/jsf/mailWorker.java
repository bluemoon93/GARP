/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Associacao;
import static entitiesGASS.jsf.beanWorker.getTheBean;
import entitiesGASS.jsf.util.JsfUtil;

/**
 *
 * @author Nuno
 */
public class mailWorker {

    private String assunto, body, allMails;

    public String getAllMails() {
        return allMails;
    }

    public void setAllMails(String allMails) {
        this.allMails = allMails;
    }

    public mailWorker() {
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    public String mailPage(){
        SocioController socCtrl = (SocioController) getTheBean("socio");
        allMails = socCtrl.mailAll();
        
        if (socCtrl.getAllSocMailList().isEmpty()) {
            JsfUtil.addErrorMessage("Selecione o(s) sócio(s) a enviar o e-mail");
            return "";
        }
        return "mailPage";
    }
    public String fillMail() {

        AssociacaoController assCtrl = (AssociacaoController) getTheBean("associacao");
        SocioController socCtrl = (SocioController) getTheBean("socio");
        Associacao temp = assCtrl.getAssociacao();
        boolean flag = false;   //to just send one message
        
        for(int i = 0; i < socCtrl.getAllSocMailList().size(); i++){
            try {
                beanWorker.send(temp.getServer(), temp.getEmail(), temp.getPasswd(), socCtrl.getAllSocMailList().get(i), assunto, body, null, temp.getPort());
                flag = true;    
            } catch (Exception ex) {
                System.out.println(ex);
                JsfUtil.addErrorMessage("Incapaz de enviar email");
                return "";
            }
        }
        
        if(flag)
           JsfUtil.addSuccessMessage("E-mail enviado. Pode demorar alguns minutos a chegar à sua caixa de correio.");     
        assunto = body = "";        //clean input texts
        return socCtrl.cleanAll();  //clean checkboxes and go to BEHome1
    }
    public String cancelar(){
        
        SocioController socCtrl = (SocioController) getTheBean("socio");
        //clean checkbox and input fields
        socCtrl.cleanAll();
        allMails = assunto = body = "";
        
        return "detalheSecretariado";
    }
}
