/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "estado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estado.findAll", query = "SELECT e FROM Estado e"),
    @NamedQuery(name = "Estado.findByIDestado", query = "SELECT e FROM Estado e WHERE e.estadoPK.iDestado = :iDestado"),
    @NamedQuery(name = "Estado.findByNIFassociacao", query = "SELECT e FROM Estado e WHERE e.estadoPK.nIFassociacao = :nIFassociacao"),
    @NamedQuery(name = "Estado.findByNome", query = "SELECT e FROM Estado e WHERE e.nome = :nome")})
public class Estado implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EstadoPK estadoPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "Nome")
    private String nome;
    @JoinColumn(name = "NIFassociacao", referencedColumnName = "NIF", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Associacao associacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado")
    private Collection<Socio> socioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado")
    private Collection<Metodopassagem> metodopassagemCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado1")
    private Collection<Metodopassagem> metodopassagemCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado")
    private Collection<Botao> botaoCollection;
    //<Ana
    private boolean checkBox;
    @NotNull
    @Column(name = "eInicial")
    private boolean Inicial;

    public void seteInicial(boolean eInicial) {
        this.Inicial = eInicial;
    }
    
    public boolean getInicial() {
        return this.Inicial;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }

    public boolean getCheckBox() {
        return this.checkBox;
    }

    public Estado() {
    }

    public Estado(EstadoPK estadoPK) {
        this.estadoPK = estadoPK;
    }

    public Estado(EstadoPK estadoPK, String nome, boolean a) {
        this.estadoPK = estadoPK;
        this.nome = nome;
        Inicial=a;
    }

    public Estado(int iDestado, int nIFassociacao) {
        this.estadoPK = new EstadoPK(iDestado, nIFassociacao);
    }

    public EstadoPK getEstadoPK() {
        return estadoPK;
    }

    public void setEstadoPK(EstadoPK estadoPK) {
        this.estadoPK = estadoPK;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Associacao getAssociacao() {
        return associacao;
    }

    public void setAssociacao(Associacao associacao) {
        this.associacao = associacao;
    }

    @XmlTransient
    public Collection<Socio> getSocioCollection() {
        return socioCollection;
    }

    public void setSocioCollection(Collection<Socio> socioCollection) {
        this.socioCollection = socioCollection;
    }

    @XmlTransient
    public Collection<Metodopassagem> getMetodopassagemCollection() {
        return metodopassagemCollection;
    }

    public void setMetodopassagemCollection(Collection<Metodopassagem> metodopassagemCollection) {
        this.metodopassagemCollection = metodopassagemCollection;
    }

    @XmlTransient
    public Collection<Metodopassagem> getMetodopassagemCollection1() {
        return metodopassagemCollection1;
    }

    public void setMetodopassagemCollection1(Collection<Metodopassagem> metodopassagemCollection1) {
        this.metodopassagemCollection1 = metodopassagemCollection1;
    }

    @XmlTransient
    public Collection<Botao> getBotaoCollection() {
        return botaoCollection;
    }

    public void setBotaoCollection(Collection<Botao> botaoCollection) {
        this.botaoCollection = botaoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estadoPK != null ? estadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estado)) {
            return false;
        }
        Estado other = (Estado) object;
        if ((this.estadoPK == null && other.estadoPK != null) || (this.estadoPK != null && !this.estadoPK.equals(other.estadoPK))) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        return nome+" ("+estadoPK.getIDestado()+")";
//    }
    // <Ana
    @Override
    public String toString() {
        return nome;
    }
}
