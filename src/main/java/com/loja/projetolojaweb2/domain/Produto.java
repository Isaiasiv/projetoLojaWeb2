package com.loja.projetolojaweb2.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produtoID;

    @Column(nullable = false, length = 20)
    private String nome;

    @Column(nullable = false, length = 20)
    private String tipo;

    @Column(nullable = false, length = 10)
    private String tamanho;

    @Column(nullable = false, length = 20)
    private String cor;

    @Column(nullable = false, length = 1000)
    private String imagem;

    @Column(nullable = false, length = 20)
    private String fabricante;

    @Column(nullable = false, length = 20)
    private String categoria;

    @Column(nullable = false, length = 20)
    private String subcategoria;

    @Column(nullable = false, length = 100)
    private String descricao;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false)
    private int quantidade;
    @ManyToMany
    @JoinTable(name = "produto_carrinho",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "carrinho_id"))
    private List<Carrinho> carrinhos;
}
