package com.tiapn.Recanto_API.dtos;

import lombok.Getter;
import lombok.Setter;

public record AnimalUpdateDto(
        String nome,
        String especie,
        String raca,
        Integer idade,
        String sexo,
        Double peso,
        String descricao,
        String status
) {
}
