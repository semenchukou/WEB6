package model.DAO;

import model.entities.WorkPlan;
import model.entities.WorkPlan_;
import model.entities.Worker;
import model.exceptions.DAOException;
import model.exceptions.JDBCConnectionException;
import org.hibernate.jdbc.Work;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * DAOWorkPlan class.
 * @version 1.0
 */
public class DAOWorkPlan extends DAO {

    public DAOWorkPlan() throws DAOException {
        super();
    }
    /**
     * Returns all work plans from database
     * */
    public List<WorkPlan> getAllPlans() throws DAOException {
        EntityManager entityManager = null;
        List workPlans = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<WorkPlan> cq = cb.createQuery(WorkPlan.class);
            Root<WorkPlan> request = cq.from(WorkPlan.class);
            cq.select(request);
            TypedQuery<WorkPlan> q = entityManager.createQuery(cq);
            workPlans = q.getResultList();
        } catch (Exception e) {
            throw new DAOException("Failed to get all work plans", e);
        } finally {
            if (entityManager != null && entityManager.isOpen())
                entityManager.close();
        }
        return workPlans;
    }

    /**
     * Returns Work Plan by id
     * */
    public WorkPlan getPlanByID(int id) throws DAOException {
        EntityManager entityManager = null;
        WorkPlan workPlan = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<WorkPlan> cq = cb.createQuery(WorkPlan.class);
            Root<WorkPlan> request = cq.from(WorkPlan.class);
            cq.select(request).where(cb.equal(request.get(WorkPlan_.PLAN_ID), id));
            TypedQuery<WorkPlan> q = entityManager.createQuery(cq);
            workPlan = q.getSingleResult();
        } catch (Exception e) {
            throw new DAOException("Failed while getting work plan", e);
        } finally {
            if (entityManager != null && entityManager.isOpen())
                entityManager.close();
        }
        return workPlan;
    }

    /**
     * Inserts work plan to database
     * */
    public void insertPlan(WorkPlan plan) throws DAOException {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            transaction = entityManager.getTransaction();

            transaction.begin();
            entityManager.persist(plan);
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
     * Changes work plan is_completed status.
     * */
    public void changeWorkPlanStatus(int id, int status) throws DAOException {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();

            CriteriaUpdate<WorkPlan> update = cb.
                    createCriteriaUpdate(WorkPlan.class);
            Root e = update.from(WorkPlan.class);
            update.set(WorkPlan_.IS_COMPLETED, status).where(cb.equal(e.get(WorkPlan_.PLAN_ID), id));
            entityManager.getTransaction().begin();
            entityManager.createQuery(update).executeUpdate();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive())
                transaction.rollback();
            throw new DAOException("Failed while changing work plan status", e);
        } finally {
            if (entityManager != null && entityManager.isOpen())
                entityManager.close();
        }
    }


    /**
     * Deletes work plan from database.
     * */
    public void deletePlan(int id) throws DAOException {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();

            CriteriaDelete<WorkPlan> delete = cb.
                    createCriteriaDelete(WorkPlan.class);
            Root e = delete.from(WorkPlan.class);
            delete.where(cb.equal(e.get(WorkPlan_.PLAN_ID), id));
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
