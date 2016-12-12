package lk.chsoft.hibernate.tryout.util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public abstract class AbstractHibernateTest {

    protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    protected static abstract class TransactionCallable<T> {
        public abstract T execute(Session session);
    }

    private SessionFactory sf;

    @Before
    public void init() {
        sf = newSessionFactory();
    }

    @After
    public void destroy() {
        sf.close();
    }

    protected abstract Class<?>[] entities();

    protected Properties getProperties() {
        return MySqlDataSourceManger.MANAGER.getProperties();
    }

    private SessionFactory newSessionFactory() {

        Properties properties = getProperties();
        Configuration configuration = new Configuration().addProperties(properties);
        for (Class<?> entityClass : entities()) {
            configuration.addAnnotatedClass(entityClass);
        }
        return configuration.buildSessionFactory(
                new StandardServiceRegistryBuilder()
                        .applySettings(properties)
                        .build()
        );
    }

    protected <T> T doInTransaction(TransactionCallable<T> callable) {
        T result = null;
        Session session = null;
        Transaction txn = null;
        try {
            session = sf.openSession();
            txn = session.beginTransaction();

            result = callable.execute(session);
            txn.commit();
        } catch (RuntimeException e) {
            if (txn != null && txn.isActive()) txn.rollback();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }
}
