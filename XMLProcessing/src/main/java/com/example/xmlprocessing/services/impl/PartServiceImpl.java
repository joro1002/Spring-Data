package com.example.xmlprocessing.services.impl;

import com.example.xmlprocessing.dtos.PartImportDto;
import com.example.xmlprocessing.dtos.PartImportRootDto;
import com.example.xmlprocessing.entities.Parts;
import com.example.xmlprocessing.entities.Suppliers;
import com.example.xmlprocessing.repositories.PartRepository;
import com.example.xmlprocessing.repositories.SupplierRepository;
import com.example.xmlprocessing.services.PartService;
import com.example.xmlprocessing.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.Random;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository, ModelMapper modelMapper, XmlParser xmlParser) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedPart() throws Exception {
        PartImportRootDto partImportRootDto = this.xmlParser.parseXml(PartImportRootDto.class, "src/main/resources/XMLs/parts.xml");

        for (PartImportDto part : partImportRootDto.getParts()) {
            Parts map = this.modelMapper.map(part, Parts.class);
            map.setSuppliers(this.getRandom());
            this.partRepository.saveAndFlush(map);
        }
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
