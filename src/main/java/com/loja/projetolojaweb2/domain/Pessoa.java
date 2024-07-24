package com.loja.projetolojaweb2.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {

    private static final long serialVersionUID = 1L;



    @Id
    private String login;

    @Column(length = 16, nullable = false)
    private String senha;

    @Column(nullable = false, length = 11,unique = true)
    private Long cpf;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 14)
    private String telefone;

    @Column(nullable = false)
    private String email;

    @Column(name = "data_nascimento",nullable = false)
    private String dataNascimento;

}
