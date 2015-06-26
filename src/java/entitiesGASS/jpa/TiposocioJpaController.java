/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entitiesGASS.Associacao;
import entitiesGASS.Socio;
import entitiesGASS.Tiposocio;
import entitiesGASS.TiposocioPK;
import entitiesGASS.jpa.exceptions.IllegalOrphanException;
import entitiesGASS.jpa.exceptions.NonexistentEntityException;
import entitiesGASS.jpa.exceptions.PreexistingEntityException;
import entitiesGASS.jpa.exceptions.RollbackFailureException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author asus
 */
public class TiposocioJpaController implements Serializable {

    public TiposocioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tiposocio tiposocio) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (tiposocio.getTiposocioPK() == null) {
            tiposocio.setTiposocioPK(new TiposocioPK());
        }
        if (tiposocio.getSocioCollection() == null) {
            tiposocio.setSocioCollection(new ArrayList<Socio>());
        }
        tiposocio.getTiposocioPK().setNIFassociacao(tiposocio.getAssociacao().getNif());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Associacao associacao = tiposocio.getAssociacao();
            if (associacao != null) {
                associacao = em.getReference(associacao.getClass(), associacao.getNif());
                tiposocio.setAssociacao(associacao);
            }
            Collection<Socio> attachedSocioCollection = new ArrayList<Socio>();
            for (Socio socioCollectionSocioToAttach : tiposocio.getSocioCollection()) {
                socioCollectionSocioToAttach = em.getReference(socioCollectionSocioToAttach.getClass(), socioCollectionSocioToAttach.getSocioPK());
                attachedSocioCollection.add(socioCollectionSocioToAttach);
            }
            tiposocio.setSocioCollection(attachedSocioCollection);
            em.persist(tiposocio);
            if (associacao != null) {
                associacao.getTiposocioCollection().add(tiposocio);
                associacao = em.merge(associacao);
            }
            for (Socio socioCollectionSocio : tiposocio.getSocioCollection()) {
                Tiposocio oldTiposocioOfSocioCollectionSocio = socioCollectionSocio.getTiposocio();
                socioCollectionSocio.setTiposocio(tiposocio);
                socioCollectionSocio = em.merge(socioCollectionSocio);
                if (oldTiposocioOfSocioCollectionSocio != null) {
                    oldTiposocioOfSocioCollectionSocio.getSocioCollection().remove(socioCollectionSocio);
                    oldTiposocioOfSocioCollectionSocio = em.merge(oldTiposocioOfSocioCollectionSocio);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findTiposocio(tiposocio.getTiposocioPK()) != null) {
                throw new PreexistingEntityException("Tiposocio " + tiposocio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tiposocio tiposocio) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        tiposocio.getTiposocioPK().setNIFassociacao(tiposocio.getAssociacao().getNif());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Tiposocio persistentTiposocio = em.find(Tiposocio.class, tiposocio.getTiposocioPK());
            Associacao associacaoOld = persistentTiposocio.getAssociacao();
            Associacao associacaoNew = tiposocio.getAssociacao();
            Collection<Socio> socioCollectionOld = persistentTiposocio.getSocioCollection();
            Collection<Socio> socioCollectionNew = tiposocio.getSocioCollection();
            List<String> illegalOrphanMessages = null;
            for (Socio socioCollectionOldSocio : socioCollectionOld) {
                if (!socioCollectionNew.contains(socioCollectionOldSocio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Socio " + socioCollectionOldSocio + " since its tiposocio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (associacaoNew != null) {
                associacaoNew = em.getReference(associacaoNew.getClass(), associacaoNew.getNif());
                tiposocio.setAssociacao(associacaoNew);
            }
            Collection<Socio> attachedSocioCollectionNew = new ArrayList<Socio>();
            for (Socio socioCollectionNewSocioToAttach : socioCollectionNew) {
                socioCollectionNewSocioToAttach = em.getReference(socioCollectionNewSocioToAttach.getClass(), socioCollectionNewSocioToAttach.getSocioPK());
                attachedSocioCollectionNew.add(socioCollectionNewSocioToAttach);
            }
            socioCollectionNew = attachedSocioCollectionNew;
            tiposocio.setSocioCollection(socioCollectionNew);
            tiposocio = em.merge(tiposocio);
            if (associacaoOld != null && !associacaoOld.equals(associacaoNew)) {
                associacaoOld.getTiposocioCollection().remove(tiposocio);
                associacaoOld = em.merge(associacaoOld);
            }
            if (associacaoNew != null && !associacaoNew.equals(associacaoOld)) {
                associacaoNew.getTiposocioCollection().add(tiposocio);
                associacaoNew = em.merge(associacaoNew);
            }
            for (Socio socioCollectionNewSocio : socioCollectionNew) {
                if (!socioCollectionOld.contains(socioCollectionNewSocio)) {
                    Tiposocio oldTiposocioOfSocioCollectionNewSocio = socioCollectionNewSocio.getTiposocio();
                    socioCollectionNewSocio.setTiposocio(tiposocio);
                    socioCollectionNewSocio = em.merge(socioCollectionNewSocio);
                    if (oldTiposocioOfSocioCollectionNewSocio != null && !oldTiposocioOfSocioCollectionNewSocio.equals(tiposocio)) {
                        oldTiposocioOfSocioCollectionNewSocio.getSocioCollection().remove(socioCollectionNewSocio);
                        oldTiposocioOfSocioCollectionNewSocio = em.merge(oldTiposocioOfSocioCollectionNewSocio);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                TiposocioPK id = tiposocio.getTiposocioPK();
                if (findTiposocio(id) == null) {
                    throw new NonexistentEntityException("The tiposocio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(TiposocioPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Tiposocio tiposocio;
            try {
                tiposocio = em.getReference(Tiposocio.class, id);
                tiposocio.getTiposocioPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tiposocio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Socio> socioCollectionOrphanCheck = tiposocio.getSocioCollection();
            for (Socio socioCollectionOrphanCheckSocio : socioCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tiposocio (" + tiposocio + ") cannot be destroyed since the Socio " + socioCollectionOrphanCheckSocio + " in its socioCollection field has a non-nullable tiposocio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Associacao associacao = tiposocio.getAssociacao();
            if (associacao != null) {
                associacao.getTiposocioCollection().remove(tiposocio);
                associacao = em.merge(associacao);
            }
            em.remove(tiposocio);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tiposocio> findTiposocioEntities() {
        return findTiposocioEntities(true, -1, -1);
    }

    public List<Tiposocio> findTiposocioEntities(int maxResults, int firstResult) {
        return findTiposocioEntities(false, maxResults, firstResult);
    }

    private List<Tiposocio> findTiposocioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tiposocio.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tiposocio findTiposocio(TiposocioPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tiposocio.class, id);
        } finally {
            em.close();
        }
    }

    public int getTiposocioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tiposocio> rt = cq.from(Tiposocio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
