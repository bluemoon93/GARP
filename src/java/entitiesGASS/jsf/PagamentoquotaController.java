/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

import entitiesGASS.Pagamentoquota;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import entitiesGASS.jsf.util.JsfUtil;
import entitiesGASS.PagamentoquotaPK;
import entitiesGASS.beans.PagamentoquotaFacade;
import static entitiesGASS.jsf.beanWorker.getTheBean;
import entitiesGASS.jsf.util.PagingInfo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;
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
public class PagamentoquotaController {

    public PagamentoquotaController() {
        pagingInfo = new PagingInfo();
        converter = new PagamentoquotaConverter();

        intC = new integerConverter();
        x = new TreeSet<Integer>();
        listaAnos = new ArrayList<Integer>();
    }
    private Pagamentoquota pagamentoquota = null;
    private List<Pagamentoquota> pagamentoquotaItems = null;
    private PagamentoquotaFacade jpaController = null;
    private PagamentoquotaConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "GASSv10PU")
    private EntityManagerFactory emf = null;
    private integerConverter intC = null;
    private List<Pagamentoquota> paymentList = null;
    private ArrayList<Integer> listaAnos = null;
    private TreeSet<Integer> x = null;
    private List<Pagamentoquota> lista = null;
    private List<Pagamentoquota> socPayList = null;
    // rui
    private List<pagamentoWorker> noReceiptList = new ArrayList<pagamentoWorker>();
    private List<pagamentoWorker> checkList = new ArrayList<pagamentoWorker>();

    public List<pagamentoWorker> getNoReceiptList() {
        return noReceiptList;
    }

    public List<pagamentoWorker> getCheckList() {
        return checkList;
    }
    private List<Pagamentoquota> pagame = null;

    public void setListaPagamento(List<Pagamentoquota> pagam) {
        pagame = pagam;
    }

    public List<Pagamentoquota> getPagam() {
        this.pagame = this.getJpaController().findAll();
        AssociacaoController ac = (AssociacaoController) getTheBean("associacao");
        for (int i = 0; i < this.pagame.size(); i++) {
            if (this.pagame.get(i).getPagamentoquotaPK().getNIFAssociacao() != ac.getAssociacao().getNif()
                    || this.pagame.get(i).getNrRecibo() != 0) {
                this.pagame.remove(i);
                i--;
            }
        }

        return pagame;
    }

    //<Ana2
    public String preencherCSV() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // tem de mudar o caminho!!
       // File f = new File("/home/ana/GASSv10/web/quotas.csv");
        //File f = new File("C:\\Users\\asus\\Documents\\NetBeansProjects\\GASSv12\\web\\quotas.csv");
        File f = new File("/opt/glassfish3/glassfish/domains/domain1/applications/GASSv12/quotas.csv");
        String content = "Ano da quota, Quantia, Data pagamento, Nr Recibo, Data recibo, "
                + "Nome Socio, Nr Socio, Email socio, Tipo Socio";
        String aux;

        for (int i = 0; i < this.pagamentoquotaItems.size(); i++) {
            aux = "\n" + this.pagamentoquotaItems.get(i).getPagamentoquotaPK().getAno()
                    + "," + this.pagamentoquotaItems.get(i).getQuantia() + ",";
            if(this.pagamentoquotaItems.get(i).getDataPag()!=null)
                aux += dateFormat.format(this.pagamentoquotaItems.get(i).getDataPag());
            else
                aux += "null";
            aux += "," + this.pagamentoquotaItems.get(i).getNrRecibo()+",";
            if(this.pagamentoquotaItems.get(i).getDataRecibo()!=null)
                aux += dateFormat.format(this.pagamentoquotaItems.get(i).getDataRecibo());
            else
                aux += "null";
            aux += "," + this.pagamentoquotaItems.get(i).getSocio().getCandidato().getNome()
                    + "," + this.pagamentoquotaItems.get(i).getSocio().getNrSocio()
                    + "," + this.pagamentoquotaItems.get(i).getSocio().getCandidato().getCandidatoPK().getEmail()
                    + "," + this.pagamentoquotaItems.get(i).getSocio().getTiposocio();
            content += aux;
        }


        try {
            if (!f.exists()) {
                f.createNewFile();
            }

            FileWriter fw = new FileWriter(f.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (Exception e) {
        }
        return "";
    }
//<Ana

    public void selectAllAna() throws IOException {
        for (int i = 0; i < this.socPayList.size(); i++) {
            this.socPayList.get(i).setCheckBox(true);
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("BackEndSocDetail.jsp#Pquotas");
    }

    public void cleanAllAna() throws IOException {
        for (int i = 0; i < this.socPayList.size(); i++) {
            this.socPayList.get(i).setCheckBox(false);
        }

        FacesContext.getCurrentInstance().getExternalContext().redirect("BackEndSocDetail.jsp#Pquotas");
    }

    public void BackEndDeleteAll() throws IOException {
        String aux = "";
        for (int i = 0; i < this.socPayList.size(); i++) {
            if (this.socPayList.get(i).getCheckBox()) {
                aux = this.BackEndDeleteSelected(this.socPayList.get(i));
                if (aux != null) {
                    this.socPayList.remove(i);
                    i--;
                }
            }
        }

        FacesContext.getCurrentInstance().getExternalContext().redirect("BackEndSocDetail.jsp#Pquotas");
    }

    public String BackEndDeleteSelected(Pagamentoquota temp) {
        SocioController x = (SocioController) beanWorker.getTheBean("socio");

        PagamentoquotaPK id = temp.getPagamentoquotaPK();
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
                return null;
            } catch (Exception ex) {
                return null;
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Pagamento quota foi removido com sucesso.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
                return null;
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        updateQuota();
        updateAnos();
        this.preencherCSV();

        if (x.getSocio().getMaxAnoQuota() >= temp.getPagamentoquotaPK().getAno()) {
            List<Pagamentoquota> listalol = getJpaController().findAll();
            int max = 0;
            for (int i = 0; i < listalol.size(); i++) {
                if (listalol.get(i).getPagamentoquotaPK().getAno() > max && listalol.get(i).getPagamentoquotaPK().getNIFAssociacao() == x.getSocio().getSocioPK().getNIFAssociacao() && listalol.get(i).getPagamentoquotaPK().getNIFsocio() == x.getSocio().getSocioPK().getNif()) {
                    max = listalol.get(i).getPagamentoquotaPK().getAno();
                }
            }
            x.getSocio().setMaxAnoQuota(max);
            x.setEditSocioSilent(x.getSocio());
        }

        return "listQuota";
    }

    public int maxRecibo() {
        AssociacaoController ac = (AssociacaoController) getTheBean("associacao");
        List<Pagamentoquota> lpq = this.getJpaController().findAll();
        int max = 0;

        for (int i = 0; i < lpq.size(); i++) {
            if (lpq.get(i).getPagamentoquotaPK().getNIFAssociacao() == ac.getAssociacao().getNif()) {
                if (lpq.get(i).getNrRecibo() > max) {
                    max = lpq.get(i).getNrRecibo();
                }
            }
        }
        return (max + 1);
    }

    public String changeMaxDefault() {
        for (int i = 0; i < this.checkList.size(); i++) {
            this.checkList.get(i).setNum_Recibo((short) (this.pagamentoquota.getMaxDefault() + i));
            this.checkList.get(i).setNum_ReciboAntes(this.pagamentoquota.getNrReciboAntes());
            this.checkList.get(i).setNum_ReciboDepois(this.pagamentoquota.getNrReciboDepois());
        }
        return "bePagam";
    }
    //       acho que o registarTodos já não é preciso

    public String salvarPagamentos() {
        for (int i = 0; i < this.checkList.size(); i++) {
            System.out.println(" ---- " + checkList.get(i).getCheckBox());
            if (this.checkList.get(i).getCheckBox()) {
                System.out.println(checkList.get(i).getAno());
                this.salvarPagam(this.checkList.get(i));
            }
        }
        checkList.clear();
        return "beRecibos";
    }
// Ana>

    public String selectAll() {

        for (int i = 0; i < noReceiptList.size(); i++) {
            noReceiptList.get(i).setCheckBox(true);
        }

        return "beRecibos";
    }

    public String cleanAll() {
        //remover os elementos da checkList no receiptList
        for (int i = 0; i < checkList.size(); i++) {
            noReceiptList.remove(checkList.get(i));
        }
        checkList.clear();  //limpar check list
        //colocar tudo a false nas checkBoxes
        for (int i = 0; i < noReceiptList.size(); i++) {
            noReceiptList.get(i).setCheckBox(false);
        }

        return "beRecibos";
    }

    public String registarTodos() {
        //Com lista ja actualizada
        List<Pagamentoquota> aux = getJpaController().findAll();
        List<Pagamentoquota> lol = new ArrayList<Pagamentoquota>();

        for (int i = 0; i < checkList.size(); i++) {
            for (int j = 0; j < aux.size(); j++) {
                if (aux.get(j).getPagamentoquotaPK().getAno() == checkList.get(i).getAno()) {
                    lol.add(aux.get(j));
                    break;
                }
            }
        }

        for (int i = 0; i < checkList.size(); i++) {
            for (int j = 0; j < lol.size(); j++) {
                if (checkList.get(i).getAno() == lol.get(j).getPagamentoquotaPK().getAno()) {
                    lol.get(j).setNrRecibo(checkList.get(i).getNum_Recibo());
                    lol.get(j).setDataRecibo(checkList.get(i).getData_Recibo());

                    try {
                        utx.begin();
                    } catch (Exception ex) {
                    }
                    try {
                        Exception transactionException = null;
                        getJpaController().edit(lol.get(j));
                        try {
                            utx.commit();
                        } catch (javax.transaction.RollbackException ex) {
                            transactionException = ex;
                        } catch (Exception ex) {
                        }
                        if (transactionException == null) {
                            JsfUtil.addSuccessMessage("Pagamentoquota was successfully updated.");
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
                }

            }
        }
        return cleanAll();
    }

    public String backToList() {
        return "detalheSecretariado";
    }

    public String registPagam() {
        this.checkList.clear();
        for (int i = 0; i < noReceiptList.size(); i++) {
            if (noReceiptList.get(i).getCheckBox()) {
                checkList.add(this.noReceiptList.get(i));
            }
        }

        return "bePagam";
    }

    public String salvarPagam(pagamentoWorker one) {
        Pagamentoquota found = getJpaController().find(new PagamentoquotaPK(one.getNif(), one.getNifAssoc(), one.getAno()));

        found.setNrRecibo(one.getNum_Recibo());
        found.setDataRecibo(one.getData_Recibo());
        //<Ana3
        found.setNrReciboAntes(one.getNum_ReciboAntes());
        found.setNrReciboDepois(one.getNum_ReciboDepois());

        if (this.reciboJaExisteNaAssociacao(found)) {
            JsfUtil.addErrorMessage("Não foi possível emitir recibo a " + one.getEmail()
                    + ", pois o nr recibo que lhe foi atribuído (" + one.getNum_ReciboAntes()
                    + +one.getNum_Recibo() + one.getNum_ReciboDepois() + ") já existe.");
            return "beRecibos";
        }

        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(found);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Pagamentoquota was successfully updated.");
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
        //para apagar o que salvamos
        for (int i = 0; i < noReceiptList.size(); i++) {
            if (one.getNif() == noReceiptList.get(i).getNif()) {
                noReceiptList.remove(i);
                break;
            }
        }
        //checkList.clear();
        return "beRecibos";
    }

    public boolean reciboJaExisteNaAssociacao(Pagamentoquota pq) {
        List<Pagamentoquota> lpq = this.getJpaController().findAll();

        for (int i = 0; i < lpq.size(); i++) {
            if (lpq.get(i).getPagamentoquotaPK().getNIFAssociacao() == pq.getPagamentoquotaPK().getNIFAssociacao()) {
                if (lpq.get(i).getNrReciboAntes().equals(pq.getNrReciboAntes())) {
                    if (lpq.get(i).getNrRecibo() == pq.getNrRecibo()) {
                        if (lpq.get(i).getNrReciboDepois().equals(pq.getNrReciboDepois())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public String getAllNullReceipt() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        PagamentoquotaFacade Jpa1Controller = (PagamentoquotaFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "pagamentoquotaJpa");
        socPayList = Jpa1Controller.findAll();
        AssociacaoController assoc= (AssociacaoController) beanWorker.getTheBean("associacao");
        noReceiptList.clear();  //clean to update
        // LF nrrecibo == 0
        for (int i = 0; i < socPayList.size(); i++) {
            if (socPayList.get(i).getNrRecibo() != 0 || socPayList.get(i).getPagamentoquotaPK().getNIFAssociacao() != assoc.getAssociacao().getNif()) {
                socPayList.remove(i);
                i--;
            }
        }

        Collections.sort(socPayList);
        for (int i = 0; i < socPayList.size(); i++) {
            noReceiptList.add(new pagamentoWorker(socPayList.get(i).getSocio().getCandidato().getNome(),
                    socPayList.get(i).getSocio().getCandidato().getCandidatoPK().getEmail(),
                    socPayList.get(i).getSocio().getSocioPK().getNif(),
                    socPayList.get(i).getPagamentoquotaPK().getAno(),
                    socPayList.get(i).getDataPag()));
            noReceiptList.get(i).setCheckBox(false);
        }
        return "beRecibos";
    }

    public boolean criarQuotaDinamica(Pagamentoquota a) {
        pagamentoquota = a;

        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(pagamentoquota);

            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Pagamento quota foi criado com sucesso.");
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

        //apagar isto qd triggers funcionarem
        if (pagamentoquota.getPagamentoquotaPK().getAno() > pagamentoquota.getSocio().getMaxAnoQuota()) {
            pagamentoquota.getSocio().setMaxAnoQuota(pagamentoquota.getPagamentoquotaPK().getAno());

            SocioController x = (SocioController) beanWorker.getTheBean("socio");
            x.setEditSocioSilent(pagamentoquota.getSocio());
        }
        
        return true;
    }

    public void SocPayList(int socNif) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        PagamentoquotaFacade Jpa1Controller = (PagamentoquotaFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "pagamentoquotaJpa");
        socPayList = Jpa1Controller.findAll();

        for (int i = 0; i < socPayList.size(); i++) {
            if (socPayList.get(i).getPagamentoquotaPK().getNIFsocio() != socNif) {
                socPayList.remove(i);
                i--;
            }
        }
    }

    public void setSocPayList(List<Pagamentoquota> newPayList) {
        socPayList = newPayList;
    }

    public List<Pagamentoquota> getSocPayList() {
        return socPayList;
    }

    public integerConverter getIntC() {
        return intC;
    }

    public String detalhesQuota() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentPagamentoquota");
        pagamentoquota = (Pagamentoquota) JsfUtil.getObjectFromRequestParameter(idAsString, converter, null);
        return "detalhesQuota";
    }

    //<Ana2
    public String detalhesQuotaSocio() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentPagamentoquota");
        pagamentoquota = (Pagamentoquota) JsfUtil.getObjectFromRequestParameter(idAsString, converter, null);
        return "detalhesQuotaSocio";
    }

    public void mostrarTodosMeusPagamentosQuotas() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("SocioDetalhe.jsp#Pquotas");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Ocorreu um erro: " + e);
        }
    }

    //---------------------------------------------------------------------------------------------------------------------  
    public String listQuota() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        PagamentoquotaFacade JPA1Controller = (PagamentoquotaFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "pagamentoquotaJpa");
        paymentList = JPA1Controller.findAll();

        //checkar a associaçao em questao
        AssociacaoController assCtrl = (AssociacaoController) getTheBean("associacao");

        for (int i = 0; i < paymentList.size(); i++) {
            if (!paymentList.get(i).getSocio().getCandidato().getAssociacao().equals(assCtrl.getAssociacao())) {
                paymentList.remove(i);
                i--;
            }
        }
        pagamentoquotaItems = paymentList;

        updateAnos();
        this.preencherCSV();
        return "listQuota";
    }

    public void updateAnos() {
//        x.clear();
//        for (int j = 0; j < pagamentoquotaItems.size(); j++) {
//            if (pagamentoquotaItems.get(j).getDataPag() != null) {
//                x.add(pagamentoquotaItems.get(j).getDataPag().getYear() + 1900);
//            }
//        }
//
//        listaAnos.clear();
//        for (int w = 0; w < x.size();) {
//            listaAnos.add(x.pollFirst());
//        }

        this.listaAnos.clear();

        for (int j = 0; j < pagamentoquotaItems.size(); j++) {
            if (!this.listaAnos.contains(this.pagamentoquotaItems.get(j).getPagamentoquotaPK().getAno())) {
                listaAnos.add(pagamentoquotaItems.get(j).getPagamentoquotaPK().getAno());
            }
        }
    }

    //---------------------------------------------------------------------------------------------------------------------  
    //para filtrar por anos diferentes
    public String filtrar() {
        int t = (Integer) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentAno", intC, null);
        //System.out.println("ANOOOOOOOO "+t);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        PagamentoquotaFacade JPA1Controller = (PagamentoquotaFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "pagamentoquotaJpa");
        paymentList = JPA1Controller.findAll();

        //checkar a associaçao em questao
        AssociacaoController assCtrl = (AssociacaoController) getTheBean("associacao");

        for (int i = 0; i < paymentList.size(); i++) {
            if (!paymentList.get(i).getSocio().getCandidato().getAssociacao().equals(assCtrl.getAssociacao())) {
                paymentList.remove(i);
                i--;
            }
        }

        for (int i = 0; i < paymentList.size(); i++) {
            if (paymentList.get(i).getDataPag() == null) {
                paymentList.remove(i);
                i--;
            } else if (paymentList.get(i).getDataPag() != null) {
                //            if (paymentList.get(i).getDataPag().getYear() + 1900 != t) {
                if (this.paymentList.get(i).getPagamentoquotaPK().getAno() != t) {
                    paymentList.remove(i);
                    i--;
                }
            }
        }

        pagamentoquotaItems = paymentList;
        this.preencherCSV();
        return "listQuota";
    }

    //---------------------------------------------------------------------------------------------------------------------  
    //para o botao "Todos" por forma a aparecer a lista de todos os pagamentos
    public String findAll() {
        AssociacaoController assCtrl = (AssociacaoController) getTheBean("associacao");

        FacesContext facesContext = FacesContext.getCurrentInstance();
        PagamentoquotaFacade JPA1Controller = (PagamentoquotaFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "pagamentoquotaJpa");
        pagamentoquotaItems = JPA1Controller.findAll();

        for (int i = 0; i < pagamentoquotaItems.size(); i++) {
            if (!pagamentoquotaItems.get(i).getSocio().getCandidato().getAssociacao().equals(assCtrl.getAssociacao())) {
                pagamentoquotaItems.remove(i);
                i--;
            }
        }
        this.preencherCSV();
        return "listQuota";
    }

    //---------------------------------------------------------------------------------------------------------------------  
    public ArrayList<Integer> getListaAnos() {
        return listaAnos;
    }

    public void setListaAnos(ArrayList<Integer> listaAnos) {
        this.listaAnos = listaAnos;
    }

    public List<Pagamentoquota> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Pagamentoquota> paymentList) {
        this.paymentList = paymentList;
    }

    //---------------------------------------------------------------------------------------------------------------------  
    //funcao destroy
    public String removeQuota() {

        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentPagamentoquota");
        PagamentoquotaPK id = converter.getId(idAsString);
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
                JsfUtil.addSuccessMessage("Pagamento quota foi removido com sucesso.");
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
        updateQuota();
        updateAnos();
        return "listQuota";
    }

    //---------------------------------------------------------------------------------------------------------------------  
    public String createQuota() {
        return "criarQuota";
    }

    //funcao create
    public String criarQuota() {
        pagamentoquota.getPagamentoquotaPK().setNIFAssociacao(pagamentoquota.getSocio().getSocioPK().getNIFAssociacao());
        pagamentoquota.getPagamentoquotaPK().setNIFsocio(pagamentoquota.getSocio().getSocioPK().getNif());

        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(pagamentoquota);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Pagamento quota foi criado com sucesso.");
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

        updateQuota();
        updateAnos();
        return listQuota();
    }

    //---------------------------------------------------------------------------------------------------------------------  
    public String editarQuota() {
        return scalarSetup("editarQuota");
    }

    //funcao edit
    public String editQuota() {

        String pagamentoquotaString = converter.getAsString(FacesContext.getCurrentInstance(), null, pagamentoquota);
        String currentPagamentoquotaString = JsfUtil.getRequestParameter("jsfcrud.currentPagamentoquota");
        if (pagamentoquotaString == null || pagamentoquotaString.length() == 0 || !pagamentoquotaString.equals(currentPagamentoquotaString)) {
            //      String outcome = editSetup();
            String outcome = editarQuota();
            if ("pagamentoquota_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Não foi possível editar o pagamento quota.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(pagamentoquota);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Pagamento quota foi editado com sucesso.");
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
        return detalhesQuota();
    }

    //---------------------------------------------------------------------------------------------------------------------  
    //fazer update as paginas qd faço create, edit ou destroy 
    private void updateQuota() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (PagamentoquotaFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "pagamentoquotaJpa");
        lista = jpaController.findAll();

        //checkar a associaçao em questao
        AssociacaoController assCtrl = (AssociacaoController) getTheBean("associacao");


        for (int i = 0; i < lista.size(); i++) {
            if (!lista.get(i).getSocio().getCandidato().getAssociacao().equals(assCtrl.getAssociacao())) {
                lista.remove(i);
                i--;
            }
        }

        pagamentoquotaItems = lista;
    }

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public PagamentoquotaFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (PagamentoquotaFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "pagamentoquotaJpa");
        }
        return jpaController;
    }

    public SelectItem[] getPagamentoquotaItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getPagamentoquotaItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Pagamentoquota getPagamentoquota() {
        if (pagamentoquota == null) {
            pagamentoquota = (Pagamentoquota) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentPagamentoquota", converter, null);
        }
        if (pagamentoquota == null) {
            pagamentoquota = new Pagamentoquota();
        }
        return pagamentoquota;
    }

    public String listSetup() {
        reset(true);
        return "pagamentoquota_list";
    }

    public String createSetup() {
        reset(false);
        pagamentoquota = new Pagamentoquota();
        pagamentoquota.setPagamentoquotaPK(new PagamentoquotaPK());
        return "pagamentoquota_create";
    }

    public String create() {
        pagamentoquota.getPagamentoquotaPK().setNIFsocio(pagamentoquota.getSocio().getSocioPK().getNif());
        pagamentoquota.getPagamentoquotaPK().setNIFAssociacao(pagamentoquota.getSocio().getSocioPK().getNIFAssociacao());
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(pagamentoquota);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Pagamentoquota was successfully created.");
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
        return scalarSetup("pagamentoquota_detail");
    }

    public String editSetup() {
        return scalarSetup("pagamentoquota_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        pagamentoquota = (Pagamentoquota) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentPagamentoquota", converter, null);
        if (pagamentoquota == null) {
            String requestPagamentoquotaString = JsfUtil.getRequestParameter("jsfcrud.currentPagamentoquota");
            JsfUtil.addErrorMessage("The pagamentoquota with id " + requestPagamentoquotaString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        pagamentoquota.getPagamentoquotaPK().setNIFsocio(pagamentoquota.getSocio().getSocioPK().getNif());
        pagamentoquota.getPagamentoquotaPK().setNIFAssociacao(pagamentoquota.getSocio().getSocioPK().getNIFAssociacao());
        String pagamentoquotaString = converter.getAsString(FacesContext.getCurrentInstance(), null, pagamentoquota);
        String currentPagamentoquotaString = JsfUtil.getRequestParameter("jsfcrud.currentPagamentoquota");
        if (pagamentoquotaString == null || pagamentoquotaString.length() == 0 || !pagamentoquotaString.equals(currentPagamentoquotaString)) {
            String outcome = editSetup();
            if ("pagamentoquota_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit pagamentoquota. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(pagamentoquota);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Pagamentoquota was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentPagamentoquota");
        PagamentoquotaPK id = converter.getId(idAsString);
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
                JsfUtil.addSuccessMessage("Pagamentoquota was successfully deleted.");
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

    public List<Pagamentoquota> getPagamentoquotaItems() {
        if (pagamentoquotaItems == null) {
            getPagingInfo();
            pagamentoquotaItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return pagamentoquotaItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "pagamentoquota_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "pagamentoquota_list";
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
        pagamentoquota = null;
        pagamentoquotaItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Pagamentoquota newPagamentoquota = new Pagamentoquota();
        newPagamentoquota.setPagamentoquotaPK(new PagamentoquotaPK());
        String newPagamentoquotaString = converter.getAsString(FacesContext.getCurrentInstance(), null, newPagamentoquota);
        String pagamentoquotaString = converter.getAsString(FacesContext.getCurrentInstance(), null, pagamentoquota);
        if (!newPagamentoquotaString.equals(pagamentoquotaString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
}
