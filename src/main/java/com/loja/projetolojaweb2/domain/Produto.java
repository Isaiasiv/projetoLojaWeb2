package com.loja.projetolojaweb2.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produto")
public class Produto {

    @Id
    private int produtoID;

    @Column(unique = false, nullable = false, length = 20)
    private String nome;
    @Column(unique = false, nullable = false, length = 20)
    private String tipo;
    @Column(unique = false, nullable = false, length = 10)
    private String tamanho;
    @Column(unique = false, nullable = false, length = 20)
    private String cor;
    @Column(unique = false, nullable = false, length = 1000)
    private String imagem;
    @Column(unique = false, nullable = false, length = 20)
    private String fabricante;
    @Column(unique = false, nullable = false, length = 20)
    private String categoria;
    @Column(unique = false, nullable = false, length = 20)
    private String subcategoria;
    @Column(unique = false, nullable = false, length = 100)
    private String descricao;
    @Column(unique = false, nullable = false)
    private double valor;
    @Column(unique = false, nullable = false)
    private int quantidade;
}
