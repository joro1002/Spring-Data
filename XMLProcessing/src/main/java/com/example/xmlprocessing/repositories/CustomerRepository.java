package com.example.xmlprocessing.repositories;

import com.example.xmlprocessing.entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {
    @Query("SELECT c FROM Customers c WHERE c.sales.size > 0")
    Set<Customers> findAllCustomerWithMoreThanOneSale();

    @Query("SELECT c FROM Customers c ORDER BY c.birthDate, c.youngDriver DESC")
    Set<Customers> findAllAndSort();
}
