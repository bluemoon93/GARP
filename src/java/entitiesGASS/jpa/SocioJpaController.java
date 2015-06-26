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
import entitiesGASS.Candidato;
import entitiesGASS.Estado;
import entitiesGASS.Tiposocio;
import entitiesGASS.Pagamentoquota;
import java.util.ArrayList;
import java.util.Collection;
import entitiesGASS.Ocupa;
import entitiesGASS.Socio;
import entitiesGASS.SocioPK;
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
public class SocioJpaController implements Serializable {

    public SocioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Socio socio) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (socio.getSocioPK() == null) {
            socio.setSocioPK(new SocioPK());
        }
        if (socio.getPagamentoquotaCollection() == null) {
            socio.setPagamentoquotaCollection(new ArrayList<Pagamentoquota>());
        }
        if (socio.getOcupaCollection() == null) {
            socio.setOcupaCollection(new ArrayList<Ocupa>());
        }
        socio.getSocioPK().setNIFAssociacao(socio.getTiposocio().getTiposocioPK().getNIFassociacao());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Candidato candidato = socio.getCandidato();
            if (candidato != null) {
                candidato = em.getReference(candidato.getClass(), candidato.getCandidatoPK());
                socio.setCandidato(candidato);
            }
            Estado estado = socio.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getEstadoPK());
                socio.setEstado(estado);
            }
            Tiposocio tiposocio = socio.getTiposocio();
            if (tiposocio != null) {
                tiposocio = em.getReference(tiposocio.getClass(), tiposocio.getTiposocioPK());
                socio.setTiposocio(tiposocio);
            }
            Collection<Pagamentoquota> attachedPagamentoquotaCollection = new ArrayList<Pagamentoquota>();
            for (Pagamentoquota pagamentoquotaCollectionPagamentoquotaToAttach : socio.getPagamentoquotaCollection()) {
                pagamentoquotaCollectionPagamentoquotaToAttach = em.getReference(pagamentoquotaCollectionPagamentoquotaToAttach.getClass(), pagamentoquotaCollectionPagamentoquotaToAttach.getPagamentoquotaPK());
                attachedPagamentoquotaCollection.add(pagamentoquotaCollectionPagamentoquotaToAttach);
            }
            socio.setPagamentoquotaCollection(attachedPagamentoquotaCollection);
            Collection<Ocupa> attachedOcupaCollection = new ArrayList<Ocupa>();
            for (Ocupa ocupaCollectionOcupaToAttach : socio.getOcupaCollection()) {
                ocupaCollectionOcupaToAttach = em.getReference(ocupaCollectionOcupaToAttach.getClass(), ocupaCollectionOcupaToAttach.getOcupaPK());
                attachedOcupaCollection.add(ocupaCollectionOcupaToAttach);
            }
            socio.setOcupaCollection(attachedOcupaCollection);
            em.persist(socio);
            if (candidato != null) {
                candidato.getSocioCollection().add(socio);
                candidato = em.merge(candidato);
            }
            if (estado != null) {
                estado.getSocioCollection().add(socio);
                estado = em.merge(estado);
            }
            if (tiposocio != null) {
                tiposocio.getSocioCollection().add(socio);
                tiposocio = em.merge(tiposocio);
            }
            for (Pagamentoquota pagamentoquotaCollectionPagamentoquota : socio.getPagamentoquotaCollection()) {
                Socio oldSocioOfPagamentoquotaCollectionPagamentoquota = pagamentoquotaCollectionPagamentoquota.getSocio();
                pagamentoquotaCollectionPagamentoquota.setSocio(socio);
                pagamentoquotaCollectionPagamentoquota = em.merge(pagamentoquotaCollectionPagamentoquota);
                if (oldSocioOfPagamentoquotaCollectionPagamentoquota != null) {
                    oldSocioOfPagamentoquotaCollectionPagamentoquota.getPagamentoquotaCollection().remove(pagamentoquotaCollectionPagamentoquota);
                    oldSocioOfPagamentoquotaCollectionPagamentoquota = em.merge(oldSocioOfPagamentoquotaCollectionPagamentoquota);
                }
            }
            for (Ocupa ocupaCollectionOcupa : socio.getOcupaCollection()) {
                Socio oldSocioOfOcupaCollectionOcupa = ocupaCollectionOcupa.getSocio();
                ocupaCollectionOcupa.setSocio(socio);
                ocupaCollectionOcupa = em.merge(ocupaCollectionOcupa);
                if (oldSocioOfOcupaCollectionOcupa != null) {
                    oldSocioOfOcupaCollectionOcupa.getOcupaCollection().remove(ocupaCollectionOcupa);
                    oldSocioOfOcupaCollectionOcupa = em.merge(oldSocioOfOcupaCollectionOcupa);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findSocio(socio.getSocioPK()) != null) {
                throw new PreexistingEntityException("Socio " + socio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Socio socio) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        socio.getSocioPK().setNIFAssociacao(socio.getTiposocio().getTiposocioPK().getNIFassociacao());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Socio persistentSocio = em.find(Socio.class, socio.getSocioPK());
            Candidato candidatoOld = persistentSocio.getCandidato();
            Candidato candidatoNew = socio.getCandidato();
            Estado estadoOld = persistentSocio.getEstado();
            Estado estadoNew = socio.getEstado();
            Tiposocio tiposocioOld = persistentSocio.getTiposocio();
            Tiposocio tiposocioNew = socio.getTiposocio();
            Collection<Pagamentoquota> pagamentoquotaCollectionOld = persistentSocio.getPagamentoquotaCollection();
            Collection<Pagamentoquota> pagamentoquotaCollectionNew = socio.getPagamentoquotaCollection();
            Collection<Ocupa> ocupaCollectionOld = persistentSocio.getOcupaCollection();
            Collection<Ocupa> ocupaCollectionNew = socio.getOcupaCollection();
            List<String> illegalOrphanMessages = null;
            for (Pagamentoquota pagamentoquotaCollectionOldPagamentoquota : pagamentoquotaCollectionOld) {
                if (!pagamentoquotaCollectionNew.contains(pagamentoquotaCollectionOldPagamentoquota)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pagamentoquota " + pagamentoquotaCollectionOldPagamentoquota + " since its socio field is not nullable.");
                }
            }
            for (Ocupa ocupaCollectionOldOcupa : ocupaCollectionOld) {
                if (!ocupaCollectionNew.contains(ocupaCollectionOldOcupa)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ocupa " + ocupaCollectionOldOcupa + " since its socio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (candidatoNew != null) {
                candidatoNew = em.getReference(candidatoNew.getClass(), candidatoNew.getCandidatoPK());
                socio.setCandidato(candidatoNew);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getEstadoPK());
                socio.setEstado(estadoNew);
            }
            if (tiposocioNew != null) {
                tiposocioNew = em.getReference(tiposocioNew.getClass(), tiposocioNew.getTiposocioPK());
                socio.setTiposocio(tiposocioNew);
            }
            Collection<Pagamentoquota> attachedPagamentoquotaCollectionNew = new ArrayList<Pagamentoquota>();
            for (Pagamentoquota pagamentoquotaCollectionNewPagamentoquotaToAttach : pagamentoquotaCollectionNew) {
                pagamentoquotaCollectionNewPagamentoquotaToAttach = em.getReference(pagamentoquotaCollectionNewPagamentoquotaToAttach.getClass(), pagamentoquotaCollectionNewPagamentoquotaToAttach.getPagamentoquotaPK());
                attachedPagamentoquotaCollectionNew.add(pagamentoquotaCollectionNewPagamentoquotaToAttach);
            }
            pagamentoquotaCollectionNew = attachedPagamentoquotaCollectionNew;
            socio.setPagamentoquotaCollection(pagamentoquotaCollectionNew);
            Collection<Ocupa> attachedOcupaCollectionNew = new ArrayList<Ocupa>();
            for (Ocupa ocupaCollectionNewOcupaToAttach : ocupaCollectionNew) {
                ocupaCollectionNewOcupaToAttach = em.getReference(ocupaCollectionNewOcupaToAttach.getClass(), ocupaCollectionNewOcupaToAttach.getOcupaPK());
                attachedOcupaCollectionNew.add(ocupaCollectionNewOcupaToAttach);
            }
            ocupaCollectionNew = attachedOcupaCollectionNew;
            socio.setOcupaCollection(ocupaCollectionNew);
            socio = em.merge(socio);
            if (candidatoOld != null && !candidatoOld.equals(candidatoNew)) {
                candidatoOld.getSocioCollection().remove(socio);
                candidatoOld = em.merge(candidatoOld);
            }
            if (candidatoNew != null && !candidatoNew.equals(candidatoOld)) {
                candidatoNew.getSocioCollection().add(socio);
                candidatoNew = em.merge(candidatoNew);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getSocioCollection().remove(socio);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getSocioCollection().add(socio);
                estadoNew = em.merge(estadoNew);
            }
            if (tiposocioOld != null && !tiposocioOld.equals(tiposocioNew)) {
                tiposocioOld.getSocioCollection().remove(socio);
                tiposocioOld = em.merge(tiposocioOld);
            }
            if (tiposocioNew != null && !tiposocioNew.equals(tiposocioOld)) {
                tiposocioNew.getSocioCollection().add(socio);
                tiposocioNew = em.merge(tiposocioNew);
            }
            for (Pagamentoquota pagamentoquotaCollectionNewPagamentoquota : pagamentoquotaCollectionNew) {
                if (!pagamentoquotaCollectionOld.contains(pagamentoquotaCollectionNewPagamentoquota)) {
                    Socio oldSocioOfPagamentoquotaCollectionNewPagamentoquota = pagamentoquotaCollectionNewPagamentoquota.getSocio();
                    pagamentoquotaCollectionNewPagamentoquota.setSocio(socio);
                    pagamentoquotaCollectionNewPagamentoquota = em.merge(pagamentoquotaCollectionNewPagamentoquota);
                    if (oldSocioOfPagamentoquotaCollectionNewPagamentoquota != null && !oldSocioOfPagamentoquotaCollectionNewPagamentoquota.equals(socio)) {
                        oldSocioOfPagamentoquotaCollectionNewPagamentoquota.getPagamentoquotaCollection().remove(pagamentoquotaCollectionNewPagamentoquota);
                        oldSocioOfPagamentoquotaCollectionNewPagamentoquota = em.merge(oldSocioOfPagamentoquotaCollectionNewPagamentoquota);
                    }
                }
            }
            for (Ocupa ocupaCollectionNewOcupa : ocupaCollectionNew) {
                if (!ocupaCollectionOld.contains(ocupaCollectionNewOcupa)) {
                    Socio oldSocioOfOcupaCollectionNewOcupa = ocupaCollectionNewOcupa.getSocio();
                    ocupaCollectionNewOcupa.setSocio(socio);
                    ocupaCollectionNewOcupa = em.merge(ocupaCollectionNewOcupa);
                    if (oldSocioOfOcupaCollectionNewOcupa != null && !oldSocioOfOcupaCollectionNewOcupa.equals(socio)) {
                        oldSocioOfOcupaCollectionNewOcupa.getOcupaCollection().remove(ocupaCollectionNewOcupa);
                        oldSocioOfOcupaCollectionNewOcupa = em.merge(oldSocioOfOcupaCollectionNewOcupa);
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
                SocioPK id = socio.getSocioPK();
                if (findSocio(id) == null) {
                    throw new NonexistentEntityException("The socio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(SocioPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Socio socio;
            try {
                socio = em.getReference(Socio.class, id);
                socio.getSocioPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The socio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Pagamentoquota> pagamentoquotaCollectionOrphanCheck = socio.getPagamentoquotaCollection();
            for (Pagamentoquota pagamentoquotaCollectionOrphanCheckPagamentoquota : pagamentoquotaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Socio (" + socio + ") cannot be destroyed since the Pagamentoquota " + pagamentoquotaCollectionOrphanCheckPagamentoquota + " in its pagamentoquotaCollection field has a non-nullable socio field.");
            }
            Collection<Ocupa> ocupaCollectionOrphanCheck = socio.getOcupaCollection();
            for (Ocupa ocupaCollectionOrphanCheckOcupa : ocupaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Socio (" + socio + ") cannot be destroyed since the Ocupa " + ocupaCollectionOrphanCheckOcupa + " in its ocupaCollection field has a non-nullable socio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Candidato candidato = socio.getCandidato();
            if (candidato != null) {
                candidato.getSocioCollection().remove(socio);
                candidato = em.merge(candidato);
            }
            Estado estado = socio.getEstado();
            if (estado != null) {
                estado.getSocioCollection().remove(socio);
                estado = em.merge(estado);
            }
            Tiposocio tiposocio = socio.getTiposocio();
            if (tiposocio != null) {
                tiposocio.getSocioCollection().remove(socio);
                tiposocio = em.merge(tiposocio);
            }
            em.remove(socio);
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

    public List<Socio> findSocioEntities() {
        return findSocioEntities(true, -1, -1);
    }

    public List<Socio> findSocioEntities(int maxResults, int firstResult) {
        return findSocioEntities(false, maxResults, firstResult);
    }

    private List<Socio> findSocioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Socio.class));
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

    public Socio findSocio(SocioPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Socio.class, id);
        } finally {
            em.close();
        }
    }

    public int getSocioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Socio> rt = cq.from(Socio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
