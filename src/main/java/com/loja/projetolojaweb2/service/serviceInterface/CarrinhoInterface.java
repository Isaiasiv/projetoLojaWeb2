package com.loja.projetolojaweb2.service.serviceInterface;

import com.loja.projetolojaweb2.domain.Carrinho;
import com.loja.projetolojaweb2.dto.carrinhoDto.CarrinhoPutRequest;
import com.loja.projetolojaweb2.repository.CarrinhoRepository;

import java.util.List;

public interface CarrinhoInterface {
    List<Carrinho> encontrarTodos();

    Carrinho encontrarPorIdOuLancarExcecao(Long id);

    void atualizar(CarrinhoPutRequest carrinhoPutRequest);

}
