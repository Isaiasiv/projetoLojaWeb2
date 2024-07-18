package com.loja.projetolojaweb2.service;

import com.loja.projetolojaweb2.domain.Pessoa;
import com.loja.projetolojaweb2.service.serviceInterface.PessoaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService implements PessoaServiceInterface {

    @Override
    public Pessoa fazerLogin() {
        return null;
    }

    @Override
    public Pessoa fazerLogout() {
        return null;
    }

    @Override
    public Pessoa recuperarSenha() {
        return null;
    }

    @Override
    public Pessoa configurarConta() {
        return null;
    }

    public List<Pessoa> list(){

        return List.of(new Pessoa("paulinho","PAULO",666432L,"paulo",null,"teste@gmail.com",null ));
    }
}
