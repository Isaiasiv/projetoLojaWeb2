package com.loja.projetolojaweb2.service.serviceInterface;

import com.loja.projetolojaweb2.domain.Usuario;
import com.loja.projetolojaweb2.dto.usuarioDto.UsuarioPostRequest;
import com.loja.projetolojaweb2.dto.usuarioDto.UsuarioPutRequest;

import java.util.List;

public interface UsuarioInterface {

    List<Usuario> encontrarTodos();

    Usuario encontrarPorIdOuLancarExcecao(String login);

    void atualizar(UsuarioPutRequest usuarioPutRequest);

    Usuario salvar(UsuarioPostRequest usuarioPostRequest);

    void delete(String login);


}
