package com.loja.projetolojaweb2.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "usuario")
public class Usuario extends Pessoa {


    private List<String> favoritos;

    private static final long serialVersionUID = 1L;


    public Usuario() {}

    @Builder
    public Usuario(String login, String senha, Long cpf, String nome, String telefone, String email, DateFormat dataNascimento, List<String> favoritos) {
        super(login, senha, cpf, nome, telefone, email, dataNascimento);
        this.favoritos = favoritos;
    }

    @Builder
    public Usuario(List<String> favoritos) {
        this.favoritos = favoritos;
    }
}
