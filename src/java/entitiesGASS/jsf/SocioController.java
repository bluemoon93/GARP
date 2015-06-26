/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Candidato;
import entitiesGASS.Estado;
import entitiesGASS.Evento;
import entitiesGASS.Pagamentoquota;
import entitiesGASS.Participa;
import entitiesGASS.ParticipaPK;
import entitiesGASS.Socio;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import entitiesGASS.jsf.util.JsfUtil;
import entitiesGASS.SocioPK;
import entitiesGASS.beans.EventoFacade;
import entitiesGASS.beans.ParticipaFacade;
import entitiesGASS.beans.SocioFacade;
import static entitiesGASS.jsf.beanWorker.getTheBean;
import entitiesGASS.jsf.util.PagingInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author asus
 */
public class SocioController {

    public SocioController() {
        pagingInfo = new PagingInfo();
        converter = new SocioConverter();

        allSocMailList = new ArrayList<String>();
    }
    private Socio socio = null;
    private List<Socio> socioItems = null;
    private SocioFacade jpaController = null;
    private SocioConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "GASSv10PU")
    private EntityManagerFactory emf = null;
    private List<Socio> pendentList = null;
    private List<String> allSocMailList = null;
    private int current = -1, prevInc = 1;
    private String search_Nif = "", search_Estado = "", search_Tipo = "";
    private String search_Nome = "", search_Mail = "", search_NumSoc = "";
    int order = 1;  //1-nome;2-nif;3-nºsocio;4-mail;5-estado;6-tipo
    private List<Evento> eventos = null;
    //<Ana
    private List<Evento> eventosParticipa = new ArrayList<Evento>();
    private List<Socio> iniciaisList = null;
    private String maxAno = "0", search_Ano = "";
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String Proximo() {
        index++;
        if (pendentList.size() > index && index >= 0) {
            socio = pendentList.get(index);
        }
        return socPayListJustSocio(socio);
    }

    public String Anterior() {
        index--;
        if (pendentList.size() > index && index >= 0) {
            socio = pendentList.get(index);
        }
        return socPayListJustSocio(socio);
    }

    public int getCurrent() {
        return current;
    }

    public String memoryPage() {
        setSearch_Nome("");
        setSearch_Nif("");
        setSearch_Mail("");
        setSearch_NumSoc("");
        setSearch_Estado("");
        setSearch_Tipo("");
        setSearch_Ano("");

        if (current == -1) {
            return getAllSoss();
        } else {
            return searchOldState();
        }
    }

    public String getMaxAno() {
        SocioController socCtrl = (SocioController) beanWorker.getTheBean("socio");
        System.out.println(socCtrl.getSocio().getMaxAnoQuota());
        return String.valueOf(socCtrl.getSocio().getMaxAnoQuota());
    }

    public String searchOldState() {

        SocioController socCtrl = (SocioController) beanWorker.getTheBean("socio");
        pendentList = socCtrl.jpaController.findAll();
        AssociacaoController assCtrl = (AssociacaoController) getTheBean("associacao");
        for (int i = 0; i < pendentList.size(); i++) {
            if (pendentList.get(i).getSocioPK().getNIFAssociacao() != assCtrl.getAssociacao().getNif() || pendentList.get(i).getEstado().getEstadoPK().getIDestado() != current) {
                pendentList.remove(i);
                i--;
            }
        }
        Socio.setOrder(prevInc);
        Collections.sort(pendentList);
        return "detalheSecretariado";
    }

    public String getSearch_Ano() {
        return search_Ano;
    }

    public void setSearch_Ano(String search_Ano) {
        this.search_Ano = search_Ano;
    }

    public boolean temQuotasEmDia(Socio alpha) {
        PagamentoquotaController x = (PagamentoquotaController) beanWorker.getTheBean("pagamentoquota");
        List<Pagamentoquota> lista = (List<Pagamentoquota>) x.getJpaController().findAll();
        Calendar hoje = Calendar.getInstance();
        int ano = hoje.get(Calendar.YEAR);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getSocio().equals(alpha)) {
                if (lista.get(i).getPagamentoquotaPK().getAno() >= ano) {
                    return true;
                }
            }
        }

        return false;
    }

    public String createGass2(int nif) {
        //System.out.println("OMG "+nif);
        ocupaWorker o = (ocupaWorker) getTheBean("ocupaWorker");
        AssociacaoController assoc = (AssociacaoController) getTheBean("associacao");
        //candidato
        int ocupa_nifAssoc = nif;
        String ocupa_email = o.getEmail();
        String ocupa_pass = o.getPassword();
        String ocupa_nome = o.getNome();
        Date ocupa_dataNasc = o.getDataNasc();
        String ocupa_tlm = o.getTelefone();

        //socio
        int ocupa_nif = o.getNif();
        
        if(!beanWorker.validarNif(ocupa_nif)){
             JsfUtil.addErrorMessage("NIF Inválido! ");
            return ""; 
        }
        

        Candidato tempcand = new Candidato(ocupa_email, ocupa_nifAssoc);
        tempcand.setNome(ocupa_nome);
        tempcand.setPalavraPasse(ocupa_pass);
        tempcand.setDataNasc(ocupa_dataNasc);
        tempcand.setTelemovel(ocupa_tlm);
        tempcand.setAssociacao(assoc.getAssociacao());

        CandidatoController x = (CandidatoController) getTheBean("candidato");
        if(!x.setCandy(tempcand)){
            return "";
        }

        Socio tempSoss = new Socio(ocupa_nif, ocupa_nifAssoc);
        tempSoss.setCandidato(tempcand);
        Date temp = new Date();
        tempSoss.setDataModificacao(temp);
        Estado state = new Estado(1, ocupa_nifAssoc);
        tempSoss.setEstado(state);

        tempSoss.setNrSocio(getNextNumber(ocupa_nifAssoc));
        tempSoss.setTiposocio(o.getTipo());
        setSocio(tempSoss);

        return "detalheSoss";
    }

    public List<Socio> getIniciaisList() {
        return iniciaisList;
    }

    public void setListaIniciais(List<Socio> lista) {
        iniciaisList = lista;
    }

