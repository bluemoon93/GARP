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
import entitiesGASS.Cargo;
import entitiesGASS.Associacao;
import entitiesGASS.Evento;
import entitiesGASS.EventoPK;
import entitiesGASS.Participa;
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
public class EventoJpaController implements Serializable {

    public EventoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Evento evento) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (evento.getEventoPK() == null) {
            evento.setEventoPK(new EventoPK());
        }
        if (evento.getParticipaCollection() == null) {
            evento.setParticipaCollection(new ArrayList<Participa>());
        }
        evento.getEventoPK().setNIFassoc(evento.getAssociacao().getNif());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Cargo cargo = evento.getCargo();
            if (cargo != null) {
                cargo = em.getReference(cargo.getClass(), cargo.getCargoPK());
                evento.setCargo(cargo);
            }
            Associacao associacao = evento.getAssociacao();
            if (associacao != null) {
                associacao = em.getReference(associacao.getClass(), associacao.getNif());
                evento.setAssociacao(associacao);
            }
            Collection<Participa> attachedParticipaCollection = new ArrayList<Participa>();
            for (Participa participaCollectionParticipaToAttach : evento.getParticipaCollection()) {
                participaCollectionParticipaToAttach = em.getReference(participaCollectionParticipaToAttach.getClass(), participaCollectionParticipaToAttach.getParticipaPK());
                attachedParticipaCollection.add(participaCollectionParticipaToAttach);
            }
            evento.setParticipaCollection(attachedParticipaCollection);
            em.persist(evento);
            if (cargo != null) {
                cargo.getEventoCollection().add(evento);
                cargo = em.merge(cargo);
            }
            if (associacao != null) {
                associacao.getEventoCollection().add(evento);
                associacao = em.merge(associacao);
            }
            for (Participa participaCollectionParticipa : evento.getParticipaCollection()) {
                Evento oldEventoOfParticipaCollectionParticipa = participaCollectionParticipa.getEvento();
                participaCollectionParticipa.setEvento(evento);
                participaCollectionParticipa = em.merge(participaCollectionParticipa);
                if (oldEventoOfParticipaCollectionParticipa != null) {
                    oldEventoOfParticipaCollectionParticipa.getParticipaCollection().remove(participaCollectionParticipa);
                    oldEventoOfParticipaCollectionParticipa = em.merge(oldEventoOfParticipaCollectionParticipa);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findEvento(evento.getEventoPK()) != null) {
                throw new PreexistingEntityException("Evento " + evento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Evento evento) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        evento.getEventoPK().setNIFassoc(evento.getAssociacao().getNif());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Evento persistentEvento = em.find(Evento.class, evento.getEventoPK());
            Cargo cargoOld = persistentEvento.getCargo();
            Cargo cargoNew = evento.getCargo();
            Associacao associacaoOld = persistentEvento.getAssociacao();
            Associacao associacaoNew = evento.getAssociacao();
            Collection<Participa> participaCollectionOld = persistentEvento.getParticipaCollection();
            Collection<Participa> participaCollectionNew = evento.getParticipaCollection();
            List<String> illegalOrphanMessages = null;
            for (Participa participaCollectionOldParticipa : participaCollectionOld) {
                if (!participaCollectionNew.contains(participaCollectionOldParticipa)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Participa " + participaCollectionOldParticipa + " since its evento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (cargoNew != null) {
                cargoNew = em.getReference(cargoNew.getClass(), cargoNew.getCargoPK());
                evento.setCargo(cargoNew);
            }
            if (associacaoNew != null) {
                associacaoNew = em.getReference(associacaoNew.getClass(), associacaoNew.getNif());
                evento.setAssociacao(associacaoNew);
            }
            Collection<Participa> attachedParticipaCollectionNew = new ArrayList<Participa>();
            for (Participa participaCollectionNewParticipaToAttach : participaCollectionNew) {
                participaCollectionNewParticipaToAttach = em.getReference(participaCollectionNewParticipaToAttach.getClass(), participaCollectionNewParticipaToAttach.getParticipaPK());
                attachedParticipaCollectionNew.add(participaCollectionNewParticipaToAttach);
            }
            participaCollectionNew = attachedParticipaCollectionNew;
            evento.setParticipaCollection(participaCollectionNew);
            evento = em.merge(evento);
            if (cargoOld != null && !cargoOld.equals(cargoNew)) {
                cargoOld.getEventoCollection().remove(evento);
                cargoOld = em.merge(cargoOld);
            }
            if (cargoNew != null && !cargoNew.equals(cargoOld)) {
                cargoNew.getEventoCollection().add(evento);
                cargoNew = em.merge(cargoNew);
            }
            if (associacaoOld != null && !associacaoOld.equals(associacaoNew)) {
                associacaoOld.getEventoCollection().remove(evento);
                associacaoOld = em.merge(associacaoOld);
            }
            if (associacaoNew != null && !associacaoNew.equals(associacaoOld)) {
                associacaoNew.getEventoCollection().add(evento);
                associacaoNew = em.merge(associacaoNew);
            }
            for (Participa participaCollectionNewParticipa : participaCollectionNew) {
                if (!participaCollectionOld.contains(participaCollectionNewParticipa)) {
                    Evento oldEventoOfParticipaCollectionNewParticipa = participaCollectionNewParticipa.getEvento();
                    participaCollectionNewParticipa.setEvento(evento);
                    participaCollectionNewParticipa = em.merge(participaCollectionNewParticipa);
                    if (oldEventoOfParticipaCollectionNewParticipa != null && !oldEventoOfParticipaCollectionNewParticipa.equals(evento)) {
                        oldEventoOfParticipaCollectionNewParticipa.getParticipaCollection().remove(participaCollectionNewParticipa);
                        oldEventoOfParticipaCollectionNewParticipa = em.merge(oldEventoOfParticipaCollectionNewParticipa);
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
                EventoPK id = evento.getEventoPK();
                if (findEvento(id) == null) {
                    throw new NonexistentEntityException("The evento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(EventoPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Evento evento;
            try {
                evento = em.getReference(Evento.class, id);
                evento.getEventoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The evento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Participa> participaCollectionOrphanCheck = evento.getParticipaCollection();
            for (Participa participaCollectionOrphanCheckParticipa : participaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Evento (" + evento + ") cannot be destroyed since the Participa " + participaCollectionOrphanCheckParticipa + " in its participaCollection field has a non-nullable evento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cargo cargo = evento.getCargo();
            if (cargo != null) {
                cargo.getEventoCollection().remove(evento);
                cargo = em.merge(cargo);
            }
            Associacao associacao = evento.getAssociacao();
            if (associacao != null) {
                associacao.getEventoCollection().remove(evento);
                associacao = em.merge(associacao);
            }
            em.remove(evento);
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

    public List<Evento> findEventoEntities() {
        return findEventoEntities(true, -1, -1);
    }

    public List<Evento> findEventoEntities(int maxResults, int firstResult) {
        return findEventoEntities(false, maxResults, firstResult);
    }

    private List<Evento> findEventoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Evento.class));
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

    public Evento findEvento(EventoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Evento.class, id);
        } finally {
            em.close();
        }
    }

    public int getEventoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Evento> rt = cq.from(Evento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
