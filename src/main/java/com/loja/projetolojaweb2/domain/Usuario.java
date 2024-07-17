package com.loja.projetolojaweb2.domain;

import lombok.*;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Usuario extends Pessoa {

    private List<String> favoritos;

    @Builder
    public Usuario(Long cpf, String nome, Long telefone, String email, DateFormat dataNascimento, String login, String senha, List<String> favoritos) {
        super(cpf, nome, telefone, email, dataNascimento, login, senha);
        this.favoritos = favoritos;
    }
}
