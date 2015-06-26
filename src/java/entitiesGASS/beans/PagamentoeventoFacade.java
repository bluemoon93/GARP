/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.beans;

import entitiesGASS.Pagamentoevento;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author asus
 */
@Stateless
public class PagamentoeventoFacade extends AbstractFacade<Pagamentoevento> {
    @PersistenceContext(unitName = "GASSv10PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagamentoeventoFacade() {
        super(Pagamentoevento.class);
    }
    
}
