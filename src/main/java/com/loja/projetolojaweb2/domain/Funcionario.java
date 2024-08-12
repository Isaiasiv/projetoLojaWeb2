package com.loja.projetolojaweb2.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.text.DateFormat;

@Data
@Entity
@Table(name = "funcionario")
@NoArgsConstructor
public class Funcionario extends Pessoa{

    private static final long serialVersionUID = 1L;


    private String vendas;

    @Column(name = "tipo_vendedor",nullable = false)
    private int tipoVendendor;

    @Column(name = "add_produto",nullable = true)
    private String addProduto;


    public Funcionario(String login, String senha, Long cpf, String nome, String telefone, String email, String dataNascimento, int tipoConta, String vendas, int tipoVendendor, String addProduto) {
        super(login, senha, cpf, nome, telefone, email, dataNascimento, tipoConta);
        this.vendas = vendas;
        this.tipoVendendor = tipoVendendor;
        this.addProduto = addProduto;
    }
}
