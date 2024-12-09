package com.tiapn.Recanto_API.dtos;

import com.tiapn.Recanto_API.classes.Voluntario;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VoluntarioDto {
    private String id;

    private String nome;

    private String telefone;

    private String email;

    private String senha;

    private Integer permissao;

    private List<AnimalDto> animais;

    private List<RelatorioDto> relatorios;

    public VoluntarioDto(Voluntario data){
        this.id = data.getId();
        this.nome = data.getNome();
        this.telefone = data.getTelefone();
        this.email = data.getEmail();;
        this.senha = data.getSenha();
        this.permissao = data.getPermissao();
        this.animais = data.getAnimais().stream().map(AnimalDto::new).toList();
        this.relatorios = data.getRelatorios().stream().map(RelatorioDto::new).toList();
    }

    public VoluntarioDto(Voluntario data, Boolean limited){
        this.id = data.getId();
        this.nome = data.getNome();
        this.telefone = data.getTelefone();
        this.email = data.getEmail();
        this.permissao = data.getPermissao();
        this.animais = data.getAnimais().stream().map(AnimalDto::new).toList();
    }
}
