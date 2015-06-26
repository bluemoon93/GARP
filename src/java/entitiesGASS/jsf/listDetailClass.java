/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import java.util.Date;


/**
 *
 * @author Carlos Manuel
 */
public class listDetailClass {

    private String conteudo, Nome, funcao;
    private short tipo;
    private int id;
    boolean check, addPay, addEvent;
    Date data;
    //double quantia;
    
    //cenas pa evento
    int nrTrans, nrRec;
    Date dataPag, dataRec;
    double quantia;

    public boolean isCheck() {
        return check;
    }

    public boolean isAddPay() {
        return addPay;
    }

    public boolean isAddEvent() {
        return addEvent;
    }

    public int getNrTrans() {
        return nrTrans;
    }

    public int getNrRec() {
        return nrRec;
    }

    public Date getDataPag() {
        return dataPag;
    }

    public Date getDataRec() {
        return dataRec;
    }

    public double getQuantia() {
        return quantia;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean getCheck() {
        return check;
    }

    public listDetailClass(int nrTrans, Date dataPag, double quantia, int nrRec, Date dataRec, int id) {
        this.nrTrans = nrTrans;
        this.nrRec = nrRec;
        this.dataPag = dataPag;
        this.dataRec = dataRec;
        this.quantia = quantia;
        this.id=id;
    }

   /* public listDetailClass(Double b, String Nome, String funcao, short tipo, int a) {
        this.quantia=a;
        this.Nome = Nome;
        this.funcao = funcao;
        this.tipo = tipo;
        this.check=false;
        this.id=a;
        
        
    } 
    
    public listDetailClass(Date data, String Nome, String funcao, short tipo, int a) {
        this.data=data;
        this.Nome = Nome;
        this.funcao = funcao;
        this.tipo = tipo;
        this.check=false;
        this.id=a;
        
        
    }*/ 
    
    public listDetailClass(String conteudo, String Nome, String funcao, short tipo, int a) {
        this.conteudo = conteudo;
        this.Nome = Nome;
        this.funcao = funcao;
        this.tipo = tipo;
        this.check=false;
        this.id=a;
        this.data=null;
        
    }

    public String getConteudo() {
        return conteudo;
    }

    public String getNome() {
        return Nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public short getTipo() {
        return tipo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void setTipo(short tipo) {
        this.tipo = tipo;
    }
    
    
}

