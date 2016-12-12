package lk.chsoft.hibernate.tryout.entityidentifier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    private Long Id;
    private String name;

    public Product() {
        this.name = "";
    }

    public Product(String name) {
        this.name = name;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
