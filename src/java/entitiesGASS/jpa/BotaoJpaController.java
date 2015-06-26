/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jpa;

import entitiesGASS.Botao;
import entitiesGASS.BotaoPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entitiesGASS.Estado;
import entitiesGASS.Detalhebotao;
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
public class BotaoJpaController implements Serializable {

    public BotaoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Botao botao) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (botao.getBotaoPK() == null) {
            botao.setBotaoPK(new BotaoPK());
        }
        if (botao.getDetalhebotaoCollection() == null) {
            botao.setDetalhebotaoCollection(new ArrayList<Detalhebotao>());
        }
        botao.getBotaoPK().setIDestado(botao.getEstado().getEstadoPK().getIDestado());
        botao.getBotaoPK().setNIFassociacao(botao.getEstado().getEstadoPK().getNIFassociacao());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Estado estado = botao.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getEstadoPK());
                botao.setEstado(estado);
            }
            Collection<Detalhebotao> attachedDetalhebotaoCollection = new ArrayList<Detalhebotao>();
            for (Detalhebotao detalhebotaoCollectionDetalhebotaoToAttach : botao.getDetalhebotaoCollection()) {
                detalhebotaoCollectionDetalhebotaoToAttach = em.getReference(detalhebotaoCollectionDetalhebotaoToAttach.getClass(), detalhebotaoCollectionDetalhebotaoToAttach.getDetalhebotaoPK());
                attachedDetalhebotaoCollection.add(detalhebotaoCollectionDetalhebotaoToAttach);
            }
            botao.setDetalhebotaoCollection(attachedDetalhebotaoCollection);
            em.persist(botao);
            if (estado != null) {
                estado.getBotaoCollection().add(botao);
                estado = em.merge(estado);
            }
            for (Detalhebotao detalhebotaoCollectionDetalhebotao : botao.getDetalhebotaoCollection()) {
                Botao oldBotaoOfDetalhebotaoCollectionDetalhebotao = detalhebotaoCollectionDetalhebotao.getBotao();
                detalhebotaoCollectionDetalhebotao.setBotao(botao);
                detalhebotaoCollectionDetalhebotao = em.merge(detalhebotaoCollectionDetalhebotao);
                if (oldBotaoOfDetalhebotaoCollectionDetalhebotao != null) {
                    oldBotaoOfDetalhebotaoCollectionDetalhebotao.getDetalhebotaoCollection().remove(detalhebotaoCollectionDetalhebotao);
                    oldBotaoOfDetalhebotaoCollectionDetalhebotao = em.merge(oldBotaoOfDetalhebotaoCollectionDetalhebotao);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findBotao(botao.getBotaoPK()) != null) {
                throw new PreexistingEntityException("Botao " + botao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Botao botao) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        botao.getBotaoPK().setIDestado(botao.getEstado().getEstadoPK().getIDestado());
        botao.getBotaoPK().setNIFassociacao(botao.getEstado().getEstadoPK().getNIFassociacao());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Botao persistentBotao = em.find(Botao.class, botao.getBotaoPK());
            Estado estadoOld = persistentBotao.getEstado();
            Estado estadoNew = botao.getEstado();
            Collection<Detalhebotao> detalhebotaoCollectionOld = persistentBotao.getDetalhebotaoCollection();
            Collection<Detalhebotao> detalhebotaoCollectionNew = botao.getDetalhebotaoCollection();
            List<String> illegalOrphanMessages = null;
            for (Detalhebotao detalhebotaoCollectionOldDetalhebotao : detalhebotaoCollectionOld) {
                if (!detalhebotaoCollectionNew.contains(detalhebotaoCollectionOldDetalhebotao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalhebotao " + detalhebotaoCollectionOldDetalhebotao + " since its botao field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getEstadoPK());
                botao.setEstado(estadoNew);
            }
            Collection<Detalhebotao> attachedDetalhebotaoCollectionNew = new ArrayList<Detalhebotao>();
            for (Detalhebotao detalhebotaoCollectionNewDetalhebotaoToAttach : detalhebotaoCollectionNew) {
                detalhebotaoCollectionNewDetalhebotaoToAttach = em.getReference(detalhebotaoCollectionNewDetalhebotaoToAttach.getClass(), detalhebotaoCollectionNewDetalhebotaoToAttach.getDetalhebotaoPK());
                attachedDetalhebotaoCollectionNew.add(detalhebotaoCollectionNewDetalhebotaoToAttach);
            }
            detalhebotaoCollectionNew = attachedDetalhebotaoCollectionNew;
            botao.setDetalhebotaoCollection(detalhebotaoCollectionNew);
            botao = em.merge(botao);
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getBotaoCollection().remove(botao);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getBotaoCollection().add(botao);
                estadoNew = em.merge(estadoNew);
            }
            for (Detalhebotao detalhebotaoCollectionNewDetalhebotao : detalhebotaoCollectionNew) {
                if (!detalhebotaoCollectionOld.contains(detalhebotaoCollectionNewDetalhebotao)) {
                    Botao oldBotaoOfDetalhebotaoCollectionNewDetalhebotao = detalhebotaoCollectionNewDetalhebotao.getBotao();
                    detalhebotaoCollectionNewDetalhebotao.setBotao(botao);
                    detalhebotaoCollectionNewDetalhebotao = em.merge(detalhebotaoCollectionNewDetalhebotao);
                    if (oldBotaoOfDetalhebotaoCollectionNewDetalhebotao != null && !oldBotaoOfDetalhebotaoCollectionNewDetalhebotao.equals(botao)) {
                        oldBotaoOfDetalhebotaoCollectionNewDetalhebotao.getDetalhebotaoCollection().remove(detalhebotaoCollectionNewDetalhebotao);
                        oldBotaoOfDetalhebotaoCollectionNewDetalhebotao = em.merge(oldBotaoOfDetalhebotaoCollectionNewDetalhebotao);
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
                BotaoPK id = botao.getBotaoPK();
                if (findBotao(id) == null) {
                    throw new NonexistentEntityException("The botao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BotaoPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Botao botao;
            try {
                botao = em.getReference(Botao.class, id);
                botao.getBotaoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The botao with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Detalhebotao> detalhebotaoCollectionOrphanCheck = botao.getDetalhebotaoCollection();
            for (Detalhebotao detalhebotaoCollectionOrphanCheckDetalhebotao : detalhebotaoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Botao (" + botao + ") cannot be destroyed since the Detalhebotao " + detalhebotaoCollectionOrphanCheckDetalhebotao + " in its detalhebotaoCollection field has a non-nullable botao field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Estado estado = botao.getEstado();
            if (estado != null) {
                estado.getBotaoCollection().remove(botao);
                estado = em.merge(estado);
            }
            em.remove(botao);
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

    public List<Botao> findBotaoEntities() {
        return findBotaoEntities(true, -1, -1);
    }

    public List<Botao> findBotaoEntities(int maxResults, int firstResult) {
        return findBotaoEntities(false, maxResults, firstResult);
    }

    private List<Botao> findBotaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Botao.class));
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

    public Botao findBotao(BotaoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Botao.class, id);
        } finally {
            em.close();
        }
    }

    public int getBotaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Botao> rt = cq.from(Botao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
