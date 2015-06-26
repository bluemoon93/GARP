/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Associacao;
import entitiesGASS.Evento;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import entitiesGASS.jsf.util.JsfUtil;
import entitiesGASS.EventoPK;
import entitiesGASS.Pagamentoevento;
import entitiesGASS.PagamentoeventoPK;
import entitiesGASS.Participa;
import entitiesGASS.beans.EventoFacade;
import static entitiesGASS.jsf.beanWorker.getTheBean;
import entitiesGASS.jsf.util.PagingInfo;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
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
public class EventoController {

    public EventoController() {
        pagingInfo = new PagingInfo();
        converter = new EventoConverter();
    }
    private Evento evento = null;
    private List<Evento> eventoItems = null;
    private EventoFacade jpaController = null;
    private EventoConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "GASSv10PU")
    private EntityManagerFactory emf = null;
// <Ana
    List<Pagamentoevento> lpe = new ArrayList<Pagamentoevento>();
    List<Participa> lParticipa;
    List<Evento> proximosEventos = null;
    Date today = new Date();

    public void setProximosEventos(int nifA) {
        this.proximosEventos = this.getJpaController().findAll();

        for (int i = 0; i < this.proximosEventos.size(); i++) {
            if (this.proximosEventos.get(i).getAssociacao().getNif() != nifA) {
                this.proximosEventos.remove(i);
                i--;
            }
        }
    }

    public List<Evento> getProximosEventos() {
        return proximosEventos;
    }

    public List<Pagamentoevento> getLpe() {
        return lpe;
    }

    public String selectAllEventos() throws IOException {
        AssociacaoController ac = (AssociacaoController) getTheBean("associacao");
        for (int i = 0; i < this.eventoItems.size(); i++) {
            if (this.eventoItems.get(i).getAssociacao().equals(ac.getAssociacao())) {
                this.eventoItems.get(i).setCheckBox(true);
            } else {
            }
        }
        return "evento_listGASS";
    }

    public String cleanAllEventos() throws IOException {
        AssociacaoController ac = (AssociacaoController) getTheBean("associacao");
        for (int i = 0; i < this.eventoItems.size(); i++) {
            if (this.eventoItems.get(i).getAssociacao().equals(ac.getAssociacao())) {
                this.eventoItems.get(i).setCheckBox(false);
            }
        }
        return "evento_listGASS";
    }

    public String backEndDeleteEventos() {
        AssociacaoController ac = (AssociacaoController) getTheBean("associacao");
        String aux = "";
        for (int i = 0; i < this.eventoItems.size(); i++) {
            if (this.eventoItems.get(i).getCheckBox()
                    && this.eventoItems.get(i).getAssociacao().equals(ac.getAssociacao())) {
                if (this.BackEndDeleteSelected(this.eventoItems.get(i)).equals("sucesso")) {
                    this.eventoItems.remove(i);
                    i--;
                }
            }
        }

        return "evento_listGASS";
    }

    public String selectAllParticipantes() throws IOException {
        Collection<Participa> cp = this.evento.getParticipaCollection();
        Iterator<Participa> ip = cp.iterator();
        Collection<Participa> aux = new ArrayList<Participa>();
        Participa p;

        while (ip.hasNext()) {
            p = ip.next();
            p.setCheckBox(true);
            aux.add(p);
        }

        this.evento.setParticipaCollection(aux);
        return "participa_listGASS";
    }

    public String cleanAllParticipantes() throws IOException {
        Collection<Participa> cp = this.evento.getParticipaCollection();
        Iterator<Participa> ip = cp.iterator();
        Collection<Participa> aux = new ArrayList<Participa>();
        Participa p;

        while (ip.hasNext()) {
            p = ip.next();
            p.setCheckBox(false);
            aux.add(p);
        }

        this.evento.setParticipaCollection(aux);
        return "participa_listGASS";
    }

    public String backEndDeleteParticipante() {
        Collection<Participa> cp = this.evento.getParticipaCollection();
        Iterator<Participa> ip = cp.iterator();
        Collection<Participa> aux = new ArrayList<Participa>();
        Participa p;
        ParticipaController pc = (ParticipaController) getTheBean("participa");

        while (ip.hasNext()) {
            p = ip.next();

            if (p.getCheckBox()) {
                if (!pc.BackEndDeleteSelected(p.getParticipaPK())) {
                    aux.add(p);
                }
            } else {
                aux.add(p);
            }
        }

        this.evento.setParticipaCollection(aux);
        return "participa_listGASS";
    }
    //Ana2 

    public void backEndCreateParticipante(Participa p) {
        Collection<Participa> cp = this.evento.getParticipaCollection();
        Iterator<Participa> ip = cp.iterator();
        Collection<Participa> aux = new ArrayList<Participa>();

        while (ip.hasNext()) {
            aux.add(ip.next());
        }

        aux.add(p);

        this.evento.setParticipaCollection(aux);
        //      return "participa_listGASS";
    }

    public String removeGASS() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentEvento");
        EventoPK id = converter.getId(idAsString);
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
                JsfUtil.addSuccessMessage("Evento was successfully deleted.");
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
        return "evento_listGASS";
    }

    public String registarPagamentosEventos() {
        Collection<Participa> cp = this.evento.getParticipaCollection();
        Iterator<Participa> ip = cp.iterator();
        Participa p;
        PagamentoeventoController pec = (PagamentoeventoController) getTheBean("pagamentoevento");
        Pagamentoevento pe = new Pagamentoevento();
        Short s = 4;
        ParticipaController pc = (ParticipaController) getTheBean("participa");
        Collection<Participa> aux = new ArrayList<Participa>();

        while (ip.hasNext()) {
            p = ip.next();
            if (p.getPagou() && pec.existeP(p) == null) {

                pe.setDataPag(new Date());
                pe.setQuantia(p.getValorAPagar());
                pe.setParticipa(p);
                pe.setPagamentoeventoPK(new PagamentoeventoPK(p.getParticipaPK().getNIFasso(),
                        p.getParticipaPK().getIDEvento(), p.getParticipaPK().getEmail()));
                pe.setDataRecibo(new Date());

                try {
                    pec.createPagamentosEventos(pe);
                    pc.editParticipa(p);
                    aux.add(p);
                    JsfUtil.addSuccessMessage("Pagamento de " + p.getParticipaPK().getEmail() + " registado com sucesso.");
                } catch (Exception err) {
                    p.setPagou(false);
                    pc.editParticipa(p);
                    aux.add(p);
                    JsfUtil.addErrorMessage("Não foi possível registar pagamento. Erro: " + err);
                }
            } else {
                aux.add(p);
            }
        }
        this.evento.setParticipaCollection(aux);
        this.setProximosEventos(this.evento.getAssociacao().getNif());
        return "participa_listGASS";
    }

    public boolean algumItemFoiSelecionado() {
        Collection<Participa> cp2 = this.evento.getParticipaCollection();
        Iterator<Participa> ip2 = cp2.iterator();
        Participa p2;
        while (ip2.hasNext()) {
            p2 = ip2.next();
            if (p2.getCheckBox()) {
                return true;
            }
        }
        return false;
    }

    public String listarEventosRecibos() {
        Collection<Participa> cp = this.evento.getParticipaCollection();
        Iterator<Participa> ip = cp.iterator();
        Participa p;
        PagamentoeventoController pec = (PagamentoeventoController) getTheBean("pagamentoevento");
        Pagamentoevento pe;
        ParticipaController pc = (ParticipaController) getTheBean("participa");
        Collection<Participa> newCP = new ArrayList<Participa>();
        int aux = 0;

        if (!this.algumItemFoiSelecionado()) {
            JsfUtil.addErrorMessage("Não selecionou nenhum dos pagamentos.");
            return "participa_listGASS";
        }

        this.lpe.clear();

        while (ip.hasNext()) {
            p = ip.next();
            pe = pec.existeP(p);
            if (p.getCheckBox()) {
                if (p.getPagou() && pe != null) {
                    if (pe.getNrRecibo() == null) {
                        pe.setDataRecibo(new Date());
                        pe.setNrRecibo(pec.maxRecibo() + aux);
                        pe.setNrReciboAntes("");
                        pe.setNrReciboDepois("");
                        this.lpe.add(pe);
                        aux++;
                        newCP.add(p);
                    } else {
                        JsfUtil.addErrorMessage("O participante " + p.getParticipaPK().getEmail()
                                + " já tem um recibo associado.");
                        newCP.add(p);
                    }
                } else {
                    JsfUtil.addErrorMessage("O participante " + p.getParticipaPK().getEmail()
                            + " ainda não pagou a inscrição ou o evento não tem custo, por isso não pode emitir factura.");
                    p.setPagou(false);
                    newCP.add(p);
                }
            }
        }

        this.evento.setParticipaCollection(newCP);
        return "evento_recibosGASS";
    }

    public String emitirRecibos() {
        PagamentoeventoController pec = (PagamentoeventoController) getTheBean("pagamentoevento");

        for (int i = 0; i < this.lpe.size(); i++) {
            try {
                pec.editPagamentosEventos(this.lpe.get(i));
                JsfUtil.addSuccessMessage("Recibo do pagamento de " + this.lpe.get(i).getParticipa().getParticipaPK().getEmail() + " registado com sucesso.");
            } catch (Exception err) {
                this.lpe.get(i).setNrRecibo(0);
                this.lpe.get(i).setNrReciboAntes("");
                this.lpe.get(i).setNrReciboDepois("");
                JsfUtil.addErrorMessage("Não foi possível registar pagamento. Erro: " + err);
            }
        }
        this.setProximosEventos(this.evento.getAssociacao().getNif());
        return "evento_recibosGASS";
    }

    public String changeMaxDefault() {
        PagamentoeventoController pec = (PagamentoeventoController) getTheBean("pagamentoevento");

        for (int i = 0; i < this.lpe.size(); i++) {
            this.lpe.get(i).setNrRecibo(pec.getPagamentoevento().getMaxDefault() + i);
            this.lpe.get(i).setNrReciboAntes(pec.getPagamentoevento().getNrReciboAntes());
            this.lpe.get(i).setNrReciboDepois(pec.getPagamentoevento().getNrReciboDepois());
        }
        return "evento_recibosGASS";
    }

    public String createSetupGASS() {
        reset(false);
        evento = new Evento();
        evento.setEventoPK(new EventoPK());
        this.evento.setDataEvento(today);
        this.evento.setQuantia(BigDecimal.ZERO);
        return "evento_createGASS";
    }

    public String createGASS() {
        //<Ana2
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        if (sdf.format(this.evento.getDataEvento()).compareTo(sdf.format(today)) < 0) {
            JsfUtil.addErrorMessage("Não pode criar eventos para datas já passadas.");
            return "evento_createGASS";
        }

        AssociacaoController x = (AssociacaoController) getTheBean("associacao");
        Associacao ev = x.getAssociacao();
        evento.getEventoPK().setNIFassoc(ev.getNif());
        //<Ana2
        evento.setAssociacao(ev);
        CargoController cc = (CargoController) getTheBean("cargo");
        evento.setCargo(cc.getCargo());
        evento.setDataCriacao(new Date());
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(evento);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("O evento " + this.evento.getTitulo() + " foi criado com sucesso.");
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

        this.setProximosEventos(this.evento.getAssociacao().getNif());
        return listSetupGASS();
    }

    public String listSetupGASS() {
        List<Evento> eve = this.getJpaController().findAll();
        AssociacaoController ac = (AssociacaoController) getTheBean("associacao");

        for (int i = 0; i < eve.size(); i++) {
            if (!eve.get(i).getAssociacao().equals(ac.getAssociacao())) {
                eve.remove(i);
                i--;
            }
        }

        this.eventoItems = eve;
        return "evento_listGASS";
    }

    public String listParticipaGASS() {
        return scalarSetup("participa_listGASS");
    }

    public String editSetupGASS() {
        return scalarSetup("evento_editGASS");
    }

    public String editGASS() {
        evento.getEventoPK().setNIFassoc(evento.getAssociacao().getNif());
        String eventoString = converter.getAsString(FacesContext.getCurrentInstance(), null, evento);
        String currentEventoString = JsfUtil.getRequestParameter("jsfcrud.currentEvento");
        if (eventoString == null || eventoString.length() == 0 || !eventoString.equals(currentEventoString)) {
            String outcome = editSetup();
            if ("evento_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit evento. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(evento);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Evento was successfully updated.");
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
        this.setProximosEventos(this.evento.getAssociacao().getNif());
        return detailSetupGASS();
    }

    public String detailSetupGASS() {
        return scalarSetup("evento_detailGASS");
    }

    public String detailSetupGASSSocio() {
        return scalarSetup("evento_detailGASSSocio");
    }

    public String BackEndDeleteSelected(Evento temp) {
        EventoPK id = temp.getEventoPK();
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
                JsfUtil.addSuccessMessage("O evento " + temp.getTitulo() + " foi apagado com sucesso");
                return "sucesso";
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
        this.setProximosEventos(this.evento.getAssociacao().getNif());
        return "";
    }

    public String nextGASS() {
        reset(false);
        getPagingInfo().nextPage();
        return "evento_listGASS";
    }

    public String prevGASS() {
        reset(false);
        getPagingInfo().previousPage();
        return "evento_listGASS";
    }

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public EventoFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (EventoFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "eventoJpa");
        }
        return jpaController;
    }

    public SelectItem[] getEventoItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getEventoItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Evento getEvento() {
        if (evento == null) {
            evento = (Evento) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentEvento", converter, null);
        }
        if (evento == null) {
            evento = new Evento();
        }
        return evento;
    }

    public String listSetup() {
        reset(true);
        return "evento_list";
    }

    public String createSetup() {
        reset(false);
        evento = new Evento();
        evento.setEventoPK(new EventoPK());
        return "evento_create";
    }

    public String create() {
        evento.getEventoPK().setNIFassoc(evento.getAssociacao().getNif());
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(evento);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Evento was successfully created.");
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
        return scalarSetup("evento_detail");
    }

    public String editSetup() {
        return scalarSetup("evento_edit");
    }
    //<Ana2

    public String voltarAoEventoParticipantes() {
        Collection<Participa> auxP = new ArrayList<Participa>();
        ParticipaController pc = (ParticipaController) getTheBean("participa");
        List<Participa> lp = pc.getJpaController().findAll();

        for (int i = 0; i < lp.size(); i++) {
            if (lp.get(i).getParticipaPK().getIDEvento() == this.evento.getEventoPK().getIDEvento()) {
                auxP.add(lp.get(i));
            }
        }
        evento.setParticipaCollection(auxP);
        return "participa_listGASS";
    }

    private String scalarSetup(String destination) {
        reset(false);

        evento = (Evento) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentEvento", converter, null);

        Collection<Participa> auxP = new ArrayList<Participa>();
        ParticipaController pc = (ParticipaController) getTheBean("participa");
        List<Participa> lp = pc.getJpaController().findAll();

        for (int i = 0; i < lp.size(); i++) {
            if (lp.get(i).getParticipaPK().getIDEvento() == this.evento.getEventoPK().getIDEvento()) {
                auxP.add(lp.get(i));
            }
        }

        evento.setParticipaCollection(auxP);

        if (evento == null) {
            String requestEventoString = JsfUtil.getRequestParameter("jsfcrud.currentEvento");
            JsfUtil.addErrorMessage("The evento with id " + requestEventoString + " no longer exists.");
            return relatedOrListOutcome();
        }

        return destination;
    }

    public String edit() {
        evento.getEventoPK().setNIFassoc(evento.getAssociacao().getNif());
        String eventoString = converter.getAsString(FacesContext.getCurrentInstance(), null, evento);
        String currentEventoString = JsfUtil.getRequestParameter("jsfcrud.currentEvento");
        if (eventoString == null || eventoString.length() == 0 || !eventoString.equals(currentEventoString)) {
            String outcome = editSetup();
            if ("evento_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit evento. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(evento);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Evento was successfully updated.");
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
        this.setProximosEventos(this.evento.getAssociacao().getNif());
        return detailSetup();
    }

    public String remove() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentEvento");
        EventoPK id = converter.getId(idAsString);
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
                JsfUtil.addSuccessMessage("Evento was successfully deleted.");
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

    public List<Evento> getEventoItems() {
        if (eventoItems == null) {
            getPagingInfo();
            eventoItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return eventoItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "evento_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "evento_list";
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
        evento = null;
        eventoItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Evento newEvento = new Evento();
        newEvento.setEventoPK(new EventoPK());
        String newEventoString = converter.getAsString(FacesContext.getCurrentInstance(), null, newEvento);
        String eventoString = converter.getAsString(FacesContext.getCurrentInstance(), null, evento);
        if (!newEventoString.equals(eventoString)) {
            //     createSetup();     <Ana2
            this.createSetupGASS();
        }
    }

    public Converter getConverter() {
        return converter;
    }
}
