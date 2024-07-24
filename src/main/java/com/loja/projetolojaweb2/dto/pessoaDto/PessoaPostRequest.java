package com.loja.projetolojaweb2.dto.pessoaDto;

import jakarta.persistence.Column;
import lombok.Data;

import java.text.DateFormat;

@Data
public class PessoaPostRequest {

    private String login;
    private String senha;
    private Long cpf;
    private String nome;
    private String telefone;
    private String email;
    private String dataNascimento;
}
