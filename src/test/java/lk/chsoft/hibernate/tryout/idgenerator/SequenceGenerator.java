package lk.chsoft.hibernate.tryout.idgenerator;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * DEBUG [main]: n.t.d.l.SLF4JQueryLoggingListener - Name: Time:135 Num:2 Query:{[insert into sequence_generator (id) values (?)][1]} {[insert into sequence_generator (id) values (?)][2]}
 * DEBUG [main]: n.t.d.l.SLF4JQueryLoggingListener - Name: Time:1 Num:4 Query:{[insert into sequence_generator (id) values (?)][1]} {[insert into sequence_generator (id) values (?)][2]} {[insert into sequence_generator (id) values (?)][3]} {[insert into sequence_generator (id) values (?)][4]}
 * DEBUG [main]: n.t.d.l.SLF4JQueryLoggingListener - Name: Time:1 Num:5 Query:{[insert into sequence_generator (id) values (?)][1]} {[insert into sequence_generator (id) values (?)][2]} {[insert into sequence_generator (id) values (?)][3]} {[insert into sequence_generator (id) values (?)][4]} {[insert into sequence_generator (id) values (?)][5]}
 */

@Entity
@Table(name = "sequence_generator")
public class SequenceGenerator {

    @Id

    /**
     * Method One

    //Sequence generator usage
    @GenericGenerator(
            name = "sequence",
            strategy = "sequence",
            parameters = {
                    @Parameter(
                            name = "sequence",
                            value = "sequence"
                    )

            })
    @GeneratedValue(generator = "sequence")

     */

    /**
     * Method Two
     *
     * Hibernate may choose SequenceGenerator implementation such as SequenceHiLoGenerator or SequenceStyleGenerator
     *
     */
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "id_gen")
    @javax.persistence.SequenceGenerator(name = "id_gen", sequenceName = "seq_gen", initialValue = 100, allocationSize = 10)
    private Long id;
}
