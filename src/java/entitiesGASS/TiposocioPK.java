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
public class TiposocioPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIFassociacao")
    private int nIFassociacao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Nome")
    private String nome;

    public TiposocioPK() {
    }

    public TiposocioPK(int nIFassociacao, String nome) {
        this.nIFassociacao = nIFassociacao;
        this.nome = nome;
    }

    public int getNIFassociacao() {
        return nIFassociacao;
    }

    public void setNIFassociacao(int nIFassociacao) {
        this.nIFassociacao = nIFassociacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) nIFassociacao;
        hash += (nome != null ? nome.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposocioPK)) {
            return false;
        }
        TiposocioPK other = (TiposocioPK) object;
        if (this.nIFassociacao != other.nIFassociacao) {
            return false;
        }
        if ((this.nome == null && other.nome != null) || (this.nome != null && !this.nome.equals(other.nome))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesGASS.TiposocioPK[ nIFassociacao=" + nIFassociacao + ", nome=" + nome + " ]";
    }
    
}
