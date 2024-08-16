package com.loja.projetolojaweb2.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.text.DateFormat;

@Data
@Entity
@Table(name = "funcionario")
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario extends Pessoa{

    private static final long serialVersionUID = 1L;


    private String vendas;

    @Column(name = "tipo_vendedor",nullable = false)
    private int tipoVendendor;

    @Column(name = "add_produto",nullable = true)
    private String addProduto;

}
