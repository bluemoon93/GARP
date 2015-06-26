/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "evento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e"),
    @NamedQuery(name = "Evento.findByIDEvento", query = "SELECT e FROM Evento e WHERE e.eventoPK.iDEvento = :iDEvento"),
    @NamedQuery(name = "Evento.findByNIFassoc", query = "SELECT e FROM Evento e WHERE e.eventoPK.nIFassoc = :nIFassoc"),
    @NamedQuery(name = "Evento.findByDataEvento", query = "SELECT e FROM Evento e WHERE e.dataEvento = :dataEvento"),
    @NamedQuery(name = "Evento.findByLocalizacao", query = "SELECT e FROM Evento e WHERE e.localizacao = :localizacao"),
    @NamedQuery(name = "Evento.findByConteudo", query = "SELECT e FROM Evento e WHERE e.conteudo = :conteudo"),
    @NamedQuery(name = "Evento.findByDataCriacao", query = "SELECT e FROM Evento e WHERE e.dataCriacao = :dataCriacao"),
    @NamedQuery(name = "Evento.findByTitulo", query = "SELECT e FROM Evento e WHERE e.titulo = :titulo")})
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EventoPK eventoPK;
    @Column(name = "DataEvento")
    @Temporal(TemporalType.DATE)
    private Date dataEvento;
    @Size(max = 30)
    @Column(name = "Localizacao")
    private String localizacao;
    @Size(max = 700)
    @Column(name = "Conteudo")
    private String conteudo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DataCriacao")
    @Temporal(TemporalType.DATE)
    private Date dataCriacao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Titulo")
    private String titulo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evento")
    private Collection<Participa> participaCollection;
    @JoinColumns({
        @JoinColumn(name = "Nome", referencedColumnName = "Nome"),
        @JoinColumn(name = "Grupo", referencedColumnName = "Grupo")})
    @ManyToOne(optional = false)
    private Cargo cargo;
    @JoinColumn(name = "NIFassoc", referencedColumnName = "NIF", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Associacao associacao;
    //<Ana
    @Basic(optional = false)
    @NotNull
    private boolean checkBox;
    @Column(name = "Quantia")
    private BigDecimal quantia;

    public BigDecimal getQuantia() {
        return quantia;
    }

    public void setQuantia(BigDecimal quantia) {
        this.quantia = quantia;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }

    public boolean getCheckBox() {
        return this.checkBox;
    }

    public Evento() {
    }

    public Evento(EventoPK eventoPK) {
        this.eventoPK = eventoPK;
    }

    public Evento(EventoPK eventoPK, Date dataCriacao, String titulo) {
        this.eventoPK = eventoPK;
        this.dataCriacao = dataCriacao;
        this.titulo = titulo;
    }

    public Evento(int iDEvento, int nIFassoc) {
        this.eventoPK = new EventoPK(iDEvento, nIFassoc);
    }

    public EventoPK getEventoPK() {
        return eventoPK;
    }

    public void setEventoPK(EventoPK eventoPK) {
        this.eventoPK = eventoPK;
    }

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @XmlTransient
    public Collection<Participa> getParticipaCollection() {
        return participaCollection;
    }

    public void setParticipaCollection(Collection<Participa> participaCollection) {
        this.participaCollection = participaCollection;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Associacao getAssociacao() {
        return associacao;
    }

    public void setAssociacao(Associacao associacao) {
        this.associacao = associacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventoPK != null ? eventoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.eventoPK == null && other.eventoPK != null) || (this.eventoPK != null && !this.eventoPK.equals(other.eventoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesGASS.Evento[ eventoPK=" + eventoPK + " ]";
    }
}
