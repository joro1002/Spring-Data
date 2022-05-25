package com.example.xmlprocessing.repositories;

import com.example.xmlprocessing.entities.Parts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository <Parts, Long>{

}
