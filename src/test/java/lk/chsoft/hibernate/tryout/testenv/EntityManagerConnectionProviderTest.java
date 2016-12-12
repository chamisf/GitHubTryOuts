package lk.chsoft.hibernate.tryout.testenv;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.Properties;

public class EntityManagerConnectionProviderTest {

    @Test
    public void test() {
        Properties properties = getMsSqlDataSourceProperties();
        properties.put(org.hibernate.jpa.AvailableSettings.LOADED_CLASSES, Arrays.asList(SecurityId.class));

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("testPersistenceUnit", properties);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        SecurityId securityId = new SecurityId("Test");
        entityManager.persist(securityId);

        Long id = securityId.getId();


        SecurityId loadedId = entityManager.find(SecurityId.class, id);

        System.out.println(">>>>>>>>>>>>>" + loadedId.getId());
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();


    }

    private Properties getHSqlDataSourceProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.ejb.use_class_enhancer", Boolean.TRUE.toString());
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
        properties.setProperty("hibernate.ejb.use_class_enhancer", Boolean.TRUE.toString());
        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        properties.put("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put("hibernate.connection.url", "jdbc:sqlserver://localhost:1433;databaseName=test");
        properties.put("hibernate.connection.username", "sa");
        properties.put("hibernate.connection.password", "csfcse86");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "true");
        return properties;
    }

}
