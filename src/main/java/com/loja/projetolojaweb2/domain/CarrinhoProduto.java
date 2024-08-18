package com.loja.projetolojaweb2.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class CarrinhoProduto {
    @EmbeddedId
    private CarrinhoProdutoId id;

    @ManyToOne
    @JoinColumn(name = "carrinho_id",nullable = false)
    @MapsId("carrinhoId")
    @JsonBackReference
    private Carrinho carrinho;

    @ManyToOne
    @JoinColumn(name = "produto_id",nullable = false)
    @JsonBackReference
    @MapsId("produtoId")
    private Produto produto;

    private int quantidade;

    public CarrinhoProduto(Carrinho carrinho, Produto produto, int quantidade) {
        this.carrinho = carrinho;
        this.produto = produto;
        this.quantidade = quantidade;

        this.id = new CarrinhoProdutoId(carrinho.getId(), produto.getProdutoID());
    }

}
