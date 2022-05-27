package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountryImportDto;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return String.join("", Files.readAllLines(Path.of("src/main/resources/files/json/countries.json")));
    }

    @Override
    public String importCountries() throws IOException {
        StringBuilder builder = new StringBuilder();

        CountryImportDto[] countryImportDtos = this.gson.fromJson(this.readCountriesFromFile(), CountryImportDto[].class);

        for (CountryImportDto countryImportDto : countryImportDtos) {
            Optional<Country> byCountryName = this.countryRepository.findByCountryName(countryImportDto.getCountryName());
            if (this.validationUtil.isValid(countryImportDto)&& byCountryName.isEmpty()) {
                Country map = this.modelMapper.map(countryImportDto, Country.class);
                this.countryRepository.saveAndFlush(map);

                builder.append(String.format("Successfully imported country %s - %s", countryImportDto.getCountryName(), countryImportDto.getCurrency()))
                        .append(System.lineSeparator());
            }else {
                builder.append("Invalid country")
                        .append(System.lineSeparator());
            }
        }
        return builder.toString();
    }

    @Override
    public Country findById(int country) {
        return this.countryRepository.findById(country).orElse(null);
    }
}
