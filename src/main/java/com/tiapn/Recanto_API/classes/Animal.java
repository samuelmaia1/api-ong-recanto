package com.tiapn.Recanto_API.classes;

import java.util.List;

import com.tiapn.Recanto_API.dtos.NewAnimalDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "animais")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cod_animal", nullable = false)
    private String id;
    
    @Column(unique = false, nullable = false)
    private String nome;

    @Column(nullable = false)
    private String especie;

    @Column(nullable = false)
    private String raca;

    @Column(nullable = false)
    private Integer idade;

    @Column(nullable = false)
    private String sexo;

    @Column(nullable = false)
    private Double peso;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "cod_voluntario", nullable = true)
    private Voluntario voluntario;

    @OneToOne(mappedBy = "animal")
    private Adocao adocao;
    
    @OneToMany(mappedBy = "animalRelatorio", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Relatorio> relatorios;

    public Animal(NewAnimalDto data, Voluntario voluntario){
        this.voluntario = voluntario;
        this.nome = data.nome();
        this.especie = data.especie();
        this.descricao = data.descricao();;
        this.status = data.status();
        this.sexo = data.sexo();
        this.idade = data.idade();
        this.raca = data.raca();
        this.peso = data.peso();
    }

}
