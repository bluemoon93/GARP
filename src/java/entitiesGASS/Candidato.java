/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS;

import entitiesGASS.jsf.beanWorker;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "candidato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Candidato.findAll", query = "SELECT c FROM Candidato c"),
    @NamedQuery(name = "Candidato.findByEmail", query = "SELECT c FROM Candidato c WHERE c.candidatoPK.email = :email"),
    @NamedQuery(name = "Candidato.findByPalavraPasse", query = "SELECT c FROM Candidato c WHERE c.palavraPasse = :palavraPasse"),
    @NamedQuery(name = "Candidato.findByNIFassociacao", query = "SELECT c FROM Candidato c WHERE c.candidatoPK.nIFassociacao = :nIFassociacao"),
    @NamedQuery(name = "Candidato.findByNome", query = "SELECT c FROM Candidato c WHERE c.nome = :nome"),
    @NamedQuery(name = "Candidato.findByDataNasc", query = "SELECT c FROM Candidato c WHERE c.dataNasc = :dataNasc"),
    @NamedQuery(name = "Candidato.findByTelemovel", query = "SELECT c FROM Candidato c WHERE c.telemovel = :telemovel")})
public class Candidato implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CandidatoPK candidatoPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PalavraPasse")
    private String palavraPasse;
    @Size(max = 70)
    @Column(name = "Nome")
    private String nome;
    @Column(name = "DataNasc")
    @Temporal(TemporalType.DATE)
    private Date dataNasc;
    @Column(name = "Telemovel")
    private String telemovel;
    @JoinColumn(name = "NIFassociacao", referencedColumnName = "NIF", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Associacao associacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidato")
    private Collection<Socio> socioCollection;

    public Candidato() {
    }

    public Candidato(CandidatoPK candidatoPK) {
        this.candidatoPK = candidatoPK;
    }

    public Candidato(CandidatoPK candidatoPK, String palavraPasse) {
        this.candidatoPK = candidatoPK;
        this.palavraPasse = beanWorker.encodePass(palavraPasse);
    }

    public Candidato(String email, int nIFassociacao) {
        this.candidatoPK = new CandidatoPK(email, nIFassociacao);
    }

    public CandidatoPK getCandidatoPK() {
        return candidatoPK;
    }

    public void setCandidatoPK(CandidatoPK candidatoPK) {
        this.candidatoPK = candidatoPK;
    }

    public String getPalavraPasse() {
        return palavraPasse;
    }

     public void setPalavraPasse(String palavraPasse) {
        this.palavraPasse = beanWorker.encodePass(palavraPasse);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getTelemovel2() {
        return String.valueOf(telemovel); //telemovel;
    }
    
    public void setTelemovel(String nmr) {
        nmr=nmr.replace(".", "");
        nmr=nmr.replace("-", "");
        nmr=nmr.replace(" ", "");
        this.telemovel = nmr;
    }
    
    public String getTelemovel() {
        return telemovel; //telemovel;
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
        hash += (candidatoPK != null ? candidatoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Candidato)) {
            return false;
        }
        Candidato other = (Candidato) object;
        if ((this.candidatoPK == null && other.candidatoPK != null) || (this.candidatoPK != null && !this.candidatoPK.equals(other.candidatoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesGASS.Candidato[ candidatoPK=" + candidatoPK + " ]";
    }
    
      
    
}
