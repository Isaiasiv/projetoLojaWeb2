package com.loja.projetolojaweb2.dto.funcionarioDto;

import com.loja.projetolojaweb2.domain.Carrinho;
import com.loja.projetolojaweb2.domain.Endereco;
import com.loja.projetolojaweb2.dto.EnderecoDto.EnderecoPostRequest;
import com.loja.projetolojaweb2.dto.EnderecoDto.EnderecoPutRequest;
import com.loja.projetolojaweb2.dto.carrinhoDto.CarrinhoPutRequest;
import lombok.Data;

import java.util.List;

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
    private List<EnderecoPutRequest> enderecos;
    private CarrinhoPutRequest carrinho;

}
