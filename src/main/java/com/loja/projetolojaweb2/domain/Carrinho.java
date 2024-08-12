package com.loja.projetolojaweb2.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "carrinho")

public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "carrinhos")
    private List<Produto> produtos;

    private long valorTotal;
    private String categoria;

    @OneToOne(mappedBy = "carrinho",cascade = CascadeType.ALL)
    private Pessoa pessoa;
}
