package com.loja.projetolojaweb2.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CarrinhoProdutoId {
    private Long carrinhoId;
    private Long produtoId;
}
