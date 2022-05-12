package softuni.jsonprocessing.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.jsonprocessing.dtos.SupplierSeedDto;
import softuni.jsonprocessing.entities.Suppliers;
import softuni.jsonprocessing.repositories.SupplierRepository;
import softuni.jsonprocessing.services.SupplierService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper, Gson gson) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedSupplier() throws IOException {
        String content = String.join("", Files.readAllLines(Path.of("src/main/resources/jsons/suppliers.json")));

        SupplierSeedDto[] supplierSeedDtos = this.gson.fromJson(content, SupplierSeedDto[].class);

        for (SupplierSeedDto supplierSeedDto : supplierSeedDtos) {
            Suppliers map = modelMapper.map(supplierSeedDto, Suppliers.class);
            this.supplierRepository.saveAndFlush(map);
        }
    }
}
