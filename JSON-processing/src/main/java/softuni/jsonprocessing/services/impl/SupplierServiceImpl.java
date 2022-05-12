package softuni.jsonprocessing.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.jsonprocessing.repositories.SupplierRepository;
import softuni.jsonprocessing.services.SupplierService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedSupplier() throws IOException {
        String content = String.join("", Files.readAllLines(Path.of("src/main/resources/jsons/suppliers.json")));
        System.out.println();
    }
}
