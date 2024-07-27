package com.loja.projetolojaweb2.service.serviceInterface;

import com.loja.projetolojaweb2.domain.Pessoa;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPostRequest;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPutRequest;

import java.util.List;

public interface PessoaServiceInterface {

   List<Pessoa> encontrarTodos();

   Pessoa encontrarPorIdOuLancarExcecao(String login);

   void atualizar(PessoaPutRequest pessoaPutRequest);

   Pessoa salvar(PessoaPostRequest pessoaPostRequest);

   void delete(String login);

   Pessoa fazerLogin(String usuario, String senha);

   Pessoa fazerLogout();

   Pessoa recuperarSenha();

   Pessoa configurarConta();


}
