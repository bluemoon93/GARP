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
public class OcupaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NomeC")
    private String nomeC;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "GrupoC")
    private String grupoC;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIFsocio")
    private int nIFsocio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIFAssociacao")
    private int nIFAssociacao;

    public OcupaPK() {
    }

    public OcupaPK(String nomeC, String grupoC, int nIFsocio, int nIFAssociacao) {
        this.nomeC = nomeC;
        this.grupoC = grupoC;
        this.nIFsocio = nIFsocio;
        this.nIFAssociacao = nIFAssociacao;
    }

    public String getNomeC() {
        return nomeC;
    }

    public void setNomeC(String nomeC) {
        this.nomeC = nomeC;
    }

    public String getGrupoC() {
        return grupoC;
    }

    public void setGrupoC(String grupoC) {
        this.grupoC = grupoC;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nomeC != null ? nomeC.hashCode() : 0);
        hash += (grupoC != null ? grupoC.hashCode() : 0);
        hash += (int) nIFsocio;
        hash += (int) nIFAssociacao;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OcupaPK)) {
            return false;
        }
        OcupaPK other = (OcupaPK) object;
        if ((this.nomeC == null && other.nomeC != null) || (this.nomeC != null && !this.nomeC.equals(other.nomeC))) {
            return false;
        }
        if ((this.grupoC == null && other.grupoC != null) || (this.grupoC != null && !this.grupoC.equals(other.grupoC))) {
            return false;
        }
        if (this.nIFsocio != other.nIFsocio) {
            return false;
        }
        if (this.nIFAssociacao != other.nIFAssociacao) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesGASS.OcupaPK[ nomeC=" + nomeC + ", grupoC=" + grupoC + ", nIFsocio=" + nIFsocio + ", nIFAssociacao=" + nIFAssociacao + " ]";
    }
    
}
