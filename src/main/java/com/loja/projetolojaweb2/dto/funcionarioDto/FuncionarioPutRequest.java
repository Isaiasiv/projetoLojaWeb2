package com.loja.projetolojaweb2.dto.funcionarioDto;

import lombok.Data;

@Data
public class FuncionarioPutRequest {

    private String login;
    private String senha;
    private Long cpf;
    private String nome;
    private String telefone;
    private String email;
    private String dataNascimento;
    private int tipoConta;
    private String vendas;
    private int tipoVendendor;
    private String addProduto;

}
