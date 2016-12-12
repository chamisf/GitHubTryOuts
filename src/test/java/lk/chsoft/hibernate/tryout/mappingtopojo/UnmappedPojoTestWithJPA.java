package lk.chsoft.hibernate.tryout.mappingtopojo;

import lk.chsoft.hibernate.tryout.util.AbstractJpaTest;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by chAmi on 9/20/2016.
 */
public class UnmappedPojoTestWithJPA extends AbstractJpaTest {
    @Override
    protected Class<?>[] entities() {
        return new Class<?>[]{SampleEntity.class};
    }

    @Test
    public void testUnmappedEntity() {
        doInTransaction(new TransactionCallable<Void>() {
            @Override
            public Void execute(EntityManager entityManager) {

                List<Actor> resultList = entityManager.createNativeQuery("SELECT first_name, last_name FROM hibtest.actor", "ActorResult").getResultList();

                System.out.println(resultList.iterator().hasNext() ? resultList.iterator().next().toString():"");

                return null;
            }
        });
    }
}
