package com.loja.projetolojaweb2.service.serviceInterface;

import com.loja.projetolojaweb2.domain.Pessoa;

public interface PessoaServiceInterface {

   Pessoa fazerLogin();

   Pessoa fazerLogout();

   Pessoa recuperarSenha();

   Pessoa configurarConta();


}
