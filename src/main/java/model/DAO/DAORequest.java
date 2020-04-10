package model.DAO;

import model.entities.Request;
import model.entities.Request_;
import model.exceptions.DAOException;
import model.exceptions.JDBCConnectionException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
/**
 * DAORequest class.
 * @version 1.0
 */
public class DAORequest extends DAO {

    public DAORequest() throws DAOException {
        super();
    }

    /**
     * Returns all requests from database
     * */
    public List<Request> getALlRequests() throws DAOException {
        EntityManager entityManager = null;
        List<Request> requests = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Request> cq = cb.createQuery(Request.class);
            Root<Request> request = cq.from(Request.class);
            cq.select(request);
            TypedQuery<Request> q = entityManager.createQuery(cq);
            requests = q.getResultList();
        } catch (Exception e) {
            throw new DAOException("Failed to get all requests", e);
        } finally {
            if (entityManager != null && entityManager.isOpen())
                entityManager.close();
        }
        return requests;
    }



    /**
     * Returns overdue requests
     * */
    public List<Request> getOverdueRequests() throws DAOException {
        EntityManager entityManager = null;
        List requests = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Request> cq = cb.createQuery(Request.class);
            Root<Request> request = cq.from(Request.class);
            cq.select(request).where(cb.lessThan(request.get(Request_.TO_DATE), cb.currentDate())).
                    having(cb.equal(request.get(Request_.STATUS), 1));
            TypedQuery<Request> q = entityManager.createQuery(cq);
            requests = q.getResultList();
        } catch (Exception e) {
            throw new DAOException("Failed to get all overdue requests", e);
        } finally {
            if (entityManager != null && entityManager.isOpen())
                entityManager.close();
        }
        return requests;
    }


    /**
     * Returns Request by id
     * */
    public Request getRequestByID(int id) throws DAOException {
        EntityManager entityManager = null;
        Request req = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Request> cq = cb.createQuery(Request.class);
            Root<Request> request = cq.from(Request.class);
            cq.select(request).where(cb.equal(request.get(Request_.REQUEST_ID), id));
            TypedQuery<Request> q = entityManager.createQuery(cq);
            req = q.getSingleResult();
        } catch (Exception e) {
            throw new DAOException("Failed while getting request", e);
        } finally {
            if (entityManager != null && entityManager.isOpen())
                entityManager.close();
        }
        return req;
    }

    /**
     * Inserts request to database
     * */
    public void insertRequest(Request request) throws DAOException {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            transaction = entityManager.getTransaction();

            transaction.begin();
            entityManager.persist(request);
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
     * Cancels request by id.
     * */
    @Transactional
    public void updateRequestStatus(int id, int status) throws DAOException {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();

            CriteriaUpdate<Request> update = cb.
                    createCriteriaUpdate(Request.class);
            Root e = update.from(Request.class);
            update.set(Request_.STATUS, status).where(cb.equal(e.get(Request_.REQUEST_ID), id));
            entityManager.getTransaction().begin();
            entityManager.createQuery(update).executeUpdate();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive())
                transaction.rollback();
            throw new DAOException("Failed while updating request status", e);
        } finally {
            if (entityManager != null && entityManager.isOpen())
                entityManager.close();
        }
    }

    /**
     * Deletes request from database.
     * */
    public void deleteRequest(int id) throws DAOException {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();

            CriteriaDelete<Request> delete = cb.
                    createCriteriaDelete(Request.class);
            Root e = delete.from(Request.class);
            delete.where(cb.equal(e.get(Request_.REQUEST_ID), id));
            entityManager.getTransaction().begin();
            entityManager.createQuery(delete).executeUpdate();
            entityManager.getTransaction().begin();
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
