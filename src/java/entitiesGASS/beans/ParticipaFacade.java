/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.beans;

import entitiesGASS.Participa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author asus
 */
@Stateless
public class ParticipaFacade extends AbstractFacade<Participa> {
    @PersistenceContext(unitName = "GASSv10PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParticipaFacade() {
        super(Participa.class);
    }
    
}
