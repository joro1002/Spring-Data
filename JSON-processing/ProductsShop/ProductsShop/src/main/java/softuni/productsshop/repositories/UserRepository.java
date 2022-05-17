package softuni.productsshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.productsshop.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
}
