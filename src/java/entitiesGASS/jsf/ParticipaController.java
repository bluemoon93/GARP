/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Pagamentoevento;
import entitiesGASS.PagamentoeventoPK;
import entitiesGASS.Participa;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import entitiesGASS.jsf.util.JsfUtil;
import entitiesGASS.ParticipaPK;
import entitiesGASS.beans.ParticipaFacade;
import static entitiesGASS.jsf.beanWorker.getTheBean;
import entitiesGASS.jsf.util.PagingInfo;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class ParticipaController {

    public ParticipaController() {
        pagingInfo = new PagingInfo();
        converter = new ParticipaConverter();
    }
    private Participa participa = null;
    private List<Participa> participaItems = null;
    private ParticipaFacade jpaController = null;
    private ParticipaConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "GASSv10PU")
    private EntityManagerFactory emf = null;
    //<Ana
    private int valorRecibo = 0;

    @Override
    public String toString() {
        return "" + this.participa.getValorAPagar();
    }

    private void updateParticipa() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ParticipaFacade Jpa1Controller = (ParticipaFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "participaJpa");
        List<Participa> pList = Jpa1Controller.findAll();

        for (int i = 0; i < pList.size(); i++) {
            if (!pList.get(i).getEvento().equals(this.participa.getEvento())) {
                pList.remove(i);
                i--;
            }
        }
        this.participaItems = pList;
    }

    //<Ana2
    public String participarEvento() {
        //      this.createSetupGASS();
        this.validateCreate(null, null, this);
        SocioController sc = (SocioController) getTheBean("socio");
        EventoController ec = (EventoController) getTheBean("evento");
        
        this.participa.getParticipaPK().setEmail(sc.getSocio().getCandidato().getCandidatoPK().getEmail());
        double d = ec.getEvento().getQuantia().doubleValue();
        if(d == 0.00){
            this.participa.setPagou(true);
        }
        this.createGASS();
        return "evento_detailGASSSocio";
    }
    
    public boolean quantiaZero(){
        double d = this.participa.getValorAPagar().doubleValue();
        if(d == 0.00){
            return true;
        }
        return false;
    }

    public boolean jaParticipa() {
        // meto o candidato ou o socio?
        CandidatoController cc = (CandidatoController) getTheBean("candidato");
        EventoController ec = (EventoController) getTheBean("evento");

        ParticipaPK pk = new ParticipaPK();
        pk.setEmail(cc.getCandidato().getCandidatoPK().getEmail());
        pk.setIDEvento(ec.getEvento().getEventoPK().getIDEvento());
        pk.setNIFasso(ec.getEvento().getEventoPK().getNIFassoc());

        Participa p = this.getJpaController().find(pk);
        if (p != null) {
            return true;
        }
        return false;
    }
//<Ana2

    public String createSetupGASS() {
        reset(false);
        participa = new Participa();
        participa.setParticipaPK(new ParticipaPK());

        EventoController ec = (EventoController) getTheBean("evento");
        participa.setValorAPagar(ec.getEvento().getQuantia());
        if (ec.getEvento().getQuantia().equals(BigDecimal.ZERO)) {
            this.participa.setPagou(true);
        }
        this.participa.setPagou(false);
        return "participa_createGASS";
    }

    public String createGASS() {
        AssociacaoController ac = (AssociacaoController) getTheBean("associacao");
        if (this.participa == null || this.participa.getParticipaPK() == null
                || this.participa.getParticipaPK().getEmail().equals("")) {
            JsfUtil.addErrorMessage("Não foram preenchidos todos os campos.");
//            return false;
            return "participa_listGASS";
        }

        this.limparCheckBox();

        this.participa.getParticipaPK().setNIFasso(ac.getAssociacao().getNif());

        EventoController ec = (EventoController) getTheBean("evento");

        this.participa.getParticipaPK().setIDEvento(ec.getEvento().getEventoPK().getIDEvento());
        this.participa.setEvento(ec.getEvento());
        this.participa.setDataInscricao(new Date());

        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(participa);

            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                ec.backEndCreateParticipante(participa);


                //               this.updateParticipa();
                JsfUtil.addSuccessMessage("O participante " + this.participa.getParticipaPK().getEmail() + " foi adicionado ao evento com sucesso.");
                //             return true;
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            //        return false;
            return null;
        }
