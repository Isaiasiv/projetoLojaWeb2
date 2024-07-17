package com.loja.projetolojaweb2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.DateFormat;

@Data
@AllArgsConstructor

public class Pessoa {

    private Long cpf;
    private String nome;
    private Long telefone;
    private String email;
    private DateFormat dataNascimento;
    private String login;
    private String senha;
}
