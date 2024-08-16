package com.loja.projetolojaweb2.service;

import com.loja.projetolojaweb2.domain.Produto;
import com.loja.projetolojaweb2.dto.produtoDto.ProdutoPostRequest;
import com.loja.projetolojaweb2.dto.produtoDto.ProdutoPutRequest;
import com.loja.projetolojaweb2.mapper.ProdutoMapper;
import com.loja.projetolojaweb2.Repository.ProdutoRepository;
import com.loja.projetolojaweb2.service.serviceInterface.ProdutoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ProdutoService implements ProdutoServiceInterface {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto salvar(ProdutoPostRequest produtoPostRequest) {
        return produtoRepository.save(ProdutoMapper.INSTANCE.toProduto(produtoPostRequest));
    }

    @Override
    public void atualizar(ProdutoPutRequest produtoPutRequest) {
        Produto produtoSalvo = encontrarPorIdOuLancarExcecao(String.valueOf(produtoPutRequest.getProdutoID()));
        Produto produto = ProdutoMapper.INSTANCE.toProduto(produtoPutRequest);
        produto.setProdutoID(produtoSalvo.getProdutoID());
        produtoRepository.save(produto);
    }

    @Override
    public void delete(String produtoID) {
        produtoRepository.delete(encontrarPorIdOuLancarExcecao(produtoID));
    }
    public List<Produto> listAll() {
        return  produtoRepository.findAll();
    }

    //regras
    @Override
    public Produto encontrarPorIdOuLancarExcecao(String produtoID) {

        return produtoRepository.findById(Long.valueOf(produtoID))
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Produto não encontrado"));
    }
}
