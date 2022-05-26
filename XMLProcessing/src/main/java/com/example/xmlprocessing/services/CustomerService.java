package com.example.xmlprocessing.services;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface CustomerService {
    void seedCustomer() throws IOException, JAXBException;

    String findAllCustomerWithMoreOneSale();
    void exportOrdered() throws JAXBException;
}
