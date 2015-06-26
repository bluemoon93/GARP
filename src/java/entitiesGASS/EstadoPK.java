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
public class EstadoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "IDestado")
    private int iDestado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIFassociacao")
    private int nIFassociacao;

    public EstadoPK() {
    }

    public EstadoPK(int iDestado, int nIFassociacao) {
        this.iDestado = iDestado;
        this.nIFassociacao = nIFassociacao;
    }

    public int getIDestado() {
        return iDestado;
    }

    public void setIDestado(int iDestado) {
        this.iDestado = iDestado;
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
        hash += (int) iDestado;
        hash += (int) nIFassociacao;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoPK)) {
            return false;
        }
        EstadoPK other = (EstadoPK) object;
        if (this.iDestado != other.iDestado) {
            return false;
        }
        if (this.nIFassociacao != other.nIFassociacao) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesGASS.EstadoPK[ iDestado=" + iDestado + ", nIFassociacao=" + nIFassociacao + " ]";
    }
    
}
