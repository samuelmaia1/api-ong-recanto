package com.tiapn.Recanto_API.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiapn.Recanto_API.classes.Adocao;
import com.tiapn.Recanto_API.classes.Relatorio;

public interface RelatorioRepository extends JpaRepository<Relatorio, String>{

}