//<Rui
    public List<String> getAllSocMailList() {
        return allSocMailList;
    }

    public String logout() {

        CandidatoController ass = (CandidatoController) getTheBean("candidato");
        ass.reset(true);
        ass.resetLogin();
        AssociacaoController ass1 = (AssociacaoController) getTheBean("associacao");
        ass1.reset(true);
        reset(true);
        search_Nif = search_Estado = search_Tipo = search_Nome = search_Mail = search_NumSoc = search_Ano = "";

        return "BackEndLogin";
    }

    public List<Evento> getEventosParticipa() {
        return eventosParticipa;
    }

    public void setEventosParticipa(List<Evento> eventosParticipa) {
        this.eventosParticipa = eventosParticipa;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setSearch_Tipo(String givenWord) {
        this.search_Tipo = givenWord;
    }

    public String getSearch_Tipo() {
        return search_Tipo;
    }

    public void setSearch_Estado(String givenWord) {
        this.search_Estado = givenWord;
    }

    public String getSearch_Estado() {
        return search_Estado;
    }

    public void setSearch_Nome(String givenWord) {
        this.search_Nome = givenWord;
    }

    public String getSearch_Nome() {
        return search_Nome;
    }

    public void setSearch_Nif(String nif) {
        this.search_Nif = nif;
    }

    public String getSearch_Nif() {
        return search_Nif;
    }

    public void setSearch_Mail(String givenWord) {
        this.search_Mail = givenWord;
    }

    public String getSearch_Mail() {
        return search_Mail;
    }

    public void setSearch_NumSoc(String givenWord) {
        this.search_NumSoc = givenWord;
    }

    public String getSearch_NumSoc() {
        return search_NumSoc;
    }

    public String shutAllText() {
        setSearch_Nome("");
        setSearch_Nif("");
        setSearch_Mail("");
        setSearch_NumSoc("");
        setSearch_Estado("");
        setSearch_Tipo("");

        return getAllSoss();
    }

    public void setOrder(int inc) throws IOException {
        //System.out.println(inc);
        int realTime = inc;
        if (prevInc == Math.abs(inc)) {
            realTime = -(Socio.getOrder());
            Socio.setOrder(realTime);
        } else {
            Socio.setOrder(inc);
        }

        Collections.sort(pendentList);
        prevInc = realTime;


        FacesContext.getCurrentInstance().getExternalContext().redirect("BackEndList.jsp#ola");
    }

    public void setOrderAux(int inc) throws IOException {
        Socio.setOrder(inc);
    }
    
    public void setEditSocioSilent(Socio tempcand) {
        socio = tempcand;
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(socio);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
                JsfUtil.addErrorMessage("NIF existente");
                return;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return;
        }
    }

    public void setEditSocio(Socio tempcand) {
        socio = tempcand;
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(socio);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
                JsfUtil.addErrorMessage("NIF existente");
                return;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage(socio.getCandidato().getCandidatoPK().getEmail() + " alterado para o estado " + socio.getEstado().getNome());
                
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return;
        }
    }

    public void searchSoc() {

        int temp = current;
        getAllSoss();   //inicializar de novo pendentList
        current = temp;
        AssociacaoController assCtrl = (AssociacaoController) getTheBean("associacao");
        if (current > 0) {
            for (int i = 0; i < pendentList.size(); i++) {
                if (pendentList.get(i).getSocioPK().getNIFAssociacao() != assCtrl.getAssociacao().getNif() || pendentList.get(i).getEstado().getEstadoPK().getIDestado() != current) {
                    pendentList.remove(i);
                    i--;
                }
            }
        }

        for (int i = 0; i < pendentList.size(); i++) {
            if (!search_Nome.isEmpty() && (pendentList.get(i).getCandidato().getNome().toLowerCase().indexOf(search_Nome.toLowerCase()) == -1)) {
                pendentList.remove(i);
                i--;
                continue;
            }
            if (!search_Nif.isEmpty() && (String.valueOf(pendentList.get(i).getSocioPK().getNif()).indexOf(search_Nif) == -1)) {
                pendentList.remove(i);
                i--;
                continue;
            }
            if (!search_Mail.isEmpty() && (pendentList.get(i).getCandidato().getCandidatoPK().getEmail().toLowerCase().indexOf(search_Mail.toLowerCase()) == -1)) {
                pendentList.remove(i);
                i--;
                continue;
            }
            if (!search_NumSoc.isEmpty() && (String.valueOf(pendentList.get(i).getNrSocio()).indexOf(search_NumSoc) == -1)) {
                pendentList.remove(i);
                i--;
                continue;
            }
            if (!search_Estado.isEmpty() && (pendentList.get(i).getEstado().getNome().toLowerCase().indexOf(search_Estado.toLowerCase()) == -1)) {
                pendentList.remove(i);
                i--;
                continue;
            }
            if (!search_Tipo.isEmpty() && (pendentList.get(i).getTiposocio().getTiposocioPK().getNome().toLowerCase().indexOf(search_Tipo.toLowerCase()) == -1)) {
                pendentList.remove(i);
                i--;
                continue;
            }
        }
    }

    public void changeState(Estado lol) {
        socio.setEstado(lol);

        socio.setDataModificacao(new Date());
        //System.out.println(socio.getEstado());
        try {
            utx.begin();
        } catch (Exception ex) {
        }


        try {
            Exception transactionException = null;
            getJpaController().edit(socio);
            //      getJpaController().create(socio);
            try {

                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }

            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Socio was successfully updated.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
                JsfUtil.addErrorMessage("rollback");
                //System.out.println("rollback = " + this.socio.getCandidato().getNome());
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return;
        }
    }

    //<Rui mudar
    public String mailAll() {
        StringBuilder allConcat = new StringBuilder();
        allSocMailList.clear();

        for (int i = 0; i < pendentList.size(); i++) {
            //System.out.println("Nome-> "+pendentList.get(i).getCandidato().getNome()+ " "+pendentList.get(i).getCheckBox());
            if (pendentList.get(i).getCandidato() != null && pendentList.get(i).getCheckBox()) {
                allSocMailList.add(pendentList.get(i).getCandidato().getCandidatoPK().getEmail() + " ");
            }
        }
        for (int i = 0; i < allSocMailList.size(); i++) {
            allConcat.append(allSocMailList.get(i));
        }
        Socio.setOrder(prevInc);
        //System.out.println(allConcat);
        Collections.sort(pendentList);
        return allConcat.toString();
    }
    //<Ana2

    public String socPayListJustSocio(Socio one) {
        socio = one;

        PagamentoquotaController payCtrl = (PagamentoquotaController) getTheBean("pagamentoquota");
        payCtrl.SocPayList(one.getSocioPK().getNif());

        BotaoController but = (BotaoController) getTheBean("botao");
        but.updateList(one.getEstado());

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ParticipaFacade Jpa2Controller = (ParticipaFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "participaJpa");
        List<Participa> participas = Jpa2Controller.findAll();

        EventoFacade Jpa3Controller = (EventoFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "eventoJpa");

        while (this.eventosParticipa.size() > 0) {
            this.eventosParticipa.remove(0);
        }

        for (int i = 0; i < participas.size(); i++) {
            if (!participas.get(i).getParticipaPK().getEmail().equals(this.socio.getCandidato().getCandidatoPK().getEmail())
                    || participas.get(i).getParticipaPK().getNIFasso() != this.socio.getCandidato().getCandidatoPK().getNIFassociacao()) {
                participas.remove(i);
                i--;
            }
        }

        Evento aux;
        for (int i = 0; i < participas.size(); i++) {
            try {
                aux = Jpa3Controller.find(participas.get(i).getEvento().getEventoPK());
                this.eventosParticipa.add(aux);
            } catch (Exception err) {
            }
        }
        return "beDetailSoc";    //nao hÃ¡ lista de pagamentos do socio em questao
    }

    public String socPayList() {

        Socio one = (Socio) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentSocio", getConverter(), null);
        socio = one;

        for (int i = 0; i < pendentList.size(); i++) {
            if (pendentList.get(i).equals(socio)) {
                index = i;
                break;
            }
        }

        PagamentoquotaController payCtrl = (PagamentoquotaController) getTheBean("pagamentoquota");
        payCtrl.SocPayList(one.getSocioPK().getNif());

        BotaoController but = (BotaoController) getTheBean("botao");
        but.updateList(one.getEstado());
//<Ana
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ParticipaFacade Jpa2Controller = (ParticipaFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "participaJpa");
        List<Participa> participas = Jpa2Controller.findAll();

        EventoFacade Jpa3Controller = (EventoFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "eventoJpa");

        while (this.eventosParticipa.size() > 0) {
            this.eventosParticipa.remove(0);
        }

        for (int i = 0; i < participas.size(); i++) {
            if (!participas.get(i).getParticipaPK().getEmail().equals(this.socio.getCandidato().getCandidatoPK().getEmail())
                    || participas.get(i).getParticipaPK().getNIFasso() != this.socio.getCandidato().getCandidatoPK().getNIFassociacao()) {
                participas.remove(i);
                i--;
            }
        }

        Evento aux;
        for (int i = 0; i < participas.size(); i++) {
            try {
                aux = Jpa3Controller.find(participas.get(i).getEvento().getEventoPK());
                this.eventosParticipa.add(aux);
            } catch (Exception err) {
            }
        }
        return "beDetailSoc";    //nao hÃ¡ lista de pagamentos do socio em questao
    }

    public String getAllSoss() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SocioFacade Jpa1Controller = (SocioFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "socioJpa");
        pendentList = Jpa1Controller.findAll();

        AssociacaoController assCtrl = (AssociacaoController) getTheBean("associacao");

        for (int i = 0; i < pendentList.size(); i++) {
            if (!assCtrl.getAssociacao().equals(pendentList.get(i).getCandidato().getAssociacao())) {
                pendentList.remove(i);
                i--;
            }
        }
        Socio.setOrder(prevInc);
        Collections.sort(pendentList);
        current = -1;
        return "detalheSecretariado";

    }

    public String stateList() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        SocioFacade Jpa1Controller = (SocioFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "socioJpa");
        pendentList = Jpa1Controller.findAll();

        EstadoController stateCtrl = (EstadoController) getTheBean("estado");//get state controller
        EstadoConverter stateConv = (EstadoConverter) stateCtrl.getConverter();//erro aqui
        Estado temp = (Estado) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentEstado", stateConv, null);
        int estadoID = temp.getEstadoPK().getIDestado();

        stateCtrl.setEstadoAux(temp);   //auxiliar para search
        current = stateCtrl.getEstadoAux().getEstadoPK().getIDestado(); //actualizar currentId 

        AssociacaoController assCtrl = (AssociacaoController) getTheBean("associacao");

        for (int i = 0; i < pendentList.size(); i++) {
            if (pendentList.get(i).getSocioPK().getNIFAssociacao() != assCtrl.getAssociacao().getNif() || pendentList.get(i).getEstado().getEstadoPK().getIDestado() != estadoID) {
                pendentList.remove(i);
                i--;
            }
        }
        Socio.setOrder(prevInc);
        Collections.sort(pendentList);
        return "detalheSecretariado";
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public List<Socio> getPendentList() {
        List<Socio> aux;


        if (pendentList == null) {
            getPagingInfo();
            pendentList = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }

        return pendentList;
    }

    public void setPendentList(List<Socio> newList) {
        pendentList = newList;
    }
