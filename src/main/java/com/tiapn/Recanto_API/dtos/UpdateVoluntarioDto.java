package com.tiapn.Recanto_API.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVoluntarioDto {
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private Integer permissao;
}
