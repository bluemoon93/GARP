/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "pagamentoevento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagamentoevento.findAll", query = "SELECT p FROM Pagamentoevento p"),
    @NamedQuery(name = "Pagamentoevento.findByDataPag", query = "SELECT p FROM Pagamentoevento p WHERE p.dataPag = :dataPag"),
    @NamedQuery(name = "Pagamentoevento.findByQuantia", query = "SELECT p FROM Pagamentoevento p WHERE p.quantia = :quantia"),
    @NamedQuery(name = "Pagamentoevento.findByNIFAssociacao", query = "SELECT p FROM Pagamentoevento p WHERE p.pagamentoeventoPK.nIFAssociacao = :nIFAssociacao"),
    @NamedQuery(name = "Pagamentoevento.findByDataRecibo", query = "SELECT p FROM Pagamentoevento p WHERE p.dataRecibo = :dataRecibo"),
    @NamedQuery(name = "Pagamentoevento.findByNrRecibo", query = "SELECT p FROM Pagamentoevento p WHERE p.nrRecibo = :nrRecibo"),
    @NamedQuery(name = "Pagamentoevento.findByIDEvento", query = "SELECT p FROM Pagamentoevento p WHERE p.pagamentoeventoPK.iDEvento = :iDEvento"),
    @NamedQuery(name = "Pagamentoevento.findByEmail", query = "SELECT p FROM Pagamentoevento p WHERE p.pagamentoeventoPK.email = :email")})
public class Pagamentoevento implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PagamentoeventoPK pagamentoeventoPK;
    @Column(name = "DataPag")
    @Temporal(TemporalType.DATE)
    private Date dataPag;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "Quantia")
    private BigDecimal quantia;
    @Column(name = "DataRecibo")
    @Temporal(TemporalType.DATE)
    private Date dataRecibo;
    @Column(name = "NrRecibo")
    private Integer nrRecibo;
    @JoinColumns({
        @JoinColumn(name = "IDEvento", referencedColumnName = "IDEvento", insertable = false, updatable = false),
        @JoinColumn(name = "Email", referencedColumnName = "Email", insertable = false, updatable = false),
        @JoinColumn(name = "NIFAssociacao", referencedColumnName = "NIFasso", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private Participa participa;
      //<Ana
    private int maxDefault;
    
     @Column(name = "NrReciboAntes")
    private String NrReciboAntes;
    @Column(name = "NrReciboDepois")
    private String NrReciboDepois;

    public String getNrReciboAntes() {
        return NrReciboAntes;
    }

    public void setNrReciboAntes(String NrReciboAntes) {
        this.NrReciboAntes = NrReciboAntes;
    }

    public String getNrReciboDepois() {
        return NrReciboDepois;
    }

    public void setNrReciboDepois(String NrReciboDepois) {
        this.NrReciboDepois = NrReciboDepois;
    }

    public void setMaxDefault(int maxDefault) {
        this.maxDefault = maxDefault;
    }

    public int getMaxDefault() {
        return maxDefault;
    }

    public Pagamentoevento() {
    }

    public Pagamentoevento(PagamentoeventoPK pagamentoeventoPK) {
        this.pagamentoeventoPK = pagamentoeventoPK;
    }

    public Pagamentoevento(PagamentoeventoPK pagamentoeventoPK, BigDecimal quantia) {
        this.pagamentoeventoPK = pagamentoeventoPK;
        this.quantia = quantia;
    }

    public Pagamentoevento(int nIFAssociacao, int iDEvento, String email) {
        this.pagamentoeventoPK = new PagamentoeventoPK(nIFAssociacao, iDEvento, email);
    }

    public PagamentoeventoPK getPagamentoeventoPK() {
        return pagamentoeventoPK;
    }

    public void setPagamentoeventoPK(PagamentoeventoPK pagamentoeventoPK) {
        this.pagamentoeventoPK = pagamentoeventoPK;
    }

    public Date getDataPag() {
        return dataPag;
    }

    public void setDataPag(Date dataPag) {
        this.dataPag = dataPag;
    }

    public BigDecimal getQuantia() {
        return quantia;
    }

    public void setQuantia(BigDecimal quantia) {
        this.quantia = quantia;
    }

    public Date getDataRecibo() {
        return dataRecibo;
    }

    public void setDataRecibo(Date dataRecibo) {
        this.dataRecibo = dataRecibo;
    }

    public Integer getNrRecibo() {
        return nrRecibo;
    }

    public void setNrRecibo(Integer nrRecibo) {
        this.nrRecibo = nrRecibo;
    }

    public Participa getParticipa() {
        return participa;
    }

    public void setParticipa(Participa participa) {
        this.participa = participa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pagamentoeventoPK != null ? pagamentoeventoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagamentoevento)) {
            return false;
        }
        Pagamentoevento other = (Pagamentoevento) object;
        if ((this.pagamentoeventoPK == null && other.pagamentoeventoPK != null) || (this.pagamentoeventoPK != null && !this.pagamentoeventoPK.equals(other.pagamentoeventoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesGASS.Pagamentoevento[ pagamentoeventoPK=" + pagamentoeventoPK + " ]";
    }
    
}
