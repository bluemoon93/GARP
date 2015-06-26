/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.beans;

import entitiesGASS.Botao;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author asus
 */
@Stateless
public class BotaoFacade extends AbstractFacade<Botao> {
    @PersistenceContext(unitName = "GASSv10PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BotaoFacade() {
        super(Botao.class);
    }
    
}
