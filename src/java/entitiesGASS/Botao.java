/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS;

import entitiesGASS.beans.EstadoFacade;
import entitiesGASS.jsf.AssociacaoController;
import entitiesGASS.jsf.PagamentoquotaController;
import entitiesGASS.jsf.SocioController;
import entitiesGASS.jsf.beanWorker;
import static entitiesGASS.jsf.beanWorker.getTheBean;
import entitiesGASS.jsf.util.JsfUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "botao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Botao.findAll", query = "SELECT b FROM Botao b"),
    @NamedQuery(name = "Botao.findByFuncao", query = "SELECT b FROM Botao b WHERE b.funcao = :funcao"),
    @NamedQuery(name = "Botao.findByNome", query = "SELECT b FROM Botao b WHERE b.nome = :nome"),
    @NamedQuery(name = "Botao.findByIDestado", query = "SELECT b FROM Botao b WHERE b.botaoPK.iDestado = :iDestado"),
    @NamedQuery(name = "Botao.findByIDbotao", query = "SELECT b FROM Botao b WHERE b.botaoPK.iDbotao = :iDbotao"),
    @NamedQuery(name = "Botao.findByNIFassociacao", query = "SELECT b FROM Botao b WHERE b.botaoPK.nIFassociacao = :nIFassociacao"),
    @NamedQuery(name = "Botao.findByTexto", query = "SELECT b FROM Botao b WHERE b.texto = :texto")})
public class Botao implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BotaoPK botaoPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Funcao")
    private String funcao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "Nome")
    private String nome;
    @Size(max = 400)
    @Column(name = "texto")
    private String texto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "botao")
    private Collection<Detalhebotao> detalhebotaoCollection;
    @JoinColumns({
        @JoinColumn(name = "IDestado", referencedColumnName = "IDestado", insertable = false, updatable = false),
        @JoinColumn(name = "NIFassociacao", referencedColumnName = "NIFassociacao", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Estado estado;
    //<Ana/Tiago
    private boolean checkBox;

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }

    public boolean getCheckBox() {
        return this.checkBox;
    }

    //>
    public Botao() {
    }

    public Botao(BotaoPK botaoPK) {
        this.botaoPK = botaoPK;
    }

    public Botao(BotaoPK botaoPK, String funcao, String nome) {
        this.botaoPK = botaoPK;
        this.funcao = funcao;
        this.nome = nome;
    }

    public Botao(int iDestado, int iDbotao, int nIFassociacao) {
        this.botaoPK = new BotaoPK(iDestado, iDbotao, nIFassociacao);
    }

    public String getFuncaoParsed() {
        String answer = "";
        String[] temp = funcao.split(";");
        for (int j = 0; j < temp.length; j++) {

            if (temp[j].indexOf("changestate") != -1) {
                answer += "Muda estado para o estado " + temp[j].substring(11) + "; ";
            } else if (temp[j].indexOf("email") != -1) {
                answer += "Envia eMail; ";

            } else if (temp[j].indexOf("addPay") != -1) {
                answer += "adiciona um pagamento; ";
            } else if (temp[j].indexOf("changeIf") != -1) {
                answer += "Muda estado para " + temp[j].substring(8) + " caso as quotas do sócio estejam atrasadas; ";
            }
        }

        return answer;
    }

    public BotaoPK getBotaoPK() {
        return botaoPK;
    }

    public void setBotaoPK(BotaoPK botaoPK) {
        this.botaoPK = botaoPK;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @XmlTransient
    public Collection<Detalhebotao> getDetalhebotaoCollection() {
        return detalhebotaoCollection;
    }

    public void setDetalhebotaoCollection(Collection<Detalhebotao> detalhebotaoCollection) {
        this.detalhebotaoCollection = detalhebotaoCollection;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (botaoPK != null ? botaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Botao)) {
            return false;
        }
        Botao other = (Botao) object;
        if ((this.botaoPK == null && other.botaoPK != null) || (this.botaoPK != null && !this.botaoPK.equals(other.botaoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesGASS.Botao[ botaoPK=" + botaoPK + " ]";
    }
}