//        return false;
        return listSetupGASS();
    }

    public String listSetupGASS() {
        return "participa_listGASS";
    }

    public String editSetupGASS() {
        return scalarSetup("participa_editGASS");
    }

    public String pagar() {
        return "evento_pagarGASS";
    }

    //<Ana
    public String editParticipa(Participa p) {
//        participa.getParticipaPK().setIDEvento(participa.getEvento().getEventoPK().getIDEvento());
//        participa.getParticipaPK().setNIFasso(participa.getEvento().getEventoPK().getNIFassoc());
//        String participaString = converter.getAsString(FacesContext.getCurrentInstance(), null, participa);
//        String currentParticipaString = JsfUtil.getRequestParameter("jsfcrud.currentParticipa");
//        if (participaString == null || participaString.length() == 0 || !participaString.equals(currentParticipaString)) {
//            String outcome = editSetup();
//            if ("participa_edit".equals(outcome)) {
//                JsfUtil.addErrorMessage("Could not edit participa. Try again.");
//            }
//            return outcome;
//        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(p);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                //            JsfUtil.addSuccessMessage("Pagamento do participante actualizado.");
            } else {
                //           JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            //     JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return "participa_listGASS";
    }

    public void limparCheckBox() {
        EventoController ec = (EventoController) getTheBean("evento");
        Collection<Participa> cp = ec.getEvento().getParticipaCollection();
        Iterator<Participa> ip = cp.iterator();
        Participa p;
        Pagamentoevento pe;
        PagamentoeventoController pec = (PagamentoeventoController) getTheBean("pagamentoevento");
        Collection<Participa> newCP = new ArrayList<Participa>();

        while (ip.hasNext()) {
            p = ip.next();
            pe = pec.existeP(p);
            if (pe == null) {
                p.setPagou(false);
                newCP.add(p);
            } else {
                newCP.add(p);
            }
        }

        ec.getEvento().setParticipaCollection(newCP);
    }

    public boolean BackEndDeleteSelected(ParticipaPK id) {
        EventoController ec = (EventoController) getTheBean("evento");
        Participa p = this.getJpaController().find(id);
        System.out.println("quero apagar o " + p.getParticipaPK().toString());
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().remove(p);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage(p.getParticipaPK().getEmail() + " já não participa no evento.");
                return true;
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return false;
        }
        return false;
    }

    public String participanteRetirarInscricao() {
        EventoController ec = (EventoController) getTheBean("evento");
        System.out.println("vmf " + participa.toString());
        ParticipaPK pk = new ParticipaPK();
        CandidatoController cc = (CandidatoController) getTheBean("candidato");
        pk.setEmail(cc.getCandidato().getCandidatoPK().getEmail());
        pk.setIDEvento(ec.getEvento().getEventoPK().getIDEvento());
        pk.setNIFasso(ec.getEvento().getEventoPK().getNIFassoc());
        
        if (this.BackEndDeleteSelected(pk)) {
            ec.getEvento().setParticipaCollection(this.getJpaController().findAll());
        }
        return "evento_detailGASSSocio";
    }

    public int getValorRecibo() {
        PagamentoeventoController pec = (PagamentoeventoController) getTheBean("pagamentoevento");

        PagamentoeventoPK pk = new PagamentoeventoPK();
        pk.setEmail(this.participa.getParticipaPK().getEmail());
        pk.setIDEvento(this.participa.getParticipaPK().getIDEvento());
        pk.setNIFAssociacao(this.participa.getParticipaPK().getNIFasso());

        Pagamentoevento pe = pec.getJpaController().find(pk);

        if (pe.getNrRecibo() != null) {
            this.valorRecibo = pe.getNrRecibo();
        }
        return this.valorRecibo;
    }

    public String remove() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentParticipa");
        ParticipaPK id = converter.getId(idAsString);
        EventoController ec = (EventoController) getTheBean("evento");

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
                JsfUtil.addSuccessMessage("O participante foi eliminado com sucesso.");
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
        return listSetupGASS();
    }

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public ParticipaFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (ParticipaFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "participaJpa");
        }
        return jpaController;
    }

    public SelectItem[] getParticipaItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getParticipaItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Participa getParticipa() {
        if (participa == null) {
            participa = (Participa) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentParticipa", converter, null);
        }
        if (participa == null) {
            participa = new Participa();
        }
        return participa;
    }

    public String listSetup() {
        reset(true);
        return "participa_list";
    }

    public String createSetup() {
        reset(false);
        participa = new Participa();
        participa.setParticipaPK(new ParticipaPK());
        return "participa_create";
    }

    public String create() {
        participa.getParticipaPK().setIDEvento(participa.getEvento().getEventoPK().getIDEvento());
        participa.getParticipaPK().setNIFasso(participa.getEvento().getEventoPK().getNIFassoc());
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(participa);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Participa was successfully created.");
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
        return scalarSetup("participa_detail");
    }

    public String editSetup() {
        return scalarSetup("participa_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        participa = (Participa) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentParticipa", converter, null);
        if (participa == null) {
            String requestParticipaString = JsfUtil.getRequestParameter("jsfcrud.currentParticipa");
            JsfUtil.addErrorMessage("The participa with id " + requestParticipaString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        participa.getParticipaPK().setIDEvento(participa.getEvento().getEventoPK().getIDEvento());
        participa.getParticipaPK().setNIFasso(participa.getEvento().getEventoPK().getNIFassoc());
        String participaString = converter.getAsString(FacesContext.getCurrentInstance(), null, participa);
        String currentParticipaString = JsfUtil.getRequestParameter("jsfcrud.currentParticipa");
        if (participaString == null || participaString.length() == 0 || !participaString.equals(currentParticipaString)) {
            String outcome = editSetup();
            if ("participa_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit participa. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(participa);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Participa was successfully updated.");
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

    public List<Participa> getParticipaItems() {
        if (participaItems == null) {
            getPagingInfo();
            participaItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return participaItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "participa_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "participa_list";
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
        participa = null;
        participaItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        System.out.println("aquifpdfj");
        Participa newParticipa = new Participa();
        newParticipa.setParticipaPK(new ParticipaPK());
        String newParticipaString = converter.getAsString(FacesContext.getCurrentInstance(), null, newParticipa);
        String participaString = converter.getAsString(FacesContext.getCurrentInstance(), null, participa);
        if (!newParticipaString.equals(participaString)) {
            //  createSetup();
            this.createSetupGASS();
        }
        EventoController ec = (EventoController) getTheBean("evento");

        participa.setValorAPagar(ec.getEvento().getQuantia());
        System.err.println("Preço = " + ec.getEvento().getQuantia());
    }

    public Converter getConverter() {
        return converter;
    }
}
