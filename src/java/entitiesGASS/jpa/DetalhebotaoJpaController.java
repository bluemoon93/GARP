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
import entitiesGASS.Botao;
import entitiesGASS.Detalhebotao;
import entitiesGASS.DetalhebotaoPK;
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
public class DetalhebotaoJpaController implements Serializable {

    public DetalhebotaoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalhebotao detalhebotao) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (detalhebotao.getDetalhebotaoPK() == null) {
            detalhebotao.setDetalhebotaoPK(new DetalhebotaoPK());
        }
        detalhebotao.getDetalhebotaoPK().setNIFassociacao(detalhebotao.getBotao().getBotaoPK().getNIFassociacao());
        detalhebotao.getDetalhebotaoPK().setIDbotao(detalhebotao.getBotao().getBotaoPK().getIDbotao());
        detalhebotao.getDetalhebotaoPK().setIDestado(detalhebotao.getBotao().getBotaoPK().getIDestado());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Botao botao = detalhebotao.getBotao();
            if (botao != null) {
                botao = em.getReference(botao.getClass(), botao.getBotaoPK());
                detalhebotao.setBotao(botao);
            }
            em.persist(detalhebotao);
            if (botao != null) {
                botao.getDetalhebotaoCollection().add(detalhebotao);
                botao = em.merge(botao);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findDetalhebotao(detalhebotao.getDetalhebotaoPK()) != null) {
                throw new PreexistingEntityException("Detalhebotao " + detalhebotao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalhebotao detalhebotao) throws NonexistentEntityException, RollbackFailureException, Exception {
        detalhebotao.getDetalhebotaoPK().setNIFassociacao(detalhebotao.getBotao().getBotaoPK().getNIFassociacao());
        detalhebotao.getDetalhebotaoPK().setIDbotao(detalhebotao.getBotao().getBotaoPK().getIDbotao());
        detalhebotao.getDetalhebotaoPK().setIDestado(detalhebotao.getBotao().getBotaoPK().getIDestado());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Detalhebotao persistentDetalhebotao = em.find(Detalhebotao.class, detalhebotao.getDetalhebotaoPK());
            Botao botaoOld = persistentDetalhebotao.getBotao();
            Botao botaoNew = detalhebotao.getBotao();
            if (botaoNew != null) {
                botaoNew = em.getReference(botaoNew.getClass(), botaoNew.getBotaoPK());
                detalhebotao.setBotao(botaoNew);
            }
            detalhebotao = em.merge(detalhebotao);
            if (botaoOld != null && !botaoOld.equals(botaoNew)) {
                botaoOld.getDetalhebotaoCollection().remove(detalhebotao);
                botaoOld = em.merge(botaoOld);
            }
            if (botaoNew != null && !botaoNew.equals(botaoOld)) {
                botaoNew.getDetalhebotaoCollection().add(detalhebotao);
                botaoNew = em.merge(botaoNew);
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
                DetalhebotaoPK id = detalhebotao.getDetalhebotaoPK();
                if (findDetalhebotao(id) == null) {
                    throw new NonexistentEntityException("The detalhebotao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetalhebotaoPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Detalhebotao detalhebotao;
            try {
                detalhebotao = em.getReference(Detalhebotao.class, id);
                detalhebotao.getDetalhebotaoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalhebotao with id " + id + " no longer exists.", enfe);
            }
            Botao botao = detalhebotao.getBotao();
            if (botao != null) {
                botao.getDetalhebotaoCollection().remove(detalhebotao);
                botao = em.merge(botao);
            }
            em.remove(detalhebotao);
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

    public List<Detalhebotao> findDetalhebotaoEntities() {
        return findDetalhebotaoEntities(true, -1, -1);
    }

    public List<Detalhebotao> findDetalhebotaoEntities(int maxResults, int firstResult) {
        return findDetalhebotaoEntities(false, maxResults, firstResult);
    }

    private List<Detalhebotao> findDetalhebotaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalhebotao.class));
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

    public Detalhebotao findDetalhebotao(DetalhebotaoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalhebotao.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalhebotaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalhebotao> rt = cq.from(Detalhebotao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
