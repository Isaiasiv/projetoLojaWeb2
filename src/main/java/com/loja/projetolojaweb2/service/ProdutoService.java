package com.loja.projetolojaweb2.service;

import com.loja.projetolojaweb2.domain.Produto;
import com.loja.projetolojaweb2.dto.produtoDto.ProdutoPostRequest;
import com.loja.projetolojaweb2.dto.produtoDto.ProdutoPutRequest;
import com.loja.projetolojaweb2.repository.ProdutoRepository;
import com.loja.projetolojaweb2.service.serviceInterface.ProdutoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService implements ProdutoServiceInterface {

    private ProdutoRepository produtoRepository;

    @Override
    public Produto salvar(ProdutoPostRequest produtoPostRequest) {
        return null;
    }

    @Override
    public void atualizar(ProdutoPutRequest produtoPutRequest) {

    }

    @Override
    public void delete(String produtoID) {

    }

    @Override
    public Produto encontrarPorIdOuLancarExcecao(String produtoID) {
        return null;
    }
}
