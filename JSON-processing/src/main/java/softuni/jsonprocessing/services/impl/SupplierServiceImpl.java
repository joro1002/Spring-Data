package softuni.jsonprocessing.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.jsonprocessing.dtos.SupplierImporterFalse;
import softuni.jsonprocessing.dtos.SupplierSeedDto;
import softuni.jsonprocessing.entities.Suppliers;
import softuni.jsonprocessing.repositories.SupplierRepository;
import softuni.jsonprocessing.services.CarService;
import softuni.jsonprocessing.services.SupplierService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final CarService carService;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, CarService carService, ModelMapper modelMapper, Gson gson) {
        this.supplierRepository = supplierRepository;
        this.carService = carService;
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

    @Override
    public String findAllImporterFalse() {
        Set<Suppliers> allByImporterIsFalse = this.supplierRepository.findAllByImporterIsFalse();
        List<SupplierImporterFalse> result = new ArrayList<>();
        for (Suppliers suppliers : allByImporterIsFalse) {
            SupplierImporterFalse map = this.modelMapper.map(suppliers, SupplierImporterFalse.class);
            result.add(map);
        }
        return this.gson.toJson(result);
    }


}
