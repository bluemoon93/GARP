/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "detalhebotao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalhebotao.findAll", query = "SELECT d FROM Detalhebotao d"),
    @NamedQuery(name = "Detalhebotao.findByConteudo", query = "SELECT d FROM Detalhebotao d WHERE d.conteudo = :conteudo"),
    @NamedQuery(name = "Detalhebotao.findByNome", query = "SELECT d FROM Detalhebotao d WHERE d.nome = :nome"),
    @NamedQuery(name = "Detalhebotao.findByIDcaixa", query = "SELECT d FROM Detalhebotao d WHERE d.detalhebotaoPK.iDcaixa = :iDcaixa"),
    @NamedQuery(name = "Detalhebotao.findByIDbotao", query = "SELECT d FROM Detalhebotao d WHERE d.detalhebotaoPK.iDbotao = :iDbotao"),
    @NamedQuery(name = "Detalhebotao.findByIDestado", query = "SELECT d FROM Detalhebotao d WHERE d.detalhebotaoPK.iDestado = :iDestado"),
    @NamedQuery(name = "Detalhebotao.findByNIFassociacao", query = "SELECT d FROM Detalhebotao d WHERE d.detalhebotaoPK.nIFassociacao = :nIFassociacao"),
    @NamedQuery(name = "Detalhebotao.findByTipo", query = "SELECT d FROM Detalhebotao d WHERE d.tipo = :tipo")})
public class Detalhebotao implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalhebotaoPK detalhebotaoPK;
    @Size(max = 700)
    @Column(name = "Conteudo")
    private String conteudo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "Nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private short tipo;
    @JoinColumns({
        @JoinColumn(name = "IDbotao", referencedColumnName = "IDbotao", insertable = false, updatable = false),
        @JoinColumn(name = "IDestado", referencedColumnName = "IDestado", insertable = false, updatable = false),
        @JoinColumn(name = "NIFassociacao", referencedColumnName = "NIFassociacao", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Botao botao;
    private boolean checkBox;

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }
    
    public boolean getCheckBox(){
        return this.checkBox;
    }
    
    public Detalhebotao() {
    }

    public Detalhebotao(DetalhebotaoPK detalhebotaoPK) {
        this.detalhebotaoPK = detalhebotaoPK;
    }

    public Detalhebotao(DetalhebotaoPK detalhebotaoPK, String nome, short tipo) {
        this.detalhebotaoPK = detalhebotaoPK;
        this.nome = nome;
        this.tipo = tipo;
    }

    public Detalhebotao(int iDcaixa, int iDbotao, int iDestado, int nIFassociacao) {
        this.detalhebotaoPK = new DetalhebotaoPK(iDcaixa, iDbotao, iDestado, nIFassociacao);
    }

    public DetalhebotaoPK getDetalhebotaoPK() {
        return detalhebotaoPK;
    }

    public void setDetalhebotaoPK(DetalhebotaoPK detalhebotaoPK) {
        this.detalhebotaoPK = detalhebotaoPK;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public short getTipo() {
        return tipo;
    }

    public void setTipo(short tipo) {
        this.tipo = tipo;
    }

    public Botao getBotao() {
        return botao;
    }

    public void setBotao(Botao botao) {
        this.botao = botao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalhebotaoPK != null ? detalhebotaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalhebotao)) {
            return false;
        }
        Detalhebotao other = (Detalhebotao) object;
        if ((this.detalhebotaoPK == null && other.detalhebotaoPK != null) || (this.detalhebotaoPK != null && !this.detalhebotaoPK.equals(other.detalhebotaoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesGASS.Detalhebotao[ detalhebotaoPK=" + detalhebotaoPK + " ]";
    }
    
}