// <Ana

    public void selectAllEventos() throws IOException {
        for (int i = 0; i < this.eventosParticipa.size(); i++) {
            this.eventosParticipa.get(i).setCheckBox(true);
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("BackEndSocDetail.jsp#Peventos");
    }

    public void cleanAllEventos() throws IOException {
        for (int i = 0; i < this.eventosParticipa.size(); i++) {
            this.eventosParticipa.get(i).setCheckBox(false);
        }

        FacesContext.getCurrentInstance().getExternalContext().redirect("BackEndSocDetail.jsp#Peventos");
    }

    public void BackEndDeleteParticipacao() throws IOException {
        ParticipaController pc = (ParticipaController) getTheBean("participa");
        EventoController ec = (EventoController) getTheBean("evento");
        ParticipaPK auxp = new ParticipaPK();
        Collection<Participa> cp = ec.getEvento().getParticipaCollection();
        Participa p;

        for (int i = 0; i < this.eventosParticipa.size(); i++) {
            if (this.eventosParticipa.get(i).getCheckBox()) {
                auxp.setIDEvento(this.eventosParticipa.get(i).getEventoPK().getIDEvento());
                auxp.setNIFasso(this.socio.getCandidato().getCandidatoPK().getNIFassociacao());
                auxp.setEmail(this.socio.getCandidato().getCandidatoPK().getEmail());

                if (pc.BackEndDeleteSelected(auxp)) {
                    p = pc.getJpaController().find(auxp);
                    cp.remove(p);
                    this.eventosParticipa.remove(i);
                    i--;
                }
            }
        }
        ec.getEvento().setParticipaCollection(cp);
        FacesContext.getCurrentInstance().getExternalContext().redirect("BackEndSocDetail.jsp#Peventos");
    }

    public String selectAll() {
        for (int i = 0; i < this.pendentList.size(); i++) {
            this.pendentList.get(i).setCheckBox(true);
        }

        return "detalheSecretariado";
    }

    public String cleanAll() {
        //colocar tudo a false nas checkBoxes
        for (int i = 0; i < this.pendentList.size(); i++) {
            this.pendentList.get(i).setCheckBox(false);
        }

        return "detalheSecretariado";
    }

    public String BackEndDeleteAll() {
        int id;
        String aux = "";
        for (int i = 0; i < this.pendentList.size(); i++) {
            if (this.pendentList.get(i).getCheckBox()) {
                aux = this.BackEndDeleteSelected(this.pendentList.get(i));
                if (!aux.equals("") && !aux.equals("Conta apagada com sucesso.")) {
                    return aux;
                }
            }
        }

        return "detalheSecretariado";
    }

    public String BackEndDeleteSelected(Socio temp) {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().remove(temp);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Conta apagada com sucesso.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }

        CandidatoController x = (CandidatoController) getTheBean("candidato");
        if (!x.removeSocioCandy(temp.getCandidato().getCandidatoPK())) {
            return "";
        }

        pendentList.remove(temp);
        return "";
    }
