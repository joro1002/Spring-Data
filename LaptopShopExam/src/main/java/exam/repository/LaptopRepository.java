package exam.repository;

import exam.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Integer> {
    Optional<Laptop> findByMacAddress(String name);

    @Query("SELECT l FROM Laptop l ORDER BY l.cpuSpeed DESC, l.ram DESC, l.storage DESC, l.macAddress")
    Set<Laptop> bestLaptops();
}
