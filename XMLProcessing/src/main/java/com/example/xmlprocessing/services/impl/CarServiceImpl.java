package com.example.xmlprocessing.services.impl;

import com.example.xmlprocessing.dtos.CarImportDto;
import com.example.xmlprocessing.dtos.CarImportRootDto;
import com.example.xmlprocessing.entities.Cars;
import com.example.xmlprocessing.entities.Parts;
import com.example.xmlprocessing.repositories.CarRepository;
import com.example.xmlprocessing.repositories.PartRepository;
import com.example.xmlprocessing.services.CarService;
import com.example.xmlprocessing.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;

    private final XmlParser xmlParser;
    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository, ModelMapper modelMapper, XmlParser xmlParser) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedCars() throws Exception {
        CarImportRootDto carImportRootDto = this.xmlParser.parseXml(CarImportRootDto.class, "src/main/resources/XMLs/cars.xml");
        for (CarImportDto car : carImportRootDto.getCars()) {
            Cars map = this.modelMapper.map(car, Cars.class);
            map.setParts(getRandomParts());
            this.carRepository.saveAndFlush(map);
        }
    }

    @Override
    public String printMakeToyota() {
       return "f";
    }

    @Override
    public String findAllCarsWithParts() {
     return "df";
    }


    private Set<Parts> getRandomParts() throws Exception {
        Set<Parts> parts = new HashSet<>();

        for (int i = 0; i < 3; i++) {
            Parts randomPart = getRandomPart();
            parts.add(randomPart);
        }
        return parts;
    }

    private Parts getRandomPart() throws Exception {
        Random random = new Random();
        long index = (long) random.nextInt((int) this.partRepository.count()) + 1;

        Optional<Parts> byId = this.partRepository.findById(index);
        if (byId.isPresent()){
            return byId.get();
        }else {
            throw new Exception("Invalid index");
        }
    }

}
