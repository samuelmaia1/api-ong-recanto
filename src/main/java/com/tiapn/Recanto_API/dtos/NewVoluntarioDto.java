package com.tiapn.Recanto_API.dtos;

import org.antlr.v4.runtime.misc.NotNull;

public record NewVoluntarioDto(@NotNull  String nome, @NotNull String telefone, @NotNull  String email, @NotNull String senha, @NotNull Integer permissao) {
}
