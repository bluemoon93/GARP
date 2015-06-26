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
public class DetalhebotaoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "IDcaixa")
    private int iDcaixa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDbotao")
    private int iDbotao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDestado")
    private int iDestado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIFassociacao")
    private int nIFassociacao;

    public DetalhebotaoPK() {
    }

    public DetalhebotaoPK(int iDcaixa, int iDbotao, int iDestado, int nIFassociacao) {
        this.iDcaixa = iDcaixa;
        this.iDbotao = iDbotao;
        this.iDestado = iDestado;
        this.nIFassociacao = nIFassociacao;
    }

    public int getIDcaixa() {
        return iDcaixa;
    }

    public void setIDcaixa(int iDcaixa) {
        this.iDcaixa = iDcaixa;
    }

    public int getIDbotao() {
        return iDbotao;
    }

    public void setIDbotao(int iDbotao) {
        this.iDbotao = iDbotao;
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
        hash += (int) iDcaixa;
        hash += (int) iDbotao;
        hash += (int) iDestado;
        hash += (int) nIFassociacao;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalhebotaoPK)) {
            return false;
        }
        DetalhebotaoPK other = (DetalhebotaoPK) object;
        if (this.iDcaixa != other.iDcaixa) {
            return false;
        }
        if (this.iDbotao != other.iDbotao) {
            return false;
        }
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
        return "entitiesGASS.DetalhebotaoPK[ iDcaixa=" + iDcaixa + ", iDbotao=" + iDbotao + ", iDestado=" + iDestado + ", nIFassociacao=" + nIFassociacao + " ]";
    }
    
}
