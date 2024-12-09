package com.tiapn.Recanto_API.controllers;

import com.tiapn.Recanto_API.classes.Animal;
import com.tiapn.Recanto_API.classes.Relatorio;
import com.tiapn.Recanto_API.classes.Response;
import com.tiapn.Recanto_API.classes.Voluntario;
import com.tiapn.Recanto_API.dtos.NewVoluntarioDto;
import com.tiapn.Recanto_API.dtos.UpdateVoluntarioDto;
import com.tiapn.Recanto_API.dtos.VoluntarioDto;
import com.tiapn.Recanto_API.repository.AnimalRepository;
import com.tiapn.Recanto_API.repository.VoluntarioRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/voluntarios")
public class VoluntarioController {
    @Autowired
    VoluntarioRepository repository;

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping
    public ResponseEntity<List<VoluntarioDto>> getVoluntarios(){
        return ResponseEntity.ok().body(repository.findAll().stream().map(VoluntarioDto::new).toList());
    }

    @GetMapping("/{voluntarioId}")
    public ResponseEntity<?> getVoluntarioById(@PathVariable String voluntarioId){
        try{
            Optional<Voluntario> voluntario = repository.findById(voluntarioId);
            if (voluntario.isEmpty()) throw new EntityNotFoundException("Voluntário não encontrado");
            return ResponseEntity.ok().body(new VoluntarioDto(voluntario.get()));
        }
        catch (EntityNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), false));
        }
    }

    @PostMapping
    public ResponseEntity<?> registrarVoluntario(@Validated @RequestBody NewVoluntarioDto data){
        Voluntario voluntario = new Voluntario(data);
        voluntario.setSenha(encoder.encode(voluntario.getSenha()));
        Voluntario novoVoluntario = repository.save(voluntario);
        return ResponseEntity.ok().body(novoVoluntario);
    }

    @DeleteMapping("/{voluntarioId}")
    public ResponseEntity<?> deletarVoluntario(@PathVariable String voluntarioId){
        Voluntario voluntario = repository.findById(voluntarioId).orElseThrow(() -> new EntityNotFoundException("Voluntário não encontrado"));

        for (Animal animal : voluntario.getAnimais()){
            animal.setVoluntario(null);
            animal.setStatus("Esperando adoção");
        }

        animalRepository.saveAll(voluntario.getAnimais());

        repository.delete(voluntario);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/permissao/{voluntarioId")
    public ResponseEntity<?> mudarPermissao(@RequestBody UpdateVoluntarioDto dto, @PathVariable String voluntarioId) {
        try {
            Voluntario voluntario = repository.findById(voluntarioId)
                    .orElseThrow(() -> new EntityNotFoundException("Voluntário não encontrado"));

            if (dto.getPermissao() != null)
                voluntario.setPermissao(dto.getPermissao());

            repository.save(voluntario);

            return ResponseEntity.ok(new VoluntarioDto(voluntario));
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Voluntário não encontrado", false));
        }
    }

    @PutMapping("/{voluntarioId}")
    public ResponseEntity<?> updateVoluntario(@RequestBody UpdateVoluntarioDto dto, @PathVariable String voluntarioId) {
        try {
            Voluntario voluntario = repository.findById(voluntarioId)
                    .orElseThrow(() -> new EntityNotFoundException("Voluntário não encontrado"));

            if (dto.getNome() != null) {
                voluntario.setNome(dto.getNome());
            }
            if (dto.getEmail() != null) {
                voluntario.setEmail(dto.getEmail());
            }
            if (dto.getTelefone() != null) {
                voluntario.setTelefone(dto.getTelefone());
            }
            if (dto.getSenha() != null) {
                voluntario.setSenha(encoder.encode(dto.getSenha()));
            }

            repository.save(voluntario);

            return ResponseEntity.ok(new VoluntarioDto(voluntario));
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Voluntário não encontrado", false));
        }
    }
}
