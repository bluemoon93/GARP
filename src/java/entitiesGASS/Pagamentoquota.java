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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "pagamentoquota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagamentoquota.findAll", query = "SELECT p FROM Pagamentoquota p"),
    @NamedQuery(name = "Pagamentoquota.findByDataPag", query = "SELECT p FROM Pagamentoquota p WHERE p.dataPag = :dataPag"),
    @NamedQuery(name = "Pagamentoquota.findByQuantia", query = "SELECT p FROM Pagamentoquota p WHERE p.quantia = :quantia"),
    @NamedQuery(name = "Pagamentoquota.findByNIFsocio", query = "SELECT p FROM Pagamentoquota p WHERE p.pagamentoquotaPK.nIFsocio = :nIFsocio"),
    @NamedQuery(name = "Pagamentoquota.findByNIFAssociacao", query = "SELECT p FROM Pagamentoquota p WHERE p.pagamentoquotaPK.nIFAssociacao = :nIFAssociacao"),
    @NamedQuery(name = "Pagamentoquota.findByDataRecibo", query = "SELECT p FROM Pagamentoquota p WHERE p.dataRecibo = :dataRecibo"),
    @NamedQuery(name = "Pagamentoquota.findByNrRecibo", query = "SELECT p FROM Pagamentoquota p WHERE p.nrRecibo = :nrRecibo"),
    @NamedQuery(name = "Pagamentoquota.findByAno", query = "SELECT p FROM Pagamentoquota p WHERE p.pagamentoquotaPK.ano = :ano")})
public class Pagamentoquota implements Serializable, Comparable<Pagamentoquota> {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PagamentoquotaPK pagamentoquotaPK;
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
        @JoinColumn(name = "NIFsocio", referencedColumnName = "NIF", insertable = false, updatable = false),
        @JoinColumn(name = "NIFAssociacao", referencedColumnName = "NIFAssociacao", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Socio socio;
    
       //<Ana
    @Column(name = "NrReciboAntes")
    private String NrReciboAntes;
    @Column(name = "NrReciboDepois")
    private String NrReciboDepois;
 
    private boolean checkBox;
    private int maxDefault;

    public String getNrReciboAntes() {
        if(NrReciboAntes==null) return "";
        return NrReciboAntes;
    }

    public void setNrReciboAntes(String NrReciboAntes) {
        this.NrReciboAntes = NrReciboAntes;
    }

    public String getNrReciboDepois() {
        if(NrReciboDepois==null) return "";
        return NrReciboDepois;
    }

    public void setNrReciboDepois(String NrReciboDepois) {
        this.NrReciboDepois = NrReciboDepois;
    }


    public int getMaxDefault() {
        return maxDefault;
    }

    public void setMaxDefault(int maxDefault) {
        this.maxDefault = maxDefault;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }

    public boolean getCheckBox() {
        return this.checkBox;
    }

    @Override
    public int compareTo(Pagamentoquota s) {
        return socio.getCandidato().getNome().compareTo(s.getSocio().getCandidato().getNome());
    }

    public Pagamentoquota() {
    }

    public Pagamentoquota(PagamentoquotaPK pagamentoquotaPK) {
        this.pagamentoquotaPK = pagamentoquotaPK;
    }

    public Pagamentoquota(PagamentoquotaPK pagamentoquotaPK, BigDecimal quantia) {
        this.pagamentoquotaPK = pagamentoquotaPK;
        this.quantia = quantia;
    }

    public Pagamentoquota(int nIFsocio, int nIFAssociacao, int ano) {
        this.pagamentoquotaPK = new PagamentoquotaPK(nIFsocio, nIFAssociacao, ano);
    }

    public PagamentoquotaPK getPagamentoquotaPK() {
        return pagamentoquotaPK;
    }

    public void setPagamentoquotaPK(PagamentoquotaPK pagamentoquotaPK) {
        this.pagamentoquotaPK = pagamentoquotaPK;
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

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pagamentoquotaPK != null ? pagamentoquotaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagamentoquota)) {
            return false;
        }
        Pagamentoquota other = (Pagamentoquota) object;
        if ((this.pagamentoquotaPK == null && other.pagamentoquotaPK != null) || (this.pagamentoquotaPK != null && !this.pagamentoquotaPK.equals(other.pagamentoquotaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesGASS.Pagamentoquota[ pagamentoquotaPK=" + pagamentoquotaPK + " ]";
    }
}
