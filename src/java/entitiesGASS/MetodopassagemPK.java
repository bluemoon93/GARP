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
public class MetodopassagemPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDestado1")
    private int iDestado1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDestado2")
    private int iDestado2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIFassociacao")
    private int nIFassociacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDmetodo")
    private int iDmetodo;

    public MetodopassagemPK() {
    }

    public MetodopassagemPK(int iDestado1, int iDestado2, int nIFassociacao, int iDmetodo) {
        this.iDestado1 = iDestado1;
        this.iDestado2 = iDestado2;
        this.nIFassociacao = nIFassociacao;
        this.iDmetodo = iDmetodo;
    }

    public int getIDestado1() {
        return iDestado1;
    }

    public void setIDestado1(int iDestado1) {
        this.iDestado1 = iDestado1;
    }

    public int getIDestado2() {
        return iDestado2;
    }

    public void setIDestado2(int iDestado2) {
        this.iDestado2 = iDestado2;
    }

    public int getNIFassociacao() {
        return nIFassociacao;
    }

    public void setNIFassociacao(int nIFassociacao) {
        this.nIFassociacao = nIFassociacao;
    }

    public int getIDmetodo() {
        return iDmetodo;
    }

    public void setIDmetodo(int iDmetodo) {
        this.iDmetodo = iDmetodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iDestado1;
        hash += (int) iDestado2;
        hash += (int) nIFassociacao;
        hash += (int) iDmetodo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MetodopassagemPK)) {
            return false;
        }
        MetodopassagemPK other = (MetodopassagemPK) object;
        if (this.iDestado1 != other.iDestado1) {
            return false;
        }
        if (this.iDestado2 != other.iDestado2) {
            return false;
        }
        if (this.nIFassociacao != other.nIFassociacao) {
            return false;
        }
        if (this.iDmetodo != other.iDmetodo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesGASS.MetodopassagemPK[ iDestado1=" + iDestado1 + ", iDestado2=" + iDestado2 + ", nIFassociacao=" + nIFassociacao + ", iDmetodo=" + iDmetodo + " ]";
    }
    
}
