package com.loja.projetolojaweb2.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 5,unique = false)
    private int estrela;
    @Column(nullable = false, length = 240,unique = false)
    private String comentario;

    @ManyToOne
    private Usuario cliente;

    @ManyToOne
    private Produto produtoAvaliado;
}
