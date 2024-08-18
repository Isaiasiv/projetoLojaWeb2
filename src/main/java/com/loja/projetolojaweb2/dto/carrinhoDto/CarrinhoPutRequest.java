package com.loja.projetolojaweb2.dto.carrinhoDto;

import com.loja.projetolojaweb2.domain.Pessoa;
import com.loja.projetolojaweb2.domain.Produto;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CarrinhoPutRequest {

    private Long id;
    private Map<Produto,Integer> produtos;
    private long valorTotal;
    private String categoria;
    private Pessoa pessoa;
}
