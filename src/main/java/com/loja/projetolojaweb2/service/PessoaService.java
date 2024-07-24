package com.loja.projetolojaweb2.service;

import com.loja.projetolojaweb2.domain.Pessoa;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPostRequest;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPutRequest;
import com.loja.projetolojaweb2.mapper.PessoaMapper;
import com.loja.projetolojaweb2.repository.PessoaRepository;
import com.loja.projetolojaweb2.service.serviceInterface.PessoaServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class PessoaService implements PessoaServiceInterface {

    private final PessoaRepository pessoaRepository;

    @Override
    public List<Pessoa> encontrarTodos() {
        return List.of();
    }

    @Override
    public Pessoa encontrarPorIdOuLancarExcecao(String login) {
        return pessoaRepository.findById(login)
            .orElseThrow(() ->new ResponseStatusException(BAD_REQUEST,"Pessoa não encontrada"));
    }

    @Override
    public void atualizar(PessoaPutRequest pessoaPutRequest) {
        Pessoa pessoaSalva = encontrarPorIdOuLancarExcecao(pessoaPutRequest.getLogin());
        Pessoa pessoa = PessoaMapper.INSTANCE.toPessoa(pessoaPutRequest);
        pessoa.setLogin(pessoaSalva.getLogin());
        pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa salvar(PessoaPostRequest pessoaPostRequest) {
        return pessoaRepository.save(PessoaMapper.INSTANCE.toPessoa(pessoaPostRequest));
    }

    @Override
    public void delete(String login) {
        pessoaRepository.delete(encontrarPorIdOuLancarExcecao(login));
    }


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
    return null;
    }

    public List<Pessoa> listAll() {
      return  pessoaRepository.findAll();
    }
}
