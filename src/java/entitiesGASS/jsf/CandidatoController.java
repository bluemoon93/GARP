/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Associacao;
import entitiesGASS.Candidato;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import entitiesGASS.jsf.util.JsfUtil;
import entitiesGASS.CandidatoPK;
import entitiesGASS.CargoPK;
import entitiesGASS.Estado;
import entitiesGASS.Metodopassagem;
import entitiesGASS.Ocupa;
import entitiesGASS.Pagamentoquota;
import entitiesGASS.Socio;
import entitiesGASS.beans.CandidatoFacade;
import static entitiesGASS.jsf.beanWorker.getTheBean;
import entitiesGASS.jsf.util.PagingInfo;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
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
public class CandidatoController {

    public CandidatoController() {
        pagingInfo = new PagingInfo();
        converter = new CandidatoConverter();
    }
    private Candidato candidato = null;
    private List<Candidato> candidatoItems = null;
    private CandidatoFacade jpaController = null;
    private CandidatoConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "GASSv10PU")
    private EntityManagerFactory emf = null;
    private Candidato pessoaLogin = null;

    public Candidato getPessoaLogin() {
        return pessoaLogin;
    }
    
    public void resetLogin(){
        pessoaLogin=null;
    }

    public void checkStates(int nif) {
        SocioController socCtrl = (SocioController) getTheBean("socio");
        List<Socio> sossList = socCtrl.getJpaController().findAll();

        // filter by association
        for (int i = 0; i < sossList.size(); i++) {
            if (sossList.get(i).getSocioPK().getNIFAssociacao() != nif) {
                sossList.remove(i);
                i--;
            }
        }

        List<Metodopassagem> mpList;
        int[] mdpDate = new int[3];

        // if exists any mdp calculate and change state else continue
        for (int i = 0; i < sossList.size(); i++) {
            //System.out.println("before "+sossList.get(i).toString());


            if (!sossList.get(i).getEstado().getMetodopassagemCollection1().isEmpty()) {  //mdp exists 
                if (!socCtrl.temQuotasEmDia(sossList.get(i))) {

                    mpList = (List<Metodopassagem>) sossList.get(i).getEstado().getMetodopassagemCollection1();
                    Metodopassagem mdp = mpList.get(0);
                    //1 para onde vai, <nada> de onde vem

                    //System.out.println("after");

                    if (sossList.get(i).getEstado().equals(mdp.getEstado1())) {   //same source state

                        //mdp timer
                        mdpDate[0] = mdp.getAnos();
                        mdpDate[1] = mdp.getMeses();
                        mdpDate[2] = mdp.getDias();

                        //data modificaçao antiga
                        Calendar t = Calendar.getInstance();
                        t.set(Calendar.MONTH, 0);
                        t.set(Calendar.DAY_OF_MONTH, 1);
                        
                        
                        t.add(Calendar.DAY_OF_MONTH, mdpDate[2]);
                        t.add(Calendar.MONTH, mdpDate[1]);
                        t.add(Calendar.YEAR, mdpDate[0]);
                        Date dataLimite = t.getTime();

                        if (dataLimite.before(new Date())) {
                            //System.out.println("--> "+sossList.get(i).getEstado()+" since "+sossList.get(i).getDataModificacao()+" to "+mdp.getEstado());
                            sossList.get(i).setEstado(mdp.getEstado());
                            sossList.get(i).setDataModificacao(dataLimite);
                            socCtrl.setEditSocio(sossList.get(i));
                        }
                    }
                }
            }
        }
    }

    public String recuperarPass() {
        Associacao temp = candidato.getAssociacao();

        String uuid = UUID.randomUUID().toString().substring(0, 8);

        candidato.getCandidatoPK().setNIFassociacao(temp.getNif());
        candidato = getJpaController().find(candidato.getCandidatoPK());
        if (candidato == null) {
            JsfUtil.addErrorMessage("eMail não existente");
            return "";
        }

        try {
            beanWorker.send(temp.getServer(), temp.getEmail(), temp.getPasswd(), candidato.getCandidatoPK().getEmail(), "Recuperacao de password", "a sua nova pass é: " + uuid, null, temp.getPort());
            JsfUtil.addSuccessMessage("eMail enviado. Pode demorar alguns minutos a chegar à sua caixa de correio.");
        } catch (Exception ex) {
            JsfUtil.addErrorMessage("Incapaz de enviar email");
            return "";
        }

        candidato.setPalavraPasse(uuid);
        setCandy(candidato);

        return "";
    }

    public String recuperarPass(int nif) {
        AssociacaoController a = (AssociacaoController) beanWorker.getTheBean("associacao");
        Associacao temp = (Associacao) a.getJpaController().find(nif);

        String uuid = UUID.randomUUID().toString().substring(0, 8);

        candidato.getCandidatoPK().setNIFassociacao(nif);
        candidato = getJpaController().find(candidato.getCandidatoPK());
        if (candidato == null) {
            JsfUtil.addErrorMessage("eMail não existente");
            return "";
        }

        try {
            beanWorker.send(temp.getServer(), temp.getEmail(), temp.getPasswd(), candidato.getCandidatoPK().getEmail(), "Recuperacao de password", "a sua nova pass é: " + uuid, null, temp.getPort());
            JsfUtil.addSuccessMessage("eMail enviado. Pode demorar alguns minutos a chegar à sua caixa de correio.");
        } catch (Exception ex) {
            JsfUtil.addErrorMessage("Incapaz de enviar email");
            return "";
        }

        candidato.setPalavraPasse(uuid);
        setCandy(candidato);

        return "";
    }

    public String loginSecretariado(int nif) {
        SocioController sc = new SocioController();
        List<Socio> sossList = sc.getJpaController().findAll();


        for (int i = 0; i < sossList.size(); i++) {
            if (sossList.get(i).getCandidato().getCandidatoPK().getEmail().equals(candidato.getCandidatoPK().getEmail())) {
                if (nif == (sossList.get(i).getCandidato().getAssociacao().getNif())) {
                    if (candidato.getPalavraPasse().equals(sossList.get(i).getCandidato().getPalavraPasse())) {
                        candidato = sossList.get(i).getCandidato();
                        this.pessoaLogin = candidato;
                        //System.out.println("aqui " + this.pessoaLogin.toString());
                        return checkCargo();
                    }
                }
            }
        }

        JsfUtil.addErrorMessage("Conta não existente.");

        return "";

    }

    public String loginSecretariado() {
        SocioController sc = new SocioController();
        List<Socio> sossList = sc.getJpaController().findAll();


        for (int i = 0; i < sossList.size(); i++) {
            if (sossList.get(i).getCandidato().getCandidatoPK().getEmail().equals(candidato.getCandidatoPK().getEmail())) {
                if (candidato.getAssociacao().equals(sossList.get(i).getCandidato().getAssociacao())) {
                    if (candidato.getPalavraPasse().equals(sossList.get(i).getCandidato().getPalavraPasse())) {
                        candidato = sossList.get(i).getCandidato();
                        this.pessoaLogin = candidato;
                        return checkCargo();
                    }
                }
            }
        }

        JsfUtil.addErrorMessage("Conta não existente.");

        return "";

    }

    public String checkCargo() {
        OcupaController sc = (OcupaController) beanWorker.getTheBean("ocupa");
        List<Ocupa> sossList = sc.getJpaController().findAll();
        CargoController cc = (CargoController) getTheBean("cargo");

        for (int i = 0; i < sossList.size(); i++) {
            if (sossList.get(i).getSocio().getCandidato().equals(candidato)) {
                Date hoje = new Date();
                if (sossList.get(i).getDataFim().after(hoje)) {
                    AssociacaoController assoc = (AssociacaoController) getTheBean("associacao");
                    assoc.setAssociacao(candidato.getAssociacao());
                    this.checkStates(assoc.getAssociacao().getNif());

                    cc.setCargo(sossList.get(i).getCargo());
                    
                    if(sossList.get(i).getCargo().getAcesso().equals("write"))
                        sc.setRead(true);
                    else
                        sc.setRead(false);

                    int inicial = 1;
                    EstadoController state = (EstadoController) beanWorker.getTheBean("estado");
                    List<Estado> listaEstado = state.getJpaController().findAll();
                    for(int t=0; t<listaEstado.size(); t++){
                        if(listaEstado.get(t).getInicial() && candidato.getAssociacao().equals(listaEstado.get(t).getAssociacao())){
                            inicial=listaEstado.get(t).getEstadoPK().getIDestado();
                        }
                    }
                    SocioController x = (SocioController) getTheBean("socio");
                    List<Socio> lista = x.getJpaController().findAll();
                    for (int j = 0; j < lista.size(); j++) {
                        if (!lista.get(j).getCandidato().getAssociacao().equals(candidato.getAssociacao())) {
                            lista.remove(j);
                            j--;
                        }
                    }
                    x.setCurrent(inicial);
                    x.setLista(lista);

                    for (int j = 0; j < lista.size(); j++) {
                        if (lista.get(j).getEstado().getEstadoPK().getIDestado() != inicial) {
                            lista.remove(j);
                            j--;
                        }
                    }
                    x.setListaIniciais(lista);

                    PagamentoquotaController p = (PagamentoquotaController) getTheBean("pagamentoquota");
                    List<Pagamentoquota> pagam = p.getJpaController().findAll();
                    for (int j = 0; j < pagam.size(); j++) {
                        if (pagam.get(j).getNrRecibo() != null) {
                            pagam.remove(j);
                            j--;
                        }

                    }
                    p.setListaPagamento(pagam);

                    return "BackEndLogin1";
                }
            }
        }

        JsfUtil.addErrorMessage("Conta sem permissões.");
        return "";
    }

    public String homeSecretariado() {
        return "detalheSecretariado";
    }

    public boolean setCandy(Candidato tempcand) {
        candidato = tempcand;

        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(candidato);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Candidato was successfully updated.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
                return false;
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return false;
        }
        return true;
    }

    

    public String createSoss() {
        stringWorker x = (stringWorker) getTheBean("stringWorker");
        if (!candidato.getPalavraPasse().equals(beanWorker.encodePass(x.getPw()))) {
            JsfUtil.addErrorMessage("As palavras-passe não condizem");
            return "";
        }
        candidato.getCandidatoPK().setNIFassociacao(candidato.getAssociacao().getNif());
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(candidato);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {

                transactionException = ex;
                JsfUtil.addErrorMessage("eMail já existente");
                return "criarCandy";
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Conta criada com sucesso.");
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
        return "detalheCandy";
    }

    public String createSoss(int nifA) {
        AssociacaoController y = (AssociacaoController) getTheBean("associacao");
        candidato.getCandidatoPK().setNIFassociacao(nifA);
        candidato.setAssociacao(y.getJpaController().find(nifA));

        return createSoss();
    }

    public String loginCandy(int nifA) {
        //<Ana2
        AssociacaoController y = new AssociacaoController();
        Associacao a = y.getJpaController().find(nifA);
        AssociacaoController ac = (AssociacaoController) getTheBean("associacao");
        ac.setAssociacao(a);

        candidato.getCandidatoPK().setNIFassociacao(nifA);
        candidato.setAssociacao(a);

        //<Ana
        EventoController ec = (EventoController) getTheBean("evento");
        ec.setProximosEventos(nifA);

        return loginCandy();
    }

    public String loginCandy() {
        SocioController sc = new SocioController();
        List<Socio> sossList = sc.getJpaController().findAll();
        List<Candidato> candyList = getJpaController().findAll();

        for (int i = 0; i < sossList.size(); i++) {
            if (sossList.get(i).getCandidato().getCandidatoPK().getEmail().equals(candidato.getCandidatoPK().getEmail())) {
                if (candidato.getAssociacao().equals(sossList.get(i).getCandidato().getAssociacao())) {
                    if (candidato.getPalavraPasse().equals(sossList.get(i).getCandidato().getPalavraPasse())) {
                        candidato.setDataNasc(sossList.get(i).getCandidato().getDataNasc());
                        candidato.setNome(sossList.get(i).getCandidato().getNome());
                        candidato.setCandidatoPK(sossList.get(i).getCandidato().getCandidatoPK());
                        candidato.setTelemovel(sossList.get(i).getCandidato().getTelemovel());

                        SocioController x = (SocioController) getTheBean("socio");
                        return x.returnSoss(sossList.get(i));
                    }
                }
            }
        }

        for (int i = 0; i < candyList.size(); i++) {
            if (candyList.get(i).getCandidatoPK().getEmail().equals(candidato.getCandidatoPK().getEmail())) {
                if (candidato.getAssociacao().equals(candyList.get(i).getAssociacao())) {
                    if (candidato.getPalavraPasse().equals(candyList.get(i).getPalavraPasse())) {
                        candidato.setDataNasc(candyList.get(i).getDataNasc());
                        candidato.setNome(candyList.get(i).getNome());
                        candidato.setCandidatoPK(candyList.get(i).getCandidatoPK());
                        candidato.setTelemovel(candyList.get(i).getTelemovel());
                        return "detalheCandy";
                    }
                }
            }
        }
        JsfUtil.addErrorMessage("Conta não existente.");
        return "";
    }

    public String detailCandySetup() {
        return scalarSetup("detalheCandy");
    }

    public String editarCandydate() {
        return scalarSetup("editarCandy");
    }

    public String editCandy() {
        String candidatoString = converter.getAsString(FacesContext.getCurrentInstance(), null, candidato);
        String currentCandidatoString = JsfUtil.getRequestParameter("jsfcrud.currentCandidato");
        if (candidatoString == null || candidatoString.length() == 0 || !candidatoString.equals(currentCandidatoString)) {
            String outcome = editSetup();
            if ("candidato_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit candidato. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(candidato);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Candidato was successfully updated.");
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
        return detailCandySetup();
    }

    public boolean removeSocioCandy(CandidatoPK id) {
        //System.out.println("id:  "+id);
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
                JsfUtil.addSuccessMessage("Conta apagada com sucesso.");
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

        reset(true);
        return true;
    }

    public String removeCandy() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentCandidato");
        CandidatoPK id = converter.getId(idAsString);
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
                JsfUtil.addSuccessMessage("Conta apagada com sucesso.");
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
        reset(true);
        return "criarCandy";
    }

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public CandidatoFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (CandidatoFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "candidatoJpa");
        }
        return jpaController;
    }

    public SelectItem[] getCandidatoItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getCandidatoItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Candidato getCandidato() {
        if (candidato == null) {
            candidato = (Candidato) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCandidato", converter, null);
        }
        if (candidato == null) {
            candidato = new Candidato();
        }
        return candidato;
    }

    public String listSetup() {
        reset(true);
        return "candidato_list";
    }

    public String createSetup() {
        reset(false);
        candidato = new Candidato();
        candidato.setCandidatoPK(new CandidatoPK());
        return "candidato_create";
    }

    public String create() {
        candidato.getCandidatoPK().setNIFassociacao(candidato.getAssociacao().getNif());
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(candidato);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Candidato was successfully created.");
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
        return scalarSetup("candidato_detail");
    }

    public String editSetup() {
        return scalarSetup("candidato_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        candidato = (Candidato) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCandidato", converter, null);
        if (candidato == null) {
            String requestCandidatoString = JsfUtil.getRequestParameter("jsfcrud.currentCandidato");
            JsfUtil.addErrorMessage("The candidato with id " + requestCandidatoString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        candidato.getCandidatoPK().setNIFassociacao(candidato.getAssociacao().getNif());
        String candidatoString = converter.getAsString(FacesContext.getCurrentInstance(), null, candidato);
        String currentCandidatoString = JsfUtil.getRequestParameter("jsfcrud.currentCandidato");
        if (candidatoString == null || candidatoString.length() == 0 || !candidatoString.equals(currentCandidatoString)) {
            String outcome = editSetup();
            if ("candidato_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit candidato. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(candidato);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Candidato was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentCandidato");
        CandidatoPK id = converter.getId(idAsString);
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
                JsfUtil.addSuccessMessage("Candidato was successfully deleted.");
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

    public List<Candidato> getCandidatoItems() {
        if (candidatoItems == null) {
            getPagingInfo();
            candidatoItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return candidatoItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "candidato_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "candidato_list";
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
        candidato = null;
        candidatoItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Candidato newCandidato = new Candidato();
        newCandidato.setCandidatoPK(new CandidatoPK());
        String newCandidatoString = converter.getAsString(FacesContext.getCurrentInstance(), null, newCandidato);
        String candidatoString = converter.getAsString(FacesContext.getCurrentInstance(), null, candidato);
        if (!newCandidatoString.equals(candidatoString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
}
