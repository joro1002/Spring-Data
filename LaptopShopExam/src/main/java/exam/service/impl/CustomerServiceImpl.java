package exam.service.impl;

import com.google.gson.Gson;
import exam.model.Customer;
import exam.model.dtos.CustomerImportDto;
import exam.repository.CustomerRepository;
import exam.service.CustomerService;
import exam.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.customerRepository.count() >0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of("src/main/resources/files/json/customers.json")));
    }

    @Override
    public String importCustomers() throws IOException {
        StringBuilder builder = new StringBuilder();

        CustomerImportDto[] customerImportDtos = this.gson.fromJson(readCustomersFileContent(), CustomerImportDto[].class);

        for (CustomerImportDto customerImportDto : customerImportDtos) {
            if (this.validationUtil.isValid(customerImportDto)) {
                Customer map = this.modelMapper.map(customerImportDto, Customer.class);
                this.customerRepository.saveAndFlush(map);

                builder.append(String.format("Successfully imported Customer %s %s - %s", map.getFirstName(), map.getLastName(), map.getEmail()))
                        .append(System.lineSeparator());
            }else {
                builder.append("Invalid Customer")
                        .append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}
