/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS;

import java.io.Serializable;
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
@Table(name = "ocupa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ocupa.findAll", query = "SELECT o FROM Ocupa o"),
    @NamedQuery(name = "Ocupa.findByNomeC", query = "SELECT o FROM Ocupa o WHERE o.ocupaPK.nomeC = :nomeC"),
    @NamedQuery(name = "Ocupa.findByGrupoC", query = "SELECT o FROM Ocupa o WHERE o.ocupaPK.grupoC = :grupoC"),
    @NamedQuery(name = "Ocupa.findByNIFsocio", query = "SELECT o FROM Ocupa o WHERE o.ocupaPK.nIFsocio = :nIFsocio"),
    @NamedQuery(name = "Ocupa.findByNIFAssociacao", query = "SELECT o FROM Ocupa o WHERE o.ocupaPK.nIFAssociacao = :nIFAssociacao"),
    @NamedQuery(name = "Ocupa.findByDataFim", query = "SELECT o FROM Ocupa o WHERE o.dataFim = :dataFim"),
    @NamedQuery(name = "Ocupa.findByDataInicio", query = "SELECT o FROM Ocupa o WHERE o.dataInicio = :dataInicio")})
public class Ocupa implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OcupaPK ocupaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DataFim")
    @Temporal(TemporalType.DATE)
    private Date dataFim;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DataInicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @JoinColumns({
        @JoinColumn(name = "NIFsocio", referencedColumnName = "NIF", insertable = false, updatable = false),
        @JoinColumn(name = "NIFAssociacao", referencedColumnName = "NIFAssociacao", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Socio socio;
    @JoinColumns({
        @JoinColumn(name = "NomeC", referencedColumnName = "Nome", insertable = false, updatable = false),
        @JoinColumn(name = "GrupoC", referencedColumnName = "Grupo", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Cargo cargo;
    private boolean checkBox;

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }
    
    public boolean getCheckBox(){
        return this.checkBox;
    }
    
    public Ocupa() {
    }

    public Ocupa(OcupaPK ocupaPK) {
        this.ocupaPK = ocupaPK;
    }

    public Ocupa(OcupaPK ocupaPK, Date dataFim, Date dataInicio) {
        this.ocupaPK = ocupaPK;
        this.dataFim = dataFim;
        this.dataInicio = dataInicio;
    }

    public Ocupa(String nomeC, String grupoC, int nIFsocio, int nIFAssociacao) {
        this.ocupaPK = new OcupaPK(nomeC, grupoC, nIFsocio, nIFAssociacao);
    }

    public OcupaPK getOcupaPK() {
        return ocupaPK;
    }

    public void setOcupaPK(OcupaPK ocupaPK) {
        this.ocupaPK = ocupaPK;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ocupaPK != null ? ocupaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ocupa)) {
            return false;
        }
        Ocupa other = (Ocupa) object;
        if ((this.ocupaPK == null && other.ocupaPK != null) || (this.ocupaPK != null && !this.ocupaPK.equals(other.ocupaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesGASS.Ocupa[ ocupaPK=" + ocupaPK + " ]";
    }
    
}
