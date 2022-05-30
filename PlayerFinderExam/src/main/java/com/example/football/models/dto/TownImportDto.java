package com.example.football.models.dto;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class TownImportDto {
    @Expose
    private String name;
    @Expose
    private int population;
    @Expose
    private String travelGuide;

    public TownImportDto() {
    }

    @Length(min = 2)
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Positive
    @NotNull
    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Length(min = 10)
    @NotNull
    public String getTravelGuide() {
        return travelGuide;
    }

    public void setTravelGuide(String travelGuide) {
        this.travelGuide = travelGuide;
    }
}
