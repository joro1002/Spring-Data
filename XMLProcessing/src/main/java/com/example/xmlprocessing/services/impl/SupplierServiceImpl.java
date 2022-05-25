package com.example.xmlprocessing.services.impl;

import com.example.xmlprocessing.repositories.SupplierRepository;
import com.example.xmlprocessing.services.CarService;
import com.example.xmlprocessing.services.SupplierService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

    }

    @Override
    public String findAllImporterFalse() {
        return "s";
    }

}
