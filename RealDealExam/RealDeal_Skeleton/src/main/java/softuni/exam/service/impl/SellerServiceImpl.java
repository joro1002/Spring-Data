package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.SellerImportDto;
import softuni.exam.models.dtos.SellerImportRootDto;
import softuni.exam.models.entities.Rating;
import softuni.exam.models.entities.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public SellerServiceImpl(SellerRepository sellerRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.sellerRepository = sellerRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return String.join("", Files.readAllLines(Path.of("src/main/resources/files/xml/sellers.xml")));
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        StringBuilder builder = new StringBuilder();

        SellerImportRootDto sellerImportRootDto = this.xmlParser.parseXml(SellerImportRootDto.class, "src/main/resources/files/xml/sellers.xml");

        for (SellerImportDto seller : sellerImportRootDto.getSellers()) {
            Rating rating;
            try {
                rating = Rating.valueOf(seller.getRating());
            }catch (Exception e){
                builder.append("Invalid seller")
                        .append(System.lineSeparator());
                continue;
            }

            Optional<Seller> byEmail = this.sellerRepository.findByEmail(seller.getEmail());

            if (this.validationUtil.isValid(seller) && byEmail.isEmpty()){
                Seller map = this.modelMapper.map(seller, Seller.class);
                map.setRating(rating);
                this.sellerRepository.saveAndFlush(map);

                builder.append(String.format("Successfully import seller %s - %s", map.getLastName(), map.getEmail()))
                        .append(System.lineSeparator());
            }else {
                builder.append("Invalid seller")
                        .append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}
