package com.loja.projetolojaweb2.service;

import com.loja.projetolojaweb2.domain.Carrinho;
import com.loja.projetolojaweb2.domain.Endereco;
import com.loja.projetolojaweb2.dto.carrinhoDto.CarrinhoPutRequest;
import com.loja.projetolojaweb2.mapper.CarrinhoMapper;
import com.loja.projetolojaweb2.mapper.EnderecoMapper;
import com.loja.projetolojaweb2.repository.CarrinhoRepository;
import com.loja.projetolojaweb2.service.serviceInterface.CarrinhoInterface;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@Data
@RequiredArgsConstructor

public class CarrinhoService implements CarrinhoInterface {
    private final CarrinhoRepository carrinhoRepository;

    @Override
    public List<Carrinho> encontrarTodos() {
        return carrinhoRepository.findAll();
    }

    @Override
    public Carrinho encontrarPorIdOuLancarExcecao(Long id) {
        return carrinhoRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(BAD_REQUEST,"Carrinho nao encontrado"));
    }

    @Override
    public void atualizar(CarrinhoPutRequest carrinhoPutRequest) {
        Carrinho carrinhoSalvo = encontrarPorIdOuLancarExcecao(carrinhoPutRequest.getId());
        Carrinho carrinho = CarrinhoMapper.INSTANCE.toCarrinho(carrinhoPutRequest);
        carrinho.setId(carrinhoSalvo.getId());
        carrinhoRepository.save(carrinho);
    }
}
