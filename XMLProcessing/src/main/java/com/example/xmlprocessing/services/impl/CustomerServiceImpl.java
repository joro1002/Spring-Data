package com.example.xmlprocessing.services.impl;

import com.example.xmlprocessing.repositories.CustomerRepository;
import com.example.xmlprocessing.services.CustomerService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


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

    }

    @Override
    public String findAllCustomerWithMoreOneSale() {
    return "s";

    }
}
