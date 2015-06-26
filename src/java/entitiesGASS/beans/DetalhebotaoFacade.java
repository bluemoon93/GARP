/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.beans;

import entitiesGASS.Detalhebotao;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author asus
 */
@Stateless
public class DetalhebotaoFacade extends AbstractFacade<Detalhebotao> {
    @PersistenceContext(unitName = "GASSv10PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalhebotaoFacade() {
        super(Detalhebotao.class);
    }
    
}
