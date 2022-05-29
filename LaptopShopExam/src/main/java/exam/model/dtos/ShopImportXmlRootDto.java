package exam.model.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "shops")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopImportXmlRootDto {
    @XmlElement(name = "shop")
    private List<ShopImportXmlDto> shops;

    public ShopImportXmlRootDto() {
    }

    public List<ShopImportXmlDto> getShops() {
        return shops;
    }

    public void setShops(List<ShopImportXmlDto> shops) {
        this.shops = shops;
    }
}
