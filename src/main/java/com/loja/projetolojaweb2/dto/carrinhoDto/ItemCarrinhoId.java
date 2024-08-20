package com.loja.projetolojaweb2.dto.carrinhoDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemCarrinhoId implements Serializable {

    @EqualsAndHashCode.Include
    private Long carrinho;

    @EqualsAndHashCode.Include
    private Long produto;
}
