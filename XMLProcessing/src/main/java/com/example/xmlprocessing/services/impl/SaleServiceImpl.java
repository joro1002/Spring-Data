package com.example.xmlprocessing.services.impl;

import com.example.xmlprocessing.entities.Cars;
import com.example.xmlprocessing.entities.Customers;
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
