/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Cargo;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import entitiesGASS.jsf.util.JsfUtil;
import entitiesGASS.CargoPK;
import entitiesGASS.beans.CargoFacade;
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
public class CargoController {

    public CargoController() {
        pagingInfo = new PagingInfo();
        converter = new CargoConverter();
    }
    private Cargo cargo = null;
    private List<Cargo> cargoItems = null;
    private CargoFacade jpaController = null;
    private CargoConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "GASSv10PU")
    private EntityManagerFactory emf = null;

    public void setCargo(Cargo t){
        cargo=t;
    }
    
    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public CargoFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (CargoFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "cargoJpa");
        }
        return jpaController;
    }

    public SelectItem[] getCargoItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getCargoItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Cargo getCargo() {
        if (cargo == null) {
            cargo = (Cargo) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCargo", converter, null);
        }
        if (cargo == null) {
            cargo = new Cargo();
        }
        return cargo;
    }

    public String listSetup() {
        reset(true);
        return "cargo_list";
    }

    public String createSetup() {
        reset(false);
        cargo = new Cargo();
        cargo.setCargoPK(new CargoPK());
        return "cargo_create";
    }

    public String create() {
        // TODO: no setter methods were found in your primary key class
        //    entitiesGASS.CargoPK
        // and therefore initialization code need manual adjustments.
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(cargo);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Cargo was successfully created.");
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
        return scalarSetup("cargo_detail");
    }

    public String editSetup() {
        return scalarSetup("cargo_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        cargo = (Cargo) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCargo", converter, null);
        if (cargo == null) {
            String requestCargoString = JsfUtil.getRequestParameter("jsfcrud.currentCargo");
            JsfUtil.addErrorMessage("The cargo with id " + requestCargoString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        // TODO: no setter methods were found in your primary key class
        //    entitiesGASS.CargoPK
        // and therefore initialization code need manual adjustments.
        String cargoString = converter.getAsString(FacesContext.getCurrentInstance(), null, cargo);
        String currentCargoString = JsfUtil.getRequestParameter("jsfcrud.currentCargo");
        if (cargoString == null || cargoString.length() == 0 || !cargoString.equals(currentCargoString)) {
            String outcome = editSetup();
            if ("cargo_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit cargo. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(cargo);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Cargo was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentCargo");
        CargoPK id = converter.getId(idAsString);
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
                JsfUtil.addSuccessMessage("Cargo was successfully deleted.");
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

    public List<Cargo> getCargoItems() {
        if (cargoItems == null) {
            getPagingInfo();
            cargoItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return cargoItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "cargo_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "cargo_list";
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
        cargo = null;
        cargoItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Cargo newCargo = new Cargo();
        newCargo.setCargoPK(new CargoPK());
        String newCargoString = converter.getAsString(FacesContext.getCurrentInstance(), null, newCargo);
        String cargoString = converter.getAsString(FacesContext.getCurrentInstance(), null, cargo);
        if (!newCargoString.equals(cargoString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
