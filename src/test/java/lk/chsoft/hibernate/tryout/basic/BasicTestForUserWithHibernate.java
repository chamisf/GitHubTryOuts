package lk.chsoft.hibernate.tryout.basic;

import lk.chsoft.hibernate.tryout.util.AbstractHibernateTest;
import org.hibernate.Session;
import org.junit.Test;


public class BasicTestForUserWithHibernate extends AbstractHibernateTest {

    @Test
    public void testPersist() {
        doInTransaction(new TransactionCallable<Void>() {

            @Override
            public Void execute(Session session) {
                User user = new User().setName("chaminda");
                session.persist(user);
                return null;
            }
        });
    }

    @Override
    protected Class<?>[] entities() {
        return new Class<?>[]{User.class};
    }
}
