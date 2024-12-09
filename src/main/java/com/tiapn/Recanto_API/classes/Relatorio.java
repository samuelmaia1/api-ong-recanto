package com.tiapn.Recanto_API.classes;

import java.sql.Date;
import java.time.LocalDate;

import com.tiapn.Recanto_API.dtos.NewRelatorioDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "relatorios")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Relatorio {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cod_relatorio")
    private String id;

    @Column(name = "data_relatorio", nullable = false)
    private Date dataRelatorio;

    @Column(nullable = false)
    private String observacao;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "cod_animal")
    private Animal animalRelatorio;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "cod_voluntario")
    private Voluntario voluntarioRelatorio;

    public Relatorio(NewRelatorioDto data, Animal animalRelatorio, Voluntario voluntarioRelatorio){
        this.observacao = data.observacao();
        this.dataRelatorio = Date.valueOf(LocalDate.now());
        this.animalRelatorio = animalRelatorio;
        this.voluntarioRelatorio = voluntarioRelatorio;
    }
}
