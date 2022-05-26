package com.example.xmlprocessing.services;


import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface SupplierService {
    void seedSupplier() throws IOException, JAXBException;

}
