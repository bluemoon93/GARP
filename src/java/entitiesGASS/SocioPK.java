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

/**
 *
 * @author asus
 */
@Embeddable
public class SocioPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIF")
    private int nif;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIFAssociacao")
    private int nIFAssociacao;

    public SocioPK() {
    }

    public SocioPK(int nif, int nIFAssociacao) {
        this.nif = nif;
        this.nIFAssociacao = nIFAssociacao;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public int getNIFAssociacao() {
        return nIFAssociacao;
    }

    public void setNIFAssociacao(int nIFAssociacao) {
        this.nIFAssociacao = nIFAssociacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) nif;
        hash += (int) nIFAssociacao;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SocioPK)) {
            return false;
        }
        SocioPK other = (SocioPK) object;
        if (this.nif != other.nif) {
            return false;
        }
        if (this.nIFAssociacao != other.nIFAssociacao) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesGASS.SocioPK[ nif=" + nif + ", nIFAssociacao=" + nIFAssociacao + " ]";
    }
    
}
