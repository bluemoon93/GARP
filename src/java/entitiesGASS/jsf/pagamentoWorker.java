/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Date;
import static entitiesGASS.jsf.beanWorker.getTheBean;
/**
 *
 * @author Nuno
 */
public class pagamentoWorker {

    private int nif, ano;
    private int num_Recibo;
    private int nifAssoc;
    
    private Date data_Pagam, data_Recibo=new Date();
    private String nome, email, num_ReciboAntes, num_ReciboDepois;
    private boolean checkBox;

    public int getNifAssoc() {
        AssociacaoController x = (AssociacaoController) beanWorker.getTheBean("associacao");
        return x.getAssociacao().getNif();
        //return nifAssoc;
    }

    public String getNum_ReciboAntes() {
        return num_ReciboAntes;
    }

    public void setNum_ReciboAntes(String num_ReciboAntes) {
        this.num_ReciboAntes = num_ReciboAntes;
    }

    public String getNum_ReciboDepois() {
        return num_ReciboDepois;
    }

    public void setNum_ReciboDepois(String num_ReciboDepois) {
        this.num_ReciboDepois = num_ReciboDepois;
    }

    public void setNifAssoc(int nifAssoc) {
        this.nifAssoc = nifAssoc;
    }
    
   
    public Date getData_Recibo() {
        return data_Recibo;
    }
    public void setNum_Recibo(int recibo){
        num_Recibo = recibo;
    }
    
    public int getNum_Recibo(){
        return num_Recibo;
    }
    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }

    public boolean getCheckBox() {
        return checkBox;
    }
    
    public pagamentoWorker(String nome, String email,int nif, int num_Transf, Date data_Pagam ) {
        this.nif = nif;
        this.ano = num_Transf;
        this.data_Pagam = data_Pagam;
        this.nome = nome;
        this.email = email;
        this.checkBox = false;
    }
    
    public Date getData_Pagam() {
        return data_Pagam;
    }

    public int getNif() {
        return nif;
    }

    public int getAno() {
        System.err.println("oiss " + ano);
        return ano;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public void setNif(int a) {
        nif = a;
    }

    public void setAno(int a) {
        ano = a;
    }

    public void setEmail(String a) {
        email = a;
    }

    public void setNome(String a) {
        nome = a;
    }

    public void setData_Pagam(Date a) {
        data_Pagam = a;
    }
    public void setData_Recibo(Date a) {
        data_Recibo = a;
    }
    
    public String toString(){
        return "Nome: " +nome + "NIF: " + nif;
    }
}
