/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS;

import entitiesGASS.jsf.SocioController;
import entitiesGASS.jsf.beanWorker;
import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "socio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Socio.findAll", query = "SELECT s FROM Socio s"),
    @NamedQuery(name = "Socio.findByNif", query = "SELECT s FROM Socio s WHERE s.socioPK.nif = :nif"),
    @NamedQuery(name = "Socio.findByNIFAssociacao", query = "SELECT s FROM Socio s WHERE s.socioPK.nIFAssociacao = :nIFAssociacao"),
    @NamedQuery(name = "Socio.findByNrSocio", query = "SELECT s FROM Socio s WHERE s.nrSocio = :nrSocio"),
    @NamedQuery(name = "Socio.findByDataModificacao", query = "SELECT s FROM Socio s WHERE s.dataModificacao = :dataModificacao")})
public class Socio implements Serializable, Comparable<Socio> {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SocioPK socioPK;
    @Column(name = "NrSocio")
    private Integer nrSocio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DataModificacao")
    @Temporal(TemporalType.DATE)
    private Date dataModificacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "socio")
    private Collection<Pagamentoquota> pagamentoquotaCollection;
    @JoinColumns({
        @JoinColumn(name = "EmailCand", referencedColumnName = "Email"),
        @JoinColumn(name = "NIFAssociacao", referencedColumnName = "NIFassociacao", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Candidato candidato;
    @JoinColumns({
        @JoinColumn(name = "IDestado", referencedColumnName = "IDestado"),
        @JoinColumn(name = "NIFAssociacao", referencedColumnName = "NIFassociacao", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Estado estado;
    @JoinColumns({
        @JoinColumn(name = "NIFAssociacao", referencedColumnName = "NIFassociacao", insertable = false, updatable = false),
        @JoinColumn(name = "tipo", referencedColumnName = "Nome")})
    @ManyToOne(optional = false)
    private Tiposocio tiposocio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "socio")
    private Collection<Ocupa> ocupaCollection;
    private static int ord=0;
       //<Ana
    private boolean checkBox;
    @NotNull
    private int maxAnoQuota;

    public String xxx(Socio a){
        SocioController socCtrl = (SocioController) beanWorker.getTheBean("socio");
        Socio x = socCtrl.getJpaController().find(a.socioPK);
        //System.out.print(x.getCandidato().getNome());
        //System.out.println(x.getMaxAnoQuota());
        return String.valueOf(x.getMaxAnoQuota());
    }
    
    public int getMaxAnoQuota() {
        return maxAnoQuota;
    }

    public void setMaxAnoQuota(int maxAnoQuota) {
        this.maxAnoQuota = maxAnoQuota;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }

    public boolean getCheckBox() {
        return this.checkBox;
    }
    
    public static void setOrder(int order){
        ord = order;
    }
    public static int getOrder(){
        return ord;
    }
    @Override
    public int compareTo(Socio s) {
        //System.out.println("Socio.java Ord->"+ord);
        
        //DEFAULT -> NOME ASCENDENTE
        // POR NIF 
        if(ord == 2){   //ASCENDENTE
            if(getSocioPK().getNif()  == s.getSocioPK().getNif())
                return 0;
            if(getSocioPK().getNif() < s.getSocioPK().getNif())
                return -1;
            else return 1;
        }
        if(ord == -2){  //DESCENDENTE
            if(getSocioPK().getNif()  == s.getSocioPK().getNif())
                return 0;
            if(getSocioPK().getNif() < s.getSocioPK().getNif())
                return 1;
            else return -1;
        }
        // POR NR. SOCIO
        if(ord == 3){   //ASCENDENTE
            if(nrSocio  == s.getNrSocio())
                return 0;
            if(nrSocio < s.getNrSocio())
                return -1;
            else return 1;
        }
        if(ord == -3){  //DESCENDENTE
            if(nrSocio  == s.getNrSocio())
                return 0;
            if(nrSocio < s.getNrSocio())
                return 1;
            else return -1;
        }
        // POR EMAIL
        if(ord == 4)   //ASCENDENTE
            return candidato.getCandidatoPK().getEmail().compareTo(s.getCandidato().getCandidatoPK().getEmail());
        if(ord == -4)  //DESCENDENTE
            return s.getCandidato().getCandidatoPK().getEmail().compareTo(candidato.getCandidatoPK().getEmail());
        // POR ESTADO
        if(ord == 5)   //ASCENDENTE
            return estado.getNome().compareTo(s.getEstado().getNome());
        if(ord == -5)  //DESCENDENTE
            return s.getEstado().getNome().compareTo(estado.getNome());
        // POR TIPO
        if(ord == 6)   //ASCENDENTE
            return tiposocio.getTiposocioPK().getNome().compareTo(s.getTiposocio().getTiposocioPK().getNome());
        if(ord == -6)  //DESCENDENTE
            return s.getTiposocio().getTiposocioPK().getNome().compareTo(tiposocio.getTiposocioPK().getNome());
        // POR NOME
        if(ord == -1)    
            return s.candidato.getNome().compareTo(candidato.getNome());            
        else
           return  candidato.getNome().compareTo(s.candidato.getNome());
    }
    public Socio() {
    }

    public Socio(SocioPK socioPK) {
        this.socioPK = socioPK;
    }

    public Socio(SocioPK socioPK, Date dataModificacao) {
        this.socioPK = socioPK;
        this.dataModificacao = dataModificacao;
    }

    public Socio(int nif, int nIFAssociacao) {
        this.socioPK = new SocioPK(nif, nIFAssociacao);
    }

    public SocioPK getSocioPK() {
        return socioPK;
    }

    public void setSocioPK(SocioPK socioPK) {
        this.socioPK = socioPK;
    }

    public Integer getNrSocio() {
        return nrSocio;
    }

    public void setNrSocio(Integer nrSocio) {
        this.nrSocio = nrSocio;
    }

    public Date getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(Date dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    @XmlTransient
    public Collection<Pagamentoquota> getPagamentoquotaCollection() {
        return pagamentoquotaCollection;
    }

    public void setPagamentoquotaCollection(Collection<Pagamentoquota> pagamentoquotaCollection) {
        this.pagamentoquotaCollection = pagamentoquotaCollection;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Tiposocio getTiposocio() {
        return tiposocio;
    }

    public void setTiposocio(Tiposocio tiposocio) {
        this.tiposocio = tiposocio;
    }

    @XmlTransient
    public Collection<Ocupa> getOcupaCollection() {
        return ocupaCollection;
    }

    public void setOcupaCollection(Collection<Ocupa> ocupaCollection) {
        this.ocupaCollection = ocupaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (socioPK != null ? socioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Socio)) {
            return false;
        }
        Socio other = (Socio) object;
        if ((this.socioPK == null && other.socioPK != null) || (this.socioPK != null && !this.socioPK.equals(other.socioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nrSocio + ", " + candidato.getNome() +" ("+candidato.getCandidatoPK().getEmail()+")";
    }
}