// Ana>

    public String BackEndDelete() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentSocio");
        Socio temp = (Socio) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentSocio", converter, null);
        SocioPK id = converter.getId(idAsString);
        //System.out.println(temp+"  .  "+idAsString+"  .  "+id);
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().remove(getJpaController().find(id));
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Conta apagada com sucesso.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }

        CandidatoController x = (CandidatoController) getTheBean("candidato");
        if (!x.removeSocioCandy(temp.getCandidato().getCandidatoPK())) {
            return "";
        }

        pendentList.remove(temp);

        return "detalheSecretariado";
    }

    public void setLista(List<Socio> lista) {
        pendentList = lista;
    }

    public void setSocio(Socio tempcand) {

        socio = tempcand;

        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(socio);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
                JsfUtil.addErrorMessage("NIF existente");
                return;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Socio was successfully created.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return;
        }
    }


    public String homeSecretariado() {
        return "detalheSecretariado";
    }

    public String detalhesSecretariado() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentSocio");
        socio = (Socio) JsfUtil.getObjectFromRequestParameter(idAsString, converter, null);
        return "BackEndDetalhesSocio";
    }

    public String listEmail() {
        return "listEmail";
    }

    public String detailSocioSetup() {
        return scalarSetup("beDetailSoc");
    }

    public String detailSocioSetup1() {
        return scalarSetup("detalheSoss");
    }

    public String editarSoss() {
        return scalarSetup("editarSoss");
    }

    public String editarSoss1() {
        return scalarSetup("editarSoss1");
    }

    //<Ana
    public String editSoss() {
        socio.getSocioPK().setNIFAssociacao(socio.getEstado().getEstadoPK().getNIFassociacao());
        String socioString = converter.getAsString(FacesContext.getCurrentInstance(), null, socio);
        String currentSocioString = JsfUtil.getRequestParameter("jsfcrud.currentSocio");

        System.out.println("aqui 1");

        if (socioString == null || socioString.length() == 0 || !socioString.equals(currentSocioString)) {
            String outcome = detailSocioSetup();
            if ("detalheSoss".equals(outcome)) {
                JsfUtil.addErrorMessage("Não foi possível editar o sócio.");
            }
            return outcome;
        }
        System.out.println("aqui 2");
        CandidatoController x = (CandidatoController) getTheBean("candidato");
        if (!x.setCandy(socio.getCandidato())) {
            return "editarSoss";
        }
        System.out.println("aqui 3");
        try {
            utx.begin();
        } catch (Exception ex) {
        }


        try {
            Exception transactionException = null;
            getJpaController().edit(socio);
            try {

                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }

            if (transactionException == null) {
                JsfUtil.addSuccessMessage("O sócio foi alterado com sucesso.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
                JsfUtil.addErrorMessage("rollback");
                //System.out.println("rollback = " + this.socio.getCandidato().getNome());
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        System.out.println("aqui 4");
        return detailSocioSetup();
    }
    //Ana>

    public String editSoss1() {
        socio.getSocioPK().setNIFAssociacao(socio.getEstado().getEstadoPK().getNIFassociacao());
        String socioString = converter.getAsString(FacesContext.getCurrentInstance(), null, socio);
        String currentSocioString = JsfUtil.getRequestParameter("jsfcrud.currentSocio");

        if (socioString == null || socioString.length() == 0 || !socioString.equals(currentSocioString)) {
            String outcome = detailSocioSetup();
            if ("detalheSoss".equals(outcome)) {
                JsfUtil.addErrorMessage("Não foi possível editar o sócio.");
            }
            return outcome;
        }
        CandidatoController x = (CandidatoController) getTheBean("candidato");
        if (!x.setCandy(socio.getCandidato())) {
            return "editarSoss";
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }


        try {
            Exception transactionException = null;
            getJpaController().edit(socio);
            try {

                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }

            if (transactionException == null) {
                JsfUtil.addSuccessMessage("O sócio foi alterado com sucesso.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
                JsfUtil.addErrorMessage("rollback");
                //System.out.println("rollback = " + this.socio.getCandidato().getNome());
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }

        return "detalheSoss";
    }

    public String removeSoss2() {
        removeSoss();
        memoryPage();
        return "detalheSecretariado";
    }

    public String removeSoss() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentSocio");
        Socio temp = (Socio) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentSocio", converter, null);
        SocioPK id = converter.getId(idAsString);

        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().remove(getJpaController().find(id));
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Conta apagada com sucesso.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }

        CandidatoController x = (CandidatoController) getTheBean("candidato");
        if (!x.removeSocioCandy(temp.getCandidato().getCandidatoPK())) {
            return "";
        }

        reset(true);
        return "sossAdd";
    }
//<Ana2

    public String returnSoss(Socio temp) {
        socio = temp;
        this.socPayListJustSocio(temp);
        return "detalheSoss";
    }

    public String homeSocioDetalhe() {
        this.socPayListJustSocio(socio);
        return "detalheSoss";
    }

    public String createSoss(int nifA) {
        socioWorker x = (socioWorker) getTheBean("socioWorker");
        AssociacaoController y = (AssociacaoController) getTheBean("associacao");
        x.setAssoc(y.getJpaController().find(nifA));

        return createSoss();
    }

  

    public String createSoss() {
        socioWorker x = (socioWorker) getTheBean("socioWorker");

        if(!beanWorker.validarNif(x.getNif())){
            JsfUtil.addErrorMessage("NIF Inválido! ");
            return ""; 
        }
        
        int nifassoc = x.getAssoc().getNif();
        String emailz = x.getEmail();

        Candidato tempcand = existeCandidato(emailz, nifassoc, beanWorker.encodePass(x.getPassword()));
        if (tempcand == null) {
            JsfUtil.addErrorMessage("Não existe nenhum candidato com esse email");
            return "sossAdd";
        }

        if (existeSoss(emailz)) {
            JsfUtil.addErrorMessage("Já existe um socio com esse email");
            return "sossAdd";
        }

        socio.getSocioPK().setNif(x.getNif());
        Date temp = new Date();
        socio.setDataModificacao(temp); // ficou lg default no dia d hj? nice

        Estado state = new Estado(1, nifassoc);
        socio.setEstado(state);

        socio.setNrSocio(getNextNumber(nifassoc));
        //Candidato candy = new Candidato(emailz, nifassoc);
        socio.setCandidato(tempcand);

        socio.getSocioPK().setNIFAssociacao(nifassoc);
        socio.setTiposocio(x.getTipoSocio());
        // System.out.println(socio.getCandidato()+" - "+ socio.getSocioPK().getNif());
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(socio);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
                JsfUtil.addErrorMessage("NIF existente");
                return "";
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Socio was successfully created.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return "detalheSoss";
    }

    public int getNextNumber(int nif) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (SocioFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "socioJpa");
        List<Socio> lista = jpaController.findAll();

        int number = 1;
        boolean repeat = true;
        while (repeat) {
            repeat = false;
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getSocioPK().getNIFAssociacao() == nif) {
                    if (lista.get(i).getNrSocio() == number) {
                        number++;
                        repeat = true;
                        break;
                    }
                }
            }
        }
        return number;
    }

    private boolean existeSoss(String email) {
        List<Socio> lista = getJpaController().findAll();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCandidato().getCandidatoPK().getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private Candidato existeCandidato(String email, int nif, String pw) {
        CandidatoController c = new CandidatoController();
        List<Candidato> lista = c.getJpaController().findAll();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCandidatoPK().getNIFassociacao() == nif) {
                if (lista.get(i).getCandidatoPK().getEmail().equals(email)) {
                    if (lista.get(i).getPalavraPasse().equals(pw)) {
                        return lista.get(i);
                    }
                }
            }
        }
        return null;
    }

    public PagingInfo getPagingInfo() {
        List<Socio> aux = this.getJpaController().findAll();
        AssociacaoController ac = (AssociacaoController) getTheBean("associacao");

        for (int i = 0; i < aux.size(); i++) {
            if (!aux.get(i).getCandidato().getAssociacao().equals(ac.getAssociacao())) {
                aux.remove(i);
                i--;
            }
        }

        this.pendentList = aux;

        if (pagingInfo.getItemCount() == -1) {
            //<Ana2
            this.pagingInfo.setItemCount(aux.size());
            System.out.println("aqui " + this.pagingInfo.getItemCount());
        }
        return pagingInfo;
    }

    public SocioFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (SocioFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "socioJpa");
        }
        return jpaController;
    }

    public SelectItem[] getSocioItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getSocioItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Socio getSocio() {
        if (socio == null) {
            socio = (Socio) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentSocio", converter, null);
        }
        if (socio == null) {
            socio = new Socio();
        }
        return socio;
    }

    public String listSetup() {
        reset(true);
        return "socio_list";
    }

    public String createSetup() {
        reset(false);
        socio = new Socio();
        socio.setSocioPK(new SocioPK());
        return "socio_create";
    }

    public String create() {
        socio.getSocioPK().setNIFAssociacao(socio.getTiposocio().getTiposocioPK().getNIFassociacao());
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(socio);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Socio was successfully created.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("socio_detail");
    }

    public String editSetup() {
        return scalarSetup("socio_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        socio = (Socio) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentSocio", converter, null);
        if (socio == null) {
            String requestSocioString = JsfUtil.getRequestParameter("jsfcrud.currentSocio");
            JsfUtil.addErrorMessage("The socio with id " + requestSocioString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        socio.getSocioPK().setNIFAssociacao(socio.getTiposocio().getTiposocioPK().getNIFassociacao());
        String socioString = converter.getAsString(FacesContext.getCurrentInstance(), null, socio);
        String currentSocioString = JsfUtil.getRequestParameter("jsfcrud.currentSocio");
        if (socioString == null || socioString.length() == 0 || !socioString.equals(currentSocioString)) {
            String outcome = editSetup();
            if ("socio_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit socio. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(socio);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Socio was successfully updated.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return detailSetup();
    }

    public String remove() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentSocio");
        SocioPK id = converter.getId(idAsString);
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().remove(getJpaController().find(id));
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Socio was successfully deleted.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return relatedOrListOutcome();
    }

    private String relatedOrListOutcome() {
        String relatedControllerOutcome = relatedControllerOutcome();
        if (relatedControllerOutcome != null) {
            return relatedControllerOutcome;
        }
        return listSetup();
    }

    public List<Socio> getSocioItems() {
        System.out.println("tamanho " + this.pendentList.size());


        if (socioItems == null) {
            getPagingInfo();
            socioItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return socioItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
//        return "socio_list";
        //<Ana2
        //     return "detalheSecretariado";
//        try {
//            FacesContext.getCurrentInstance().getExternalContext().redirect("BackEndList.jsp#tabela");
//        } catch (Exception e) {
//        }
        return "detalheSecretariado";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
//        return "socio_list";
        //<Ana2
//        try {
//            FacesContext.getCurrentInstance().getExternalContext().redirect("BackEndList.jsp#tabela");
//        } catch (Exception e) {
//        }
        return "detalheSecretariado";
    }

    private String relatedControllerOutcome() {
        String relatedControllerString = JsfUtil.getRequestParameter("jsfcrud.relatedController");
        String relatedControllerTypeString = JsfUtil.getRequestParameter("jsfcrud.relatedControllerType");
        if (relatedControllerString != null && relatedControllerTypeString != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Object relatedController = context.getApplication().getELResolver().getValue(context.getELContext(), null, relatedControllerString);
            try {
                Class<?> relatedControllerType = Class.forName(relatedControllerTypeString);
                Method detailSetupMethod = relatedControllerType.getMethod("detailSetup");
                return (String) detailSetupMethod.invoke(relatedController);
            } catch (ClassNotFoundException e) {
                throw new FacesException(e);
            } catch (NoSuchMethodException e) {
                throw new FacesException(e);
            } catch (IllegalAccessException e) {
                throw new FacesException(e);
            } catch (InvocationTargetException e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

    private void reset(boolean resetFirstItem) {
        socio = null;
        socioItems = null;
        //<Ana2
        //     pendentList = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Socio newSocio = new Socio();
        newSocio.setSocioPK(new SocioPK());
        String newSocioString = converter.getAsString(FacesContext.getCurrentInstance(), null, newSocio);
        String socioString = converter.getAsString(FacesContext.getCurrentInstance(), null, socio);
        if (!newSocioString.equals(socioString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
}
