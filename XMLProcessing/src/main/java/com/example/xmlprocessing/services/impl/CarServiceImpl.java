package softuni.jsonprocessing.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.jsonprocessing.dtos.CarSeedDto;
import softuni.jsonprocessing.dtos.CarsWithPartsViewDto;
import softuni.jsonprocessing.dtos.MakeToyotaDto;
import softuni.jsonprocessing.dtos.PartsViewDto;
import softuni.jsonprocessing.entities.Cars;
import softuni.jsonprocessing.entities.Parts;
import softuni.jsonprocessing.repositories.CarRepository;
import softuni.jsonprocessing.repositories.PartRepository;
import softuni.jsonprocessing.services.CarService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository, ModelMapper modelMapper, Gson gson) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedCars() throws Exception {
        String content = String.join("", Files.readAllLines(Path.of("src/main/resources/jsons/cars.json")));

        CarSeedDto[] carSeedDtos = this.gson.fromJson(content, CarSeedDto[].class);

        for (CarSeedDto carSeedDto : carSeedDtos) {
            Cars map = this.modelMapper.map(carSeedDto, Cars.class);
            map.setParts(getRandomParts());
            this.carRepository.saveAndFlush(map);
        }
    }

    @Override
    public String printMakeToyota() {
        Set<Cars> toyota = this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota");

        List<MakeToyotaDto> allToyota = new ArrayList<>();

        for (Cars car : toyota) {
            MakeToyotaDto map = this.modelMapper.map(car, MakeToyotaDto.class);
            allToyota.add(map);
        }
        return this.gson.toJson(allToyota);
    }

    @Override
    public String findAllCarsWithParts() {
        Set<Cars> allBy = this.carRepository.findAllBy();

        List<CarsWithPartsViewDto> result = new ArrayList<>();

        for (Cars car : allBy) {
            CarsWithPartsViewDto map = this.modelMapper.map(car, CarsWithPartsViewDto.class);

            Set<PartsViewDto> partsViewDtos = new HashSet<>();
            for (Parts part : car.getParts()) {
                partsViewDtos.add(this.modelMapper.map(part, PartsViewDto.class));
            }
            map.setParts(partsViewDtos);
            result.add(map);
        }
        return this.gson.toJson(result);
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
