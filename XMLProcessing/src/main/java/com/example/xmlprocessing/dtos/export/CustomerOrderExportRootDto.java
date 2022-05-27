package com.example.xmlprocessing.dtos.export;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerOrderExportRootDto {
    @XmlElement(name = "customer")
    private List<CustomerOrderExportDto> customers;

    public CustomerOrderExportRootDto() {
    }

    public List<CustomerOrderExportDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerOrderExportDto> customers) {
        this.customers = customers;
    }
}
