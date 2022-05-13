package softuni.jsonprocessing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.jsonprocessing.entities.Suppliers;

import java.util.Set;
import java.util.function.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Suppliers, Long> {
    Set<Suppliers> findAllByImporterIsFalse();
}
