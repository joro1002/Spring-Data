package softuni.jsonprocessing.dtos;

import com.google.gson.annotations.Expose;

import java.util.List;
import java.util.Set;

public class CarsWithPartsViewDto {
    @Expose
    private String make;
    @Expose
    private String model;
    @Expose
    private Long travelledDistance;
    @Expose
    private Set<PartsViewDto> parts;

    public CarsWithPartsViewDto() {
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

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public Set<PartsViewDto> getParts() {
        return parts;
    }

    public void setParts(Set<PartsViewDto> parts) {
        this.parts = parts;
    }
}
