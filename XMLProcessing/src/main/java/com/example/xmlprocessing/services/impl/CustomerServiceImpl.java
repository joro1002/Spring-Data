package softuni.jsonprocessing.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.jsonprocessing.dtos.CustomerSeedDto;
import softuni.jsonprocessing.dtos.CustomerWithPurchaseDto;
import softuni.jsonprocessing.entities.Customers;
import softuni.jsonprocessing.entities.Parts;
import softuni.jsonprocessing.entities.Sales;
import softuni.jsonprocessing.repositories.CustomerRepository;
import softuni.jsonprocessing.services.CustomerService;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, Gson gson, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCustomer() throws IOException {
        String content = String.join("", Files.readAllLines(Path.of("src/main/resources/jsons/customers.json")));

        CustomerSeedDto[] customerSeedDtos = this.gson.fromJson(content, CustomerSeedDto[].class);

        for (CustomerSeedDto customerSeedDto : customerSeedDtos) {
            Customers map = this.modelMapper.map(customerSeedDto, Customers.class);

            this.customerRepository.saveAndFlush(map);
        }
    }

    @Override
    public String findAllCustomerWithMoreOneSale() {
        Set<Customers> allCustomerWithMoreThanOneSale = this.customerRepository.findAllCustomerWithMoreThanOneSale();

        List<CustomerWithPurchaseDto> result = new ArrayList<>();

        for (Customers customer : allCustomerWithMoreThanOneSale) {
            CustomerWithPurchaseDto map = this.modelMapper.map(customer, CustomerWithPurchaseDto.class);
            map.setBoughtCars(customer.getSales().size());

            Set<Sales> sales = customer.getSales();
            BigDecimal spentMoney = new BigDecimal("0");

            for (Sales sale : sales) {
                List<BigDecimal> prices = sale.getCar().getParts()
                        .stream()
                        .map(Parts::getPrice)
                        .collect(Collectors.toList());

                for (BigDecimal price : prices) {
                    spentMoney = spentMoney.add(price);
                }
            }

            map.setSpentMoney(spentMoney);
            result.add(map);

        }
        return this.gson.toJson(result);

    }
}
