package com.loja.projetolojaweb2.dto.produtoDto;

import com.loja.projetolojaweb2.domain.Produto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoDto {
    private long produtoId;
    private String nome;
    private BigDecimal valor;

    public ProdutoDto(Produto produto) {
        this.produtoId = produto.getProdutoID();
        this.nome = produto.getNome();
        this.valor = produto.getValor();
    }

}
