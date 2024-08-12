package com.loja.projetolojaweb2.dto.carrinhoDto;

import com.loja.projetolojaweb2.domain.Pessoa;
import com.loja.projetolojaweb2.domain.Produto;
import lombok.Data;

import java.util.List;
@Data
public class CarrinhoPutRequest {

    private Long id;
    private List<Produto> produtos;
    private long valorTotal;
    private String categoria;
    private Pessoa pessoa;
}
