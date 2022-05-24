package softuni.jsonprocessing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.jsonprocessing.entities.Customers;

import java.util.Set;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {
    @Query("SELECT c FROM Customers c WHERE c.sales.size > 0")
    Set<Customers> findAllCustomerWithMoreThanOneSale();
}
