package com.example.xmlprocessing.services.impl;

import com.example.xmlprocessing.dtos.SupplierImportDto;
import com.example.xmlprocessing.dtos.SupplierImportRootDto;
import com.example.xmlprocessing.entities.Suppliers;
import com.example.xmlprocessing.repositories.SupplierRepository;
import com.example.xmlprocessing.services.CarService;
import com.example.xmlprocessing.services.SupplierService;
import com.example.xmlprocessing.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.xml.bind.JAXBException;
import java.io.IOException;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final CarService carService;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, CarService carService, ModelMapper modelMapper, XmlParser xmlParser) {
        this.supplierRepository = supplierRepository;
        this.carService = carService;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedSupplier() throws IOException, JAXBException {
        SupplierImportRootDto supplierImportRootDto = this.xmlParser.parseXml(SupplierImportRootDto.class, "src/main/resources/XMLs/suppliers.xml");
        for (SupplierImportDto supplier : supplierImportRootDto.getSuppliers()) {
            this.supplierRepository.saveAndFlush(this.modelMapper.map(supplier, Suppliers.class));
        }
    }


}
