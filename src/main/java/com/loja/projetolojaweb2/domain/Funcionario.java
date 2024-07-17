package com.loja.projetolojaweb2.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;


@Getter
@Setter
public class Funcionario extends Pessoa{

    @Builder
    public Funcionario(Long cpf, String nome, Long telefone, String email, DateFormat dataNascimento, String login, String senha,
                       String vendas, int tipoVendendor, String addProduto) {

        super(cpf, nome, telefone, email, dataNascimento, login, senha);
        this.vendas = vendas;
        this.tipoVendendor = tipoVendendor;
        this.addProduto = addProduto;
    }

    private String vendas;
    private int tipoVendendor;
    private String addProduto;

}
