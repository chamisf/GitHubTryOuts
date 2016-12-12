package lk.chsoft.hibernate.tryout.test;

import lk.chsoft.hibernate.tryout.util.AbstractHibernateTest;
import lk.chsoft.hibernate.tryout.util.MySqlDataSourceManger;
import org.hibernate.Session;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;

/**
 * Created by chami on 10/30/16.
 */
public class QuickTest extends AbstractHibernateTest {
    @Override
    protected Class<?>[] entities() {
        return new Class<?>[] {
                Student.class, Address.class, City.class, ClassOfSchool.class
        };
    }

    @Override
    protected Properties getProperties() {
        return MySqlDataSourceManger.MANAGER.getProperties();
    }

    @Test
    public void testEntityIdentifier() {
        doInTransaction(new TransactionCallable<Void>() {
            @Override
            public Void execute(Session session) {

                City city = session.load(City.class, 1L);
                Address address = new Address();
                address.setLineOne("line one");
                address.setCity(city);

                Address address1 = new Address();
                address1.setLineOne("line one one");
                address1.setCity(city);

                ClassOfSchool classOfSchool = new ClassOfSchool();
                classOfSchool.setName("1-A");
                session.persist(classOfSchool);

                ClassOfSchool classOfSchool1 = new ClassOfSchool();
                classOfSchool1.setName("2_F");
                session.persist(classOfSchool1);

                Student student = new Student();
                student.setName("chami");
                student.setAddresses(new HashSet<>(Arrays.asList(address, address1)));
                student.setClassOfSchools(new HashSet<>(Arrays.asList(classOfSchool, classOfSchool1)));
                session.persist(student);

                Long id = student.getId();

                Student loadedStudent = session.load(Student.class, id);
                System.out.println(loadedStudent.toString());
                return null;
            }
        });
    }
}
