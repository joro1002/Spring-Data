package exam.model;

import org.thymeleaf.standard.expression.Each;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity{
    private String name;
    private int population;
    private String travelGuide;
    private Set<Shop> shops;
    private Set<Customer> customers;

    public Town() {
    }

    @Column(unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Lob
    @Column(name = "travel_guide", length = 512)
    public String getTravelGuide() {
        return travelGuide;
    }

    public void setTravelGuide(String travelGuide) {
        this.travelGuide = travelGuide;
    }

    @OneToMany(mappedBy = "town", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Shop> getShops() {
        return shops;
    }

    public void setShops(Set<Shop> shops) {
        this.shops = shops;
    }

    @OneToMany(mappedBy = "town", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
}
