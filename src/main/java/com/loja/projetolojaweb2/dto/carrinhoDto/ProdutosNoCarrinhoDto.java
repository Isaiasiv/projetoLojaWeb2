package com.loja.projetolojaweb2.dto.carrinhoDto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutosNoCarrinhoDto {

    private String nome;
    private String descricao;
    private BigDecimal preco;

    public ProdutosNoCarrinhoDto(String nome, String descricao, BigDecimal preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }
}
