package com.loja.projetolojaweb2.service.serviceInterface;

import com.loja.projetolojaweb2.domain.Produto;
import com.loja.projetolojaweb2.dto.produtoDto.ProdutoPostRequest;
import com.loja.projetolojaweb2.dto.produtoDto.ProdutoPutRequest;

import java.util.List;

public interface ProdutoServiceInterface {

    public String nome();

    public Produto salvar(ProdutoPostRequest produtoPostRequest);

    public void atualizar(ProdutoPutRequest produtoPutRequest);

    public void delete(String produtoID);

    public Produto encontrarPorIdOuLancarExcecao(String produto);

    public List<Produto> listAll();

    public List<Produto> findByNome(String nome);

    public List<Produto> findByTipo(String tipo);

    public List<Produto> findByTamanho(String tamanho);

    public List<Produto> findByCor(String cor);

    public List<Produto> findByImagem(String imagem);

    public List<Produto> findByFabricante(String fabricante);

    public List<Produto> findByCategoria(String categoria);

    public List<Produto> findBySubcategoria(String subcategoria);

    public List<Produto> findByDescricao(String descricao);

    public List<Produto> findByValor(double valor);

    public List<Produto> findByQuantidade(int quantidade);
}
