package com.loja.projetolojaweb2.dto.itemPedidoDto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ItemPedidoDto {
    private Long id;
    private ProdutoItemPedidoDto produto;
    private int quantidade;
}
