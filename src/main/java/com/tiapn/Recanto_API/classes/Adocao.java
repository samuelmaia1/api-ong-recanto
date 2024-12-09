package com.tiapn.Recanto_API.classes;

import java.sql.Date;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "adocoes")
@Entity
@Getter
@Setter
public class Adocao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cod_adocao")
    private String id;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Date dataAdocao;

    @ManyToOne
    @JoinColumn(name = "cod_usuario")
    private User usuario;

    @OneToOne
    @JoinColumn(name = "cod_animal", referencedColumnName = "cod_animal")
    private Animal animal;

}
