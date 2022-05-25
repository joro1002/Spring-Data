package com.example.xmlprocessing.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Cars extends BaseEntity{
    private String make;
    private String model;
    private long travelledDistance;
    private Set<Parts> parts;

    public Cars(String make, String model, int travelledDistance) {
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
    }

    public Cars() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "travelled_distance")
    public long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "parts_cars",
            joinColumns = {@JoinColumn(name = "car_id")},
            inverseJoinColumns = {@JoinColumn(name = "part_id")})
    public Set<Parts> getParts() {
        return parts;
    }

    public void setParts(Set<Parts> parts) {
        this.parts = parts;
    }

}
