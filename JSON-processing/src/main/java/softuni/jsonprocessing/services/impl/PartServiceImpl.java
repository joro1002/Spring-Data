package softuni.jsonprocessing.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.jsonprocessing.dtos.PartSeedDto;
import softuni.jsonprocessing.entities.Parts;
import softuni.jsonprocessing.entities.Suppliers;
import softuni.jsonprocessing.repositories.PartRepository;
import softuni.jsonprocessing.repositories.SupplierRepository;
import softuni.jsonprocessing.services.PartService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Random;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository, ModelMapper modelMapper, Gson gson) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedPart() throws Exception {
        String content = String.join("", Files.readAllLines(Path.of("src/main/resources/jsons/parts.json")));

        PartSeedDto[] partSeedDtos = this.gson.fromJson(content, PartSeedDto[].class);

        for (PartSeedDto partSeedDto : partSeedDtos) {
            Parts map = this.modelMapper.map(partSeedDto, Parts.class);
            map.setSuppliers(getRandom());
            this.partRepository.saveAndFlush(map);
        }

    }

    private Suppliers getRandom() throws Exception {
        Random random = new Random();

        long index = (long) random.nextInt((int) this.supplierRepository.count()) + 1;
        Optional<Suppliers> byId = this.supplierRepository.findById(index);

        if (byId.isPresent()){
            return byId.get();
        }else {
            throw new Exception("Supplier don't exist");
        }
    }
}
