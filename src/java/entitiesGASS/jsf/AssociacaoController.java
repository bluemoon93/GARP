/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Associacao;
import entitiesGASS.Estado;
import entitiesGASS.beans.AssociacaoFacade;
import entitiesGASS.beans.EstadoFacade;
import static entitiesGASS.jsf.beanWorker.getTheBean;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import entitiesGASS.jsf.util.JsfUtil;
import entitiesGASS.jsf.util.PagingInfo;
import java.io.IOException;
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
public class AssociacaoController {

    public AssociacaoController() {
        pagingInfo = new PagingInfo();
        converter = new AssociacaoConverter();
    }
    private Associacao associacao = null;
    private List<Associacao> associacaoItems = null;
    private AssociacaoFacade jpaController = null;
    private AssociacaoConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "GASSv10PU")
    private EntityManagerFactory emf = null;
    private char where = 'z';
    private List<Estado> meusEstados = null;

    public List<Estado> getMeusEstados() {
        EstadoController ec = (EstadoController) getTheBean("estado");
        this.meusEstados = ec.getJpaController().findAll();

        for (int i = 0; i < this.meusEstados.size(); i++) {
            if (!this.meusEstados.get(i).getAssociacao().equals(this.getAssociacao())) {
                this.meusEstados.remove(i);
                i--;
            }
        }
        this.associacao.setEstadoCollection(meusEstados);
        return meusEstados;
    }

    public char getWhere() {
        return where;
    }

    public String sigLink() {
        return this.getAssociacao().getUrl();
    }

    public String editGass2() {

        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(associacao);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Associacao was successfully updated.");
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

    public void gotoEstados() throws IOException {
        where = 'A';
        FacesContext.getCurrentInstance().getExternalContext().redirect("GASSDetail.jsp#A");
    }

    public void gotoBotoes() throws IOException {
        where = 'B';
        FacesContext.getCurrentInstance().getExternalContext().redirect("GASSDetail.jsp#B");
    }

    public void gotoMetodos() throws IOException {
        where = 'C';
        FacesContext.getCurrentInstance().getExternalContext().redirect("GASSDetail.jsp#C");
    }

    public void setAssociacao(Associacao x) {
        associacao = x;
    }

    public void updateDetail(List<Estado> lista) {
        System.out.println(lista.size() + " <<--");
        if (associacao != null) {
            System.out.println("assoc -> " + associacao);
        } else {
            System.out.println("es um genio");
        }
        associacao.setEstadoCollection(lista);
    }

    private List<Estado> getStateList() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        EstadoFacade EjpaController = (EstadoFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "estadoJpa");
        List<Estado> lista = EjpaController.findAll();

        for (int i = 0; i < lista.size(); i++) {
            if (!lista.get(i).getAssociacao().equals(associacao)) {
                lista.remove(i);
                i--;
            }
        }

        return lista;
    }

    public String gassHome() {
        return "gass";
    }

    public String loginAssoc() {
        getAssociacaoItems();
        for (int i = 0; i < associacaoItems.size(); i++) {
            if (associacao.getEmail().equals(associacaoItems.get(i).getEmail())) {
                if (associacao.getPasswd().equals(associacaoItems.get(i).getPasswd())) {
                    associacao = associacaoItems.get(i);

                    associacao.setEstadoCollection(getStateList());
                    OcupaController x = (OcupaController) getTheBean("ocupa");
                    x.updateList();
                    TiposocioController y = (TiposocioController) getTheBean("tiposocio");
                    y.updateList(associacao);

                    return "gassDetail";
                }
            }
        }

        JsfUtil.addErrorMessage("Associacao nao existente");
        return "";
    }

    public String removeGass() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentAssociacao");
        Integer id = new Integer(idAsString);
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
                JsfUtil.addSuccessMessage("Associacao was successfully deleted.");
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
        return "gass";
    }


    public String createGass() {
        //System.out.println(associacao);
        if(!beanWorker.validarNif(associacao.getNif())){
             JsfUtil.addErrorMessage("NIF Inválido! ");
            return ""; 
        }
        
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(associacao);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
                JsfUtil.addErrorMessage("NIF já existente");
                return "gass";
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Associacao was successfully created.");
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
        OcupaController x = (OcupaController) getTheBean("ocupa");
        x.updateList();

        // if windows{
        beanWorker.createPages(associacao.getNif(), associacao.getUrl());

        TiposocioController y = (TiposocioController) getTheBean("tiposocio");
        y.updateList(associacao);

        return "gassDetail";
    }

    public String detailSetupGass() {
        associacao.setEstadoCollection(getStateList());
        OcupaController x = (OcupaController) getTheBean("ocupa");
        x.updateList();
        TiposocioController y = (TiposocioController) getTheBean("tiposocio");
        y.updateList(associacao);
        return "gassDetail";
    }

    public String editSetupGass() {
        return scalarSetup("gassEditAssoc");
    }

    public String editGass() {
        String associacaoString = converter.getAsString(FacesContext.getCurrentInstance(), null, associacao);
        String currentAssociacaoString = JsfUtil.getRequestParameter("jsfcrud.currentAssociacao");
        if (associacaoString == null || associacaoString.length() == 0 || !associacaoString.equals(currentAssociacaoString)) {
            String outcome = editSetupGass();
            if ("associacao_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit associacao. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(associacao);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Associacao was successfully updated.");
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

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public AssociacaoFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (AssociacaoFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "associacaoJpa");
        }
        return jpaController;
    }

    public SelectItem[] getAssociacaoItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getAssociacaoItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Associacao getAssociacao() {
        if (associacao == null) {
            associacao = (Associacao) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentAssociacao", converter, null);
        }
        if (associacao == null) {
            associacao = new Associacao();
        }
        return associacao;
    }

    public String listSetup() {
        reset(true);
        return "associacao_list";
    }

    public String createSetup() {
        reset(false);
        associacao = new Associacao();
        return "associacao_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(associacao);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Associacao was successfully created.");
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
        return scalarSetup("associacao_detail");
    }

    public String editSetup() {
        return scalarSetup("associacao_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        associacao = (Associacao) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentAssociacao", converter, null);
        if (associacao == null) {
            String requestAssociacaoString = JsfUtil.getRequestParameter("jsfcrud.currentAssociacao");
            JsfUtil.addErrorMessage("The associacao with id " + requestAssociacaoString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String associacaoString = converter.getAsString(FacesContext.getCurrentInstance(), null, associacao);
        String currentAssociacaoString = JsfUtil.getRequestParameter("jsfcrud.currentAssociacao");
        if (associacaoString == null || associacaoString.length() == 0 || !associacaoString.equals(currentAssociacaoString)) {
            String outcome = editSetup();
            if ("associacao_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit associacao. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(associacao);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Associacao was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentAssociacao");
        Integer id = new Integer(idAsString);
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
                JsfUtil.addSuccessMessage("Associacao was successfully deleted.");
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

    public List<Associacao> getAssociacaoItems() {
        if (associacaoItems == null) {
            getPagingInfo();
            associacaoItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return associacaoItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "associacao_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "associacao_list";
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

    public void reset(boolean resetFirstItem) {
        associacao = null;
        associacaoItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Associacao newAssociacao = new Associacao();
        String newAssociacaoString = converter.getAsString(FacesContext.getCurrentInstance(), null, newAssociacao);
        String associacaoString = converter.getAsString(FacesContext.getCurrentInstance(), null, associacao);
        if (!newAssociacaoString.equals(associacaoString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
}
