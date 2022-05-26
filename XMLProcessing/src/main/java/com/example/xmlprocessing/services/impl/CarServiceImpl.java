package com.example.xmlprocessing.services.impl;

import com.example.xmlprocessing.dtos.CarImportDto;
import com.example.xmlprocessing.dtos.CarImportRootDto;
import com.example.xmlprocessing.dtos.export.*;
import com.example.xmlprocessing.entities.Cars;
import com.example.xmlprocessing.entities.Parts;
import com.example.xmlprocessing.repositories.CarRepository;
import com.example.xmlprocessing.repositories.PartRepository;
import com.example.xmlprocessing.services.CarService;
import com.example.xmlprocessing.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public void makeToyota() throws JAXBException {
        List<CarMakeToyotaExportDto> toyota = this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota")
                .stream()
                .map(c -> this.modelMapper.map(c, CarMakeToyotaExportDto.class)).toList();

        CarMakeModelExportRootDto carMakeModelExportRootDto = new CarMakeModelExportRootDto();
        carMakeModelExportRootDto.setCars(toyota);

        this.xmlParser.exportXml(carMakeModelExportRootDto, CarMakeModelExportRootDto.class, "src/main/resources/XMLs/export/make-Toyota.xml");
    }

    @Override
    public void carsAndParts() throws JAXBException {
        List<Cars> all = this.carRepository.findAll();
        CarExportRootDto carRootDto = new CarExportRootDto();
        List<CarExportDto> carExportDtos = new ArrayList<>();

        for (Cars car : all) {
            CarExportDto carExportDto = this.modelMapper.map(car, CarExportDto.class);

            PartExportRootDto partRootDto = new PartExportRootDto();
            List<PartExportDto> partExportDtos = new ArrayList<>();

            for (Parts part : car.getParts()) {
                PartExportDto partDto = this.modelMapper.map(part, PartExportDto.class);
                partExportDtos.add(partDto);
            }
            partRootDto.setParts(partExportDtos);
            carExportDto.setParts(partRootDto);
            carExportDtos.add(carExportDto);
        }
        carRootDto.setCars(carExportDtos);
        this.xmlParser.exportXml(carRootDto, CarExportRootDto.class, "src/main/resources/XMLs/export/cars-and-parts.xml");
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
