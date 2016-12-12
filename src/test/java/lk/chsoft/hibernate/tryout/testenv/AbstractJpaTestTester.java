package lk.chsoft.hibernate.tryout.testenv;

import lk.chsoft.hibernate.tryout.util.AbstractJpaTest;
import org.junit.Test;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.Assert.assertSame;

/**
 * Test AbstractJPATest functionality.
 */
public class AbstractJpaTestTester extends AbstractJpaTest {
    @Override
    protected Class<?>[] entities() {
        return new Class<?>[]{
                SecurityId.class
        };
    }

    @Test
    public void testAbstractJPATest() {
        doInTransaction(new TransactionCallable<Void>() {
            @Override
            public Void execute(EntityManager entityManager) {
                SecurityId securityId = new SecurityId("CH");
                entityManager.persist(securityId);
                Long id = securityId.getId();
                SecurityId loadedId = entityManager.find(SecurityId.class, id);

                assertSame(1, entityManager.createQuery("select si from SecurityId si where si.id = :id")
                        .setParameter("id", new Long("1"))
                        .getResultList().size());
                return null;
            }
        });
    }
}
