package lk.chsoft.hibernate.tryout.testenv;

import javax.persistence.*;
import java.io.Serializable;


@Table(name = "SecurityId")
@Entity(name = "SecurityId")
public class SecurityId implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    public SecurityId() {
        this.name = "";
    }

    public SecurityId(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

