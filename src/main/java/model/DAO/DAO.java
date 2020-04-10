package model.DAO;

import model.exceptions.DAOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * DAO class.
 * @version 1.0
 */

public class DAO {
    private static String PERSISTENCE_UNIT_NAME = "Test_Local";
    private EntityManagerFactory factory;
    /**
     * Creates DAO class
     * */
    public DAO() throws DAOException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    /**
     * Returns JDBCConnector
     * */
    protected EntityManagerFactory getEntityManagerFactory() {
        return factory;
    }
}
