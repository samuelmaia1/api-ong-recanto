package com.tiapn.Recanto_API.controllers;

import com.tiapn.Recanto_API.classes.Relatorio;
import com.tiapn.Recanto_API.dtos.RelatorioDto;
import com.tiapn.Recanto_API.repository.RelatorioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {
    @Autowired
    RelatorioRepository relatorioRepository;

    @GetMapping
    public ResponseEntity<List<RelatorioDto>> getRelatorios(){
        return ResponseEntity.ok().body(relatorioRepository.findAll().stream().map(RelatorioDto::new).toList());
    }

    @GetMapping("/{relatorioId}")
    public ResponseEntity<RelatorioDto> getRelatorioById(@PathVariable String relatorioId){
        return ResponseEntity.ok().body(new RelatorioDto(relatorioRepository.findById(relatorioId).orElseThrow(() -> new EntityNotFoundException("Relatório não encontrado"))));
    }

    @DeleteMapping("/{relatorioId}")
    public ResponseEntity<Void> deleterelatorio(@PathVariable String relatorioId) {
        if (!relatorioRepository.findById(relatorioId).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        relatorioRepository.delete(relatorioRepository.findById(relatorioId).get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{relatorioId}")
    public ResponseEntity<RelatorioDto> updateRelatorio(@PathVariable String relatorioId, @RequestBody RelatorioDto relatorioDto) {
        Relatorio relatorio = relatorioRepository.findById(relatorioId)
                .orElseThrow(() -> new RuntimeException("Relatório não encontrado"));

        relatorio.setObservacao(relatorioDto.getObservacao());

        relatorioRepository.save(relatorio);

        return ResponseEntity.ok(new RelatorioDto(relatorio));
    }
}
