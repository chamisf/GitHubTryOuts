package lk.chsoft.hibernate.tryout.isolations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

/**
 * Refer http://gavindraper.com/2012/02/18/sql-server-isolation-levels-by-example/ ,
 * http://stackoverflow.com/questions/16162357/transaction-isolation-levels-relation-with-locks-on-table
 * http://www.oracle.com/technetwork/testcontent/o65asktom-082389.html for more informations.
 */
public class TestTransactionIsolation {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    /**
     * With READ UNCOMMITTED isolation level {@link #testRead()} will return uncommited data changes (dirty read) but
     * READ COMMITTED isolation level it will wait fot this this method to be comepleted.
     *
     * @throws Exception
     */
    @Test
    public void testReadUncommited() throws Exception {
        final SessionFactory sf = buildSessionFactory();

        // populate database with a container
        Session session = sf.openSession();
        Transaction txn = session.beginTransaction();

        session.createQuery("update IsolationTests set col1=:col1").setParameter("col1", -1).executeUpdate();
        session.createNativeQuery("WAITFOR DELAY '00:00:15'").executeUpdate();

        txn.rollback();
        session.close();
    }

    @Test
    public void testRead() {
        final SessionFactory sf = buildSessionFactory();

        // populate database with a container
        Session session = sf.openSession();
        Transaction txn = session.beginTransaction();

        List<IsolationTests> isolationTestsList = session.createQuery("select it from IsolationTests it", IsolationTests.class)
                .list();
        for (IsolationTests isolationTests : isolationTestsList) {
            LOG.info(isolationTests.toString());
        }
        txn.commit();
        session.close();
    }


    /**
     * Non repeatable read issue is available with READ COMMITTED isolation type but might not visible due to
     * hibernate session cache (first level cache.) Unlike second level cache (session factory cache) this cannot
     * disable. So had to use {@link SessionFactory#openStatelessSession()} to test this scenario. This issue
     * can be fixed with REPEATABLE READ isolation level. Run with {@link #testUpdate()} simultaniously.
     * <p>
     * Phantom read can only be avoid with SERIALIZABLE or SNAPSHOT isolation levels. Run with {@link #testInsertNew()}
     * simultaniously. Need to run ALTER DATABASE IsolationTests  SET ALLOW_SNAPSHOT_ISOLATION ON to use SNAPSHOT level
     * isolation. SNAPSHOT level does not block the table like SERIALIZABLE.
     *
     * @throws Exception
     */
    @Test
    public void testRepeatableReadAndPhatomRead() throws Exception {
        final SessionFactory sf = buildSessionFactory();

        // populate database with a container
        StatelessSession session = sf.openStatelessSession();
        Transaction txn = session.beginTransaction();

        List<IsolationTests> isolationTestsList = session.createQuery("select it from IsolationTests it", IsolationTests.class)
                .list();
        for (IsolationTests isolationTests : isolationTestsList) {
            LOG.info(isolationTests.toString());
        }
        session.createNativeQuery("WAITFOR DELAY '00:00:15'").executeUpdate();
        List<IsolationTests> isolationTestsListSecond = session.createQuery("select it from IsolationTests it", IsolationTests.class)
                .list();
        for (IsolationTests isolationTests1 : isolationTestsListSecond) {
            LOG.info(isolationTests1.toString());
        }
        txn.commit();
        session.close();
    }

    @Test
    public void testUpdate() throws Exception {
        final SessionFactory sf = buildSessionFactory();

        // populate database with a container
        Session session = sf.openSession();
        Transaction txn = session.beginTransaction();

        session.createQuery("update IsolationTests set col1=:col1").setParameter("col1", 1).executeUpdate();

        txn.commit();
        session.close();
    }

    @Test
    public void testInsertNew() throws Exception {
        final SessionFactory sf = buildSessionFactory();

        // populate database with a container
        Session session = sf.openSession();
        Transaction txn = session.beginTransaction();

        session.persist(new IsolationTests().setCol1(100).setCol2(100).setCol3(100));

        txn.commit();
        session.close();
    }

    private SessionFactory buildSessionFactory() {
        Properties properties = getMsSqlDataSourceProperties();

        return new Configuration()
                .addProperties(properties)
                .addAnnotatedClass(IsolationTests.class)
                .buildSessionFactory(
                        new StandardServiceRegistryBuilder()
                                .applySettings(properties)
                                .build()
                );
    }

    private Properties getMsSqlDataSourceProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        properties.put("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put("hibernate.connection.url", "jdbc:sqlserver://localhost:1433;databaseName=test");
        properties.put("hibernate.connection.username", "sa");
        properties.put("hibernate.connection.password", "csfcse86");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.generate_statistics", true);
        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.connection.isolation", "1"); // READ UNCOMMITTED
//        properties.put("hibernate.connection.isolation", "2"); // READ COMMITTED
//        properties.put("hibernate.connection.isolation", "4"); // REPEATABLE READ
//        properties.put("hibernate.connection.isolation", "8"); // SERIALIZABLE
        properties.put("hibernate.connection.isolation", "4096"); // SNAPSHOT

        return properties;
    }
}
