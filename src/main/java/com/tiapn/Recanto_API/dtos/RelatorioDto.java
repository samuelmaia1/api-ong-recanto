package com.tiapn.Recanto_API.dtos;

import com.tiapn.Recanto_API.classes.Relatorio;
import com.tiapn.Recanto_API.classes.Voluntario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RelatorioDto {
    private String observacao;
    private LocalDate data;
    private AnimalDto animal;
    private VoluntarioDto voluntario;

    public RelatorioDto(Relatorio data){
        this.observacao = data.getObservacao();
        this.data = data.getDataRelatorio().toLocalDate();
        this.animal = new AnimalDto(data.getAnimalRelatorio());
        this.voluntario = new VoluntarioDto(data.getVoluntarioRelatorio(), true);
    }
}
