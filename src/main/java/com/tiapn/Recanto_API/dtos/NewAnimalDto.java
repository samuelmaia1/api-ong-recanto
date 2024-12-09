package com.tiapn.Recanto_API.dtos;

import org.antlr.v4.runtime.misc.NotNull;

public record NewAnimalDto(
        @NotNull String nome,
        @NotNull String especie,
        @NotNull String raca,
        @NotNull String sexo,
        @NotNull Double peso,
        @NotNull String descricao,
        @NotNull String status,
        @NotNull Integer idade
) {
}
