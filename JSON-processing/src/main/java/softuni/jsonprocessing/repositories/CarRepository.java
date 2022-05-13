package softuni.jsonprocessing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.jsonprocessing.entities.Cars;

import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository<Cars, Long> {
    Set<Cars> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);

    Set<Cars> findAllBy();
}
