package com.example.football.service.impl;

import com.example.football.models.dto.PlayerImportXmlDto;
import com.example.football.models.dto.PlayersImportXmlRootDto;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.PositionEnum;
import com.example.football.repository.PlayerRepository;
import com.example.football.service.PlayerService;
import com.example.football.service.StatService;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Set;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private final TownService townService;
    private final TeamService teamService;
    private final StatService statService;

    public PlayerServiceImpl(PlayerRepository playerRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser, TownService townService, TeamService teamService, StatService statService) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.townService = townService;
        this.teamService = teamService;
        this.statService = statService;
    }


    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of("src/main/resources/files/xml/players.xml")));
    }

    @Override
    public String importPlayers() throws JAXBException {
        StringBuilder builder = new StringBuilder();

        PlayersImportXmlRootDto playersImportXmlRootDto = this.xmlParser.parseXml(PlayersImportXmlRootDto.class, "src/main/resources/files/xml/players.xml");

        for (PlayerImportXmlDto player : playersImportXmlRootDto.getPlayers()) {
            PositionEnum positionEnum;
            try {
                positionEnum = PositionEnum.valueOf(player.getPosition());
            } catch (Exception e) {
                builder.append("Invalid Player")
                        .append(System.lineSeparator());
                continue;
            }
            if (this.validationUtil.isValid(player)) {
                Player map = this.modelMapper.map(player, Player.class);
                map.setTown(this.townService.getTownByName(player.getTown().getName()));
                map.setTeam(this.teamService.getByName(player.getTeam().getName()));
                map.setStat(this.statService.getById(player.getStat().getId()));
                map.setPosition(positionEnum);

                this.playerRepository.saveAndFlush(map);

                builder.append(String.format("Successfully imported Player %s %s - %s", player.getFirstName(),
                                player.getLastName(), player.getPosition()))
                        .append(System.lineSeparator());
            } else {
                builder.append("Invalid Player")
                        .append(System.lineSeparator());
            }
        }

        return builder.toString();
    }

    @Override
    public String exportBestPlayers() {
        StringBuilder builder = new StringBuilder();

        Set<Player> players = this.playerRepository.bestPlayers(LocalDate.of(1995, 1, 1), LocalDate.of(2003, 1, 1));

        for (Player player : players) {
            builder.append(String.format("Player - %s %s", player.getFirstName(), player.getLastName())).append(System.lineSeparator());
            builder.append(String.format("\tPosition - %s", player.getPosition())).append(System.lineSeparator());
            builder.append(String.format("\tTeam - %s", player.getTeam().getName())).append(System.lineSeparator());
            builder.append(String.format("\tStadium - %s", player.getTeam().getStadiumName())).append(System.lineSeparator());
        }
        return builder.toString();
    }
}

