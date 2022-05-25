package com.example.xmlprocessing.services.impl;

import com.example.xmlprocessing.entities.Suppliers;
import com.example.xmlprocessing.repositories.PartRepository;
import com.example.xmlprocessing.repositories.SupplierRepository;
import com.example.xmlprocessing.services.PartService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Random;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository, ModelMapper modelMapper, Gson gson) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedPart() throws Exception {

    }

    private Suppliers getRandom() throws Exception {
        Random random = new Random();

        long index = (long) random.nextInt((int) this.supplierRepository.count()) + 1;
        Optional<Suppliers> byId = this.supplierRepository.findById(index);

        if (byId.isPresent()){
            return byId.get();
        }else {
            throw new Exception("Supplier don't exist");
        }
    }
}
