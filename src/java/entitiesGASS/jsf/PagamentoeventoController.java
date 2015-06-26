/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Pagamentoevento;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import entitiesGASS.jsf.util.JsfUtil;
import entitiesGASS.PagamentoeventoPK;
import entitiesGASS.Participa;
import entitiesGASS.beans.PagamentoeventoFacade;
import static entitiesGASS.jsf.beanWorker.getTheBean;
import entitiesGASS.jsf.util.PagingInfo;
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
public class PagamentoeventoController {

    public PagamentoeventoController() {
        pagingInfo = new PagingInfo();
        converter = new PagamentoeventoConverter();
    }
    private Pagamentoevento pagamentoevento = null;
    private List<Pagamentoevento> pagamentoeventoItems = null;
    private PagamentoeventoFacade jpaController = null;
    private PagamentoeventoConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "GASSv10PU")
    private EntityManagerFactory emf = null;
    //<Ana

    public String createSetupGASS() {
        reset(false);
        pagamentoevento = new Pagamentoevento();
        ParticipaController pc = (ParticipaController) getTheBean("participa");
        EventoController ev = (EventoController) getTheBean("evento");

        this.pagamentoevento.setDataPag(new Date());
        return "evento_pagarGASS";
    }

    public String createPagamentosEventos(Pagamentoevento p) throws Exception {
//        try {
        utx.begin();
//        } catch (Exception ex) {
//        }
        try {
            Exception transactionException = null;
            getJpaController().create(p);
            //          try {
            utx.commit();
//            } catch (javax.transaction.RollbackException ex) {
//                transactionException = ex;
//            } catch (Exception ex) {
//            }
//            if (transactionException == null) {
//        //        JsfUtil.addSuccessMessage("Pagamento do evento registado com sucesso.");
//            } else {
//      //          JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
//            }
        } catch (Exception e) {
//            try {
            utx.rollback();
//            } catch (Exception ex) {
//            }
            //       JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");

        }
        return "participa_listGASS";
    }

    public String editPagamentosEventos(Pagamentoevento p) {
//         String pagamentoeventoString = converter.getAsString(FacesContext.getCurrentInstance(), null, pagamentoevento);
//        String currentPagamentoeventoString = JsfUtil.getRequestParameter("jsfcrud.currentPagamentoevento");
//        if (pagamentoeventoString == null || pagamentoeventoString.length() == 0 || !pagamentoeventoString.equals(currentPagamentoeventoString)) {
//            String outcome = editSetup();
//            if ("pagamentoevento_edit".equals(outcome)) {
//                JsfUtil.addErrorMessage("Could not edit pagamentoevento. Try again.");
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
                //             JsfUtil.addSuccessMessage("Pagamentoevento was successfully updated.");
            } else {
                //            JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            //         JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return "";
    }

    public Pagamentoevento existeP(Participa p) {
        List<Pagamentoevento> todos = this.getJpaController().findAll();
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).getParticipa().equals(p)) {
                return todos.get(i);
            }
        }
        return null;
    }

    public int maxRecibo() {
        AssociacaoController ac = (AssociacaoController) getTheBean("associacao");
        List<Pagamentoevento> lpq = this.getJpaController().findAll();
        int max = 0;

        for (int i = 0; i < lpq.size(); i++) {
            if (lpq.get(i).getParticipa().getParticipaPK().getNIFasso() == ac.getAssociacao().getNif()) {
                if (lpq.get(i).getNrRecibo() != null && lpq.get(i).getNrRecibo() > max) {
                    max = lpq.get(i).getNrRecibo();
                }
            }
        }
        this.pagamentoevento.setMaxDefault(max + 1);
        return this.pagamentoevento.getMaxDefault();
    }

    public String createGASS() {
        ParticipaController pc = (ParticipaController) getTheBean("participa");
        this.pagamentoevento.setQuantia(pc.getParticipa().getValorAPagar());
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(pagamentoevento);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Pagamentoevento was successfully created.");
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
//Ana>
    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public PagamentoeventoFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (PagamentoeventoFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "pagamentoeventoJpa");
        }
        return jpaController;
    }

    public SelectItem[] getPagamentoeventoItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getPagamentoeventoItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Pagamentoevento getPagamentoevento() {
        if (pagamentoevento == null) {
            pagamentoevento = (Pagamentoevento) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentPagamentoevento", converter, null);
        }
        if (pagamentoevento == null) {
            pagamentoevento = new Pagamentoevento();
        }
        return pagamentoevento;
    }

    public String listSetup() {
        reset(true);
        return "pagamentoevento_list";
    }

    public String createSetup() {
        reset(false);
        pagamentoevento = new Pagamentoevento();
        pagamentoevento.setPagamentoeventoPK(new PagamentoeventoPK());
        return "pagamentoevento_create";
    }

    public String create() {
        pagamentoevento.getPagamentoeventoPK().setEmail(pagamentoevento.getParticipa().getParticipaPK().getEmail());
        pagamentoevento.getPagamentoeventoPK().setNIFAssociacao(pagamentoevento.getParticipa().getParticipaPK().getNIFasso());
        pagamentoevento.getPagamentoeventoPK().setIDEvento(pagamentoevento.getParticipa().getParticipaPK().getIDEvento());
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(pagamentoevento);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Pagamentoevento was successfully created.");
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
        return scalarSetup("pagamentoevento_detail");
    }

    public String editSetup() {
        return scalarSetup("pagamentoevento_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        pagamentoevento = (Pagamentoevento) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentPagamentoevento", converter, null);
        if (pagamentoevento == null) {
            String requestPagamentoeventoString = JsfUtil.getRequestParameter("jsfcrud.currentPagamentoevento");
            JsfUtil.addErrorMessage("The pagamentoevento with id " + requestPagamentoeventoString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        pagamentoevento.getPagamentoeventoPK().setEmail(pagamentoevento.getParticipa().getParticipaPK().getEmail());
        pagamentoevento.getPagamentoeventoPK().setNIFAssociacao(pagamentoevento.getParticipa().getParticipaPK().getNIFasso());
        pagamentoevento.getPagamentoeventoPK().setIDEvento(pagamentoevento.getParticipa().getParticipaPK().getIDEvento());
        String pagamentoeventoString = converter.getAsString(FacesContext.getCurrentInstance(), null, pagamentoevento);
        String currentPagamentoeventoString = JsfUtil.getRequestParameter("jsfcrud.currentPagamentoevento");
        if (pagamentoeventoString == null || pagamentoeventoString.length() == 0 || !pagamentoeventoString.equals(currentPagamentoeventoString)) {
            String outcome = editSetup();
            if ("pagamentoevento_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit pagamentoevento. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(pagamentoevento);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Pagamentoevento was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentPagamentoevento");
        PagamentoeventoPK id = converter.getId(idAsString);
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
                JsfUtil.addSuccessMessage("Pagamentoevento was successfully deleted.");
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

    public List<Pagamentoevento> getPagamentoeventoItems() {
        if (pagamentoeventoItems == null) {
            getPagingInfo();
            pagamentoeventoItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return pagamentoeventoItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "pagamentoevento_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "pagamentoevento_list";
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
        pagamentoevento = null;
        pagamentoeventoItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Pagamentoevento newPagamentoevento = new Pagamentoevento();
        newPagamentoevento.setPagamentoeventoPK(new PagamentoeventoPK());
        String newPagamentoeventoString = converter.getAsString(FacesContext.getCurrentInstance(), null, newPagamentoevento);
        String pagamentoeventoString = converter.getAsString(FacesContext.getCurrentInstance(), null, pagamentoevento);
        if (!newPagamentoeventoString.equals(pagamentoeventoString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
}
