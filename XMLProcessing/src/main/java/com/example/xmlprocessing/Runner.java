package com.example.xmlprocessing;

import com.example.xmlprocessing.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class Runner implements CommandLineRunner {

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    @Autowired
    public Runner(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
       // this.supplierService.seedSupplier();
       // this.partService.seedPart();
      //  this.carService.seedCars();
        //this.customerService.seedCustomer();
        //this.saleService.seedSale();
        //this.customerService.exportOrdered();
        //this.carService.makeToyota();
        this.carService.carsAndParts();
    }
}
