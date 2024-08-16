package com.loja.projetolojaweb2.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor

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

    @Column(nullable = false, length = 2)
    private int tipoConta;

    @OneToMany(mappedBy = "pessoa",fetch = FetchType.LAZY,targetEntity = Endereco.class)
    private List<Endereco> enderecos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carrinhoId",referencedColumnName = "id")
    private Carrinho carrinho;


    public Pessoa(){

    }

    public void addEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
        endereco.setPessoa(this);
    }

}
