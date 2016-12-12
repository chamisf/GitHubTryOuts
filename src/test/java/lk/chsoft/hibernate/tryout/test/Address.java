package lk.chsoft.hibernate.tryout.test;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

@Embeddable
public class Address {
    private String lineOne;
    @OneToOne
    private City city;

    public String getLineOne() {
        return lineOne;
    }

    public void setLineOne(String lineOne) {
        this.lineOne = lineOne;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "lineOne='" + lineOne + '\'' +
                ", city=" + city +
                '}';
    }
}
