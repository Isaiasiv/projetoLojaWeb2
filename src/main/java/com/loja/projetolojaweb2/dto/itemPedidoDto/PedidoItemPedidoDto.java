package com.loja.projetolojaweb2.dto.itemPedidoDto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoItemPedidoDto {
    private Long id;
    private Boolean status;
    private LocalDateTime dataPedido;
    private List<ItemPedidoDto> itemPedido;
}
