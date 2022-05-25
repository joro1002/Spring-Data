package com.example.xmlprocessing.repositories;

import com.example.xmlprocessing.entities.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.function.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Suppliers, Long> {
    Set<Suppliers> findAllByImporterIsFalse();
}
