package com.tiapn.Recanto_API.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiapn.Recanto_API.classes.Adocao;
import com.tiapn.Recanto_API.classes.Animal;

public interface AdocaoRepository extends JpaRepository<Adocao, String> {

}
