/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author asus
 */
@Embeddable
public class CandidatoPK implements Serializable {
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIFassociacao")
    private int nIFassociacao;

    public CandidatoPK() {
    }

    public CandidatoPK(String email, int nIFassociacao) {
        this.email = email;
        this.nIFassociacao = nIFassociacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNIFassociacao() {
        return nIFassociacao;
    }

    public void setNIFassociacao(int nIFassociacao) {
        this.nIFassociacao = nIFassociacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        hash += (int) nIFassociacao;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CandidatoPK)) {
            return false;
        }
        CandidatoPK other = (CandidatoPK) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        if (this.nIFassociacao != other.nIFassociacao) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesGASS.CandidatoPK[ email=" + email + ", nIFassociacao=" + nIFassociacao + " ]";
    }
    
}
