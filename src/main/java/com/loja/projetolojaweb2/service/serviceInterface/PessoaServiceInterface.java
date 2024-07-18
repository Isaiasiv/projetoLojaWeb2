package com.loja.projetolojaweb2.service.serviceInterface;

import com.loja.projetolojaweb2.domain.Pessoa;

import java.util.List;

public interface PessoaServiceInterface {

   Pessoa fazerLogin();

   Pessoa fazerLogout();

   Pessoa recuperarSenha();

   Pessoa configurarConta();


   List<Pessoa> list();
}
