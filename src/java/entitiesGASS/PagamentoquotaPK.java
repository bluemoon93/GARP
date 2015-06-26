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
public class PagamentoquotaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIFsocio")
    private int nIFsocio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIFAssociacao")
    private int nIFAssociacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ano")
    private int ano;

    public PagamentoquotaPK() {
    }

    public PagamentoquotaPK(int nIFsocio, int nIFAssociacao, int ano) {
        this.nIFsocio = nIFsocio;
        this.nIFAssociacao = nIFAssociacao;
        this.ano = ano;
    }

    public int getNIFsocio() {
        return nIFsocio;
    }

    public void setNIFsocio(int nIFsocio) {
        this.nIFsocio = nIFsocio;
    }

    public int getNIFAssociacao() {
        return nIFAssociacao;
    }

    public void setNIFAssociacao(int nIFAssociacao) {
        this.nIFAssociacao = nIFAssociacao;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) nIFsocio;
        hash += (int) nIFAssociacao;
        hash += (int) ano;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagamentoquotaPK)) {
            return false;
        }
        PagamentoquotaPK other = (PagamentoquotaPK) object;
        if (this.nIFsocio != other.nIFsocio) {
            return false;
        }
        if (this.nIFAssociacao != other.nIFAssociacao) {
            return false;
        }
        if (this.ano != other.ano) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesGASS.PagamentoquotaPK[ nIFsocio=" + nIFsocio + ", nIFAssociacao=" + nIFAssociacao + ", ano=" + ano + " ]";
    }
    
}
