package com.loja.projetolojaweb2.dto.usuarioDto;

import com.loja.projetolojaweb2.domain.Carrinho;
import com.loja.projetolojaweb2.domain.Endereco;
import com.loja.projetolojaweb2.dto.EnderecoDto.EnderecoPutRequest;
import com.loja.projetolojaweb2.dto.carrinhoDto.CarrinhoPutRequest;
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
    private CarrinhoPutRequest carrinho;
    private List<EnderecoPutRequest> enderecos;
}
