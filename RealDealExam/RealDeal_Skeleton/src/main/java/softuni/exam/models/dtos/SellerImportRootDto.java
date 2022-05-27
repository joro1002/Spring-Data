package softuni.exam.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sellers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerImportRootDto {
    @XmlElement(name = "seller")
    private List<SellerImportDto> sellers;

    public SellerImportRootDto() {
    }

    public List<SellerImportDto> getSellers() {
        return sellers;
    }

    public void setSellers(List<SellerImportDto> sellers) {
        this.sellers = sellers;
    }
}
