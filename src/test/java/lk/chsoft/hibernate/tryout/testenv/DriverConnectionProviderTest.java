package lk.chsoft.hibernate.tryout.testenv;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class DriverConnectionProviderTest {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());


    @Test
    public void test() {
        final SessionFactory sf = buildSessionFactory();

        // populate database with a container
        Session session = sf.openSession();
        Transaction txn = session.beginTransaction();

        SecurityId securityId = new SecurityId("Test");
        session.persist(securityId);

        Long id = securityId.getId();
        txn.commit(); /// This will release the lock sooner.

        Transaction txn1 = session.beginTransaction();

        SecurityId loadedId = session.load(SecurityId.class, id);
        LOG.info(">>>>>>>>>>>>>" + loadedId.getId());
        txn1.commit();
        session.close();
    }

    private SessionFactory buildSessionFactory() {
        Properties properties = getMsSqlDataSourceProperties();

        return new Configuration()
                .addProperties(properties)
                .addAnnotatedClass(SecurityId.class)
                .buildSessionFactory(
                        new StandardServiceRegistryBuilder()
                                .applySettings(properties)
                                .build()
                );
    }

    private Properties getHSqlDataSourceProperties() throws Exception{
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        properties.put("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
        properties.put("hibernate.connection.url", "jdbc:hsqldb:mem:test");
        properties.put("hibernate.connection.username", "sa");
        properties.put("hibernate.connection.password", "");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "true");

        return properties;

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
        return properties;
    }

}
