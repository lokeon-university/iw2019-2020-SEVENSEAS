package es.uca.iw.sss.spring;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column
    private String name = "";
    @Column
    private String pic = "";
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "city")
    private Set<AdviceCity> adviceSet = new HashSet<>();
    @OneToOne
    private Scale scale;

    public City()
    {

    }

    public City(Scale scale) {
        this.scale = scale;
    }

    public City(String name,Scale scale)
    {
        this.name = name;
        this.scale = scale;
    }

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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Set<AdviceCity> getAdviceSet() {
        return adviceSet;
    }

    public void setAdviceSet(Set<AdviceCity> adviceSet) {
        this.adviceSet = adviceSet;
    }

    public Scale getScale() {
        return scale;
    }

    public void setScale(Scale scale) {
        this.scale = scale;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.id == null) {
            return false;
        }

        if (obj instanceof City && obj.getClass().equals(getClass())) {
            return this.id.equals(((City) obj).id);
        }

        return false;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }

}
