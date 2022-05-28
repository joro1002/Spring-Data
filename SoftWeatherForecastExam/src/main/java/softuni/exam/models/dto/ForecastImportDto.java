package softuni.exam.models.dto;

import org.hibernate.validator.constraints.Length;
import softuni.exam.config.LocalTimeAdapter;
import softuni.exam.models.entity.City;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalTime;

@XmlRootElement(name = "forecast")
@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastImportDto {
    @XmlElement(name = "day_of_week")
    private String dayOfWeek;
    @XmlElement(name = "max_temperature")
    private double maxTemperature;
    @XmlElement(name = "min_temperature")
    private double minTemperature;
    @XmlElement
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    private LocalTime sunrise;
    @XmlElement
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    private LocalTime sunset;
    @XmlElement
    private int city;

    public ForecastImportDto() {
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Min(value = -20)
    @Max(value = 60)
    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    @Min(value = -50)
    @Max(value = 40)
    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public LocalTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalTime sunrise) {
        this.sunrise = sunrise;
    }

    public LocalTime getSunset() {
        return sunset;
    }

    public void setSunset(LocalTime sunset) {
        this.sunset = sunset;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }
}
