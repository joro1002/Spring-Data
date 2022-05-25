package com.example.xmlprocessing.repositories;

import com.example.xmlprocessing.entities.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SaleRepository extends JpaRepository<Sales, Long> {
}
