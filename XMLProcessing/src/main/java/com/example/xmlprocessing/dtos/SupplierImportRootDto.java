package com.example.xmlprocessing.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierImportRootDto {
    @XmlElement(name = "supplier")
    private List<SupplierImportDto> suppliers;

    public SupplierImportRootDto() {
    }

    public List<SupplierImportDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierImportDto> suppliers) {
        this.suppliers = suppliers;
    }
}
