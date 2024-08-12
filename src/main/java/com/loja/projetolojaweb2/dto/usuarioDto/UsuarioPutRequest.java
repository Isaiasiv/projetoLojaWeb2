package com.loja.projetolojaweb2.dto.usuarioDto;

import com.loja.projetolojaweb2.domain.Carrinho;
import lombok.Data;

import java.util.List;

@Data
public class UsuarioPutRequest {

    private String login;
    private String senha;
    private Long cpf;
    private String nome;
    private String telefone;
    private String email;
    private String dataNascimento;
    private List<String> favoritos;
    private int tipoConta;
    private Carrinho carrinho;
}
