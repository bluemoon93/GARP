/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jpa;

import entitiesGASS.Cargo;
import entitiesGASS.CargoPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entitiesGASS.Evento;
import java.util.ArrayList;
import java.util.Collection;
import entitiesGASS.Ocupa;
import entitiesGASS.jpa.exceptions.IllegalOrphanException;
import entitiesGASS.jpa.exceptions.NonexistentEntityException;
import entitiesGASS.jpa.exceptions.PreexistingEntityException;
import entitiesGASS.jpa.exceptions.RollbackFailureException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author asus
 */
public class CargoJpaController implements Serializable {

    public CargoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cargo cargo) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (cargo.getCargoPK() == null) {
            cargo.setCargoPK(new CargoPK());
        }
        if (cargo.getEventoCollection() == null) {
            cargo.setEventoCollection(new ArrayList<Evento>());
        }
        if (cargo.getOcupaCollection() == null) {
            cargo.setOcupaCollection(new ArrayList<Ocupa>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Evento> attachedEventoCollection = new ArrayList<Evento>();
            for (Evento eventoCollectionEventoToAttach : cargo.getEventoCollection()) {
                eventoCollectionEventoToAttach = em.getReference(eventoCollectionEventoToAttach.getClass(), eventoCollectionEventoToAttach.getEventoPK());
                attachedEventoCollection.add(eventoCollectionEventoToAttach);
            }
            cargo.setEventoCollection(attachedEventoCollection);
            Collection<Ocupa> attachedOcupaCollection = new ArrayList<Ocupa>();
            for (Ocupa ocupaCollectionOcupaToAttach : cargo.getOcupaCollection()) {
                ocupaCollectionOcupaToAttach = em.getReference(ocupaCollectionOcupaToAttach.getClass(), ocupaCollectionOcupaToAttach.getOcupaPK());
                attachedOcupaCollection.add(ocupaCollectionOcupaToAttach);
            }
            cargo.setOcupaCollection(attachedOcupaCollection);
            em.persist(cargo);
            for (Evento eventoCollectionEvento : cargo.getEventoCollection()) {
                Cargo oldCargoOfEventoCollectionEvento = eventoCollectionEvento.getCargo();
                eventoCollectionEvento.setCargo(cargo);
                eventoCollectionEvento = em.merge(eventoCollectionEvento);
                if (oldCargoOfEventoCollectionEvento != null) {
                    oldCargoOfEventoCollectionEvento.getEventoCollection().remove(eventoCollectionEvento);
                    oldCargoOfEventoCollectionEvento = em.merge(oldCargoOfEventoCollectionEvento);
                }
            }
            for (Ocupa ocupaCollectionOcupa : cargo.getOcupaCollection()) {
                Cargo oldCargoOfOcupaCollectionOcupa = ocupaCollectionOcupa.getCargo();
                ocupaCollectionOcupa.setCargo(cargo);
                ocupaCollectionOcupa = em.merge(ocupaCollectionOcupa);
                if (oldCargoOfOcupaCollectionOcupa != null) {
                    oldCargoOfOcupaCollectionOcupa.getOcupaCollection().remove(ocupaCollectionOcupa);
                    oldCargoOfOcupaCollectionOcupa = em.merge(oldCargoOfOcupaCollectionOcupa);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCargo(cargo.getCargoPK()) != null) {
                throw new PreexistingEntityException("Cargo " + cargo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cargo cargo) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Cargo persistentCargo = em.find(Cargo.class, cargo.getCargoPK());
            Collection<Evento> eventoCollectionOld = persistentCargo.getEventoCollection();
            Collection<Evento> eventoCollectionNew = cargo.getEventoCollection();
            Collection<Ocupa> ocupaCollectionOld = persistentCargo.getOcupaCollection();
            Collection<Ocupa> ocupaCollectionNew = cargo.getOcupaCollection();
            List<String> illegalOrphanMessages = null;
            for (Evento eventoCollectionOldEvento : eventoCollectionOld) {
                if (!eventoCollectionNew.contains(eventoCollectionOldEvento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Evento " + eventoCollectionOldEvento + " since its cargo field is not nullable.");
                }
            }
            for (Ocupa ocupaCollectionOldOcupa : ocupaCollectionOld) {
                if (!ocupaCollectionNew.contains(ocupaCollectionOldOcupa)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ocupa " + ocupaCollectionOldOcupa + " since its cargo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Evento> attachedEventoCollectionNew = new ArrayList<Evento>();
            for (Evento eventoCollectionNewEventoToAttach : eventoCollectionNew) {
                eventoCollectionNewEventoToAttach = em.getReference(eventoCollectionNewEventoToAttach.getClass(), eventoCollectionNewEventoToAttach.getEventoPK());
                attachedEventoCollectionNew.add(eventoCollectionNewEventoToAttach);
            }
            eventoCollectionNew = attachedEventoCollectionNew;
            cargo.setEventoCollection(eventoCollectionNew);
            Collection<Ocupa> attachedOcupaCollectionNew = new ArrayList<Ocupa>();
            for (Ocupa ocupaCollectionNewOcupaToAttach : ocupaCollectionNew) {
                ocupaCollectionNewOcupaToAttach = em.getReference(ocupaCollectionNewOcupaToAttach.getClass(), ocupaCollectionNewOcupaToAttach.getOcupaPK());
                attachedOcupaCollectionNew.add(ocupaCollectionNewOcupaToAttach);
            }
            ocupaCollectionNew = attachedOcupaCollectionNew;
            cargo.setOcupaCollection(ocupaCollectionNew);
            cargo = em.merge(cargo);
            for (Evento eventoCollectionNewEvento : eventoCollectionNew) {
                if (!eventoCollectionOld.contains(eventoCollectionNewEvento)) {
                    Cargo oldCargoOfEventoCollectionNewEvento = eventoCollectionNewEvento.getCargo();
                    eventoCollectionNewEvento.setCargo(cargo);
                    eventoCollectionNewEvento = em.merge(eventoCollectionNewEvento);
                    if (oldCargoOfEventoCollectionNewEvento != null && !oldCargoOfEventoCollectionNewEvento.equals(cargo)) {
                        oldCargoOfEventoCollectionNewEvento.getEventoCollection().remove(eventoCollectionNewEvento);
                        oldCargoOfEventoCollectionNewEvento = em.merge(oldCargoOfEventoCollectionNewEvento);
                    }
                }
            }
            for (Ocupa ocupaCollectionNewOcupa : ocupaCollectionNew) {
                if (!ocupaCollectionOld.contains(ocupaCollectionNewOcupa)) {
                    Cargo oldCargoOfOcupaCollectionNewOcupa = ocupaCollectionNewOcupa.getCargo();
                    ocupaCollectionNewOcupa.setCargo(cargo);
                    ocupaCollectionNewOcupa = em.merge(ocupaCollectionNewOcupa);
                    if (oldCargoOfOcupaCollectionNewOcupa != null && !oldCargoOfOcupaCollectionNewOcupa.equals(cargo)) {
                        oldCargoOfOcupaCollectionNewOcupa.getOcupaCollection().remove(ocupaCollectionNewOcupa);
                        oldCargoOfOcupaCollectionNewOcupa = em.merge(oldCargoOfOcupaCollectionNewOcupa);
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
                CargoPK id = cargo.getCargoPK();
                if (findCargo(id) == null) {
                    throw new NonexistentEntityException("The cargo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CargoPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Cargo cargo;
            try {
                cargo = em.getReference(Cargo.class, id);
                cargo.getCargoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cargo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Evento> eventoCollectionOrphanCheck = cargo.getEventoCollection();
            for (Evento eventoCollectionOrphanCheckEvento : eventoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cargo (" + cargo + ") cannot be destroyed since the Evento " + eventoCollectionOrphanCheckEvento + " in its eventoCollection field has a non-nullable cargo field.");
            }
            Collection<Ocupa> ocupaCollectionOrphanCheck = cargo.getOcupaCollection();
            for (Ocupa ocupaCollectionOrphanCheckOcupa : ocupaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cargo (" + cargo + ") cannot be destroyed since the Ocupa " + ocupaCollectionOrphanCheckOcupa + " in its ocupaCollection field has a non-nullable cargo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cargo);
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

    public List<Cargo> findCargoEntities() {
        return findCargoEntities(true, -1, -1);
    }

    public List<Cargo> findCargoEntities(int maxResults, int firstResult) {
        return findCargoEntities(false, maxResults, firstResult);
    }

    private List<Cargo> findCargoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cargo.class));
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

    public Cargo findCargo(CargoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cargo.class, id);
        } finally {
            em.close();
        }
    }

    public int getCargoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cargo> rt = cq.from(Cargo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
