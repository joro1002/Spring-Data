package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.OfferImportDto;
import softuni.exam.models.dtos.OfferImportRootDto;
import softuni.exam.models.entities.Car;
import softuni.exam.models.entities.Offer;
import softuni.exam.models.entities.Seller;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private final CarRepository carRepository;
    private final SellerRepository sellerRepository;

    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser, CarRepository carRepository, SellerRepository sellerRepository) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.carRepository = carRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of("src/main/resources/files/xml/offers.xml")));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder builder = new StringBuilder();

        OfferImportRootDto offerImportRootDto = this.xmlParser.parseXml(OfferImportRootDto.class, "src/main/resources/files/xml/offers.xml");

        for (OfferImportDto offer : offerImportRootDto.getOffers()) {
            if (this.validationUtil.isValid(offer)){
                Offer map = this.modelMapper.map(offer, Offer.class);

                Car car = this.carRepository.findById(offer.getCar().getId()).get();
                Seller seller = this.sellerRepository.findById(offer.getSeller().getId()).get();

                map.setPictures(new HashSet<>(car.getPictures()));
                map.setCar(car);
                map.setSeller(seller);
                this.offerRepository.saveAndFlush(map);

                builder.append(String.format("Successfully import offer %s - %s", map.getAddedOn(), map.isHasGoldStatus()))
                        .append(System.lineSeparator());

            }else {
                builder.append("Invalid offer")
                        .append(System.lineSeparator());
            }
        }

        return builder.toString();
    }
}
