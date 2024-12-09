package com.tiapn.Recanto_API.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiapn.Recanto_API.classes.Animal;
import com.tiapn.Recanto_API.classes.Voluntario;

public interface VoluntarioRepository extends JpaRepository<Voluntario, String > {

}
