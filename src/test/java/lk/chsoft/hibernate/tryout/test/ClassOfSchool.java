package lk.chsoft.hibernate.tryout.test;

import javax.persistence.*;

/**
 * Created by chami on 10/30/16.
 */
@Entity
public class ClassOfSchool {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
