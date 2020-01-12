package es.uca.iw.sss.spring;

import javax.persistence.*;

@Entity
public class Scale {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(nullable = false)
    private String date = "";
    @ManyToOne
    private Ship ship;
    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
    private City city;

    public Scale()
    {

    }

    public Scale(String date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.id == null) {
            return false;
        }

        if (obj instanceof Scale && obj.getClass().equals(getClass())) {
            return this.id.equals(((Scale) obj).id);
        }

        return false;
    }

    @Override
    public String toString() {
        return "Scales{" +
                ", date='" + date + '\'' +
                '}';
    }
}
