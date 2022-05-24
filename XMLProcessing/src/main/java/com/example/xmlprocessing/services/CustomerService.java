package softuni.jsonprocessing.services;

import java.io.IOException;

public interface CustomerService {
    void seedCustomer() throws IOException;

    String findAllCustomerWithMoreOneSale();
}
