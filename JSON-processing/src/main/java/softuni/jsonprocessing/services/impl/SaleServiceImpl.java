package softuni.jsonprocessing.services.impl;

import org.springframework.stereotype.Service;
import softuni.jsonprocessing.entities.Cars;
import softuni.jsonprocessing.entities.Customers;
import softuni.jsonprocessing.entities.Sales;
import softuni.jsonprocessing.repositories.CarRepository;
import softuni.jsonprocessing.repositories.CustomerRepository;
import softuni.jsonprocessing.repositories.SaleRepository;
import softuni.jsonprocessing.services.SaleService;

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
        this.saleRepository.saveAndFlush(sale);

        Sales sale1 = new Sales();
        sale1.setCar(getRandomCar());
        sale1.setCustomer(getRandomCustomer());
        sale1.setDiscount(8);
        this.saleRepository.saveAndFlush(sale1);

        Sales sale2 = new Sales();
        sale2.setCar(getRandomCar());
        sale2.setCustomer(getRandomCustomer());
        sale2.setDiscount(12);
        this.saleRepository.saveAndFlush(sale2);

        Sales sale3 = new Sales();
        sale3.setCar(getRandomCar());
        sale3.setCustomer(getRandomCustomer());
        sale3.setDiscount(123);
        this.saleRepository.saveAndFlush(sale3);

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
