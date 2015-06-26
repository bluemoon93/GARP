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
public class BotaoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDestado")
    private int iDestado;
    @Basic(optional = false)
    @Column(name = "IDbotao")
    private int iDbotao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIFassociacao")
    private int nIFassociacao;

    public BotaoPK() {
    }

    public BotaoPK(int iDestado, int iDbotao, int nIFassociacao) {
        this.iDestado = iDestado;
        this.iDbotao = iDbotao;
        this.nIFassociacao = nIFassociacao;
    }

    public int getIDestado() {
        return iDestado;
    }

    public void setIDestado(int iDestado) {
        this.iDestado = iDestado;
    }

    public int getIDbotao() {
        return iDbotao;
    }

    public void setIDbotao(int iDbotao) {
        this.iDbotao = iDbotao;
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
        hash += (int) iDbotao;
        hash += (int) nIFassociacao;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BotaoPK)) {
            return false;
        }
        BotaoPK other = (BotaoPK) object;
        if (this.iDestado != other.iDestado) {
            return false;
        }
        if (this.iDbotao != other.iDbotao) {
            return false;
        }
        if (this.nIFassociacao != other.nIFassociacao) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesGASS.BotaoPK[ iDestado=" + iDestado + ", iDbotao=" + iDbotao + ", nIFassociacao=" + nIFassociacao + " ]";
    }
    
}
