package com.example.xmlprocessing.services.impl;

import com.example.xmlprocessing.dtos.CustomerImportDto;
import com.example.xmlprocessing.dtos.CustomerImportRootDto;
import com.example.xmlprocessing.dtos.export.CustomerOrderExportDto;
import com.example.xmlprocessing.dtos.export.CustomerOrderExportRootDto;
import com.example.xmlprocessing.entities.Customers;
import com.example.xmlprocessing.repositories.CustomerRepository;
import com.example.xmlprocessing.services.CustomerService;
import com.example.xmlprocessing.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, XmlParser xmlParser) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedCustomer() throws IOException, JAXBException {
        CustomerImportRootDto customerImportRootDto = this.xmlParser.parseXml(CustomerImportRootDto.class, "src/main/resources/XMLs/customers.xml");
        for (CustomerImportDto customer : customerImportRootDto.getCustomers()) {
            this.customerRepository.saveAndFlush(this.modelMapper.map(customer, Customers.class));
        }
    }

    @Override
    public String findAllCustomerWithMoreOneSale() {
    return "s";

    }

    @Override
    public void exportOrdered() throws JAXBException {
        List<CustomerOrderExportDto> customerOrderExportDtos = this.customerRepository.findAllAndSort()
                .stream()
                .map(c -> this.modelMapper.map(c, CustomerOrderExportDto.class)).toList();

        CustomerOrderExportRootDto rootDto = new CustomerOrderExportRootDto();
        rootDto.setCustomers(customerOrderExportDtos);

        this.xmlParser.exportXml(rootDto, CustomerOrderExportRootDto.class, "src/main/resources/XMLs/export/order-customer.xml");


    }
}
