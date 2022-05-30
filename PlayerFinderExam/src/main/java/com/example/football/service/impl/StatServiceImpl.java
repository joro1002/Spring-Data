package com.example.football.service.impl;

import com.example.football.models.dto.StatImportXmlDto;
import com.example.football.models.dto.StatImportXmlRootDto;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class StatServiceImpl implements StatService {
    private final StatRepository statRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    public StatServiceImpl(StatRepository statRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.statRepository = statRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;

        this.xmlParser = xmlParser;
    }


    @Override
    public boolean areImported() {
        return this.statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of("src/main/resources/files/xml/stats.xml")));
    }

    @Override
    public String importStats() throws JAXBException {
        StringBuilder builder = new StringBuilder();

        StatImportXmlRootDto statImportXmlRootDto = this.xmlParser.parseXml(StatImportXmlRootDto.class, "src/main/resources/files/xml/stats.xml");

        for (StatImportXmlDto stat : statImportXmlRootDto.getStats()) {
            if (this.validationUtil.isValid(stat)) {
                Stat map = this.modelMapper.map(stat, Stat.class);
                this.statRepository.saveAndFlush(map);

                builder.append(String.format("Successfully imported Stat %.2f - %.2f - %.2f", stat.getShooting(), stat.getPassing(), stat.getEndurance()))
                        .append(System.lineSeparator());
            }else {
                builder.append("Invalid Stat")
                        .append(System.lineSeparator());
            }
        }

        return builder.toString();
    }

    @Override
    public Stat getById(int id) {
        return this.statRepository.findById(id).orElse(null);
    }
}
