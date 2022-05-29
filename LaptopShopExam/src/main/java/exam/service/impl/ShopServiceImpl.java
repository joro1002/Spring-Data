package exam.service.impl;

import exam.model.Shop;
import exam.model.dtos.ShopImportXmlDto;
import exam.model.dtos.ShopImportXmlRootDto;
import exam.repository.ShopRepository;
import exam.repository.TownRepository;
import exam.service.ShopService;
import exam.util.ValidationUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private final TownRepository townRepository;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser, TownRepository townRepository) {
        this.shopRepository = shopRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return this.shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of("src/main/resources/files/xml/shops.xml")));
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {
        StringBuilder builder = new StringBuilder();

        ShopImportXmlRootDto shopImportXmlRootDto = this.xmlParser.parseXml(ShopImportXmlRootDto.class, "src/main/resources/files/xml/shops.xml");

        for (ShopImportXmlDto shop : shopImportXmlRootDto.getShops()) {
            Optional<Shop> byName = this.shopRepository.findByName(shop.getName());
            if (this.validationUtil.isValid(shop) && byName.isEmpty()) {
                Shop map = this.modelMapper.map(shop, Shop.class);
                map.setTown(this.townRepository.findByName(shop.getName()));
                this.shopRepository.saveAndFlush(map);

                builder.append(String.format("Successfully imported Shop %s - %s", shop.getName(), shop.getIncome()))
                        .append(System.lineSeparator());
            }else {
                builder.append("Invalid shop")
                        .append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}
