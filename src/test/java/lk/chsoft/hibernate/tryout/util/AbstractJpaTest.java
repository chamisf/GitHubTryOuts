package lk.chsoft.hibernate.tryout.util;

import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.Properties;

public abstract class AbstractJpaTest {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    protected static abstract class TransactionCallable<T> {
        public abstract T execute(EntityManager entityManager);
    }

    private EntityManagerFactory entityManagerFactory;

    @Before
    public void init() {
        entityManagerFactory = newEntityManagerFactory();
    }

    @After
    public void destroy() {
        entityManagerFactory.close();
    }

    protected abstract Class<?>[] entities();

    private EntityManagerFactory newEntityManagerFactory() {
        Properties properties = MySqlDataSourceManger.MANAGER.getProperties();
        properties.put(org.hibernate.jpa.AvailableSettings.LOADED_CLASSES, Arrays.asList(entities()));
        return Persistence.createEntityManagerFactory("testPersistenceUnit", properties);

    }

    protected <T> T doInTransaction(TransactionCallable<T> callable) {
        T result = null;
        EntityManager entityManager = null;
        EntityTransaction txn = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            txn = entityManager.getTransaction();
            txn.begin();
            result = callable.execute(entityManager);
            txn.commit();
        } catch (RuntimeException e) {
            if ( txn != null && txn.isActive() ) txn.rollback();
            throw e;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return result;
    }
}

