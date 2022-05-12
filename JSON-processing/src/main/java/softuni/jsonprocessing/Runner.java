package softuni.jsonprocessing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.jsonprocessing.services.PartService;
import softuni.jsonprocessing.services.SupplierService;

@Component
public class Runner implements CommandLineRunner {

    private final SupplierService supplierService;
    private final PartService partService;

    @Autowired
    public Runner(SupplierService supplierService, PartService partService) {
        this.supplierService = supplierService;
        this.partService = partService;
    }

    @Override
    public void run(String... args) throws Exception {
        //supplierService.seedSupplier();
        partService.seedPart();
    }
}
