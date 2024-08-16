package com.loja.projetolojaweb2.service.serviceInterface;

import com.loja.projetolojaweb2.domain.Produto;
import com.loja.projetolojaweb2.dto.produtoDto.ProdutoPostRequest;
import com.loja.projetolojaweb2.dto.produtoDto.ProdutoPutRequest;

public interface ProdutoServiceInterface {

    Produto salvar(ProdutoPostRequest produtoPostRequest);

    void atualizar(ProdutoPutRequest produtoPutRequest);

    void delete(String produtoID);

    Produto encontrarPorIdOuLancarExcecao(String produtoID);

}
