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
import entitiesGASS.Evento;
import entitiesGASS.Pagamentoevento;
import entitiesGASS.Participa;
import entitiesGASS.ParticipaPK;
import entitiesGASS.jpa.exceptions.IllegalOrphanException;
import entitiesGASS.jpa.exceptions.NonexistentEntityException;
import entitiesGASS.jpa.exceptions.PreexistingEntityException;
import entitiesGASS.jpa.exceptions.RollbackFailureException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author asus
 */
public class ParticipaJpaController implements Serializable {

    public ParticipaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Participa participa) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (participa.getParticipaPK() == null) {
            participa.setParticipaPK(new ParticipaPK());
        }
        participa.getParticipaPK().setNIFasso(participa.getEvento().getEventoPK().getNIFassoc());
        participa.getParticipaPK().setIDEvento(participa.getEvento().getEventoPK().getIDEvento());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Evento evento = participa.getEvento();
            if (evento != null) {
                evento = em.getReference(evento.getClass(), evento.getEventoPK());
                participa.setEvento(evento);
            }
            Pagamentoevento pagamentoevento = participa.getPagamentoevento();
            if (pagamentoevento != null) {
                pagamentoevento = em.getReference(pagamentoevento.getClass(), pagamentoevento.getPagamentoeventoPK());
                participa.setPagamentoevento(pagamentoevento);
            }
            em.persist(participa);
            if (evento != null) {
                evento.getParticipaCollection().add(participa);
                evento = em.merge(evento);
            }
            if (pagamentoevento != null) {
                Participa oldParticipaOfPagamentoevento = pagamentoevento.getParticipa();
                if (oldParticipaOfPagamentoevento != null) {
                    oldParticipaOfPagamentoevento.setPagamentoevento(null);
                    oldParticipaOfPagamentoevento = em.merge(oldParticipaOfPagamentoevento);
                }
                pagamentoevento.setParticipa(participa);
                pagamentoevento = em.merge(pagamentoevento);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findParticipa(participa.getParticipaPK()) != null) {
                throw new PreexistingEntityException("Participa " + participa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Participa participa) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        participa.getParticipaPK().setNIFasso(participa.getEvento().getEventoPK().getNIFassoc());
        participa.getParticipaPK().setIDEvento(participa.getEvento().getEventoPK().getIDEvento());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Participa persistentParticipa = em.find(Participa.class, participa.getParticipaPK());
            Evento eventoOld = persistentParticipa.getEvento();
            Evento eventoNew = participa.getEvento();
            Pagamentoevento pagamentoeventoOld = persistentParticipa.getPagamentoevento();
            Pagamentoevento pagamentoeventoNew = participa.getPagamentoevento();
            List<String> illegalOrphanMessages = null;
            if (pagamentoeventoOld != null && !pagamentoeventoOld.equals(pagamentoeventoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Pagamentoevento " + pagamentoeventoOld + " since its participa field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (eventoNew != null) {
                eventoNew = em.getReference(eventoNew.getClass(), eventoNew.getEventoPK());
                participa.setEvento(eventoNew);
            }
            if (pagamentoeventoNew != null) {
                pagamentoeventoNew = em.getReference(pagamentoeventoNew.getClass(), pagamentoeventoNew.getPagamentoeventoPK());
                participa.setPagamentoevento(pagamentoeventoNew);
            }
            participa = em.merge(participa);
            if (eventoOld != null && !eventoOld.equals(eventoNew)) {
                eventoOld.getParticipaCollection().remove(participa);
                eventoOld = em.merge(eventoOld);
            }
            if (eventoNew != null && !eventoNew.equals(eventoOld)) {
                eventoNew.getParticipaCollection().add(participa);
                eventoNew = em.merge(eventoNew);
            }
            if (pagamentoeventoNew != null && !pagamentoeventoNew.equals(pagamentoeventoOld)) {
                Participa oldParticipaOfPagamentoevento = pagamentoeventoNew.getParticipa();
                if (oldParticipaOfPagamentoevento != null) {
                    oldParticipaOfPagamentoevento.setPagamentoevento(null);
                    oldParticipaOfPagamentoevento = em.merge(oldParticipaOfPagamentoevento);
                }
                pagamentoeventoNew.setParticipa(participa);
                pagamentoeventoNew = em.merge(pagamentoeventoNew);
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
                ParticipaPK id = participa.getParticipaPK();
                if (findParticipa(id) == null) {
                    throw new NonexistentEntityException("The participa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ParticipaPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Participa participa;
            try {
                participa = em.getReference(Participa.class, id);
                participa.getParticipaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The participa with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Pagamentoevento pagamentoeventoOrphanCheck = participa.getPagamentoevento();
            if (pagamentoeventoOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Participa (" + participa + ") cannot be destroyed since the Pagamentoevento " + pagamentoeventoOrphanCheck + " in its pagamentoevento field has a non-nullable participa field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Evento evento = participa.getEvento();
            if (evento != null) {
                evento.getParticipaCollection().remove(participa);
                evento = em.merge(evento);
            }
            em.remove(participa);
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

    public List<Participa> findParticipaEntities() {
        return findParticipaEntities(true, -1, -1);
    }

    public List<Participa> findParticipaEntities(int maxResults, int firstResult) {
        return findParticipaEntities(false, maxResults, firstResult);
    }

    private List<Participa> findParticipaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Participa.class));
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

    public Participa findParticipa(ParticipaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Participa.class, id);
        } finally {
            em.close();
        }
    }

    public int getParticipaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Participa> rt = cq.from(Participa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
