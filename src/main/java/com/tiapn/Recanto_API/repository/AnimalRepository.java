package com.tiapn.Recanto_API.repository;

import com.tiapn.Recanto_API.classes.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, String> {

}
