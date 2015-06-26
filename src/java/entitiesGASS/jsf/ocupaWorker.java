/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Associacao;
import entitiesGASS.Cargo;
import entitiesGASS.Tiposocio;
import entitiesGASS.jsf.util.JsfUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author root
 */
public class ocupaWorker {

    private int nif, idAssoc;
    private String email, password, nome, telefone;
    private Cargo cargo;
    private Date dataNasc, dataFim = new Date(), dataInicio = new Date();
    private Tiposocio tipo;
    private List<Tiposocio> lista = new ArrayList<Tiposocio>();

    public Tiposocio getTipo() {
        return tipo;
    }

    public void setTipo(Tiposocio tipo) {
        this.tipo = tipo;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date a) {
        dataNasc = a;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date a) {
        dataFim = a;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date a) {
        dataInicio = a;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String a) {
        telefone = a;
    }
    
    public SelectItem[] tipoItemsAvailableSelectOne2(int useless) {

        AssociacaoController assoc = (AssociacaoController) beanWorker.getTheBean("associacao");
        TiposocioController a = (TiposocioController) beanWorker.getTheBean("tiposocio");
        lista = a.getJpaController().findAll();


        for (int i = 0; i < lista.size(); i++) {
            if (!lista.get(i).getAssociacao().equals(assoc.getJpaController().find(useless))) {
                lista.remove(i);
                i--;
            }

        }

        return JsfUtil.getSelectItems(lista, false);
    }

    public SelectItem[] tipoItemsAvailableSelectOne(int useless) {

        AssociacaoController assoc = (AssociacaoController) beanWorker.getTheBean("associacao");
        TiposocioController a = (TiposocioController) beanWorker.getTheBean("tiposocio");
        lista = a.getJpaController().findAll();


        for (int i = 0; i < lista.size(); i++) {
            if (!lista.get(i).getAssociacao().equals(assoc.getAssociacao())) {
                lista.remove(i);
                i--;
            }

        }

        return JsfUtil.getSelectItems(lista, false);
    }

    public int getNif() {
        return nif;
    }

    public int getIdAssoc() {
        return idAssoc;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String a) {
        nome = a;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo a) {
        cargo = a;
    }

    public void setNif(int a) {
        nif = a;
    }

    public void setIdAssoc(int a) {
        idAssoc = a;
    }

    public void setEmail(String a) {
        email = a;
    }

    public void setPassword(String a) {
        password = a;
    }
}
