package com.loja.projetolojaweb2.service;

import com.loja.projetolojaweb2.domain.Funcionario;
import com.loja.projetolojaweb2.domain.Usuario;
import com.loja.projetolojaweb2.dto.funcionarioDto.FuncionarioPostRequest;
import com.loja.projetolojaweb2.dto.funcionarioDto.FuncionarioPutRequest;
import com.loja.projetolojaweb2.dto.usuarioDto.UsuarioPostRequest;
import com.loja.projetolojaweb2.dto.usuarioDto.UsuarioPutRequest;
import com.loja.projetolojaweb2.mapper.FuncionarioMapper;
import com.loja.projetolojaweb2.mapper.UsuarioMapper;
import com.loja.projetolojaweb2.repository.FuncionarioRepository;
import com.loja.projetolojaweb2.service.serviceInterface.FuncionarioInterface;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class FuncionarioService implements FuncionarioInterface {

    private final FuncionarioRepository funcionarioRepository;

    @Override
    public List<Funcionario> encontrarTodos() {
        return funcionarioRepository.findAll();
    }


    @Override
    public Funcionario encontrarPorIdOuLancarExcecao(String login) {
        return funcionarioRepository.findById(login)
                .orElseThrow(() ->new ResponseStatusException(BAD_REQUEST,"Funcionario não encontrado"));
    }

    @Override
    public void atualizar(FuncionarioPutRequest funcionarioPutRequest) {
        Funcionario funcionarioSalvo = encontrarPorIdOuLancarExcecao(funcionarioPutRequest.getLogin());
        Funcionario funcionario = FuncionarioMapper.INSTANCE.toFuncionario(funcionarioPutRequest);
        funcionario.setLogin(funcionarioSalvo.getLogin());
        funcionarioRepository.save(funcionario);
    }

    @Override
    public Funcionario salvar(FuncionarioPostRequest funcionarioPostRequest) {
        return funcionarioRepository.save(FuncionarioMapper.INSTANCE.toFuncionario(funcionarioPostRequest));
    }

    @Override
    public void delete(String login) {
        funcionarioRepository.delete(encontrarPorIdOuLancarExcecao(login));
    }


}
