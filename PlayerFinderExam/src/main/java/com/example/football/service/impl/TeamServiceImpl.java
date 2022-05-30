package com.example.football.service.impl;

import com.example.football.models.dto.TeamImportDto;
import com.example.football.models.entity.Team;
import com.example.football.repository.TeamRepository;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final TownService townService;

    public TeamServiceImpl(TeamRepository teamRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, TownService townService) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.townService = townService;
    }


    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of("src/main/resources/files/json/teams.json")));
    }

    @Override
    public String importTeams() throws IOException {
        StringBuilder builder = new StringBuilder();

        TeamImportDto[] teamImportDtos = this.gson.fromJson(readTeamsFileContent(), TeamImportDto[].class);

        for (TeamImportDto teamImportDto : teamImportDtos) {
            if (this.validationUtil.isValid(teamImportDto)){
                Team map = this.modelMapper.map(teamImportDto, Team.class);
                map.setTown(this.townService.getTownByName(teamImportDto.getName()));
                this.teamRepository.saveAndFlush(map);

                builder.append(String.format("Successfully imported Team %s - %d", map.getName(), map.getFanBase()))
                        .append(System.lineSeparator());
            }else {
                builder.append("Invalid Team")
                        .append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}
