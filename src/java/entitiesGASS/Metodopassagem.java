/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "metodopassagem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Metodopassagem.findAll", query = "SELECT m FROM Metodopassagem m"),
    @NamedQuery(name = "Metodopassagem.findByIDestado1", query = "SELECT m FROM Metodopassagem m WHERE m.metodopassagemPK.iDestado1 = :iDestado1"),
    @NamedQuery(name = "Metodopassagem.findByIDestado2", query = "SELECT m FROM Metodopassagem m WHERE m.metodopassagemPK.iDestado2 = :iDestado2"),
    @NamedQuery(name = "Metodopassagem.findByNIFassociacao", query = "SELECT m FROM Metodopassagem m WHERE m.metodopassagemPK.nIFassociacao = :nIFassociacao"),
    @NamedQuery(name = "Metodopassagem.findByIDmetodo", query = "SELECT m FROM Metodopassagem m WHERE m.metodopassagemPK.iDmetodo = :iDmetodo"),
    @NamedQuery(name = "Metodopassagem.findByAnos", query = "SELECT m FROM Metodopassagem m WHERE m.anos = :anos"),
    @NamedQuery(name = "Metodopassagem.findByMeses", query = "SELECT m FROM Metodopassagem m WHERE m.meses = :meses"),
    @NamedQuery(name = "Metodopassagem.findByDias", query = "SELECT m FROM Metodopassagem m WHERE m.dias = :dias")})
public class Metodopassagem implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MetodopassagemPK metodopassagemPK;
    @Column(name = "anos")
    private Integer anos;
    @Column(name = "meses")
    private Integer meses;
    @Column(name = "dias")
    private Integer dias;
    @JoinColumns({
        @JoinColumn(name = "IDestado2", referencedColumnName = "IDestado", insertable = false, updatable = false),
        @JoinColumn(name = "NIFassociacao", referencedColumnName = "NIFassociacao", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Estado estado;
    @JoinColumns({
        @JoinColumn(name = "IDestado1", referencedColumnName = "IDestado", insertable = false, updatable = false),
        @JoinColumn(name = "NIFassociacao", referencedColumnName = "NIFassociacao", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Estado estado1;
        //<Ana
    private boolean checkBox;

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }

    public boolean getCheckBox() {
        return this.checkBox;
    }

    public Metodopassagem() {
    }

    public Metodopassagem(MetodopassagemPK metodopassagemPK) {
        this.metodopassagemPK = metodopassagemPK;
    }

    public Metodopassagem(int iDestado1, int iDestado2, int nIFassociacao, int iDmetodo) {
        this.metodopassagemPK = new MetodopassagemPK(iDestado1, iDestado2, nIFassociacao, iDmetodo);
    }

    public MetodopassagemPK getMetodopassagemPK() {
        return metodopassagemPK;
    }

    public void setMetodopassagemPK(MetodopassagemPK metodopassagemPK) {
        this.metodopassagemPK = metodopassagemPK;
    }

    public Integer getAnos() {
        return anos;
    }

    public void setAnos(Integer anos) {
        this.anos = anos;
    }

    public Integer getMeses() {
        return meses;
    }

    public void setMeses(Integer meses) {
        this.meses = meses;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Estado getEstado1() {
        return estado1;
    }

    public void setEstado1(Estado estado1) {
        this.estado1 = estado1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (metodopassagemPK != null ? metodopassagemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Metodopassagem)) {
            return false;
        }
        Metodopassagem other = (Metodopassagem) object;
        if ((this.metodopassagemPK == null && other.metodopassagemPK != null) || (this.metodopassagemPK != null && !this.metodopassagemPK.equals(other.metodopassagemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesGASS.Metodopassagem[ metodopassagemPK=" + metodopassagemPK + " ]";
    }
    
}
