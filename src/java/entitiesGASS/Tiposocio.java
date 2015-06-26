/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "tiposocio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tiposocio.findAll", query = "SELECT t FROM Tiposocio t"),
    @NamedQuery(name = "Tiposocio.findByNIFassociacao", query = "SELECT t FROM Tiposocio t WHERE t.tiposocioPK.nIFassociacao = :nIFassociacao"),
    @NamedQuery(name = "Tiposocio.findByQuantia", query = "SELECT t FROM Tiposocio t WHERE t.quantia = :quantia"),
    @NamedQuery(name = "Tiposocio.findByNome", query = "SELECT t FROM Tiposocio t WHERE t.tiposocioPK.nome = :nome")})
public class Tiposocio implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TiposocioPK tiposocioPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "Quantia")
    private BigDecimal quantia;
    @JoinColumn(name = "NIFassociacao", referencedColumnName = "NIF", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Associacao associacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiposocio")
    private Collection<Socio> socioCollection;
    private boolean checkBox;

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }
    
    public boolean getCheckBox(){
        return this.checkBox;
    }
    public Tiposocio() {
    }

    public Tiposocio(TiposocioPK tiposocioPK) {
        this.tiposocioPK = tiposocioPK;
    }

    public Tiposocio(TiposocioPK tiposocioPK, BigDecimal quantia) {
        this.tiposocioPK = tiposocioPK;
        this.quantia = quantia;
    }

    public Tiposocio(int nIFassociacao, String nome) {
        this.tiposocioPK = new TiposocioPK(nIFassociacao, nome);
    }

    public TiposocioPK getTiposocioPK() {
        return tiposocioPK;
    }

    public void setTiposocioPK(TiposocioPK tiposocioPK) {
        this.tiposocioPK = tiposocioPK;
    }

    public BigDecimal getQuantia() {
        return quantia;
    }

    public void setQuantia(BigDecimal quantia) {
        this.quantia = quantia;
    }

    public Associacao getAssociacao() {
        return associacao;
    }

    public void setAssociacao(Associacao associacao) {
        this.associacao = associacao;
    }

    @XmlTransient
    public Collection<Socio> getSocioCollection() {
        return socioCollection;
    }

    public void setSocioCollection(Collection<Socio> socioCollection) {
        this.socioCollection = socioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tiposocioPK != null ? tiposocioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiposocio)) {
            return false;
        }
        Tiposocio other = (Tiposocio) object;
        if ((this.tiposocioPK == null && other.tiposocioPK != null) || (this.tiposocioPK != null && !this.tiposocioPK.equals(other.tiposocioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.tiposocioPK.getNome();
    }
    
}
