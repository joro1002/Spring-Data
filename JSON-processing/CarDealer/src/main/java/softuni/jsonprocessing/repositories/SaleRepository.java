package softuni.jsonprocessing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.jsonprocessing.entities.Sales;

@Repository
public interface SaleRepository extends JpaRepository<Sales, Long> {
}
