/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Associacao;
import entitiesGASS.Candidato;
import entitiesGASS.Cargo;
import entitiesGASS.Estado;
import entitiesGASS.Ocupa;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import entitiesGASS.jsf.util.JsfUtil;
import entitiesGASS.OcupaPK;
import entitiesGASS.Socio;
import entitiesGASS.Tiposocio;
import entitiesGASS.beans.OcupaFacade;
import static entitiesGASS.jsf.beanWorker.getTheBean;
import entitiesGASS.jsf.util.PagingInfo;
import java.io.IOException;
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
public class OcupaController {

    public OcupaController() {
        pagingInfo = new PagingInfo();
        converter = new OcupaConverter();
    }
    private Ocupa ocupa = null;
    private List<Ocupa> ocupaItems = null;
    private OcupaFacade jpaController = null;
    private OcupaConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "GASSv10PU")
    private EntityManagerFactory emf = null;
    private boolean read=false;

    public boolean getRead() {
        return read;
    }

    public void setRead(boolean RW) {
        this.read = RW;
    }
    
    
    public String criarCandySoss() {
        return "criarcandysossGASS";
    }
  
    public String createGass2() {
        ocupaWorker o = (ocupaWorker) getTheBean("ocupaWorker");
         if(!beanWorker.validarNif(o.getNif())){
             JsfUtil.addErrorMessage("NIF Inválido!");
            return ""; }
        AssociacaoController assoc = (AssociacaoController) getTheBean("associacao");
        //candidato
        int ocupa_nifAssoc = assoc.getAssociacao().getNif();
        String ocupa_email = o.getEmail();
        String ocupa_pass = o.getPassword();
        String ocupa_nome = o.getNome();
        Date ocupa_dataNasc = o.getDataNasc();
        String ocupa_tlm = o.getTelefone();

        //socio
        int ocupa_nif = o.getNif();

        //ocupa
        Date ocupa_dataFim = o.getDataFim();
        Date ocupa_dataInicio = o.getDataInicio();
        Cargo ocupa_cargo = o.getCargo();

        Candidato tempcand = new Candidato(ocupa_email, ocupa_nifAssoc);
        tempcand.setNome(ocupa_nome);
        tempcand.setPalavraPasse(ocupa_pass);
        tempcand.setDataNasc(ocupa_dataNasc);
        tempcand.setTelemovel(ocupa_tlm);
        tempcand.setAssociacao(assoc.getAssociacao());

        CandidatoController x = (CandidatoController) getTheBean("candidato");
        x.setCandy(tempcand);

        Socio tempSoss = new Socio(ocupa_nif, ocupa_nifAssoc);
        tempSoss.setCandidato(tempcand);
        Date temp = new Date();
        tempSoss.setDataModificacao(temp);
        Estado state = new Estado(1, ocupa_nifAssoc);
        tempSoss.setEstado(state);
        SocioController s = (SocioController) getTheBean("socio");
        tempSoss.setNrSocio(s.getNextNumber(ocupa_nifAssoc));
        tempSoss.setTiposocio(o.getTipo());
        s.setSocio(tempSoss);

        OcupaPK tmp = new OcupaPK(ocupa_cargo.getCargoPK().getNome(), ocupa_cargo.getCargoPK().getGrupo(), ocupa_nif, ocupa_nifAssoc);
        Ocupa tempOcupa = new Ocupa(tmp, ocupa_dataInicio, ocupa_dataFim);
        tempOcupa.setDataFim(ocupa_dataFim);
        tempOcupa.setCargo(ocupa_cargo);
        tempOcupa.setDataInicio(ocupa_dataInicio);
        tempOcupa.setSocio(tempSoss);

        ocupa = tempOcupa;

        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(ocupa);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
                JsfUtil.addErrorMessage("Candidato com esse cargo já existente.");
                return "";
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Ocupa was successfully created.");
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
        updateList();
        return "gassDetail";
    }
    
    public void setCargo(Ocupa a){
        ocupa=a;
    }
    
    public String removeTodos() {
        

        
        AssociacaoController ac = (AssociacaoController) getTheBean("associacao");
        List<Ocupa> lb = this.getJpaController().findAll();

        for (int i = 0; i < lb.size(); i++) {
            if (lb.get(i).getOcupaPK().getNIFAssociacao()!=(ac.getAssociacao().getNif())) {
                lb.remove(i);
                i--;
            }
        }
        
       for (int i = 0; i < lb.size(); i++) {
            //System.out.println(lista.get(i).getTiposocioPK().getNome());
            if (ocupaItems.get(i).getCheckBox()) {
                try {
                    utx.begin();
                } catch (Exception ex) {
                }
                try {
                    Exception transactionException = null;
                    getJpaController().remove(lb.get(i));
                    //System.out.println("ola3");
                    try {
                        utx.commit();
                    } catch (javax.transaction.RollbackException ex) {
                        transactionException = ex;
                    } catch (Exception ex) {
                    }
                    if (transactionException == null) {
                        JsfUtil.addSuccessMessage("Ocupa was successfully deleted.");
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
                //System.out.println("removing!");
            }
        }
        updateList();
        return "gassDetail";
    }

    public String removeGass(Ocupa b) {
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
                JsfUtil.addSuccessMessage("Ocupa was successfully deleted.");
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
        updateList();
        return "gassDetail";
    }
    
    public String editGass() {
        ocupa.getOcupaPK().setNIFAssociacao(ocupa.getSocio().getSocioPK().getNIFAssociacao());
        ocupa.getOcupaPK().setGrupoC(ocupa.getCargo().getCargoPK().getGrupo());
        ocupa.getOcupaPK().setNIFsocio(ocupa.getSocio().getSocioPK().getNif());
        ocupa.getOcupaPK().setNomeC(ocupa.getCargo().getCargoPK().getNome());
        /*String ocupaString = converter.getAsString(FacesContext.getCurrentInstance(), null, ocupa);
        String currentOcupaString = JsfUtil.getRequestParameter("jsfcrud.currentOcupa");
        if (ocupaString == null || ocupaString.length() == 0 || !ocupaString.equals(currentOcupaString)) {
            String outcome = editSetup();
            if ("ocupa_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit ocupa. Try again.");
            }
            return outcome;
        }*/
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(ocupa);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Ocupa was successfully updated.");
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
        updateList();
        return "gassDetail";
    }
    
    public String editSetupGass() {
        return scalarSetup("gassocupaedit");
    }
    
    public String createGass() {
        ocupa.getOcupaPK().setNIFAssociacao(ocupa.getSocio().getSocioPK().getNIFAssociacao());
        ocupa.getOcupaPK().setGrupoC(ocupa.getCargo().getCargoPK().getGrupo());
        ocupa.getOcupaPK().setNIFsocio(ocupa.getSocio().getSocioPK().getNif());
        ocupa.getOcupaPK().setNomeC(ocupa.getCargo().getCargoPK().getNome());
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(ocupa);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
                JsfUtil.addErrorMessage("Candidato com esse cargo já existente.");
                return "";
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Ocupa was successfully created.");
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
        updateList();
        return "gassocupaedit";
    }
    
    public String createSetupGass() {
        reset(false);
        ocupa = new Ocupa();
        ocupa.setOcupaPK(new OcupaPK());
        return "gassocupanew";
    }
    
    public List<Socio> getSociosForAssoc() throws IOException{
        SocioController x=(SocioController) getTheBean("socio");
        List<Socio> lista= x.getJpaController().findAll();
        AssociacaoController xc = (AssociacaoController) getTheBean("associacao");
        
        for(int i=0; i<lista.size(); i++){
            if(!lista.get(i).getCandidato().getAssociacao().equals(xc.getAssociacao())){
                lista.remove(i);
                i--;
            }
        }
        
        x.setOrderAux(1);
        Collections.sort(lista);
        return lista;
        
    }
    
    public List<Socio> getSociosForAssoc2() throws IOException{
        SocioController x=(SocioController) getTheBean("socio");
        List<Socio> lista= x.getJpaController().findAll();
        AssociacaoController xc = (AssociacaoController) getTheBean("associacao");
        
        for(int i=0; i<lista.size(); i++){
            if(!lista.get(i).getCandidato().getAssociacao().equals(xc.getAssociacao())){
                lista.remove(i);
                i--;
            }
        }
        
        x.setOrderAux(3);
        Collections.sort(lista);
        return lista;
        
    }
    
    
    public void updateList(){
        AssociacaoController x = (AssociacaoController) getTheBean("associacao");
        Associacao assoc=x.getAssociacao();
        
        List<Ocupa> lista = getJpaController().findAll();
        
        for(int i=0; i<lista.size(); i++){
            if(!lista.get(i).getSocio().getCandidato().getAssociacao().equals(assoc)){
                lista.remove(i);
                i--;
            }
        }
        ocupaItems=lista;
    }
    
    
    
    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public OcupaFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (OcupaFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "ocupaJpa");
        }
        return jpaController;
    }

    public SelectItem[] getOcupaItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getOcupaItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Ocupa getOcupa() {
        if (ocupa == null) {
            ocupa = (Ocupa) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentOcupa", converter, null);
        }
        if (ocupa == null) {
            ocupa = new Ocupa();
        }
        return ocupa;
    }

    public String listSetup() {
        reset(true);
        return "ocupa_list";
    }

    public String createSetup() {
        reset(false);
        ocupa = new Ocupa();
        ocupa.setOcupaPK(new OcupaPK());
        return "ocupa_create";
    }

    public String create() {
        ocupa.getOcupaPK().setNIFAssociacao(ocupa.getSocio().getSocioPK().getNIFAssociacao());
        ocupa.getOcupaPK().setNomeC(ocupa.getCargo().getCargoPK().getNome());
        ocupa.getOcupaPK().setNIFsocio(ocupa.getSocio().getSocioPK().getNif());
        ocupa.getOcupaPK().setGrupoC(ocupa.getCargo().getCargoPK().getGrupo());
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(ocupa);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Ocupa was successfully created.");
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
        return scalarSetup("ocupa_detail");
    }

    public String editSetup() {
        return scalarSetup("ocupa_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        ocupa = (Ocupa) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentOcupa", converter, null);
        if (ocupa == null) {
            String requestOcupaString = JsfUtil.getRequestParameter("jsfcrud.currentOcupa");
            JsfUtil.addErrorMessage("The ocupa with id " + requestOcupaString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        ocupa.getOcupaPK().setNIFAssociacao(ocupa.getSocio().getSocioPK().getNIFAssociacao());
        ocupa.getOcupaPK().setNomeC(ocupa.getCargo().getCargoPK().getNome());
        ocupa.getOcupaPK().setNIFsocio(ocupa.getSocio().getSocioPK().getNif());
        ocupa.getOcupaPK().setGrupoC(ocupa.getCargo().getCargoPK().getGrupo());
        String ocupaString = converter.getAsString(FacesContext.getCurrentInstance(), null, ocupa);
        String currentOcupaString = JsfUtil.getRequestParameter("jsfcrud.currentOcupa");
        if (ocupaString == null || ocupaString.length() == 0 || !ocupaString.equals(currentOcupaString)) {
            String outcome = editSetup();
            if ("ocupa_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit ocupa. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(ocupa);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Ocupa was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentOcupa");
        OcupaPK id = converter.getId(idAsString);
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
                JsfUtil.addSuccessMessage("Ocupa was successfully deleted.");
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

    public List<Ocupa> getOcupaItems() {
        if (ocupaItems == null) {
            getPagingInfo();
            ocupaItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return ocupaItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "ocupa_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "ocupa_list";
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
        ocupa = null;
        ocupaItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Ocupa newOcupa = new Ocupa();
        newOcupa.setOcupaPK(new OcupaPK());
        String newOcupaString = converter.getAsString(FacesContext.getCurrentInstance(), null, newOcupa);
        String ocupaString = converter.getAsString(FacesContext.getCurrentInstance(), null, ocupa);
        if (!newOcupaString.equals(ocupaString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
