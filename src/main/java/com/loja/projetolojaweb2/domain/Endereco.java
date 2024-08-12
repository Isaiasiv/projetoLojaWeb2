package com.loja.projetolojaweb2.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "endereco" )
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String rua;

    @Column(nullable = false)
    private String setor;

    @Column(nullable = false)
    private String uf;

    @Column(nullable = false)
    private String cidade;

    private String complemento;

    @ManyToOne()
    @JoinColumn(name = "fk_pessoa_login")
    private Pessoa pessoa;

}
