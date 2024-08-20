package com.loja.projetolojaweb2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import java.util.List;

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
    private BigDecimal valor;

    @Column(nullable = false)
    private int quantidade;

    @OneToMany(mappedBy = "produto",cascade = CascadeType.ALL)
    private List<ItemCarrinho> itemCarrinhoProduto;

    @ManyToOne
    @JoinColumn(name = "fk_produto_pedido")
    @JsonIgnore
    private Pedido pedidos;



}
