package model.DAO;

import model.entities.Request;
import model.entities.Tenant;
import model.entities.Tenant_;
import model.exceptions.DAOException;
import model.exceptions.JDBCConnectionException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * DAOTenant class.
 * @version 1.0
 */
public class DAOTenant extends DAO {

    public DAOTenant() throws DAOException {
        super();
    }
    /**
     * Returns all tenants from database
     * */
    public List<Tenant> getAllTenants() throws DAOException {
        EntityManager entityManager = null;
        List tenants = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Tenant> cq = cb.createQuery(Tenant.class);
            Root<Tenant> request = cq.from(Tenant.class);
            cq.select(request);
            TypedQuery<Tenant> q = entityManager.createQuery(cq);
            tenants = q.getResultList();
        } catch (Exception e) {
            throw new DAOException("Failed to get all tenants", e);
        } finally {
            if (entityManager != null && entityManager.isOpen())
                entityManager.close();
        }
        return tenants;
    }

    /**
     * Returns Tenant by id
     * */
    public Tenant getTenantByID(int id) throws DAOException {
        EntityManager entityManager = null;
        Tenant tenant = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Tenant> cq = cb.createQuery(Tenant.class);
            Root<Tenant> request = cq.from(Tenant.class);
            cq.select(request).where(cb.equal(request.get(Tenant_.TENANT_ID), id));
            TypedQuery<Tenant> q = entityManager.createQuery(cq);
            tenant = q.getSingleResult();
        } catch (Exception e) {
            throw new DAOException("Failed while getting tenant", e);
        } finally {
            if (entityManager != null && entityManager.isOpen())
                entityManager.close();
        }
        return tenant;
    }

    /**
     * Inserts tenant to database
     * */
    public void insertTenant(Tenant tenant) throws DAOException {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            transaction = entityManager.getTransaction();

            transaction.begin();
            entityManager.persist(tenant);
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
     * Deletes tenant from database.
     * */
    public void deleteTenant(int id) throws DAOException {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();

            CriteriaDelete<Tenant> delete = cb.
                    createCriteriaDelete(Tenant.class);
            Root e = delete.from(Tenant.class);
            delete.where(cb.equal(e.get(Tenant_.TENANT_ID), id));
            entityManager.createQuery(delete).executeUpdate();
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
