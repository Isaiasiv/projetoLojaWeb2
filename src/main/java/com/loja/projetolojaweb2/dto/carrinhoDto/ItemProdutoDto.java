package com.loja.projetolojaweb2.dto.carrinhoDto;

import lombok.Data;

@Data
public class ItemProdutoDto {

    private Long carrinhoId;
    private Long produtoId;
    private int quantidade;
}
