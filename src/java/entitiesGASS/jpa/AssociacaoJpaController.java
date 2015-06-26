/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jpa;

import entitiesGASS.Associacao;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entitiesGASS.Tiposocio;
import java.util.ArrayList;
import java.util.Collection;
import entitiesGASS.Candidato;
import entitiesGASS.Evento;
import entitiesGASS.Estado;
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
public class AssociacaoJpaController implements Serializable {

    public AssociacaoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Associacao associacao) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (associacao.getTiposocioCollection() == null) {
            associacao.setTiposocioCollection(new ArrayList<Tiposocio>());
        }
        if (associacao.getCandidatoCollection() == null) {
            associacao.setCandidatoCollection(new ArrayList<Candidato>());
        }
        if (associacao.getEventoCollection() == null) {
            associacao.setEventoCollection(new ArrayList<Evento>());
        }
        if (associacao.getEstadoCollection() == null) {
            associacao.setEstadoCollection(new ArrayList<Estado>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Tiposocio> attachedTiposocioCollection = new ArrayList<Tiposocio>();
            for (Tiposocio tiposocioCollectionTiposocioToAttach : associacao.getTiposocioCollection()) {
                tiposocioCollectionTiposocioToAttach = em.getReference(tiposocioCollectionTiposocioToAttach.getClass(), tiposocioCollectionTiposocioToAttach.getTiposocioPK());
                attachedTiposocioCollection.add(tiposocioCollectionTiposocioToAttach);
            }
            associacao.setTiposocioCollection(attachedTiposocioCollection);
            Collection<Candidato> attachedCandidatoCollection = new ArrayList<Candidato>();
            for (Candidato candidatoCollectionCandidatoToAttach : associacao.getCandidatoCollection()) {
                candidatoCollectionCandidatoToAttach = em.getReference(candidatoCollectionCandidatoToAttach.getClass(), candidatoCollectionCandidatoToAttach.getCandidatoPK());
                attachedCandidatoCollection.add(candidatoCollectionCandidatoToAttach);
            }
            associacao.setCandidatoCollection(attachedCandidatoCollection);
            Collection<Evento> attachedEventoCollection = new ArrayList<Evento>();
            for (Evento eventoCollectionEventoToAttach : associacao.getEventoCollection()) {
                eventoCollectionEventoToAttach = em.getReference(eventoCollectionEventoToAttach.getClass(), eventoCollectionEventoToAttach.getEventoPK());
                attachedEventoCollection.add(eventoCollectionEventoToAttach);
            }
            associacao.setEventoCollection(attachedEventoCollection);
            Collection<Estado> attachedEstadoCollection = new ArrayList<Estado>();
            for (Estado estadoCollectionEstadoToAttach : associacao.getEstadoCollection()) {
                estadoCollectionEstadoToAttach = em.getReference(estadoCollectionEstadoToAttach.getClass(), estadoCollectionEstadoToAttach.getEstadoPK());
                attachedEstadoCollection.add(estadoCollectionEstadoToAttach);
            }
            associacao.setEstadoCollection(attachedEstadoCollection);
            em.persist(associacao);
            for (Tiposocio tiposocioCollectionTiposocio : associacao.getTiposocioCollection()) {
                Associacao oldAssociacaoOfTiposocioCollectionTiposocio = tiposocioCollectionTiposocio.getAssociacao();
                tiposocioCollectionTiposocio.setAssociacao(associacao);
                tiposocioCollectionTiposocio = em.merge(tiposocioCollectionTiposocio);
                if (oldAssociacaoOfTiposocioCollectionTiposocio != null) {
                    oldAssociacaoOfTiposocioCollectionTiposocio.getTiposocioCollection().remove(tiposocioCollectionTiposocio);
                    oldAssociacaoOfTiposocioCollectionTiposocio = em.merge(oldAssociacaoOfTiposocioCollectionTiposocio);
                }
            }
            for (Candidato candidatoCollectionCandidato : associacao.getCandidatoCollection()) {
                Associacao oldAssociacaoOfCandidatoCollectionCandidato = candidatoCollectionCandidato.getAssociacao();
                candidatoCollectionCandidato.setAssociacao(associacao);
                candidatoCollectionCandidato = em.merge(candidatoCollectionCandidato);
                if (oldAssociacaoOfCandidatoCollectionCandidato != null) {
                    oldAssociacaoOfCandidatoCollectionCandidato.getCandidatoCollection().remove(candidatoCollectionCandidato);
                    oldAssociacaoOfCandidatoCollectionCandidato = em.merge(oldAssociacaoOfCandidatoCollectionCandidato);
                }
            }
            for (Evento eventoCollectionEvento : associacao.getEventoCollection()) {
                Associacao oldAssociacaoOfEventoCollectionEvento = eventoCollectionEvento.getAssociacao();
                eventoCollectionEvento.setAssociacao(associacao);
                eventoCollectionEvento = em.merge(eventoCollectionEvento);
                if (oldAssociacaoOfEventoCollectionEvento != null) {
                    oldAssociacaoOfEventoCollectionEvento.getEventoCollection().remove(eventoCollectionEvento);
                    oldAssociacaoOfEventoCollectionEvento = em.merge(oldAssociacaoOfEventoCollectionEvento);
                }
            }
            for (Estado estadoCollectionEstado : associacao.getEstadoCollection()) {
                Associacao oldAssociacaoOfEstadoCollectionEstado = estadoCollectionEstado.getAssociacao();
                estadoCollectionEstado.setAssociacao(associacao);
                estadoCollectionEstado = em.merge(estadoCollectionEstado);
                if (oldAssociacaoOfEstadoCollectionEstado != null) {
                    oldAssociacaoOfEstadoCollectionEstado.getEstadoCollection().remove(estadoCollectionEstado);
                    oldAssociacaoOfEstadoCollectionEstado = em.merge(oldAssociacaoOfEstadoCollectionEstado);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findAssociacao(associacao.getNif()) != null) {
                throw new PreexistingEntityException("Associacao " + associacao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Associacao associacao) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Associacao persistentAssociacao = em.find(Associacao.class, associacao.getNif());
            Collection<Tiposocio> tiposocioCollectionOld = persistentAssociacao.getTiposocioCollection();
            Collection<Tiposocio> tiposocioCollectionNew = associacao.getTiposocioCollection();
            Collection<Candidato> candidatoCollectionOld = persistentAssociacao.getCandidatoCollection();
            Collection<Candidato> candidatoCollectionNew = associacao.getCandidatoCollection();
            Collection<Evento> eventoCollectionOld = persistentAssociacao.getEventoCollection();
            Collection<Evento> eventoCollectionNew = associacao.getEventoCollection();
            Collection<Estado> estadoCollectionOld = persistentAssociacao.getEstadoCollection();
            Collection<Estado> estadoCollectionNew = associacao.getEstadoCollection();
            List<String> illegalOrphanMessages = null;
            for (Tiposocio tiposocioCollectionOldTiposocio : tiposocioCollectionOld) {
                if (!tiposocioCollectionNew.contains(tiposocioCollectionOldTiposocio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tiposocio " + tiposocioCollectionOldTiposocio + " since its associacao field is not nullable.");
                }
            }
            for (Candidato candidatoCollectionOldCandidato : candidatoCollectionOld) {
                if (!candidatoCollectionNew.contains(candidatoCollectionOldCandidato)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Candidato " + candidatoCollectionOldCandidato + " since its associacao field is not nullable.");
                }
            }
            for (Evento eventoCollectionOldEvento : eventoCollectionOld) {
                if (!eventoCollectionNew.contains(eventoCollectionOldEvento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Evento " + eventoCollectionOldEvento + " since its associacao field is not nullable.");
                }
            }
            for (Estado estadoCollectionOldEstado : estadoCollectionOld) {
                if (!estadoCollectionNew.contains(estadoCollectionOldEstado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estado " + estadoCollectionOldEstado + " since its associacao field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Tiposocio> attachedTiposocioCollectionNew = new ArrayList<Tiposocio>();
            for (Tiposocio tiposocioCollectionNewTiposocioToAttach : tiposocioCollectionNew) {
                tiposocioCollectionNewTiposocioToAttach = em.getReference(tiposocioCollectionNewTiposocioToAttach.getClass(), tiposocioCollectionNewTiposocioToAttach.getTiposocioPK());
                attachedTiposocioCollectionNew.add(tiposocioCollectionNewTiposocioToAttach);
            }
            tiposocioCollectionNew = attachedTiposocioCollectionNew;
            associacao.setTiposocioCollection(tiposocioCollectionNew);
            Collection<Candidato> attachedCandidatoCollectionNew = new ArrayList<Candidato>();
            for (Candidato candidatoCollectionNewCandidatoToAttach : candidatoCollectionNew) {
                candidatoCollectionNewCandidatoToAttach = em.getReference(candidatoCollectionNewCandidatoToAttach.getClass(), candidatoCollectionNewCandidatoToAttach.getCandidatoPK());
                attachedCandidatoCollectionNew.add(candidatoCollectionNewCandidatoToAttach);
            }
            candidatoCollectionNew = attachedCandidatoCollectionNew;
            associacao.setCandidatoCollection(candidatoCollectionNew);
            Collection<Evento> attachedEventoCollectionNew = new ArrayList<Evento>();
            for (Evento eventoCollectionNewEventoToAttach : eventoCollectionNew) {
                eventoCollectionNewEventoToAttach = em.getReference(eventoCollectionNewEventoToAttach.getClass(), eventoCollectionNewEventoToAttach.getEventoPK());
                attachedEventoCollectionNew.add(eventoCollectionNewEventoToAttach);
            }
            eventoCollectionNew = attachedEventoCollectionNew;
            associacao.setEventoCollection(eventoCollectionNew);
            Collection<Estado> attachedEstadoCollectionNew = new ArrayList<Estado>();
            for (Estado estadoCollectionNewEstadoToAttach : estadoCollectionNew) {
                estadoCollectionNewEstadoToAttach = em.getReference(estadoCollectionNewEstadoToAttach.getClass(), estadoCollectionNewEstadoToAttach.getEstadoPK());
                attachedEstadoCollectionNew.add(estadoCollectionNewEstadoToAttach);
            }
            estadoCollectionNew = attachedEstadoCollectionNew;
            associacao.setEstadoCollection(estadoCollectionNew);
            associacao = em.merge(associacao);
            for (Tiposocio tiposocioCollectionNewTiposocio : tiposocioCollectionNew) {
                if (!tiposocioCollectionOld.contains(tiposocioCollectionNewTiposocio)) {
                    Associacao oldAssociacaoOfTiposocioCollectionNewTiposocio = tiposocioCollectionNewTiposocio.getAssociacao();
                    tiposocioCollectionNewTiposocio.setAssociacao(associacao);
                    tiposocioCollectionNewTiposocio = em.merge(tiposocioCollectionNewTiposocio);
                    if (oldAssociacaoOfTiposocioCollectionNewTiposocio != null && !oldAssociacaoOfTiposocioCollectionNewTiposocio.equals(associacao)) {
                        oldAssociacaoOfTiposocioCollectionNewTiposocio.getTiposocioCollection().remove(tiposocioCollectionNewTiposocio);
                        oldAssociacaoOfTiposocioCollectionNewTiposocio = em.merge(oldAssociacaoOfTiposocioCollectionNewTiposocio);
                    }
                }
            }
            for (Candidato candidatoCollectionNewCandidato : candidatoCollectionNew) {
                if (!candidatoCollectionOld.contains(candidatoCollectionNewCandidato)) {
                    Associacao oldAssociacaoOfCandidatoCollectionNewCandidato = candidatoCollectionNewCandidato.getAssociacao();
                    candidatoCollectionNewCandidato.setAssociacao(associacao);
                    candidatoCollectionNewCandidato = em.merge(candidatoCollectionNewCandidato);
                    if (oldAssociacaoOfCandidatoCollectionNewCandidato != null && !oldAssociacaoOfCandidatoCollectionNewCandidato.equals(associacao)) {
                        oldAssociacaoOfCandidatoCollectionNewCandidato.getCandidatoCollection().remove(candidatoCollectionNewCandidato);
                        oldAssociacaoOfCandidatoCollectionNewCandidato = em.merge(oldAssociacaoOfCandidatoCollectionNewCandidato);
                    }
                }
            }
            for (Evento eventoCollectionNewEvento : eventoCollectionNew) {
                if (!eventoCollectionOld.contains(eventoCollectionNewEvento)) {
                    Associacao oldAssociacaoOfEventoCollectionNewEvento = eventoCollectionNewEvento.getAssociacao();
                    eventoCollectionNewEvento.setAssociacao(associacao);
                    eventoCollectionNewEvento = em.merge(eventoCollectionNewEvento);
                    if (oldAssociacaoOfEventoCollectionNewEvento != null && !oldAssociacaoOfEventoCollectionNewEvento.equals(associacao)) {
                        oldAssociacaoOfEventoCollectionNewEvento.getEventoCollection().remove(eventoCollectionNewEvento);
                        oldAssociacaoOfEventoCollectionNewEvento = em.merge(oldAssociacaoOfEventoCollectionNewEvento);
                    }
                }
            }
            for (Estado estadoCollectionNewEstado : estadoCollectionNew) {
                if (!estadoCollectionOld.contains(estadoCollectionNewEstado)) {
                    Associacao oldAssociacaoOfEstadoCollectionNewEstado = estadoCollectionNewEstado.getAssociacao();
                    estadoCollectionNewEstado.setAssociacao(associacao);
                    estadoCollectionNewEstado = em.merge(estadoCollectionNewEstado);
                    if (oldAssociacaoOfEstadoCollectionNewEstado != null && !oldAssociacaoOfEstadoCollectionNewEstado.equals(associacao)) {
                        oldAssociacaoOfEstadoCollectionNewEstado.getEstadoCollection().remove(estadoCollectionNewEstado);
                        oldAssociacaoOfEstadoCollectionNewEstado = em.merge(oldAssociacaoOfEstadoCollectionNewEstado);
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
                Integer id = associacao.getNif();
                if (findAssociacao(id) == null) {
                    throw new NonexistentEntityException("The associacao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Associacao associacao;
            try {
                associacao = em.getReference(Associacao.class, id);
                associacao.getNif();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The associacao with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tiposocio> tiposocioCollectionOrphanCheck = associacao.getTiposocioCollection();
            for (Tiposocio tiposocioCollectionOrphanCheckTiposocio : tiposocioCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Associacao (" + associacao + ") cannot be destroyed since the Tiposocio " + tiposocioCollectionOrphanCheckTiposocio + " in its tiposocioCollection field has a non-nullable associacao field.");
            }
            Collection<Candidato> candidatoCollectionOrphanCheck = associacao.getCandidatoCollection();
            for (Candidato candidatoCollectionOrphanCheckCandidato : candidatoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Associacao (" + associacao + ") cannot be destroyed since the Candidato " + candidatoCollectionOrphanCheckCandidato + " in its candidatoCollection field has a non-nullable associacao field.");
            }
            Collection<Evento> eventoCollectionOrphanCheck = associacao.getEventoCollection();
            for (Evento eventoCollectionOrphanCheckEvento : eventoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Associacao (" + associacao + ") cannot be destroyed since the Evento " + eventoCollectionOrphanCheckEvento + " in its eventoCollection field has a non-nullable associacao field.");
            }
            Collection<Estado> estadoCollectionOrphanCheck = associacao.getEstadoCollection();
            for (Estado estadoCollectionOrphanCheckEstado : estadoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Associacao (" + associacao + ") cannot be destroyed since the Estado " + estadoCollectionOrphanCheckEstado + " in its estadoCollection field has a non-nullable associacao field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(associacao);
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

    public List<Associacao> findAssociacaoEntities() {
        return findAssociacaoEntities(true, -1, -1);
    }

    public List<Associacao> findAssociacaoEntities(int maxResults, int firstResult) {
        return findAssociacaoEntities(false, maxResults, firstResult);
    }

    private List<Associacao> findAssociacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Associacao.class));
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

    public Associacao findAssociacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Associacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getAssociacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Associacao> rt = cq.from(Associacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
