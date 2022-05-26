package com.example.xmlprocessing.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerImportRootDto {
    @XmlElement(name = "customer")
    private List<CustomerImportDto> customers;

    public CustomerImportRootDto() {
    }

    public List<CustomerImportDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerImportDto> customers) {
        this.customers = customers;
    }
}
