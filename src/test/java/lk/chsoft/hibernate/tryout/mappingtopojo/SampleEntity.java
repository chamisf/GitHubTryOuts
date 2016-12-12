package lk.chsoft.hibernate.tryout.mappingtopojo;

import javax.persistence.*;

/**
 * You need at least one entity to put {@link SqlResultSetMapping}. Or specify them in the orm.xml file.
 */
@SqlResultSetMapping(name="ActorResult", classes = {
        @ConstructorResult(targetClass = Actor.class,
                           columns = {@ColumnResult(name="first_name"), @ColumnResult(name="last_name")})
})
@Entity
public class SampleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
}
