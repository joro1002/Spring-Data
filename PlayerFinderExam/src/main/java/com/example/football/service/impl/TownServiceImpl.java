package com.example.football.service.impl;

import com.example.football.models.dto.TownImportDto;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of("src/main/resources/files/json/towns.json")));
    }

    @Override
    public String importTowns() throws IOException {
        StringBuilder builder = new StringBuilder();

        TownImportDto[] townImportDtos = this.gson.fromJson(readTownsFileContent(), TownImportDto[].class);

        for (TownImportDto townImportDto : townImportDtos) {
            if (this.validationUtil.isValid(townImportDto)){
                Town map = this.modelMapper.map(townImportDto, Town.class);
                this.townRepository.saveAndFlush(map);

                builder.append(String.format("Successfully imported Town %s - %d", map.getName(), map.getPopulation()))
                        .append(System.lineSeparator());
            }else {
                builder.append("Invalid Town")
                        .append(System.lineSeparator());
            }
        }

        return builder.toString();
    }

    @Override
    public Town getTownByName(String name) {
        return this.townRepository.findFirstByName(name);
    }
}
