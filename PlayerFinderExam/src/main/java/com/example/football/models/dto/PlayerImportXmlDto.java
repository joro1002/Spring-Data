package com.example.football.models.dto;

import com.example.football.config.LocalDateAdapter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerImportXmlDto {
    @XmlElement(name = "first-name")
    private String firstName;
    @XmlElement(name = "last-name")
    private String lastName;
    @XmlElement
    private String email;
    @XmlElement(name = "birth-date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate birthDate;
    @XmlElement
    private String position;
    @XmlElement
    private TownImportXmlDto town;
    @XmlElement
    private TeamImportXmlDto team;
    @XmlElement
    private StatIdImportXmlDto stat;

    public PlayerImportXmlDto() {
    }

    @Length(min = 3)
    @NotNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Length(min = 3)
    @NotNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Pattern(regexp = "^(\\w+@\\w+)(.\\w+){2,}$")
    @NotNull
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public TownImportXmlDto getTown() {
        return town;
    }

    public void setTown(TownImportXmlDto town) {
        this.town = town;
    }

    public TeamImportXmlDto getTeam() {
        return team;
    }

    public void setTeam(TeamImportXmlDto team) {
        this.team = team;
    }

    public StatIdImportXmlDto getStat() {
        return stat;
    }

    public void setStat(StatIdImportXmlDto stat) {
        this.stat = stat;
    }
}
