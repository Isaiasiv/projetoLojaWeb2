package com.loja.projetolojaweb2.service.serviceInterface;

import com.loja.projetolojaweb2.domain.Endereco;
import com.loja.projetolojaweb2.domain.Funcionario;
import com.loja.projetolojaweb2.dto.EnderecoDto.EnderecoPostRequest;
import com.loja.projetolojaweb2.dto.EnderecoDto.EnderecoPutRequest;
import com.loja.projetolojaweb2.dto.funcionarioDto.FuncionarioPostRequest;
import com.loja.projetolojaweb2.dto.funcionarioDto.FuncionarioPutRequest;
import com.loja.projetolojaweb2.repository.EnderecoRepository;

import java.util.List;

public interface EnderecoInterface {
    List<Endereco> encontrarTodos();

    Endereco encontrarPorIdOuLancarExcecao(Long id);

    void atualizar(EnderecoPutRequest enderecoPutRequest);

    Endereco salvar(EnderecoPostRequest enderecoPostRequest);

    void delete(Long id);
}
