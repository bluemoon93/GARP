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
public class EventoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "IDEvento")
    private int iDEvento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIFassoc")
    private int nIFassoc;

    public EventoPK() {
    }

    public EventoPK(int iDEvento, int nIFassoc) {
        this.iDEvento = iDEvento;
        this.nIFassoc = nIFassoc;
    }

    public int getIDEvento() {
        return iDEvento;
    }

    public void setIDEvento(int iDEvento) {
        this.iDEvento = iDEvento;
    }

    public int getNIFassoc() {
        return nIFassoc;
    }

    public void setNIFassoc(int nIFassoc) {
        this.nIFassoc = nIFassoc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iDEvento;
        hash += (int) nIFassoc;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventoPK)) {
            return false;
        }
        EventoPK other = (EventoPK) object;
        if (this.iDEvento != other.iDEvento) {
            return false;
        }
        if (this.nIFassoc != other.nIFassoc) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesGASS.EventoPK[ iDEvento=" + iDEvento + ", nIFassoc=" + nIFassoc + " ]";
    }
    
}
