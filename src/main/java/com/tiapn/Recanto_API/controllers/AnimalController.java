package com.tiapn.Recanto_API.controllers;

import com.tiapn.Recanto_API.classes.Animal;
import com.tiapn.Recanto_API.classes.Relatorio;
import com.tiapn.Recanto_API.classes.Response;
import com.tiapn.Recanto_API.classes.Voluntario;
import com.tiapn.Recanto_API.dtos.*;
import com.tiapn.Recanto_API.repository.AnimalRepository;
import com.tiapn.Recanto_API.repository.RelatorioRepository;
import com.tiapn.Recanto_API.repository.VoluntarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/animais")
public class AnimalController {
    @Autowired
    EntityManager entityManager;

    @Autowired
    private AnimalRepository repository;

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    @Autowired
    private RelatorioRepository relatorioRepository;

    @GetMapping
    public ResponseEntity<List<AnimalDto>> getAnimais(){
        List<Animal> animals = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(animals.stream().map(AnimalDto::new).toList());
    }

    @GetMapping("/{animalId}")
    public ResponseEntity<AnimalDto> getAnimalById(@PathVariable String animalId){
        try{
            Optional<Animal> animal = repository.findById(animalId);
            if (animal.isPresent())
                return ResponseEntity.status(HttpStatus.OK).body(new AnimalDto(animal.get()));
            throw new EntityNotFoundException("Animal não encontrado");
        }
        catch (EntityNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @Transactional
    @PostMapping("/{voluntarioId}")
    public ResponseEntity<?> registerAnimal(@RequestBody @Validated NewAnimalDto data, @PathVariable String voluntarioId){
        try{
            Voluntario voluntario = getVoluntario(voluntarioId);
            Animal novoAnimal = repository.save(new Animal(data, voluntario));
            return ResponseEntity.status(HttpStatus.CREATED).body(new AnimalDto(novoAnimal));
        }
        catch (EntityNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PutMapping("/{animalId}")
    public ResponseEntity<?> updateAnimal(@RequestBody AnimalUpdateDto dto, @PathVariable String animalId) {
        try {
            Animal animal = repository.findById(animalId)
                    .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado"));

            if (dto.nome() != null) {
                animal.setNome(dto.nome());
            }
            if (dto.especie() != null) {
                animal.setEspecie(dto.especie());
            }
            if (dto.raca() != null) {
                animal.setRaca(dto.raca());
            }
            if (dto.idade() != null) {
                animal.setIdade(dto.idade());
            }
            if (dto.sexo() != null) {
                animal.setSexo(dto.sexo());
            }
            if (dto.peso() != null) {
                animal.setPeso(dto.peso());
            }
            if (dto.descricao() != null) {
                animal.setDescricao(dto.descricao());
            }
            if (dto.status() != null) {
                animal.setStatus(dto.status());
            }

            repository.save(animal);

            return ResponseEntity.ok(new AnimalDto(animal));
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Animal não encontrado", false));
        }
    }

    @DeleteMapping("/{animalId}")
    public ResponseEntity<?> deleteAnimal(@PathVariable String animalId){
        try{
            repository.delete(repository.findById(animalId).orElseThrow(() -> new EntityNotFoundException("Animal não encontrado")));
            if (!repository.existsById(animalId))
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("Deletado com sucesso!", true));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Erro interno ao deletar", false));
        }
        catch (EntityNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Id de animal inexistente", false));
        }
    }

    @PostMapping("/relatorio/{animalId}")
    public ResponseEntity<?> novoRelatorio(@Validated @RequestBody NewRelatorioDto data, @PathVariable String animalId){
        try{
            Animal animal = getAnimal(animalId);
            Voluntario voluntario = animal.getVoluntario();

            Relatorio relatorio = new Relatorio(data, animal, voluntario);

            animal.getRelatorios().add(relatorio);
            voluntario.getRelatorios().add(relatorio);

            relatorio.setAnimalRelatorio(animal);
            relatorio.setVoluntarioRelatorio(voluntario);

            relatorioRepository.save(relatorio);

            return ResponseEntity.ok().body(new RelatorioDto(relatorio));
        }
        catch (EntityNotFoundException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Animal não encontrado", false));
        }
    }

    private Voluntario getVoluntario(String id){
        return voluntarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Voluntário não encontrado"));
    }

    private Animal getAnimal(String id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado"));
    }
}