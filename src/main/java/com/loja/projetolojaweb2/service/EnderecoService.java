package com.loja.projetolojaweb2.service;

import com.loja.projetolojaweb2.domain.Endereco;
import com.loja.projetolojaweb2.domain.Funcionario;
import com.loja.projetolojaweb2.dto.EnderecoDto.EnderecoPostRequest;
import com.loja.projetolojaweb2.dto.EnderecoDto.EnderecoPutRequest;
import com.loja.projetolojaweb2.mapper.EnderecoMapper;
import com.loja.projetolojaweb2.repository.EnderecoRepository;
import com.loja.projetolojaweb2.service.serviceInterface.EnderecoInterface;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Data
@RequiredArgsConstructor
@Service
public class EnderecoService implements EnderecoInterface {

    @Autowired
    EnderecoRepository enderecoRepository;


    @Override
    public List<Endereco> encontrarTodos() {
        return enderecoRepository.findAll();
    }

    @Override
    public Endereco encontrarPorIdOuLancarExcecao(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(BAD_REQUEST,"endereço não encotrado"));
    }

    @Override
    public void atualizar(EnderecoPutRequest enderecoPutRequest) {
        Endereco enderecoSalvo = encontrarPorIdOuLancarExcecao(enderecoPutRequest.getId());
        Endereco endereco = EnderecoMapper.INSTANCE.toEndereco(enderecoPutRequest);
        endereco.setId(enderecoSalvo.getId());
        enderecoRepository.save(endereco);
    }

    @Override
    public Endereco salvar(EnderecoPostRequest enderecoPostRequest) {
        return enderecoRepository.save(EnderecoMapper.INSTANCE.toEndereco(enderecoPostRequest));

    }

    @Override
    public void delete(Long id) {
        enderecoRepository.delete(encontrarPorIdOuLancarExcecao(id));
    }
}
