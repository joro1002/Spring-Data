package com.example.xmlprocessing.services;

import java.io.IOException;

public interface CustomerService {
    void seedCustomer() throws IOException;

    String findAllCustomerWithMoreOneSale();
}
