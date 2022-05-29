package exam.model.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "towns")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownImportXmlRootDto {
    @XmlElement(name = "town")
    private List<TownImportXmlDto> towns;

    public TownImportXmlRootDto() {
    }

    public List<TownImportXmlDto> getTowns() {
        return towns;
    }

    public void setTowns(List<TownImportXmlDto> towns) {
        this.towns = towns;
    }
}
