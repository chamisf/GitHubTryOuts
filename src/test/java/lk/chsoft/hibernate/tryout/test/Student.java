package lk.chsoft.hibernate.tryout.test;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    @ElementCollection
    private Set<Address> addresses;
    @OneToMany
    private Set<ClassOfSchool> classOfSchools;

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

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<ClassOfSchool> getClassOfSchools() {
        return classOfSchools;
    }

    public void setClassOfSchools(Set<ClassOfSchool> classOfSchools) {
        this.classOfSchools = classOfSchools;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
