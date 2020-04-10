package model.DAO;

import model.entities.Worker;
import model.entities.Worker_;
import model.exceptions.DAOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
/**
 * DAOWorker class.
 * @version 1.0
 */
public class DAOWorker extends DAO {

    public DAOWorker() throws DAOException {
        super();
    }
    /**
     * Returns all workers from database
     * */
    public List<Worker> getAllWorkers() throws DAOException {
        EntityManager entityManager = null;
        List workers = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Worker> cq = cb.createQuery(Worker.class);
            Root<Worker> request = cq.from(Worker.class);
            cq.select(request);
            TypedQuery<Worker> q = entityManager.createQuery(cq);
            workers = q.getResultList();
        } catch (Exception e) {
            throw new DAOException("Failed to get all workers", e);
        } finally {
            if (entityManager != null && entityManager.isOpen())
                entityManager.close();
        }
        return workers;
    }

    /**
     * Returns Worker by id
     * */
    public Worker getWorkerByID(int id) throws DAOException {
        EntityManager entityManager = null;
        Worker worker = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Worker> cq = cb.createQuery(Worker.class);
            Root<Worker> request = cq.from(Worker.class);
            cq.select(request).where(cb.equal(request.get(Worker_.WORKER_ID), id));
            TypedQuery<Worker> q = entityManager.createQuery(cq);
            worker = q.getSingleResult();
        } catch (Exception e) {
            throw new DAOException("Failed while getting worker", e);
        } finally {
            if (entityManager != null && entityManager.isOpen())
                entityManager.close();
        }
        return worker;
    }

    public List getWorkerByProfile(String profile) throws DAOException {
        EntityManager entityManager = null;
        List workers = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Worker> cq = cb.createQuery(Worker.class);
            Root<Worker> request = cq.from(Worker.class);
            cq.select(request).where(cb.equal(request.get(Worker_.WORKER_PROFILE), profile));
            TypedQuery<Worker> q = entityManager.createQuery(cq);
            workers = q.getResultList();
        } catch (Exception e) {
            throw new DAOException("Failed while getting worker", e);
        } finally {
            if (entityManager != null && entityManager.isOpen())
                entityManager.close();
        }
        return workers;
    }


    /**
     * Inserts worker to database
     * */
    public void insertWorker(Worker worker) throws DAOException {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            transaction = entityManager.getTransaction();

            transaction.begin();
            entityManager.persist(worker);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive())
                transaction.rollback();
            throw new DAOException("Failed while inserting", e);
        } finally {
            if (entityManager != null && entityManager.isOpen())
                entityManager.close();
        }
    }

    /**
     * Deletes worker from database.
     * */
    public void deleteWorker(int id) throws DAOException {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();

            CriteriaDelete<Worker> delete = cb.
                    createCriteriaDelete(Worker.class);
            Root e = delete.from(Worker.class);
            delete.where(cb.equal(e.get(Worker_.WORKER_ID), id));
            entityManager.getTransaction().begin();
            entityManager.createQuery(delete).executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive())
                transaction.rollback();
            throw new DAOException("Failed while deleting", e);
        } finally {
            if (entityManager != null && entityManager.isOpen())
                entityManager.close();
        }
    }
}
