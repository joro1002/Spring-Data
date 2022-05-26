package com.example.xmlprocessing.repositories;

import com.example.xmlprocessing.entities.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository<Cars, Long> {
    Set<Cars> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);

    Set<Cars> findAllBy();

}
