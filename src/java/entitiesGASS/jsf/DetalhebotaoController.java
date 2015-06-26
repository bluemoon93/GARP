/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Botao;
import entitiesGASS.Detalhebotao;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import entitiesGASS.jsf.util.JsfUtil;
import entitiesGASS.DetalhebotaoPK;
import entitiesGASS.beans.DetalhebotaoFacade;
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
public class DetalhebotaoController {

    public DetalhebotaoController() {
        pagingInfo = new PagingInfo();
        converter = new DetalhebotaoConverter();
    }
    private Detalhebotao detalhebotao = null;
    private List<Detalhebotao> detalhebotaoItems = null;
    private DetalhebotaoFacade jpaController = null;
    private DetalhebotaoConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "GASSv10PU")
    private EntityManagerFactory emf = null;

    
    public String removeTodos() {
        AssociacaoController ac = (AssociacaoController) getTheBean("associacao");
        List<Detalhebotao> lb = this.getJpaController().findAll();
        BotaoController bc = (BotaoController) getTheBean("botao");
        Collection<Detalhebotao> cb = bc.getBotao().getDetalhebotaoCollection();
        Iterator<Detalhebotao> ib = cb.iterator();
        Detalhebotao aux;
        
        while(ib.hasNext()){     
            aux = ib.next();
            if(aux.getCheckBox()){
                this.removeGass(aux); 
            }
        }
        
         return "gassbuttondetail";
    }
    
    public void removeGass(Detalhebotao b) {
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
                JsfUtil.addSuccessMessage("Detalhebotao was successfully deleted.");
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
        BotaoController x=(BotaoController) getTheBean("botao");
        x.updateDetails();
        
       
    }
    
    public String createSetupGass() {
        reset(false);
        detalhebotao = new Detalhebotao();
        detalhebotao.setDetalhebotaoPK(new DetalhebotaoPK());
        return "gassbutdetailnew";
    }
    
    private int getNextNumber(Botao t) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (DetalhebotaoFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "detalhebotaoJpa");
        List<Detalhebotao> lista = jpaController.findAll();
        
        int number=1;
        boolean repeat=true;
        while(repeat){
            repeat=false;
            for (int i = 0; i < lista.size(); i++) {
                if(lista.get(i).getBotao().equals(t)){
                    if(lista.get(i).getDetalhebotaoPK().getIDcaixa()==number){
                        number++;
                        repeat=true;
                        break;
                    }
                }
            }
        }
        return number;
    }
    
    public String createGass(Botao a, String b, short c, String d, int nmbr) {
        BotaoController x=(BotaoController) getTheBean("botao");
        
        //buttonDetailWorker bdw=(buttonDetailWorker) getTheBean("buttonDetailWorker");
        detalhebotao=new Detalhebotao();
        detalhebotao.setDetalhebotaoPK(new DetalhebotaoPK());
        detalhebotao.setBotao(a);
        detalhebotao.setNome(b);
        detalhebotao.setTipo(c);
        detalhebotao.getDetalhebotaoPK().setIDcaixa(nmbr);
        if(detalhebotao.getTipo()==1 || detalhebotao.getTipo()==3){
            detalhebotao.setConteudo(d);
        }
        else{
            detalhebotao.setConteudo("");
        }
        
        detalhebotao.getDetalhebotaoPK().setNIFassociacao(detalhebotao.getBotao().getBotaoPK().getNIFassociacao());
        detalhebotao.getDetalhebotaoPK().setIDbotao(detalhebotao.getBotao().getBotaoPK().getIDbotao());
        detalhebotao.getDetalhebotaoPK().setIDestado(detalhebotao.getBotao().getBotaoPK().getIDestado());
   
        //System.out.println(detalhebotao.getBotao()+" "+detalhebotao.getNome()+" "+detalhebotao.getTipo()+" "+detalhebotao.getDetalhebotaoPK());
        
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(detalhebotao);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Detalhebotao was successfully created.");
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
        x.updateDetails();
        
        return "gassbuttondetail";
    }
    
    public String createGass() {
        BotaoController x=(BotaoController) getTheBean("botao");
        
        buttonDetailWorker bdw=(buttonDetailWorker) getTheBean("buttonDetailWorker");
        
        detalhebotao.setBotao(x.getBotao());
        detalhebotao.setNome(bdw.getNome());
        detalhebotao.setTipo(bdw.getTipoN());
        detalhebotao.getDetalhebotaoPK().setIDcaixa(getNextNumber(x.getBotao()));
        if(detalhebotao.getTipo()==1){
            detalhebotao.setConteudo(bdw.getConteudo());
        }
        else{
            detalhebotao.setConteudo(" ");
        }
        
        detalhebotao.getDetalhebotaoPK().setNIFassociacao(detalhebotao.getBotao().getBotaoPK().getNIFassociacao());
        detalhebotao.getDetalhebotaoPK().setIDbotao(detalhebotao.getBotao().getBotaoPK().getIDbotao());
        detalhebotao.getDetalhebotaoPK().setIDestado(detalhebotao.getBotao().getBotaoPK().getIDestado());
   
        System.out.println(detalhebotao.getBotao()+" "+detalhebotao.getNome()+" "+detalhebotao.getTipo()+" "+detalhebotao.getDetalhebotaoPK());
        
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(detalhebotao);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Detalhebotao was successfully created.");
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
        x.updateDetails();
        
        return "gassbutdetaildetail";
    }
    
    public String editSetupGass() {
        return scalarSetup("gassbutdetailedit");
    }
    
    public String detailSetupGass() {
        return scalarSetup("gassbutdetaildetail");
    }
    
    public String editGass() {
        detalhebotao.getDetalhebotaoPK().setNIFassociacao(detalhebotao.getBotao().getBotaoPK().getNIFassociacao());
        detalhebotao.getDetalhebotaoPK().setIDbotao(detalhebotao.getBotao().getBotaoPK().getIDbotao());
        detalhebotao.getDetalhebotaoPK().setIDestado(detalhebotao.getBotao().getBotaoPK().getIDestado());
        // TODO: no setter methods were found in your primary key class
        //    entitiesGASS.DetalhebotaoPK
        // and therefore initialization code need manual adjustments.
        String detalhebotaoString = converter.getAsString(FacesContext.getCurrentInstance(), null, detalhebotao);
        String currentDetalhebotaoString = JsfUtil.getRequestParameter("jsfcrud.currentDetalhebotao");
        if (detalhebotaoString == null || detalhebotaoString.length() == 0 || !detalhebotaoString.equals(currentDetalhebotaoString)) {
            String outcome = editSetup();
            if ("detalhebotao_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit detalhebotao. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(detalhebotao);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Detalhebotao was successfully updated.");
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

    public DetalhebotaoFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (DetalhebotaoFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "detalhebotaoJpa");
        }
        return jpaController;
    }

    public SelectItem[] getDetalhebotaoItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getDetalhebotaoItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Detalhebotao getDetalhebotao() {
        if (detalhebotao == null) {
            detalhebotao = (Detalhebotao) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentDetalhebotao", converter, null);
        }
        if (detalhebotao == null) {
            detalhebotao = new Detalhebotao();
        }
        return detalhebotao;
    }

    public String listSetup() {
        reset(true);
        return "detalhebotao_list";
    }

    public String createSetup() {
        reset(false);
        detalhebotao = new Detalhebotao();
        detalhebotao.setDetalhebotaoPK(new DetalhebotaoPK());
        return "detalhebotao_create";
    }

    public String create() {
        detalhebotao.getDetalhebotaoPK().setIDbotao(detalhebotao.getBotao().getBotaoPK().getIDbotao());
        detalhebotao.getDetalhebotaoPK().setNIFassociacao(detalhebotao.getBotao().getBotaoPK().getNIFassociacao());
        detalhebotao.getDetalhebotaoPK().setIDestado(detalhebotao.getBotao().getBotaoPK().getIDestado());
        // TODO: no setter methods were found in your primary key class
        //    entitiesGASS.DetalhebotaoPK
        // and therefore initialization code need manual adjustments.
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(detalhebotao);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Detalhebotao was successfully created.");
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
        return scalarSetup("detalhebotao_detail");
    }

    public String editSetup() {
        return scalarSetup("detalhebotao_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        detalhebotao = (Detalhebotao) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentDetalhebotao", converter, null);
        if (detalhebotao == null) {
            String requestDetalhebotaoString = JsfUtil.getRequestParameter("jsfcrud.currentDetalhebotao");
            JsfUtil.addErrorMessage("The detalhebotao with id " + requestDetalhebotaoString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        detalhebotao.getDetalhebotaoPK().setIDbotao(detalhebotao.getBotao().getBotaoPK().getIDbotao());
        detalhebotao.getDetalhebotaoPK().setNIFassociacao(detalhebotao.getBotao().getBotaoPK().getNIFassociacao());
        detalhebotao.getDetalhebotaoPK().setIDestado(detalhebotao.getBotao().getBotaoPK().getIDestado());
        // TODO: no setter methods were found in your primary key class
        //    entitiesGASS.DetalhebotaoPK
        // and therefore initialization code need manual adjustments.
        String detalhebotaoString = converter.getAsString(FacesContext.getCurrentInstance(), null, detalhebotao);
        String currentDetalhebotaoString = JsfUtil.getRequestParameter("jsfcrud.currentDetalhebotao");
        if (detalhebotaoString == null || detalhebotaoString.length() == 0 || !detalhebotaoString.equals(currentDetalhebotaoString)) {
            String outcome = editSetup();
            if ("detalhebotao_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit detalhebotao. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(detalhebotao);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Detalhebotao was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentDetalhebotao");
        DetalhebotaoPK id = converter.getId(idAsString);
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
                JsfUtil.addSuccessMessage("Detalhebotao was successfully deleted.");
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

    public List<Detalhebotao> getDetalhebotaoItems() {
        if (detalhebotaoItems == null) {
            getPagingInfo();
            detalhebotaoItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return detalhebotaoItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "detalhebotao_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "detalhebotao_list";
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
        detalhebotao = null;
        detalhebotaoItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Detalhebotao newDetalhebotao = new Detalhebotao();
        newDetalhebotao.setDetalhebotaoPK(new DetalhebotaoPK());
        String newDetalhebotaoString = converter.getAsString(FacesContext.getCurrentInstance(), null, newDetalhebotao);
        String detalhebotaoString = converter.getAsString(FacesContext.getCurrentInstance(), null, detalhebotao);
        if (!newDetalhebotaoString.equals(detalhebotaoString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
