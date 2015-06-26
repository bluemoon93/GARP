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
import entitiesGASS.Estado;
import entitiesGASS.Metodopassagem;
import entitiesGASS.MetodopassagemPK;
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
public class MetodopassagemJpaController implements Serializable {

    public MetodopassagemJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Metodopassagem metodopassagem) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (metodopassagem.getMetodopassagemPK() == null) {
            metodopassagem.setMetodopassagemPK(new MetodopassagemPK());
        }
        metodopassagem.getMetodopassagemPK().setIDestado2(metodopassagem.getEstado().getEstadoPK().getIDestado());
        metodopassagem.getMetodopassagemPK().setNIFassociacao(metodopassagem.getEstado1().getEstadoPK().getNIFassociacao());
        metodopassagem.getMetodopassagemPK().setIDestado1(metodopassagem.getEstado1().getEstadoPK().getIDestado());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Estado estado = metodopassagem.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getEstadoPK());
                metodopassagem.setEstado(estado);
            }
            Estado estado1 = metodopassagem.getEstado1();
            if (estado1 != null) {
                estado1 = em.getReference(estado1.getClass(), estado1.getEstadoPK());
                metodopassagem.setEstado1(estado1);
            }
            em.persist(metodopassagem);
            if (estado != null) {
                estado.getMetodopassagemCollection().add(metodopassagem);
                estado = em.merge(estado);
            }
            if (estado1 != null) {
                estado1.getMetodopassagemCollection().add(metodopassagem);
                estado1 = em.merge(estado1);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findMetodopassagem(metodopassagem.getMetodopassagemPK()) != null) {
                throw new PreexistingEntityException("Metodopassagem " + metodopassagem + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Metodopassagem metodopassagem) throws NonexistentEntityException, RollbackFailureException, Exception {
        metodopassagem.getMetodopassagemPK().setIDestado2(metodopassagem.getEstado().getEstadoPK().getIDestado());
        metodopassagem.getMetodopassagemPK().setNIFassociacao(metodopassagem.getEstado1().getEstadoPK().getNIFassociacao());
        metodopassagem.getMetodopassagemPK().setIDestado1(metodopassagem.getEstado1().getEstadoPK().getIDestado());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Metodopassagem persistentMetodopassagem = em.find(Metodopassagem.class, metodopassagem.getMetodopassagemPK());
            Estado estadoOld = persistentMetodopassagem.getEstado();
            Estado estadoNew = metodopassagem.getEstado();
            Estado estado1Old = persistentMetodopassagem.getEstado1();
            Estado estado1New = metodopassagem.getEstado1();
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getEstadoPK());
                metodopassagem.setEstado(estadoNew);
            }
            if (estado1New != null) {
                estado1New = em.getReference(estado1New.getClass(), estado1New.getEstadoPK());
                metodopassagem.setEstado1(estado1New);
            }
            metodopassagem = em.merge(metodopassagem);
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getMetodopassagemCollection().remove(metodopassagem);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getMetodopassagemCollection().add(metodopassagem);
                estadoNew = em.merge(estadoNew);
            }
            if (estado1Old != null && !estado1Old.equals(estado1New)) {
                estado1Old.getMetodopassagemCollection().remove(metodopassagem);
                estado1Old = em.merge(estado1Old);
            }
            if (estado1New != null && !estado1New.equals(estado1Old)) {
                estado1New.getMetodopassagemCollection().add(metodopassagem);
                estado1New = em.merge(estado1New);
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
                MetodopassagemPK id = metodopassagem.getMetodopassagemPK();
                if (findMetodopassagem(id) == null) {
                    throw new NonexistentEntityException("The metodopassagem with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MetodopassagemPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Metodopassagem metodopassagem;
            try {
                metodopassagem = em.getReference(Metodopassagem.class, id);
                metodopassagem.getMetodopassagemPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The metodopassagem with id " + id + " no longer exists.", enfe);
            }
            Estado estado = metodopassagem.getEstado();
            if (estado != null) {
                estado.getMetodopassagemCollection().remove(metodopassagem);
                estado = em.merge(estado);
            }
            Estado estado1 = metodopassagem.getEstado1();
            if (estado1 != null) {
                estado1.getMetodopassagemCollection().remove(metodopassagem);
                estado1 = em.merge(estado1);
            }
            em.remove(metodopassagem);
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

    public List<Metodopassagem> findMetodopassagemEntities() {
        return findMetodopassagemEntities(true, -1, -1);
    }

    public List<Metodopassagem> findMetodopassagemEntities(int maxResults, int firstResult) {
        return findMetodopassagemEntities(false, maxResults, firstResult);
    }

    private List<Metodopassagem> findMetodopassagemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Metodopassagem.class));
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

    public Metodopassagem findMetodopassagem(MetodopassagemPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Metodopassagem.class, id);
        } finally {
            em.close();
        }
    }

    public int getMetodopassagemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Metodopassagem> rt = cq.from(Metodopassagem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
