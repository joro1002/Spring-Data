package softuni.jsonprocessing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.jsonprocessing.services.SupplierService;

@Component
public class Runner implements CommandLineRunner {

    private final SupplierService supplierService;

    @Autowired
    public Runner(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public void run(String... args) throws Exception {
        supplierService.seedSupplier();
    }
}
