package lk.chsoft.hibernate.tryout.idgenerator;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DEBUG [main]: n.t.d.l.SLF4JQueryLoggingListener - Name: Time:54 Num:2 Query:{[insert into table_type_generator (id) values (?)][1]} {[insert into table_type_generator (id) values (?)][2]}
 * DEBUG [main]: n.t.d.l.SLF4JQueryLoggingListener - Name: Time:1 Num:4 Query:{[insert into table_type_generator (id) values (?)][1]} {[insert into table_type_generator (id) values (?)][2]} {[insert into table_type_generator (id) values (?)][3]} {[insert into table_type_generator (id) values (?)][4]}
 * DEBUG [main]: n.t.d.l.SLF4JQueryLoggingListener - Name: Time:0 Num:5 Query:{[insert into table_type_generator (id) values (?)][1]} {[insert into table_type_generator (id) values (?)][2]} {[insert into table_type_generator (id) values (?)][3]} {[insert into table_type_generator (id) values (?)][4]} {[insert into table_type_generator (id) values (?)][5]}
*/

@Entity
@Table(name = "table_type_generator")
public class TableTypeGenerator {
    @Id
    @GenericGenerator(
            name = "table_gen",
            strategy = "enhanced-table",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "table_name",
                            value = "sequence_table"
                    )
            })
    @GeneratedValue(strategy = GenerationType.TABLE,
                    generator = "table_gen")
    private Long id;
}
