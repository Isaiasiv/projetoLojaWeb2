package com.loja.projetolojaweb2.service.serviceInterface;

import com.loja.projetolojaweb2.domain.Endereco;
import com.loja.projetolojaweb2.domain.Pessoa;
import com.loja.projetolojaweb2.dto.EnderecoToPessoaDto.EnderecoToPessoaPutRequest;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPostRequest;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPutRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import java.util.List;

public interface PessoaServiceInterface {

   List<Pessoa> encontrarTodos();

   Pessoa encontrarPorIdOuLancarExcecao(String login);

   void atualizar(PessoaPutRequest pessoaPutRequest);

   Pessoa salvar(PessoaPostRequest pessoaPostRequest);

   void delete(String login);

   Pessoa fazerLogin(String usuario, String senha);

   void fazerLogout(HttpServletRequest request);

   Pessoa recuperarSenha(PessoaPutRequest pessoaPutRequest);

   Pessoa configurarConta();

  void verificarLoginOuLancarExcecao(HttpServletRequest request);

   Endereco addEnderecoToPessoa(EnderecoToPessoaPutRequest enderecoToPessoaPutRequest);




   List<Pessoa> list();
}
