package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CitySeedDto;
import softuni.exam.models.entity.City;
import softuni.exam.repository.CityRepository;
import softuni.exam.service.CityService;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final CountryService countryService;

    public CityServiceImpl(CityRepository cityRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, CountryService countryService) {
        this.cityRepository = cityRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;

        this.countryService = countryService;
    }

    @Override
    public boolean areImported() {
        return this.cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of("src/main/resources/files/json/cities.json")));
    }

    @Override
    public String importCities() throws IOException {
        StringBuilder builder = new StringBuilder();

        CitySeedDto[] citySeedDtos = this.gson.fromJson(this.readCitiesFileContent(), CitySeedDto[].class);

        for (CitySeedDto citySeedDto : citySeedDtos) {
            Optional<City> byCityName = this.cityRepository.findByCityName(citySeedDto.getCityName());
            if (this.validationUtil.isValid(citySeedDto)&& byCityName.isEmpty()) {
                City map = this.modelMapper.map(citySeedDto, City.class);
                map.setCountry(this.countryService.findById(citySeedDto.getCountry()));
                this.cityRepository.saveAndFlush(map);

                builder.append(String.format("Successfully imported city %s - %d", map.getCityName(), map.getPopulation()))
                        .append(System.lineSeparator());
            }else {
                builder.append("Invalid city")
                        .append(System.lineSeparator());
            }
        }
        return builder.toString();
    }

    @Override
    public City findById(int city) {
        return this.cityRepository.findById(city).orElse(null);
    }
}
