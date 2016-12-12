package lk.chsoft.hibernate.tryout.idgenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DEBUG [main]: n.t.d.l.SLF4JQueryLoggingListener - Name: Time:106 Num:1 Query:{[insert into identity_generator default values][]}
 * DEBUG [main]: n.t.d.l.SLF4JQueryLoggingListener - Name: Time:3 Num:1 Query:{[insert into identity_generator default values][]}
 * DEBUG [main]: n.t.d.l.SLF4JQueryLoggingListener - Name: Time:1 Num:1 Query:{[insert into identity_generator default values][]}
 * DEBUG [main]: n.t.d.l.SLF4JQueryLoggingListener - Name: Time:1 Num:1 Query:{[insert into identity_generator default values][]}
 * DEBUG [main]: n.t.d.l.SLF4JQueryLoggingListener - Name: Time:1 Num:1 Query:{[insert into identity_generator default values][]}
 */

@Entity
@Table(name = "identity_generator")
public class IdentityGenerator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
