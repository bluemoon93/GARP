/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Botao;
import entitiesGASS.Estado;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import entitiesGASS.jsf.util.JsfUtil;
import entitiesGASS.EstadoPK;
import entitiesGASS.Metodopassagem;
import entitiesGASS.beans.BotaoFacade;
import entitiesGASS.beans.EstadoFacade;
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
public class EstadoController {

    public EstadoController() {
        pagingInfo = new PagingInfo();
        converter = new EstadoConverter();
    }
    private Estado estado = null;
    private List<Estado> estadoItems = null;
    private EstadoFacade jpaController = null;
    private EstadoConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "GASSv10PU")
    private EntityManagerFactory emf = null;
    private Estado estadoAux = null;

    public String setInicial(){
        
        List<Estado> lista = getJpaController().findAll();
        for(int i=0; i<lista.size(); i++){
            if(lista.get(i).getAssociacao().equals(estado.getAssociacao())){
                if(lista.get(i).getInicial()){
                    lista.get(i).seteInicial(false);
                    setEstado(lista.get(i));
                }
            }
        }
        estado = (Estado) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentEstado", converter, null);
        estado.seteInicial(true);
        setEstado(estado);
        return "";
    }
    
    public String setEstado(Estado tempState) {
        estado=tempState;
        
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(estado);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("O estado "+estado.getNome()+" foi alterado.");
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
    
    public void setEstadoAux(Estado aux) {
        estadoAux = aux;
    }

    public Estado getEstadoAux() {
        return estadoAux;
    }

    // <Ana
    public SelectItem[] getEstadoAssocItemsAvailableSelectOne() {
        AssociacaoController ac = (AssociacaoController) getTheBean("associacao");
        List<Estado> lista = (List<Estado>) getJpaController().findAll();
        for (int i = 0; i < lista.size(); i++) {
            if (!lista.get(i).getAssociacao().equals(ac.getAssociacao())) {
                lista.remove(i);
                i--;
            }
        }
        return JsfUtil.getSelectItems(lista, true);
    }
//Ana>

    public SelectItem[] getEstadoItemsAvailableSelectOneGass() {
        List<Estado> lista = (List<Estado>) getJpaController().findAll();
        for (int i = 0; i < lista.size(); i++) {
            if (!lista.get(i).getAssociacao().equals(estado.getAssociacao())) {
                lista.remove(i);
                i--;
            }
        }
        return JsfUtil.getSelectItems(lista, true);
    }

    public void updateDetail(List<Botao> lista) {
        estado.setBotaoCollection(lista);
    }

    public void updateMetodos() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        MetodopassagemFacade jpa1Controller = (MetodopassagemFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "metodopassagemJpa");
        List<Metodopassagem> lista = jpa1Controller.findAll();

        for (int i = 0; i < lista.size(); i++) {
            if (!lista.get(i).getEstado1().equals(estado)) {
                lista.remove(i);
                i--;
            }
        }
        estado.setMetodopassagemCollection1(lista);

        List<Metodopassagem> lista1 = jpa1Controller.findAll();

        for (int i = 0; i < lista1.size(); i++) {
            if (!lista1.get(i).getEstado().equals(estado)) {
                lista1.remove(i);
                i--;
            }
        }
        estado.setMetodopassagemCollection(lista1);
    }

    public void updateButtons() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        BotaoFacade jpa1Controller = (BotaoFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "botaoJpa");
        List<Botao> lista = jpa1Controller.findAll();

        for (int i = 0; i < lista.size(); i++) {
            if (!lista.get(i).getEstado().equals(estado)) {
                lista.remove(i);
                i--;
            }
        }
        estado.setBotaoCollection(lista);
    }

    public String removeTodos() {
        AssociacaoController ac = (AssociacaoController) getTheBean("associacao");
        Collection<Estado> cb = ac.getAssociacao().getEstadoCollection();
        Iterator<Estado> ib = cb.iterator();
        Estado aux;

        while (ib.hasNext()) {
            aux = ib.next();
            if (aux.getCheckBox()) {
                this.removeGass(aux);
                
            }
        }
        return "gassDetail";
    }

    public String removeGass(Estado b) {
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
                JsfUtil.addSuccessMessage("Estado was successfully deleted.");
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
        updateAssoc();
        return "gassDetail";

    }

    private void updateAssoc() {
        AssociacaoController temp = (AssociacaoController) getTheBean("associacao");
        List<Estado> lista = jpaController.findAll();
        
        for (int i = 0; i < lista.size(); i++) {
            if (!lista.get(i).getAssociacao().equals(temp.getAssociacao())) {
                lista.remove(i);
                i--;
            }
        }

        temp.updateDetail(lista);
    }

    public String createGass() {

        AssociacaoController temp = (AssociacaoController) getTheBean("associacao");
        estado.setAssociacao(temp.getAssociacao());
        estado.getEstadoPK().setIDestado(getNextNumber(estado.getAssociacao().getNif()));
        estado.getEstadoPK().setNIFassociacao(estado.getAssociacao().getNif());

        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(estado);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Estado was successfully created.");
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
        updateAssoc();

        return "estadoDetailGass";
    }

    public String detailSetupGass() {
        estado = (Estado) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentEstado", converter, null);
        updateButtons();
        updateMetodos();
        return "estadoDetailGass";
    }

    public String createSetupGass() {
        reset(false);
        estado = new Estado();
        estado.setEstadoPK(new EstadoPK());
        return "gassEstadoNew";
    }

    public String editGassSetup() {
        return scalarSetup("estadoEditGass");
    }

    public String editGass() {
        estado.getEstadoPK().setNIFassociacao(estado.getAssociacao().getNif());
        String estadoString = converter.getAsString(FacesContext.getCurrentInstance(), null, estado);
        String currentEstadoString = JsfUtil.getRequestParameter("jsfcrud.currentEstado");
        if (estadoString == null || estadoString.length() == 0 || !estadoString.equals(currentEstadoString)) {
            String outcome = editSetup();
            if ("estado_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit estado. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(estado);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Estado was successfully updated.");
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

    private int getNextNumber(int nif) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (EstadoFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "estadoJpa");
        List<Estado> lista = jpaController.findAll();

        int number = 1;
        boolean repeat = true;
        while (repeat) {
            repeat = false;
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getAssociacao().getNif() == nif) {
                    if (lista.get(i).getEstadoPK().getIDestado() == number) {
                        number++;
                        repeat = true;
                        break;
                    }
                }
            }
        }
        return number;
    }

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public EstadoFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (EstadoFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "estadoJpa");
        }
        return jpaController;
    }

    public SelectItem[] getEstadoItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getEstadoItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Estado getEstado() {
        if (estado == null) {
            estado = (Estado) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentEstado", converter, null);
        }
        if (estado == null) {
            estado = new Estado();
        }
        return estado;
    }

    public String listSetup() {
        reset(true);
        return "estado_list";
    }

    public String createSetup() {
        reset(false);
        estado = new Estado();
        estado.setEstadoPK(new EstadoPK());
        return "estado_create";
    }

    public String create() {
        estado.getEstadoPK().setNIFassociacao(estado.getAssociacao().getNif());
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(estado);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Estado was successfully created.");
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
        return scalarSetup("estado_detail");
    }

    public String editSetup() {
        return scalarSetup("estado_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        estado = (Estado) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentEstado", converter, null);
        if (estado == null) {
            String requestEstadoString = JsfUtil.getRequestParameter("jsfcrud.currentEstado");
            JsfUtil.addErrorMessage("The estado with id " + requestEstadoString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        estado.getEstadoPK().setNIFassociacao(estado.getAssociacao().getNif());
        String estadoString = converter.getAsString(FacesContext.getCurrentInstance(), null, estado);
        String currentEstadoString = JsfUtil.getRequestParameter("jsfcrud.currentEstado");
        if (estadoString == null || estadoString.length() == 0 || !estadoString.equals(currentEstadoString)) {
            String outcome = editSetup();
            if ("estado_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit estado. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(estado);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Estado was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentEstado");
        EstadoPK id = converter.getId(idAsString);
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
                JsfUtil.addSuccessMessage("Estado was successfully deleted.");
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

    public List<Estado> getEstadoItems() {
        if (estadoItems == null) {
            getPagingInfo();
            estadoItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return estadoItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "estado_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "estado_list";
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
        estado = null;
        estadoItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Estado newEstado = new Estado();
        newEstado.setEstadoPK(new EstadoPK());
        String newEstadoString = converter.getAsString(FacesContext.getCurrentInstance(), null, newEstado);
        String estadoString = converter.getAsString(FacesContext.getCurrentInstance(), null, estado);
        if (!newEstadoString.equals(estadoString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
}
