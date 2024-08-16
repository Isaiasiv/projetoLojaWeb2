package com.loja.projetolojaweb2.dto.carrinhoDto;

import com.loja.projetolojaweb2.domain.Pessoa;
import com.loja.projetolojaweb2.domain.Produto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;
@Data
public class CarrinhoPostRequest {

    private List<Produto> produtos;
    private long valorTotal;
    private String categoria;
    private Pessoa pessoa;
}
