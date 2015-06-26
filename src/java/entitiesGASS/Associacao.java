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
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "associacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Associacao.findAll", query = "SELECT a FROM Associacao a"),
    @NamedQuery(name = "Associacao.findByNif", query = "SELECT a FROM Associacao a WHERE a.nif = :nif"),
    @NamedQuery(name = "Associacao.findByEmail", query = "SELECT a FROM Associacao a WHERE a.email = :email"),
    @NamedQuery(name = "Associacao.findByNome", query = "SELECT a FROM Associacao a WHERE a.nome = :nome"),
    @NamedQuery(name = "Associacao.findByPasswd", query = "SELECT a FROM Associacao a WHERE a.passwd = :passwd")})
public class Associacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIF")
    private Integer nif;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "Nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "passwd")
    private String passwd;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "server")
    private String server;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "port")
    private String port;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "associacao")
    private Collection<Tiposocio> tiposocioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "associacao")
    private Collection<Candidato> candidatoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "associacao")
    private Collection<Evento> eventoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "associacao")
    private Collection<Estado> estadoCollection;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Associacao() {
    }

    public Associacao(Integer nif) {
        this.nif = nif;
    }

    public Associacao(Integer nif, String email, String nome, String passwd, String url, String server, String port) {
        this.nif = nif;
        this.email = email;
        this.nome = nome;
        this.passwd = passwd;
        this.url = url;
        this.server = server;
        this.port = port;
    }

    public Associacao(Integer nif, String email, String nome, String passwd) {
        this.nif = nif;
        this.email = email;
        this.nome = nome;
        this.passwd = passwd;
    }

    public Integer getNif() {
        return nif;
    }

    public void setNif(Integer nif) {
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @XmlTransient
    public Collection<Tiposocio> getTiposocioCollection() {
        return tiposocioCollection;
    }

    public void setTiposocioCollection(Collection<Tiposocio> tiposocioCollection) {
        this.tiposocioCollection = tiposocioCollection;
    }

    @XmlTransient
    public Collection<Candidato> getCandidatoCollection() {
        return candidatoCollection;
    }

    public void setCandidatoCollection(Collection<Candidato> candidatoCollection) {
        this.candidatoCollection = candidatoCollection;
    }

    @XmlTransient
    public Collection<Evento> getEventoCollection() {
        return eventoCollection;
    }

    public void setEventoCollection(Collection<Evento> eventoCollection) {
        this.eventoCollection = eventoCollection;
    }

    @XmlTransient
    public Collection<Estado> getEstadoCollection() {
        return estadoCollection;
    }

    public void setEstadoCollection(Collection<Estado> estadoCollection) {
        this.estadoCollection = estadoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nif != null ? nif.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Associacao)) {
            return false;
        }
        Associacao other = (Associacao) object;
        if ((this.nif == null && other.nif != null) || (this.nif != null && !this.nif.equals(other.nif))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesGASS.Associacao[ nif=" + nif + " ]";
    }
    
}
