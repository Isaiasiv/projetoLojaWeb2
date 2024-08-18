package com.loja.projetolojaweb2.dto.produtoCarrinhoDto;

import lombok.Data;

@Data
public class ProdutoCarrinhoDto {
    private Long idCarrinho;
    private Long idProduto;
    private int quantidade;
}
