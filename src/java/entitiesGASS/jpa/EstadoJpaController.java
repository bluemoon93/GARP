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
import java.util.ArrayList;
import java.util.Collection;
import entitiesGASS.Metodopassagem;
import entitiesGASS.Botao;
import entitiesGASS.Estado;
import entitiesGASS.EstadoPK;
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
public class EstadoJpaController implements Serializable {

    public EstadoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estado estado) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (estado.getEstadoPK() == null) {
            estado.setEstadoPK(new EstadoPK());
        }
        if (estado.getSocioCollection() == null) {
            estado.setSocioCollection(new ArrayList<Socio>());
        }
        if (estado.getMetodopassagemCollection() == null) {
            estado.setMetodopassagemCollection(new ArrayList<Metodopassagem>());
        }
        if (estado.getMetodopassagemCollection1() == null) {
            estado.setMetodopassagemCollection1(new ArrayList<Metodopassagem>());
        }
        if (estado.getBotaoCollection() == null) {
            estado.setBotaoCollection(new ArrayList<Botao>());
        }
        estado.getEstadoPK().setNIFassociacao(estado.getAssociacao().getNif());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Associacao associacao = estado.getAssociacao();
            if (associacao != null) {
                associacao = em.getReference(associacao.getClass(), associacao.getNif());
                estado.setAssociacao(associacao);
            }
            Collection<Socio> attachedSocioCollection = new ArrayList<Socio>();
            for (Socio socioCollectionSocioToAttach : estado.getSocioCollection()) {
                socioCollectionSocioToAttach = em.getReference(socioCollectionSocioToAttach.getClass(), socioCollectionSocioToAttach.getSocioPK());
                attachedSocioCollection.add(socioCollectionSocioToAttach);
            }
            estado.setSocioCollection(attachedSocioCollection);
            Collection<Metodopassagem> attachedMetodopassagemCollection = new ArrayList<Metodopassagem>();
            for (Metodopassagem metodopassagemCollectionMetodopassagemToAttach : estado.getMetodopassagemCollection()) {
                metodopassagemCollectionMetodopassagemToAttach = em.getReference(metodopassagemCollectionMetodopassagemToAttach.getClass(), metodopassagemCollectionMetodopassagemToAttach.getMetodopassagemPK());
                attachedMetodopassagemCollection.add(metodopassagemCollectionMetodopassagemToAttach);
            }
            estado.setMetodopassagemCollection(attachedMetodopassagemCollection);
            Collection<Metodopassagem> attachedMetodopassagemCollection1 = new ArrayList<Metodopassagem>();
            for (Metodopassagem metodopassagemCollection1MetodopassagemToAttach : estado.getMetodopassagemCollection1()) {
                metodopassagemCollection1MetodopassagemToAttach = em.getReference(metodopassagemCollection1MetodopassagemToAttach.getClass(), metodopassagemCollection1MetodopassagemToAttach.getMetodopassagemPK());
                attachedMetodopassagemCollection1.add(metodopassagemCollection1MetodopassagemToAttach);
            }
            estado.setMetodopassagemCollection1(attachedMetodopassagemCollection1);
            Collection<Botao> attachedBotaoCollection = new ArrayList<Botao>();
            for (Botao botaoCollectionBotaoToAttach : estado.getBotaoCollection()) {
                botaoCollectionBotaoToAttach = em.getReference(botaoCollectionBotaoToAttach.getClass(), botaoCollectionBotaoToAttach.getBotaoPK());
                attachedBotaoCollection.add(botaoCollectionBotaoToAttach);
            }
            estado.setBotaoCollection(attachedBotaoCollection);
            em.persist(estado);
            if (associacao != null) {
                associacao.getEstadoCollection().add(estado);
                associacao = em.merge(associacao);
            }
            for (Socio socioCollectionSocio : estado.getSocioCollection()) {
                Estado oldEstadoOfSocioCollectionSocio = socioCollectionSocio.getEstado();
                socioCollectionSocio.setEstado(estado);
                socioCollectionSocio = em.merge(socioCollectionSocio);
                if (oldEstadoOfSocioCollectionSocio != null) {
                    oldEstadoOfSocioCollectionSocio.getSocioCollection().remove(socioCollectionSocio);
                    oldEstadoOfSocioCollectionSocio = em.merge(oldEstadoOfSocioCollectionSocio);
                }
            }
            for (Metodopassagem metodopassagemCollectionMetodopassagem : estado.getMetodopassagemCollection()) {
                Estado oldEstadoOfMetodopassagemCollectionMetodopassagem = metodopassagemCollectionMetodopassagem.getEstado();
                metodopassagemCollectionMetodopassagem.setEstado(estado);
                metodopassagemCollectionMetodopassagem = em.merge(metodopassagemCollectionMetodopassagem);
                if (oldEstadoOfMetodopassagemCollectionMetodopassagem != null) {
                    oldEstadoOfMetodopassagemCollectionMetodopassagem.getMetodopassagemCollection().remove(metodopassagemCollectionMetodopassagem);
                    oldEstadoOfMetodopassagemCollectionMetodopassagem = em.merge(oldEstadoOfMetodopassagemCollectionMetodopassagem);
                }
            }
            for (Metodopassagem metodopassagemCollection1Metodopassagem : estado.getMetodopassagemCollection1()) {
                Estado oldEstado1OfMetodopassagemCollection1Metodopassagem = metodopassagemCollection1Metodopassagem.getEstado1();
                metodopassagemCollection1Metodopassagem.setEstado1(estado);
                metodopassagemCollection1Metodopassagem = em.merge(metodopassagemCollection1Metodopassagem);
                if (oldEstado1OfMetodopassagemCollection1Metodopassagem != null) {
                    oldEstado1OfMetodopassagemCollection1Metodopassagem.getMetodopassagemCollection1().remove(metodopassagemCollection1Metodopassagem);
                    oldEstado1OfMetodopassagemCollection1Metodopassagem = em.merge(oldEstado1OfMetodopassagemCollection1Metodopassagem);
                }
            }
            for (Botao botaoCollectionBotao : estado.getBotaoCollection()) {
                Estado oldEstadoOfBotaoCollectionBotao = botaoCollectionBotao.getEstado();
                botaoCollectionBotao.setEstado(estado);
                botaoCollectionBotao = em.merge(botaoCollectionBotao);
                if (oldEstadoOfBotaoCollectionBotao != null) {
                    oldEstadoOfBotaoCollectionBotao.getBotaoCollection().remove(botaoCollectionBotao);
                    oldEstadoOfBotaoCollectionBotao = em.merge(oldEstadoOfBotaoCollectionBotao);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findEstado(estado.getEstadoPK()) != null) {
                throw new PreexistingEntityException("Estado " + estado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estado estado) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        estado.getEstadoPK().setNIFassociacao(estado.getAssociacao().getNif());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Estado persistentEstado = em.find(Estado.class, estado.getEstadoPK());
            Associacao associacaoOld = persistentEstado.getAssociacao();
            Associacao associacaoNew = estado.getAssociacao();
            Collection<Socio> socioCollectionOld = persistentEstado.getSocioCollection();
            Collection<Socio> socioCollectionNew = estado.getSocioCollection();
            Collection<Metodopassagem> metodopassagemCollectionOld = persistentEstado.getMetodopassagemCollection();
            Collection<Metodopassagem> metodopassagemCollectionNew = estado.getMetodopassagemCollection();
            Collection<Metodopassagem> metodopassagemCollection1Old = persistentEstado.getMetodopassagemCollection1();
            Collection<Metodopassagem> metodopassagemCollection1New = estado.getMetodopassagemCollection1();
            Collection<Botao> botaoCollectionOld = persistentEstado.getBotaoCollection();
            Collection<Botao> botaoCollectionNew = estado.getBotaoCollection();
            List<String> illegalOrphanMessages = null;
            for (Socio socioCollectionOldSocio : socioCollectionOld) {
                if (!socioCollectionNew.contains(socioCollectionOldSocio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Socio " + socioCollectionOldSocio + " since its estado field is not nullable.");
                }
            }
            for (Metodopassagem metodopassagemCollectionOldMetodopassagem : metodopassagemCollectionOld) {
                if (!metodopassagemCollectionNew.contains(metodopassagemCollectionOldMetodopassagem)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Metodopassagem " + metodopassagemCollectionOldMetodopassagem + " since its estado field is not nullable.");
                }
            }
            for (Metodopassagem metodopassagemCollection1OldMetodopassagem : metodopassagemCollection1Old) {
                if (!metodopassagemCollection1New.contains(metodopassagemCollection1OldMetodopassagem)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Metodopassagem " + metodopassagemCollection1OldMetodopassagem + " since its estado1 field is not nullable.");
                }
            }
            for (Botao botaoCollectionOldBotao : botaoCollectionOld) {
                if (!botaoCollectionNew.contains(botaoCollectionOldBotao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Botao " + botaoCollectionOldBotao + " since its estado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (associacaoNew != null) {
                associacaoNew = em.getReference(associacaoNew.getClass(), associacaoNew.getNif());
                estado.setAssociacao(associacaoNew);
            }
            Collection<Socio> attachedSocioCollectionNew = new ArrayList<Socio>();
            for (Socio socioCollectionNewSocioToAttach : socioCollectionNew) {
                socioCollectionNewSocioToAttach = em.getReference(socioCollectionNewSocioToAttach.getClass(), socioCollectionNewSocioToAttach.getSocioPK());
                attachedSocioCollectionNew.add(socioCollectionNewSocioToAttach);
            }
            socioCollectionNew = attachedSocioCollectionNew;
            estado.setSocioCollection(socioCollectionNew);
            Collection<Metodopassagem> attachedMetodopassagemCollectionNew = new ArrayList<Metodopassagem>();
            for (Metodopassagem metodopassagemCollectionNewMetodopassagemToAttach : metodopassagemCollectionNew) {
                metodopassagemCollectionNewMetodopassagemToAttach = em.getReference(metodopassagemCollectionNewMetodopassagemToAttach.getClass(), metodopassagemCollectionNewMetodopassagemToAttach.getMetodopassagemPK());
                attachedMetodopassagemCollectionNew.add(metodopassagemCollectionNewMetodopassagemToAttach);
            }
            metodopassagemCollectionNew = attachedMetodopassagemCollectionNew;
            estado.setMetodopassagemCollection(metodopassagemCollectionNew);
            Collection<Metodopassagem> attachedMetodopassagemCollection1New = new ArrayList<Metodopassagem>();
            for (Metodopassagem metodopassagemCollection1NewMetodopassagemToAttach : metodopassagemCollection1New) {
                metodopassagemCollection1NewMetodopassagemToAttach = em.getReference(metodopassagemCollection1NewMetodopassagemToAttach.getClass(), metodopassagemCollection1NewMetodopassagemToAttach.getMetodopassagemPK());
                attachedMetodopassagemCollection1New.add(metodopassagemCollection1NewMetodopassagemToAttach);
            }
            metodopassagemCollection1New = attachedMetodopassagemCollection1New;
            estado.setMetodopassagemCollection1(metodopassagemCollection1New);
            Collection<Botao> attachedBotaoCollectionNew = new ArrayList<Botao>();
            for (Botao botaoCollectionNewBotaoToAttach : botaoCollectionNew) {
                botaoCollectionNewBotaoToAttach = em.getReference(botaoCollectionNewBotaoToAttach.getClass(), botaoCollectionNewBotaoToAttach.getBotaoPK());
                attachedBotaoCollectionNew.add(botaoCollectionNewBotaoToAttach);
            }
            botaoCollectionNew = attachedBotaoCollectionNew;
            estado.setBotaoCollection(botaoCollectionNew);
            estado = em.merge(estado);
            if (associacaoOld != null && !associacaoOld.equals(associacaoNew)) {
                associacaoOld.getEstadoCollection().remove(estado);
                associacaoOld = em.merge(associacaoOld);
            }
            if (associacaoNew != null && !associacaoNew.equals(associacaoOld)) {
                associacaoNew.getEstadoCollection().add(estado);
                associacaoNew = em.merge(associacaoNew);
            }
            for (Socio socioCollectionNewSocio : socioCollectionNew) {
                if (!socioCollectionOld.contains(socioCollectionNewSocio)) {
                    Estado oldEstadoOfSocioCollectionNewSocio = socioCollectionNewSocio.getEstado();
                    socioCollectionNewSocio.setEstado(estado);
                    socioCollectionNewSocio = em.merge(socioCollectionNewSocio);
                    if (oldEstadoOfSocioCollectionNewSocio != null && !oldEstadoOfSocioCollectionNewSocio.equals(estado)) {
                        oldEstadoOfSocioCollectionNewSocio.getSocioCollection().remove(socioCollectionNewSocio);
                        oldEstadoOfSocioCollectionNewSocio = em.merge(oldEstadoOfSocioCollectionNewSocio);
                    }
                }
            }
            for (Metodopassagem metodopassagemCollectionNewMetodopassagem : metodopassagemCollectionNew) {
                if (!metodopassagemCollectionOld.contains(metodopassagemCollectionNewMetodopassagem)) {
                    Estado oldEstadoOfMetodopassagemCollectionNewMetodopassagem = metodopassagemCollectionNewMetodopassagem.getEstado();
                    metodopassagemCollectionNewMetodopassagem.setEstado(estado);
                    metodopassagemCollectionNewMetodopassagem = em.merge(metodopassagemCollectionNewMetodopassagem);
                    if (oldEstadoOfMetodopassagemCollectionNewMetodopassagem != null && !oldEstadoOfMetodopassagemCollectionNewMetodopassagem.equals(estado)) {
                        oldEstadoOfMetodopassagemCollectionNewMetodopassagem.getMetodopassagemCollection().remove(metodopassagemCollectionNewMetodopassagem);
                        oldEstadoOfMetodopassagemCollectionNewMetodopassagem = em.merge(oldEstadoOfMetodopassagemCollectionNewMetodopassagem);
                    }
                }
            }
            for (Metodopassagem metodopassagemCollection1NewMetodopassagem : metodopassagemCollection1New) {
                if (!metodopassagemCollection1Old.contains(metodopassagemCollection1NewMetodopassagem)) {
                    Estado oldEstado1OfMetodopassagemCollection1NewMetodopassagem = metodopassagemCollection1NewMetodopassagem.getEstado1();
                    metodopassagemCollection1NewMetodopassagem.setEstado1(estado);
                    metodopassagemCollection1NewMetodopassagem = em.merge(metodopassagemCollection1NewMetodopassagem);
                    if (oldEstado1OfMetodopassagemCollection1NewMetodopassagem != null && !oldEstado1OfMetodopassagemCollection1NewMetodopassagem.equals(estado)) {
                        oldEstado1OfMetodopassagemCollection1NewMetodopassagem.getMetodopassagemCollection1().remove(metodopassagemCollection1NewMetodopassagem);
                        oldEstado1OfMetodopassagemCollection1NewMetodopassagem = em.merge(oldEstado1OfMetodopassagemCollection1NewMetodopassagem);
                    }
                }
            }
            for (Botao botaoCollectionNewBotao : botaoCollectionNew) {
                if (!botaoCollectionOld.contains(botaoCollectionNewBotao)) {
                    Estado oldEstadoOfBotaoCollectionNewBotao = botaoCollectionNewBotao.getEstado();
                    botaoCollectionNewBotao.setEstado(estado);
                    botaoCollectionNewBotao = em.merge(botaoCollectionNewBotao);
                    if (oldEstadoOfBotaoCollectionNewBotao != null && !oldEstadoOfBotaoCollectionNewBotao.equals(estado)) {
                        oldEstadoOfBotaoCollectionNewBotao.getBotaoCollection().remove(botaoCollectionNewBotao);
                        oldEstadoOfBotaoCollectionNewBotao = em.merge(oldEstadoOfBotaoCollectionNewBotao);
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
                EstadoPK id = estado.getEstadoPK();
                if (findEstado(id) == null) {
                    throw new NonexistentEntityException("The estado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(EstadoPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Estado estado;
            try {
                estado = em.getReference(Estado.class, id);
                estado.getEstadoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Socio> socioCollectionOrphanCheck = estado.getSocioCollection();
            for (Socio socioCollectionOrphanCheckSocio : socioCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estado (" + estado + ") cannot be destroyed since the Socio " + socioCollectionOrphanCheckSocio + " in its socioCollection field has a non-nullable estado field.");
            }
            Collection<Metodopassagem> metodopassagemCollectionOrphanCheck = estado.getMetodopassagemCollection();
            for (Metodopassagem metodopassagemCollectionOrphanCheckMetodopassagem : metodopassagemCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estado (" + estado + ") cannot be destroyed since the Metodopassagem " + metodopassagemCollectionOrphanCheckMetodopassagem + " in its metodopassagemCollection field has a non-nullable estado field.");
            }
            Collection<Metodopassagem> metodopassagemCollection1OrphanCheck = estado.getMetodopassagemCollection1();
            for (Metodopassagem metodopassagemCollection1OrphanCheckMetodopassagem : metodopassagemCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estado (" + estado + ") cannot be destroyed since the Metodopassagem " + metodopassagemCollection1OrphanCheckMetodopassagem + " in its metodopassagemCollection1 field has a non-nullable estado1 field.");
            }
            Collection<Botao> botaoCollectionOrphanCheck = estado.getBotaoCollection();
            for (Botao botaoCollectionOrphanCheckBotao : botaoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estado (" + estado + ") cannot be destroyed since the Botao " + botaoCollectionOrphanCheckBotao + " in its botaoCollection field has a non-nullable estado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Associacao associacao = estado.getAssociacao();
            if (associacao != null) {
                associacao.getEstadoCollection().remove(estado);
                associacao = em.merge(associacao);
            }
            em.remove(estado);
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

    public List<Estado> findEstadoEntities() {
        return findEstadoEntities(true, -1, -1);
    }

    public List<Estado> findEstadoEntities(int maxResults, int firstResult) {
        return findEstadoEntities(false, maxResults, firstResult);
    }

    private List<Estado> findEstadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estado.class));
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

    public Estado findEstado(EstadoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estado> rt = cq.from(Estado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
