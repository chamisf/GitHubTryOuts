package lk.chsoft.hibernate.tryout.mappingtopojo;

import lk.chsoft.hibernate.tryout.util.AbstractHibernateTest;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.StandardBasicTypes;
import org.junit.Test;

import javax.persistence.ConstructorResult;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chAmi on 9/20/2016.
 */
public class UnmappedPojoTestWithHibernate extends AbstractHibernateTest {
    @Override
    protected Class<?>[] entities() {
        return new Class<?>[]{SampleEntity.class};
    }

    @Test
    public void testUnmappedEntity_RawCast() {
        doInTransaction(new TransactionCallable<Actor>() {

            @Override
            public Actor execute(Session session) {

                Query query = session.createNativeQuery("SELECT first_name, last_name FROM hibtest.actor");
                List<Object[]> resultList = query.list();
                List<Actor> actors = new ArrayList<>();
                resultList.stream().forEach((record) -> {
                    String first_name = (String) record[0];
                    String last_name = (String) record[1];
                    actors.add(new Actor(first_name, last_name));
                });

                System.out.println(actors.iterator().hasNext() ? actors.iterator().next().toString() : "");

                return actors.get(0);
            }
        });
    }

    @Test
    public void testUnmappedEntity_ScallerMapping() {
        doInTransaction(new TransactionCallable<Actor>() {
            @Override
            public Actor execute(Session session) {
                List<Object[]> results = session.createNativeQuery("SELECT first_name as fn, last_name as ln FROM hibtest.actor")
                        .addScalar("fn", StandardBasicTypes.STRING).addScalar("ln", StandardBasicTypes.STRING).list();

                results.stream().forEach((record) -> {
                    String first_name = (String) record[0];
                    String last_name = (String) record[1];
                    System.out.println("[" + first_name + "]" + " [" + last_name + "]");
                });
                return null;
            }
        });
    }

    /**
     * Unlike in {@link ConstructorResult} {@link AliasToBeanResultTransformer} use default constructor to instantiate
     * an object and searches the getter methods based on the alias and type of the return column.
     */
    @Test
    public void testUnmappedEntity_ResultSetTransformer() {
        doInTransaction(new TransactionCallable<Actor>() {
            @Override
            public Actor execute(Session session) {
                List<Actor> results = session.createNativeQuery("SELECT first_name , last_name FROM hibtest.actor")
                        .addScalar("first_name").addScalar("last_name").
                                setResultTransformer(new AliasToBeanResultTransformer(Actor.class)).list();

                System.out.println("[" + results.get(0).getFirst_name() + "]" + " [" + results.get(0).getLast_name() + "]");
                return null;
            }
        });
    }

    /** You need mapped entity class with table to map other one.
     */
//    @Test()
//    public void testUnmappedEntity_NewOp() {
//        doInTransaction(new TransactionCallable<Actor>() {
//            @Override
//            public Actor execute(Session session) {
//                List<Actor> results = session.createQuery("SELECT new lk.chsoft.hibernate.tryout.mappingtopojo.Actor(first_name , last_name) FROM hibtest.actor").list();
//
//                System.out.println("[" + results.get(0).getFirst_name() + "]" + " [" + results.get(0).getLast_name() + "]");
//                return null;
//            }
//        });
//    }
}
