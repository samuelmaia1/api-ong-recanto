package com.tiapn.Recanto_API.dtos;

import com.tiapn.Recanto_API.classes.Animal;
import com.tiapn.Recanto_API.classes.Relatorio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AnimalDto {
    private String cod_animal;
    private String nome;
    private String especie;
    private String raca;
    private Integer idade;
    private String sexo;
    private Double peso;
    private String descricao;
    private String status;

    public AnimalDto(Animal dados) {
        this.cod_animal = dados.getId();
        this.nome = dados.getNome();
        this.especie = dados.getEspecie();
        this.raca = dados.getRaca();
        this.idade = dados.getIdade();
        this.sexo = dados.getSexo();
        this.peso = dados.getPeso();
        this.descricao = dados.getDescricao();
        this.status = dados.getStatus();
    }
}
