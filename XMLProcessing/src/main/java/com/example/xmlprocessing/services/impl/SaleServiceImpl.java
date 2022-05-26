package com.example.xmlprocessing.services.impl;

import com.example.xmlprocessing.entities.Cars;
import com.example.xmlprocessing.entities.Customers;
import com.example.xmlprocessing.entities.Sales;
import com.example.xmlprocessing.repositories.CarRepository;
import com.example.xmlprocessing.repositories.CustomerRepository;
import com.example.xmlprocessing.repositories.SaleRepository;
import com.example.xmlprocessing.services.SaleService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void seedSale() {
        Sales sale = new Sales();
        sale.setCar(getRandomCar());
        sale.setCustomer(getRandomCustomer());
        sale.setDiscount(5);

        Sales sale1 = new Sales();
        sale1.setCar(getRandomCar());
        sale1.setCustomer(getRandomCustomer());
        sale1.setDiscount(10);

        Sales sale2 = new Sales();
        sale2.setCar(getRandomCar());
        sale2.setCustomer(getRandomCustomer());
        sale2.setDiscount(15);

        this.saleRepository.saveAndFlush(sale);
        this.saleRepository.saveAndFlush(sale1);
        this.saleRepository.saveAndFlush(sale2);
    }

    private Customers getRandomCustomer() {
        Random random = new Random();
        long index = (long) random.nextInt((int) this.customerRepository.count()) + 1;
        return this.customerRepository.findById(index).get();

    }

    private Cars getRandomCar() {
        Random random = new Random();
        long index = (long) random.nextInt((int) this.carRepository.count()) + 1;
        return this.carRepository.findById(index).get();
    }
}
