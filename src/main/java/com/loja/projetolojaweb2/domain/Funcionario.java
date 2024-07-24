package com.loja.projetolojaweb2.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;

@Data
@Entity
@Table(name = "funcionario")
public class Funcionario extends Pessoa{

    private static final long serialVersionUID = 1L;


    private String vendas;

    @Column(name = "tipo_vendedor",nullable = false)
    private int tipoVendendor;

    @Column(name = "add_produto",nullable = true)
    private String addProduto;

    @Builder
    public Funcionario(String login, String senha, Long cpf, String nome, String telefone, String email, DateFormat dataNascimento, String vendas, int tipoVendendor, String addProduto) {
        super(login, senha, cpf, nome, telefone, email, dataNascimento);
        this.vendas = vendas;
        this.tipoVendendor = tipoVendendor;
        this.addProduto = addProduto;
    }
    @Builder
    public Funcionario(String vendas, int tipoVendendor, String addProduto) {
        this.vendas = vendas;
        this.tipoVendendor = tipoVendendor;
        this.addProduto = addProduto;
    }

    public Funcionario() {

    }

}
