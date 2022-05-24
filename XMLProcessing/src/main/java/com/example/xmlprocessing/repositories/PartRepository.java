package softuni.jsonprocessing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.jsonprocessing.entities.Parts;

@Repository
public interface PartRepository extends JpaRepository <Parts, Long>{

}
