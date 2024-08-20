package com.loja.projetolojaweb2.dto.carrinhoDto;

import com.loja.projetolojaweb2.domain.Pessoa;
import com.loja.projetolojaweb2.domain.Produto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class CarrinhoPutRequest {

    private Long id;
    private BigDecimal valorTotal;
    private String categoria;
    private Pessoa pessoa;
}
