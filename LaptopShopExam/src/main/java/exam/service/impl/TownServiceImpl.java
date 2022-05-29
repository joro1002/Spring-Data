package exam.service.impl;

import exam.model.Town;
import exam.model.dtos.TownImportXmlDto;
import exam.model.dtos.TownImportXmlRootDto;
import exam.repository.TownRepository;
import exam.service.TownService;
import exam.util.ValidationUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of("src/main/resources/files/xml/towns.xml")));
    }

    @Override
    public String importTowns() throws JAXBException, FileNotFoundException {
        StringBuilder builder = new StringBuilder();
        TownImportXmlRootDto townImportXmlRootDto = this.xmlParser.parseXml(TownImportXmlRootDto.class, "src/main/resources/files/xml/towns.xml");

        for (TownImportXmlDto town : townImportXmlRootDto.getTowns()) {
          // Optional<Town> byName = this.townRepository.findByName(town.getName());
            if (this.validationUtil.isValid(town)){
                this.townRepository.saveAndFlush(this.modelMapper.map(town, Town.class));
                builder.append(String.format("Successfully imported Town %s", town.getName()))
                        .append(System.lineSeparator());
            }else {
                builder.append("Invalid town")
                        .append(System.lineSeparator());
            }
        }

        return builder.toString();
    }
}
