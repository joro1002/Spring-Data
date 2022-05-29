package exam.service.impl;

import com.google.gson.Gson;
import exam.model.Laptop;
import exam.model.WarrantyType;
import exam.model.dtos.LaptopImportDto;
import exam.repository.LaptopRepository;
import exam.repository.ShopRepository;
import exam.repository.TownRepository;
import exam.service.LaptopService;
import exam.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;

@Service
public class LaptopServiceImpl implements LaptopService {
    private final LaptopRepository laptopRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final ShopRepository shopRepository;
    private final TownRepository townRepository;

    @Autowired
    public LaptopServiceImpl(LaptopRepository laptopRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, ShopRepository shopRepository, TownRepository townRepository) {
        this.laptopRepository = laptopRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.shopRepository = shopRepository;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return this.laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of("src/main/resources/files/json/laptops.json")));
    }

    @Override
    public String importLaptops() throws IOException {
        StringBuilder builder = new StringBuilder();

        System.out.println();
        LaptopImportDto[] laptopImportDtos = this.gson.fromJson(this.readLaptopsFileContent(), LaptopImportDto[].class);

        for (LaptopImportDto laptopImportDto : laptopImportDtos) {
            WarrantyType warrantyType;
            try {
                warrantyType = WarrantyType.valueOf(laptopImportDto.getWarrantyType());
            }catch (Exception e){
                builder.append("Invalid Laptop")
                        .append(System.lineSeparator());
                continue;
            }

            Optional<Laptop> byMacAddress = this.laptopRepository.findByMacAddress(laptopImportDto.getMacAddress());
            if (this.validationUtil.isValid(laptopImportDto) && byMacAddress.isEmpty()){
                Laptop map = this.modelMapper.map(laptopImportDto, Laptop.class);
                map.setWarrantyType(warrantyType);
                this.laptopRepository.saveAndFlush(map);

                builder.append(String.format("Successfully imported Laptop %s - %f - %d - %d", map.getMacAddress(),
                        map.getCpuSpeed(), map.getRam(), map.getStorage()))
                        .append(System.lineSeparator());
            }else {
                builder.append("Invalid Laptop")
                        .append(System.lineSeparator());
            }
        }
        return builder.toString();
    }

    @Override
    public String exportBestLaptops() {
        StringBuilder builder = new StringBuilder();
        Set<Laptop> laptops = this.laptopRepository.bestLaptops();

        for (Laptop laptop : laptops) {
            builder.append(String.format("Laptop - %s", laptop.getMacAddress())).append(System.lineSeparator());
            builder.append(String.format("*Cpu speed - %.2f", laptop.getCpuSpeed())).append(System.lineSeparator());
            builder.append(String.format("**Ram - %d", laptop.getRam())).append(System.lineSeparator());
            builder.append(String.format("***Storage - %d", laptop.getStorage())).append(System.lineSeparator());
            builder.append(String.format("****Price - %.2f", laptop.getPrice())).append(System.lineSeparator());
            builder.append(String.format("#Shop name - %s", shopRepository.findByName(laptop.getMacAddress())))
                    .append(System.lineSeparator());
            builder.append(String.format("##Town - %s", townRepository.findByName(laptop.getMacAddress())))
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }
}
