package com.loja.projetolojaweb2.service.serviceInterface;

import com.loja.projetolojaweb2.domain.Funcionario;
import com.loja.projetolojaweb2.domain.Usuario;
import com.loja.projetolojaweb2.dto.funcionarioDto.FuncionarioPostRequest;
import com.loja.projetolojaweb2.dto.funcionarioDto.FuncionarioPutRequest;
import com.loja.projetolojaweb2.dto.usuarioDto.UsuarioPostRequest;
import com.loja.projetolojaweb2.dto.usuarioDto.UsuarioPutRequest;

import java.util.List;

public interface FuncionarioInterface {

    List<Funcionario> encontrarTodos();

    Funcionario encontrarPorIdOuLancarExcecao(String login);

    void atualizar(FuncionarioPutRequest funcionarioPutRequest);

    Funcionario salvar(FuncionarioPostRequest funcionarioPostRequest);

    void delete(String login);
}
