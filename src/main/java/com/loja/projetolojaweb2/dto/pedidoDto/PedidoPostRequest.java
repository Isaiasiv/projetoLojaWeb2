package com.loja.projetolojaweb2.dto.pedidoDto;

import com.loja.projetolojaweb2.domain.Produto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoPostRequest {
    private Long id;
    private boolean status;
    private LocalDateTime data;
    private String transportadora;
    private List<Produto> produtos;
}
