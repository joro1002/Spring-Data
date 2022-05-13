package softuni.jsonprocessing.dtos;

import com.google.gson.annotations.Expose;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CustomerSeedDto {
    @Expose
    private String name;
    @Expose
    private LocalDateTime birthDate;
    @Expose
    private boolean isYoungDriver;

    public CustomerSeedDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}

