/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "cargo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cargo.findAll", query = "SELECT c FROM Cargo c"),
    @NamedQuery(name = "Cargo.findByNome", query = "SELECT c FROM Cargo c WHERE c.cargoPK.nome = :nome"),
    @NamedQuery(name = "Cargo.findByGrupo", query = "SELECT c FROM Cargo c WHERE c.cargoPK.grupo = :grupo"),
    @NamedQuery(name = "Cargo.findByAcesso", query = "SELECT c FROM Cargo c WHERE c.acesso = :acesso")})
public class Cargo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CargoPK cargoPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Acesso")
    private String acesso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargo")
    private Collection<Evento> eventoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargo")
    private Collection<Ocupa> ocupaCollection;

    public Cargo() {
    }

    public Cargo(CargoPK cargoPK) {
        this.cargoPK = cargoPK;
    }

    public Cargo(CargoPK cargoPK, String acesso) {
        this.cargoPK = cargoPK;
        this.acesso = acesso;
    }

    public Cargo(String nome, String grupo) {
        this.cargoPK = new CargoPK(nome, grupo);
    }

    public CargoPK getCargoPK() {
        return cargoPK;
    }

    public void setCargoPK(CargoPK cargoPK) {
        this.cargoPK = cargoPK;
    }

    public String getAcesso() {
        return acesso;
    }

    public void setAcesso(String acesso) {
        this.acesso = acesso;
    }

    @XmlTransient
    public Collection<Evento> getEventoCollection() {
        return eventoCollection;
    }

    public void setEventoCollection(Collection<Evento> eventoCollection) {
        this.eventoCollection = eventoCollection;
    }

    @XmlTransient
    public Collection<Ocupa> getOcupaCollection() {
        return ocupaCollection;
    }

    public void setOcupaCollection(Collection<Ocupa> ocupaCollection) {
        this.ocupaCollection = ocupaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cargoPK != null ? cargoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cargo)) {
            return false;
        }
        Cargo other = (Cargo) object;
        if ((this.cargoPK == null && other.cargoPK != null) || (this.cargoPK != null && !this.cargoPK.equals(other.cargoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return cargoPK.getNome()+" da "+cargoPK.getGrupo();
    }
    
}
