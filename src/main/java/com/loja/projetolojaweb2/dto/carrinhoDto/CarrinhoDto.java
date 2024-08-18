package com.loja.projetolojaweb2.dto.carrinhoDto;

import com.loja.projetolojaweb2.dto.produtoDto.ProdutoDetalhesDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class CarrinhoDto {
    private Long carrinhoId;
    private List<ProdutoDetalhesDto> produtosDetalhe;
    private BigDecimal valorTotal;
    private String categoria;


}
