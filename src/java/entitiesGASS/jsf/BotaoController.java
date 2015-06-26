/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Botao;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import entitiesGASS.jsf.util.JsfUtil;
import entitiesGASS.BotaoPK;
import entitiesGASS.Detalhebotao;
import entitiesGASS.Estado;
import entitiesGASS.EstadoPK;
import entitiesGASS.Pagamentoquota;
import entitiesGASS.PagamentoquotaPK;
import entitiesGASS.beans.BotaoFacade;
import entitiesGASS.beans.DetalhebotaoFacade;
import entitiesGASS.beans.EstadoFacade;
import static entitiesGASS.jsf.beanWorker.getTheBean;
import entitiesGASS.jsf.util.PagingInfo;
import java.math.BigDecimal;
import java.security.NoSuchProviderException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author asus
 */
public class BotaoController {

    public BotaoController() {
        pagingInfo = new PagingInfo();
        converter = new BotaoConverter();

        intC = new integerConverter();
    }
    private Botao botao = null;
    private List<Botao> botaoItems = null;
    private BotaoFacade jpaController = null;
    private BotaoConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "GASSv10PU")
    private EntityManagerFactory emf = null;
    private List<listDetailClass> listaDetalhes = null;
    private integerConverter intC = null;

    /*public Converter getConverterList(){
     return converterList;
     }*/
    public integerConverter getIntC() {
        return intC;
    }

    public List<listDetailClass> getListaDetalhes() {
        return listaDetalhes;
    }

    public void updateList(Estado a) {
        listaDetalhes = new ArrayList<listDetailClass>();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        BotaoFacade jpaController2 = (BotaoFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "botaoJpa");
        List<Botao> listaBotoes = jpaController2.findAll();

        DetalhebotaoFacade jpa1Controller = (DetalhebotaoFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "detalhebotaoJpa");
        List<Detalhebotao> listaDet = jpa1Controller.findAll();

        for (int i = 0; i < listaBotoes.size(); i++) {
            if (listaBotoes.get(i).getEstado().equals(a)) {

                for (int j = 0; j < listaDet.size(); j++) {
                    if (listaDet.get(j).getBotao().equals(listaBotoes.get(i))) {
                        //System.out.println("comparison! ="+listaDet.get(j).getConteudo()+"=");
                        if (listaDet.get(j).getConteudo().compareTo("#today") == 0) {
                            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            listaDetalhes.add(new listDetailClass(dateFormat.format(new Date()).toString(), listaDet.get(j).getNome(), "", listaDet.get(j).getTipo(), 0));
                        } else if (listaDet.get(j).getConteudo().compareTo("#ano") == 0) {
                            Calendar t = Calendar.getInstance();
                            listaDetalhes.add(new listDetailClass(String.valueOf(t.get(Calendar.YEAR)), listaDet.get(j).getNome(), "", listaDet.get(j).getTipo(), 0));
                        } else if (listaDet.get(j).getConteudo().compareTo("#tipo") == 0) {
                            SocioController x = (SocioController) getTheBean("socio");
                            listaDetalhes.add(new listDetailClass(String.valueOf(x.getSocio().getTiposocio().getQuantia()), listaDet.get(j).getNome(), "", listaDet.get(j).getTipo(), 0));
                        } else {
                            listaDetalhes.add(new listDetailClass(listaDet.get(j).getConteudo(), listaDet.get(j).getNome(), "", listaDet.get(j).getTipo(), 0));
                        }
                    }
                }
                listaDetalhes.add(new listDetailClass(listaBotoes.get(i).getTexto(), listaBotoes.get(i).getNome(), listaBotoes.get(i).getFuncao(), (short) 5, listaBotoes.get(i).getBotaoPK().getIDbotao()));
            }
        }
    }

    public String pressButton() {
        Estado updateListzEstado = null;
        int a = (Integer) JsfUtil.getObjectFromRequestParameter("jsfcrud.buttonPress", intC, null);
        SocioController x = (SocioController) getTheBean("socio");
        int firstRofl = -1;
        boolean validNif=false;
        boolean errorAddPay = false, errorGetFields = false;
        int nrTransf = 0, nrRec = 0;
        Date dataPag = null, dataRec = null;
        double quantia = 0.0;
        String nrRecAntes="", nrRecDepois="";
        // System.out.println("buton");
        for (int i = 0; i < listaDetalhes.size(); i++) {
            if (listaDetalhes.get(i).getTipo() == 1 || listaDetalhes.get(i).getTipo() == 3) {
                //System.out.println(i + " -> " + listaDetalhes.get(i).getConteudo());
                try {
                    if (listaDetalhes.get(i).getNome().compareTo("#Ano: *") == 0) {
                        nrTransf = Integer.parseInt(listaDetalhes.get(i).getConteudo());
                    } else if (listaDetalhes.get(i).getNome().compareTo("#DataPag: ") == 0) {
                        if (!listaDetalhes.get(i).getConteudo().equals("")) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            dataPag = dateFormat.parse(listaDetalhes.get(i).getConteudo());
                        }
                    } else if (listaDetalhes.get(i).getNome().compareTo("#Quantia: ") == 0) {
                        if (!listaDetalhes.get(i).getConteudo().equals("")) {
                            quantia = Double.parseDouble(listaDetalhes.get(i).getConteudo());
                        }
                        // if == #tipo
                    } else if (listaDetalhes.get(i).getNome().compareTo("#NrRecibo: ") == 0) {
                        if (!listaDetalhes.get(i).getConteudo().equals("")) {
                          String temp=listaDetalhes.get(i).getConteudo();
                            int min=10000, max=0;
                            for(int u=0;u<10; u++){
                                if(min>temp.indexOf(String.valueOf(u)) && temp.indexOf(String.valueOf(u))!=-1){
                                    min=temp.indexOf(String.valueOf(u));
                                }
                            }
                            
                            for(int y=min; y<temp.length(); y++){
                                validNif=false;
                                for(int u=0;u<10; u++)
                                if(String.valueOf(temp.charAt(y)).equals(String.valueOf(u))){
                                    max=y;
                                    validNif=true;
                                }
                                if(!validNif){
                                    break;
                                }
                            }
                            //System.out.println(listaDetalhes.get(i).getConteudo().substring(0, min)+"--"+listaDetalhes.get(i).getConteudo().substring(min, max+1)+"--"+listaDetalhes.get(i).getConteudo().substring(max+1));
                            nrRecAntes=listaDetalhes.get(i).getConteudo().substring(0, min);
                            nrRec = Integer.parseInt(listaDetalhes.get(i).getConteudo().substring(min, max+1));
                            nrRecDepois=listaDetalhes.get(i).getConteudo().substring(max+1);
                        }
                    } else if (listaDetalhes.get(i).getNome().compareTo("#DataRec: ") == 0) {
                        if (!listaDetalhes.get(i).getConteudo().equals("")) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            dataRec = dateFormat.parse(listaDetalhes.get(i).getConteudo());
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                    errorGetFields = true;
                }
            } else if (listaDetalhes.get(i).getTipo() == 0) {
                // checkbox?...
            } else if (listaDetalhes.get(i).getTipo() == 5 && listaDetalhes.get(i).getId() == a) {
                String[] temp = listaDetalhes.get(i).getFuncao().split(";");
                for (int j = 0; j < temp.length; j++) {
                    //System.out.println(temp[j]);
                    if (temp[j].indexOf("changestate") != -1) {
                        if (!errorAddPay) {

                            try {
                                int statePos = Integer.parseInt(temp[j].substring(11));

                                FacesContext facesContext = FacesContext.getCurrentInstance();
                                EstadoFacade Jpa1Controller = (EstadoFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "estadoJpa");
                                List<Estado> lista = Jpa1Controller.findAll();

                                for (int g = 0; g < lista.size(); g++) {
                                    if (lista.get(g).getAssociacao().equals(x.getSocio().getCandidato().getAssociacao())) {
                                        if (lista.get(g).getEstadoPK().getIDestado() == statePos) {

                                            x.changeState(lista.get(g));
                                            updateListzEstado = lista.get(g);
                                            break;
                                        }
                                    }

                                }
                            } catch (Exception ex) {
                                JsfUtil.addErrorMessage("Can't change state");
                                System.out.println(ex);

                            }
                        }
                    } else if (temp[j].indexOf("email") != -1) {
                        if (!errorAddPay) {
                            AssociacaoController assoc = (AssociacaoController) getTheBean("associacao");
                            SocioController soss = (SocioController) getTheBean("socio");
                            try {
                                int separa = listaDetalhes.get(i).getConteudo().indexOf(';');
                                String texto = " " + listaDetalhes.get(i).getConteudo().substring(separa + 1);
                                String[] textoSep = texto.split("[<>]");
                                texto = "";

                                for (int asd = 0; asd < textoSep.length; asd++) {
                                    if (asd % 2 == 0) {
                                        texto += textoSep[asd];
                                    } else {
                                        if (textoSep[asd].equals("nome")) {
                                            texto += soss.getSocio().getCandidato().getNome();
                                        } else if (textoSep[asd].equals("estado")) {
                                            texto += soss.getSocio().getEstado();
                                        } else if (textoSep[asd].equals("nmrSocio")) {
                                            texto += soss.getSocio().getNrSocio();
                                        } else if (textoSep[asd].equals("bDay")) {
                                            texto += soss.getSocio().getCandidato().getDataNasc();
                                        } else if (textoSep[asd].equals("tipo")) {
                                            texto += soss.getSocio().getTiposocio().getQuantia().toString();
                                        } else {
                                            for (int rofl = firstRofl + 1; rofl < i; rofl++) {
                                                if (listaDetalhes.get(rofl).getNome().equals(textoSep[asd])) {
                                                    texto += listaDetalhes.get(rofl).getConteudo();
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                                beanWorker.send(assoc.getAssociacao().getServer(), assoc.getAssociacao().getEmail(), assoc.getAssociacao().getPasswd(), soss.getSocio().getCandidato().getCandidatoPK().getEmail(), listaDetalhes.get(i).getConteudo().substring(0, separa), texto, null, assoc.getAssociacao().getPort());
                            } catch (Exception ex) {
                                JsfUtil.addErrorMessage("Couldn't send email");
                            }
                        }

                    } else if (temp[j].indexOf("addPay") != -1) {
                        if (errorGetFields) {
                            JsfUtil.addErrorMessage("Campos do pagamento incorretos");
                        } else {
                            Pagamentoquota t = new Pagamentoquota(x.getSocio().getSocioPK().getNif(), x.getSocio().getSocioPK().getNIFAssociacao(), nrTransf);
                            t.setNrReciboAntes(nrRecAntes);
                            t.setNrReciboDepois(nrRecDepois);
                            t.setQuantia(BigDecimal.valueOf(quantia)); //quantia
                            t.setDataPag(dataPag);
                            t.setDataRecibo(dataRec);
                            t.setSocio(x.getSocio());
                            t.setNrRecibo(nrRec);
                            t.setCheckBox(false);
                            PagamentoquotaController pqc = (PagamentoquotaController) getTheBean("pagamentoquota");
                            t.setMaxDefault(pqc.maxRecibo());
                            if (pqc.criarQuotaDinamica(t) == false) {
                                errorAddPay = true;
                            }
                            pqc.SocPayList(x.getSocio().getSocioPK().getNif());
                        }
                    } else if (temp[j].indexOf("changeIf") != -1) {
                        if (!errorAddPay) {
                            try {
                                int statePos = Integer.parseInt(temp[j].substring(8));
                                Calendar t = Calendar.getInstance();
                                
                                if (x.getSocio().getMaxAnoQuota() >= t.get(Calendar.YEAR)) {
                                    EstadoFacade Jpa1Controller = (EstadoFacade) beanWorker.getTheBean("estadoJpa");
                                    Estado rofl = Jpa1Controller.find(new EstadoPK(statePos, x.getSocio().getCandidato().getAssociacao().getNif()));

                                    x.changeState(rofl);
                                    updateList(rofl);
                                }
                            } catch (Exception ex) {
                                JsfUtil.addErrorMessage("Can't change state");
                                System.out.println(ex);
                            }
                        }
                    }
                }
                firstRofl = i;
            }
        }

        if (updateListzEstado != null) {
            updateList(updateListzEstado);
        }

        return "";
    }

    public String removeTodos() {
        AssociacaoController ac = (AssociacaoController) getTheBean("associacao");
        //List<Botao> lb = this.getJpaController().findAll();
        EstadoController ec = (EstadoController) getTheBean("estado");
        Collection<Botao> cb = ec.getEstado().getBotaoCollection();
        Iterator<Botao> ib = cb.iterator();
        Botao aux;

        while (ib.hasNext()) {
            aux = ib.next();
            System.out.println("olfgfdsa + " + aux.getCheckBox() + " nif = " + aux.getBotaoPK().getNIFassociacao());
            if (aux.getCheckBox()) {
                this.removeGass(aux);
            }
        }

        return "estadoDetailGass";
    }

    public void removeGass(Botao b) {

        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().remove(b);
            //System.err.println("olaapos");
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Botao was successfully deleted.");
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

        updateState();
    }

    public void updateDetails() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        DetalhebotaoFacade jpa1Controller = (DetalhebotaoFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "detalhebotaoJpa");
        List<Detalhebotao> lista = jpa1Controller.findAll();

        for (int i = 0; i < lista.size(); i++) {
            if (!lista.get(i).getBotao().equals(botao)) {
                lista.remove(i);
                i--;
            }
        }
        botao.setDetalhebotaoCollection(lista);
    }

    public String detailGassSetup() {
        botao = (Botao) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentBotao", converter, null);
        updateDetails();
        return "gassbuttondetail";
    }

    public String editGassSetup() {
        return scalarSetup("gassbuttonedit");
    }

    public String editGass() {
        botao.getBotaoPK().setIDestado(botao.getEstado().getEstadoPK().getIDestado());
        botao.getBotaoPK().setNIFassociacao(botao.getEstado().getEstadoPK().getNIFassociacao());

        String botaoString = converter.getAsString(FacesContext.getCurrentInstance(), null, botao);
        String currentBotaoString = JsfUtil.getRequestParameter("jsfcrud.currentBotao");
        if (botaoString == null || botaoString.length() == 0 || !botaoString.equals(currentBotaoString)) {
            String outcome = editSetup();
            if ("botao_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit botao. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(botao);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Botao was successfully updated.");
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
        return detailGassSetup();
    }

    public String createGassSetup() {
        reset(false);
        botao = new Botao();
        botao.setBotaoPK(new BotaoPK());
        return "gassbuttonadd";
    }

    public String createGass() {

        EstadoController x = (EstadoController) getTheBean("estado");
        Estado temp = x.getEstado();

        buttonWorker but = (buttonWorker) getTheBean("buttonWorker");
        int error = but.checkFunction(temp.getAssociacao());
        if (error != 0) {
            if (error == 1) {
                JsfUtil.addErrorMessage("Insira valores na caixa de destino");
                return "";
            }
        }
        botao.setFuncao(but.getFunction());
        botao.setTexto(but.getAssunto() + ";" + botao.getTexto());
        botao.getBotaoPK().setIDbotao(getNextNumber(temp));
        botao.getBotaoPK().setIDestado(temp.getEstadoPK().getIDestado());
        botao.getBotaoPK().setNIFassociacao(temp.getEstadoPK().getNIFassociacao());
        botao.setEstado(temp);

        botao.getBotaoPK().setNIFassociacao(temp.getEstadoPK().getNIFassociacao());
        // TODO: no setter methods were found in your primary key class
        //    entitiesGASS.BotaoPK
        // and therefore initialization code need manual adjustments.
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(botao);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Botao was successfully created.");
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
        if (but.getAddPay()) {
            DetalhebotaoController db = (DetalhebotaoController) getTheBean("detalhebotao");
            db.createGass(botao, "#Ano: *", (short) 1, "#ano", 100);
            db.createGass(botao, "#DataPag: ", (short) 3, "#today", 101);
            db.createGass(botao, "#Quantia: ", (short) 1, "#tipo", 102);
            db.createGass(botao, "#NrRecibo: ", (short) 1, "", 103);
            db.createGass(botao, "#DataRec: ", (short) 3, "", 104);
        }
        updateState();
        return "gassbuttondetail";
    }

    private void updateState() {
        EstadoController temp = (EstadoController) getTheBean("estado");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (BotaoFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "botaoJpa");
        List<Botao> lista = jpaController.findAll();

        for (int i = 0; i < lista.size(); i++) {
            if (!lista.get(i).getEstado().equals(botao.getEstado())) {
                lista.remove(i);
                i--;
            }
        }

        temp.updateDetail(lista);
    }

    private int getNextNumber(Estado estado) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (BotaoFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "botaoJpa");
        List<Botao> lista = jpaController.findAll();

        int number = 1;
        boolean repeat = true;
        while (repeat) {
            repeat = false;
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getEstado().equals(estado)) {
                    if (lista.get(i).getBotaoPK().getIDbotao() == number) {
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

    public BotaoFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (BotaoFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "botaoJpa");
        }
        return jpaController;
    }

    public SelectItem[] getBotaoItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getBotaoItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Botao getBotao() {
        if (botao == null) {
            botao = (Botao) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentBotao", converter, null);
        }
        if (botao == null) {
            botao = new Botao();
        }
        return botao;
    }

    public String listSetup() {
        reset(true);
        return "botao_list";
    }

    public String createSetup() {
        reset(false);
        botao = new Botao();
        botao.setBotaoPK(new BotaoPK());
        return "botao_create";
    }

    public String create() {
        botao.getBotaoPK().setNIFassociacao(botao.getEstado().getEstadoPK().getNIFassociacao());
        botao.getBotaoPK().setIDestado(botao.getEstado().getEstadoPK().getIDestado());
        // TODO: no setter methods were found in your primary key class
        //    entitiesGASS.BotaoPK
        // and therefore initialization code need manual adjustments.
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(botao);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Botao was successfully created.");
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
        return scalarSetup("botao_detail");
    }

    public String editSetup() {
        return scalarSetup("botao_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        botao = (Botao) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentBotao", converter, null);
        if (botao == null) {
            String requestBotaoString = JsfUtil.getRequestParameter("jsfcrud.currentBotao");
            JsfUtil.addErrorMessage("The botao with id " + requestBotaoString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        botao.getBotaoPK().setNIFassociacao(botao.getEstado().getEstadoPK().getNIFassociacao());
        botao.getBotaoPK().setIDestado(botao.getEstado().getEstadoPK().getIDestado());
        // TODO: no setter methods were found in your primary key class
        //    entitiesGASS.BotaoPK
        // and therefore initialization code need manual adjustments.
        String botaoString = converter.getAsString(FacesContext.getCurrentInstance(), null, botao);
        String currentBotaoString = JsfUtil.getRequestParameter("jsfcrud.currentBotao");
        if (botaoString == null || botaoString.length() == 0 || !botaoString.equals(currentBotaoString)) {
            String outcome = editSetup();
            if ("botao_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit botao. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(botao);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Botao was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentBotao");
        BotaoPK id = converter.getId(idAsString);
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
                JsfUtil.addSuccessMessage("Botao was successfully deleted.");
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

    public List<Botao> getBotaoItems() {
        if (botaoItems == null) {
            getPagingInfo();
            botaoItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return botaoItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "botao_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "botao_list";
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
        botao = null;
        botaoItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Botao newBotao = new Botao();
        newBotao.setBotaoPK(new BotaoPK());
        String newBotaoString = converter.getAsString(FacesContext.getCurrentInstance(), null, newBotao);
        String botaoString = converter.getAsString(FacesContext.getCurrentInstance(), null, botao);
        if (!newBotaoString.equals(botaoString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
}
