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
import entitiesGASS.Socio;
import entitiesGASS.Cargo;
import entitiesGASS.Ocupa;
import entitiesGASS.OcupaPK;
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
public class OcupaJpaController implements Serializable {

    public OcupaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ocupa ocupa) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (ocupa.getOcupaPK() == null) {
            ocupa.setOcupaPK(new OcupaPK());
        }
        ocupa.getOcupaPK().setNIFAssociacao(ocupa.getSocio().getSocioPK().getNIFAssociacao());
        ocupa.getOcupaPK().setNomeC(ocupa.getCargo().getCargoPK().getNome());
        ocupa.getOcupaPK().setGrupoC(ocupa.getCargo().getCargoPK().getGrupo());
        ocupa.getOcupaPK().setNIFsocio(ocupa.getSocio().getSocioPK().getNif());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Socio socio = ocupa.getSocio();
            if (socio != null) {
                socio = em.getReference(socio.getClass(), socio.getSocioPK());
                ocupa.setSocio(socio);
            }
            Cargo cargo = ocupa.getCargo();
            if (cargo != null) {
                cargo = em.getReference(cargo.getClass(), cargo.getCargoPK());
                ocupa.setCargo(cargo);
            }
            em.persist(ocupa);
            if (socio != null) {
                socio.getOcupaCollection().add(ocupa);
                socio = em.merge(socio);
            }
            if (cargo != null) {
                cargo.getOcupaCollection().add(ocupa);
                cargo = em.merge(cargo);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findOcupa(ocupa.getOcupaPK()) != null) {
                throw new PreexistingEntityException("Ocupa " + ocupa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ocupa ocupa) throws NonexistentEntityException, RollbackFailureException, Exception {
        ocupa.getOcupaPK().setNIFAssociacao(ocupa.getSocio().getSocioPK().getNIFAssociacao());
        ocupa.getOcupaPK().setNomeC(ocupa.getCargo().getCargoPK().getNome());
        ocupa.getOcupaPK().setGrupoC(ocupa.getCargo().getCargoPK().getGrupo());
        ocupa.getOcupaPK().setNIFsocio(ocupa.getSocio().getSocioPK().getNif());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Ocupa persistentOcupa = em.find(Ocupa.class, ocupa.getOcupaPK());
            Socio socioOld = persistentOcupa.getSocio();
            Socio socioNew = ocupa.getSocio();
            Cargo cargoOld = persistentOcupa.getCargo();
            Cargo cargoNew = ocupa.getCargo();
            if (socioNew != null) {
                socioNew = em.getReference(socioNew.getClass(), socioNew.getSocioPK());
                ocupa.setSocio(socioNew);
            }
            if (cargoNew != null) {
                cargoNew = em.getReference(cargoNew.getClass(), cargoNew.getCargoPK());
                ocupa.setCargo(cargoNew);
            }
            ocupa = em.merge(ocupa);
            if (socioOld != null && !socioOld.equals(socioNew)) {
                socioOld.getOcupaCollection().remove(ocupa);
                socioOld = em.merge(socioOld);
            }
            if (socioNew != null && !socioNew.equals(socioOld)) {
                socioNew.getOcupaCollection().add(ocupa);
                socioNew = em.merge(socioNew);
            }
            if (cargoOld != null && !cargoOld.equals(cargoNew)) {
                cargoOld.getOcupaCollection().remove(ocupa);
                cargoOld = em.merge(cargoOld);
            }
            if (cargoNew != null && !cargoNew.equals(cargoOld)) {
                cargoNew.getOcupaCollection().add(ocupa);
                cargoNew = em.merge(cargoNew);
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
                OcupaPK id = ocupa.getOcupaPK();
                if (findOcupa(id) == null) {
                    throw new NonexistentEntityException("The ocupa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(OcupaPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Ocupa ocupa;
            try {
                ocupa = em.getReference(Ocupa.class, id);
                ocupa.getOcupaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ocupa with id " + id + " no longer exists.", enfe);
            }
            Socio socio = ocupa.getSocio();
            if (socio != null) {
                socio.getOcupaCollection().remove(ocupa);
                socio = em.merge(socio);
            }
            Cargo cargo = ocupa.getCargo();
            if (cargo != null) {
                cargo.getOcupaCollection().remove(ocupa);
                cargo = em.merge(cargo);
            }
            em.remove(ocupa);
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

    public List<Ocupa> findOcupaEntities() {
        return findOcupaEntities(true, -1, -1);
    }

    public List<Ocupa> findOcupaEntities(int maxResults, int firstResult) {
        return findOcupaEntities(false, maxResults, firstResult);
    }

    private List<Ocupa> findOcupaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ocupa.class));
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

    public Ocupa findOcupa(OcupaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ocupa.class, id);
        } finally {
            em.close();
        }
    }

    public int getOcupaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ocupa> rt = cq.from(Ocupa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
