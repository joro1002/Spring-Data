package softuni.jsonprocessing.services;

import softuni.jsonprocessing.dtos.SupplierImporterFalse;

import java.io.IOException;
import java.util.List;

public interface SupplierService {
    void seedSupplier() throws IOException;

    String findAllImporterFalse();
}
