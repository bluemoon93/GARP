/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "participa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Participa.findAll", query = "SELECT p FROM Participa p"),
    @NamedQuery(name = "Participa.findByDataInscricao", query = "SELECT p FROM Participa p WHERE p.dataInscricao = :dataInscricao"),
    @NamedQuery(name = "Participa.findByValorAPagar", query = "SELECT p FROM Participa p WHERE p.valorAPagar = :valorAPagar"),
    @NamedQuery(name = "Participa.findByIDEvento", query = "SELECT p FROM Participa p WHERE p.participaPK.iDEvento = :iDEvento"),
    @NamedQuery(name = "Participa.findByEmail", query = "SELECT p FROM Participa p WHERE p.participaPK.email = :email"),
    @NamedQuery(name = "Participa.findByNIFasso", query = "SELECT p FROM Participa p WHERE p.participaPK.nIFasso = :nIFasso")})
public class Participa implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ParticipaPK participaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DataInscricao")
    @Temporal(TemporalType.DATE)
    private Date dataInscricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "ValorAPagar")
    private BigDecimal valorAPagar;
    @JoinColumns({
        @JoinColumn(name = "IDEvento", referencedColumnName = "IDEvento", insertable = false, updatable = false),
        @JoinColumn(name = "NIFasso", referencedColumnName = "NIFassoc", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Evento evento;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "participa")
    private Pagamentoevento pagamentoevento;
 // <Ana
    private boolean checkBox;
    private boolean pagou;

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }
    
    public boolean getCheckBox(){
        return this.checkBox;
    }

    public void setPagou(boolean pagou) {
        this.pagou = pagou;
    }
    
    public boolean getPagou(){
        return this.pagou;
    }

    public Participa() {
    }

    public Participa(ParticipaPK participaPK) {
        this.participaPK = participaPK;
    }

    public Participa(ParticipaPK participaPK, Date dataInscricao, BigDecimal valorAPagar) {
        this.participaPK = participaPK;
        this.dataInscricao = dataInscricao;
        this.valorAPagar = valorAPagar;
    }

    public Participa(int iDEvento, String email, int nIFasso) {
        this.participaPK = new ParticipaPK(iDEvento, email, nIFasso);
    }

    public ParticipaPK getParticipaPK() {
        return participaPK;
    }

    public void setParticipaPK(ParticipaPK participaPK) {
        this.participaPK = participaPK;
    }

    public Date getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(Date dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public BigDecimal getValorAPagar() {
        return valorAPagar;
    }

    public void setValorAPagar(BigDecimal valorAPagar) {
        this.valorAPagar = valorAPagar;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Pagamentoevento getPagamentoevento() {
        return pagamentoevento;
    }

    public void setPagamentoevento(Pagamentoevento pagamentoevento) {
        this.pagamentoevento = pagamentoevento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (participaPK != null ? participaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Participa)) {
            return false;
        }
        Participa other = (Participa) object;
        if ((this.participaPK == null && other.participaPK != null) || (this.participaPK != null && !this.participaPK.equals(other.participaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesGASS.Participa[ participaPK=" + participaPK + " ]";
    }
    
}
