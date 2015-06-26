/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Associacao;
import entitiesGASS.Estado;
import entitiesGASS.Tiposocio;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import entitiesGASS.jsf.util.JsfUtil;
import entitiesGASS.TiposocioPK;
import entitiesGASS.beans.TiposocioFacade;
import static entitiesGASS.jsf.beanWorker.getTheBean;
import entitiesGASS.jsf.util.PagingInfo;
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
public class TiposocioController {

    public TiposocioController() {
        pagingInfo = new PagingInfo();
        converter = new TiposocioConverter();
    }
    private Tiposocio tiposocio = null;
    private List<Tiposocio> tiposocioItems = null;
    private TiposocioFacade jpaController = null;
    private TiposocioConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "GASSv10PU")
    private EntityManagerFactory emf = null;
    
    
    // <Ana
    public SelectItem[] getTiposocioAssocItemsAvailableSelectOne() {
        AssociacaoController ac = (AssociacaoController) getTheBean("associacao");
        List<Tiposocio> lista = (List<Tiposocio>) getJpaController().findAll();
        for (int i = 0; i < lista.size(); i++) {
            if (!lista.get(i).getAssociacao().equals(ac.getAssociacao())) {
                lista.remove(i);
                i--;
            }
        }
        return JsfUtil.getSelectItems(lista, true);
    }
//Ana>

    public void updateList(Associacao a){
        List<Tiposocio> lista=getJpaController().findAll();
        for(int i=0; i<lista.size(); i++){
            if(!lista.get(i).getAssociacao().equals(a)){
                lista.remove(i);
                i--;
            }
        }
        tiposocioItems=lista;
    }
    
    public String editGass() {
        tiposocio.getTiposocioPK().setNIFassociacao(tiposocio.getAssociacao().getNif());
        String tiposocioString = converter.getAsString(FacesContext.getCurrentInstance(), null, tiposocio);
        String currentTiposocioString = JsfUtil.getRequestParameter("jsfcrud.currentTiposocio");
        if (tiposocioString == null || tiposocioString.length() == 0 || !tiposocioString.equals(currentTiposocioString)) {
            String outcome = editSetup();
            if ("tiposocio_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit tiposocio. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(tiposocio);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Tiposocio was successfully updated.");
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
        updateList(tiposocio.getAssociacao());
        return "gassDetail";
    }
    
    public String createSetupGass() {
        reset(false);
        tiposocio = new Tiposocio();
        tiposocio.setTiposocioPK(new TiposocioPK());
        return "gasstiponew";
    }
    
    public String createGass() {
        AssociacaoController a = (AssociacaoController) beanWorker.getTheBean("associacao");
        if (tiposocio.getTiposocioPK().getNome().equals("")) {
            JsfUtil.addErrorMessage("Nome é requerido.");
            updateList(a.getAssociacao());
            return "gassDetail";
        }
        if (tiposocio.getQuantia() == null) {
            JsfUtil.addErrorMessage("Quantia é requerida.");
            updateList(a.getAssociacao());
            return "gassDetail";
        }

        tiposocio.setAssociacao(a.getAssociacao());
        tiposocio.getTiposocioPK().setNIFassociacao(tiposocio.getAssociacao().getNif());

        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(tiposocio);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Tiposocio was successfully created.");
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
        updateList(a.getAssociacao());
        return "gassDetail";
    }
    
    public String editSetupGass() {
        tiposocio = (Tiposocio) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentTiposocio", converter, null);

        return "gasstipoedit";
    }
    
    public String removeTodos() {
        AssociacaoController a = (AssociacaoController) beanWorker.getTheBean("associacao");

        List<Tiposocio> lista = getJpaController().findAll();
        for (int i = 0; i < lista.size(); i++) {
            if (!lista.get(i).getAssociacao().equals(a.getAssociacao())) {
                lista.remove(i);
                i--;
            }
        }

        for (int i = 0; i < lista.size(); i++) {
            if (tiposocioItems.get(i).getCheckBox()) {
                try {
                    utx.begin();
                } catch (Exception ex) {
                }
                try {
                    Exception transactionException = null;
                    getJpaController().remove(lista.get(i));
                    try {
                        utx.commit();
                    } catch (javax.transaction.RollbackException ex) {
                        transactionException = ex;
                    } catch (Exception ex) {
                    }
                    if (transactionException == null) {
                        JsfUtil.addSuccessMessage("Tiposocio was successfully deleted.");
                    } else {
                        JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
                    }
                } catch (Exception e) {
                    try {
                        utx.rollback();
                    } catch (Exception ex) {
                    }
                    JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
                }
            }
        }
        updateList(a.getAssociacao());
        return "gassDetail";
    }

    public String removeGass(Tiposocio b) {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().remove(b);
            //System.out.println("ola3");
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Tiposocio was successfully deleted.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
        }

        updateList(b.getAssociacao());
        return "gassDetail";
    }
    
    
    
    
    
    
    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public TiposocioFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (TiposocioFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "tiposocioJpa");
        }
        return jpaController;
    }

    public SelectItem[] getTiposocioItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getTiposocioItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Tiposocio getTiposocio() {
        if (tiposocio == null) {
            tiposocio = (Tiposocio) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentTiposocio", converter, null);
        }
        if (tiposocio == null) {
            tiposocio = new Tiposocio();
        }
        return tiposocio;
    }

    public String listSetup() {
        reset(true);
        return "tiposocio_list";
    }

    public String createSetup() {
        reset(false);
        tiposocio = new Tiposocio();
        tiposocio.setTiposocioPK(new TiposocioPK());
        return "tiposocio_create";
    }

    public String create() {
        tiposocio.getTiposocioPK().setNIFassociacao(tiposocio.getAssociacao().getNif());
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(tiposocio);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Tiposocio was successfully created.");
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
        return scalarSetup("tiposocio_detail");
    }

    public String editSetup() {
        return scalarSetup("tiposocio_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        tiposocio = (Tiposocio) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentTiposocio", converter, null);
        if (tiposocio == null) {
            String requestTiposocioString = JsfUtil.getRequestParameter("jsfcrud.currentTiposocio");
            JsfUtil.addErrorMessage("The tiposocio with id " + requestTiposocioString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        tiposocio.getTiposocioPK().setNIFassociacao(tiposocio.getAssociacao().getNif());
        String tiposocioString = converter.getAsString(FacesContext.getCurrentInstance(), null, tiposocio);
        String currentTiposocioString = JsfUtil.getRequestParameter("jsfcrud.currentTiposocio");
        if (tiposocioString == null || tiposocioString.length() == 0 || !tiposocioString.equals(currentTiposocioString)) {
            String outcome = editSetup();
            if ("tiposocio_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit tiposocio. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(tiposocio);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Tiposocio was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentTiposocio");
        TiposocioPK id = converter.getId(idAsString);
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
                JsfUtil.addSuccessMessage("Tiposocio was successfully deleted.");
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

    public List<Tiposocio> getTiposocioItems() {
        if (tiposocioItems == null) {
            getPagingInfo();
            tiposocioItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return tiposocioItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "tiposocio_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "tiposocio_list";
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
        tiposocio = null;
        tiposocioItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Tiposocio newTiposocio = new Tiposocio();
        newTiposocio.setTiposocioPK(new TiposocioPK());
        String newTiposocioString = converter.getAsString(FacesContext.getCurrentInstance(), null, newTiposocio);
        String tiposocioString = converter.getAsString(FacesContext.getCurrentInstance(), null, tiposocio);
        if (!newTiposocioString.equals(tiposocioString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
