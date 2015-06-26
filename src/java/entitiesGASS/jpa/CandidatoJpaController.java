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
import entitiesGASS.Candidato;
import entitiesGASS.CandidatoPK;
import entitiesGASS.Socio;
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
public class CandidatoJpaController implements Serializable {

    public CandidatoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Candidato candidato) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (candidato.getCandidatoPK() == null) {
            candidato.setCandidatoPK(new CandidatoPK());
        }
        if (candidato.getSocioCollection() == null) {
            candidato.setSocioCollection(new ArrayList<Socio>());
        }
        candidato.getCandidatoPK().setNIFassociacao(candidato.getAssociacao().getNif());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Associacao associacao = candidato.getAssociacao();
            if (associacao != null) {
                associacao = em.getReference(associacao.getClass(), associacao.getNif());
                candidato.setAssociacao(associacao);
            }
            Collection<Socio> attachedSocioCollection = new ArrayList<Socio>();
            for (Socio socioCollectionSocioToAttach : candidato.getSocioCollection()) {
                socioCollectionSocioToAttach = em.getReference(socioCollectionSocioToAttach.getClass(), socioCollectionSocioToAttach.getSocioPK());
                attachedSocioCollection.add(socioCollectionSocioToAttach);
            }
            candidato.setSocioCollection(attachedSocioCollection);
            em.persist(candidato);
            if (associacao != null) {
                associacao.getCandidatoCollection().add(candidato);
                associacao = em.merge(associacao);
            }
            for (Socio socioCollectionSocio : candidato.getSocioCollection()) {
                Candidato oldCandidatoOfSocioCollectionSocio = socioCollectionSocio.getCandidato();
                socioCollectionSocio.setCandidato(candidato);
                socioCollectionSocio = em.merge(socioCollectionSocio);
                if (oldCandidatoOfSocioCollectionSocio != null) {
                    oldCandidatoOfSocioCollectionSocio.getSocioCollection().remove(socioCollectionSocio);
                    oldCandidatoOfSocioCollectionSocio = em.merge(oldCandidatoOfSocioCollectionSocio);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCandidato(candidato.getCandidatoPK()) != null) {
                throw new PreexistingEntityException("Candidato " + candidato + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Candidato candidato) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        candidato.getCandidatoPK().setNIFassociacao(candidato.getAssociacao().getNif());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Candidato persistentCandidato = em.find(Candidato.class, candidato.getCandidatoPK());
            Associacao associacaoOld = persistentCandidato.getAssociacao();
            Associacao associacaoNew = candidato.getAssociacao();
            Collection<Socio> socioCollectionOld = persistentCandidato.getSocioCollection();
            Collection<Socio> socioCollectionNew = candidato.getSocioCollection();
            List<String> illegalOrphanMessages = null;
            for (Socio socioCollectionOldSocio : socioCollectionOld) {
                if (!socioCollectionNew.contains(socioCollectionOldSocio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Socio " + socioCollectionOldSocio + " since its candidato field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (associacaoNew != null) {
                associacaoNew = em.getReference(associacaoNew.getClass(), associacaoNew.getNif());
                candidato.setAssociacao(associacaoNew);
            }
            Collection<Socio> attachedSocioCollectionNew = new ArrayList<Socio>();
            for (Socio socioCollectionNewSocioToAttach : socioCollectionNew) {
                socioCollectionNewSocioToAttach = em.getReference(socioCollectionNewSocioToAttach.getClass(), socioCollectionNewSocioToAttach.getSocioPK());
                attachedSocioCollectionNew.add(socioCollectionNewSocioToAttach);
            }
            socioCollectionNew = attachedSocioCollectionNew;
            candidato.setSocioCollection(socioCollectionNew);
            candidato = em.merge(candidato);
            if (associacaoOld != null && !associacaoOld.equals(associacaoNew)) {
                associacaoOld.getCandidatoCollection().remove(candidato);
                associacaoOld = em.merge(associacaoOld);
            }
            if (associacaoNew != null && !associacaoNew.equals(associacaoOld)) {
                associacaoNew.getCandidatoCollection().add(candidato);
                associacaoNew = em.merge(associacaoNew);
            }
            for (Socio socioCollectionNewSocio : socioCollectionNew) {
                if (!socioCollectionOld.contains(socioCollectionNewSocio)) {
                    Candidato oldCandidatoOfSocioCollectionNewSocio = socioCollectionNewSocio.getCandidato();
                    socioCollectionNewSocio.setCandidato(candidato);
                    socioCollectionNewSocio = em.merge(socioCollectionNewSocio);
                    if (oldCandidatoOfSocioCollectionNewSocio != null && !oldCandidatoOfSocioCollectionNewSocio.equals(candidato)) {
                        oldCandidatoOfSocioCollectionNewSocio.getSocioCollection().remove(socioCollectionNewSocio);
                        oldCandidatoOfSocioCollectionNewSocio = em.merge(oldCandidatoOfSocioCollectionNewSocio);
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
                CandidatoPK id = candidato.getCandidatoPK();
                if (findCandidato(id) == null) {
                    throw new NonexistentEntityException("The candidato with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CandidatoPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Candidato candidato;
            try {
                candidato = em.getReference(Candidato.class, id);
                candidato.getCandidatoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The candidato with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Socio> socioCollectionOrphanCheck = candidato.getSocioCollection();
            for (Socio socioCollectionOrphanCheckSocio : socioCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Candidato (" + candidato + ") cannot be destroyed since the Socio " + socioCollectionOrphanCheckSocio + " in its socioCollection field has a non-nullable candidato field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Associacao associacao = candidato.getAssociacao();
            if (associacao != null) {
                associacao.getCandidatoCollection().remove(candidato);
                associacao = em.merge(associacao);
            }
            em.remove(candidato);
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

    public List<Candidato> findCandidatoEntities() {
        return findCandidatoEntities(true, -1, -1);
    }

    public List<Candidato> findCandidatoEntities(int maxResults, int firstResult) {
        return findCandidatoEntities(false, maxResults, firstResult);
    }

    private List<Candidato> findCandidatoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Candidato.class));
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

    public Candidato findCandidato(CandidatoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Candidato.class, id);
        } finally {
            em.close();
        }
    }

    public int getCandidatoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Candidato> rt = cq.from(Candidato.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
