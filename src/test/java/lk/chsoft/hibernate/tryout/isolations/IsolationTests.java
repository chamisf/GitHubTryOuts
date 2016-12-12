package lk.chsoft.hibernate.tryout.isolations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class IsolationTests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COLl")
    private Integer col1;

    @Column(name = "COL2")
    private Integer col2;

    @Column(name = "COL3")
    private Integer col3;

    public IsolationTests setCol1(Integer col1) {
        this.col1 = col1;
        return this;
    }

    public IsolationTests setCol2(Integer col2) {
        this.col2 = col2;
        return this;
    }

    public IsolationTests setCol3(Integer col3) {
        this.col3 = col3;
        return this;
    }

    @Override
    public String toString() {
        return "IsolationTests{" +
                "id=" + id +
                ", col1=" + col1 +
                ", col2=" + col2 +
                ", col3=" + col3 +
                '}';
    }
}

