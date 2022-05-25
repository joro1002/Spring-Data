package com.example.xmlprocessing.services;


import java.io.IOException;
import java.util.List;

public interface SupplierService {
    void seedSupplier() throws IOException;

    String findAllImporterFalse();
}
