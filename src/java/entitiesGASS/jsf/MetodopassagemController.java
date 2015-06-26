/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Estado;
import entitiesGASS.Metodopassagem;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import entitiesGASS.jsf.util.JsfUtil;
import entitiesGASS.MetodopassagemPK;
import entitiesGASS.beans.MetodopassagemFacade;
import static entitiesGASS.jsf.beanWorker.getTheBean;
import entitiesGASS.jsf.util.PagingInfo;
import java.util.Collection;
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
public class MetodopassagemController {

    public MetodopassagemController() {
        pagingInfo = new PagingInfo();
        converter = new MetodopassagemConverter();
    }
    private Metodopassagem metodopassagem = null;
    private List<Metodopassagem> metodopassagemItems = null;
    private MetodopassagemFacade jpaController = null;
    private MetodopassagemConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "GASSv10PU")
    private EntityManagerFactory emf = null;

    public String createSetupGass() {
        //reset(false);
        metodopassagem = new Metodopassagem();
        metodopassagem.setMetodopassagemPK(new MetodopassagemPK());
        return "gassmetodonew";
        
    }
    
    private int getNextNumber(Estado x) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (MetodopassagemFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "metodopassagemJpa");
        List<Metodopassagem> lista = jpaController.findAll();
        
        int number=1;
        boolean repeat=true;
        while(repeat){
            repeat=false;
            for (int i = 0; i < lista.size(); i++) {
                if(lista.get(i).getEstado1()==x){
                    if(lista.get(i).getMetodopassagemPK().getIDmetodo()==number){
                        number++;
                        repeat=true;
                        break;
                    }
                }
            }
        }
        return number;
    }
    
    public String createGass() {
        if(metodopassagem.getAnos()<0){
            JsfUtil.addErrorMessage("Nº de anos inválido.");
            return "";
        }
        if(metodopassagem.getMeses()<0 || metodopassagem.getMeses()>12){
            JsfUtil.addErrorMessage("Nº de meses inválido.");
            return "";
        }
        if(metodopassagem.getDias()<0 || metodopassagem.getDias()>31){
            JsfUtil.addErrorMessage("Nº de dias inválido.");
            return "";
        }
        
        EstadoController x=(EstadoController) getTheBean("estado");
        metodopassagem.setEstado1(x.getEstado());
        metodopassagem.getMetodopassagemPK().setIDmetodo(getNextNumber(x.getEstado()));
        
        metodopassagem.getMetodopassagemPK().setIDestado1(metodopassagem.getEstado1().getEstadoPK().getIDestado());
        metodopassagem.getMetodopassagemPK().setIDestado2(metodopassagem.getEstado().getEstadoPK().getIDestado());
        metodopassagem.getMetodopassagemPK().setNIFassociacao(metodopassagem.getEstado1().getEstadoPK().getNIFassociacao());
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(metodopassagem);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Metodopassagem was successfully created.");
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
        x.updateMetodos();
        return "gassmetododetail";
    }
    
    public String detailSetupGass() {
        return scalarSetup("gassmetododetail");
    }
    
    public String editSetupGass() {
        return scalarSetup("gassmetodoedit");
    }
    
    public String editGass() {
        metodopassagem.getMetodopassagemPK().setIDestado2(metodopassagem.getEstado().getEstadoPK().getIDestado());
        metodopassagem.getMetodopassagemPK().setIDestado1(metodopassagem.getEstado1().getEstadoPK().getIDestado());
        metodopassagem.getMetodopassagemPK().setNIFassociacao(metodopassagem.getEstado1().getEstadoPK().getNIFassociacao());
        String metodopassagemString = converter.getAsString(FacesContext.getCurrentInstance(), null, metodopassagem);
        String currentMetodopassagemString = JsfUtil.getRequestParameter("jsfcrud.currentMetodopassagem");
        if (metodopassagemString == null || metodopassagemString.length() == 0 || !metodopassagemString.equals(currentMetodopassagemString)) {
            String outcome = editSetup();
            if ("metodopassagem_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit metodopassagem. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(metodopassagem);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Metodopassagem was successfully updated.");
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
        return detailSetupGass();
    }
    
    public String removeTodos() {
        AssociacaoController ac = (AssociacaoController) getTheBean("associacao");
        List<Metodopassagem> lb = this.getJpaController().findAll();
        EstadoController ec = (EstadoController) getTheBean("estado");
        Collection<Metodopassagem> cb = ec.getEstado().getMetodopassagemCollection1();
        Iterator<Metodopassagem> ib = cb.iterator();
        Metodopassagem aux;
              
        while(ib.hasNext()){
            aux = ib.next();            
            if(aux.getCheckBox()){
                this.removeGass(aux);
            }
        }
        
         return "estadoDetailGass";
    }

    
    public void removeGass(Metodopassagem b) {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().remove(b);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Metodopassagem was successfully deleted.");
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
        EstadoController x=(EstadoController) getTheBean("estado");
        x.updateMetodos();
 
    }
    
    
    
    
    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public MetodopassagemFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (MetodopassagemFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "metodopassagemJpa");
        }
        return jpaController;
    }

    public SelectItem[] getMetodopassagemItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getMetodopassagemItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Metodopassagem getMetodopassagem() {
        if (metodopassagem == null) {
            metodopassagem = (Metodopassagem) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentMetodopassagem", converter, null);
        }
        if (metodopassagem == null) {
            metodopassagem = new Metodopassagem();
        }
        return metodopassagem;
    }

    public String listSetup() {
        reset(true);
        return "metodopassagem_list";
    }

    public String createSetup() {
        reset(false);
        metodopassagem = new Metodopassagem();
        metodopassagem.setMetodopassagemPK(new MetodopassagemPK());
        return "metodopassagem_create";
    }

    public String create() {
        metodopassagem.getMetodopassagemPK().setIDestado1(metodopassagem.getEstado1().getEstadoPK().getIDestado());
        metodopassagem.getMetodopassagemPK().setIDestado2(metodopassagem.getEstado().getEstadoPK().getIDestado());
        metodopassagem.getMetodopassagemPK().setNIFassociacao(metodopassagem.getEstado1().getEstadoPK().getNIFassociacao());
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(metodopassagem);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Metodopassagem was successfully created.");
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
        return scalarSetup("metodopassagem_detail");
    }

    public String editSetup() {
        return scalarSetup("metodopassagem_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        metodopassagem = (Metodopassagem) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentMetodopassagem", converter, null);
        if (metodopassagem == null) {
            String requestMetodopassagemString = JsfUtil.getRequestParameter("jsfcrud.currentMetodopassagem");
            JsfUtil.addErrorMessage("The metodopassagem with id " + requestMetodopassagemString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        metodopassagem.getMetodopassagemPK().setIDestado1(metodopassagem.getEstado1().getEstadoPK().getIDestado());
        metodopassagem.getMetodopassagemPK().setIDestado2(metodopassagem.getEstado().getEstadoPK().getIDestado());
        metodopassagem.getMetodopassagemPK().setNIFassociacao(metodopassagem.getEstado1().getEstadoPK().getNIFassociacao());
        String metodopassagemString = converter.getAsString(FacesContext.getCurrentInstance(), null, metodopassagem);
        String currentMetodopassagemString = JsfUtil.getRequestParameter("jsfcrud.currentMetodopassagem");
        if (metodopassagemString == null || metodopassagemString.length() == 0 || !metodopassagemString.equals(currentMetodopassagemString)) {
            String outcome = editSetup();
            if ("metodopassagem_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit metodopassagem. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(metodopassagem);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Metodopassagem was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentMetodopassagem");
        MetodopassagemPK id = converter.getId(idAsString);
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
                JsfUtil.addSuccessMessage("Metodopassagem was successfully deleted.");
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

    public List<Metodopassagem> getMetodopassagemItems() {
        if (metodopassagemItems == null) {
            getPagingInfo();
            metodopassagemItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return metodopassagemItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "metodopassagem_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "metodopassagem_list";
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
        metodopassagem = null;
        metodopassagemItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Metodopassagem newMetodopassagem = new Metodopassagem();
        newMetodopassagem.setMetodopassagemPK(new MetodopassagemPK());
        String newMetodopassagemString = converter.getAsString(FacesContext.getCurrentInstance(), null, newMetodopassagem);
        String metodopassagemString = converter.getAsString(FacesContext.getCurrentInstance(), null, metodopassagem);
        if (!newMetodopassagemString.equals(metodopassagemString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
