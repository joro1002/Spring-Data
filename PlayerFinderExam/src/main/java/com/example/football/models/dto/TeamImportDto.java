package com.example.football.models.dto;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TeamImportDto {
    @Expose
    private String name;
    @Expose
    private String stadiumName;
    @Expose
    private int fanBase;
    @Expose
    private String history;
    @Expose
    private String townName;

    public TeamImportDto() {
    }

    @Length(min = 3)
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 3)
    @NotNull
    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    @Min(value = 1000)
    @NotNull
    public int getFanBase() {
        return fanBase;
    }


    public void setFanBase(int fanBase) {
        this.fanBase = fanBase;
    }

    @Length(min = 10)
    @NotNull
    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    @NotNull
    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
