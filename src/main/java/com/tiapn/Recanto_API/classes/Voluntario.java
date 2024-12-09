package com.tiapn.Recanto_API.classes;

import java.util.List;

import com.tiapn.Recanto_API.dtos.NewVoluntarioDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

@Table(name = "voluntarios")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Voluntario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cod_voluntario")
    private String id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String telefone;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private Integer permissao;

    @OneToMany(mappedBy = "voluntario")
    private List<Animal> animais;

    @OneToMany(mappedBy = "voluntarioRelatorio", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Relatorio> relatorios;

    public Voluntario(NewVoluntarioDto data) {
        this.nome = data.nome();
        this.telefone = data.telefone();
        this.email = data.email();
        this.senha = data.senha();
        this.permissao = data.permissao();
    }
}
