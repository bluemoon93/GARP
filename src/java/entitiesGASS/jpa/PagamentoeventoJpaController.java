/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jpa;

import entitiesGASS.Pagamentoevento;
import entitiesGASS.PagamentoeventoPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entitiesGASS.Participa;
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
public class PagamentoeventoJpaController implements Serializable {

    public PagamentoeventoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pagamentoevento pagamentoevento) throws IllegalOrphanException, PreexistingEntityException, RollbackFailureException, Exception {
        if (pagamentoevento.getPagamentoeventoPK() == null) {
            pagamentoevento.setPagamentoeventoPK(new PagamentoeventoPK());
        }
        pagamentoevento.getPagamentoeventoPK().setEmail(pagamentoevento.getParticipa().getParticipaPK().getEmail());
        pagamentoevento.getPagamentoeventoPK().setIDEvento(pagamentoevento.getParticipa().getParticipaPK().getIDEvento());
        pagamentoevento.getPagamentoeventoPK().setNIFAssociacao(pagamentoevento.getParticipa().getParticipaPK().getNIFasso());
        List<String> illegalOrphanMessages = null;
        Participa participaOrphanCheck = pagamentoevento.getParticipa();
        if (participaOrphanCheck != null) {
            Pagamentoevento oldPagamentoeventoOfParticipa = participaOrphanCheck.getPagamentoevento();
            if (oldPagamentoeventoOfParticipa != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Participa " + participaOrphanCheck + " already has an item of type Pagamentoevento whose participa column cannot be null. Please make another selection for the participa field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Participa participa = pagamentoevento.getParticipa();
            if (participa != null) {
                participa = em.getReference(participa.getClass(), participa.getParticipaPK());
                pagamentoevento.setParticipa(participa);
            }
            em.persist(pagamentoevento);
            if (participa != null) {
                participa.setPagamentoevento(pagamentoevento);
                participa = em.merge(participa);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findPagamentoevento(pagamentoevento.getPagamentoeventoPK()) != null) {
                throw new PreexistingEntityException("Pagamentoevento " + pagamentoevento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pagamentoevento pagamentoevento) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        pagamentoevento.getPagamentoeventoPK().setEmail(pagamentoevento.getParticipa().getParticipaPK().getEmail());
        pagamentoevento.getPagamentoeventoPK().setIDEvento(pagamentoevento.getParticipa().getParticipaPK().getIDEvento());
        pagamentoevento.getPagamentoeventoPK().setNIFAssociacao(pagamentoevento.getParticipa().getParticipaPK().getNIFasso());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Pagamentoevento persistentPagamentoevento = em.find(Pagamentoevento.class, pagamentoevento.getPagamentoeventoPK());
            Participa participaOld = persistentPagamentoevento.getParticipa();
            Participa participaNew = pagamentoevento.getParticipa();
            List<String> illegalOrphanMessages = null;
            if (participaNew != null && !participaNew.equals(participaOld)) {
                Pagamentoevento oldPagamentoeventoOfParticipa = participaNew.getPagamentoevento();
                if (oldPagamentoeventoOfParticipa != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Participa " + participaNew + " already has an item of type Pagamentoevento whose participa column cannot be null. Please make another selection for the participa field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (participaNew != null) {
                participaNew = em.getReference(participaNew.getClass(), participaNew.getParticipaPK());
                pagamentoevento.setParticipa(participaNew);
            }
            pagamentoevento = em.merge(pagamentoevento);
            if (participaOld != null && !participaOld.equals(participaNew)) {
                participaOld.setPagamentoevento(null);
                participaOld = em.merge(participaOld);
            }
            if (participaNew != null && !participaNew.equals(participaOld)) {
                participaNew.setPagamentoevento(pagamentoevento);
                participaNew = em.merge(participaNew);
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
                PagamentoeventoPK id = pagamentoevento.getPagamentoeventoPK();
                if (findPagamentoevento(id) == null) {
                    throw new NonexistentEntityException("The pagamentoevento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PagamentoeventoPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Pagamentoevento pagamentoevento;
            try {
                pagamentoevento = em.getReference(Pagamentoevento.class, id);
                pagamentoevento.getPagamentoeventoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagamentoevento with id " + id + " no longer exists.", enfe);
            }
            Participa participa = pagamentoevento.getParticipa();
            if (participa != null) {
                participa.setPagamentoevento(null);
                participa = em.merge(participa);
            }
            em.remove(pagamentoevento);
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

    public List<Pagamentoevento> findPagamentoeventoEntities() {
        return findPagamentoeventoEntities(true, -1, -1);
    }

    public List<Pagamentoevento> findPagamentoeventoEntities(int maxResults, int firstResult) {
        return findPagamentoeventoEntities(false, maxResults, firstResult);
    }

    private List<Pagamentoevento> findPagamentoeventoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pagamentoevento.class));
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

    public Pagamentoevento findPagamentoevento(PagamentoeventoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pagamentoevento.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagamentoeventoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pagamentoevento> rt = cq.from(Pagamentoevento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
