package com.loja.projetolojaweb2.dto.pessoaDto;

import com.loja.projetolojaweb2.domain.Carrinho;
import com.loja.projetolojaweb2.domain.Endereco;
import com.loja.projetolojaweb2.dto.EnderecoDto.EnderecoPostRequest;
import com.loja.projetolojaweb2.dto.carrinhoDto.CarrinhoPostRequest;
import com.loja.projetolojaweb2.dto.carrinhoDto.CarrinhoPutRequest;
import jakarta.persistence.Column;
import lombok.Data;

import java.text.DateFormat;
import java.util.List;

@Data
public class PessoaPostRequest {

    private String login;
    private String senha;
    private Long cpf;
    private String nome;
    private String telefone;
    private String email;
    private String dataNascimento;
    private int tipoConta;
    private List<EnderecoPostRequest> enderecos;
    private CarrinhoPostRequest carrinho;

}
