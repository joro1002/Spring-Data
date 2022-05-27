package com.example.xmlprocessing.dtos.export;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarMakeModelExportRootDto {
    @XmlElement(name = "car")
    private List<CarMakeToyotaExportDto> cars;

    public CarMakeModelExportRootDto() {
    }

    public List<CarMakeToyotaExportDto> getCars() {
        return cars;
    }

    public void setCars(List<CarMakeToyotaExportDto> cars) {
        this.cars = cars;
    }
}
