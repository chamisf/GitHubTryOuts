package lk.chsoft.hibernate.tryout.testenv;

import lk.chsoft.hibernate.tryout.util.AbstractHibernateTest;
import org.hibernate.Session;
import org.junit.Test;

import static junit.framework.Assert.assertSame;

public class AbstractHibernateTestTester extends AbstractHibernateTest {

    @Override
    protected Class<?>[] entities() {
        return new Class<?>[] {
                SecurityId.class
        };
    }

    @Test
    public void testEntityIdentifier() {
        doInTransaction(new TransactionCallable<Void>() {
            @Override
            public Void execute(Session session) {
                SecurityId securityId = new SecurityId("CH");
                session.persist(securityId);
                Long id = securityId.getId();
                SecurityId loadedId = (SecurityId) session.load(SecurityId.class, id);

                assertSame(1, session.createQuery("select si from SecurityId si where si.id = :id")
                        .setParameter("id", new Long("1"))
                        .list().size());
                return null;
            }
        });
    }
}
