package com.example.football.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity{
    private String name;
    private int population;
    private String travelGuide;

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
    @Column(length = 512)
    public String getTravelGuide() {
        return travelGuide;
    }

    public void setTravelGuide(String travelGuide) {
        this.travelGuide = travelGuide;
    }
}
