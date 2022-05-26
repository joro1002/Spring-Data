package com.example.xmlprocessing.services;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface CarService {
    void seedCars() throws Exception;
    String printMakeToyota();

    String findAllCarsWithParts();
    void makeToyota() throws JAXBException;
    void carsAndParts() throws JAXBException;
}
