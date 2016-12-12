package lk.chsoft.hibernate.tryout.idgenerator;


import lk.chsoft.hibernate.tryout.util.AbstractHibernateTest;
import lk.chsoft.hibernate.tryout.util.MySqlDataSourceManger;
import org.hibernate.Session;
import org.junit.Test;

import java.util.Properties;

public class IdGeneratorTest extends AbstractHibernateTest{



    @Test
    public void testIdentityGenerator() {
        doInTransaction(new AbstractHibernateTest.TransactionCallable<Void>() {
            @Override
            public Void execute(Session session) {
                for (int i = 0; i < 5; i++) {
                    session.persist(new IdentityGenerator());
                }
                return null;
            }
        });
    }

    @Test
    public void testSequenceGenerator() {
        doInTransaction(new AbstractHibernateTest.TransactionCallable<Void>() {
            @Override
            public Void execute(Session session) {
                for (int i = 0; i < 5; i++) {
                    session.persist(new SequenceGenerator());
                }
                return null;
            }
        });
    }

    @Test
    public void testTableGenerator() {
        doInTransaction(new AbstractHibernateTest.TransactionCallable<Void>() {
            @Override
            public Void execute(Session session) {
                for (int i = 0; i < 5; i++) {
                    session.persist(new TableTypeGenerator());
                }
                return null;
            }
        });
    }

    @Override
    protected Properties getProperties() {
        Properties properties = MySqlDataSourceManger.MANAGER.getProperties();
        properties.put("hibernate.order_inserts", "true");
        properties.put("hibernate.order_updates", "true");
        properties.put("hibernate.jdbc.batch_size", "2");
        return properties;
    }

    @Override
    protected Class<?>[] entities() {
        return new Class<?>[]{IdentityGenerator.class, SequenceGenerator.class, TableTypeGenerator.class};
    }
}
