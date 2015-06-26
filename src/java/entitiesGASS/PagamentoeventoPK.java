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
public class PagamentoeventoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIFAssociacao")
    private int nIFAssociacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEvento")
    private int iDEvento;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "Email")
    private String email;

    public PagamentoeventoPK() {
    }

    public PagamentoeventoPK(int nIFAssociacao, int iDEvento, String email) {
        this.nIFAssociacao = nIFAssociacao;
        this.iDEvento = iDEvento;
        this.email = email;
    }

    public int getNIFAssociacao() {
        return nIFAssociacao;
    }

    public void setNIFAssociacao(int nIFAssociacao) {
        this.nIFAssociacao = nIFAssociacao;
    }

    public int getIDEvento() {
        return iDEvento;
    }

    public void setIDEvento(int iDEvento) {
        this.iDEvento = iDEvento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) nIFAssociacao;
        hash += (int) iDEvento;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagamentoeventoPK)) {
            return false;
        }
        PagamentoeventoPK other = (PagamentoeventoPK) object;
        if (this.nIFAssociacao != other.nIFAssociacao) {
            return false;
        }
        if (this.iDEvento != other.iDEvento) {
            return false;
        }
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesGASS.PagamentoeventoPK[ nIFAssociacao=" + nIFAssociacao + ", iDEvento=" + iDEvento + ", email=" + email + " ]";
    }
    
}
