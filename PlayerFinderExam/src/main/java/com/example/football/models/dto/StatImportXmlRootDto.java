package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "stats")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatImportXmlRootDto {
    @XmlElement(name = "stat")
    private List<StatImportXmlDto> stats;

    public StatImportXmlRootDto() {
    }

    public List<StatImportXmlDto> getStats() {
        return stats;
    }

    public void setStats(List<StatImportXmlDto> stats) {
        this.stats = stats;
    }
}
