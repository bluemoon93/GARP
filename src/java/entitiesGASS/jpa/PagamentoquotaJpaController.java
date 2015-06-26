/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jpa;

import entitiesGASS.Pagamentoquota;
import entitiesGASS.PagamentoquotaPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entitiesGASS.Socio;
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
public class PagamentoquotaJpaController implements Serializable {

    public PagamentoquotaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pagamentoquota pagamentoquota) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (pagamentoquota.getPagamentoquotaPK() == null) {
            pagamentoquota.setPagamentoquotaPK(new PagamentoquotaPK());
        }
        pagamentoquota.getPagamentoquotaPK().setNIFsocio(pagamentoquota.getSocio().getSocioPK().getNif());
        pagamentoquota.getPagamentoquotaPK().setNIFAssociacao(pagamentoquota.getSocio().getSocioPK().getNIFAssociacao());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Socio socio = pagamentoquota.getSocio();
            if (socio != null) {
                socio = em.getReference(socio.getClass(), socio.getSocioPK());
                pagamentoquota.setSocio(socio);
            }
            em.persist(pagamentoquota);
            if (socio != null) {
                socio.getPagamentoquotaCollection().add(pagamentoquota);
                socio = em.merge(socio);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findPagamentoquota(pagamentoquota.getPagamentoquotaPK()) != null) {
                throw new PreexistingEntityException("Pagamentoquota " + pagamentoquota + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pagamentoquota pagamentoquota) throws NonexistentEntityException, RollbackFailureException, Exception {
        pagamentoquota.getPagamentoquotaPK().setNIFsocio(pagamentoquota.getSocio().getSocioPK().getNif());
        pagamentoquota.getPagamentoquotaPK().setNIFAssociacao(pagamentoquota.getSocio().getSocioPK().getNIFAssociacao());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Pagamentoquota persistentPagamentoquota = em.find(Pagamentoquota.class, pagamentoquota.getPagamentoquotaPK());
            Socio socioOld = persistentPagamentoquota.getSocio();
            Socio socioNew = pagamentoquota.getSocio();
            if (socioNew != null) {
                socioNew = em.getReference(socioNew.getClass(), socioNew.getSocioPK());
                pagamentoquota.setSocio(socioNew);
            }
            pagamentoquota = em.merge(pagamentoquota);
            if (socioOld != null && !socioOld.equals(socioNew)) {
                socioOld.getPagamentoquotaCollection().remove(pagamentoquota);
                socioOld = em.merge(socioOld);
            }
            if (socioNew != null && !socioNew.equals(socioOld)) {
                socioNew.getPagamentoquotaCollection().add(pagamentoquota);
                socioNew = em.merge(socioNew);
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
                PagamentoquotaPK id = pagamentoquota.getPagamentoquotaPK();
                if (findPagamentoquota(id) == null) {
                    throw new NonexistentEntityException("The pagamentoquota with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PagamentoquotaPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Pagamentoquota pagamentoquota;
            try {
                pagamentoquota = em.getReference(Pagamentoquota.class, id);
                pagamentoquota.getPagamentoquotaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagamentoquota with id " + id + " no longer exists.", enfe);
            }
            Socio socio = pagamentoquota.getSocio();
            if (socio != null) {
                socio.getPagamentoquotaCollection().remove(pagamentoquota);
                socio = em.merge(socio);
            }
            em.remove(pagamentoquota);
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

    public List<Pagamentoquota> findPagamentoquotaEntities() {
        return findPagamentoquotaEntities(true, -1, -1);
    }

    public List<Pagamentoquota> findPagamentoquotaEntities(int maxResults, int firstResult) {
        return findPagamentoquotaEntities(false, maxResults, firstResult);
    }

    private List<Pagamentoquota> findPagamentoquotaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pagamentoquota.class));
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

    public Pagamentoquota findPagamentoquota(PagamentoquotaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pagamentoquota.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagamentoquotaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pagamentoquota> rt = cq.from(Pagamentoquota.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
