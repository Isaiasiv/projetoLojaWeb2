package com.loja.projetolojaweb2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.loja.projetolojaweb2.dto.carrinhoDto.ItemProdutoDto;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "carrinho")

public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "carrinho",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ItemCarrinho> itemCarrinho;

    private BigDecimal valorTotal;
    private String categoria;

    @OneToOne(mappedBy = "carrinho")
    @JsonIgnore
    private Pessoa pessoa;

//    public void addProduto(Produto produto, int quantidade) {
//        // Verifica se o produto já está no carrinho
//        for (ItemCarrinho item : itemCarrinho) {
//            if (item.getProduto().equals(produto)) {
//                item.setQuantidade(item.getQuantidade() + quantidade);
//                return;
//            }
//        }
//        // Se o produto não estiver no carrinho, adiciona um novo ItemCarrinho
//        ItemCarrinho novoItemCarrinho = new ItemCarrinho();
//        novoItemCarrinho.setProduto(produto);
//        novoItemCarrinho.setQuantidade(quantidade);
//        novoItemCarrinho.setCarrinho(this);
//        itemCarrinho.add(novoItemCarrinho);
//    }

//    private void atualizarValorTotal() {
//        this.valorTotal = itemCarrinho.stream()
//                .map(item -> item.getItemCarrinhoProduto().getValor().multiply(BigDecimal.valueOf(item.getQuantidade())))
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//    }

//    public void addProduto(Produto produto, int quantidade) {
//        if (produto == null || quantidade <= 0) {
//            throw new IllegalArgumentException("Produto ou quantidade inválidos.");
//        }
//
//        // Encontrar o item no carrinho usando o ID do produto para garantir a comparação correta
//        ItemCarrinho itemCarrinho = this.carrinho.stream()
//                .filter(item -> item.getProduto().getProdutoID().equals(produto.getProdutoID()))
//                .findFirst()
//                .orElse(null);
//
//        if (itemCarrinho != null) {
//            itemCarrinho.setQuantidade(itemCarrinho.getQuantidade() + quantidade);
//        } else {
//            itemCarrinho = new ItemCarrinho();
//            itemCarrinho.setProduto(produto);
//            itemCarrinho.setQuantidade(quantidade);
//            itemCarrinho.setCarrinho(this);
//            this.carrinho.add(itemCarrinho);
//        }
//
//    }
}
