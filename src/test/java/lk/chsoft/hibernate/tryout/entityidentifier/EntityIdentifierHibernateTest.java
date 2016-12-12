package lk.chsoft.hibernate.tryout.entityidentifier;

import lk.chsoft.hibernate.tryout.util.AbstractHibernateTest;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

public class EntityIdentifierHibernateTest extends AbstractHibernateTest {
    @Override
    protected Class<?>[] entities() {
        return new Class<?>[]{
                Product.class, EntityAttribute.class, EntityEvent.class
        };
    }

    @Test
    public void testEntityAttributes() {
        doInTransaction(new TransactionCallable<Void>() {
            @Override
            public Void execute(Session session) {

                Product product = new Product("LCD");
                session.persist(product);

                EntityEvent entityEvent = new EntityEvent();
                entityEvent.setMessage("Product " + product.getName() + " is added.");
                entityEvent.setEntityIdentifier(new EntityIdentifier(product.getClass(), product.getId()));
                session.persist(entityEvent);

                EntityAttribute entityAttribute = new EntityAttribute();
                entityAttribute.setName("Ad campaign.");
                entityAttribute.setValue("LCD Promotion");
                entityAttribute.setEntityIdentifier(new EntityIdentifier(product.getClass(), product.getId()));
                session.persist(entityAttribute);

                Assert.assertSame(1, session.createQuery("select ea from EntityAttribute ea where ea.entityIdentifier = :entityIdentifier").
                        setParameter("entityIdentifier", new EntityIdentifier(product.getClass(), product.getId())).list().size());

                return null;
            }
        });
    }
}
