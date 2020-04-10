package model.DAO;


import model.entities.User;
import model.exceptions.DAOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDAO extends DAO {

    public UserDAO() throws DAOException {
        super();
    }

    public User getUser(String login, String password) throws DAOException {
        EntityManager entityManager = null;
        User user = null;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> userr = cq.from(User.class);
            cq.select(userr).where(cb.equal(userr.get("username"), login), cb.equal(userr.get("password"), password));
            TypedQuery<User> q = entityManager.createQuery(cq);
            List res = q.getResultList();
            if (!res.isEmpty()) {
                user = (User)res.get(0);
            }
        } catch (Exception e) {
            throw new DAOException("Failed to get user", e);
        } finally {
            if (entityManager != null && entityManager.isOpen())
                entityManager.close();
        }
        return user;
    }

    public User getUser(String login) throws DAOException {
        EntityManager entityManager = null;
        User user = null;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> userr = cq.from(User.class);
            cq.select(userr).where(cb.equal(userr.get("username"), login));
            TypedQuery<User> q = entityManager.createQuery(cq);
            List res = q.getResultList();
            if (!res.isEmpty()) {
                user = (User)res.get(0);
            }
        } catch (Exception e) {
            throw new DAOException("Failed to get user", e);
        } finally {
            if (entityManager != null && entityManager.isOpen())
                entityManager.close();
        }
        return user;
    }


    public void insertUser(User user) throws DAOException {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            transaction = entityManager.getTransaction();

            transaction.begin();
            entityManager.persist(user);
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
}
