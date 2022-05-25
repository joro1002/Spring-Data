package com.example.xmlprocessing.services;

import java.io.IOException;

public interface CarService {
    void seedCars() throws Exception;
    String printMakeToyota();

    String findAllCarsWithParts();
}
