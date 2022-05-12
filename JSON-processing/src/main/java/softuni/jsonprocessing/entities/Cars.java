package softuni.jsonprocessing.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Cars extends BaseEntity{
    private String make;
    private String model;
    private int travelledDistance;
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
    public int getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(int travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    @ManyToMany
    public Set<Parts> getParts() {
        return parts;
    }

    public void setParts(Set<Parts> parts) {
        this.parts = parts;
    }

}
