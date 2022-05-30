package com.example.football.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity{
    private String name;
    private String stadiumName;
    private int fanBase;
    private String history;
    private Town town;

    public Team() {
    }

    @Column(unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "stadium_name")
    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    @Column(name = "fan_base")
    public int getFanBase() {
        return fanBase;
    }

    public void setFanBase(int fanBase) {
        this.fanBase = fanBase;
    }

    @Lob
    @Column(length = 512)
    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    @OneToOne
    @JoinColumn(name = "town_id")
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}

